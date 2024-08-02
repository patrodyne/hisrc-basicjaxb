package org.swixml.examples.tree;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.MutableTreeNode;

import org.jdesktop.application.Action;

/**
 *
 * @author softphone
 */
public class TreeDialog extends JDialog
{
	private static final long serialVersionUID = 20240701L;
	final MyTreeModel myModel = new MyTreeModel("root");
	public JTree tree; /* automatically bound */
	private StringBuilder result = new StringBuilder();

	public TreeDialog()
	{
		myModel.addNodeToRoot("node1");
		MutableTreeNode node2 = myModel.addNodeToRoot("node2", true);
		myModel.addNode(node2, "node2.1");
		myModel.addNode(node2, "node2.2");
		myModel.addNode(node2, "node2.3");
		myModel.addNodeToRoot("node3");
	}

	/**
	 * tree bound
	 */
	public final MyTreeModel getMyModel()
	{
		return myModel;
	}

	public final String getResult()
	{
		return result.toString();
	}

	public final void setResult(String result)
	{
		this.result = new StringBuilder(result);
		firePropertyChange("result", null, null); // force the widget update
	}

	private void appendResult(String value)
	{
		this.result.append(value).append('\n');
		firePropertyChange("result", null, null); // force the widget update
	}

	/**
	 * event raised when a row is selected on table
	 */
	@Action
	public void selectNode(ActionEvent e)
	{
		TreeSelectionEvent ev = (TreeSelectionEvent) e.getSource();
		appendResult("select node: " + myModel.getSelectedObject(ev));
	}

	/**
	 * event raised when a double click is performed upon row
	 */
	@Action
	public void activeNode(ActionEvent e)
	{
		MouseEvent ev = (MouseEvent) e.getSource();
		appendResult("activate node: " + myModel.getSelectedObject(ev));
	}
}
