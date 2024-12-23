package org.example.PurchaseOrder.swing;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

import org.example.PurchaseOrder.model.Credit;
import org.example.PurchaseOrder.model.Item;
import org.example.PurchaseOrder.model.Payment;
import org.example.PurchaseOrder.model.PurchaseOrder;
import org.example.PurchaseOrder.model.USAddress;

public class MainTreeModel
	extends DefaultTreeModel
{
	private static final long serialVersionUID = 20241201L;
	
	private MutableTreeNode root;
	@Override
	public MutableTreeNode getRoot() { return root; }
	public void setRoot(MutableTreeNode root) { this.root = root; }
	
	public class NodeInfo
	{
		private String nodeName;
		public String getNodeName() { return nodeName; }
		public void setNodeName(String nodeName) { this.nodeName = nodeName; }

		private Class<?> cardClass;
		public Class<?> getCardClass() { return cardClass; }
		public void setCardClass(Class<?> cardClass) { this.cardClass = cardClass; }
		
		public String getCardName()
		{
			return getCardClass().getName();
		}
		
		public NodeInfo(String nodeName, Class<?> cardClass)
		{
			setNodeName(nodeName);
			setCardClass(cardClass);
		}
		
		@Override
		public String toString()
		{
			return getNodeName();
		}
		
	}

	public MainTreeModel()
	{
		super(new DefaultMutableTreeNode("Schemas"));
		setRoot((MutableTreeNode) super.getRoot());
		
		MutableTreeNode purchaseOrderSchemaNode = new DefaultMutableTreeNode(PurchaseOrder.class.getPackageName());
		getRoot().insert(purchaseOrderSchemaNode, 0);
		
		MutableTreeNode purchaseOrderNode = new DefaultMutableTreeNode(new NodeInfo("PurchaseOrder", PurchaseOrder.class));
		purchaseOrderSchemaNode.insert(purchaseOrderNode, 0);

		MutableTreeNode purchaseOrderBillToNode = new DefaultMutableTreeNode(new NodeInfo("billTo", USAddress.class));
		purchaseOrderNode.insert(purchaseOrderBillToNode, 0);

		MutableTreeNode purchaseOrderShipToNode = new DefaultMutableTreeNode(new NodeInfo("shipTo", USAddress.class));
		purchaseOrderNode.insert(purchaseOrderShipToNode, 1);

		MutableTreeNode purchaseOrderItemsNode = new DefaultMutableTreeNode(new NodeInfo("items", Item.class));
		purchaseOrderNode.insert(purchaseOrderItemsNode, 2);

		MutableTreeNode purchaseOrderPaymentsNode = new DefaultMutableTreeNode(new NodeInfo("payments", Payment.class));
		purchaseOrderNode.insert(purchaseOrderPaymentsNode, 3);
		
		MutableTreeNode purchaseOrderCreditsNode = new DefaultMutableTreeNode(new NodeInfo("credits", Credit.class));
		purchaseOrderNode.insert(purchaseOrderCreditsNode, 4);
	}
}
