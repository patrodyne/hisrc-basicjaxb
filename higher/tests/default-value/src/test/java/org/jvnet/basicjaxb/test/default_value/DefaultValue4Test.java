package org.jvnet.basicjaxb.test.default_value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.example.document.Document4;
import org.example.document.ObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class DefaultValue4Test extends AbstractSamplesTest
{
	private Map<String, File> sampleMap = new HashMap<>();
	@Override
	protected Map<String, File> getSampleMap() { return sampleMap; }
	@Override
	protected void setSampleMap(Map<String, File> sampleMap) { this.sampleMap = sampleMap; }
	
	protected Document4 document4A = null;
	protected Document4 document4B = null;
	protected Document4 document4C = null;

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
		getSampleMap().put(sampleFile.getName(), sampleFile);
	}
	
	@BeforeEach
	public void beforeAll() throws JAXBException
	{
		for ( File sampleFile : getSampleFiles() )
			getSampleMap().put(sampleFile.getName(), sampleFile);
		
		Unmarshaller unmarshaller = createContext().createUnmarshaller();
		
		document4A = (Document4) unmarshaller.unmarshal(getSampleMap().get("document4.xml"));
		document4B = new Document4();
		document4C = new Document4();
	}

	@Test
	public void testDefaultValue4AB()
	{
		// document4A: All attributes and elements have set values, see documentN.xml.
		// document4B: All attributes and elements use the unset default values.
		assertNotEquals(document4A, document4B, "document4A is set but document4B is unset");
	}
	
	@Test
	public void testDefaultValue4AC()
	{
		// document4C: All unset elements are set from the default values.
		if (!document4C.isSetDvDecimalValues()) document4C.getDvDecimalValues();
		if (!document4C.isSetDvIntegerValues()) document4C.getDvIntegerValues();
		if (!document4C.isSetDvBooleanValues()) document4C.getDvBooleanValues();
		if (!document4C.isSetDvByteValues()) document4C.getDvByteValues();
		if (!document4C.isSetDvDoubleValues()) document4C.getDvDoubleValues();
		if (!document4C.isSetDvDurationValues()) document4C.getDvDurationValues();
		if (!document4C.isSetDvFloatValues()) document4C.getDvFloatValues();
		if (!document4C.isSetDvIntValues()) document4C.getDvIntValues();
		if (!document4C.isSetDvLongValues()) document4C.getDvLongValues();
		if (!document4C.isSetDvShortValues()) document4C.getDvShortValues();
		if (!document4C.isSetDvStringValues()) document4C.getDvStringValues();
		if (!document4C.isSetDvDateTimeValues()) document4C.getDvDateTimeValues();
		
		// document4C: All attributes are set from the default values.
		document4C.getDaDecimalValues();
		document4C.getDaIntegerValues();
		document4C.getDaBooleanValues();
		document4C.getDaByteValues();
		document4C.getDaDoubleValues();
		document4C.getDaDurationValues();
		document4C.getDaFloatValues();
		document4C.getDaIntValues();
		document4C.getDaLongValues();
		document4C.getDaShortValues();
		document4C.getDaStringValues();
		document4C.getDaDateTimeValues();
		
		assertEquals(document4A, document4C, "document4A is set from XML and document4C is set from defaults");
	}
}
