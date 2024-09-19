package org.swixml.examples.table;

import static java.lang.String.format;
import static org.jdesktop.observablecollections.ObservableCollections.observableList;
import static org.jvnet.basicjaxb.lang.ClassUtils.identifySimple;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.JTableHeader;

import org.jdesktop.application.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swixml.examples.SimpleBean;
import org.swixml.examples.SimpleBean2;
import org.swixml.jsr.widgets.JTableBind;

public class TableDialog extends JDialog
{
	private static final long serialVersionUID = 20240701L;
	private static final Logger logger = LoggerFactory.getLogger(TableDialog.class);
	
	/* automatically bound */
	public JTableBind table1;
	public JTableBind table2;

	/* automatically bound */
	public JTableHeader tableHeader1;
	public JTableHeader tableHeader2;

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
	private final List<SimpleBean> myData1 = observableList(new ArrayList<SimpleBean>());
	public final List<SimpleBean> getMyData1()
	{
		return myData1;
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
		getMyData1().add(new SimpleBean("Bartolomeo", 41));
		getMyData1().add(new SimpleBean("Francesco", 38));
		getMyData1().add(new SimpleBean("Vincenzo", 39));
		
		for ( int i = 0; i < 20; ++i )
			getMyData2().add(new SimpleBean2());
	}

	/**
	 * event raised when a row is selected on table
	 */
	@Action
	public void selectRow(ActionEvent ae)
	{
		ListSelectionEvent se = (ListSelectionEvent) ae.getSource();
		ListSelectionModel sm = (ListSelectionModel) se.getSource();
		logger.debug(format("selectRow model=%s firstIndex=%d lastIndex=%d valueIsAdjusting=%b",
			identifySimple(sm), se.getFirstIndex(), se.getLastIndex(), se.getValueIsAdjusting()));
		logger.info("TH1A: [{}] [{}]", identifySimple(table1.getTableHeader()), table1.getTableHeader().getFont());
		logger.info("TH1B: [{}] [{}]", identifySimple(tableHeader1), tableHeader1.getFont());
		logger.info("TH2A: [{}] [{}]", identifySimple(table2.getTableHeader()), table2.getTableHeader().getFont());
		logger.info("TH2B: [{}] [{}]", identifySimple(tableHeader2), tableHeader2.getFont());
	}
	
	/**
	 * event raised when a double click is performed upon table 1 row
	 */
	@Action
	public void activateRow(ActionEvent ae)
	{
		MouseEvent me = (MouseEvent) ae.getSource();
		JTableBind st = (JTableBind) me.getSource();
		int sr = st.getSelectedRow();
		List<?> bl = st.getBindList();
		logger.info(format("activate object=[%s] row [%d]\n[%s]", identifySimple(st),
			sr, bl.get(sr)));
	}
}
