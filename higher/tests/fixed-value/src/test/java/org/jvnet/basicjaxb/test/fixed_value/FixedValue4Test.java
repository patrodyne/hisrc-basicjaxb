package org.jvnet.basicjaxb.test.fixed_value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.StringWriter;

import org.example.document.Document4;
import org.example.document.ObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

import jakarta.xml.bind.JAXBException;

@Order(2)
public class FixedValue4Test extends AbstractSamplesTest
{
	protected Document4 document4A = null;
	protected Document4 document4B = null;

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
		// Document4 is FIXED; thus, the unmarshal should not change any values.
		document4A = (Document4) getUnmarshaller().unmarshal(getSampleMap().get("document4.xml"));
		document4B = new Document4();
	}

	@Test
	public void testFixedValue4AB() throws JAXBException
	{
		StringWriter sw4A = new StringWriter();
		getMarshaller().marshal(document4A, sw4A);
		String doc4A = sw4A.toString();
		getLogger().debug("doc4A: {}\n", doc4A);

		StringWriter sw4B = new StringWriter();
		getMarshaller().marshal(document4B, sw4B);
		String doc4B = sw4B.toString();
		getLogger().debug("doc4B: {}\n", doc4B);

		// document4A: All attributes and elements have FIXED values, see document2.xml.
		// document4B: All attributes and elements have FIXED values.
		assertEquals(doc4A, doc4B, "document4A is FIXED and document4B is FIXED");
		assertEquals(document4A, document4B, "document4A is FIXED and document4B is FIXED");

		getLogger().debug("document4A: {}", document4A);
	}
}
