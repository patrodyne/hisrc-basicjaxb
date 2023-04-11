package org.jvnet.basicjaxb.lang;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.jvnet.basicjaxb.config.LocatorInputFactory;
import org.patrodyne.jvnet.basicjaxb.validation.SchemaOutputDomResolver;
import org.patrodyne.jvnet.basicjaxb.validation.SchemaOutputStringResolver;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * Utility class for JAXB Context helpers.
 */
public class ContextUtils
{
	public static final SchemaFactory XML_SCHEMA_FACTORY = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
	
	// Seal this utility class.
	private ContextUtils() { }
	
	/**
	 * Build a JAXB context path of colon (":") delimited package names.
	 * 
	 * @param classes One or more classes to include on the path.
	 * 
	 * @return A colon (":") delimited path of package names.
	 */
	public static String getContextPath(Class<?>... classes)
	{
		if (classes == null)
			throw new IllegalArgumentException("The validated object is null");
		
		for (int i = 0; i < classes.length; i++)
		{
			if (classes[i] == null)
				throw new IllegalArgumentException("The validated array contains null element at index: " + i);
		}
		
		final StringBuilder contextPath = new StringBuilder();
		for (int index = 0; index < classes.length; index++)
		{
			if (index > 0)
				contextPath.append(':');
			contextPath.append(classes[index].getPackageName());
		}
		
		return contextPath.toString();
	}
	
