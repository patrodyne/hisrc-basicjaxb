package org.jvnet.basicjaxb.dom;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI;
import static javax.xml.XMLConstants.XMLNS_ATTRIBUTE_NS_URI;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Stack;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jvnet.basicjaxb.locator.util.LocatorBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import jakarta.xml.bind.JAXBElement;

/**
 * A utility class to provide DOM helper methods.
 */
public class DOMUtils
{
	private static final String LOCATOR_COLUMN_NUMBER = "ColumnNumber";
	private static final String LOCATOR_LINE_NUMBER = "LineNumber";
	private static final String LOCATOR_SYSTEM_ID = "SystemId";
	private static final String LOCATOR_PUBLIC_ID = "PublicId";
	
	private static Logger logger = LoggerFactory.getLogger(DOMUtils.class);
	public static Logger getLogger() { return logger; }

	// Represent DocumentBuilderFactory and DocumentBuilder instances that ARE namespace aware.
	private static final DocumentBuilderFactory DOM_DOCUMENT_BUILDER_FACTORY_NS = DocumentBuilderFactory.newInstance();
	private static DocumentBuilder DOM_DOCUMENT_BUILDER_NS = null;
	static
	{
		DOM_DOCUMENT_BUILDER_FACTORY_NS.setNamespaceAware(true);
		try
		{
			DOM_DOCUMENT_BUILDER_NS = DOM_DOCUMENT_BUILDER_FACTORY_NS.newDocumentBuilder();
		}
		catch (ParserConfigurationException pce)
		{
			getLogger().error("Cannot create DOM Document Builder (NS)", pce);
		}
	}
	
	// Represents a SAXParserFactory instance that IS namespace aware.
	private static final SAXParserFactory SAX_PARSER_FACTORY_NS = SAXParserFactory.newInstance();
	static
	{
		SAX_PARSER_FACTORY_NS.setNamespaceAware(true);
	}

	// Constructor: Seal class for static use only
	private DOMUtils() { }
	
	/**
	 * Convert a QName instance into a W3C DOM Element instance.
	 * 
	 * @param qname The instance to be converted.
	 * 
	 * @return A W3C DOM Element representaion.
	 */
	public static Element toElement(QName qname, String value)
	{
		Document domDocument = DOM_DOCUMENT_BUILDER_NS.newDocument();
		Element theElement = null;
		theElement = domDocument.createElementNS(qname.getNamespaceURI(), qname.getLocalPart());
		theElement.setPrefix("tns"); // qname.getPrefix() ???
		theElement.setAttributeNS(XMLNS_ATTRIBUTE_NS_URI, "xmlns:"+theElement.getPrefix(), qname.getNamespaceURI());
		theElement.setAttributeNS(XMLNS_ATTRIBUTE_NS_URI, "xmlns:xsi", W3C_XML_SCHEMA_INSTANCE_NS_URI);
		Text valueNode = domDocument.createTextNode(value);
		theElement.appendChild(valueNode);
		// Element extends Node.
		return theElement;
	}
	
	/**
	 * Create a W3C DOM Element instance.
	 * 
	 * @param namespaceURI The node's namespace URI.
	 * @param localPart The node's local part (name).
	 * @param prefix The namespace prefix for this node.
	 * @param value The elements's value.
	 * 
	 * @return A W3C DOM Node representaion.
	 */
	public static Element toElement(String namespaceURI, String localPart, String prefix, String value)
	{
		return toElement(new QName(namespaceURI, localPart, prefix), value);
	}
	
