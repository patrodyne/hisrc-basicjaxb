package org.jdesktop.swingbinding;

import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ;
import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BindingListener;
import org.jdesktop.beansbinding.Property;
import org.jdesktop.beansbinding.PropertyStateEvent;
import org.jdesktop.beansbinding.PropertyStateListener;
import org.jdesktop.swingbinding.impl.AbstractColumnBinding;
import org.jdesktop.swingbinding.impl.ListBindingManager;

/**
 * Binds a {@code List} of objects to act as the rows of a {@code JTable}.
 * Each object in the source {@code List} represents one row in the {@code JTable}.
 * Mappings from properties of the source objects to columns are created by
 * adding {@link org.jdesktop.swingbinding.ColumnBinding ColumnBindings}
 * to a {@code JTableBinding}. Instances of {@code JTableBinding} are obtained by
 * calling one of the {@code createJTableBinding} methods in the {@code SwingBindings}
 * class.
 * 
 * <p>Here is an example of creating a binding from a {@code List} of {@code Person}
 * objects to a {@code JTable}:</p>
 * 
 * <pre><code>
 *    // create the person List
 *    List&lt;Person&gt; people = createPersonList();
 *
 *    // create the binding from List to JTable
 *    JTableBinding tb = SwingBindings.createJTableBinding(READ, people, jTable);
 *
 *    // define the properties to be used for the columns
 *    BeanProperty firstNameP = BeanProperty.create("firstName");
 *    BeanProperty lastNameP = BeanProperty.create("lastName");
 *    BeanProperty ageP = BeanProperty.create("age");
 *
 *    // configure how the properties map to columns
 *    tb.addColumnBinding(firstNameP).setColumnName("First Name");
 *    tb.addColumnBinding(lastNameP).setColumnName("Last Name");
 *    tb.addColumnBinding(ageP).setColumnName("Age").setColumnClass(Integer.class);
 *
 *    // realize the binding
 *    tb.bind();
 * </code></pre>
 * 
 * <p>The {@code JTable} target of a {@code JTableBinding} acts as a live view of
 * the objects in the source {@code List},
 * regardless of the update strategy (the meaning of the update strategy is
 * <a href="#CLARIFICATION">clarified later</a> in this document). {@code JTableBinding}
 * listens to the properties specified for the {@code ColumnBindings}, 
 * for all objects in the {@code List}, and updates the values
 * displayed in the {@code JTable} in response to change. All successful
 * edits made to {@code JTable} cell values are immediately committed back to
 * corresponding objects in the source {@code List}. If the {@code List} is an
 * instance of {@code ObservableList}, then changes to the {@code List} contents
 * (such as adding, removing or replacing an object) are also reflected in the
 * {@code JTable}. <b>Important:</b> Changing the contents of a non-observable
 * {@code List} while it is participating in a {@code JTableBinding} is unsupported,
 * resulting in undefined behavior and possible exceptions.</p>
 * 
 * <p>
 * A cell in the {@code JTable} is editable for any given row and
 * column when all of the following are true: the property specified for that column
 * by its {@code ColumnBinding} is writable for the object representing that row,
 * the {@code "editable"} property of the {@code JTableBinding} is {@code true}
 * (the default), and the {@code "editable"} property of the {@code ColumnBinding}
 * is {@code true} (the default).
 * 
 * <p>{@code JTableBinding} requires extra clarification on the operation of the
 * {@code refresh} and {@code save} methods and the meaning of the update
 * strategy. The target property of a {@code JTableBinding} is not the
 * target {@code JTable} property provided in the constructor, but rather a
 * private synthetic property representing the {@code List} of objects to show
 * in the target {@code JTable}. This synthetic property is readable/writeable
 * only when the {@code JTableBinding} is bound and the target {@code JTable}
 * property is readable with a {@code non-null} value.</p>
 * 
 * <p>It is this private synthetic property on which the {@code refresh} and
 * {@code save} methods operate; meaning that these methods simply cause syncing
 * between the value of the source {@code List} property and the value of the
 * synthetic target property (representing the {@code List} to be shown in the
 * target {@code JTable}). These methods do not, therefore, have anything to do
 * with refreshing or saving <i>values</i> in the {@code JTable}. Likewise, the update
 * strategy, which simply controls when {@code refresh} and {@code save} are
 * automatically called, also has nothing to do with refreshing or saving
 * <i>values</i> in the {@code JTable}.</p>
 * 
 * <p><b>Note:</b> At the current time, the {@code READ_WRITE} update strategy
 * is not useful for {@code JTableBinding}. To prevent unwanted confusion,
 * {@code READ_WRITE} is translated to {@code READ} by {@code JTableBinding's}
 * constructor.</p>
 * 
 * <p>{@code JTableBinding} works by installing a custom model on the target
 * {@code JTable}, as appropriate, to represent the source {@code List}. The
 * model is installed on a target {@code JTable} with the first successful call
 * to {@code refresh} with that {@code JTable} as the target. Subsequent calls
 * to {@code refresh} update the elements in this already-installed model.
 * The model is un-installed from a target {@code JTable} when either the
 * {@code JTableBinding} is unbound or when the target {@code JTable} property
 * changes to no longer represent that {@code JTable}. Note: When the model is
 * un-installed from a {@code JTable}, it is replaced with a {@code DefaultTableModel},
 * in order to leave the {@code JTable} functional.</p>
 * 
 * <p>Some of the above is easier to understand with an example. Let's consider
 * a {@code JTableBinding} ({@code binding}), with update strategy
 * {@code READ}, between a property representing a {@code List} ({@code listP})
 * and a property representing a {@code JTable} ({@code jTableP}). {@code listP}
 * and {@code jTableP} both start off readable, referring to a {@code non-null}
 * {@code List} and {@code non-null} {@code JTable} respectively. Let's look at
 * what happens for each of a sequence of events:</p>
 * 
 * <table border=1>
 *   <caption><b>Sequence of Events</b></caption>
 *   <tr><th>Sequence</th><th>Event</th><th>Result</th></tr>
 *   <tr>
 *     <td>1</td>
 *     <td>explicit call to {@code binding.bind()}</td>
 *     <td>
 *         - synthetic target property becomes readable/writeable
 *         <br>
 *         - {@code refresh()} is called
 *         <br>
 *         - model is installed on target {@code JTable}, representing list of objects
 *     </td>
 *   </tr>
 *   <tr >
 *     <td >2</td>
 *     <td>{@code listP} changes to a new {@code List}</td>
 *     <td>
 *         - {@code refresh()} is called
 *         <br>
 *         - model is updated with new list of objects
 *     </td>
 *   </tr>
 *   <tr>
 *     <td >3</td>
 *     <td>{@code jTableP} changes to a new {@code JTable}</td>
 *     <td>
 *         - model is uninstalled from old {@code JTable}
 *     </td>
 *   </tr>
 *   <tr >
 *     <td >4</td>
 *     <td>explicit call to {@code binding.refresh()}</td>
 *     <td>
 *         - model is installed on target {@code JTable}, representing list of objects
 *     </td>
 *   </tr>
 *   <tr >
 *     <td >5</td>
 *     <td>{@code listP} changes to a new {@code List}</td>
 *     <td>
 *         - {@code refresh()} is called
 *         <br>
 *         - model is updated with new list of objects
 *     </td>
 *   </tr>
 *   <tr >
 *     <td >6</td>
 *     <td>explicit call to {@code binding.unbind()}</td>
 *     <td>
 *         - model is uninstalled from target {@code JTable}
 *     </td>
 *   </tr>
 * </table>
 * 
 * <p>Notice that in <a href="#STEP3">step 3</a>, when the value
 * of the {@code JTable} property changed, the new {@code JTable} did not
 * automatically get the model with the elements applied to it. A change to the
 * target value should not cause an {@code AutoBinding} to sync the target from
 * the source. Step 4 forces a sync by explicitly calling {@code refresh}.
 * Alternatively, it could be caused by any other action that results
 * in a {@code refresh} (for example, the source property changing value, or an
 * explicit call to {@code unbind} followed by {@code bind}).</p>
 * 
 * <p>{@code ColumnBindings} are managed by the {@code JTableBinding}. They are not
 * to be explicitly bound, unbound, added to a {@code BindingGroup}, or accessed
 * in a way that is not allowed for a managed binding. {@code BindingListeners}
 * added to a {@code ColumnBinding} are notified at the time an edited {@code JTable} value
 * is to be committed back to the source {@code List}. They receive notification of either
 * {@code synced} or {@code syncFailed}. {@code BindingListeners} added to the
 * {@code JTableBinding} itself are also notified of {@code sync} and {@code syncFailed}
 * for the {@code JTableBinding's ColumnBindings}.</p>
 * 
 * <p>In addition to binding the elements of a {@code JTable}, it is possible to
 * bind to the selection of a {@code JTable}. When binding to the selection of a {@code JTable}
 * backed by a {@code JTableBinding}, the selection is always in terms of elements
 * from the source {@code List}. See the list of <a href="package-summary.html#SWING_PROPS">
 * interesting swing properties</a> in the package summary for more details.</p>
 * 
 * @author Shannon Hickey
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @apiNote {@code <E>}  the type of elements in the source {@code List}
 * @apiNote {@code <SS>} the type of source object (on which the source property resolves to {@code List})
 * @apiNote {@code <TS>} the type of target object (on which the target property resolves to {@code JTable})
 */
