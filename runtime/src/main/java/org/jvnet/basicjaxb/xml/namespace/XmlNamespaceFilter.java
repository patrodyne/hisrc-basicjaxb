package org.jvnet.basicjaxb.xml.namespace;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * This class sits between an {@link XMLReader} and this application's event handlers.
 * It pass requests up to the reader and events on to the handlers modified to
 * either add a namespace (on unmarshalling) or remove a namespace (on marshalling).
 */
public class XmlNamespaceFilter extends XMLFilterImpl
{
	private String namespaceURI = "";
	public String getNamespaceURI()
	{
		return namespaceURI;
	}
	public void setNamespaceURI(String namespaceURI)
	{
		this.namespaceURI = namespaceURI;
	}
	
	private boolean addNamespace = false;
	public boolean isAddNamespace()
	{
		return addNamespace;
	}
	public void setAddNamespace(boolean addNamespace)
	{
		this.addNamespace = addNamespace;
	}

	private boolean namespaceAdded = false;
	public boolean isNamespaceAdded()
	{
		return namespaceAdded;
	}
	public void setNamespaceAdded(boolean namespaceAdded)
	{
		this.namespaceAdded = namespaceAdded;
	}
	
	public XmlNamespaceFilter(String namespaceURI, boolean addNamespace)
	{
		super();
		setAddNamespace(addNamespace);
		
		if ( isAddNamespace() )
			setNamespaceURI(namespaceURI);
	}

	@Override
	public void startDocument()
		throws SAXException
	{
		super.startDocument();
		if ( isAddNamespace() )
			startControlledPrefixMapping();
	}

	@Override
	public void startElement(String arg0, String localName, String qName, Attributes atts)
		throws SAXException
	{
		super.startElement(getNamespaceURI(), localName, qName, atts);
	}

	@Override
	public void endElement(String arg0, String localName, String qName)
		throws SAXException
	{
		super.endElement(getNamespaceURI(), localName, qName);
	}

	@Override
	public void startPrefixMapping(String prefix, String url)
		throws SAXException
	{
		if ( isAddNamespace() )
			this.startControlledPrefixMapping();
	}

	private void startControlledPrefixMapping() throws SAXException
	{
		if ( isAddNamespace() && !isNamespaceAdded() )
		{
			// We should add namespace since it is set and has not yet been done.
			super.startPrefixMapping("", getNamespaceURI());

			// Make sure we dont do it twice
			setNamespaceAdded(true);
		}
	}
}
