package org.swixml.jsr.widgets;

import java.util.Stack;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

public class MutableTreeModel
	extends DefaultTreeModel
{
	private static final long serialVersionUID = 20241201L;
	
	private Stack<DefaultMutableTreeNode> nodeStack;
	public Stack<DefaultMutableTreeNode> getNodeStack()
	{
		if ( nodeStack == null )
			setNodeStack(new Stack<>());
		return nodeStack;
	}
	public void setNodeStack(Stack<DefaultMutableTreeNode> nodeStack)
	{
		this.nodeStack = nodeStack;
	}

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
	
	public DefaultMutableTreeNode pushAdd(DefaultMutableTreeNode node)
	{
		if ( getNodeStack().isEmpty() )
			push(node);
		else
		{
			peekAdd(node);
			push(node);
		}
		return node;
	}
	
	public DefaultMutableTreeNode pushAdd(CardNodeInfo cni)
	{
		return pushAdd(new DefaultMutableTreeNode(cni));
	}
	
	public DefaultMutableTreeNode pushAdd(String nodeName, Class<?> cardClass)
	{
		return pushAdd(new CardNodeInfo(nodeName, cardClass));
	}
	
	public DefaultMutableTreeNode peekAdd(DefaultMutableTreeNode node)
	{
		if ( getNodeStack().isEmpty() )
			push(node);
		else
			peek().add(node);
		return node;
	}
	
	public DefaultMutableTreeNode peekAdd(CardNodeInfo cni)
	{
		return peekAdd(new DefaultMutableTreeNode(cni));
	}
	
	public DefaultMutableTreeNode peekAdd(String nodeName, Class<?> cardClass)
	{
		return peekAdd(new CardNodeInfo(nodeName, cardClass));
	}
	
	public DefaultMutableTreeNode push(DefaultMutableTreeNode node)
	{
		return getNodeStack().push(node);
	}
	
	public DefaultMutableTreeNode push(CardNodeInfo cni)
	{
		return push(new DefaultMutableTreeNode(cni));
	}
	
	public DefaultMutableTreeNode push(String nodeName, Class<?> cardClass)
	{
		return push(new CardNodeInfo(nodeName, cardClass));
	}
	
	public DefaultMutableTreeNode push(String nodeName)
	{
		return push(new DefaultMutableTreeNode(nodeName));
	}
	
	public DefaultMutableTreeNode pop()
	{
		return getNodeStack().pop();
	}
	
	public DefaultMutableTreeNode peek()
	{
		return getNodeStack().peek();
	}
}
