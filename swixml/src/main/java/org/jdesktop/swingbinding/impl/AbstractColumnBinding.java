package org.jdesktop.swingbinding.impl;

import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.Property;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @author Shannon Hickey
 */
public abstract class AbstractColumnBinding<SS, SV, TS, TV>
	extends Binding<SS, SV, TS, TV>
{
	private int column;

	public AbstractColumnBinding(int column,
		Property<SS, SV> columnSource,
		Property<TS, TV> columnTarget, String name)
	{
		super(null, columnSource, null, columnTarget, name);
		this.column = column;
		setManaged(true);
	}

	public final int getColumn()
	{
		return column;
	}

	protected final void setColumn(int column)
	{
		this.column = column;
	}

	@Override
	public void bindImpl()
	{
	}

	@Override
	public void unbindImpl()
	{
	}
}
