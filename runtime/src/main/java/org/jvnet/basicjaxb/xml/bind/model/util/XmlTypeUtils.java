package org.jvnet.basicjaxb.xml.bind.model.util;

import static java.util.Objects.requireNonNull;
import static org.glassfish.jaxb.core.api.impl.NameConverter.standard;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSchema;
import jakarta.xml.bind.annotation.XmlType;

/**
 * Utility methods to introspect on the {@link XmlType} annotation.
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
	 * Get the {@link QName} for the given target class.
	 * 
	 * <p>
	 * Introspect on the {@link XmlType} annotation when present or use a
	 * default identifier suitable for variables to determine the local part.
	 * </p>
	 * 
	 * <p>
	 * Introspect on the {@link XmlType} annotation when present or use a
	 * default namespace from the package containing the target class.
	 * </p>
	 * 
	 * @param targetClass The class used to resolve a {@link QName}.
	 * 
	 * @return The {@link QName} for the given target class.
	 */
	public static QName getTypeName(Class<?> targetClass)
	{
		requireNonNull(targetClass);
		final Package targetPackage = targetClass.getPackage();
		final XmlType xmlTypeAnnotation = targetClass.getAnnotation(XmlType.class);
		final String localPart;
		final String namespaceURI;
		final String prefix;
		
		if ( xmlTypeAnnotation == null )
		{
			localPart = standard.toVariableName(targetClass.getSimpleName());
			namespaceURI = getNamespace(targetPackage);
		}
		else
		{
			final String name = xmlTypeAnnotation.name();
			if ( (name == null) || "".equals(name))
				localPart = null;
			else
			{
				if ( "##default".equals(name) )
					localPart = standard.toVariableName(targetClass.getSimpleName());
				else
					localPart = name;
			}
			
			final String namespace = xmlTypeAnnotation.namespace();
			if ( (namespace == null) || "".equals(namespace) )
				namespaceURI = "";
			else
			{
				if ( "##default".equals(namespace) )
					namespaceURI = getNamespace(targetPackage);
				else
					namespaceURI = namespace;
			}
		}
		
		if (localPart == null)
			return null;
		else
			prefix = getPrefix(targetPackage, namespaceURI);
		
		return (prefix == null) ? new QName(namespaceURI, localPart) : new QName(namespaceURI, localPart, prefix);
	}

	/**
	 * Get the XML namespace prefix for the given package and namespace.
	 * 
	 * @param targetPackage The target package to extract the XML namespace.
	 * @param namespaceURI The XML namespace used to select the prefix.
	 * 
	 * @return The XML namespace prefix or null.
	 */
	public static String getPrefix(final Package targetPackage, String namespaceURI)
	{
		String prefix;
		final Map<String, String> namespacePrefixes = new HashMap<String, String>();
		if ( targetPackage != null )
		{
			final XmlSchema xmlSchemaAnnotation = targetPackage.getAnnotation(XmlSchema.class);
			if ( xmlSchemaAnnotation != null )
			{
				for ( XmlNs xmlns : xmlSchemaAnnotation.xmlns() )
					namespacePrefixes.put(xmlns.namespaceURI(), xmlns.prefix());
			}
		}
		prefix = namespacePrefixes.get(namespaceURI);
		return prefix;
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
