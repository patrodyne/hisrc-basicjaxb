package org.jvnet.basicjaxb.tests.swixml;

import static org.jvnet.basicjaxb.dom.DOMUtils.formatXml;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.sax.SAXSource;

import org.jvnet.basicjaxb.testing.AbstractSamplesTest;
import org.jvnet.basicjaxb.xml.namespace.XmlNamespaceFilter;
import org.swixml.schema.model.Component;
import org.swixml.schema.model.Dialog;
import org.swixml.schema.model.Frame;
import org.swixml.schema.model.ObjectFactory;
import org.swixml.schema.model.Window;
import org.xml.sax.XMLFilter;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;

public abstract class AbstractSwixmlTest extends AbstractSamplesTest
{
	public AbstractSwixmlTest()
	{
		super();
	}

	@Override
	protected String getContextPath()
	{
		return ObjectFactory.class.getPackageName();
	}

	@Override
	protected void checkSample(File sample)
		throws Exception
	{
		// getSampleMap().put(sample.getName(), sample);
	}

	@SuppressWarnings("unchecked")
	protected <T> T unmarshalSample(File sample, Class<T> clazz)
		throws Exception
	{
		// Unmarshal with a filter to add a namespace.
		XMLFilter readFilter = new XmlNamespaceFilter("http://www.swixml.org/2007/swixml", true);
		SAXSource saxSource = createSaxSource(sample, readFilter);
		Object sampleObject = getUnmarshaller().unmarshal(saxSource);
		getLogger().debug("Sample Object: {}", sampleObject);
		
		List<Object> contentList = null;
		if ( sampleObject instanceof Dialog )
		{
			Dialog dialog = (Dialog) sampleObject;
			contentList = dialog.getContent();
		}
		else if ( sampleObject instanceof Frame )
		{
			Frame frame = (Frame) sampleObject;
			contentList = frame.getContent();
		}
		logContents("\t", contentList);
		
		return (T) sampleObject;
	}

	protected void marshalSample(Window window, String name)
		throws JAXBException
	{
		// Marshal using the filter that will remove the xmlns attribute.
		XmlNamespaceFilter writeFilter = new XmlNamespaceFilter(null, false);
		StringWriter sw = createStringWriter(writeFilter);
		getMarshaller().marshal(window, writeFilter);
		getLogger().info("XML: {}\n{}", name, formatXml(sw.toString(), 4));
	}

	protected void logContents(String tabs, List<Object> contentList)
	{
		if ( getLogger().isDebugEnabled() )
		{
			for ( Object content : contentList )
			{
				Object value = unwrap(content);
				getLogger().debug("{}{}", tabs, value);
				if ( value instanceof Component )
				{
					Component component = (Component) value;
					component.getContent();
					logContents(tabs+"\t", component.getContent());
				}
			}
		}
	}
	
	protected Object unwrap(Object content)
	{
		Object value = content;
		if ( content instanceof JAXBElement )
			value = ((JAXBElement<?>) content).getValue();
		return value;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T unwrap(Object content, Class<T> type)
	{
		Object value = content;
		if ( content instanceof JAXBElement )
			value = ((JAXBElement<T>) content).getValue();
		return type.isInstance(value) ? (T) value : (T) null;
	}

	protected List<Object> removeStrings(List<Object> contents)
	{
		List<Object> components = new ArrayList<>();
		for ( Object content : contents)
		{
			if ( ! (content instanceof String) )
				components.add(content);
		}
		return components;
	}
}
