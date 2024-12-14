package org.swixml.examples.treecard;

import java.awt.CardLayout;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * Configure a {@link JTree} of cards to dynamically display business data.
 */
public class MainTreeConfig
{
	/**
	 * Build a {@link JTree} to select one 'card' from a {@link CardLayout} manager
	 * to dynamically display business data.
	 * 
	 * @return A {@link TreeNode} configured with business cards (i.e. {@link JPanel}s.
	 */
	public TreeNode buildTree()
	{
		ImageIcon statusIcon = readImageIcon("/org/swixml/icons/status-clean.png");
		ImageIcon networkIcon = readImageIcon("/org/swixml/icons/network-high.png");
		
		// Configure NodeInfo instances to define the business model.
		NodeInfo n0 = new NodeInfo("Devices", null);
		NodeInfo n1 = new NodeInfo("Module 1", n0);
		NodeInfo n2 = new NodeInfo("Status", n1, statusIcon);
		NodeInfo n3 = new NodeInfo("Device", n2);
		NodeInfo n4 = new NodeInfo("Network", n2, networkIcon);
		NodeInfo n5 = new NodeInfo("Chassis", n2);
		NodeInfo n6 = new NodeInfo("Resources", n2);
		NodeInfo n7 = new NodeInfo("Project Editor", n1);
		NodeInfo n8 = new NodeInfo("Project Manager", n1);
		NodeInfo n9 = new NodeInfo("Administration", n1);
		NodeInfo n10 = new NodeInfo("Device", n9);
		NodeInfo n11 = new NodeInfo("Network", n9);
		NodeInfo n12 = new NodeInfo("Users", n9);
		NodeInfo n13 = new NodeInfo("Logging", n1);
		
		// Use the NodeInfo instances to configure the tree. Each
		// NodeInfo instance becomes the user-object of a tree node.
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(n0);
		DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode(n1);
		top.add(branch1);
		DefaultMutableTreeNode node1_b1 = new DefaultMutableTreeNode(n2);
		DefaultMutableTreeNode n1_node1_b1 = new DefaultMutableTreeNode(n3);
		DefaultMutableTreeNode n2_node1_b1 = new DefaultMutableTreeNode(n4);
		DefaultMutableTreeNode n3_node1_b1 = new DefaultMutableTreeNode(n5);
		DefaultMutableTreeNode n4_node1_b1 = new DefaultMutableTreeNode(n6);
		node1_b1.add(n1_node1_b1);
		node1_b1.add(n2_node1_b1);
		node1_b1.add(n3_node1_b1);
		node1_b1.add(n4_node1_b1);
		DefaultMutableTreeNode node2_b1 = new DefaultMutableTreeNode(n7);
		DefaultMutableTreeNode node3_b1 = new DefaultMutableTreeNode(n8);
		DefaultMutableTreeNode node4_b1 = new DefaultMutableTreeNode(n9);
		DefaultMutableTreeNode n1_node4_b1 = new DefaultMutableTreeNode(n10);
		DefaultMutableTreeNode n2_node4_b1 = new DefaultMutableTreeNode(n11);
		DefaultMutableTreeNode n3_node4_b1 = new DefaultMutableTreeNode(n12);
		node4_b1.add(n1_node4_b1);
		node4_b1.add(n2_node4_b1);
		node4_b1.add(n3_node4_b1);
		DefaultMutableTreeNode node5_b1 = new DefaultMutableTreeNode(n13);
		branch1.add(node1_b1);
		branch1.add(node2_b1);
		branch1.add(node3_b1);
		branch1.add(node4_b1);
		branch1.add(node5_b1);
		
		return top;
	}

	/*
	 * Read an image file from the resource path and create an {@link ImageIcon} instance.
	 */
	private ImageIcon readImageIcon(String resourcePath)
	{
		ImageIcon imageIcon = null;
		try ( InputStream stream = getClass().getResourceAsStream(resourcePath) )
		{
			imageIcon = new ImageIcon(ImageIO.read(stream));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		return imageIcon;
	}
}