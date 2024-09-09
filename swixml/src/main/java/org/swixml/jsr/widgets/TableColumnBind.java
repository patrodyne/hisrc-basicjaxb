package org.swixml.jsr.widgets;

import javax.swing.table.TableColumn;

public class TableColumnBind
	extends TableColumn
	implements BindableWidget
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

