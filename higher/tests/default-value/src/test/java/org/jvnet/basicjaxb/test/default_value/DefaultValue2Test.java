package org.jvnet.basicjaxb.test.default_value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.example.document.Document2;
import org.example.document.ObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.test.AbstractSamplesTest;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class DefaultValue2Test extends AbstractSamplesTest
{
	private Map<String, File> sampleMap = new HashMap<>();
	@Override
	protected Map<String, File> getSampleMap() { return sampleMap; }
	@Override
	protected void setSampleMap(Map<String, File> sampleMap) { this.sampleMap = sampleMap; }
	
	protected Document2 document2A = null;
	protected Document2 document2B = null;
	protected Document2 document2C = null;

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
		
		document2A = (Document2) unmarshaller.unmarshal(getSampleMap().get("document2.xml"));
		document2B = new Document2();
		document2C = new Document2();
	}

	@Test
	public void testDefaultValue2AB()
	{
		// document2A: All attributes and elements have set values, see documentN.xml.
		// document2B: All attributes and elements use the unset default values.
		assertNotEquals(document2A, document2B, "document2A is set but document2B is unset");
	}
	
	/** <p>Note:</p>
     * <p>Two <code>QName</code>s are considered equal if and only if
     * both the Namespace URI and local part are equal. This method
     * uses <code>String.equals()</code> to check equality of the
     * Namespace URI and local part. The prefix is
     * <strong><em>NOT</em></strong> used to determine equality.</p>
     */
	@Test
	public void testDefaultValue2AC()
	{
		// document2C: All unset elements are set from the default values.
		if (!document2C.isSetDvString()) document2C.setDvString(document2C.getDvString());
		if (!document2C.isSetDvBoolean()) document2C.setDvBoolean(document2C.isDvBoolean());
		if (!document2C.isSetDvDecimal()) document2C.setDvDecimal(document2C.getDvDecimal());
		if (!document2C.isSetDvFloat()) document2C.setDvFloat(document2C.getDvFloat());
		if (!document2C.isSetDvDouble()) document2C.setDvDouble(document2C.getDvDouble());
		if (!document2C.isSetDvDuration()) document2C.setDvDuration(document2C.getDvDuration());
		if (!document2C.isSetDvDateTime()) document2C.setDvDateTime(document2C.getDvDateTime());
		if (!document2C.isSetDvTime()) document2C.setDvTime(document2C.getDvTime());
		if (!document2C.isSetDvDate()) document2C.setDvDate(document2C.getDvDate());
		if (!document2C.isSetDvGYearMonth()) document2C.setDvGYearMonth(document2C.getDvGYearMonth());
		if (!document2C.isSetDvGYear()) document2C.setDvGYear(document2C.getDvGYear());
		if (!document2C.isSetDvGMonthDay()) document2C.setDvGMonthDay(document2C.getDvGMonthDay());
		if (!document2C.isSetDvGDay()) document2C.setDvGDay(document2C.getDvGDay());
		if (!document2C.isSetDvGMonth()) document2C.setDvGMonth(document2C.getDvGMonth());
		if (!document2C.isSetDvHexBinary()) document2C.setDvHexBinary(document2C.getDvHexBinary());
		if (!document2C.isSetDvBase64Binary()) document2C.setDvBase64Binary (document2C.getDvBase64Binary());
		if (!document2C.isSetDvAnyURI()) document2C.setDvAnyURI(document2C.getDvAnyURI());
		if (!document2C.isSetDvQName()) document2C.setDvQName(document2C.getDvQName());

		// document2C: All attributes are set from the default values.
		document2C.setDaString(document2C.getDaString());
		document2C.setDaBoolean(document2C.isDaBoolean());
		document2C.setDaDecimal(document2C.getDaDecimal());
		document2C.setDaFloat(document2C.getDaFloat());
		document2C.setDaDouble(document2C.getDaDouble());
		document2C.setDaDuration(document2C.getDaDuration());
		document2C.setDaDateTime(document2C.getDaDateTime());
		document2C.setDaTime(document2C.getDaTime());
		document2C.setDaDate(document2C.getDaDate());
		document2C.setDaGYearMonth(document2C.getDaGYearMonth());
		document2C.setDaGYear(document2C.getDaGYear());
		document2C.setDaGMonthDay(document2C.getDaGMonthDay());
		document2C.setDaGDay(document2C.getDaGDay());
		document2C.setDaGMonth(document2C.getDaGMonth());
		document2C.setDaHexBinary(document2C.getDaHexBinary());
		document2C.setDaBase64Binary (document2C.getDaBase64Binary());
		document2C.setDaAnyURI(document2C.getDaAnyURI());
		document2C.setDaQName(document2C.getDaQName());
		
		assertEquals(document2A, document2C, "document2A is set from XML and document2C is set from defaults");
	}
}
