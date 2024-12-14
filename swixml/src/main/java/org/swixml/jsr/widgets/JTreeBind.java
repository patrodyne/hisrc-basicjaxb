package org.swixml.jsr.widgets;

import static org.swixml.SwingEngine.ENGINE_PROPERTY;
import static org.swixml.jsr295.BindingUtils.boundCheckAndSet;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.swixml.LogAware;
import org.swixml.SwingEngine;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @author sorrentino
 */
public class JTreeBind
	extends JTree
	implements BindableWidget, LogAware
{
	private static final long serialVersionUID = 20240701L;

	@Override
	public String getBindWith()
	{
		return (String) getClientProperty(BINDWITH_PROPERTY);
	}
	@Override
	public void setBindWith(String bindWith)
	{
		putClientProperty(BINDWITH_PROPERTY, bindWith);
	}
	
	private Action action;
	public Action getAction()
	{
		return action;
	}
	public void setAction(Action action)
	{
		this.action = action;
	}

	private Action dblClickAction = null;
	public final Action getDblClickAction()
	{
		return dblClickAction;
	}
	public final void setDblClickAction(Action dblClickAction)
	{
		this.dblClickAction = dblClickAction;
	}

	private ImageIcon openIcon = null;
	public ImageIcon getOpenIcon()
	{
		return openIcon;
	}
	public void setOpenIcon(ImageIcon folderIcon)
	{
		this.openIcon = folderIcon;
	}

	private ImageIcon leafIcon = null;
	public ImageIcon getLeafIcon()
	{
		return leafIcon;
	}
	public void setLeafIcon(ImageIcon leafIcon)
	{
		this.leafIcon = leafIcon;
	}

	private ImageIcon closedIcon = null;
	public ImageIcon getClosedIcon()
	{
		return closedIcon;
	}
	public void setClosedIcon(ImageIcon closedIcon)
	{
		this.closedIcon = closedIcon;
	}
	
	/**
	 * Create and add {@link AutoBinding} instance(s) to synchronize model
	 * properties with this {@link JTableBind}.
	 * 
	 * <p>Notifies this {@link Component} that it now has a parent component. It
	 * makes the {@link Container} displayable by connecting it to a native
	 * screen resource.</p>
	 */
	@Override
	public void addNotify()
	{
		if ( getBindWith() != null )
		{
			SwingEngine<?> engine = (SwingEngine<?>) getClientProperty(ENGINE_PROPERTY);
			BeanProperty<Object, TreeModel> bp = BeanProperty.create(getBindWith());
			TreeModel tm = bp.getValue(engine.getClient());
			setModel(tm);
		}
		
		if ( !boundCheckAndSet(this) )
		{
			if ( (null != getOpenIcon()) || (null != getLeafIcon()) || (null != getClosedIcon()) )
			{
				DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
				if ( null != getLeafIcon() )
					renderer.setLeafIcon(getLeafIcon());
				if ( null != getOpenIcon() )
					renderer.setOpenIcon(getOpenIcon());
				if ( null != getClosedIcon() )
					renderer.setClosedIcon(getClosedIcon());
				setCellRenderer(renderer);
			}
		}
		
		super.addNotify();
	}
	
	public JTreeBind()
	{
		super();
		init();
	}

	public JTreeBind(TreeNode treeNode)
	{
		super(treeNode);
		init();
	}

	public JTreeBind(TreeModel treeModel)
	{
		super(treeModel);
		init();
	}

	private void init()
	{
		super.addTreeSelectionListener(new TreeSelectionListener()
		{
			@Override
			public void valueChanged(TreeSelectionEvent e)
			{
				Action a = getAction();
				if ( null == a )
					return;
				ActionEvent ev = new ActionEvent(e, 0, null);
				a.actionPerformed(ev);
			}
		});
		MouseListener ml = new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				int selRow = getRowForLocation(e.getX(), e.getY());
				// TreePath selPath = getPathForLocation(e.getX(), e.getY());
				logger.debug(String.format("mousePressed selRow=[%d] clickCount=[%d]",
					selRow, e.getClickCount()));
				if ( selRow != -1 )
				{
					if ( e.getClickCount() == 2 )
					{
						Action a = getDblClickAction();
						if ( a == null )
							return;
						ActionEvent ev = new ActionEvent(e, 0, null);
						a.actionPerformed(ev);
					}
				}
			}
		};
		addMouseListener(ml);
	}
}
