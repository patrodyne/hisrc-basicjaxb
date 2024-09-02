package org.swixml.schema;

import static java.beans.Introspector.decapitalize;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static javax.xml.transform.OutputKeys.INDENT;
import static org.swixml.Factory.ADDER_ID;
import static org.swixml.Factory.SETTER_ID;
import static org.swixml.LogUtil.logger;

import java.awt.Container;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.swixml.Factory;
import org.swixml.SwingEngine;
import org.swixml.TagLibrary;
import org.swixml.factory.BoxFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Abstract Swixml XML Schema Generator
 *
 * @author Wolf Paulus
 */
abstract public class SchemaGeneratorBase
{
	/**
	 * Loops through all tags in Swixml's tag library.
	 *
	 * @param root The <code>Element</code> node tags will be inserted in.
	 */
	protected void addSwixmlTags(Element root)
	{
		Document doc = root.getOwnerDocument();
		TagLibrary taglib = new SwingEngine<Container>().getTaglib();
		TreeSet<String> treeSet = new TreeSet<>(taglib.getTagClasses().keySet());
		for ( String name : treeSet )
		{
			Factory factory = taglib.getTagClasses().get(name);
			Element tag = doc.createElement("xs:element");
			tag.setAttribute("name", name);
			
			addSwixmlAttr(tag, factory);
			appendElements(root, tag);
		}
		
		appendElements(doc, root, taglib, treeSet);
	}
	
	protected void appendElements(Element root, Element tag)
	{
		// See SchemaGeneratorFlat
	}
	
	protected void appendElements(Document doc, Element root, TagLibrary taglib, TreeSet<String> treeSet)
	{
		// See SchemaGeneratorNorm
	}
	
	/**
	 * Generate the Swixml XML Schema.
	 *
	 * @param uri The target name space, e.g. http://www.swixml.org/2007/SwixmlTags
	 * 
	 * @throws ParserConfigurationException When the document cannot be built.
	 */
	protected Document generate(URI uri) throws ParserConfigurationException
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		String tns = uri.toString();
		Element root = doc.createElementNS(W3C_XML_SCHEMA_NS_URI, "xs:schema");
		root.setAttribute("xmlns:xs", W3C_XML_SCHEMA_NS_URI);
		root.setAttribute("xmlns:tns", tns);
		root.setAttribute("targetNamespace", tns);
		root.setAttribute("elementFormDefault", "qualified");
		doc.appendChild(root);
		addSwixmlTags(root);
		return doc;
	}

	/**
	 * Loops through all the available setters in the factory and adds the
	 * attributes.
	 *
	 * @param tag The <code>Element</code> node tags will be inserted in.
	 * @param factory The SWIXML <code>Factory</code>.
	 */
	protected void addSwixmlAttr(Element tag, Factory factory)
	{
		Document doc = tag.getOwnerDocument();
		Element complexType = doc.createElement("xs:complexType");
		complexType.setAttribute("mixed", "true");
		{
			Element sequence = doc.createElement("xs:sequence");
			{
				Element any = doc.createElement("xs:any");
				sequence.appendChild(any);
				sequence.setAttribute("minOccurs", "0");
				sequence.setAttribute("maxOccurs", "unbounded");
				complexType.appendChild(sequence);
			}
			Set<String> attributes = new HashSet<String>();
			addFactoryAttributes(factory, attributes, complexType);
			
		}
		tag.appendChild(complexType);
	}

	protected void addFactoryAttributes(Factory factory, Set<String> attributes, Element elem)
	{
		if ( factory instanceof BoxFactory )
		{
			BoxFactory bf = (BoxFactory) factory;
			BoxFactory.Type boxtype = bf.getType();
			switch (boxtype)
			{
				case HSTRUT:
					addAttribute(attributes, elem, "width", Integer.class);
					break;
				case VSTRUT:
					addAttribute(attributes, elem, "height", Integer.class);
					break;
				case RIGIDAREA:
					addAttribute(attributes, elem, "size", String.class);
					break;
				case VGLUE:
				case HGLUE:
				case GLUE:
					break;
			}
		}
		else
		{
			Map<String, Method> settersByName = new TreeMap<>();
			for ( Method setter : factory.getSetters() )
			{
				if ( ! setter.isAnnotationPresent(Deprecated.class) )
				{
					String mName = setter.getName();
					String aName = null;
					if ( mName.startsWith(SETTER_ID) )
						aName = decapitalize(mName.substring(SETTER_ID.length()));
					else if ( mName.startsWith(ADDER_ID) )
						aName = decapitalize(mName.substring(Factory.ADDER_ID.length()));
					settersByName.put(aName, setter);
				}
			}

			for ( Entry<String, Method> setterEntry : settersByName.entrySet() )
				addAttribute(attributes, elem, setterEntry.getKey(), setterEntry.getValue());
			
			addCustomAttributes(elem);
		}
	}

	protected Element addAttribute(Set<String> attributes, Element elem, String aName,
		Class<?> type)
	{
		Document doc = elem.getOwnerDocument();
		Element attribute = null;
		if ( !attributes.contains(aName) )
		{
			boolean b = boolean.class.equals(type);
			attribute = doc.createElement("xs:attribute");
			attribute.setAttribute("name", aName);
			attribute.setAttribute("type", b ? "xs:boolean" : "xs:string");
			elem.appendChild(attribute);
			attributes.add(aName);
		}
		return attribute;
	}
	
	protected abstract Element addAttribute(Set<String> attributes, Element elem, String key, Method setter);

	protected abstract void addCustomAttributes(Element elem);

	protected void addDocumentation(final Element elem, final String description)
	{
		Document doc = elem.getOwnerDocument();
		{
			Element ann = doc.createElement("xs:annotation");
			{
				Element anndoc = doc.createElement("xs:documentation");
				anndoc.setTextContent(description);
				ann.appendChild(anndoc);
			}
			elem.appendChild(ann);
		}
	}
	
	/**
	 * Print the Swixml XML Schema document to standard output.
	 *
	 * @param doc The <code>Document</code> source.
	 * 
	 * @throws TransformerException When the transformation process fails.
	 */
	protected void print(Document doc)
		throws TransformerException
	{
		// Use a standard output for StreamResult
	    StreamResult result = new StreamResult(System.out);
		print(doc, result);
	}
	
	/**
	 * Print the Swixml XML Schema document to a file.
	 *
	 * @param doc The <code>Document</code> source.
	 * @param file The <code>File</code> target.
	 * 
	 * @throws IOException When schema cannot be printed into the given file.
	 * @throws TransformerException When the transformation process fails.
	 */
	protected void print(Document doc, File file)
		throws IOException, TransformerException
	{
		// Use a File for StreamResult
	    try ( FileWriter writer = new FileWriter(file) )
	    {
		    StreamResult result = new StreamResult(writer);
			print(doc, result);
	    }
	}
	
	protected void print(Document doc, StreamResult result)
		throws TransformerException
	{
		// Use a Transformer for output
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.setOutputProperty(INDENT, "yes");
		
		// Use the document for the DOM source.
		DOMSource source = new DOMSource(doc);
		tf.transform(source, result);
	}

	protected void execute(String[] args)
		throws ParserConfigurationException, URISyntaxException, IOException, TransformerException
	{
		Document doc = generate(new URI("http://www.swixml.org/2007/Swixml"));
		if ( args != null && args.length > 0 )
		{
			File file = new File(args[0]);
			print(doc, file);
		}
		else
		{
			logger.warn("Output file argument missing. Printing to standard out.");
			print(doc);
		}
	}
}
