package org.jvnet.basicjaxb.test.fixed_value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.StringWriter;

import org.example.document.Document3;
import org.example.document.ObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

import jakarta.xml.bind.JAXBException;

@Order(2)
public class FixedValue3Test extends AbstractSamplesTest
{	
	protected Document3 document3A = null;
	protected Document3 document3B = null;

	@Override
	protected String getContextPath()
	{
		return ObjectFactory.class.getPackageName();
	}
	
	@Override
	protected void checkSample(File sampleFile)
		throws Exception
	{
		assertNotNull(sampleFile, "Sample must exist.");
	}
	
	@BeforeEach
	public void beforeEach() throws JAXBException
	{
		// Document3 is FIXED; thus, the unmarshal should not change any values.
		document3A = (Document3) getUnmarshaller().unmarshal(getSampleMap().get("document3.xml"));
		document3B = new Document3();
	}

	@Test
	public void testFixedValue3AB() throws JAXBException
	{
		StringWriter sw3A = new StringWriter();
		getMarshaller().marshal(document3A, sw3A);
		String doc3A = sw3A.toString();
		getLogger().debug("doc3A: {}\n", doc3A);

		StringWriter sw3B = new StringWriter();
		getMarshaller().marshal(document3B, sw3B);
		String doc3B = sw3B.toString();
		getLogger().debug("doc3B: {}\n", doc3B);

		// document3A: All attributes and elements have FIXED values, see document2.xml.
		// document3B: All attributes and elements have FIXED values.
		assertEquals(doc3A, doc3B, "document3A is FIXED and document3B is FIXED");
		assertEquals(document3A, document3B, "document3A is FIXED and document3B is FIXED");

		getLogger().debug("document3A: {}", document3A);
	}
}
