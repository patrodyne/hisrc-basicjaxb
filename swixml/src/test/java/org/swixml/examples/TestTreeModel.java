package org.swixml.examples;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

/**
 *
 * @author sorrentino
 */
public class TestTreeModel extends DefaultTreeModel
{
	private static final long serialVersionUID = 20240701L;
	final MutableTreeNode root;

	public TestTreeModel()
	{
		super(new DefaultMutableTreeNode("ROOT"));
		root = (MutableTreeNode) super.getRoot();
		root.insert(new DefaultMutableTreeNode("node.1"), 0);
	}
}
