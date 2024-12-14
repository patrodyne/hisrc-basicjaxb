package org.swixml.examples.treecard;

import static javax.swing.BorderFactory.createTitledBorder;
import static org.swixml.examples.treecard.NodeInfo.getNodeInfo;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * An example of a JTree Selection to a CardLayout Panel.
 * 
 * <p>This example provides ability to click on a {@link TreeNode} on the left hand side
 * of a {@link JFrame}, and display a card from the {@link CardLayout} on the right side.</p>
 * 
 * @see <a href="https://forums.oracle.com/ords/apexds/post/jtree-cardlayout-2603">jtree-cardlayout-2603</a>
 */
public class TreeCard1 extends JFrame
{
	private static final long serialVersionUID = 20241201L;
	
	private CardLayout cardLayout;
	public CardLayout getCardLayout()
	{
		if ( cardLayout == null )
			setCardLayout(new CardLayout());
		return cardLayout;
	}
	public void setCardLayout(CardLayout cardLayout)
	{
		this.cardLayout = cardLayout;
	}

	private JPanel cardLayoutPanel;
	public JPanel getCardLayoutPanel()
	{
		if ( cardLayoutPanel == null )
			setCardLayoutPanel(new JPanel(getCardLayout()));
		return cardLayoutPanel;
	}
	public void setCardLayoutPanel(JPanel cardLayoutPanel)
	{
		this.cardLayoutPanel = cardLayoutPanel;
	}
	
	private JPanel devicesPanel;
	public JPanel getDevicesPanel()
	{
		if ( devicesPanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("DEVICES");
			panel.add(label);
			devicesPanel = panel;
		}
		return devicesPanel;
	}

	private JPanel module1Panel;
	public JPanel getModule1Panel()
	{
		if ( module1Panel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("MODULE 1");
			panel.add(label);
			module1Panel = panel;
		}
		return module1Panel;
	}

	private JPanel statusPanel;
	public JPanel getStatusPanel()
	{
		if ( statusPanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("STATUS");
			panel.add(label);
			statusPanel = panel;
		}
		return statusPanel;
	}

	private JPanel statusDevicePanel;
	public JPanel getStatusDevicePanel()
	{
		if ( statusDevicePanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("STATUS: DEVICE");
			panel.add(label);
			statusDevicePanel = panel;
		}
		return statusDevicePanel;
	}

	private JPanel statusNetworkPanel;
	public JPanel getStatusNetworkPanel()
	{
		if ( statusNetworkPanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("STATUS: NETWORK");
			panel.add(label);
			statusNetworkPanel = panel;
		}
		return statusNetworkPanel;
	}
	
	private JPanel statusChassisPanel;
	public JPanel getStatusChassisPanel()
	{
		if ( statusChassisPanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("STATUS: CHASSIS");
			panel.add(label);
			statusChassisPanel = panel;
		}
		return statusChassisPanel;
	}
	
	private JPanel statusResourcesPanel;
	public JPanel getStatusResourcesPanel()
	{
		if ( statusResourcesPanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("STATUS: RESOURCES");
			panel.add(label);
			statusResourcesPanel = panel;
		}
		return statusResourcesPanel;
	}
	
	private JPanel projectEditorPanel;
	public JPanel getProjectEditorPanel()
	{
		if ( projectEditorPanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("PROJECT EDITOR");
			panel.add(label);
			projectEditorPanel = panel;
		}
		return projectEditorPanel;
	}
	
	private JPanel projectManagerPanel;
	public JPanel getProjectManagerPanel()
	{
		if ( projectManagerPanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("PROJECT MANAGEMENT");
			panel.add(label);
			projectManagerPanel = panel;
		}
		return projectManagerPanel;
	}
	
	private JPanel administrationPanel;
	public JPanel getAdministrationPanel()
	{
		if ( administrationPanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("ADMINISTRATION");
			panel.add(label);
			administrationPanel = panel;
		}
		return administrationPanel;
	}
	
	private JPanel adminDevicePanel;
	public JPanel getAdminDevicePanel()
	{
		if ( adminDevicePanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("ADMIN: DEVICE");
			panel.add(label);
			adminDevicePanel = panel;
		}
		return adminDevicePanel;
	}

	private JPanel adminNetworkPanel;
	public JPanel getAdminNetworkPanel()
	{
		if ( adminNetworkPanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("ADMIN: NETWORK");
			panel.add(label);
			adminNetworkPanel = panel;
		}
		return adminNetworkPanel;
	}
	
	private JPanel adminUsersPanel;
	public JPanel getAdminUsersPanel()
	{
		if ( adminUsersPanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("ADMIN: USERS");
			panel.add(label);
			adminUsersPanel = panel;
		}
		return adminUsersPanel;
	}
	
	private JPanel loggingPanel;
	public JPanel getLoggingPanel()
	{
		if ( loggingPanel == null )
		{
			JPanel panel = new JPanel();
			JLabel label = new JLabel("LOGGING");
			panel.add(label);
			loggingPanel = panel;
		}
		return loggingPanel;
	}


	/**
	 * Construct this {@JFrame} instance by:
	 * 
	 * <ul>
	 * <li>Setting the size and layout for this application.</li>
	 * <li>Add an initial blank 'card' to the card layout container.</li>
	 * <li>Create a {@link JTree} from a configured {@link TreeNode} graph.</li>
	 * <li>Use a custom {@link TreeCellRenderer} to render a custom icon, if any.</li>
	 * <li>Add the main tree as the left hand side of the main grid layout.</li>
	 * <li>Load the card panels from the given {@link JTree} root into the card layout panel.</li>
	 * <li>Add the card layout panel as the right hand side of the main grid layout.</li>
	 * <li>Use a custom TreeSelectionListener to show the card for a selected node.</li>
	 * <li>On start up, show the top card.</li>
	 * </ul>
	 */
	public TreeCard1()
	{
		// Set the size and layout for this application.
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new GridLayout(1, 2));
		
