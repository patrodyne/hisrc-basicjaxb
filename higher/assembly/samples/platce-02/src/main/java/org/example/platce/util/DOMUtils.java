package org.example.platce.util;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A utility class to provide DOM helper methods.
 * 
 * TODO: Replace with org.jvnet.basicjaxb.dom.DOMUtils.
 */
public class DOMUtils
{
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
		return writer.toString();
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
	
	private static boolean isBlank(String value)
	{
		return (value != null) ? value.isBlank() : true;
	}
}
