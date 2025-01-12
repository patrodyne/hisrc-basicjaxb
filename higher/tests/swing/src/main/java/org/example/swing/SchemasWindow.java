package org.example.swing;

import static org.jdesktop.observablecollections.ObservableCollections.observableList;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import org.example.PurchaseOrder.model.Credit;
import org.example.PurchaseOrder.model.Item;
import org.example.PurchaseOrder.model.ObjectFactory;
import org.example.PurchaseOrder.model.Payment;
import org.example.PurchaseOrder.model.PurchaseOrder;
import org.example.PurchaseOrder.model.USAddress;
import org.jdesktop.application.Action;
import org.jvnet.basicjaxb.lang.DataBeanInfo;
import org.jvnet.basicjaxb.lang.DataDescriptor;
import org.jvnet.basicjaxb.xml.XmlContext;
import org.swixml.jsr.widgets.CardNodeInfo;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.JAXBIntrospector;

/**
 * The top level {@link Window} for the {@link SchemasTool}.
 * 
 * <p>
 * This {@link Window} can be a {@link JFrame} or a {@link JDialog}.
 * </p>
 */
public class SchemasWindow extends JFrame
{
	private static final long serialVersionUID = 20241001L;
	
	public static final String SAMPLE01_PO_FILE = "src/test/samples/PurchaseOrder01.xml";
	
	private XmlContext xmlContext;
	public XmlContext getXmlContext()
	{
		if ( xmlContext == null )
			setXmlContext(new XmlContext(ObjectFactory.class));
		return xmlContext;
	}
	public void setXmlContext(XmlContext xmlContext)
	{
		this.xmlContext = xmlContext;
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

	// Bound by id in SchemasWindow.xml
	private JTree schemasTree;
	public JTree getSchemasTree() { return schemasTree; }
	public void setSchemasTree(JTree schemasTree) { this.schemasTree = schemasTree; }
	
	private JPanel mainPanel;
	public JPanel getMainPanel() { return mainPanel; }
	public void setMainPanel(JPanel mainPanel) { this.mainPanel = mainPanel; }

	private TreeModel schemasTreeModel;
	public TreeModel getSchemasTreeModel()
	{
		if ( schemasTreeModel == null )
			setSchemasTreeModel(new SchemasTreeModel());
		return schemasTreeModel;
	}
	public void setSchemasTreeModel(TreeModel schemasTreeModel)
	{
		this.schemasTreeModel = schemasTreeModel;
	}
	
	private TreeCellRenderer schemasTreeCellRenderer = null;
	public TreeCellRenderer getSchemasTreeCellRenderer()
	{
		if ( schemasTreeCellRenderer == null )
		{
			schemasTreeCellRenderer = new DefaultTreeCellRenderer()
			{
			    private static final long serialVersionUID = 20241201L;

				@Override
				public Color getBackgroundNonSelectionColor()
			    {
			        return getSchemasTree().getBackground();
			    }
			};
		}
		return schemasTreeCellRenderer;
	}
	public void setSchemasTreeCellRenderer(TreeCellRenderer schemasTreeCellRenderer)
	{
		this.schemasTreeCellRenderer = schemasTreeCellRenderer;
	}

	/* bindWith="query" */
	private StringBuilder query = new StringBuilder();
	public final String getQuery()
	{
		if ( query == null )
			setQuery(new StringBuilder());
		return query.toString();
	}
	public void setQuery(StringBuilder query)
	{
		this.query = ( query != null ) ? query : new StringBuilder();
	}
	public final void setQuery(String query)
	{
		setQuery(new StringBuilder(query));
		// force the widget update
		firePropertyChange("query", null, null); 
	}
	@SuppressWarnings("unused")
	private void appendQuery(String value)
	{
		this.query.append(value).append('\n');
		// force the widget update
		firePropertyChange("query", null, null); 
	}

	/* bindWith="result" */
	private StringBuilder result = new StringBuilder();
	public final String getResult()
	{
		return result.toString();
	}
	public void setResult(StringBuilder result)
	{
		this.result = ( result != null ) ? result : new StringBuilder();
	}
	public final void setResult(String result)
	{
		setResult(new StringBuilder(result));
		// force the widget update
		firePropertyChange("result", null, null); 
	}
	private void appendResult(String value)
	{
		this.result.append(value).append('\n');
		// force the widget update
		firePropertyChange("result", null, null); 
	}
	
	@Action
	public void selectNode(ActionEvent ae)
	{
		TreeSelectionEvent ev = (TreeSelectionEvent) ae.getSource();
		DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) ev.getPath().getLastPathComponent();
		if ( treeNode.getUserObject() instanceof CardNodeInfo )
		{
			CardNodeInfo nodeInfo = (CardNodeInfo) treeNode.getUserObject();
			CardLayout cardLayout = (CardLayout) getMainPanel().getLayout();
			cardLayout.show(getMainPanel(), nodeInfo.getCardName());
		}
	}
	
	@Action
	public void select(ActionEvent ae)
	{
		switch ( ae.getActionCommand() )
		{
			case "colSelection":
				break;
			case "rowSelection":
				DataBeanInfo dbi = (DataBeanInfo) ae.getSource();
				DataDescriptor dd = dbi.getDataDescriptor();
				Method jewm = dd.getJAXBElementWrapperMethod();
				@SuppressWarnings("unchecked")
				List<Object> rows = (List<Object>) dbi.getData();
				setResult((StringBuilder) null);
				for ( Object row : rows )
				{
					try
					{
						if ( dd.isXmlRootElement() )
							appendResult(getXmlContext().marshalToString(row));
						else if ( jewm != null )
						{
							JAXBElement<?> jeRow = (JAXBElement<?>) jewm.invoke(dd.getObjectFactory(), row);
							appendResult(getXmlContext().marshalToString(jeRow));
						}
						else
							appendResult(row.toString());
					}
					catch (JAXBException | IOException | ReflectiveOperationException ex)
					{
						appendResult(row.toString());
					}
				}
				break;
		}
	}
	
	public void initialize()
		throws JAXBException
	{
		Object obj = getXmlContext().unmarshal(SAMPLE01_PO_FILE);
		PurchaseOrder po01 = (PurchaseOrder) JAXBIntrospector.getValue(obj);
		
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
	public SchemasWindow()
	{
		super();
	}
}
