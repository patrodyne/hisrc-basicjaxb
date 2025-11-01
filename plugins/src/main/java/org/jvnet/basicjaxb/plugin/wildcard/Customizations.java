package org.jvnet.basicjaxb.plugin.wildcard;

import static org.jvnet.basicjaxb.util.LocatorUtils.getLocator;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.wildcard.model.HandlerClass;
import org.jvnet.basicjaxb.plugin.wildcard.model.ObjectFactory;
import org.jvnet.basicjaxb.util.CustomizationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Locator;

import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CPluginCustomization;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

public class Customizations {

	public static String NAMESPACE_URI = "urn:jvnet.org:basicjaxb:xjc:wildcard";

	public static QName HANDLER_ELEMENT_NAME = new QName(NAMESPACE_URI, "handler");
	public static QName LAX_ELEMENT_NAME = new QName(NAMESPACE_URI, "lax");
	public static QName STRICT_ELEMENT_NAME = new QName(NAMESPACE_URI, "strict");
	public static QName SKIP_ELEMENT_NAME = new QName(NAMESPACE_URI, "skip");
	
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
	
	public static void _handler(CClassInfo classInfo, String className)
	{
		final HandlerClass handlerClass = new HandlerClass();
		handlerClass.setValue(className);
		final Locator locator = getLocator(classInfo);
		final CPluginCustomization customization =
			CustomizationUtils.marshal(getContext(), HANDLER_ELEMENT_NAME, handlerClass, locator);
		classInfo.getCustomizations().add(customization);
		customization.markAsAcknowledged();
	}
}
