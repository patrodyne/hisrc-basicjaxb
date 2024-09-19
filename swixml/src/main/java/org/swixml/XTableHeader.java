package org.swixml;

import static java.lang.System.identityHashCode;

import java.awt.Color;
import java.awt.Font;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class XTableHeader extends JTableHeader
{
	private static final long serialVersionUID = 20240901L;

    public XTableHeader()
    {
        this(null);
    }
    
    public XTableHeader(TableColumnModel cm)
    {
    	super(cm);
    }
	
	@Override
	public void setBackground(Color bg)
	{
		super.setBackground(bg);
		System.out.println("OUT: XTableHeader Background.: " + identityHashCode(this) + "; "+ bg);
	}
	
	@Override
	public void setFont(Font font)
	{
		super.setFont(font);
		System.out.println("OUT: XTableHeader Font.......: "  + identityHashCode(this) + "; "+ font);
	}
	
//	private static final DefaultTableCellRenderer head_render = new DefaultTableCellRenderer(); 
//    
//	@Override
//	public TableCellRenderer getDefaultRenderer()
//	{
//		return head_render;
//	}
}
