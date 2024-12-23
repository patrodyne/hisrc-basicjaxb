package org.jvnet.basicjaxb.plugin.beaninfo;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.beaninfo.model.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

/**
 * Custom elements for the beaninfo plugin namespace.
 */
public class Customizations
{
	public static String NAMESPACE_URI = "http://jvnet.org/basicjaxb/xjc/beaninfo";
	public static QName BEAN_ELEMENT_NAME = new QName(NAMESPACE_URI, "bean");
	public static QName PROPERTY_ELEMENT_NAME = new QName(NAMESPACE_URI, "property");
	
    private static final Logger logger = LoggerFactory.getLogger(Customizations.class);
    public static Logger getLogger() { return logger; }
	
	private static final JAXBContext context;
	static
	{
		try
		{
			context = JAXBContext.newInstance
			(
				ObjectFactory.class.getPackage().getName(),
				ObjectFactory.class.getClassLoader()
			);
		}
		catch (JAXBException ex)
		{
			getLogger().error("Cannot initialize JAXBContext", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static JAXBContext getContext()
	{
		return context;
	}
}
