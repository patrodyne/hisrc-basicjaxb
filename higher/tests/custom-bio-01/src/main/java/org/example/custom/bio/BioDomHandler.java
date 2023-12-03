package org.example.custom.bio;

import static org.example.custom.bio.Context.mainMarshalWrap;
import static org.example.custom.bio.Context.mainUnmarshalByType;
import static org.example.custom.bio.Context.mainWrap;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import jakarta.xml.bind.ValidationEventHandler;
import jakarta.xml.bind.annotation.DomHandler;
import jakarta.xml.bind.annotation.W3CDomHandler;

/**
 * An implementation of {@link DomHandler} to trace handler events:
 * create a new {@link DOMResult} holder, access the {@link Element}
 * instance or marshal the instance into a {@link Source} to regenerate
 * the XML sub-tree.
 */
public class BioDomHandler implements DomHandler<Object,DOMResult>
{
	private static final W3CDomHandler W3C = new W3CDomHandler();
	
	// Represents the logger for this class.
	private static Logger logger = LoggerFactory.getLogger(BioDomHandler.class);
	public static Logger getLogger() { return logger; }
	
	/**
	 * Create a new {@link DOMResult} instance for the XML Binding provider
	 * to transform a portion of the XML into the instance.
	 */
	@Override
	public DOMResult createUnmarshaller(ValidationEventHandler errorHandler)
	{
		getLogger().trace("createUnmarshaller: {}", errorHandler);
		DOMResult domResult = W3C.createUnmarshaller(errorHandler);
		return domResult;
	}
	
	/**
	 * Once a portion of the XML is transformed to the {@link DOMResult} instance,
	 * this method is called by the XML Binding provider to obtain the unmarshalled
	 * {@link Element} representation.
	 */
	@Override
	public Object getElement(DOMResult domResult)
	{
		getLogger().trace("unmarshal: {}", domResult);
		Element element = W3C.getElement(domResult);
		prepare(element);
        Object value = mainUnmarshalByType(element);
		return mainWrap(value);
	}

	public void prepare(Element element)
	{
		String type = element.getAttribute("type");
        if ( type.isBlank() && element.hasChildNodes() )
        {
            Node firstChild = element.getFirstChild();
            String tag = firstChild.getNodeName();
            element.setAttribute("type", tag);
        }
	}
	
	/**
	 * This method is called when a the XML Binding provider needs to marshal
	 * the given {@link Object} instance to an XML representation. Typically,
	 * the {@link Source} is a {@link DOMSource} that acts as a holder for a
	 * transformation source Document Object Model (DOM) tree.
	 */
	@Override
	public Source marshal(Object value, ValidationEventHandler errorHandler)
	{
		getLogger().trace("marshal: {}", value);
		Element element = mainMarshalWrap(value);
		return W3C.marshal(element, errorHandler);
	}
}
