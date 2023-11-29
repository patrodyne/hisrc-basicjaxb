package com.bdoughan.blog;

import static jakarta.xml.bind.JAXBContext.newInstance;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.annotation.DomHandler;
import jakarta.xml.bind.annotation.W3CDomHandler;

public class BioDomHandler implements DomHandler<Element,DOMResult>
{
	private static final W3CDomHandler W3C = new W3CDomHandler();
	private static final DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
	private static final Class<?> HTML_RESUME_CLASS = com.bdoughan.blog.domhandler.resume.html.Résumé.class;
	private static final Class<?> XML_RESUME_CLASS = com.bdoughan.blog.domhandler.resume.xml.Résumé.class;
	private static JAXBContext HTML_CONTEXT = null;
	private static JAXBContext XML_CONTEXT = null;
	static
	{
		try
		{
			HTML_CONTEXT = newInstance(HTML_RESUME_CLASS);
			XML_CONTEXT = newInstance(XML_RESUME_CLASS);
		}
		catch (JAXBException ex)
		{
			ex.printStackTrace();
		}
	}

	@Override
	public DOMResult createUnmarshaller(ValidationEventHandler errorHandler)
	{
		return W3C.createUnmarshaller(errorHandler);
	}
	
	@Override
	public Element getElement(DOMResult domResult)
	{
		Object obj = null;
		Element element = W3C.getElement(domResult);
		String type = element.getAttribute("type");
		JAXBContext jc = null;
		switch (type)
		{
			case "html": jc = HTML_CONTEXT; break;
			case "xml": jc = XML_CONTEXT; break;
		}
		if ( jc != null )
			obj = unmarshal(jc, element);
		else
			obj = element;
		return element;
	}

	@Override
	public Source marshal(Element element, ValidationEventHandler errorHandler)
	{
		return W3C.marshal(element, errorHandler);
	}
	
	public Source marshalNot(Object obj, ValidationEventHandler errorHandler)
	{
		JAXBContext jc = null;
		if ( obj instanceof com.bdoughan.blog.domhandler.resume.html.Résumé )
			jc = HTML_CONTEXT;
		else if ( obj instanceof com.bdoughan.blog.domhandler.resume.xml.Résumé )
			jc = XML_CONTEXT;
		Document doc = marshal(jc, obj);
		return new DOMSource(doc);
	}

	private Object unmarshal(JAXBContext jc, Element element)
	{
		Object obj = null;
		if ( jc != null )
		{
			try
			{
				Unmarshaller unm = jc.createUnmarshaller();
				obj = unm.unmarshal(element);
			}
			catch (JAXBException e)
			{
				throw new IllegalStateException(element.toString());
			}
		}
		return obj;
	}

	private Document marshal(JAXBContext jc, Object obj)
	{
		Document doc = null;
		if ( jc != null )
		{
			try
			{
				DocumentBuilder builder = W3C.getBuilder();
				if ( builder == null )
					builder = DBF.newDocumentBuilder();
				doc = builder.newDocument();
				Marshaller mar = jc.createMarshaller();
				mar.marshal(obj, doc);
			}
			catch (JAXBException | ParserConfigurationException ex)
			{
				throw new IllegalStateException(obj.toString());
			}
		}
		return doc;
	}
}
