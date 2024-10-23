package org.swixml.legacy;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

import org.swixml.SwingEngine;
import org.swixml.legacy.product.Product;
import org.swixml.legacy.product.ProductTableModel;

/**
 * The Accelerator shows in the usage of accelerators.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml (#101)
 */
public class Accelerator extends JDialog
{
	private static final long serialVersionUID = 20240701L;

	private static final String DESCRIPTOR = "org/swixml/legacy/accelerator.xml";
	
	private SwingEngine<JDialog> swix = new SwingEngine<>(this);
	
	/* automatically bound by id="tree" */
	private JTree tree;
	public JTree getTree() { return tree; }
	public void setTree(JTree tree) { this.tree = tree; }
	
	/* tree bound by treeCellRenderer="${window.treeCellRenderer}" */
	private TreeCellRenderer treeCellRenderer = null;
	public TreeCellRenderer getTreeCellRenderer()
	{
		if ( treeCellRenderer == null )
		{
			treeCellRenderer = new DefaultTreeCellRenderer()
			{
			    private static final long serialVersionUID = 20240701L;

				@Override
				public Color getBackgroundNonSelectionColor()
			    {
			        return getTree().getBackground();
			    }
			};
		}
		return treeCellRenderer;
	}
	public void setTreeCellRenderer(TreeCellRenderer treeCellRenderer)
	{
		this.treeCellRenderer = treeCellRenderer;
	}
	
	/* bound by id="table" */
	private JTable table;
	public JTable getTable() { return table; }
	public void setTable(JTable table) { this.table = table; }

	private List<Product> productList;
	public List<Product> getProductList()
	{
		if ( productList == null )
		{
			productList = new ArrayList<>();
			Properties products = new Properties();
			try ( InputStream is = resourceAsStream(Product.class, "org/swixml/legacy/product/Product.properties") )
			{
				products.load(new InputStreamReader(is));
			}
			catch (IOException ex)
			{
				showErrorDialog(ex);
			}
			for ( Entry<Object, Object> product : products.entrySet() )
			{
				String partNum = (String) product.getKey();
				String value = (String) product.getValue();
				productList.add(new Product(partNum, value));
			}
		}
		return productList;
	}
	public void setProductList(List<Product> productList)
	{
		this.productList = productList;
	}
	
	public Accelerator()
		throws Exception
	{
		swix.getELProcessor().defineBean("el", swix.getELMethods());
		swix.getELProcessor().defineBean("window", this);
		swix.render(DESCRIPTOR);
		
		getTable().setModel(new ProductTableModel(getProductList(), getTable()));
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public Action cmdAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			String cmd = ae.getActionCommand();
			showMessageDialog(Accelerator.this, "Sorry, '"+cmd+"' is not implemented yet.");
		}
	};
	
	public Action newAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			showMessageDialog(Accelerator.this, "Sorry, 'New' is not implemented yet.");
		}
	};
	
	public Action aboutAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			showMessageDialog(Accelerator.this, "This is the Accelerator Example.");
		}
	};
	
	public Action exitAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			showMessageDialog(Accelerator.this, "Sorry, 'Exit' is not implemented yet.");
		}
	};
	
	private InputStream resourceAsStream(Class<Product> clazz, String resource)
	{
		return clazz.getClassLoader().getResourceAsStream(resource);
	}
	
	private static void showErrorDialog(Exception ex)
	{
		ex.printStackTrace();
		String msg = ex.getClass().getSimpleName() + ": " + ex.getMessage() +"\n";
		showMessageDialog(null, msg, "ERROR", ERROR_MESSAGE);
	} 

	public static void main(String[] args)
	{
		try
		{
			new Accelerator();
		}
		catch (Exception ex)
		{
			showErrorDialog(ex);
		}
	}
}
