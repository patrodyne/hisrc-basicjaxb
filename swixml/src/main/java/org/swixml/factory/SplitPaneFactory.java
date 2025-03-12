package org.swixml.factory;

import org.swixml.SwingEngine;
import org.swixml.XSplitPane;
import org.swixml.dom.Attribute;
import org.swixml.processor.TagProcessor;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public class SplitPaneFactory extends BeanFactory
{
	public SplitPaneFactory(TagProcessor processor, SwingEngine<?> engine)
	{
		super(XSplitPane.class, processor, engine);
	}

	public SplitPaneFactory(SwingEngine<?> engine)
	{
		super(XSplitPane.class, engine);
	}

	@Override
	public void setProperty(Object bean, Attribute attr, Object value, Class<?> type)
		throws Exception
	{
		Class<?> propType = getPropertyType(attr);
		if ( propType == Double.TYPE )
			super.setProperty(bean, attr, value, Double.TYPE);
		else if ( propType == Integer.TYPE )
			super.setProperty(bean, attr, value, Integer.TYPE);
		else
			super.setProperty(bean, attr, value, type);
	}
	
	@Override
	public Class<?>[] getPropertyType(Object bean, Attribute attr)
	{
		Class<?>[] propTypes = null;
		Class<?> propType = getPropertyType(attr);
		if ( propType != null )
			propTypes = new Class<?>[] { propType };
		else
			propTypes = super.getPropertyType(bean, attr);
		return propTypes;
	}
	
	private Class<?> getPropertyType(Attribute attr)
	{
		Class<?> propType = null;
		if ( "dividerlocation".equalsIgnoreCase(attr.getLocalName()) )
		{
			if ( attr.getValue().contains(".") )
				propType = Double.TYPE;
			else
				propType = Integer.TYPE;
		}
		return propType;
	}
}
