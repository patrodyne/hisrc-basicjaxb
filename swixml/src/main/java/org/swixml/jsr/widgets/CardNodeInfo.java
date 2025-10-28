package org.swixml.jsr.widgets;

import java.awt.Component;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.lang.reflect.Method;

import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.table.JTableHeader;

import org.jvnet.basicjaxb.lang.DataBeanInfo;
import org.jvnet.basicjaxb.lang.DataDescriptor;
import org.swixml.XScrollPane;

public class CardNodeInfo
{
	private String nodeName;
	public String getNodeName() { return nodeName; }
	public void setNodeName(String nodeName){ this.nodeName = nodeName; }

	private Class<?> cardClass;
	public Class<?> getCardClass() { return cardClass; }
	public void setCardClass(Class<?> cardClass) { this.cardClass = cardClass; }

	private String cardName;
	public String getCardName()
	{
		if ( (cardName == null) && (getCardClass() != null) && (getCardClass().getName() != null) )
			setCardName(getCardClass().getName());
		return cardName;
	}
	public void setCardName(String cardName)
	{
		this.cardName = cardName;
	}

	private DataBeanInfo dataBeanInfo;
	public DataBeanInfo getDataBeanInfo()
	{
		if ( dataBeanInfo == null )
		{
			try
			{
				if ( getCardClass() != null )
				{
					BeanInfo beanInfo = Introspector.getBeanInfo(getCardClass());
					if ( beanInfo instanceof DataBeanInfo )
						setDataBeanInfo((DataBeanInfo) beanInfo);
					else
						setDataBeanInfo(new DataBeanInfo(beanInfo));
				}
				else
					setDataBeanInfo(new DataBeanInfo());
			}
			catch (IntrospectionException e)
			{
				setDataBeanInfo(new DataBeanInfo());
			}
		}
		return dataBeanInfo;
	}
	public void setDataBeanInfo(DataBeanInfo dataBeanInfo)
	{
		this.dataBeanInfo = dataBeanInfo;
	}

	private JTableBind cardTableBind;
	public JTableBind getCardTableBind()
	{
		return cardTableBind;
	}
	public void setCardTableBind(JTableBind cardTableBind)
	{
		this.cardTableBind = cardTableBind;
	}

	private JTableHeader cardTableHead;
	public JTableHeader getCardTableHead()
	{
		return cardTableHead;
	}
	public void setCardTableHead(JTableHeader cardTableHead)
	{
		this.cardTableHead = cardTableHead;
	}

	public void setCardTableComponents(JPanel cardPanel)
	{
		if ( (getCardTableBind() == null) || (getCardTableHead() == null) )
		{
			for ( Component cardComp : cardPanel.getComponents() )
			{
				if ( cardComp.isVisible() && (cardComp instanceof XScrollPane) )
				{
					XScrollPane cardPane = (XScrollPane) cardComp;
					for ( Component cardPaneComp : cardPane.getComponents() )
					{
						if ( cardPaneComp instanceof JViewport )
						{
							JViewport cardPaneView = (JViewport) cardPaneComp;
							for ( Component cardPaneViewComp : cardPaneView.getComponents() )
							{
								if ( cardPaneViewComp instanceof JTableBind )
									setCardTableBind((JTableBind) cardPaneViewComp);
								else if ( cardPaneViewComp instanceof JTableHeader )
									setCardTableHead((JTableHeader) cardPaneViewComp);
							}
						}
					}
				}
			}
		}
	}

	public CardNodeInfo(String nodeName, String cardName)
	{
		setNodeName(nodeName);
		setCardName(cardName);
	}

	public CardNodeInfo(String nodeName, Class<?> cardClass)
	{
		setNodeName(nodeName);
		setCardClass(cardClass);
	}

	@Override
	public String toString()
	{
		DataDescriptor dd = getDataBeanInfo().getDataDescriptor();
		Method jewm = dd.getJAXBElementWrapperMethod();
		if ( dd.isXmlRootElement() || (jewm != null) )
			return "<html><<b>"+getNodeName()+"</b></html>";
		else
			return getNodeName();
	}
}

