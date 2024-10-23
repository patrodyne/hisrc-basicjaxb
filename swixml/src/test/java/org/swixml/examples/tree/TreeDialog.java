package org.swixml.examples.tree;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellRenderer;

import org.jdesktop.application.Action;

/**
 *
 * @author softphone
 */
public class TreeDialog extends JDialog
{
	private static final long serialVersionUID = 20240701L;


	/* automatically bound by id="tree" */
	private JTree tree;
	public JTree getTree() { return tree; }
	public void setTree(JTree tree) { this.tree = tree; }
	
	/* tree bound by model="${window.myModel}" */
	private MyTreeModel myModel;
	public MyTreeModel getMyModel()
	{
		if ( myModel == null )
			setMyModel(new MyTreeModel("root"));
		return myModel;
	}
	public void setMyModel(MyTreeModel myModel)
	{
		this.myModel = myModel;
	}
		
	/* tree bound by cellRenderer="${window.cellRenderer}" */
	private TreeCellRenderer cellRenderer = null;
	public TreeCellRenderer getCellRenderer()
	{
		if ( cellRenderer == null )
		{
			cellRenderer = new DefaultTreeCellRenderer()
			{
			    private static final long serialVersionUID = 20240701L;

				@Override
				public Color getBackgroundNonSelectionColor()
			    {
			        return getTree().getBackground();
			    }
			};
		}
		return cellRenderer;
	}
	public void setCellRenderer(TreeCellRenderer cellRenderer)
	{
		this.cellRenderer = cellRenderer;
	}
	
	/* bindWith="result" */
	private StringBuilder result = new StringBuilder();
	public final String getResult()
	{
		if ( result == null )
			setResult(new StringBuilder());
		return result.toString();
	}
	public void setResult(StringBuilder result)
	{
		this.result = result;
	}
	
	public final void setResult(String result)
	{
		setResult(new StringBuilder(result));
		// force the widget update
		firePropertyChange("result", null, null); 
	}
	
	private void appendResult(String value)
	{
		this.result.append(value).append('\n');
		// force the widget update
		firePropertyChange("result", null, null); 
	}
	
	public TreeDialog()
	{
		getMyModel().addNodeToRoot("node1");
		MutableTreeNode node2 = getMyModel().addNodeToRoot("node2", true);
		getMyModel().addNode(node2, "node2.1");
		getMyModel().addNode(node2, "node2.2");
		getMyModel().addNode(node2, "node2.3");
		getMyModel().addNodeToRoot("node3");
	}

	/**
	 * event raised when a row is selected on table
	 */
	@Action
	public void selectNode(ActionEvent ae)
	{
		TreeSelectionEvent ev = (TreeSelectionEvent) ae.getSource();
		appendResult("select node: " + getMyModel().getSelectedObject(ev));
	}

	/**
	 * event raised when a double click is performed upon row
	 */
	@Action
	public void activeNode(ActionEvent ae)
	{
		MouseEvent ev = (MouseEvent) ae.getSource();
		appendResult("activate node: " + getMyModel().getSelectedObject(ev));
	}
}
