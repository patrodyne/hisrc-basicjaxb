package org.jvnet.basicjaxb.test.fixed_value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.StringWriter;

import org.example.document.Document2;
import org.example.document.ObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

import jakarta.xml.bind.JAXBException;

@Order(2)
public class FixedValue2Test extends AbstractSamplesTest
{
	protected Document2 document2A = null;
	protected Document2 document2B = null;

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
		// Document2 is FIXED; thus, the unmarshal should not change any values.
		document2A = (Document2) getUnmarshaller().unmarshal(getSampleMap().get("document2.xml"));
		document2B = new Document2();
	}

	@Test
	public void testFixedValue2AB() throws JAXBException
	{
		StringWriter sw2A = new StringWriter();
		getMarshaller().marshal(document2A, sw2A);
		String doc2A = sw2A.toString();
		getLogger().debug("doc2A: {}\n", doc2A);

		StringWriter sw2B = new StringWriter();
		getMarshaller().marshal(document2B, sw2B);
		String doc2B = sw2B.toString();
		getLogger().debug("doc2B: {}\n", doc2B);

		// document2A: All attributes and elements have FIXED values, see document2.xml.
		// document2B: All attributes and elements have FIXED values.
		assertEquals(doc2A, doc2B, "document2A is FIXED and document2B is FIXED");
		assertEquals(document2A, document2B, "document2A is FIXED and document2B is FIXED");

		getLogger().debug("document2A: {}", document2A);
	}
}
