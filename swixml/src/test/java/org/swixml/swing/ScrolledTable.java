package org.swixml.swing;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class ScrolledTable extends JFrame
{
	private static final long serialVersionUID = 20240801L;
	
	Object[] titles = { "First", "Second", "Third", "Fourth" };
	Object[][] data = { { "Zero, Zero", "Zero, One", "Zero, Two", "Zero, Three" },
						{ "One, Zero", "One, One", "One, Two", "One, Three" },
						{ "Two, Zero", "Two, One", "Two, Two", "Two, Three" },
						{ "Three, Zero", "Three, One", "Three, Two", "Three, Three" },
						{ "Four, Zero", "Four, One", "Four, Two", "Four, Three" },
						{ "Five, Zero", "Five, One", "Five, Two", "Five, Three" },
						{ "Six, Zero", "Six, One", "Six, Two", "Six, Three" } };

	public ScrolledTable()
	{
		Container container = getContentPane();
		JTable table = new JTable(data, titles);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		TableColumnModel colModel = table.getColumnModel();
		for ( int i = 0; i < titles.length; i++ )
		{
			TableColumn column = colModel.getColumn(i);
			column.setMinWidth(70);
			column.setPreferredWidth(70 + i * 20);
		}
		
		JScrollPane scrollpane = new JScrollPane(table);
//		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		container.add(scrollpane);
		setLocationRelativeTo(null);
		setSize(250, 120);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		new ScrolledTable();
	}
}
