package org.example.PurchaseOrder.swing;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

import org.example.PurchaseOrder.model.Credit;
import org.example.PurchaseOrder.model.Item;
import org.example.PurchaseOrder.model.Payment;
import org.example.PurchaseOrder.model.PurchaseOrder;
import org.example.PurchaseOrder.model.USAddress;
import org.swixml.jsr.widgets.CardNodeInfo;
import org.swixml.jsr.widgets.MutableTreeModel;

public class SchemasTreeModel
	extends MutableTreeModel
{
	private static final long serialVersionUID = 20241201L;
	
	public SchemasTreeModel()
	{
		super("Schemas");
		setRoot((MutableTreeNode) super.getRoot());
		
		MutableTreeNode purchaseOrderSchemaNode = new DefaultMutableTreeNode(PurchaseOrder.class.getPackageName());
		getRoot().insert(purchaseOrderSchemaNode, 0);
		
		MutableTreeNode purchaseOrderNode = new DefaultMutableTreeNode(new CardNodeInfo("PurchaseOrder", PurchaseOrder.class));
		purchaseOrderSchemaNode.insert(purchaseOrderNode, 0);

		MutableTreeNode purchaseOrderBillToNode = new DefaultMutableTreeNode(new CardNodeInfo("billTo", USAddress.class));
		purchaseOrderNode.insert(purchaseOrderBillToNode, 0);

		MutableTreeNode purchaseOrderShipToNode = new DefaultMutableTreeNode(new CardNodeInfo("shipTo", USAddress.class));
		purchaseOrderNode.insert(purchaseOrderShipToNode, 1);

		MutableTreeNode purchaseOrderItemsNode = new DefaultMutableTreeNode(new CardNodeInfo("items", Item.class));
		purchaseOrderNode.insert(purchaseOrderItemsNode, 2);

		MutableTreeNode purchaseOrderPaymentsNode = new DefaultMutableTreeNode(new CardNodeInfo("payments", Payment.class));
		purchaseOrderNode.insert(purchaseOrderPaymentsNode, 3);
		
		MutableTreeNode purchaseOrderCreditsNode = new DefaultMutableTreeNode(new CardNodeInfo("credits", Credit.class));
		purchaseOrderNode.insert(purchaseOrderCreditsNode, 4);
	}
}
