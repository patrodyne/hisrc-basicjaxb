package org.jvnet.basicjaxb.util;

import static java.lang.Character.isJavaIdentifierPart;
import static java.lang.Character.isJavaIdentifierStart;
import static java.lang.String.format;

import java.io.File;
import java.util.Stack;

import javax.xml.namespace.QName;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A helper application to generate JAXB bindings for type safe enumeration members. This
 * application searches an XML Schema file for simple types that are restricted by enumerations.
 * In the event where the default settings for the JAXB code generator cannot create valid Java
 * identifiers for all of the members of an enumeration. This application can customize how the
 * XJC code generator handles this by providing <code>typesafeEnumMember</code> bindings. 
 * 
 * To use this helper, run it on each schema file and copy/paste the output to your bindings file
 * (i.e. bindings.xjb).
 */
public class CreateTypesafeEnumMember extends DefaultHandler
{
	private static final String XML_SCHEMA_SIMPLE_TYPE = "{http://www.w3.org/2001/XMLSchema}simpleType";
	private static final String XML_SCHEMA_ENUMERATION = "{http://www.w3.org/2001/XMLSchema}enumeration";
	// Represents the default namespace providing a safe identifier.
	private static final String DEFAULT_SAFE_NS = "urn:un:unece:uncefact:documentation:standard:CoreComponentsTechnicalSpecification:2";
	// Represents the default element providing a safe identifier.
	private static final String DEFAULT_SAFE_ELEM = "Name";
	// Represents the default attribute providing a safe identifier.
	private static final String DEFAULT_SAFE_ATTR = null;
	// Represents the default member providing a safe identifier.
	private static final String DEFAULT_SAFE_ENUM_MBR = "{"+DEFAULT_SAFE_NS+"}"+DEFAULT_SAFE_ELEM;
	// Represents the name of the XML schema file to parse.
	private static String sourceName = null;
	// Represents the namespace providing a safe identifier.
	private static String safeNS = null;
	// Represents the element providing a safe identifier.
	private static String safeElem = null;
	// Represents the attribute providing a safe identifier.
	private static String safeAttr = null;
	
	// keep track of the succession of elements
	private Stack<Tag> tags;
	
	protected Tag getStartTag()
	{
		Tag tag = null;
		if ( !tags.isEmpty() )
			tag = tags.peek();
		return tag;
	}
	
	protected StringBuilder getContent()
	{
		StringBuilder content = null;
		if ( !tags.isEmpty() )
			content = tags.peek().content;
		return content;
	}
	protected void setContent(StringBuilder content)
	{
		if ( !tags.isEmpty() )
			tags.peek().content = content;
	}
	
	protected boolean isTypeSafeEnumMember()
	{
		boolean result = false;
		if ( !tags.isEmpty() )
			result = tags.peek().isTypeSafeEnumMember();
		return result;
	}

	// Represents an XML Schema element relevant to the xpath.
	private static class Tag
	{
		private QName qname;
		private String nameValue;
		private StringBuilder content = new StringBuilder();
		
		public Tag(String ns, String localName, String nameValue)
		{
			this.nameValue = nameValue;
			this.qname = new QName(ns, localName);
		}
		
		public Tag(String ns, String localName, Attributes attributes)
		{
			this(ns, localName, nameValue(attributes));
		}
		
		public Tag(String ns, String localName)
		{
			this(ns, localName, (String) null);
		}

		@Override
		public String toString()
		{
			return "Tag[" + qname + ", " + nameValue + ", " + content + "]";
		}

		private boolean isSimpleType()
		{
			return XML_SCHEMA_SIMPLE_TYPE.equals(this.qname.toString());
		}

		private boolean isEnumeration()
		{
			return XML_SCHEMA_ENUMERATION.equals(this.qname.toString());
		}

		public boolean isTypeSafeEnumMember()
		{
			return DEFAULT_SAFE_ENUM_MBR.equals(this.qname.toString());
		}
		
