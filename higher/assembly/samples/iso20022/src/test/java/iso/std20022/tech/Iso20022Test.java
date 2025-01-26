package iso.std20022.tech;

import static jakarta.xml.bind.JAXBIntrospector.getValue;
import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static javax.xml.datatype.DatatypeFactory.newInstance;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;
import org.patrodyne.jvnet.basicjaxb.validation.SchemaOutputDomResolver;
import org.xml.sax.SAXException;

import iso.std20022.tech.pain_013_001_10.ActiveCurrencyAndAmount;
import iso.std20022.tech.pain_013_001_10.CreditorPaymentActivationRequest;
import iso.std20022.tech.pain_013_001_10.Document;
import iso.std20022.tech.pain_013_001_10.ObjectFactory;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

/**
 * A JUnit test to check a sample XML file.
 */
public class Iso20022Test extends AbstractSamplesTest
{
	/**
	 * PAIN ObjectFactory (partial)
	 */
	protected static final ObjectFactory OF = new ObjectFactory();
	
	private static XMLGregorianCalendar XGC_NOW;
	static
	{
		try
		{
			GregorianCalendar gcNow = new GregorianCalendar();
			XGC_NOW = newInstance().newXMLGregorianCalendar(gcNow);
		}
		catch (DatatypeConfigurationException ex)
		{
			ex.printStackTrace();
		}
	}
	
	/**
	 * Configure the JAXB context path in AbstractSamplesTest
	 * to include all classes from two packages.
	 */
	@Override
	protected String getContextPath()
	{
		return "iso.std20022.tech.pain_013_001_10";
	}
	
	@BeforeEach
	public void beforeEach() throws JAXBException
	{
		setJaxbContext(createContext());
		setUnmarshaller(getJaxbContext().createUnmarshaller());
		setMarshaller(getJaxbContext().createMarshaller());
		getMarshaller().setProperty(JAXB_FORMATTED_OUTPUT, true);
		// Enable XML Schema Validation
		generateXmlSchemaValidatorFromDom();
	}
	
	@Test
	public void testMarshalSample() throws Exception
	{
		ActiveCurrencyAndAmount acaa = OF.createActiveCurrencyAndAmount();
		acaa.setCcy("USD");
		acaa.setValue(new BigDecimal("63400000000.49"));
		
		CreditorPaymentActivationRequest cpar = OF.createCreditorPaymentActivationRequest();
		cpar.setCreationDate(XGC_NOW);
		cpar.setDebtor("Enron");
		cpar.setInstructedAmount(acaa);
		cpar.setMessageIdentification(UUID.randomUUID().toString());
		
		Document doc = OF.createDocument();
		doc.setCreditorPaymentActivationRequest(cpar);
		
		JAXBElement<Document> jeDoc = OF.createDocument(doc);
		
		String xmlDoc = marshal(getMarshaller(), jeDoc);
		getLogger().debug("Document:\n\n{}", xmlDoc);
	}
	
	/**
	 * Override the test method in AbstractSamplesTest to read
	 * this project's sample files and assert expectations.
	 */
	@Override
	protected void checkSample(File sample) throws Exception
	{
		setFailFast(true);
		
		Object root = getUnmarshaller().unmarshal(sample);
		String xmlRoot = marshal(getMarshaller(), root);
		getLogger().debug("Root:\n\n" + xmlRoot);
		
		root = getValue(root);

		if ( root instanceof Document )
		{
			Document doc = (Document) root;
			
			assertNotNull(doc.getCreditorPaymentActivationRequest(), "Creditor Payment Activation Request expected.");
			CreditorPaymentActivationRequest cpar = doc.getCreditorPaymentActivationRequest();
			
			assertNotNull(cpar.getInstructedAmount(), "Instructed Amount expected.");
			ActiveCurrencyAndAmount acaa = cpar.getInstructedAmount();
			
			assertNotNull(acaa.getValue(), "Instructed Amount value expected.");
			assertTrue(acaa.getValue().compareTo(BigDecimal.ZERO) > 0, "Positive instructed amount value expected" );
		}
		else
			fail("Object is not instance of Document: " + root);
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

	private void generateXmlSchemaValidatorFromDom() throws JAXBException
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
