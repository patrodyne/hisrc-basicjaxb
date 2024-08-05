package org.swixml.examples;

import static org.jdesktop.observablecollections.ObservableCollections.observableList;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TreeSelectionEvent;

import org.jdesktop.application.Action;

/**
 * The ApplicationTest shows in the usage of client attributes in swixml tags.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml 0.98
 */
public class BindingExamplesFrame extends JFrame
{
	private static final long serialVersionUID = 20240701L;
	
	public JButton btn1, btn2;
	public JTextArea ta;
	public Container panel_dlg;
	public ActionMap actionMap;
	public JLabel statusbar;
	public JTextField tv;
	public JTable testTable;
	
	private TestDialog testDialog = null;
	
	final CustomListCellRenderer listRenderer = new CustomListCellRenderer();
	boolean connected = false;
	List<SimpleBean> myData = observableList(new ArrayList<SimpleBean>());
	final List<String> comboList = observableList(Arrays.asList("item1", "item2", "item3"));
	TestTreeModel myTree = new TestTreeModel();
	
	private String testValue = "TEST";

	public final CustomListCellRenderer getListRenderer()
	{
		return listRenderer;
	}

	public Class<?> getMyDataClass()
	{
		return SimpleBean.class;
	}

	public final List<String> getComboList()
	{
		return comboList;
	}

	public List<SimpleBean> getMyData()
	{
		return myData;
	}

	public TestTreeModel getMyTree()
	{
		return myTree;
	}

	public Dimension getFrameSize()
	{
		return new Dimension(100, 600);
	}

	@Action
	public void comboAction()
	{
		System.out.println("comboAction");
	}

	@Action
	public void listAction()
	{
		System.out.println("listAction");
	}

	@Action(enabledProperty = "connected")
	public void test()
	{
		System.out.printf("hello world! %s\n", testValue);
		setTestValue("hello world!");
		firePropertyChange("testValue", null, "hello world!");
	}

	@Action
	public void selectNode(ActionEvent e)
	{
		TreeSelectionEvent ev = (TreeSelectionEvent) e.getSource();
		System.out.printf("selectNode path=%s\n", ev.getPath().toString());
		myData.add(new SimpleBean(ev.getPath().toString(), 0));
	}

	@Action
	public void selectRow(ActionEvent e)
	{
		ListSelectionEvent ev = (ListSelectionEvent) e.getSource();
		System.out.printf("selectRow firstIndex=%d lastIndex=%d valueIsAdjusting=%b\n",
			ev.getFirstIndex(), ev.getLastIndex(), ev.getValueIsAdjusting());
	}

	@Action
	public void activateRow(ActionEvent e)
	{
		System.out.printf("activate row [%d]\n", testTable.getSelectedRow());
	}

	@Action()
	public void show(ActionEvent e)
	{
		if ( (e.getSource() == btn1) ||  (e.getSource() == btn2) )
		{
			JButton btn = (JButton) e.getSource();
			ta.setText("X:" + btn.getClientProperty("X") + "\n" + "Y:" + btn.getClientProperty("Y"));
			setConnected(true);
			if ( testDialog == null )
				testDialog = new TestDialog();
			testDialog.setVisible(true);
		}
	}

	public boolean isConnected()
	{
		return connected;
	}

	public void setConnected(boolean connected)
	{
		this.connected = connected;
		firePropertyChange("connected", null, connected);
	}

	public String getTestValue()
	{
		return testValue;
	}

	public void setTestValue(String testValue)
	{
		this.testValue = testValue;
		System.out.printf("updated %s\n", testValue);
	}

	protected void progressMessage(String message)
	{
		statusbar.setText(message);
	}
}
