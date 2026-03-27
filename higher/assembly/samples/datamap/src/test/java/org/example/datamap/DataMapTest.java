package org.example.datamap;

import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

import jakarta.xml.bind.JAXBException;

/**
 * A JUnit test to check a sample XML file.
 */
public class DataMapTest extends AbstractSamplesTest
{
	// Represents the Employee object factory.
	private static ObjectFactory OF = new ObjectFactory();

	/**
	 * Configure the JAXB context path in AbstractSamplesTest
	 * to include all classes from two packages.
	 */
	@Override
	protected String getContextPath()
	{
		return "org.example.datamap";
	}

	@BeforeEach
	public void beforeEach() throws JAXBException
	{
		setJaxbContext(createContext());
		setUnmarshaller(getJaxbContext().createUnmarshaller());
		setMarshaller(getJaxbContext().createMarshaller());
		getMarshaller().setProperty(JAXB_FORMATTED_OUTPUT, true);
	}

	/**
	 * Override the test method in AbstractSamplesTest to read
	 * this project's sample files and assert expectations.
	 */
	@Override
	protected void checkSample(File sample)	throws Exception
	{
		Object root = getUnmarshaller().unmarshal(sample);

		if ( root instanceof Employee )
		{
			Employee employee = (Employee) root;
			String employeeXml = marshalToString(employee);
			getLogger().debug("Employee:\n\n" + employeeXml);
			assertNotNull(employee.getDataMap(), "Employee DataMap expected.");
			assertFalse(employee.getDataMap().isEmpty(), "Employee DataMap not empty expected.");
			assertInstanceOf(Map.class, employee.getDataMap(), "Employee DataMap should be a java.util.Map");
			for (  Entry<String, Object> entry : employee.getDataMap().entrySet()  )
			{
				assertNotNull(entry.getKey(), "Entry must have a Key");
				assertNotNull(entry.getValue(), "Entry must have a Value");
				getLogger().trace("Entry: " + entry);
			}
			assertNotNull(employee.getValue(), "Employee Value not null expected.");
		}
		else
			fail("Object is not instance of Employee: " + root);
	}

	@Test
	public void adapterTest() throws Exception
	{
		// Fluently create a DataMap instance.
		DataMap dataMap1 = OF.createDataMap()
			.useEntry(OF.createEntry()
				.useKey("key1")
				.useValue("value1"))
			.useEntry(OF.createEntry()
				.useKey("key2")
				.useValue("value2"));

		// DataMapXmlAdapter round trip
		DataMapXmlAdapter adapter = new DataMapXmlAdapter();
		Map<String, Object> map = adapter.unmarshal(dataMap1);
		DataMap dataMap2 = adapter.marshal(map);

		// Assert round trip result
		assertEquals(dataMap1, dataMap2, "Adapter round trip should be equal");
	}
}
