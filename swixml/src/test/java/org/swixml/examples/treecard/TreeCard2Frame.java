package org.swixml.examples.treecard;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.jdesktop.application.Action;

public class TreeCard2Frame extends JFrame
{
	private static final long serialVersionUID = 20241201L;
	
	// Bound from SwiXML id="cardLayoutPanel"
	private JPanel cardLayoutPanel;
	public JPanel getCardLayoutPanel() { return cardLayoutPanel; }
	public void setCardLayoutPanel(JPanel cardLayoutPanel) { this.cardLayoutPanel = cardLayoutPanel; }

	// Bound from SwiXML id="mainTree"
	private JTree mainTree;
	public JTree getMainTree()
	{
		return mainTree;
	}
	public void setMainTree(JTree mainTree)
	{
		this.mainTree = mainTree;
	}
	
	/* Bound to SwiXML model="${window.mainTreeCellRenderer}" */
	private MainTreeCellRenderer mainTreeCellRenderer;
	public MainTreeCellRenderer getMainTreeCellRenderer()
	{
		if ( mainTreeCellRenderer == null)
			setMainTreeCellRenderer(new MainTreeCellRenderer(getMainTree()));
		return mainTreeCellRenderer;
	}
	public void setMainTreeCellRenderer(MainTreeCellRenderer mainTreeCellRenderer)
	{
		this.mainTreeCellRenderer = mainTreeCellRenderer;
	}
	
	/**
	 * Action event is raised when a node is selected on the main tree.
	 * 
	 * @param ae Event to indicate that a component-defined action occurred.
	 */
	@Action
	public void selectMainTreeNode(ActionEvent ae)
	{
		// Get the object on which the Event initially occurred.
		TreeSelectionEvent tse = (TreeSelectionEvent) ae.getSource();
		// Get the object that uniquely identifies the path to a node in a tree.
		TreePath tsePath = (TreePath) tse.getPath();
		// Get the last component of the selected path.
		DefaultMutableTreeNode tseLastPathComp = (DefaultMutableTreeNode) tsePath.getLastPathComponent();
		// Get the NodeInfo user object from the selected component.
		NodeInfo nodeInfo = NodeInfo.getNodeInfo(tseLastPathComp);
		// When present, show the card that matches the NodeInfo instance ID.
		CardLayout cardLayout = (CardLayout) getCardLayoutPanel().getLayout();
		if ( nodeInfo != null )
			cardLayout.show(getCardLayoutPanel(), nodeInfo.getID());
	}
}
