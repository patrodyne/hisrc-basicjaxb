package org.example.platce;

import static jakarta.xml.bind.JAXBContext.newInstance;
import static org.example.platce.util.XmlTypeUtils.getNamespace;
import static org.example.platce.util.XmlTypeUtils.isJAXBElement;
import static org.example.platce.util.XmlTypeUtils.isXmlRootElement;
import static org.jvnet.basicjaxb.xml.bind.model.util.XmlTypeUtils.getTypeName;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class Context
{
	public static final Class<org.example.platce.Response> RESPONSE_CLASS = org.example.platce.Response.class;
	public static final Class<org.example.platce.MethodXX1> METHODXX1_CLASS = org.example.platce.MethodXX1.class;
	public static final Class<org.example.platce.MethodXX2> METHODXX2_CLASS = org.example.platce.MethodXX2.class;

	public static final String RESPONSE_NS = getNamespace(RESPONSE_CLASS.getPackage());
	
	public static final QName METHOD_QNAME = new QName(RESPONSE_NS, "method");
	public static final QName METHODXX1_QNAME = getTypeName(METHODXX1_CLASS);
	public static final QName METHODXX2_QNAME = getTypeName(METHODXX2_CLASS);
	
	public static JAXBContext MAIN_CONTEXT = null;
	static
	{
		try
		{
			MAIN_CONTEXT = newInstance
			(
				RESPONSE_CLASS,
				METHODXX1_CLASS,
				METHODXX2_CLASS
			);
		}
		catch (JAXBException ex)
		{
			ex.printStackTrace();
		}
	}
	private static final DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();

	public Context()
	{
	}

	public static Unmarshaller createMainUnmarshaller()
	{
		return createUnmarshaller(MAIN_CONTEXT);
	}
	
	public static Marshaller createMainMarshaller()
	{
		return createMarshaller(MAIN_CONTEXT);
	}
	
	public static Object mainUnmarshal(Element element, Class<?> clazz)
	{
		return unmarshal(MAIN_CONTEXT, element, clazz);
	}

	public static Document mainMarshal(Object obj)
	{
		return marshal(MAIN_CONTEXT, obj);
	}
	
	public static Element mainMarshalWrap(Object value)
	{
		Document doc = mainMarshal(mainWrap(value));
		return doc.getDocumentElement();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object mainWrap(Object value)
	{
		Object wrap = null;
		if ( value != null )
		{
			if ( isXmlRootElement(value) || isJAXBElement(value) )
				wrap = value;
			else
			{
				if ( METHODXX1_CLASS.equals(value.getClass()) )
					wrap = new JAXBElement(METHODXX1_QNAME, METHODXX1_CLASS, value);
				else if ( METHODXX2_CLASS.equals(value.getClass()) )
					wrap = new JAXBElement(METHODXX2_QNAME, METHODXX2_CLASS, value);
			}
		}
		return wrap;
	}

	public static Element mainMarshalWrapAsMethod(Object value)
	{
		Document doc = mainMarshal(mainWrapAsMethod(value));
		return doc.getDocumentElement();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object mainWrapAsMethod(Object value)
	{
		Object wrap = null;
		if ( value != null )
		{
			if ( isJAXBElement(value) )
				value = ((JAXBElement) value).getValue();
			
			if ( METHODXX1_CLASS.equals(value.getClass()) )
				wrap = new JAXBElement(METHOD_QNAME, METHODXX1_CLASS, value);
			else if ( METHODXX2_CLASS.equals(value.getClass()) )
				wrap = new JAXBElement(METHOD_QNAME, METHODXX2_CLASS, value);
		}
		return wrap;
	}
	
	public static Object mainUnmarshalByName(Element element)
	{
		Object value = null;
		String name = element.getAttribute("name");
		switch (name)
		{
			case "platce-info":
				value = mainUnmarshal(element, METHODXX1_CLASS);
				break;
			case "platce-data":
				value = mainUnmarshal(element, METHODXX2_CLASS);
				break;
			default:
				value = element;
				break;
		}
		return value;
	}

	@SuppressWarnings("rawtypes")
	private static Object unmarshal(JAXBContext jc, Element element, Class<?> clazz)
	{
		Object obj = null;
		if ( jc != null )
		{
			try
			{
				Unmarshaller unm = createUnmarshaller(jc);
				obj = unm.unmarshal(element, clazz);
				if ( obj instanceof JAXBElement )
					obj = ((JAXBElement) obj).getValue();
			}
			catch (JAXBException ex)
			{
				throw new IllegalAccessError(createMessage(ex, obj));
			}
		}
		return obj;
	}

	private static Document marshal(JAXBContext jc, Object obj)
	{
		Document doc = null;
		if ( jc != null )
		{
			try
			{
				DocumentBuilder builder = DBF.newDocumentBuilder();
				doc = builder.newDocument();
				Marshaller mar = createMarshaller(jc);
				mar.marshal(obj, doc);
			}
			catch (JAXBException | ParserConfigurationException ex)
			{
				throw new IllegalAccessError(createMessage(ex, obj));
			}
		}
		return doc;
	}

	private static Unmarshaller createUnmarshaller(JAXBContext jc)
	{
		try
		{
			return jc.createUnmarshaller();
		}
		catch (JAXBException ex)
		{
			throw new IllegalAccessError(createMessage(ex));
		}		
	}

	private static Marshaller createMarshaller(JAXBContext jc)
	{
		try
		{
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			return marshaller;
		}
		catch (JAXBException ex)
		{
			throw new IllegalAccessError(createMessage(ex));
		}
	}
	
	private static String createMessage(Exception ex)
	{
		Throwable th = (ex.getCause() != null) ? ex.getCause() : ex;
		String msg = th.getClass().getSimpleName() + ": " + th.getMessage();
		return msg;
	}

	private static String createMessage(Exception ex, Object obj)
	{
		return createMessage(ex) +  "(" + obj + ")";
	}
}
