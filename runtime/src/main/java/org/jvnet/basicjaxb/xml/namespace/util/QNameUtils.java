package org.jvnet.basicjaxb.xml.namespace.util;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.bind.JAXBElement;

/**
 * A utility class to provide QName helper methods.
 */
public class QNameUtils
{
    private static Logger logger = LoggerFactory.getLogger(QNameUtils.class);
    public static Logger getLogger() { return logger; }

	// Constructor: Seal class for static use only
	private QNameUtils() { }
	
	/**
	 * Represent a {@link QName} in the format: <code>"{namespaceURI}prefix:localpart"</code>.
	 * 
	 * @param qname A QName to be represented.
	 * 
	 * @return A string representaion of the given {@link QName}.
	 */
	public static String toKey(QName qname)
	{
		if (qname == null)
			return null;
		
		final StringBuilder sb = new StringBuilder();
		
		// Build "{@link QName}"
		final String namespaceURI = qname.getNamespaceURI();
		if (!namespaceURI.equals(XMLConstants.NULL_NS_URI))
			sb.append("{").append(namespaceURI).append("}");
		
		// Add "prefix:".
		final String prefix = qname.getPrefix();
		if (!XMLConstants.DEFAULT_NS_PREFIX.equals(prefix))
			sb.append(prefix).append(":");
		
		// Add locale part.
		final String localPart = qname.getLocalPart();
		sb.append(localPart);
		
		// Return "{namespaceURI}prefix:localpart".
		return sb.toString();
	}
	
	public static QName toName(JAXBElement<?> jaxbElement, String qname)
	{
		String namespaceURI = jaxbElement.getName().getNamespaceURI();
		String localPart = jaxbElement.getName().getLocalPart();
		String prefix = jaxbElement.getName().getPrefix();
		if ( qname != null )
		{
			String[] splits = qname.split(":", 2);
			if ( splits.length > 1)
			{
				prefix = splits[0];
				localPart = splits[1];
			}
			else if (splits.length > 0 )
				localPart = splits[0];
		}
		return new QName(namespaceURI, localPart, prefix);
	}
}
