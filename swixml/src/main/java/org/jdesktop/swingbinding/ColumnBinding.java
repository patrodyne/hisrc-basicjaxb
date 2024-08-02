package org.jdesktop.swingbinding;

import java.util.List;

import org.jdesktop.beansbinding.Property;
import org.jdesktop.swingbinding.impl.AbstractColumnBinding;

/**
 * {@code ColumnBinding} represents a binding between a property of the elements
 * in the {@code JTableBinding's} source {@code List}, and a column in the
 * table. Each {@code ColumnBinding} added to a {@code JTableBinding} represents
 * a column to be displayed by the {@code JTable}. A value for any given row in
 * a column is acquired by fetching the value of the associated
 * {@code ColumnBinding's} source property for the element in the source
 * {@code List} representing that row.
 * <p>
 * A {@code Converter} may be specified on a {@code ColumnBinding}, as may be a
 * {@code Validator}. Validation occurs at the time a cell value is to be
 * committed back to the source {@code List}.
 * <p>
 * {@code BindingListeners} registered on a {@code ColumnBinding} are notified
 * of successful {@code sync} or {@code syncFailure}. These notifications are
 * also sent to the {@code JTableBinding's} {@code BindingListeners}.
 * <p>
 * {@code ColumnBindings} are managed by their {@code JTableBinding}. They are
 * not to be explicitly bound, unbound, added to a {@code BindingGroup}, or
 * accessed in a way that is not allowed for a managed binding.
 *
 * @see org.jdesktop.swingbinding.JTableBinding#addColumnBinding(Property,
 *      String)
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class ColumnBinding<E, SS, TS>
	extends AbstractColumnBinding<SS, List<E>, TS, List<?>>
{
	private String columnName;
	private boolean editable = true;
	private Class<?> columnClass;

	/**
	 * Returns the column class to be used by {@code JTable} to determine the
	 * renderer and editor for the column represented by this
	 * {@code ColumnBinding}.
	 *
	 * @see #setColumnClass
	 * @see javax.swing.table.TableModel#getColumnClass
	 */
	public Class<?> getColumnClass()
	{
		return columnClass == null ? Object.class : columnClass;
	}

	/**
	 * Sets the column class to be used by {@code JTable} to determine the
	 * renderer and editor for the column represented by this
	 * {@code ColumnBinding}.
	 *
	 * @param columnClass the column class
	 * @return the {@code ColumnBinding} itself, to allow for method chaining
	 * @see javax.swing.table.TableModel#getColumnClass
	 */
	public ColumnBinding<E, SS, TS> setColumnClass(Class<?> columnClass)
	{
		throwIfBound();
		this.columnClass = columnClass;
		return this;
	}

	private Object editingObject;

	public Object getEditingObject()
	{
		return editingObject;
	}

	public void setEditingObject(Object editingObject)
	{
		this.editingObject = editingObject;
	}

	@SuppressWarnings("unused")
	private boolean editableSet;

	/**
	 * Returns the {@code Binding's} source {@link ColumnProperty}, which may
	 * not be {@code null}.
	 *
	 * @return The {@code Binding's} source {@link ColumnProperty},
	 *         {@code non-null}
	 * 
	 * @see #getSourceProperty
	 */
	public ColumnProperty<SS, List<E>> getSourceColumnProperty()
	{
		return (ColumnProperty<SS, List<E>>) getSourceProperty();
	}

	/**
	 * Returns the {@code Binding's} target {@link ColumnProperty}, which may
	 * not be {@code null}.
	 *
	 * @return The {@code Binding's} target {@link ColumnProperty},
	 *         {@code non-null}
	 * 
	 * @see #getTargetProperty
	 */
	public ColumnProperty<TS, List<?>> getTargetColumnProperty()
	{
		return (ColumnProperty<TS, List<?>>) getTargetProperty();
	}

	public ColumnBinding(int column, Property<SS, List<E>> columnProperty, String name)
	{
		super(column, columnProperty, new ColumnProperty<TS, List<?>>(), name);
		getTargetColumnProperty().setColumnBinding(this);
	}

	public void adjustColumn(int newCol)
	{
		setColumn(newCol);
	}

	/**
	 * Sets a name for the column represented by this {@code ColumnBinding}.
	 * This is used to initialize the table's column header name. If
	 * {@code null} is specified, the {@code toString()} value of the
	 * {@code ColumnBinding's} source property is used.
	 *
	 * @param name the name
	 * @return the {@code ColumnBinding} itself, to allow for method chaining
	 * @see javax.swing.table.TableModel#getColumnName
	 */
	public ColumnBinding<E, SS, TS> setColumnName(String name)
	{
		throwIfBound();
		this.columnName = name;
		return this;
	}

	/**
	 * Returns the name for the column represented by this
	 * {@code ColumnBinding}. This is used to initialize the table's column
	 * header name. If no name has been specified, or if it has been set to
	 * {@code null}, the {@code toString()} value of the {@code ColumnBinding's}
	 * source property is returned.
	 *
	 * @return the name for the column
	 * @see #setColumnName
	 * @see javax.swing.table.TableModel#getColumnName
	 */
	public String getColumnName()
	{
		return columnName == null ? getSourceProperty().toString() : columnName;
	}

	/**
	 * Sets whether or not the cells of the column should be editable. The
	 * default for this property is {@code true}. See this
	 * <a href="JTableBinding.html#EDITABILITY">paragraph</a> in the class level
	 * documentation on editability.
	 *
	 * @param editable whether or not the cells of the column should be editable
	 * @return the {@code ColumnBinding} itself, to allow for method chaining
	 */
	public ColumnBinding<E, SS, TS> setEditable(boolean editable)
	{
		this.editable = editable;
		return this;
	}

	/**
	 * Returns whether or not the cells of the column should be editable. The
	 * default for this property is {@code true}. See this
	 * <a href="JTableBinding.html#EDITABILITY">paragraph</a> in the class level
	 * documentation on editability.
	 *
	 * @return whether or not the cells of the column should be editable
	 */
	public boolean isEditable()
	{
		return editable;
	}

	public void bindUnmanaged0()
	{
		bindUnmanaged();
	}

	public void unbindUnmanaged0()
	{
		unbindUnmanaged();
	}

	public SyncFailure saveUnmanaged0()
	{
		return saveUnmanaged();
	}

	public void setSourceObjectUnmanaged0(SS source)
	{
		setSourceObjectUnmanaged(source);
	}
}
