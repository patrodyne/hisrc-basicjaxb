package org.swixml.examples.util;

import java.awt.event.MouseEvent;
import java.util.Collection;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author sorrentino
 */
public class GenericTreeModel<T> extends DefaultTreeModel
{
	private static final long serialVersionUID = 20240701L;
	public GenericTreeModel(T root)
	{
		super(new DefaultMutableTreeNode(root), true);
	}

	public GenericTreeModel(DefaultMutableTreeNode root, boolean asksAllowsChildren)
	{
		super(root, asksAllowsChildren);
	}

	public GenericTreeModel(DefaultMutableTreeNode root)
	{
		super(root);
	}

	public MutableTreeNode addNodeToRoot(T userObject)
	{
		return this.addNodeToRoot(userObject, false);
	}

	public MutableTreeNode addNodeToRoot(T userObject, boolean allowChildren)
	{
		DefaultMutableTreeNode _root = (DefaultMutableTreeNode) getRoot();
		DefaultMutableTreeNode result = new DefaultMutableTreeNode(userObject, allowChildren);
		_root.add(result);
		return result;
	}

	public void addNodeToRoot(Collection<T> userObjectList)
	{
		DefaultMutableTreeNode _root = (DefaultMutableTreeNode) getRoot();
		for ( T o : userObjectList )
		{
			_root.add(new DefaultMutableTreeNode(o, false));
		}
	}

	public MutableTreeNode addNode(MutableTreeNode parent, T userObject)
	{
		return this.addNode(parent, userObject, false);
	}

	public MutableTreeNode addNode(MutableTreeNode parent, T userObject, boolean allowChildren)
	{
		if ( !(parent instanceof DefaultMutableTreeNode) )
			throw new IllegalArgumentException("parent is not correct type!");
		DefaultMutableTreeNode result = new DefaultMutableTreeNode(userObject, allowChildren);
		((DefaultMutableTreeNode) parent).add(result);
		return result;
	}

	@SuppressWarnings("unchecked")
	public T getSelectedObject(MouseEvent ev)
	{
		Object source = ev.getSource();
		if ( source instanceof JTree )
		{
			JTree tbl = (JTree) source;
			TreePath selPath = tbl.getPathForLocation(ev.getX(), ev.getY());
			if ( selPath != null )
			{
				DefaultMutableTreeNode n = (DefaultMutableTreeNode) selPath.getLastPathComponent();
				T bean = (T) n.getUserObject();
				return bean;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public T getSelectedObject(TreeSelectionEvent ev)
	{
		DefaultMutableTreeNode n = (DefaultMutableTreeNode) ev.getPath().getLastPathComponent();
		T bean = (T) n.getUserObject();
		return bean;
	}
}
