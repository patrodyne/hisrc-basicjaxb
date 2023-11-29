package org.jvnet.basicjaxb.dom;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.annotation.W3CDomHandler;

/**
 * An extension of {@link W3CDomHandler} to trace handler events:
 * create a new {@link DOMResult} holder, access the {@link Element}
 * instance or marshal the instance into a {@link Source} to regenerate
 * the XML sub-tree.
 */
public class W3CTraceDomHandler extends W3CDomHandler
{
	// Represents the logger for this class.
	private static Logger logger = LoggerFactory.getLogger(W3CTraceDomHandler.class);
	public static Logger getLogger() { return logger; }
	
	/**
	 * Create a new {@link DOMResult} instance for the XML Binding provider
	 * to transform a portion of the XML into the instance.
	 */
	@Override
	public DOMResult createUnmarshaller(ValidationEventHandler errorHandler)
	{
		DOMResult domResult = super.createUnmarshaller(errorHandler);
		getLogger().trace("createUnmarshaller: {}", domResult);
		return domResult;
	}
	
	/**
	 * Once a portion of the XML is transformed to the {@link DOMResult} instance,
	 * this method is called by the XML Binding provider to obtain the unmarshalled
	 * {@link Element} representation.
	 */
	@Override
	public Element getElement(DOMResult domResult)
	{
		Element element = super.getElement(domResult);
		getLogger().trace("getElement: {}", element);
		return element;
	}
	
	/**
	 * This method is called when a the XML Binding provider needs to marshal
	 * the given {@link Element} instance to an XML representation. Typically,
	 * the {@link Source} is a {@link DOMSource} that acts as a holder for a
	 * transformation source Document Object Model (DOM) tree.
	 */
	@Override
	public Source marshal(Element element, ValidationEventHandler errorHandler)
	{
		Source source = super.marshal(element, errorHandler);
		getLogger().trace("marshal: {}", source);
		return source;
	}
}
