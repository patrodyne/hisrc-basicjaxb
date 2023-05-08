package org.jvnet.basicjaxb.util;

import java.io.File;
import java.util.Stack;
import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A helper application to generate JAXB bindings for nested complex types. This application
 * searches an XML Schema file for anonymously defined complex types that are nested within
 * parent types. JPA 2.1 requires that member types be elevated to top-level classes. The
 * XJC tool provides a feature to generate top-level classes but uses a simple naming convention
 * for the Java type that may lead to name collisions. This helper generates bindings with complex
 * type names based on the entire parentage to avoid name collisions.
 * 
 * To use this helper, run it on each schema file and copy/paste the output to your bindings file
 * (i.e. bindings.xjb).
 */
public class CreateToplevelXJBindings extends DefaultHandler
{
	// Represents the default complex type suffix.
	private static final String DEFAULT_SUFFIX = "Type";
	// Represents the name of the XML schema file to parse.
	private static String sourceName = null;
	// Represents indicator to output only nested classes.
	private static boolean nested = false;
	// Represents the type old suffix to remove.
	private static String oldSuffix = DEFAULT_SUFFIX;
	// Represents the type new suffix to append.
	private static String newSuffix = DEFAULT_SUFFIX;
	
	// keep track of the succession of elements
	private Stack<Tag> tags;
	
	// Represents an XML Schema element relevant to the xpath.
	private static class Tag
	{
		public String localName;
		public String nameValue;
		
		public Tag(String localName, String nameValue)
		{
			this.localName = localName;
			this.nameValue = nameValue;
		}
		
		public Tag(String localName)
		{
			this(localName, (String) null);
		}
		
		public Tag(String localName, Attributes attributes)
		{
			this(localName, nameValue(attributes));
		}
		
		@Override
		public String toString()
		{
			return "Tag[" + localName + ", " + nameValue + "]";
		}
		
		private boolean isRelevant()
		{
			boolean isRelevant = false;
			switch (this.localName)
			{
				case "element":
				case "complexType":
				case "sequence":
				case "choice":
				case "complexContent":
				case "extension":
					isRelevant = true;
					break;
				default:
					isRelevant = false;
					break;
			}
			return isRelevant;
		}
		
		private boolean isComplexType()
		{
			return "complexType".equals(this.localName);
		}
		
		private static String nameValue(Attributes attributes)
		{
			String nameValue = null;
			for (int i = 0; i < attributes.getLength(); i++)
			{
				String attrName = attributes.getQName(i);
				String attrVal = attributes.getValue(i);
				if ( "name".equals(attrName) )
				{
					nameValue = attrVal;
					break;
				}
			}
			return nameValue;
		}
	}
	
	@Override
	public void startDocument() throws SAXException
	{
		tags = new Stack<Tag>();
		println("<jaxb:bindings schemaLocation=\""+sourceName+"\" node=\"/xs:schema\">");
	}

	@Override
	public void endDocument() throws SAXException
	{
		println("</jaxb:bindings>\n");
		tags = null;
	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes attributes)
		throws SAXException
	{		
		Tag tag = new Tag(localName, attributes);
		if ( tag.isRelevant() )
			tags.push(tag);
	}

	@Override
	public void endElement (String uri, String localName, String qName) throws SAXException
	{
		Tag tag = new Tag(localName);
		if ( tag.isRelevant() )
		{
			if ( tag.isComplexType() )
			{
				if ( !nested || (countComplexTypeTags() > 1) )
				{
					println("\t<jaxb:bindings node=\""+buildCurrentXPath()+"\">");
					println("\t\t<jaxb:class name=\""+buildCurrentJavaType()+"\"/>");
					println("\t</jaxb:bindings>");
				}
			}
			tags.pop();
		}
	}

	private int countComplexTypeTags()
	{
		int count = 0;
		for (Tag tag : tags)
		{
			if ( tag.isComplexType() )
				++count;
		}
		return count;
	}

	/**
	 * Build XPath from current tags.
	 */
	private String buildCurrentXPath()
	{
		StringBuilder xpath = new StringBuilder();
		xpath.append("/");
		for (Tag tag : tags)
		{
			xpath.append("/xs:" + tag.localName);
			if ( tag.nameValue != null )
				xpath = xpath.append("[@name='" + tag.nameValue + "']");
		}
		return xpath.toString();
	}

	/**
	 * Build Java type name from current tags.
	 */
	private String buildCurrentJavaType()
	{
		StringBuilder javaType = new StringBuilder();
		for (Tag tag : tags)
		{
			if ( tag.nameValue != null )
			{
				String[] tokens = tag.nameValue.split("[_-]");
				for ( String token : tokens )
				{
					if ( !token.isEmpty() )
					{
						if ( token.endsWith(oldSuffix) )
						{
							int offset = token.length() - oldSuffix.length();
							if ( offset > 0 )
								token = token.substring(0, offset);
							else
								continue;
						}
						javaType.append(token.substring(0, 1).toUpperCase() + token.substring(1));
					}
				}
			}
		}
		if ( !javaType.toString().endsWith(newSuffix) )
			javaType.append(newSuffix);
		return javaType.toString();
	}
	
	private static void println(Object obj)
	{
		if ( obj != null )
			System.out.println(obj.toString());
	}

	private static void errorln(Object obj)
	{
		if ( obj != null )
			System.err.println(obj.toString());
	}

	private static String parseArg(String arg, String def)
	{
		String rhs = def;
		String[] nvp = arg.split("=");
		if ( nvp.length > 1)
			rhs = nvp[1].trim();
		return rhs;
	}
	
	/**
	 * Main application start.
	 * @param args The command line arguments
	 * @throws Exception When the XML schema cannot be parsed.
	 */
	public static void main (String[] args) throws Exception
	{
		for ( String arg : args )
		{
			if ( !arg.startsWith("--") )
				sourceName = arg;
			else if ( arg.startsWith("--nested") )
				nested = Boolean.parseBoolean(parseArg(arg, "true"));
			else if ( arg.startsWith("--oldSuffix") )
				oldSuffix = parseArg(arg, DEFAULT_SUFFIX);
			else if ( arg.startsWith("--newSuffix") )
				newSuffix = parseArg(arg, DEFAULT_SUFFIX);
		}

		if (sourceName != null)
		{
			// Create a JAXP SAXParserFactory and configure it
			SAXParserFactory spf = SAXParserFactory.newInstance();
			spf.setNamespaceAware(true);
			spf.setValidating(false);

			// Create a JAXP SAXParser
			SAXParser saxParser = spf.newSAXParser();

			// Get the encapsulated SAX XMLReader
			XMLReader xmlReader = saxParser.getXMLReader();

			// Set the ContentHandler of the XMLReader
			xmlReader.setContentHandler(new CreateToplevelXJBindings());

			String path = new File(sourceName).getAbsolutePath();

			// Tell the XMLReader to parse the XML document
			xmlReader.parse("file:"+path);
			
			// Done
			System.exit(0);
		}
		else
		{
			errorln("Usage: CreateToplevelXJBindings [--nested] [--oldSuffix=somename] [--newSuffix=somename] <file.xml>");
			System.exit(1);
		}
	}
}
