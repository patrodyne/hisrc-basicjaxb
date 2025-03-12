package org.swixml.examples;

import static java.lang.System.out;
import static java.util.Arrays.asList;
import static org.jdesktop.observablecollections.ObservableCollections.observableList;
import static org.swixml.jsr.widgets.JTableBind.ACTION_SELECT_COL;
import static org.swixml.jsr.widgets.JTableBind.ACTION_SELECT_ROW;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import org.jdesktop.application.Action;
import org.jdesktop.observablecollections.ObservableList;
import org.jvnet.basicjaxb.lang.DataBeanInfo;
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
	
	public JTableBind testTable1, testTable2;
	public JTextAreaBind ta;
	public JLabelBind statusbar;
	
	public Container panel_dlg;
	public ActionMap actionMap;

	public Dimension getFrameSize()
	{
		return new Dimension(800, 800);
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

	// Bound by scrollpane/list to set cellRenderer1.
	private CustomListCellRenderer listRenderer;
	public final CustomListCellRenderer getListRenderer()
	{
		if ( listRenderer == null )
			listRenderer = new CustomListCellRenderer();
		return listRenderer;
	}
	
	/* myTree1 bound by model="${window.cellRenderer}" */
	private TreeCellRenderer cellRenderer1 = null;
	public TreeCellRenderer getCellRenderer1()
	{
		if ( cellRenderer1 == null )
		{
			cellRenderer1 = new DefaultTreeCellRenderer()
			{
			    private static final long serialVersionUID = 20240701L;

				@Override
				public Color getBackgroundNonSelectionColor()
			    {
			        return getMyTree1().getBackground();
			    }
			};
		}
		return cellRenderer1;
	}
	public void setCellRenderer1(TreeCellRenderer cellRenderer)
	{
		this.cellRenderer1 = cellRenderer;
	}
	
	/* myTree2 bound by model="${window.cellRenderer}" */
	private TreeCellRenderer cellRenderer2 = null;
	public TreeCellRenderer getCellRenderer2()
	{
		if ( cellRenderer2 == null )
		{
			cellRenderer2 = new DefaultTreeCellRenderer()
			{
			    private static final long serialVersionUID = 20240701L;

				@Override
				public Color getBackgroundNonSelectionColor()
			    {
			        return getMyTree2().getBackground();
			    }
			};
		}
		return cellRenderer2;
	}
	public void setCellRenderer2(TreeCellRenderer cellRenderer)
	{
		this.cellRenderer2 = cellRenderer;
	}
	
	/* automatically bound by id="myTree1" */
	private JTree myTree1;
	public JTree getMyTree1() { return myTree1; }
	public void setMyTree1(JTree myTree) { this.myTree1 = myTree; }
	
	/* automatically bound by id="myTree2" */
	private JTree myTree2;
	public JTree getMyTree2() { return myTree2; }
	public void setMyTree2(JTree myTree) { this.myTree2 = myTree; }
	
	// Bound by scrollpane-1/myTree in "table-myTree" panel
	private TestTreeModel myTreeModel;
	public TestTreeModel getMyTreeModel()
	{
		if ( myTreeModel == null)
			myTreeModel = new TestTreeModel();
		return myTreeModel;
	}
	
	// Bound by scrollpane-2/table in "table-myTree" panel
	private List<SimpleBean> myData;
	public List<SimpleBean> getMyData()
	{
		if ( myData == null )
			myData = observableList(new ArrayList<SimpleBean>());
		return myData;
	}

	// Bound by scrollpane-2/table in "table-myTree" panel
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
	public void selectInfo(ActionEvent ae)
	{
		if ( ACTION_SELECT_ROW.equals(ae.getActionCommand()) )
		{
			if ( ae.getSource() instanceof ListSelectionEvent )
			{
				ListSelectionEvent ev = (ListSelectionEvent) ae.getSource();
				out.printf("OUT: selectInfo firstIndex=%d lastIndex=%d valueIsAdjusting=%b\n",
					ev.getFirstIndex(), ev.getLastIndex(), ev.getValueIsAdjusting());
			}
			else if ( ae.getSource() instanceof DataBeanInfo )
			{
				DataBeanInfo dbi = (DataBeanInfo) ae.getSource();
				out.printf("OUT: selectInfo dataBeanIfo=%s\n", dbi.toString());
			}
		}
		else if ( ACTION_SELECT_COL.equals(ae.getActionCommand()) )
		{
			// Ignore
		}
	}

	@Action
	public void activateRow(ActionEvent ae)
	{
		if ( ae.getSource() instanceof MouseEvent )
		{
			MouseEvent me = (MouseEvent) ae.getSource();
			if ( me.getSource() instanceof JTableBind )
			{
				JTableBind jtb = (JTableBind) me.getSource();
				out.printf("OUT: activate row [%d] on [%s]\n",
					jtb.getSelectedRow(), jtb.getName());
			}
		}
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
