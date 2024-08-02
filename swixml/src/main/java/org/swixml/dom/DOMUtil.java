package org.swixml.dom;

import static java.lang.String.format;

import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.swixml.LogAware;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 */
public class DOMUtil implements LogAware
{
	protected DOMUtil()
	{
	}

	public static org.w3c.dom.Element getChildByTagName(org.w3c.dom.Element parent, String tagName)
	{
		return getChildByTagName(parent, tagName, null);
	}

	public static org.w3c.dom.Element getChildByTagName(org.w3c.dom.Element parent, String tagName, String namespaceURI)
	{
		NodeList children = parent.getChildNodes();
		for ( int i = 0; i < children.getLength(); ++i )
		{
			org.w3c.dom.Node n = children.item(i);
			if ( n instanceof org.w3c.dom.Element )
			{
				org.w3c.dom.Element e = (org.w3c.dom.Element) n;
				if ( tagName.equals(e.getTagName())
						&& (namespaceURI == null || e.getNamespaceURI().equals(namespaceURI)) )
				{
					return e;
				}
			}
		}
		return null;
	}

	public static java.util.List<Node> getContent(Element source)
	{
		logger.warn(String.format("getContent of [%s] ", source.getLocalName()));
		return Collections.emptyList();
	}

	public static DocumentBuilder getDocumentBuilder()
		throws ParserConfigurationException
	{
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		builderFactory.setValidating(false);
		builderFactory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder builder = null;
		builder = builderFactory.newDocumentBuilder();
		return builder;
	}

	/**
	 * Recursive element by id finder
	 *
	 * @param element <code>Element</code> start node
	 * @param name attribute name
	 * @param value <code>String</code> attribute value to look for
	 * @return <code>Element</code> - with the given id in the id attribute or
	 *         null if not found
	 */
	public static Element findByAttribute(final Element element, final String name, final String value)
	{
		Element elem = null;
		Attr attr = element.getAttributeNode(name);
		if ( attr != null && value.equals(attr.getValue().trim()) )
		{
			elem = element;
		}
		else
		{
			NodeList children = element.getChildNodes();
			for ( int i = 0; i < children.getLength(); ++i )
			{
				Node n = children.item(i);
				if ( n instanceof Element )
				{
					elem = findByAttribute((Element) n, name, value);
					if ( elem != null )
						break;
				}
			}
		}
		return elem;
	}

	/**
	 * Moves the content from the source into the traget <code>Element</code>
	 *
	 * @param source <code>Element</code> Content provider
	 * @param target <code>Element</code> Content receiver
	 */
	public static void moveContent(Element source, Element target)
	{
		NodeList list = source.getChildNodes();
		for ( int i = 0; i < list.getLength(); i++ )
		{
			Node node = target.getOwnerDocument().importNode(list.item(i), true);
			target.appendChild(node);
		}
	}

	/**
	 * 
	 * @param e
	 * @return
	 */
	public static String getText(Element e)
	{
		if ( e == null )
			return null;
		Node n = e.getFirstChild();
		return (n != null && n.getNodeType() == Node.TEXT_NODE) ? n.getNodeValue() : null;
	}

	/**
	 * 
	 * @param map
	 */
	public static void print(NamedNodeMap map)
	{
		for ( int i = 0; i < map.getLength(); ++i )
		{
			Node n = map.item(i);
			logger.info(format("\tnode [%s] [%s] [%s] [%s]", n.getLocalName(), n.getNodeValue(),
				n.getNamespaceURI(), n.getPrefix()));
		}
	}
}
