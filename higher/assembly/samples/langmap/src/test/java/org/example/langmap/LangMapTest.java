package org.example.langmap;

import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
public class LangMapTest extends AbstractSamplesTest
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
		return "org.example.langmap";
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

		if ( root instanceof TextileMaterialDetails tmd )
		{
			String tmdXml = marshalToString(tmd);
			getLogger().debug("TextileMaterialDetails:\n\n" + tmdXml);

			assertNotNull(tmd.getTextileMaterialDescription(), "TextileMaterialDescription expected.");
			assertFalse(tmd.getTextileMaterialDescription().isEmpty(), "TextileMaterialDescription not empty expected.");
			assertInstanceOf(Map.class, tmd.getTextileMaterialDescription(), "TextileMaterialDescription should be a java.util.Map");
			for (  Entry<String, String> entry : tmd.getTextileMaterialDescription().entrySet()  )
			{
				assertNotNull(entry.getKey(), "Entry must have a Key");
				assertNotNull(entry.getValue(), "Entry must have a Value");
				getLogger().trace("Entry: " + entry);
			}
			assertTrue(tmd.getTextileMaterialPercentage() > 0.0, "Positive TextileMaterialPercentage expected.");
		}
		else
			fail("Object is not instance of Employee: " + root);
	}

	@Test
	public void adapterTest() throws Exception
	{
		// Fluently create a EntryLangMap instance.
		EntryLangString entryLangString1 = OF.createEntryLangString()
			.useEntry
				(
					OF.createLangString().useLang("en").useValue("Rayon"),
					OF.createLangString().useLang("de").useValue("Viskose")
				);

		// LangMapXmlAdapter round trip
		LangMapXmlAdapter adapter = new LangMapXmlAdapter();
		Map<String, String> map = adapter.unmarshal(entryLangString1);
		EntryLangString entryLangString2 = adapter.marshal(map);

		// Assert round trip result
		assertEquals(entryLangString1, entryLangString2, "Adapter round trip should be equal");
	}
}
