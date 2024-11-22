package org.example.beaninfo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.StringWriter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.plugin.beaninfo.model.AlignmentType;
import org.jvnet.basicjaxb.plugin.beaninfo.model.Bean;
import org.jvnet.basicjaxb.plugin.beaninfo.model.ObjectFactory;
import org.jvnet.basicjaxb.plugin.beaninfo.model.Property;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

import jakarta.xml.bind.JAXBException;

@Order(2)
public class BeanInfoTest extends AbstractSamplesTest
{
	protected Bean bean01A = null, bean01B = null;
	protected Property property01A = null, property01B = null;

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
		// Unmarshal bean01B and create an expected bean01A.
		bean01A = new Bean();
		bean01A.setName("PurchaseOrder");
		bean01A.setDisplayName("Purchase Order");
		bean01A.setDescription("A document issued by a buyer to a seller, indicating types, quantities, and prices for products.");
		bean01B = (Bean) getUnmarshaller().unmarshal(getSampleMap().get("bean01.xml"));

		// Unmarshal property01B and create an expected property01A.
		property01A = new Property();
		property01A.setName("Comment");
		property01A.setDisplayName("Observation");
		property01A.setDescription("A remark, or statement based on what one has noticed.");
		property01A.setAlignment(AlignmentType.LEFT);
		property01A.setMaxWidth(30);
		property01B = (Property) getUnmarshaller().unmarshal(getSampleMap().get("property01.xml"));
	}

	@Test
	public void testBean01AB() throws JAXBException
	{
		StringWriter sw01A = new StringWriter();
		getMarshaller().marshal(bean01A, sw01A);
		getLogger().debug("bean01A:\n{}", sw01A.toString());

		StringWriter sw01B = new StringWriter();
		getMarshaller().marshal(bean01B, sw01B);
		getLogger().debug("bean01B:\n{}", sw01B.toString());

		// bean01A: Expected instance of bean01.xml.
		// bean01B: Actual instance of bean01.xml.
		assertEquals(bean01A, bean01B, "bean");

		getLogger().debug("bean01A: {}", bean01A);
		getLogger().debug("bean01B: {}", bean01B);
	}
	
	@Test
	public void testProperty01AB() throws JAXBException
	{
		StringWriter sw01A = new StringWriter();
		getMarshaller().marshal(property01A, sw01A);
		getLogger().debug("property01A:\n{}", sw01A.toString());

		StringWriter sw01B = new StringWriter();
		getMarshaller().marshal(property01B, sw01B);
		getLogger().debug("property01B:\n{}", sw01B.toString());

		// property01A: Expected instance of property01.xml.
		// property01B: Actual instance of property01.xml.
		assertEquals(property01A, property01B, "property");

		getLogger().debug("property01A: {}", property01A);
		getLogger().debug("property01B: {}", property01B);
	}
}
