package org.swixml.examples.table;

import static java.lang.String.format;
import static org.jdesktop.observablecollections.ObservableCollections.observableList;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;

import org.jdesktop.application.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swixml.examples.SimpleBean;
import org.swixml.examples.SimpleBean2;

public class TableDialog extends JDialog
{
	private static final long serialVersionUID = 20240701L;
	private static final Logger logger = LoggerFactory.getLogger(TableDialog.class);
	
	public JTable table; /* automatically bound */
	public JTable table2; /* automatically bound */

	/**
	 * indicate type of list bound
	 */
	public Class<?> getMyDataClass()
	{
		return SimpleBean.class;
	}
	
	/**
	 * list bound
	 */
	private final List<SimpleBean> myData = observableList(new ArrayList<SimpleBean>());
	public final List<SimpleBean> getMyData()
	{
		return myData;
	}

	/**
	 * list bound
	 */
	private final List<SimpleBean2> myData2 = observableList(new ArrayList<SimpleBean2>());
	public final List<SimpleBean2> getMyData2()
	{
		return myData2;
	}

	/**
	 * Default constructor
	 */
	public TableDialog()
	{
		getMyData().add(new SimpleBean("Bartolomeo", 41));
		getMyData().add(new SimpleBean("Francesco", 38));
		getMyData().add(new SimpleBean("Vincenzo", 39));
		
		for ( int i = 0; i < 20; ++i )
			getMyData2().add(new SimpleBean2());
	}

	/**
	 * event raised when a row is selected on table
	 */
	@Action
	public void selectRow(ActionEvent e)
	{
		ListSelectionEvent ev = (ListSelectionEvent) e.getSource();
		logger.info(format("selectRow firstIndex=%d lastIndex=%d valueIsAdjusting=%b",
			ev.getFirstIndex(),	ev.getLastIndex(), ev.getValueIsAdjusting()));
	}

	/**
	 * event raised when a double click is performed upon row
	 */
	@Action
	public void activateRow(ActionEvent e)
	{
		logger.info(format("activate row [%d]\n[%s]",
			table.getSelectedRow(), getMyData().get(table.getSelectedRow())));
	}

	@Action
	public void activateRow2(ActionEvent e)
	{
		logger.info(format("activate row [%d]\n[%s]",
			table2.getSelectedRow(), getMyData2().get(table2.getSelectedRow())));
	}
}