		// Use MainTreeNodeFactory to create an instance of MainTreeConfig to build 
		// a TreeNode graph then create a JTree from the configured graph.
		JTree mainTree = new JTree(MainTreeNodeFactory.getInstance());
		
		// Set a custom TreeCellRenderer on the main tree to
		// get the user object (as a NodeInfo) from given value
		// object (as a DefaultMutableTreeNode). Use the NodeInfo
		// instance to render a custom icon, if any.
		mainTree.setCellRenderer(new MainTreeCellRenderer(mainTree));
		
		// Add the main tree as the left hand side of the main grid layout.
		getContentPane().add(new JScrollPane(mainTree));
		
		// Load the card panels from the given main tree root into the card layout panel.
		DefaultMutableTreeNode treeRoot = (DefaultMutableTreeNode) ((TreeModel) mainTree.getModel()).getRoot();
		loadCardPanels(treeRoot);
		
		// Add the card layout panel as the right hand side of the main grid layout.
		getContentPane().add(getCardLayoutPanel());
		
		// Set a custom TreeSelectionListener on the main tree to show
		// the card whose name matches the ID of the NodeInfo user object.
		//
		// The card layout stores the constraint as a key-value pair that can be used
		// for random access to a particular card, by calling the show method. The key-value
		// pair is an new instance of java.awt.CardLayout.Card(String cardName, Component cardComponent).
		mainTree.addTreeSelectionListener(new TreeSelectionListener()
		{
			@Override
			public void valueChanged(TreeSelectionEvent tse)
			{
				// Get the object that uniquely identifies the path to a node in a tree.
				TreePath tsePath = (TreePath) tse.getPath();
				// Get the last component of the selected path.
				DefaultMutableTreeNode tseLastPathComp = (DefaultMutableTreeNode) tsePath.getLastPathComponent();
				// Get the NodeInfo user object from the selected component.
				NodeInfo nodeInfo = getNodeInfo(tseLastPathComp);
				// When present, show the card that matches the NodeInfo instance ID.
				if ( nodeInfo != null )
					getCardLayout().show(getCardLayoutPanel(), nodeInfo.getID());
			}
		});
		
		// On start up, show the top card.
		NodeInfo nodeInfo = getNodeInfo(treeRoot);
		getCardLayout().show(getCardLayoutPanel(), nodeInfo.getID());
	}

	/**
	 * Recursively load the card panels from the given {@link JTree} root
	 * into the card layout container.
	 * 
	 * @param dmtn A tree node beginning with the root node.
	 */
	public void loadCardPanels(DefaultMutableTreeNode dmtn)
	{
		addNodePanel(dmtn);
		
		// Loop over the child nodes of the given parent node, if any.
		for ( int index = 0; index < dmtn.getChildCount(); ++index )
		{
			// Get the child node by index number.
			DefaultMutableTreeNode dmtnChild = (DefaultMutableTreeNode) dmtn.getChildAt(index);
			
			// Conditionally, add a the panel for this node; or,
			// Recursively, call this method for each non-leaf child nodes.
			if ( dmtnChild.isLeaf() )
				addNodePanel(dmtnChild);
			else
				loadCardPanels(dmtnChild);
		}
	}

	/*
	 * Conditionally, add a panel for this node.
	 * 
	 * @param dmtn A tree node beginning with the root node.
	 */
	private void addNodePanel(DefaultMutableTreeNode dmtn)
	{
		// In this project, the user object is a custom object (NodeInfo)
		// that provides the desired node values. The NodeInfo is assigned
		// to the tree node by construction.
		NodeInfo node = getNodeInfo(dmtn);
		
		// Conditionally, add each node panel to the card layout panel
		// by its ID.
		JPanel nodePanel = null;
		switch (node.getID())
		{
			case "Devices": nodePanel = getDevicesPanel(); break;
			case "Devices:Module 1": nodePanel = getModule1Panel(); break;
			case "Devices:Module 1:Status": nodePanel = getStatusPanel(); break;
			case "Devices:Module 1:Status:Device": nodePanel = getStatusDevicePanel(); break;
			case "Devices:Module 1:Status:Network": nodePanel = getStatusNetworkPanel(); break;
			case "Devices:Module 1:Status:Chassis": nodePanel = getStatusChassisPanel(); break;
			case "Devices:Module 1:Status:Resources": nodePanel = getStatusResourcesPanel(); break;
			case "Devices:Module 1:Project Editor": nodePanel = getProjectEditorPanel(); break;
			case "Devices:Module 1:Project Manager": nodePanel = getProjectManagerPanel(); break;
			case "Devices:Module 1:Administration": nodePanel = getAdministrationPanel(); break;
			case "Devices:Module 1:Administration:Device": nodePanel = getAdminDevicePanel(); break;
			case "Devices:Module 1:Administration:Network": nodePanel = getAdminNetworkPanel(); break;
			case "Devices:Module 1:Administration:Users": nodePanel = getAdminUsersPanel(); break;
			case "Devices:Module 1:Logging": nodePanel = getLoggingPanel(); break;
		}
		if ( nodePanel != null )
		{
			nodePanel.setBorder(createTitledBorder(node.getID()));
			getCardLayoutPanel().add(node.getID(), nodePanel);
		}
	}

	/**
	 * The point where the JVM starts the execution of this application.
	 * 
	 * @param args The array of command line arguments.
	 */
	public static void main(String[] args)
	{
		// Create and show this JFrame applicati0on.
		new TreeCard1().setVisible(true);
	}
}
