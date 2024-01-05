package org.example.custom.bio;

import static org.example.custom.bio.Context.CUSTOMER_CLASS;
import static org.example.custom.bio.Context.HTML_RESUME_CLASS;
import static org.example.custom.bio.Context.XML_RESUME_CLASS;
import static org.example.custom.bio.Context.createMainUnmarshaller;
import static org.example.custom.bio.Context.mainMarshalWrap;
import static org.example.custom.bio.Context.mainUnmarshalByType;
import static org.example.custom.bio.Context.mainWrap;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.jvnet.basicjaxb.xml.bind.model.util.XmlTypeUtils.isJAXBElement;
import static org.jvnet.basicjaxb.xml.bind.model.util.XmlTypeUtils.isXmlRootElement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.example.custom.bio.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;
import org.w3c.dom.Element;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class ContextTest extends AbstractSamplesTest
{
	private Map<String, File> sampleMap = new HashMap<>();
	@Override
	protected Map<String, File> getSampleMap() { return sampleMap; }
	@Override
	protected void setSampleMap(Map<String, File> sampleMap) { this.sampleMap = sampleMap; }

	@Override
	protected String getContextPath()
	{
		StringBuilder cp = new StringBuilder();
		cp.append(Context.CUSTOMER_CLASS.getName());
		cp.append(":" + Context.HTML_RESUME_CLASS.getName());
		cp.append(":" + Context.XML_RESUME_CLASS.getName());
		return cp.toString();
	}
	
	@Override
	public JAXBContext createContext()
		throws JAXBException
	{
		return Context.MAIN_CONTEXT;
	}
	
	@Override
	protected void checkSample(File sampleFile)
		throws Exception
	{
		assertNotNull(sampleFile, "Sample must exist.");
		getSampleMap().put(sampleFile.getName(), sampleFile);
	}
	
	@BeforeEach
	public void beforeAll() throws JAXBException
	{
		for ( File sampleFile : getSampleFiles() )
			getSampleMap().put(sampleFile.getName(), sampleFile);
	}
	
	@Test
	public void testMainWrap() throws Exception
	{
		List<Object> résuméList = new ArrayList<>();
		résuméList.add(HTML_RESUME_CLASS.getDeclaredConstructor().newInstance());
		résuméList.add(XML_RESUME_CLASS.getDeclaredConstructor().newInstance());

		for ( Object résumé : résuméList )
		{
			Object wrapRésumé = mainWrap(résumé);
			assertNotNull(wrapRésumé);
			assertTrue(isXmlRootElement(wrapRésumé) || isJAXBElement(wrapRésumé));
		}
	}

	@Test
	public void testMainUnmarshalByType() throws JAXBException
	{
		for ( Entry<String, File> sampleEntry : getSampleMap().entrySet() )
		{
			getLogger().debug(sampleEntry.toString());
			// Unmarshal sample file to customer
			Unmarshaller unm = createMainUnmarshaller();
			Object sample = unm.unmarshal(sampleEntry.getValue());
			assertInstanceOf(CUSTOMER_CLASS, sample);
			Customer customer = CUSTOMER_CLASS.cast(sample);
			
			// Marshal customer.bio.any to DOM document
			assertNotNull(customer.getBio());
			assertNotNull(customer.getBio().getAny());
			Element anyElement = mainMarshalWrap(customer.getBio().getAny());
			
			// Use BioDomHandler to invoke custom logic to resolve missing type.
			BioDomHandler bioDomHandler = new BioDomHandler();
			bioDomHandler.prepare(anyElement);
			
			// Unmarshal DOM element by type.
			Object any = mainUnmarshalByType(anyElement);
			assertNotNull(any);
			if ( HTML_RESUME_CLASS.equals(any.getClass()) )
				assertNotNull(HTML_RESUME_CLASS.cast(any).getHtml());
			else if ( XML_RESUME_CLASS.equals(any.getClass()) )
				assertNotNull(XML_RESUME_CLASS.cast(any).getHistory());
			else
				fail("Not a Résumé instance: " + any.getClass());
		}
	}
}
