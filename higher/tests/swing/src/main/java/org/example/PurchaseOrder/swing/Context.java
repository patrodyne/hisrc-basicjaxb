package org.example.PurchaseOrder.swing;

import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.example.PurchaseOrder.model.ObjectFactory;
import org.jvnet.basicjaxb.config.LocatorProperties;
import org.patrodyne.jvnet.basicjaxb.validation.SchemaOutputDomResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * JAXB context for {@link org.example.PurchaseOrder.model.ObjectFactory}
 */
 public class Context
{
	private static Logger logger = LoggerFactory.getLogger(Context.class);
	public static Logger getLogger() { return logger; }
	
	// Configuration
	static
	{
		try
		{
			// Load JVM system properties from the classpath.
			LocatorProperties systemProperties = new LocatorProperties();
			systemProperties.load("classpath:/jvmsystem.properties");
			System.getProperties().putAll(systemProperties);
		}
		catch (IOException ex)
		{
			getLogger().error("", ex);
		}
	}
	
	// JAXB Context
	
	private JAXBContext jaxbContext;
	public JAXBContext getJaxbContext() throws JAXBException
	{
		if ( jaxbContext == null )
			setJaxbContext(JAXBContext.newInstance(ObjectFactory.class));
		return jaxbContext;
	}
	public void setJaxbContext(JAXBContext jaxbContext)
	{
		this.jaxbContext = jaxbContext;
	}

	private Unmarshaller unmarshaller = null;
	protected Unmarshaller getUnmarshaller() throws JAXBException
	{
		if ( unmarshaller == null )
			setUnmarshaller(getJaxbContext().createUnmarshaller());
		return unmarshaller;
	}
	protected void setUnmarshaller(Unmarshaller unmarshaller)
	{
		this.unmarshaller = unmarshaller;
	}

	private Marshaller marshaller = null;
	public Marshaller getMarshaller() throws JAXBException
	{
		if ( marshaller == null )
		{
			setMarshaller(getJaxbContext().createMarshaller());
			getMarshaller().setProperty(JAXB_FORMATTED_OUTPUT, true);
		}
		return marshaller;
	}
	public void setMarshaller(Marshaller marshaller)
	{
		this.marshaller = marshaller;
	}

	@SuppressWarnings("unchecked")
	protected <T> T unmarshal(String xmlFileName, Class<T> clazz) throws JAXBException
	{
		File xmlFile = new File(xmlFileName);
		return (T) getUnmarshaller().unmarshal(xmlFile);
	}
	
    protected String marshalToString(Object instance) throws JAXBException, IOException
    {
        String xml = null;
        if ( instance != null)
        {
            try ( StringWriter writer = new StringWriter() )
            {
                getMarshaller().marshal(instance, writer);
                xml = writer.toString();
            }
        }
        return xml;
    }

	protected void generateXmlSchemaValidatorFromDom() throws JAXBException, IOException, SAXException
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
		}
		else
			getLogger().error("Please create marshaller and unmarshaller!");
    }
}
// vi:set tabstop=4 hardtabs=4 shiftwidth=4: