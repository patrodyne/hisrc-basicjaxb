package org.swixml.examples.treecard;

import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Node configuration fields (ID, nodeName, nodePanel and nodeIcon) used to build a
 * tree bound to cards.
 * 
 * <p>A card is a {@link JPanel} managed by a {@link CardLayout} container.
 * Only one card is visible at a time, and the container acts as a stack of cards.</p>
 * 
 * <p>The {@code ID} field is used as the title of the card and as the name of the component
 * to be added to the {@link CardLayout} container.</p>
 * 
 * <p>The {@code nodeName} field is the displayable name assigned to each {@link NodeInfo}
 * and is used to generate the ID field.</p>
 * 
 * <p>The {@code nodePanel} field is the "card" {@link JPanel} to be added to a
 * {@link CardLayout} container.</p>
 * 
 * <p>The {@code nodeIcon} is an optional custom resource image for this node for display
 * in the {@link JTree}</p>
 */
public class NodeInfo
{
	private String ID;
	public String getID() { return ID; }
	public void setID(String iD) { ID = iD; }

	private String nodeName;
	public String getNodeName() { return nodeName; }
	public void setNodeName(String nodeName) { this.nodeName = nodeName; }

	private ImageIcon nodeIcon;
	public ImageIcon getNodeIcon() { return nodeIcon; }
	public void setNodeIcon(ImageIcon nodeIcon) { this.nodeIcon = nodeIcon; }
	
	/**
	 * Construct with a node name and the node's parent name.
	 * 
	 * <p>The parent and node names are combined to generate an ID for this node.<p>
	 * 
	 * @param nodeName The displayable simple name for this node.
	 * @param parentNode The parent of this node.
	 */
	public NodeInfo(String nodeName, NodeInfo parentNode)
	{
		setNodeName(nodeName);
		if ( parentNode != null )
			setID(parentNode.getID() + ":" + nodeName);
		else
			setID(nodeName);
	}
	
	/**
	 * Construct with a node name, the node's parent name,
	 * and an {@link ImageIcon}.
	 * 
	 * @param nodeName The displayable simple name for this node.
	 * @param parentName The displayable parent name for this node.
	 * @param customIcon The custom icon for this node.
	 */
	public NodeInfo(String nodeName, NodeInfo parentNode, ImageIcon customIcon)
	{
		this(nodeName, parentNode);
		setNodeIcon(customIcon);
	}

	/**
	 * Display the name for this node.
	 */
	@Override
	public String toString()
	{
		return getNodeName();
	}
	
	/**
	 * Get the NodeInfo user object for the given DefaultMutableTreeNode instance.
	 * 
	 * @param dmtn The DefaultMutableTreeNode instance to examine.
	 * 
	 * @return The the NodeInfo user object for the given DefaultMutableTreeNode instance.
	 */
	public static NodeInfo getNodeInfo(DefaultMutableTreeNode dmtn)
	{
		return (NodeInfo) dmtn.getUserObject();
	}
}