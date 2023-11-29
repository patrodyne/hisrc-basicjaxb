package org.example.custom.bio;

import static jakarta.xml.bind.JAXBContext.newInstance;
import static org.jvnet.basicjaxb.xml.bind.model.util.XmlTypeUtils.getTypeName;
import static org.jvnet.basicjaxb.xml.bind.model.util.XmlTypeUtils.isJAXBElement;
import static org.jvnet.basicjaxb.xml.bind.model.util.XmlTypeUtils.isXmlRootElement;

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
	public static final Class<org.example.custom.bio.customer.Customer> CUSTOMER_CLASS = org.example.custom.bio.customer.Customer.class;
	public static final Class<org.example.custom.bio.résumé.html.Résumé> HTML_RESUME_CLASS = org.example.custom.bio.résumé.html.Résumé.class;
	public static final Class<org.example.custom.bio.résumé.xml.Résumé> XML_RESUME_CLASS = org.example.custom.bio.résumé.xml.Résumé.class;

	public static final QName HTML_RESUME_QNAME = getTypeName(HTML_RESUME_CLASS);
	public static final QName XML_RESUME_QNAME = getTypeName(XML_RESUME_CLASS);
	public static JAXBContext MAIN_CONTEXT = null;
	static
	{
		try
		{
			MAIN_CONTEXT = newInstance
			(
				CUSTOMER_CLASS,
				HTML_RESUME_CLASS,
				XML_RESUME_CLASS
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
				if ( HTML_RESUME_CLASS.equals(value.getClass()) )
					wrap = new JAXBElement(HTML_RESUME_QNAME, HTML_RESUME_CLASS, value);
				else if ( XML_RESUME_CLASS.equals(value.getClass()) )
					wrap = new JAXBElement(XML_RESUME_QNAME, XML_RESUME_CLASS, value);
			}
		}
		return wrap;
	}

	public static Object mainUnmarshalByType(Element element)
	{
		Object value = null;
		String type = element.getAttribute("type");
		switch (type)
		{
			case "html":
				value = mainUnmarshal(element, HTML_RESUME_CLASS);
				break;
			case "xml":
				value = mainUnmarshal(element, XML_RESUME_CLASS);
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