		private static String nameValue(Attributes attributes)
		{
			String nameValue = null;
			for (int i = 0; i < attributes.getLength(); i++)
			{
				String attrName = attributes.getQName(i);
				String attrVal = attributes.getValue(i);
				if ( "name".equals(attrName) )
					nameValue = attrVal;
				else if ( "value".equals(attrName) )
					nameValue = attrVal;
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
		Tag tag = new Tag(namespaceURI, localName, attributes);
		if ( tag.isSimpleType() || !tags.isEmpty() )
			tags.push(tag);
	}
	
    @Override
    public void characters (char ch[], int start, int length)
        throws SAXException
    {
    	if ( isTypeSafeEnumMember() )
    	{
        	String text = new String(ch, start, length).trim();
        	if ( text != null )
        		getContent().append(text);
    	}
    }

	@Override
	public void endElement (String uri, String localName, String qName) throws SAXException
	{
		Tag et = new Tag(uri, localName);
		if ( !tags.isEmpty() )
		{
			if ( et.isSimpleType() )
			{
				String enumValue = null;
				for ( Tag tag : tags )
				{
					if ( tag.isSimpleType() )
					{
						println(format("\t<jaxb:bindings node=\"xs:simpleType[@name='%s']\">", tag.nameValue));
						println(format("\t\t<jaxb:typesafeEnumClass name=\"%s\">", tag.nameValue));
					}
					else if ( tag.isEnumeration() )
						enumValue = tag.nameValue;
					else if ( tag.isTypeSafeEnumMember() )
					{
						String enumName = createEnumIdentifier(tag.content.toString());
						println(format("\t\t\t<jaxb:typesafeEnumMember value=\"%s\" name=\"%s\"/>", enumValue, enumName));
					}
				}
				println("\t\t</jaxb:typesafeEnumClass>");
				println("\t</jaxb:bindings>");
				tags.clear();
			}
		}
	}
	
	private String createEnumIdentifier(String text)
	{
		String enumId = null;
		if ( (text != null) && !text.isBlank() )
		{
			char c1 = ' ', c2 = ' ';
			StringBuilder sb = new StringBuilder();
			char[] chars = text.toCharArray();
			if ( !isJavaIdentifierStart(chars[0]) )
				sb.append(c1 = '_');
			for ( int index = 0; index < chars.length; ++index )
			{
				c2 = chars[index];
				if ( isJavaIdentifierPart(c2) )
					sb.append(c1 = c2);
				else if ( (c1 != '_') && (index < chars.length-1) )
					sb.append(c1 = '_');
			}
			enumId = sb.toString().toUpperCase();
		}
		return enumId;
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
			else if ( arg.startsWith("--safeNS") )
				safeNS = parseArg(arg, DEFAULT_SAFE_NS);
			else if ( arg.startsWith("--safeElem") )
				safeElem = parseArg(arg, DEFAULT_SAFE_ELEM);
			else if ( arg.startsWith("--safeAttr") )
				safeAttr = parseArg(arg, DEFAULT_SAFE_ATTR);
			if ( safeNS == null )
				safeNS = DEFAULT_SAFE_NS;
			if ( safeElem == null )
				safeElem = DEFAULT_SAFE_ELEM;
			if ( safeAttr == null )
				safeAttr = DEFAULT_SAFE_ATTR;
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
			xmlReader.setContentHandler(new CreateTypesafeEnumMember());

			String path = new File(sourceName).getAbsolutePath();

			// Tell the XMLReader to parse the XML document
			xmlReader.parse("file:"+path);
			
			// Done
			System.exit(0);
		}
		else
		{
			errorln("Usage: CreateTypesafeEnumMember [--safeNS=someNS] [--safeElem=someElem] [--safeAttr=someAttr] <file.xml>");
			System.exit(1);
		}
	}
}
