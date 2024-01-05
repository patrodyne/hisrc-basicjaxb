package org.example.platce;

import static org.example.platce.Context.RESPONSE_CLASS;
import static org.example.platce.Context.METHODXX1_CLASS;
import static org.example.platce.Context.METHODXX2_CLASS;
import static org.example.platce.Context.createMainUnmarshaller;
import static org.example.platce.Context.mainMarshalWrap;
import static org.example.platce.Context.mainUnmarshalByName;
import static org.example.platce.Context.mainWrap;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.example.platce.util.XmlTypeUtils.isJAXBElement;
import static org.example.platce.util.XmlTypeUtils.isXmlRootElement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
		cp.append(Context.RESPONSE_CLASS.getName());
		cp.append(":" + Context.METHODXX1_CLASS.getName());
		cp.append(":" + Context.METHODXX2_CLASS.getName());
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
		List<Object> methodList = new ArrayList<>();
		methodList.add(METHODXX1_CLASS.getDeclaredConstructor().newInstance());
		methodList.add(METHODXX2_CLASS.getDeclaredConstructor().newInstance());

		for ( Object method : methodList )
		{
			Object wrapMethod = mainWrap(method);
			assertNotNull(wrapMethod);
			assertTrue(isXmlRootElement(wrapMethod) || isJAXBElement(wrapMethod));
		}
	}

	@Test
	public void testMainUnmarshalByName() throws JAXBException
	{
		for ( Entry<String, File> sampleEntry : getSampleMap().entrySet() )
		{
			getLogger().debug(sampleEntry.toString());
			// Unmarshal sample file to response
			Unmarshaller unm = createMainUnmarshaller();
			Object sample = unm.unmarshal(sampleEntry.getValue());
			assertInstanceOf(RESPONSE_CLASS, sample);
			Response response = RESPONSE_CLASS.cast(sample);
			
			// Marshal response.clazz.any to DOM document
			assertNotNull(response.getClazz());
			assertNotNull(response.getClazz().getAny());
			Element anyElement = mainMarshalWrap(response.getClazz().getAny());
			
			// Unmarshal DOM element by name.
			Object any = mainUnmarshalByName(anyElement);
			assertNotNull(any);
			if ( METHODXX1_CLASS.equals(any.getClass()) )
				assertNotNull(METHODXX1_CLASS.cast(any).getInfo());
			else if ( METHODXX2_CLASS.equals(any.getClass()) )
				assertNotNull(METHODXX2_CLASS.cast(any).getData());
			else
				fail("Not a Method instance: " + any.getClass());
		}
	}
}
