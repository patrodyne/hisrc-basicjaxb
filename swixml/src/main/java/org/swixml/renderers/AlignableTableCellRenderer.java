package org.swixml.renderers;

import javax.swing.table.DefaultTableCellRenderer;

public class AlignableTableCellRenderer extends DefaultTableCellRenderer
{
	private static final long serialVersionUID = 20241001L;

	public AlignableTableCellRenderer(int horizontalAlignment)
	{
		setHorizontalAlignment(horizontalAlignment);
	}
}
