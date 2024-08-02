package org.jdesktop.swingbinding;

import org.jdesktop.beansbinding.Property;
import org.jdesktop.beansbinding.PropertyStateListener;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @param <S>
 * @param <V>
 */
public class ColumnProperty<S, V> extends Property<S, V>
{
	private ColumnBinding<?, ?, ?> columnBinding;
	public ColumnBinding<?, ?, ?> getColumnBinding()
	{
		return columnBinding;
	}
	public void setColumnBinding(ColumnBinding<?, ?, ?> columnBinding)
	{
		this.columnBinding = columnBinding;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Class<? extends V> getWriteType(S source)
	{
		Class<?> writeType = columnBinding.getColumnClass() != null
			? columnBinding.getColumnClass()
			: Object.class;
		return (Class<? extends V>) writeType;
	}

	@Override
	@SuppressWarnings("unchecked")
	public V getValue(S source)
	{
		if ( columnBinding.isBound() )
			return (V) columnBinding.getEditingObject();
		
		throw new UnsupportedOperationException();
	}

	@Override
	public void setValue(S source, V value)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isReadable(S source)
	{
		return columnBinding.isBound();
	}

	@Override
	public boolean isWriteable(S source)
	{
		return true;
	}


	/**
	 * Default constructor.
	 */
	public ColumnProperty()
	{
		super();
	}
	
	/**
	 * Construct with a {@link ColumnBinding}.
	 * 
	 * @param columnBinding a binding between a property of the elements in the
	 *        {@code JTableBinding's} source {@code List}, and a column in the table.
	 */
	public ColumnProperty(ColumnBinding<?, ?, ?> columnBinding)
	{
		super();
		setColumnBinding(columnBinding);
	}
	
	@Override
	public void addPropertyStateListener(S source, PropertyStateListener listener)
	{
	}

	@Override
	public void removePropertyStateListener(S source, PropertyStateListener listener)
	{
	}

	@Override
	public PropertyStateListener[] getPropertyStateListeners(S source)
	{
		return new PropertyStateListener[0];
	}
}
