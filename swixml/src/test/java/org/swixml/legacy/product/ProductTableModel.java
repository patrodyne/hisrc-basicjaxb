package org.swixml.legacy.product;

import static java.beans.Introspector.getBeanInfo;
import static org.jvnet.basicjaxb.lang.StringUtils.isBlank;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import org.swixml.jsr.widgets.JTableBind;
import org.swixml.jsr.widgets.TableColumnBind;

public class ProductTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 20240701L;
	
	public static BeanInfo getProductBeanInfo()
		throws IntrospectionException
	{
		return getBeanInfo(Product.class, Object.class);
	}

	private List<Product> productList;
	public List<Product> getProductList() { return productList; }
	public void setProductList(List<Product> productList) { this.productList = productList; }
	
	private JTable productTable;
	public JTable getProductTable() { return productTable; }
	public void setProductTable(JTable productTable) { this.productTable = productTable; }
	
	public ProductTableModel(List<Product> productList, JTable productTable)
	{
		super();
		setProductList(productList);
		if ( productTable instanceof JTableBind )
			((JTableBind) productTable).setBindClass(Product.class);
		setProductTable(productTable);
	}
	
	@Override
	public int getRowCount()
	{
		return getProductList().size();
	}

	private Integer columnCount = null;
	@Override
	public int getColumnCount()
	{
		if ( columnCount == null )
		{
			if ( getProductTable().getAutoCreateColumnsFromModel() )
				columnCount = Product.NAMES.length;
			else
				columnCount = getProductTable().getColumnModel().getColumnCount();
		}
		return columnCount;
	}
	
	@Override
	public String getColumnName(int columnIndex)
	{
		String name = null;
		if ( getProductTable().getAutoCreateColumnsFromModel() )
			name = Product.NAMES[columnIndex];
		else
		{
			TableColumn tc = getProductTable().getColumnModel().getColumn(columnIndex);
			Object hv = tc.getHeaderValue();
			name = (hv != null) ? (String) hv : super.getColumnName(columnIndex);
		}
		return name;
	}
	
    @Override
	public Class<?> getColumnClass(int columnIndex)
    {
    	Class<?> type = null;
    	if ( getProductTable().getAutoCreateColumnsFromModel() )
    		type = Product.TYPES[columnIndex];
    	else
    	{
			TableColumnBind tcb = (TableColumnBind) getProductTable().getColumnModel().getColumn(columnIndex);
    		if ( !isBlank(tcb.getType()) )
    		{
    			try
				{
					type = Class.forName(tcb.getType());
				}
				catch (ClassNotFoundException e)
				{
					type = super.getColumnClass(columnIndex);
				}
    		}
    		else
    			type = super.getColumnClass(columnIndex);
    	}
        return type;
    }
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		return getProductList().get(rowIndex).get(columnIndex);
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex)
	{
		getProductList().get(rowIndex).set(columnIndex, value);
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		boolean isCellEditable = false;
		
		if ( getProductTable().getAutoCreateColumnsFromModel() )
		{
			PropertyDescriptor pd = findPropertyDescriptor(Product.NAMES[columnIndex]);
			if ( pd != null )
				isCellEditable = (pd.getWriteMethod() != null);
		}
		else
		{
			TableColumnBind tcb = (TableColumnBind) getProductTable().getColumnModel().getColumn(columnIndex);
			isCellEditable = tcb.isEditable();
		}
		return isCellEditable;
	}
	
	private PropertyDescriptor findPropertyDescriptor(String name)
	{
		PropertyDescriptor propertyDescriptor = null;
		try
		{
			for ( PropertyDescriptor pd : getProductBeanInfo().getPropertyDescriptors() )
			{
				if ( pd.getName().equals(name) )
				{
					propertyDescriptor = pd;
					break;
				}
			}
		}
		catch (IntrospectionException ie)
		{
			propertyDescriptor = null;
		}
		return propertyDescriptor;
	}
}
