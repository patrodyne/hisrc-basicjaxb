package org.jvnet.basicjaxb.xml.namespace;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.sun.xml.txw2.output.IndentingXMLStreamWriter;

public class NamespaceStrippingXMLStreamWriter extends IndentingXMLStreamWriter
{
	public NamespaceStrippingXMLStreamWriter(XMLStreamWriter xmlWriter)
		throws XMLStreamException
	{
		super(xmlWriter);
	}

	@Override
	public void writeNamespace(String prefix, String uri)
		throws XMLStreamException
	{
		// intentionally doing nothing
	}

	@Override
	public void writeDefaultNamespace(String uri)
		throws XMLStreamException
	{
		// intentionally doing nothing
	}

	@Override
	public void writeStartElement(String prefix, String local, String uri)
		throws XMLStreamException
	{
		super.writeStartElement("", local, "");
	}

	@Override
	public void writeStartElement(String uri, String local)
		throws XMLStreamException
	{
		super.writeStartElement(null, local);
	}

	@Override
	public void writeEmptyElement(String uri, String local)
		throws XMLStreamException
	{
		super.writeEmptyElement(null, local);
	}

	@Override
	public void writeEmptyElement(String prefix, String local, String uri)
		throws XMLStreamException
	{
		super.writeEmptyElement(null, local, null);
	}

	@Override
	public void writeAttribute(String prefix, String uri, String local, String value)
		throws XMLStreamException
	{
		super.writeAttribute(null, null, local, value);
	}

	@Override
	public void writeAttribute(String uri, String local, String value)
		throws XMLStreamException
	{
		super.writeAttribute(null, local, value);
	}
}
