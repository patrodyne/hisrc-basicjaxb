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
	
//	public static void _bean(CClassInfo classInfo, String displayName)
//	{
//		final Bean bean = new Bean();
//		bean.setDisplayName(displayName);
//		
//		final Locator locator = getLocator(classInfo);
//		final CPluginCustomization customization =
//			CustomizationUtils.marshal(getContext(), BEAN_ELEMENT_NAME, bean, locator);
//		classInfo.getCustomizations().add(customization);
//		customization.markAsAcknowledged();
//	}
//
//	public static void _property(CClassInfo classInfo, String... displayNames)
//	{
//		for (String displayName : displayNames)
//		{
//			final Property property = new Property();
//			property.setDisplayName(displayName);
//			
//			final Locator locator = getLocator(classInfo);
//			final CPluginCustomization customization =
//				marshal(getContext(), PROPERTY_ELEMENT_NAME, property, locator);
//			classInfo.getCustomizations().add(customization);
//			customization.markAsAcknowledged();
//		}
//	}
}
