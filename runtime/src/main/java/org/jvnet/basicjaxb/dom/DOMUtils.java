package org.jvnet.basicjaxb.dom;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI;
import static javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import jakarta.xml.bind.JAXBElement;

/**
 * A utility class to provide DOM helper methods.
 */
public class DOMUtils
{
    private static Logger logger = LoggerFactory.getLogger(DOMUtils.class);
    public static Logger getLogger() { return logger; }
    
	private static final DocumentBuilderFactory DOM_DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder DOM_DOCUMENT_BUILDER = null;
	static
	{
		DOM_DOCUMENT_BUILDER_FACTORY.setNamespaceAware(true);
		try
		{
			DOM_DOCUMENT_BUILDER = DOM_DOCUMENT_BUILDER_FACTORY.newDocumentBuilder();
		}
		catch (ParserConfigurationException pce)
		{
			getLogger().error("Cannot create DOM Document Builder", pce);
		}
	}

	// Constructor: Seal class for static use only
	private DOMUtils() { }
	
	/**
	 * Convert a QName instance into a W3C DOM Node instance.
	 * 
	 * @param qname The instance to be converted.
	 * 
	 * @return A W3C DOM Node representaion.
	 */
	public static Node toNode(QName qname, String value)
	{
		Document domDocument = DOM_DOCUMENT_BUILDER.newDocument();
		Element theElement = null;
		theElement = domDocument.createElementNS(qname.getNamespaceURI(), qname.getLocalPart());
		theElement.setPrefix("tns");
		theElement.setAttributeNS(XMLNS_ATTRIBUTE_NS_URI, "xmlns:"+theElement.getPrefix(), qname.getNamespaceURI());
		theElement.setAttributeNS(XMLNS_ATTRIBUTE_NS_URI, "xmlns:xsi", W3C_XML_SCHEMA_INSTANCE_NS_URI);
		Text valueNode = domDocument.createTextNode(value);
		theElement.appendChild(valueNode);
		// Element extends Node.
		return theElement;
	}
	
	/**
	 * Convert a JAXBElement instance into a W3C DOM Node instance.
	 * 
	 * @param jaxbElement The instance to be converted.
	 * @param prefix A flag to assign the prefix.
	 * 
	 * @return A W3C DOM Node representaion.
	 */
	public static Node toNode(JAXBElement<Object> jaxbElement, boolean prefix)
	{
		String value = jaxbElement.getValue() != null ? jaxbElement.getValue().toString() : null;
		return toNode(jaxbElement.getName(), value);
	}

	/**
	 * Convert a JAXBElement instance into a W3C DOM Node instance. Ignore the prefix.
	 * 
	 * @param jaxbElement The instance to be converted.
	 * 
	 * @return A W3C DOM Node representaion.
	 */
	public static Node toNode(JAXBElement<Object> jaxbElement)
	{
		String value = jaxbElement.getValue() != null ? jaxbElement.getValue().toString() : null;
		return toNode(jaxbElement.getName(), value);
	}
	
	/**
	 * Create a W3C DOM Node instance.
	 * 
	 * @param namespaceURI The node's namespace URI.
	 * @param localPart The node's local part (name).
	 * @param prefix The namespace prefix for this node.
	 * @param value The node's value.
	 * 
	 * @return A W3C DOM Node representaion.
	 */
	public static Node toNode(String namespaceURI, String localPart, String prefix, String value)
	{
		return toNode(new QName(namespaceURI, localPart, prefix), value);
	}
	
