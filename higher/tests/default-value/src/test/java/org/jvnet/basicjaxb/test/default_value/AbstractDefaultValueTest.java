package org.jvnet.basicjaxb.test.default_value;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import org.jvnet.basicjaxb.testing.AbstractSamplesTest;
import org.jvnet.basicjaxb.xml.namespace.XmlNamespaceStrippingStreamWriter;

import jakarta.xml.bind.JAXBException;

public abstract class AbstractDefaultValueTest extends AbstractSamplesTest
{
	protected static final boolean DEFAULT_STRIP_NAMESPACE = true;
	
	@Override
	protected String marshalToString(Object document)
		throws JAXBException
	{
		return marshalToString(document, DEFAULT_STRIP_NAMESPACE);
	}

	protected String marshalToString(Object document, boolean stripNamespace)
		throws JAXBException
	{
		try
		{
			String documentXml;
			try ( StringWriter sw = new StringWriter() )
			{
				XMLStreamWriter xw = XMLOutputFactory.newFactory().createXMLStreamWriter(sw);
				if ( stripNamespace )
					getMarshaller().marshal(document, new XmlNamespaceStrippingStreamWriter(xw));
				else
					getMarshaller().marshal(document, xw);
				documentXml = sw.toString();
			}
			return documentXml;
		}
		catch ( Exception ex )
		{
			throw new JAXBException("marshalToString: "+document, ex);
		}
	}
}
