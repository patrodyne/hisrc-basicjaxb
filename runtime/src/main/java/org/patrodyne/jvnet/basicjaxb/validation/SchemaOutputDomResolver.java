package org.patrodyne.jvnet.basicjaxb.validation;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;

/**
 * Controls where a JAXB implementation puts the generated schema.
 * This implementation puts the schema into a DOM instance.
 * 
 * @author Rick O'Sullivan
 *
 */
public class SchemaOutputDomResolver extends SchemaOutputResolver
{
    private DOMResult result = null;
    public DOMResult getResult() { return result; }
    public void setResult(DOMResult result) { this.result = result; }
    
	public Result createOutput(String namespaceURI, String systemId) throws IOException 
	{
		setResult(new DOMResult());
        getResult().setSystemId(systemId);
        return getResult();
    }
	
	public Node getSchemaNode()
	{
		return getResult().getNode();
	}
	
	public DOMSource getDomSource()
	{
		return new DOMSource(getSchemaNode());
	}
	
	public String getSchemaNodeString() throws IOException, TransformerException
	{
		TransformerFactory tf = TransformerFactory.newInstance();
		tf.setAttribute("indent-number", 2);
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");

		StringWriter writer = new StringWriter();
		transformer.transform(getDomSource(), new StreamResult(writer));
		return writer.toString();
	}
}
