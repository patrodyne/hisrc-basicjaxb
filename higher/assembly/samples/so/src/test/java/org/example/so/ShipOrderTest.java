package org.example.so;

import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.junit.jupiter.api.BeforeEach;
import org.jvnet.basicjaxb.lang.ContextUtils;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;
import org.patrodyne.jvnet.basicjaxb.validation.SchemaOutputDomResolver;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

/**
 * A JUnit test to check a sample XML file.
 */
public class ShipOrderTest extends AbstractSamplesTest
{
	/**
	 * Configure the JAXB context path in AbstractSamplesTest
	 * to include all classes from two packages.
	 */
	@Override
	protected String getContextPath()
	{
		return ContextUtils.getContextPath
		(
			org.example.so.ObjectFactory.class
		);
	}

	@BeforeEach
	public void beforeEach() throws JAXBException
	{
		setJaxbContext(createContext());
		setUnmarshaller(getJaxbContext().createUnmarshaller());
		setMarshaller(getJaxbContext().createMarshaller());
		getMarshaller().setProperty(JAXB_FORMATTED_OUTPUT, true);

		// Enable XML Schema Validation
		//
		// cvc-elt.1.a: Cannot find the declaration of element 'ShipOrder'.
		// May need ???
		//   DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
	    //   fact.setNamespaceAware(true);
		//
		// generateXmlSchemaValidatorFromDom();
	}

	/**
	 * Override the test method in AbstractSamplesTest to read
	 * this project's sample files and assert expectations.
	 */
	@Override
	protected void checkSample(File sample)	throws Exception
	{
		Object root = getUnmarshaller().unmarshal(sample);

		if ( root instanceof ShipOrder )
		{
			ShipOrder so = (ShipOrder) root;
			String soXml = marshal(getMarshaller(), so);
			getLogger().debug("ShipOrder:\n\n" + soXml);
			assertNotNull(so.getItem(), "Items expected.");
			assertFalse(so.getItem().isEmpty(), "Items not empty expected.");
			for ( Item item : so.getItem() )
			{
				assertNotNull(item.getTitle(), "Item must have a title");
				getLogger().trace("Item: " + item);
			}
		}
		else
			fail("Object is not instance of ShipOrder: " + root);
	}

	// Marshall a JAXB object into a String for logging, etc.
	private String marshal(Marshaller marshaller, Object jaxbObject)
		throws JAXBException, IOException
	{
		String xml;
		try ( StringWriter sw = new StringWriter() )
		{
			marshaller.marshal(jaxbObject, sw);
			xml = sw.toString();
		}
		return xml;
	}

	@SuppressWarnings("unused")
	private void generateXmlSchemaValidatorFromDom()
		throws JAXBException
	{
		try
		{
			if ( (getMarshaller() != null) && (getUnmarshaller() != null) )
			{
				// Generate a Schema Validator from given the JAXB context.
				SchemaOutputDomResolver sodr = new SchemaOutputDomResolver();
				getJaxbContext().generateSchema(sodr);
				SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
				Schema schemaValidator = schemaFactory.newSchema(sodr.getDomSource());

				// Configure Marshaller / unmarshaller to use validator.
				getMarshaller().setSchema(schemaValidator);
				getUnmarshaller().setSchema(schemaValidator);

				println("Schema Validation of XML is ON.");
			}
			else
				errorln("Please create marshaller and unmarshaller!");
		}
		catch ( IOException | SAXException ex )
		{
			errorln("generateXmlSchemaValidatorFromDom", ex);
		}
	}

	private void errorln(String msg)
	{
		getLogger().error(msg);
	}

	private void errorln(String msg, Exception ex)
	{
		getLogger().error(msg, ex);

	}

	private void println(String msg)
	{
		getLogger().info(msg);
	}
}
