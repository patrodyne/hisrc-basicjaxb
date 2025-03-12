package org.swixml.swing;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.swixml.SwingEngine;

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
		setSize(280, 180);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args)
	{
		SwingEngine.invokeLater(ScrolledTable.class);
	}
}