	/**
	 * <p>Tests whether two nodes are equal.</p>
	 * 
	 * <p>
	 * This method tests for equality of nodes, <em>not sameness</em> (i.e., whether the two nodes are
	 * references to the same object), which can be tested with <code>Node.isSameNode</code>. All
	 * nodes that are the same will also be equal, though the reverse may not be true.
	 * </p>
	 * 
	 * <p>Two nodes are equal <em>if and only if</em> the following conditions are satisfied:</p>
	 * 
	 * <ul>
	 *   <li>The two nodes are of the same type.</li>
	 *   <li>The following string attributes are equal (they are both <code>null</code>,
	 *   or they have the same length and are character for character identical):
	 *   <ul>
	 *     <li><code>namespaceURI</code></li>
	 *     <li><code>localName</code></li>
	 *     <li>Optional:
	 *       <ul>
	 *         <li><code>prefix</code>: The prefix xmlns is used only to declare namespace bindings.</li>
	 *         <li><code>nodeName</code>: Concatenation of <code>prefix</code>, <code>":"</code> and <code>localName</code>.</li>
	 *       </ul>
	 *     </li>
	 *     <li><code>nodeValue</code></li>
	 *     <li><code>baseURI</code></li>
	 *   </ul></li>
	 *   <li>The <code>attributes</code> <code>NamedNodeMaps</code> are equal (they are both
	 *   <code>null</code>, or they have the same map size and for each node that exists in one map
	 *   there is a node that exists in the other map and is equal, although not necessarily at
	 *   the same index).</li>
	 *   <li>The <code>childNodes</code> <code>NodeLists</code> are equal (they are both <code>null</code>,
	 *   or they have the same length and contain equal nodes at the same index).</li>
	 * </ul>
	 * 
	 * <p><b>Note:</b> Normalization can affect equality; to avoid this, nodes should be normalized
	 * before being compared.</p>
	 * 
	 * <p>For two <code>DocumentType</code> nodes to be equal, the following conditions must also be
	 * satisfied:</p>
	 * 
	 * <ul>
	 * <li>The following string attributes are equal:
	 *   <ul>
	 *     <li><code>publicId</code></li>
	 *     <li><code>systemId</code> </li>
	 *     <li><code>internalSubset</code></li>
	 *   </ul>
	 * </li>
	 * <li>The <code>entities</code> <code>NamedNodeMaps</code> are equal.</li>
	 * <li>The <code>notations</code> <code>NamedNodeMaps</code> are equal.</li>
	 * </ul>
	 * 
	 * <p>On the other hand, the following do not affect equality:</p>
	 * 
	 * <ul>
	 *   <li>The <code>ownerDocument</code> attribute.</li>
	 *   <li>The <code>specified</code> attribute for <code>Attr</code> nodes.</li>
	 *   <li>The <code>isWhitespaceInElementContent</code> attribute for <code>Text</code> nodes.</li>
	 *   <li>As well as any user data or event listeners registered on the nodes.</li>
	 * </ul>
	 *
	 * <p>
     * <b>Note:</b> This method is derived from <code>com.sun.org.apache.xerces.internal.dom.NodeImpl</code>.
     * </p>
	 * 
	 * @param node1 The first node to compare equality with.
	 * @param node2 The second node to compare equality with.
	 * @param ignorePrefix When true, ignore the <code>prefix</code> related values.
	 * 
	 * @return If the nodes are equal, <code>true</code>; otherwise <code>false</code>.
	 */
	public static boolean areEqualNodes(Node node1, Node node2, boolean ignorePrefix)
	{
		if (node1 == node2)
			return true;
		
		if (node1.getNodeType() != node2.getNodeType())
			return false;
		
		// NamespaceURI
		if (node1.getNamespaceURI() == null)
		{
			if (node2.getNamespaceURI() != null)
				return false;
		}
		else if (!node1.getNamespaceURI().equals(node2.getNamespaceURI()))
			return false;
		
		// LocalName
		if (node1.getLocalName() == null)
		{
			if (node2.getLocalName() != null)
				return false;
		}
		else if (!node1.getLocalName().equals(node2.getLocalName()))
			return false;
		
		if ( !ignorePrefix )
		{
			// Prefix
			if (node1.getPrefix() == null)
			{
				if (node2.getPrefix() != null)
					return false;
			}
			else if (!node1.getPrefix().equals(node2.getPrefix()))
				return false;
			
			// NodeName: Concatenation of prefix and localName.
			//   In theory nodeName can't be null but better be careful
			//   who knows what other implementations may be doing?...
			if (node1.getNodeName() == null)
			{
				if (node2.getNodeName() != null)
					return false;
			}
			else if (!node1.getNodeName().equals(node2.getNodeName()))
				return false;
		}
		
		// NodeValue
		if (node1.getNodeValue() == null)
		{
			if (node2.getNodeValue() != null)
				return false;
		}
		else if (!node1.getNodeValue().equals(node2.getNodeValue()))
			return false;
		
		return true;
	}
	
