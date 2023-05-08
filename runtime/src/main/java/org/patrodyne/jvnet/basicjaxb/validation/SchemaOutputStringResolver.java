package org.patrodyne.jvnet.basicjaxb.validation;

import java.io.IOException;
import java.io.StringWriter;

import jakarta.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

/**
 * Controls where a JAXB implementation puts the generated schema.
 * This implementation puts the schema into a String instance.
 * 
 * @author Rick O'Sullivan
 *
 */
public class SchemaOutputStringResolver extends SchemaOutputResolver
{
    private StringWriter stringWriter = null;
    public StringWriter getStringWriter() { return stringWriter; }
	public void setStringWriter(StringWriter stringWriter) { this.stringWriter = stringWriter; }
	
	private Result result = null;
	public Result getResult() { return result; }
	public void setResult(Result result) { this.result = result; }
	
	@Override
	public Result createOutput(String namespaceURI, String systemId) throws IOException 
	{
		setStringWriter(new StringWriter());
		setResult(new StreamResult(getStringWriter()));
		getResult().setSystemId(systemId);
        return getResult();
    }

    public String getSchemaString()
	{
        return getStringWriter().toString();
    }
}
