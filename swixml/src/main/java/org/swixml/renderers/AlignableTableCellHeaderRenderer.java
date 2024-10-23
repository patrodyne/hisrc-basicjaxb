package org.swixml.renderers;

import static javax.swing.UIManager.getDefaults;
import static javax.swing.UIManager.getLookAndFeelDefaults;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

@SuppressWarnings("serial")
public class AlignableTableCellHeaderRenderer extends DefaultTableCellRenderer
	implements UIResource
{
	private boolean horizontalTextPositionSet;
	private Icon sortArrow;
	private EmptyIcon emptyIcon = new EmptyIcon();

	public AlignableTableCellHeaderRenderer()
	{
		setHorizontalAlignment(JLabel.CENTER);
	}

	public AlignableTableCellHeaderRenderer(int alignment)
	{
		setHorizontalAlignment(alignment);
	}

	@Override
	public void setHorizontalTextPosition(int textPosition)
	{
		horizontalTextPositionSet = true;
		super.setHorizontalTextPosition(textPosition);
	}
	
	private static Color lookupDefaultColor(JComponent comp, ComponentUI ui, String key)
	{
		Color color = getDefaults().getColor(key);
		if ( color == null )
			color = getLookAndFeelDefaults().getColor(key);
		return color;
	}
	
	private static Icon lookupDefaultIcon(JComponent comp, ComponentUI ui, String key)
	{
		Icon icon = getDefaults().getIcon(key);
		if ( icon == null )
			icon = getLookAndFeelDefaults().getIcon(key);
		return icon;
	}
	
	private static Border lookupDefaultBorder(JComponent comp, ComponentUI ui, String key)
	{
		Border border = getDefaults().getBorder(key);
		if ( border == null )
			border = getLookAndFeelDefaults().getBorder(key);
		return border;
	}
	
	private static Boolean lookupDefaultBoolean(JComponent comp, ComponentUI ui, String key, boolean defaultValue)
	{
		return (key != null) ? getDefaults().getBoolean(key) : defaultValue;
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
		boolean isSelected, boolean hasFocus, int row, int column)
	{
		Icon sortIcon = null;

		boolean isPaintingForPrint = false;

		if (table != null)
		{
			JTableHeader header = table.getTableHeader();
			if (header != null)
			{
				Color fgColor = null;
				Color bgColor = null;
				if (hasFocus)
				{
					fgColor = lookupDefaultColor(this, ui, "TableHeader.focusCellForeground");
					bgColor = lookupDefaultColor(this, ui, "TableHeader.focusCellBackground");
				}
				
				if (fgColor == null)
					fgColor = header.getForeground();
				if (bgColor == null)
					bgColor = header.getBackground();
				
				setForeground(fgColor);
				setBackground(bgColor);

				setFont(header.getFont());

				isPaintingForPrint = header.isPaintingForPrint();
			}

			if (!isPaintingForPrint && table.getRowSorter() != null)
			{
				if (!horizontalTextPositionSet)
				{
					// There is a row sorter, and the developer hasn't
					// set a text position, change to leading.
					setHorizontalTextPosition(JLabel.LEADING);
				}
				SortOrder sortOrder = getColumnSortOrder(table, column);
				if (sortOrder != null)
				{
					switch(sortOrder)
					{
						case ASCENDING:
							sortIcon = lookupDefaultIcon(this, ui, "Table.ascendingSortIcon");
							break;
						case DESCENDING:
							sortIcon = lookupDefaultIcon(this, ui, "Table.descendingSortIcon");
							break;
						case UNSORTED:
							sortIcon = lookupDefaultIcon(this, ui, "Table.naturalSortIcon");
							break;
					}
				}
			}
		}

		setText(value == null ? "" : value.toString());
		setIcon(sortIcon);
		sortArrow = sortIcon;

		Border border = null;
		if (hasFocus)
			border = lookupDefaultBorder(this, ui, "TableHeader.focusCellBorder");
		if (border == null)
			border = lookupDefaultBorder(this, ui, "TableHeader.cellBorder");
		setBorder(border);

		return this;
	}

	public static SortOrder getColumnSortOrder(JTable table, int column)
	{
		SortOrder rv = null;
		if (table == null || table.getRowSorter() == null)
			return rv;
		java.util.List<? extends RowSorter.SortKey> sortKeys =
			table.getRowSorter().getSortKeys();
		if ( (sortKeys.size() > 0)
			&& (sortKeys.get(0).getColumn() == table.convertColumnIndexToModel(column)) )
		{
			rv = sortKeys.get(0).getSortOrder();
		}
		return rv;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		boolean b = lookupDefaultBoolean(this, ui, "TableHeader.rightAlignSortArrow", false);
		if (b && sortArrow != null)
		{
			//emptyIcon is used so that if the text in the header is right
			//aligned, or if the column is too narrow, then the text will
			//be sized appropriately to make room for the icon that is about
			//to be painted manually here.
			emptyIcon.width = sortArrow.getIconWidth();
			emptyIcon.height = sortArrow.getIconHeight();
			setIcon(emptyIcon);
			super.paintComponent(g);
			Point position = computeIconPosition(g);
			sortArrow.paintIcon(this, g, position.x, position.y);
		}
		else
			super.paintComponent(g);
	}

	private Point computeIconPosition(Graphics g)
	{
		FontMetrics fontMetrics = g.getFontMetrics();
		Rectangle viewR = new Rectangle();
		Rectangle textR = new Rectangle();
		Rectangle iconR = new Rectangle();
		Insets i = getInsets();
		viewR.x = i.left;
		viewR.y = i.top;
		viewR.width = getWidth() - (i.left + i.right);
		viewR.height = getHeight() - (i.top + i.bottom);
		SwingUtilities.layoutCompoundLabel
		(
			this,
			fontMetrics,
			getText(),
			sortArrow,
			getVerticalAlignment(),
			getHorizontalAlignment(),
			getVerticalTextPosition(),
			getHorizontalTextPosition(),
			viewR,
			iconR,
			textR,
			getIconTextGap()
		);
		int x = getWidth() - i.right - sortArrow.getIconWidth();
		int y = iconR.y;
		return new Point(x, y);
	}

	private class EmptyIcon implements Icon, Serializable
	{
		int width = 0;
		int height = 0;
		
		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {}
		
		@Override
		public int getIconWidth() { return width; }
		
		@Override
		public int getIconHeight() { return height; }
	}
}