    /**
     * <p>Override inherited behavior from <code>areEqualNodes</code> to support deep equal.</p>
     * 
     * <p><b>Reference:</b> DOM Level 3 Working Draft - Experimental.</p>
     * 
     * <p><b>Note:</b> This method is derived from <code>com.sun.org.apache.xerces.internal.dom.ParentNode</code>.</p>
     * 
	 * @param node1 The first node to compare equality with.
	 * @param node2 The second node to compare equality with.
	 * @param ignorePrefix When true, ignore the <code>prefix</code> related values.
	 * 
	 * @return If the nodes (and any subtrees) are equal, <code>true</code>; otherwise <code>false</code>.
     */
	public static boolean areEqualParentNodes(Node node1, Node node2, boolean ignorePrefix)
	{
		if (!areEqualNodes(node1, node2, ignorePrefix))
			return false;
		
		// There are many ways to do this test, and there isn't any way
		// better than another. Performance may vary greatly depending on
		// the implementations involved. This one should work fine for us.
		Node child1 = node1.getFirstChild();
		Node child2 = node2.getFirstChild();
		while (child1 != null && child2 != null)
		{
			if (!areEqualNodes(child1, child2, ignorePrefix))
				return false;
			
			child1 = child1.getNextSibling();
			child2 = child2.getNextSibling();
		}
		
		if (child1 != child2)
			return false;
		
		return true;
	}
	
    /**
     * <p>Override inherited behavior from <code>areEqualNodes</code> and <code>areEqualParentNodes</code>
     * to check on attributes.</p>
     * 
     * <p><b>Reference:</b> DOM Level 3 Working Draft - Experimental.</p>
     * 
     * <p><b>Note:</b> This method is derived from <code>com.sun.org.apache.xerces.internal.dom.ElementImpl</code></p>
     * 
	 * @param node1 The first element to compare equality with.
	 * @param node2 The second element to compare equality with.
	 * @param ignorePrefix When true, ignore the <code>prefix</code> related values.
	 * 
	 * @return If the elements (and any subtrees) are equal, <code>true</code>; otherwise <code>false</code>.
     */
	public static boolean areEqualElements(Element node1, Element node2, boolean ignorePrefix)
	{
		if (!areEqualParentNodes(node1, node2, ignorePrefix))
			return false;
		
		boolean hasAttrs = node1.hasAttributes();
		if (hasAttrs != node2.hasAttributes())
			return false;
		
		if (hasAttrs)
		{
			NamedNodeMap map1 = node1.getAttributes();
			NamedNodeMap map2 = node2.getAttributes();
			int len = map1.getLength();
			if (len != map2.getLength())
				return false;
			
			for (int i = 0; i < len; i++)
			{
				Node n1 = map1.item(i);
				if (n1.getLocalName() == null)
				{
					// DOM Level 1 Node
					Node n2 = map2.getNamedItem(n1.getNodeName());
					if (n2 == null || !areEqualNodes(n1, n2, ignorePrefix))
						return false;
				}
				else
				{
					String prefix1 = node1.getPrefix();
					String localName1 = n1.getLocalName();
					if ( !(ignorePrefix && (prefix1 != null) && prefix1.equals(localName1)) )
					{
						Node n2 = map2.getNamedItemNS(n1.getNamespaceURI(), n1.getLocalName());
						if (n2 == null || !areEqualNodes(n1, n2, ignorePrefix))
							return false;
					}
				}
			}
		}
		return true;
	}
}
