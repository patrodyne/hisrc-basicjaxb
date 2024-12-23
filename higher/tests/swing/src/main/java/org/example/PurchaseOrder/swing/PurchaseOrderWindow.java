package org.example.PurchaseOrder.swing;

import static java.lang.System.out;
import static org.jdesktop.observablecollections.ObservableCollections.observableList;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.example.PurchaseOrder.model.Credit;
import org.example.PurchaseOrder.model.Item;
import org.example.PurchaseOrder.model.Payment;
import org.example.PurchaseOrder.model.PurchaseOrder;
import org.example.PurchaseOrder.model.USAddress;
import org.example.PurchaseOrder.swing.MainTreeModel.NodeInfo;
import org.jdesktop.application.Action;

import jakarta.xml.bind.JAXBException;

/**
 * The top level {@link Window} for the {@link PurchaseOrderTool}.
 * 
 * <p>
 * This {@link Window} can be a {@link JFrame} or a {@link JDialog}.
 * </p>
 */
public class PurchaseOrderWindow extends JFrame
{
	private static final long serialVersionUID = 20241001L;
	
	public static final String SAMPLE01_PO_FILE = "src/test/samples/PurchaseOrder01.xml";
	
	private Context context;
	public Context getContext()
	{
		if ( context == null )
			setContext(new Context());
		return context;
	}
	public void setContext(Context context)
	{
		this.context = context;
	}
	
	public Class<?> getPurchaseOrderClass()
	{
		return PurchaseOrder.class;
	}
	
	private List<PurchaseOrder> purchaseOrderList = null;
	public List<PurchaseOrder> getPurchaseOrderList()
	{
		if ( purchaseOrderList == null )
			purchaseOrderList = observableList(new ArrayList<PurchaseOrder>());
		return purchaseOrderList;
	}
	
	public Class<USAddress> getUSAddressClass()
	{
		return USAddress.class;
	}

	private List<USAddress> USAddressList = null;
	public List<USAddress> getUSAddressList()
	{
		if ( USAddressList == null )
			USAddressList = observableList(new ArrayList<USAddress>());
		return USAddressList;
	}

	public Class<Credit> getCreditClass()
	{
		return Credit.class;
	}
	
	private List<Credit> creditList;
	public List<Credit> getCreditList()
	{
		if ( creditList == null )
			creditList = observableList(new ArrayList<Credit>());
		return creditList;
	}
	
	public Class<Payment> getPaymentClass()
	{
		return Payment.class;
	}
	
	private List<Payment> paymentList;
	public List<Payment> getPaymentList()
	{
		if ( paymentList == null )
			paymentList = observableList(new ArrayList<Payment>());
		return paymentList;
	}
	
	public Class<Item> getItemClass()
	{
		return Item.class;
	}
	
	private List<Item> itemList = null;
	public List<Item> getItemList()
	{
		if ( itemList == null )
			itemList = observableList(new ArrayList<Item>());
		return itemList;
	}

	// Bound by id in PurchaseOrderWindow.xml
	private JTree mainTree;
	public JTree getMainTree() { return mainTree; }
	public void setMainTree(JTree mainTree) { this.mainTree = mainTree; }
	
	private JPanel mainPanel;
	public JPanel getMainPanel() { return mainPanel; }
	public void setMainPanel(JPanel mainPanel) { this.mainPanel = mainPanel; }

	private TreeModel mainTreeModel;
	public TreeModel getMainTreeModel()
	{
		if ( mainTreeModel == null )
			setMainTreeModel(new MainTreeModel());
		return mainTreeModel;
	}
	public void setMainTreeModel(TreeModel mainTreeModel)
	{
		this.mainTreeModel = mainTreeModel;
	}
	
	private TreeCellRenderer mainTreeCellRenderer = null;
	public TreeCellRenderer getMainTreeCellRenderer()
	{
		if ( mainTreeCellRenderer == null )
		{
			mainTreeCellRenderer = new DefaultTreeCellRenderer()
			{
			    private static final long serialVersionUID = 20241201L;

				@Override
				public Color getBackgroundNonSelectionColor()
			    {
			        return getMainTree().getBackground();
			    }
			};
		}
		return mainTreeCellRenderer;
	}
	public void setMainTreeCellRenderer(TreeCellRenderer mainTreeCellRenderer)
	{
		this.mainTreeCellRenderer = mainTreeCellRenderer;
	}
	
	@Action
	public void selectNode(ActionEvent ae)
	{
		TreeSelectionEvent ev = (TreeSelectionEvent) ae.getSource();
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) ev.getPath().getLastPathComponent();
		if ( treeNode.getUserObject() instanceof NodeInfo )
		{
			NodeInfo nodeInfo = (NodeInfo) treeNode.getUserObject();
			CardLayout cardLayout = (CardLayout) getMainPanel().getLayout();
			cardLayout.show(getMainPanel(), nodeInfo.getCardName());
		}
	}
	
	public void initialize()
		throws JAXBException
	{
		PurchaseOrder po01 = getContext().unmarshal(SAMPLE01_PO_FILE, PurchaseOrder.class);
		
		// PurchaseOrder list
		getPurchaseOrderList().add(po01);
		
		// USAddress list
		getUSAddressList().add(po01.getBillTo());
		getUSAddressList().add(po01.getShipTo());
		
		// Credit list
		for ( Credit credit : po01.getCredits() )
			getCreditList().add(credit);
		
		// Payment list
		for ( Payment payment : po01.getPayments().getPayment() )
			getPaymentList().add(payment);
		
		// Item list
		for ( Item item : po01.getItems().getItem() )
			getItemList().add(item);
	}
	
	/**
	 * Default constructor
	 */
	public PurchaseOrderWindow()
	{
		super();
	}
}