	/**
	 * Convert a QName instance into a W3C DOM Node instance.
	 * 
	 * @param qname The instance to be converted.
	 * 
	 * @return A W3C DOM Node representaion.
	 */
	public static Node toNode(QName qname, String value)
	{
		return toElement(qname, value);
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
	 *	 <li>The two nodes are of the same type.</li>
	 *	 <li>The following string attributes are equal (they are both <code>null</code>,
	 *	 or they have the same length and are character for character identical):
	 *	 <ul>
	 *	   <li><code>namespaceURI</code></li>
	 *	   <li><code>localName</code></li>
	 *	   <li>Optional:
	 *		 <ul>
	 *		   <li><code>prefix</code>: The prefix xmlns is used only to declare namespace bindings.</li>
	 *		   <li><code>nodeName</code>: Concatenation of <code>prefix</code>, <code>":"</code> and <code>localName</code>.</li>
	 *		 </ul>
	 *	   </li>
	 *	   <li><code>nodeValue</code></li>
	 *	   <li><code>baseURI</code></li>
	 *	 </ul></li>
	 *	 <li>The <code>attributes</code> <code>NamedNodeMaps</code> are equal (they are both
	 *	 <code>null</code>, or they have the same map size and for each node that exists in one map
	 *	 there is a node that exists in the other map and is equal, although not necessarily at
	 *	 the same index).</li>
	 *	 <li>The <code>childNodes</code> <code>NodeLists</code> are equal (they are both <code>null</code>,
	 *	 or they have the same length and contain equal nodes at the same index).</li>
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
	 *	 <ul>
	 *	   <li><code>publicId</code></li>
	 *	   <li><code>systemId</code> </li>
	 *	   <li><code>internalSubset</code></li>
	 *	 </ul>
	 * </li>
	 * <li>The <code>entities</code> <code>NamedNodeMaps</code> are equal.</li>
	 * <li>The <code>notations</code> <code>NamedNodeMaps</code> are equal.</li>
	 * </ul>
	 * 
	 * <p>On the other hand, the following do not affect equality:</p>
	 * 
	 * <ul>
	 *	 <li>The <code>ownerDocument</code> attribute.</li>
	 *	 <li>The <code>specified</code> attribute for <code>Attr</code> nodes.</li>
	 *	 <li>The <code>isWhitespaceInElementContent</code> attribute for <code>Text</code> nodes.</li>
	 *	 <li>As well as any user data or event listeners registered on the nodes.</li>
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
			//	 In theory nodeName can't be null but better be careful
			//	 who knows what other implementations may be doing?...
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
	
	/**
	 * Use SAX to parse an XML document with line numbers.
	 * 
	 * @param is An input stream for the source XML file.
	 * @param locatorPrefix Custom prefix for publicId, systemId, lineNumber and columnNumber.
	 * @param systemId The systemId which is needed for resolving relative URIs.
	 * 
	 * @return A W3C Document instance enhanced with line numbers per element.
	 * 
	 * @throws IOException When SAX cannot parse the input stream.
	 * @throws SAXException When {@link SAXParserFactory} cannot create a new SAX parser.
	 * 
	 * @see <a href="https://stackoverflow.com/questions/2798376/">Parse XML via SAX/DOM with line numbers</a>
	 */
	public static Document saxParseDocument(InputStream is, String systemId, final String locatorPrefix)
		throws IOException, SAXException
	{
		try
		{
			// Build a DOM document from the SAX events.
			final Document doc = DOM_DOCUMENT_BUILDER_NS.newDocument();
			
			// A SAX2 event handler to build the document and capture the location.
			final Stack<Element> elementStack = new Stack<Element>();
			final StringBuilder textBuffer = new StringBuilder();
			DefaultHandler handler = new DefaultHandler()
			{
				// Save the documentLocator, so that it can be
				// used later for line tracking when traversing nodes.
				private Locator documentLocator;
				public Locator getDocumentLocator() { return documentLocator; }
				/**
				 * Receive a Locator object for document events.
				 *
				 * @param locator A locator for all SAX document events.
				 * 
				 * @see org.xml.sax.ContentHandler#setDocumentLocator
				 * @see org.xml.sax.Locator
				 */
				@Override
				public void setDocumentLocator(Locator locator)
				{
					this.documentLocator = locator;
				}

				/**
				 * Receive notification of the start of a SAX element then create a DOM
				 * element with child text and locator elements, when needed. Push the
				 * DOM element onto this handler's stack.
				 *
				 * @param uri The Namespace URI, or the empty string if the
				 *		  element has no Namespace URI or if Namespace
				 *		  processing is not being performed.
				 * @param localName The local name (without prefix), or the
				 *		  empty string if Namespace processing is not being
				 *		  performed.
				 * @param qName The qualified name (with prefix), or the
				 *		  empty string if qualified names are not available.
				 * @param attributes The attributes attached to the element.  If
				 *		  there are no attributes, it shall be an empty
				 *		  Attributes object.
				 *		  
				 * @throws SAXException Any SAX exception, possibly wrapping another exception.
				 * 
				 * @see org.xml.sax.ContentHandler#startElement
				 */
				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
					throws SAXException
				{
					appendChildTextIfNeeded();
					Element el = doc.createElementNS(uri, qName);
					
					for (int i = 0; i < attributes.getLength(); i++)
						el.setAttribute(attributes.getQName(i), attributes.getValue(i));
					
					// Conditionally, add the locator's publicId as an attribute on the element.
					if ( !isBlank(getDocumentLocator().getPublicId()) )
						el.setAttribute(locatorPrefix + LOCATOR_PUBLIC_ID, getDocumentLocator().getPublicId());
					
					// Conditionally, add the locator's systemId as an attribute on the element.
					if ( !isBlank(getDocumentLocator().getSystemId()) )
						el.setAttribute(locatorPrefix + LOCATOR_SYSTEM_ID, getDocumentLocator().getSystemId());
					
					// Conditionally, add the locator's line number as an attribute on the element.
					if ( isPositive(getDocumentLocator().getLineNumber()) )
					{
						String lineNumber = String.valueOf(getDocumentLocator().getLineNumber());
						el.setAttribute(locatorPrefix + LOCATOR_LINE_NUMBER, lineNumber);
					}
					
					// Conditionally, add the locator's column number as an attribute on the element.
					if ( isPositive(getDocumentLocator().getColumnNumber()) )
					{
						String columnNumber = String.valueOf(getDocumentLocator().getColumnNumber());
						el.setAttribute(locatorPrefix + LOCATOR_COLUMN_NUMBER, columnNumber);
					}
					
					// Push the DOM element onto this handler's stack.
					elementStack.push(el);
				}

				/**
				 * Receive notification of the end of an element and close
				 * the element stack and append any child text and/or child
				 * elements.
				 * 
				 * @param uri The Namespace URI, or the empty string if the
				 *		  element has no Namespace URI or if Namespace
				 *		  processing is not being performed.
				 * @param localName The local name (without prefix), or the
				 *		  empty string if Namespace processing is not being
				 *		  performed.
				 * @param qName The qualified name (with prefix), or the
				 *		  empty string if qualified names are not available.
				 *		  
				 * @throws SAXException Any SAX exception, possibly wrapping
				 *		   another exception.
				 *		   
				 * @see org.xml.sax.ContentHandler#endElement
				 */
				@Override
				public void endElement(String uri, String localName, String qName)
				{
					appendChildTextIfNeeded();
					Element closedEl = elementStack.pop();
					
					// Is this the root element?
					if (elementStack.isEmpty())
						doc.appendChild(closedEl);
					else
					{
						Element parentEl = elementStack.peek();
						parentEl.appendChild(closedEl);
					}
				}

				/**
				 * Receive notification of character data inside an element and append
				 * characters to text buffer.
				 * 
				 * @param ch The characters to append.
				 * @param start The start position in the character array.
				 * @param length The number of characters to use from the character array.
				 *				 
				 * @throws SAXException Any SAX exception, possibly wrapping another exception.
				 * 
				 * @see org.xml.sax.ContentHandler#characters
				 */
				@Override
				public void characters(char ch[], int start, int length)
					throws SAXException
				{
					textBuffer.append(ch, start, length);
				}

				// Outputs text accumulated under the current node
				private void appendChildTextIfNeeded()
				{
					if (textBuffer.length() > 0)
					{
						Element el = elementStack.peek();
						Node textNode = doc.createTextNode(textBuffer.toString());
						el.appendChild(textNode);
						textBuffer.delete(0, textBuffer.length());
					}
				}
				
				// Return true when string in null or blank; otherwise, false.
				private boolean isBlank(String string)
				{
					return string == null || string.isBlank();
				}

				// Return true when value greater than zero; otherwise, false.
				private boolean isPositive(int value)
				{
					return value > 0;
				}
			};
			
			// Parse the content of the given InputStream
			// instance as XML using the specified DefaultHandler.
			final SAXParser parser = SAX_PARSER_FACTORY_NS.newSAXParser();
			parser.parse(is, handler, systemId);
			
			return doc;
		}
		catch (ParserConfigurationException ex)
		{
			throw new SAXException("Can't create SAX parser / DOM builder.", ex);
		}
	}
	
	/**
	 * Get a {@link LocatorBean} from a {@link Element}.
	 * 
	 * @param element A DOM element with optional locator attributes.
	 * @param locatorPrefix Custom prefix for publicId, systemId, lineNumber and columnNumber.
	 * 
	 * @return A {@link Locator} with optional values or null.
	 */
	public static LocatorBean getLocator(Element element, String locatorPrefix)
	{
		LocatorBean locatorBean = null;
		if ( element != null )
		{
			String publicIdName = locatorPrefix + LOCATOR_PUBLIC_ID;
			String systemIdName = locatorPrefix + LOCATOR_SYSTEM_ID;
			String lineNumberName = locatorPrefix + LOCATOR_LINE_NUMBER;
			String columnNumberName = locatorPrefix + LOCATOR_COLUMN_NUMBER;
			
			String publicId = element.getAttribute(publicIdName);
			String systemId = element.getAttribute(systemIdName);
			String lineNumberAttr = element.getAttribute(lineNumberName);
			String columnNumberAttr = element.getAttribute(columnNumberName);
			
			int lineNumber = lineNumberAttr.isEmpty() ? 0 : Integer.valueOf(lineNumberAttr);
			int columnNumber = columnNumberAttr.isEmpty() ? 0 : Integer.valueOf(columnNumberAttr);
			
			// Construct the locator bean.
			locatorBean = new LocatorBean(publicId, systemId, lineNumber, columnNumber);
		}
		return locatorBean;
	}

	public static String formatXml(String xml, int indent)
	{
		try
		{
			Source xmlInput = new StreamSource(new StringReader(xml));
			StringWriter stringWriter = new StringWriter();
			StreamResult xmlOutput = new StreamResult(stringWriter);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", indent);
			transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
			transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(xmlInput, xmlOutput);
			return fixXmlEol(xmlOutput.getWriter().toString());
		}
		catch (Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}
	
	/**
	 * Transform a {@link Node} instance into a formatted XML string.
	 * 
	 * @param node The {@link Node} instance to be transformed.
	 * 
	 * @return A {@link Node} instance into a formatted XML string.
	 * 
	 * @throws TransformerException When the {@link Node} instance cannot be transformed.
	 */
	public static String transformToString(Node node) throws TransformerException
	{
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(node), new StreamResult(writer));
		return fixXmlEol(writer.toString());
	}
	
	private static String fixXmlEol(String xml)
	{
		xml = xml.replaceFirst("><", ">\n<");
		xml = xml.replaceAll("(?m)^[ \t]*\r?\n", "");
		return xml;
	}

	/**
	 * Build a {@link Node} instance into a string representation.
	 * 
	 * @param tab The string for indentation.
	 * @param node The {@link Node} instance to build into a string.
	 * 
	 * @return A {@link Node} instance into a formatted XML string.
	 */
	public static String buildToString(String tab, Node node)
	{
		if (node.getNodeType() == Node.DOCUMENT_NODE)
		{
			try
			{
				return transformToString(node);
			}
			catch (TransformerException ex)
			{
				throw new RuntimeException("cannot build to string", ex);
			}
		}
		else
		{
			StringBuilder sb = new StringBuilder();
			if (node.getNodeType() == Node.TEXT_NODE)
			{
				if ( !isBlank(node.getNodeValue()) )
					sb.append(tab + node.getNodeValue() + "\n");
			}
			else if (node.getNodeType() == Node.ELEMENT_NODE)
			{
				StringBuilder ab = new StringBuilder();
				NamedNodeMap atts = node.getAttributes();
				for ( int i = 0; i < atts.getLength(); ++i )
				{
					Node att = atts.item(i);
					ab.append(" " + att.getNodeName() + "=\"" + att.getNodeValue()+"\"");
				}
				sb.append(tab + "<" + node.getNodeName() + ab + ">\n");
				NodeList kids = node.getChildNodes();
				for (int i = 0; i < kids.getLength(); ++i)
					sb.append(buildToString(tab + tab, kids.item(i)));
				sb.append(tab + "</" + node.getNodeName() + ">\n");
			}
			else
				sb.append(tab + node.getNodeType() + " UNKNOWN NODE TYPE\n");
			return sb.toString();
		}
	}
	
	/**
	 * Build a {@link Node} instance into a string representation.
	 * 
	 * @param tab The string for indentation.
	 * @param node The {@link Node} instance to build into a string.
	 * 
	 * @return A {@link Node} instance into a formatted XML string.
	 */

	
	/**
	 * Log a {@link Node} instance into a string representation.
	 * 
	 * @param logger A SLF4J logger.
	 * @param label A distinguishing text lable.
	 * @param node The {@link Node} instance to be logged.
	 */
	public static void logNode(Logger logger, String label, Node node)
	{
		if ( logger.isTraceEnabled() )
		{
			logger.trace("{} BaseURI: {}", label, node.getBaseURI());
			logger.trace("{} NamespaceURI: {}", label, node.getNamespaceURI());
			logger.trace("{} LocalName: {}", label, node.getLocalName());
			logger.trace("{} Prefix: {}", label, node.getPrefix());
			logger.trace("{} NodeName: {}", label, node.getNodeName());
			logger.trace("{} NodeType: {}", label, node.getNodeType());
			logger.trace("{} NodeValue: {}", label, node.getNodeValue());
			if ( node.getAttributes() != null )
			{
				logger.trace("{} Attributes: {}", label, node.getAttributes().getLength());
				for ( int index = 0; index < node.getAttributes().getLength(); ++index)
					logNode(logger, label + "[" + index+"]", node.getAttributes().item(index));
			}
		}
		logger.debug("{} TextContent: {}", label, node.getTextContent());
	}
	
	private static boolean isBlank(String value)
	{
		return (value != null) ? value.isBlank() : true;
	}
}
