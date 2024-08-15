package org.swixml.jsr.widgets;

import javax.swing.table.TableColumn;

public class TableColumnBind extends TableColumn
{
	private static final long serialVersionUID = 20240701L;
	boolean editable = false;
	String type = null;

	public TableColumnBind()
	{
		super();
	}

	public final String getBindWith()
	{
		return (String) super.getIdentifier();
	}

	public final void setBindWith(String bindWith)
	{
		super.setIdentifier(bindWith);
	}

	public final boolean isEditable()
	{
		return editable;
	}

	public final void setEditable(boolean editable)
	{
		this.editable = editable;
	}

	public final String getType()
	{
		return type;
	}

	public final void setType(String type)
	{
		this.type = type;
	}
}

