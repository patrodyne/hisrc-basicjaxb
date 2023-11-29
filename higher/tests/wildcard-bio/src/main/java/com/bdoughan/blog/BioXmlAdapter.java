package com.bdoughan.blog;

import static jakarta.xml.bind.JAXBContext.newInstance;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class BioXmlAdapter extends XmlAdapter<Element, Object>
{
	// Represents the logger for this class.
	private static Logger logger = LoggerFactory.getLogger(BioXmlAdapter.class);
	public static Logger getLogger() { return logger; }

	public static final Class<?> HTML_RESUME_CLASS = com.bdoughan.blog.domhandler.resume.html.Résumé.class;
	public static final Class<?> XML_RESUME_CLASS = com.bdoughan.blog.domhandler.resume.xml.Résumé.class;

	private static final DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
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
	public Object unmarshal(Element element) throws Exception
	{
		getLogger().trace("unmarshal: {}", element);
		Object value = null;
		String type = element.getAttribute("type");
		JAXBContext jc = null;
		switch (type)
		{
			case "html": jc = HTML_CONTEXT; break;
			case "xml": jc = XML_CONTEXT; break;
		}
		if ( jc != null )
			value = unmarshal(jc, element);
		else
			value = element;
		return value;
	}

	@Override
	public Element marshal(Object value) throws Exception
	{
		getLogger().trace("marshal: {}", value);
		Element element = null;
		if ( value != null )
		{
			JAXBContext jc = null;
			if ( HTML_RESUME_CLASS.equals(value.getClass()) )
				jc = HTML_CONTEXT;
			else if ( XML_RESUME_CLASS.equals(value.getClass()) )
				jc = XML_CONTEXT;
			Document doc = marshal(jc, value);
			element = doc.getDocumentElement();
		}
		return element;
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
				throw new IllegalAccessError(element.toString());
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
				DocumentBuilder builder = DBF.newDocumentBuilder();
				doc = builder.newDocument();
				Marshaller mar = jc.createMarshaller();
				mar.marshal(obj, doc);
			}
			catch (JAXBException | ParserConfigurationException ex)
			{
				throw new IllegalAccessError(obj.toString());
			}
		}
		return doc;
	}
}