	/**
	 * Create a {@link Marshaller} from the given JAXB context.
	 * 
	 * @param context The JAXB context.
	 * @param formatted True when the marshalled XML data is to be formatted with linefeeds and indentation.
	 * 
	 * @return A JAXB {@link Marshaller}
	 * 
	 * @throws JAXBException When the {@link Marshaller} cannot be created.
	 */
	public static Marshaller createMarshaller(JAXBContext context, boolean formatted)
		throws JAXBException
	{
		if ( context != null )
		{
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, formatted);
			return marshaller;
		}
		else
			throw new JAXBException("Cannot create Marshaller because JAXBContext is null.");
	}

	/**
	 * Create a {@link Unmarshaller} from the given JAXB context.
	 * 
	 * @param context The JAXB context.
	 * 
	 * @return A JAXB {@link Unmarshaller}
	 * 
	 * @throws JAXBException When the {@link Unmarshaller} cannot be created.
	 */
	public static Unmarshaller createUnmarshaller(JAXBContext context)
		throws JAXBException
	{
		if ( context != null )
		{
			return context.createUnmarshaller();
		}
		else
			throw new JAXBException("Cannot create Unmarshaller because JAXBContext is null.");
	}
	
	/**
	 * Marshal the given object to a formatted XML representation.
	 * 
	 * @param context The JAXB context.
	 * @param object The object to be marshaled.
	 * 
	 * @return An XML representation of the given object.
	 * 
	 * @throws JAXBException When the object cannot be marshalled.
	 */
	public static String toString(JAXBContext context, Object object)
		throws JAXBException
	{
		final Marshaller marshaller = createMarshaller(context, true);
		return toString(marshaller, object);
	}
	
	/**
	 * Marshal the given object to a XML representation.
	 * 
	 * @param marshaller The JAXB marshaller.
	 * @param object The object to be marshaled.
	 * 
	 * @return An XML representation of the given object.
	 * 
	 * @throws JAXBException When the object cannot be marshalled.
	 */
	public static String toString(Marshaller marshaller, Object object)
		throws JAXBException
	{
		String xml = null;
		if ( object != null )
		{
			try ( final StringWriter sw = new StringWriter() )
			{
				marshaller.marshal(object, sw);
				xml = sw.toString();
			}
			catch (IOException ex)
			{
				// Closing a StringWriter has no effect.
			}
		}
		return xml;
	}
	
	/**
	 * Unmarshal an XML representaion to an object of the given class type.
	 * 
	 * @param <T> The object's class type.
	 * @param context The JAXB context.
	 * @param xml The XML representation.
	 * @param clazz The unmarshalled object class type.
	 * 
	 * @return An unmarshalled object of the given type.
	 * 
	 * @throws JAXBException When the object cannot be unmarshalled.
	 */
	public static <T> T fromString(JAXBContext context, String xml, Class<?> clazz)
		throws JAXBException
	{
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return fromString(unmarshaller, xml, clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T fromString(Unmarshaller unmarshaller, String xml)
		throws JAXBException
	{
		T instance = null;
		if ( xml != null )
		{
			try ( StringReader reader = new StringReader(xml) )
			{
				StreamSource streamSource = new StreamSource(reader);
				Object obj = unmarshaller.unmarshal(streamSource);
				if ( obj instanceof JAXBElement )
				{
					JAXBElement<T> jaxbElement = (JAXBElement<T>) obj;
					instance = jaxbElement.getValue();
				}
				else
					instance = (T) obj;
			}
		}
		return instance;
	}
	
	/**
	 * Unmarshal an XML representaion to an object of the given class type.
	 * 
	 * @param <T> The object's class type.
	 * @param unmarshaller The JAXB unmarshaller.
	 * @param xml The XML representation.
	 * @param clazz The unmarshalled object class type.
	 * 
	 * @return An unmarshalled object of the given type.
	 * 
	 * @throws JAXBException When the object cannot be unmarshalled.
	 */
	public static <T> T fromString(Unmarshaller unmarshaller, String xml, Class<?> clazz)
		throws JAXBException
	{
		T instance = null;
		if ( xml != null )
		{
			try ( StringReader reader = new StringReader(xml) )
			{
				StreamSource streamSource = new StreamSource(reader);
				@SuppressWarnings("unchecked")
				JAXBElement<T> jaxbElement = (JAXBElement<T>) unmarshaller.unmarshal(streamSource, clazz);
				instance = jaxbElement.getValue();
			}
		}
		return instance;
	}
	
	/**
	 * Enable XML Schema Validation for the given schema URL(s).
	 * URL(s) can be "file:", "classpath:", etc.
	 * 
	 * @param unmarshaller An optional unmarshaller.
	 * @param marshaller An optional marshaller.
	 * @param schemaUrls A list of XML schema URL(s).
	 * 
	 * @return Immutable in-memory representation of grammar.
	 * 
	 * @throws IOException When an InputStream cannot be used.
	 * @throws SAXException When an XML schema cannot be parsed.
	 */
	public static Schema enableXmlSchemaValidator(Unmarshaller unmarshaller, Marshaller marshaller, String ... schemaUrls)
		throws IOException, SAXException
	{
		// Create streams for sources from URL(s).
		StreamSource[] sources = new StreamSource[schemaUrls.length];
		for ( int index=0; index < schemaUrls.length; ++index )
		{
			String schemaUrl = schemaUrls[index];
			InputStream is = LocatorInputFactory.createInputStream(schemaUrl);
			sources[index] = new StreamSource(is, schemaUrl);
		}
		
		// Create the validation schema.
		Schema schema = XML_SCHEMA_FACTORY.newSchema(sources);
		
		// Close source streams.
		for ( StreamSource source : sources )
			source.getInputStream().close();
		
		// Optionally, set validation schema on unmarshaller.
		if ( unmarshaller != null )
			unmarshaller.setSchema(schema);
		
		// Optionally, set validation schema on marshaller.
		if ( marshaller != null )
			marshaller.setSchema(schema);
		
		return schema;
	}
	
	/**
	 * Create a {@link SchemaOutputStringResolver} from a {@link JAXBContext}
	 * 
	 * @param jaxbContext The JAXB context.
	 * 
	 * @return A {@link SchemaOutputStringResolver} instance.
	 * 
	 * @throws IOException When JAXB context cannot generate (output) a schema.
	 */
	public static SchemaOutputStringResolver createSchemaOutputStringResolver(JAXBContext jaxbContext)
		throws IOException
	{
		SchemaOutputStringResolver sosr = new SchemaOutputStringResolver();
		jaxbContext.generateSchema(sosr);
		return sosr;
	}
	
	/**
	 * Create a {@link SchemaOutputDomResolver} from a {@link JAXBContext}
	 * 
	 * @param jaxbContext The JAXB context.
	 * 
	 * @return A {@link SchemaOutputDomResolver} instance.
	 * 
	 * @throws IOException When JAXB context cannot generate (output) a schema.
	 */
	public static SchemaOutputDomResolver createSchemaOutputDomResolver(JAXBContext jaxbContext)
		throws IOException
	{
		SchemaOutputDomResolver sodr = new SchemaOutputDomResolver();
		jaxbContext.generateSchema(sodr);
		return sodr;
	}
	
	/**
	 * Enable XML Schema Validation for the given JAXBContext.
	 * 
	 * @param unmarshaller An optional unmarshaller.
	 * @param marshaller An optional marshaller.
	 * @param jaxbContext A JAXB context with one or more packages.
	 * 
	 * @return Immutable in-memory representation of grammar.
	 * 
	 * @throws IOException When an SchemaOutputDomResolver cannot be used.
	 * @throws SAXException When an XML schema cannot be parsed.
	 */
	public static Schema enableXmlSchemaValidator(Unmarshaller unmarshaller, Marshaller marshaller, JAXBContext jaxbContext)
		throws IOException, SAXException
	{
		// Generate a Schema Validator from given the JAXB context.
		SchemaOutputDomResolver sodr = createSchemaOutputDomResolver(jaxbContext);
		
		// Create the validation schema.
		Schema schema = XML_SCHEMA_FACTORY.newSchema(sodr.getDomSource());
		
		// Optionally, set validation schema on unmarshaller.
		if ( unmarshaller != null )
			unmarshaller.setSchema(schema);
		
		// Optionally, set validation schema on marshaller.
		if ( marshaller != null )
			marshaller.setSchema(schema);
		
		return schema;
	}
}
