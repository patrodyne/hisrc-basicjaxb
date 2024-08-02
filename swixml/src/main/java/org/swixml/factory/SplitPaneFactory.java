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
		final String name = attr.getLocalName();
		if ( "dividerlocation".equalsIgnoreCase(name) )
		{
			if ( attr.getValue().contains(".") )
				super.setProperty(bean, attr, attr.getDoubleValue(), Double.TYPE);
			else
				super.setProperty(bean, attr, attr.getIntValue(), Integer.TYPE);
			return;
		}
		super.setProperty(bean, attr, value, type);
	}
}
