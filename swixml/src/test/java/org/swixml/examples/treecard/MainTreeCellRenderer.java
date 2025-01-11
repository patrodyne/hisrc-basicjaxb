package org.swixml.examples.treecard;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MainTreeCellRenderer extends DefaultTreeCellRenderer
{
	private static final long serialVersionUID = 20241201L;
	
	private JTree mainTree;
	public JTree getMainTree() { return mainTree; }
	public void setMainTree(JTree mainTree) { this.mainTree = mainTree; }
	
	public MainTreeCellRenderer(JTree mainTree)
	{
		super();
		setMainTree(mainTree);
	}
	
	@Override
	public Color getBackgroundNonSelectionColor()
    {
        return getMainTree().getBackground();
    }
	
	/**
	 * Configures the renderer based on the passed in components.
	 * {@inheritDoc} 
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
		boolean expanded, boolean leaf, int row, boolean hasFocus)
	{
		// Note: MainTreeCellRenderer is a DefaultTreeCellRenderer is a JLabel!
		JLabel lbl = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		
		if ( value instanceof DefaultMutableTreeNode )
		{
			Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
			if ( userObject instanceof NodeInfo )
			{
				// The user object is our CardNodeInfo from construction.
				NodeInfo node = (NodeInfo) userObject;
				if ( node.getNodeIcon() != null )
					lbl.setIcon(node.getNodeIcon());
			}
		}
		
		return lbl;
	}
}
