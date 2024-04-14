package org.jvnet.basicjaxb.test.default_value;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.jvnet.basicjaxb.testing.AbstractSamplesTest;
import org.jvnet.basicjaxb.xml.namespace.NamespaceStrippingXMLStreamWriter;

import jakarta.xml.bind.JAXBException;

public abstract class AbstractDefaultValueTest extends AbstractSamplesTest
{
	protected static final boolean DEFAULT_STRIP_NAMESPACE = true;
	
	protected String marshalToString(Object document)
		throws Exception
	{
		return marshalToString(document, DEFAULT_STRIP_NAMESPACE);
	}

	protected String marshalToString(Object document, boolean stripNamespace)
		throws XMLStreamException, FactoryConfigurationError, JAXBException, IOException
	{
		String documentXml;
		try ( StringWriter sw = new StringWriter() )
		{
			XMLStreamWriter xw = XMLOutputFactory.newFactory().createXMLStreamWriter(sw);
			if ( stripNamespace )
				getMarshaller().marshal(document, new NamespaceStrippingXMLStreamWriter(xw));
			else
				getMarshaller().marshal(document, xw);
			documentXml = sw.toString();
		}
		return documentXml;
	}
}
