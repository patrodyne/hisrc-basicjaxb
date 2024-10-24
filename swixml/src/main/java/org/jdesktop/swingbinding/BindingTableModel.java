package org.jdesktop.swingbinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.jdesktop.beansbinding.Binding.SyncFailure;
import org.jdesktop.beansbinding.BindingListener;
import org.jdesktop.beansbinding.Property;
import org.jdesktop.beansbinding.PropertyStateEvent;
import org.jdesktop.swingbinding.impl.AbstractColumnBinding;
import org.jdesktop.swingbinding.impl.ListBindingManager;

/**
 * 
 * @param <E>  the type of elements in the source {@code List}
 * @param <SS> the type of source object (on which the source property resolves
 *			   to {@code List})
 * @param <TS> the type of target object (on which the target property resolves
 *			   to {@code JComboBox})
 *			   
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class BindingTableModel<E, SS, TS>
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
	}
	
	/**
	 * Construct with a {@link JTableBinding} instance.
	 * 
	 * @param tableBinding Binds a {@code List} of objects to act as
	 *		  the rows of a {@code JTable}.
	 */
	public BindingTableModel(JTableBinding<E, SS, TS> tableBinding)
	{
		initialize(tableBinding);
	}
	
	public void initialize(JTableBinding<E, SS, TS> tableBinding)
	{
		setTableBinding(tableBinding);
		setListeners(new CopyOnWriteArrayList<TableModelListener>());
	}

	@Override
	@SuppressWarnings("unchecked")
	protected AbstractColumnBinding<SS, List<E>, TS, List<?>>[] getColBindings()
	{
		AbstractColumnBinding<SS, List<E>, TS, List<?>>[] bindings = null;
		if ( getTableBinding() != null )
		{
			bindings = new AbstractColumnBinding[getTableBinding().getColumnBindings().size()];
			bindings = getTableBinding().getColumnBindings().toArray(bindings);
		}
		else
		{
			bindings = new AbstractColumnBinding[0];
			ArrayList<AbstractColumnBinding<SS, List<E>, TS, List<?>>> bindingList =
				new ArrayList<AbstractColumnBinding<SS, List<E>, TS, List<?>>>();
			bindings = Collections.unmodifiableList(bindingList).toArray(bindings);
		}
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
		ColumnBinding cb = getTableBinding().getColumnBinding(columnIndex);
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
