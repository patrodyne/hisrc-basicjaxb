package org.example.platce.util;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchema;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Utility methods to introspect on the {@link XmlType} annotation.
 * 
 * TODO: Replace with org.jvnet.basicjaxb.xml.bind.model.util.XmlTypeUtils.
 */
public class XmlTypeUtils
{
	// Seal this utility class for static use only.
	private XmlTypeUtils() { }
	
	/**
	 * Is the given object an instance of {@link JAXBElement}.
	 * 
	 * @param value The object to inspect.
	 * 
	 * @return True when the object is an instance of {@link JAXBElement};
	 *         otherwise, false.
	 */
	public static boolean isJAXBElement(Object value)
	{
		return (value instanceof JAXBElement);
	}
	
	/**
	 * Is the given object bound to an {@link XmlRootElement}.
	 * 
	 * @param value The object to inspect.
	 * 
	 * @return True when the object is bound to an {@link XmlRootElement};
	 *         otherwise, false.
	 */
	public static boolean isXmlRootElement(Object value)
	{
		return ( value != null ) ? isXmlRootElement(value.getClass()) : false;
	}
	
	/**
	 * Is the given target class bound to an {@link XmlRootElement}.
	 * 
	 * @param targetClass The class to inspect.
	 * 
	 * @return True when the target class is bound to an {@link XmlRootElement};
	 *         otherwise, false.
	 */
	public static boolean isXmlRootElement(Class<?> targetClass)
	{
		XmlRootElement rootAnno = null;
		if ( targetClass != null )
			rootAnno = targetClass.getAnnotation(XmlRootElement.class);
		return ( rootAnno != null ) ? true : false;
	}
	
	/**
	 * Get the XML namespace from given package.
	 * 
	 * @param targetPackage The target package to extract the XML namespace.
	 * 
	 * @return The XML namespace or an empty string.
	 */
	public static String getNamespace(final Package targetPackage)
	{
		String namespaceURI;
		if ( targetPackage == null )
			namespaceURI = "";
		else
		{
			final XmlSchema xmlSchemaAnnotation = targetPackage.getAnnotation(XmlSchema.class);
			if (xmlSchemaAnnotation == null)
				namespaceURI = "";
			else
			{
				final String packageNamespace = xmlSchemaAnnotation.namespace();
				if (packageNamespace == null || "".equals(packageNamespace))
					namespaceURI = "";
				else
					namespaceURI = packageNamespace;
			}
		}
		return namespaceURI;
	}
}
