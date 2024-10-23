package org.swixml.legacy.product;

import static java.beans.Introspector.getBeanInfo;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class ProductTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 20240701L;
	
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
		setProductTable(productTable);
	}
	
	@Override
	public int getRowCount()
	{
		return getProductList().size();
	}

	@Override
	public int getColumnCount()
	{
		return getProductTable().getColumnModel().getColumnCount();
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
	public String getColumnName(int columnIndex)
    {
    	TableColumn tc = getProductTable().getColumnModel().getColumn(columnIndex);
    	Object hv = tc.getHeaderValue();
    	return (hv != null) ? (String) hv : super.getColumnName(columnIndex);
    }
    
    @Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
    {
    	boolean isCellEditable = false;
    	try
		{
			BeanInfo bi = getBeanInfo(Product.class);
			PropertyDescriptor pd = bi.getPropertyDescriptors()[columnIndex];
			isCellEditable = (pd.getWriteMethod() != null);
		}
		catch (IntrospectionException e)
		{
			isCellEditable = false;
		}
    	return isCellEditable;
    }
}