public final class JTableBinding<E, SS, TS>
	extends AutoBinding<SS, List<E>, TS, List<?>>
{
    private Property<TS, ? extends JTable> tableP;
    private ElementsProperty<TS> elementsP;
    private Handler handler = new Handler();
    private JTable table;
    private BindingTableModel model;
    private boolean editable = true;
    private List<ColumnBinding<?,?,?>> columnBindings = new ArrayList<>();

    /**
     * Constructs an instance of {@code JTableBinding}.
     *
     * @param strategy the update strategy
     * @param sourceObject the source object
     * @param sourceListProperty a property on the source object that resolves to the {@code List} of elements
     * @param targetObject the target object
     * @param targetJTableProperty a property on the target object that resolves to a {@code JTable}
     * @param name a name for the {@code JTableBinding}
     * @throws IllegalArgumentException if the source property or target property is {@code null}
     */
    protected JTableBinding(UpdateStrategy strategy,
    	SS sourceObject, Property<SS, List<E>> sourceListProperty,
    	TS targetObject, Property<TS, ? extends JTable> targetJTableProperty,
    	String name)
    {
        super(strategy == READ_WRITE ? READ : strategy,
              sourceObject, sourceListProperty,
              targetObject, new ElementsProperty<TS>(),
              name);

        if (targetJTableProperty == null)
            throw new IllegalArgumentException("target JTable property can't be null");

        tableP = targetJTableProperty;
        elementsP = (ElementsProperty<TS>) getTargetProperty();
    }

    @Override
	protected void bindImpl()
    {
        elementsP.setAccessible(isTableAccessible());
        tableP.addPropertyStateListener(getTargetObject(), handler);
        elementsP.addPropertyStateListener(null, handler);
        super.bindImpl();
    }

    @Override
	protected void unbindImpl()
    {
        elementsP.removePropertyStateListener(null, handler);
        tableP.removePropertyStateListener(getTargetObject(), handler);
        elementsP.setAccessible(false);
        cleanupForLast();
        super.unbindImpl();
    }

    private boolean isTableAccessible()
    {
        return tableP.isReadable(getTargetObject()) && tableP.getValue(getTargetObject()) != null;
    }

    private boolean isTableAccessible(Object value)
    {
        return value != null && value != PropertyStateEvent.UNREADABLE;
    }

    private void cleanupForLast()
    {
        if (table == null)
            return;

        table.setModel(new DefaultTableModel());
        table = null;
        model.setElements((List<?>) null, true);
        model = null;
    }
    
    /**
     * Sets whether or not the cells of the table should be editable.
     * The default for this property is {@code true}.
     * See this <a href="#EDITABILITY">paragraph</a> in the class level
     * documentation on editability.
     *
     * @param editable whether or not the cells of the table should be editable
     */
    public void setEditable(boolean editable)
    {
        this.editable = editable;
    }

    /**
     * Returns whether or not the cells of the table should be editable.
     * The default for this property is {@code true}.
     * See this <a href="#EDITABILITY">paragraph</a> in the class level
     * documentation on editability.
     *
     * @return whether or not the cells of the table should be editable
     */
    public boolean isEditable()
    {
        return editable;
    }

    /**
     * Creates a {@code ColumnBinding} and adds it to the end of the list of {@code ColumnBindings}
     * maintained by this {@code JTableBinding}.
     * <p>
     * The list of {@code ColumnBindings} dictates the columns to be displayed in the
     * {@code JTable}, with a {@code ColumnBinding's} order in the list determining its
     * table model index.
     *
     * @param columnProperty the property with which to derive cell values from the
     *                       elements of the source {@code List}
     *                       
     * @return the {@code ColumnBinding}
     * 
     * @throws IllegalArgumentException if {@code columnProperty} is {@code null}
     * 
     * @see org.jdesktop.swingbinding.ColumnBinding
     */
    public ColumnBinding<?, ?, ?> addColumnBinding(Property<?, ?> columnProperty)
    {
        return addColumnBinding(columnProperty, null);
    }

    /**
     * Creates a named {@code ColumnBinding} and adds it to the end of the list of {@code ColumnBindings}
     * maintained by this {@code JTableBinding}.
     * <p>
     * The list of {@code ColumnBindings} dictates the columns to be displayed in the
     * {@code JTable}, with a {@code ColumnBinding's} order in the list determining its
     * table model index.
     *
     * @param columnProperty the property with which to derive cell values from the
     *                       elements of the source {@code List}
     * @param name a name for the column binding
     * @return the {@code ColumnBinding}
     * @throws IllegalArgumentException if {@code columnProperty} is {@code null}
     * @see org.jdesktop.swingbinding.ColumnBinding
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public ColumnBinding<?, ?, ?> addColumnBinding(Property<?, ?> columnProperty, String name)
    {
        throwIfBound();

        if (columnProperty == null)
        {
            throw new IllegalArgumentException("can't have null column property");
        }

        if (name == null && JTableBinding.this.getName() != null)
        {
            name = JTableBinding.this.getName() + ".COLUMN_BINDING";
        }

		ColumnBinding<?, ?, ?> binding =
			new ColumnBinding(columnBindings.size(), columnProperty, name);
        columnBindings.add(binding);
        return binding;
    }

    /**
     * Creates a {@code ColumnBinding} and inserts it at the given index into the list
     * of {@code ColumnBindings} maintained by this {@code JTableBinding}.
     * <p>
     * The list of {@code ColumnBindings} dictates the columns to be displayed in the
     * {@code JTable}, with a {@code ColumnBinding's} order in the list determining its
     * table model index.
     *
     * @param index the index at which to insert the {@code ColumnBinding}
     * @param columnProperty the property with which to derive cell values from the
     *                       elements of the source {@code List}
     * @return the {@code ColumnBinding}
     * @throws IllegalArgumentException if {@code columnProperty} is {@code null}
     * @see org.jdesktop.swingbinding.ColumnBinding
     */
    public ColumnBinding<?, ?, ?> addColumnBinding(int index, Property<E, ?> columnProperty) {
        return addColumnBinding(index, columnProperty, null);
    }

    /**
     * Creates a {@code ColumnBinding} and inserts it at the given index into the list
     * of {@code ColumnBindings} maintained by this {@code JTableBinding}.
     * <p>
     * The list of {@code ColumnBindings} dictates the columns to be displayed in the
     * {@code JTable}, with a {@code ColumnBinding's} order in the list determining its
     * table model index.
     *
     * @param index the index at which to insert the {@code ColumnBinding}
     * @param columnProperty the property with which to derive cell values from the
     *                       elements of the source {@code List}
     * @param name a name for the {@code ColumnBinding}
     * 
     * @return the {@code ColumnBinding}
     * 
     * @throws IllegalArgumentException if {@code columnProperty} is {@code null}
     * 
     * @see org.jdesktop.swingbinding.ColumnBinding
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public ColumnBinding<?, ?, ?> addColumnBinding(int index, Property<E, ?> columnProperty, String name) {
        throwIfBound();

        if (columnProperty == null) {
            throw new IllegalArgumentException("can't have null column property");
        }

        if (name == null && JTableBinding.this.getName() != null) {
            name = JTableBinding.this.getName() + ".COLUMN_BINDING";
        }
        
        ColumnBinding binding = new ColumnBinding(index, columnProperty, name);
        columnBindings.add(index, binding);
        adjustIndices(index + 1, true);
        return binding;
    }

    /**
     * Removes the given {@code ColumnBinding} from the list maintained
     * by this {@code JTableBinding}.
     * <p>
     * The list of {@code ColumnBindings} dictates the columns to be displayed in the
     * {@code JTable}, with a {@code ColumnBinding's} order in the list determining its
     * table model index.
     *
     * @param binding the {@code ColumnBinding} to remove
     * @see #addColumnBinding(Property, String)
     */
    @SuppressWarnings("rawtypes")
	public boolean removeColumnBinding(ColumnBinding binding) {
        throwIfBound();
        boolean retVal = columnBindings.remove(binding);

        if (retVal) {
            adjustIndices(binding.getColumn(), false);
        }

        return retVal;
    }

    /**
     * Removes the {@code ColumnBinding} with the given index from the list maintained
     * by this {@code JTableBinding}.
     * <p>
     * The list of {@code ColumnBindings} dictates the columns to be displayed in the
     * {@code JTable}, with a {@code ColumnBinding's} order in the list determining its
     * table model index.
     *
     * @param index the index of the {@code ColumnBinding} to remove
     * @see #addColumnBinding(Property, String)
     */
    @SuppressWarnings("rawtypes")
	public ColumnBinding removeColumnBinding(int index) {
        throwIfBound();
        ColumnBinding retVal = columnBindings.remove(index);
        
        if (retVal != null) {
            adjustIndices(index, false);
        }

        return retVal;
    }

    /**
     * Returns the {@code ColumnBinding} with the given index in the list maintained
     * by this {@code JTableBinding}.
     * <p>
     * The list of {@code ColumnBindings} dictates the columns to be displayed in the
     * {@code JTable}, with a {@code ColumnBinding's} order in the list determining its
     * table model index.
     *
     * @param index the index of the {@code ColumnBinding} to return
     * @return the {@code ColumnBinding} at the given index
     * @see #addColumnBinding(Property, String)
     */
    @SuppressWarnings("rawtypes")
	public ColumnBinding getColumnBinding(int index) {
        return columnBindings.get(index);
    }

    /**
     * Returns an unmodifiable copy of the list of {@code ColumnBindings} maintained
     * by this {@code JTableBinding}.
     * <p>
     * The list of {@code ColumnBindings} dictates the columns to be displayed in the
     * {@code JTable}, with a {@code ColumnBinding's} order in the list determining its
     * table model index.
     *
     * @return the list of {@code ColumnBindings}
     * @see #addColumnBinding(Property, String)
     */
    @SuppressWarnings("rawtypes")
	public List<ColumnBinding> getColumnBindings() {
        return Collections.unmodifiableList(columnBindings);
    }

    @SuppressWarnings("rawtypes")
	private void adjustIndices(int start, boolean up) {
        int size = columnBindings.size();
        for (int i = start; i < size; i++) {
            ColumnBinding cb = columnBindings.get(i);
            cb.adjustColumn(cb.getColumn() + (up ? 1 : -1));
        }
    }

	private class Handler implements PropertyStateListener
	{
		@Override
		public void propertyStateChanged(PropertyStateEvent pse)
		{
			if ( !pse.getValueChanged() )
				return;
			
			if ( pse.getSourceProperty() == tableP )
			{
				cleanupForLast();
				boolean wasAccessible = isTableAccessible(pse.getOldValue());
				boolean isAccessible = isTableAccessible(pse.getNewValue());
				if ( wasAccessible != isAccessible )
					elementsP.setAccessible(isAccessible);
				else if ( elementsP.isAccessible() )
					elementsP.setValueAndIgnore(null, null);
			}
			else
			{
				@SuppressWarnings("unchecked")
				ElementsProperty<TS>.ElementsPropertyStateEvent epse =
					(ElementsProperty<TS>.ElementsPropertyStateEvent) pse;
				if ( epse.shouldIgnore() )
					return;
				
				if ( table == null )
				{
					table = tableP.getValue(getTargetObject());
					model = new BindingTableModel();
					table.setModel(model);	
				}
				model.setElements((List<?>) pse.getNewValue(), true);
			}
		}
	}

	private final class BindingTableModel
		extends ListBindingManager<SS, List<E>, TS, List<?>>
		implements TableModel
	{
		private List<TableModelListener> listeners;
		public List<TableModelListener> getListeners()
		{
			return listeners;
		}
		public void setListeners(List<TableModelListener> listeners)
		{
			this.listeners = listeners;
		}

		private JTableBinding<E, SS, TS> tableBinding;
		public JTableBinding<E, SS, TS> getTableBinding()
		{
			if ( tableBinding == null )
				setTableBinding((JTableBinding<E, SS, TS>) JTableBinding.this);
			return tableBinding;
		}
		public void setTableBinding(JTableBinding<E, SS, TS> tableBinding)
		{
			this.tableBinding = tableBinding;
		}

		/**
		 * Default constructor.
		 */
		public BindingTableModel()
		{
			setListeners(new CopyOnWriteArrayList<TableModelListener>());
		}

		@Override
		@SuppressWarnings("unchecked")
		protected AbstractColumnBinding<SS, List<E>, TS, List<?>>[] getColBindings()
		{
			AbstractColumnBinding<SS, List<E>, TS, List<?>>[] bindings = null;
			bindings = new AbstractColumnBinding[getColumnBindings().size()];
			bindings = getColumnBindings().toArray(bindings);
			return bindings;
		}

		@Override
		public int getRowCount()
		{
			return size();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex)
		{
			return valueAt(rowIndex, columnIndex);
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void setValueAt(Object value, int rowIndex, int columnIndex)
		{
			ColumnBinding cb = getColumnBinding(columnIndex);
			BindingListener[] cbListeners = cb.getBindingListeners();
			BindingListener[] tbListeners = getTableBinding().getBindingListeners();
			cb.setSourceObjectUnmanaged0((E) this.getElement(rowIndex));
			cb.setEditingObject(value);
			cb.bindUnmanaged0();
			
			for ( BindingListener listener : tbListeners )
				listener.bindingBecameBound(cb);
			
			Property<?, ?> targetProperty = cb.getTargetProperty();
			Object targetObject = cb.getTargetObject();
			boolean valueChanged = true;
			Object oldValue = getValueAt(rowIndex, columnIndex);
			Object newValue = value;
			boolean writeableChanged = false;
			Property sourceProperty = cb.getSourceProperty();
			Object sourceObject = cb.getSourceObject();
			boolean isWriteable = sourceProperty.isWriteable(sourceObject);
			
			PropertyStateEvent pse = new PropertyStateEvent(targetProperty, targetObject,
				valueChanged, oldValue, newValue, writeableChanged, isWriteable);
			
			for ( BindingListener listener : cbListeners )
				listener.targetChanged(cb, pse);
			
			for ( BindingListener listener : tbListeners )
				listener.targetChanged(cb, pse);
			
			SyncFailure failure = cb.saveUnmanaged0();
			if ( failure == null )
			{
				for ( BindingListener listener : cbListeners )
					listener.synced(cb);
				for ( BindingListener listener : tbListeners )
					listener.synced(cb);
			}
			else
			{
				for ( BindingListener listener : cbListeners )
					listener.syncFailed(cb, failure);
				for ( BindingListener listener : tbListeners )
					listener.syncFailed(cb, failure);
			}
			cb.unbindUnmanaged0();
			for ( BindingListener listener : tbListeners )
				listener.bindingBecameUnbound(cb);
			cb.setEditingObject(null);
			cb.setSourceObjectUnmanaged0(null);
		}

		@Override
		public Class<?> getColumnClass(int columnIndex)
		{
			Class<?> klass = getTableBinding().getColumnBinding(columnIndex).getColumnClass();
			return klass == null ? Object.class : klass;
		}

		@Override
		protected void allChanged()
		{
			fireTableModelEvent(new TableModelEvent(this, 0, Integer.MAX_VALUE));
		}

		@Override
		protected void valueChanged(int row, int column)
		{
			fireTableModelEvent(new TableModelEvent(this, row, row, column));
		}

		@Override
		protected void added(int row, int length)
		{
			// enforced by ListBindingManager
			assert length > 0; 
			
			fireTableModelEvent(
				new TableModelEvent(this, row, row + length - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
		}

		@Override
		protected void removed(int row, int length)
		{
			// enforced by ListBindingManager
			assert length > 0; 
			
			fireTableModelEvent(
				new TableModelEvent(this, row, row + length - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE));
		}

		@Override
		protected void changed(int row)
		{
			fireTableModelEvent(new TableModelEvent(this, row, row, TableModelEvent.ALL_COLUMNS));
		}

		@SuppressWarnings("rawtypes")
		@Override
		public String getColumnName(int columnIndex)
		{
			ColumnBinding binding = getTableBinding().getColumnBinding(columnIndex);
			return binding.getColumnName() == null ? binding.getSourceProperty().toString() : binding.getColumnName();
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex)
		{
			if ( !getTableBinding().isEditable() )
				return false;
			
			ColumnBinding binding = getTableBinding().getColumnBinding(columnIndex);
			
			if ( !binding.isEditable() )
				return false;
			
			return binding.getSourceProperty().isWriteable(getElement(rowIndex));
		}

		@Override
		public void addTableModelListener(TableModelListener l)
		{
			getListeners().add(l);
		}

		@Override
		public void removeTableModelListener(TableModelListener l)
		{
			getListeners().remove(l);
		}

		private void fireTableModelEvent(TableModelEvent e)
		{
			for ( TableModelListener listener : getListeners() )
				listener.tableChanged(e);
		}

		@Override
		public int getColumnCount()
		{
			return columnCount();
		}
	}
}
