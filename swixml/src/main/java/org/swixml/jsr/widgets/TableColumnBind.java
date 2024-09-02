package org.swixml.jsr.widgets;

import javax.swing.table.TableColumn;

import org.jdesktop.beansbinding.Converter;

public class TableColumnBind
	extends TableColumn
	implements BindableBasicWidget
{
	private static final long serialVersionUID = 20240701L;

	@Override
	public final String getBindWith()
	{
		return (String) super.getIdentifier();
	}
	@Override
	public final void setBindWith(String bindWith)
	{
		super.setIdentifier(bindWith);
	}
	
	@Override
	public Converter<?, ?> getConverter()
	{
		return null;
	}
	@Override
	public void setConverter(Converter<?, ?> converter)
	{
	}
	
	boolean editable = false;
	public final boolean isEditable()
	{
		return editable;
	}
	public final void setEditable(boolean editable)
	{
		this.editable = editable;
	}

	String type = null;
	public final String getType()
	{
		return type;
	}
	public final void setType(String type)
	{
		this.type = type;
	}
	
	public TableColumnBind()
	{
		super();
	}

}

