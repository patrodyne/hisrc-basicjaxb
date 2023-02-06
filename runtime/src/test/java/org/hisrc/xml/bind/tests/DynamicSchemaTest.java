package org.hisrc.xml.bind.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.MarshalException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.SchemaOutputResolver;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import javax.xml.XMLConstants;
import javax.xml.transform.Result;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.hisrc.xml.bind.tests.dynamicelementname.DynamicElementNameTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class DynamicSchemaTest
{
	private Logger logger = LoggerFactory.getLogger(DynamicElementNameTest.class);
	public Logger getLogger() { return logger; }
	
	@XmlRootElement
	public static class A
	{
		@XmlAttribute(required = true)
		public String name;

		public A()
		{
		}

		public A(String name)
		{
			this.name = name;
		}
	}

	@Test
	public void generatesAndUsesSchema()
		throws JAXBException, IOException, SAXException
	{
		MarshalException exception = assertThrows(MarshalException.class, () -> {
			final JAXBContext context = JAXBContext.newInstance(A.class);
			final DOMResult result = new DOMResult();
			result.setSystemId("schema.xsd");
			context.generateSchema(new SchemaOutputResolver()
			{
				@Override
				public Result createOutput(String namespaceUri, String suggestedFileName)
				{
					return result;
				}
			});
			final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			final Schema schema = schemaFactory.newSchema(new DOMSource(result.getNode()));
			final Marshaller marshaller = context.createMarshaller();
			marshaller.setSchema(schema);
			
			// TODO: assertion
			// Works
			Writer sw = new StringWriter();
			marshaller.marshal(new A("works"), sw);
			getLogger().debug("A works:\n" + sw);
			
			// Fails
			sw = new StringWriter();
			marshaller.marshal(new A(null), sw);
			getLogger().debug("A null:\n" + sw);
		});
		assertEquals("cvc-complex-type.4: Attribute 'name' must appear on element 'a'.",
			exception.getLinkedException().getMessage());
	}
}
