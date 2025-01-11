package org.swixml.jsr.widgets;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

public class MutableTreeModel
	extends DefaultTreeModel
{
	private static final long serialVersionUID = 20241201L;
	
	private MutableTreeNode root;
	@Override
	public MutableTreeNode getRoot() { return root; }
	public void setRoot(MutableTreeNode root) { this.root = root; }
	
	public MutableTreeModel()
	{
		this("Schemas");
	}
	
	public MutableTreeModel(String rootName)
	{
		super(new DefaultMutableTreeNode(rootName));
		setRoot((MutableTreeNode) super.getRoot());
	}
}
