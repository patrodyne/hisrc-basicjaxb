package org.swixml.examples.treecard;

import javax.swing.tree.TreeNode;

public class MainTreeNodeFactory
{
	public static MainTreeConfig MAIN_TREE_CONFIG_INSTANCE;
	private static TreeNode MAIN_TREE_NODE_INSTANCE;
	static
	{
		MAIN_TREE_CONFIG_INSTANCE = new MainTreeConfig();
		MAIN_TREE_NODE_INSTANCE = MAIN_TREE_CONFIG_INSTANCE.buildTree();
	}
	
	public static TreeNode getInstance()
	{
		return MAIN_TREE_NODE_INSTANCE;
	}
}
