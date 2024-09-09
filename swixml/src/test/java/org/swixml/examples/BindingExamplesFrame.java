package org.swixml.examples;

import static java.lang.System.out;
import static java.util.Arrays.asList;
import static org.jdesktop.observablecollections.ObservableCollections.observableList;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TreeSelectionEvent;

import org.jdesktop.application.Action;
import org.jdesktop.observablecollections.ObservableList;
import org.swixml.jsr.widgets.JLabelBind;
import org.swixml.jsr.widgets.JTableBind;
import org.swixml.jsr.widgets.JTextAreaBind;
import org.swixml.jsr.widgets.JTextFieldBind;

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

	// Bound by "id" attribute
	//
	// field [tv]        mapped in class [BindingExamplesFrame]
	// field [btn1]      mapped in class [BindingExamplesFrame]
	// field [btn2]      mapped in class [BindingExamplesFrame]
	// field [testTable] mapped in class [BindingExamplesFrame]
	// field [ta]        mapped in class [BindingExamplesFrame]
	// field [statusbar] mapped in class [BindingExamplesFrame]	
	public JTextFieldBind tv;
	public JTextFieldBind getTextField() { return tv; }
	
	public JButton btn1, btn2;
	
	public JTableBind testTable;
	public JTextAreaBind ta;
	public JLabelBind statusbar;
	
	public Container panel_dlg;
	public ActionMap actionMap;

	public Dimension getFrameSize()
	{
		return new Dimension(100, 600);
	}
	
	boolean konnected = false;
	public boolean isConnected()
	{
		return konnected;
	}
	public void setConnected(boolean connected)
	{
		boolean oldValue = this.konnected;
		boolean newValue = connected;
		this.konnected = newValue;
		firePropertyChange("connected", oldValue, newValue);
		out.printf("OUT: connected updated from [%s] to [%s]\n", oldValue, newValue);
	}
	
	private String testValue = "TEST1";
	public String getTestValue()
	{
		return testValue;
	}
	public void setTestValue(String testValue)
	{
		String oldValue = this.testValue;
		String newValue = testValue;
		this.testValue = newValue;
		firePropertyChange("testValue", oldValue, newValue);
		out.printf("OUT: testValue updated from [%s] to [%s]\n", oldValue, newValue);
	}

	// Bound by hbox/combobox AND scrollpane/list
	private List<String> comboList;
	public final List<String> getComboList()
	{
		// This property may be initialized in BindingExamplesFrame.xml!
		if ( comboList == null )
			setComboList(asList("item1", "item2", "item3", "item4"));
		return comboList;
	}
	public void setComboList(List<String> comboList)
	{
		if ( comboList instanceof ObservableList )
			this.comboList = comboList;
		else
			this.comboList = observableList(comboList);
	}
	
	// Note; BindingExamplesFrame.xml can override the initial value.
	private String comboItem = "item2";
	public String getComboItem()
	{
		return comboItem;
	}
	public void setComboItem(String comboItem)
	{
		this.comboItem = comboItem;
	}

	// Bound by scrollpane/list to set cellRenderer.
	private CustomListCellRenderer listRenderer;
	public final CustomListCellRenderer getListRenderer()
	{
		if ( listRenderer == null )
			listRenderer = new CustomListCellRenderer();
		return listRenderer;
	}

	// Bound by scrollpane-1/tree in "table-tree" panel
	private TestTreeModel myTree;
	public TestTreeModel getMyTree()
	{
		if ( myTree == null)
			myTree = new TestTreeModel();
		return myTree;
	}
	
	// Bound by scrollpane-2/table in "table-tree" panel
	private List<SimpleBean> myData;
	public List<SimpleBean> getMyData()
	{
		if ( myData == null )
			myData = observableList(new ArrayList<SimpleBean>());
		return myData;
	}

	// Bound by scrollpane-2/table in "table-tree" panel
	public Class<?> getMyDataClass()
	{
		return SimpleBean.class;
	}

	private BindingExamplesTestDialog testDialog = null;
	public BindingExamplesTestDialog getTestDialog()
	{
		return testDialog;
	}
	public void setTestDialog(BindingExamplesTestDialog testDialog)
	{
		this.testDialog = testDialog;
	}
	
	/**
	 * Default constructor
	 */
	public BindingExamplesFrame()
	{
		out.println("OUT: BindingExamplesFrame: " + this.getName());
	}
	
	@Action
	public void comboAction()
	{
		out.println("OUT: comboAction");
	}

	@Action
	public void listAction()
	{
		out.println("OUT: listAction");
	}

	@Action(enabledProperty = "connected")
	public void test()
	{
		out.printf("OUT: hello world! %s\n", testValue);
		setTestValue("hello world!");
		firePropertyChange("testValue", null, "hello world!");
	}

	@Action
	public void selectNode(ActionEvent e)
	{
		TreeSelectionEvent ev = (TreeSelectionEvent) e.getSource();
		out.printf("OUT: selectNode path=%s\n", ev.getPath().toString());
		getMyData().add(new SimpleBean(ev.getPath().toString(), 0));
	}

	@Action
	public void selectRow(ActionEvent e)
	{
		ListSelectionEvent ev = (ListSelectionEvent) e.getSource();
		out.printf("OUT: selectRow firstIndex=%d lastIndex=%d valueIsAdjusting=%b\n",
			ev.getFirstIndex(), ev.getLastIndex(), ev.getValueIsAdjusting());
	}

	@Action
	public void activateRow(ActionEvent e)
	{
		out.printf("OUT: activate row [%d]\n", testTable.getSelectedRow());
	}

	@Action()
	public void show(ActionEvent e)
	{
		if ( (e.getSource() == btn1) ||  (e.getSource() == btn2) )
		{
			JButton btn = (JButton) e.getSource();
			ta.setText("X:" + btn.getClientProperty("X") + "\n" + "Y:" + btn.getClientProperty("Y"));
			setConnected(true);
			if ( getTestDialog() == null )
			{
				setTestDialog(new BindingExamplesTestDialog(this));
			}
			getTestDialog().setVisible(true);
		}
	}

	protected void progressMessage(String message)
	{
		statusbar.setText(message);
	}
}
