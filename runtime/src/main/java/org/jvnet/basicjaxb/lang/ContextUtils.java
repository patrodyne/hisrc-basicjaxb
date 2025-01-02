package org.jvnet.basicjaxb.lang;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static org.jvnet.basicjaxb.lang.StringUtils.capitalize;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

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
	public static final boolean DEFAULT_JAXB_FORMATTED_OUTPUT = false;
	
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
		
		Set<String> packageNameSet = new LinkedHashSet<>();
		
		for (int i = 0; i < classes.length; i++)
		{
			if (classes[i] == null)
				throw new IllegalArgumentException("The validated array contains null element at index: " + i);
			else
				packageNameSet.add(classes[i].getPackageName());
		}
		
		final StringBuilder contextPath = new StringBuilder();
		boolean firstPackageName = true;
		for ( String packageName : packageNameSet )
		{
			if ( firstPackageName )
				firstPackageName = false;
			else
				contextPath.append(':');
			contextPath.append(packageName);
		}
		
		return contextPath.toString();
	}
	
	public static Marshaller createMarshaller(JAXBContext context)
		throws JAXBException
	{
		return createMarshaller(context, DEFAULT_JAXB_FORMATTED_OUTPUT);
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
	public static String marshalToString(JAXBContext context, Object object)
		throws JAXBException
	{
		final Marshaller marshaller = createMarshaller(context);
		return marshalToString(marshaller, object);
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
	public static String marshalToString(Marshaller marshaller, Object object)
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
	public static <T> T unmarshalFromString(JAXBContext context, String xml, Class<?> clazz)
		throws JAXBException
	{
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return unmarshalFromString(unmarshaller, xml, clazz);
	}

	@SuppressWarnings("unchecked")
	public static <T> T unmarshalFromString(Unmarshaller unmarshaller, String xml)
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
	public static <T> T unmarshalFromString(Unmarshaller unmarshaller, String xml, Class<?> clazz)
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


	/**
	 * Enable XML Schema Validation for the given schema URL(s).
	 * URL(s) can be "file:", "classpath:", etc.
	 * 
	 * @param unmarshaller Governs the process of deserializing XML data.
	 * @param marshaller Governs the process of serializing XML data.
	 * @param schemaUrls A list of XML schema URL(s).
	 * 
	 * @throws IOException When an InputStream cannot be used.
	 * @throws SAXException When an XML schema cannot be parsed.
	 * @throws JAXBException When marshaller and unmarshaller are not available.
	 */
	public static void enableSchemaValidation(Unmarshaller unmarshaller, Marshaller marshaller, String ... schemaUrls)
		throws IOException, SAXException, JAXBException
	{
		if ( (unmarshaller != null) && (marshaller != null) )
			ContextUtils.enableXmlSchemaValidator(unmarshaller, marshaller, schemaUrls);
		else
			throw new JAXBException("Please create marshaller and unmarshaller!");	
	}

	/**
	 * Create a JAXBElement to wrap the given value object. Looks for
	 * <code>ObjectFactory</code> in the value object's package.
	 * 
	 * @param <T> The generic value type.
	 * @param value The object to wrap.
	 * 
	 * @return a JAXBElement to wrap the given value object or null.
	 */
	public static <T> JAXBElement<T> createJAXBElement(T value)
		throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, NoSuchMethodException, SecurityException
	{
		JAXBElement<T> jaxbElement = null;
		if ( value != null )
		{
			Class<?> objectFactoryClass = getClass(value.getClass().getPackageName(), "ObjectFactory");
			if ( objectFactoryClass != null )
			{
				Object objectFactory = objectFactoryClass.getDeclaredConstructor().newInstance();
				jaxbElement = createJAXBElement(objectFactory, value);
			}
		}
		return jaxbElement;
	}
	
	/**
	 * Use <code>ObjectFactory</code> to create a JAXBElement to wrap the given value object.
	 * 
	 * @param <T> The generic value type.
	 * @param objectFactory The <code>ObjectFactory</code> for the JAXB context.
	 * @param value The object to wrap.
	 * 
	 * @return a JAXBElement to wrap the given value object or null.
	 */
	@SuppressWarnings("unchecked")
	public static <T> JAXBElement<T> createJAXBElement(Object objectFactory, T value)
	{
		JAXBElement<T> jaxbElement = null;
		if ( value != null )
		{
			for ( Method method : objectFactory.getClass().getDeclaredMethods() )
			{
				if ( method.getName().startsWith("create") )
				{
					if ( method.getParameterCount() == 1 )
					{
						Class<?> type = method.getParameterTypes()[0];
						if ( type.equals(value.getClass()) )
						{
							try
							{
								jaxbElement = (JAXBElement<T>) method.invoke(objectFactory, value);
							}
							catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
							{
								jaxbElement = null;
							}
							break;
						}
					}
				}
			}
		}
		return jaxbElement;
	}
	
	/**
	 * Find am {@code ObjectFactory} method to create a
	 * {@code JAXBElement} for the given type name.
	 * 
	 * @param objectFactoryClass The {@code ObjectFactory} class to reflect on.
	 * @param typeName The name of the {@code JAXBElement}'s type.
	 * 
	 * @return An method to create a {@code JAXBElement} for the given type name.
	 */
	public static Method findJAXBElementMethod(Class<?> objectFactoryClass, String typeName)
	{
		Method jaxbElementMethod = null;
		String methodName = "create" + capitalize(typeName);
		for ( Method method : objectFactoryClass.getDeclaredMethods() )
		{
			if ( method.getName().equals(methodName) )
			{
				if ( method.getParameterCount() == 1 )
				{
					Class<?> returnType = method.getReturnType();
					if ( JAXBElement.class.isAssignableFrom(returnType) )
					{
						jaxbElementMethod = method;
						break;
					}
				}
			}
		}
		return jaxbElementMethod;
	}
	
	/**
	 * Find an {@code ObjectFactory} class for the given JAXB class reference.
	 * 
	 * @param jaxbClass The JAXB class reference.
	 * 
	 * @return An {@code ObjectFactory} class for the given JAXB class reference.
	 */
	public static Class<?> findObjectFactoryClass(Class<?> jaxbClass)
	{
		return getClass(jaxbClass.getPackageName(), "ObjectFactory");
	}
	
	private static Class<?> getClass(String packageName, String typeName)
	{
        try
        {
            return Class.forName(packageName + "." + typeName);
        }
        catch (ClassNotFoundException e)
        {
            return null;
        }
    }
}
