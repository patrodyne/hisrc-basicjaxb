package org.jvnet.basicjaxb.test.default_value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.jvnet.basicjaxb.dom.DOMUtils.logNode;

import java.io.File;
import java.util.LinkedHashMap;

import org.example.document.Document1;
import org.example.document.DvChoice;
import org.example.document.ObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Node;

import jakarta.xml.bind.JAXBException;

@Order(2)
public class DefaultValue1Test extends AbstractDefaultValueTest
{
	protected Document1 document1A = null;
	protected Document1 document1B = null;
	protected Document1 document1C = null;

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
	public void beforeAll() throws JAXBException
	{
		document1A = (Document1) getUnmarshaller().unmarshal(getSampleMap().get("document1.xml"));
		document1B = new Document1();
		document1C = new Document1();
	}

	@Test
	public void testDefaultValue1AB() throws Exception
	{
		String doc1A = marshalToString(document1A);
		getLogger().debug("doc1A: {}\n", doc1A);

		String doc1B = marshalToString(document1B);
		getLogger().debug("doc1B: {}\n", doc1B);
		
		// document1A: All attributes and elements have set values, see document1.xml.
		// document1B: All attributes and elements use the unset default values.
		assertNotEquals(document1A, document1B, "document1A is set but document1B is unset");
	}
	
	@Test
	public void testDefaultValue1AC()
	{
		// The unmarshalled DvChoice from documentN.xml
		// set dvBoolean but did not set dvString, by choice.
		DvChoice dvChoiceA = document1A.getDvChoice();

		// Choose to set dvBoolean on dvChoiceC.
		DvChoice dvChoiceC = new DvChoice();
		dvChoiceC.setDvBoolean(dvChoiceC.isDvBoolean());
		assertEquals(dvChoiceA, dvChoiceC, "dvChoiceA is set from XML and dvChoiceC is set from defaults");
		document1C.setDvChoice(dvChoiceC);
		
		// document1C: All unset elements are set from the default values.
		if (!document1C.isSetDvDataMap())
		{
			LinkedHashMap<String, Object> dvDataMap = new LinkedHashMap<>();
			dvDataMap.put("Key1", "Value1");
			dvDataMap.put("Key2", "Value2");
			dvDataMap.put("Key3", "Value3");
			document1C.setDvDataMap(dvDataMap);
		}
		
		if (!document1C.isSetDvLimit()) document1C.setDvLimit(document1C.getDvLimit());
		if (!document1C.isSetDvDow()) document1C.setDvDow(document1C.getDvDow());
		if (!document1C.isSetDvBoolean()) document1C.setDvBoolean(document1C.isDvBoolean());
		if (!document1C.isSetDvByte()) document1C.setDvByte(document1C.getDvByte());
		if (!document1C.isSetDvDecimal()) document1C.setDvDecimal(document1C.getDvDecimal());
		if (!document1C.isSetDvDouble()) document1C.setDvDouble(document1C.getDvDouble());
		if (!document1C.isSetDvFloat()) document1C.setDvFloat(document1C.getDvFloat());
		if (!document1C.isSetDvInt()) document1C.setDvInt(document1C.getDvInt());
		if (!document1C.isSetDvInteger()) document1C.setDvInteger(document1C.getDvInteger());
		if (!document1C.isSetDvLong()) document1C.setDvLong(document1C.getDvLong());
		if (!document1C.isSetDvShort()) document1C.setDvShort(document1C.getDvShort());
		if (!document1C.isSetDvString()) document1C.setDvString(document1C.getDvString());
		if (!document1C.isSetDvDateTime()) document1C.setDvDateTime(document1C.getDvDateTime());
		if (!document1C.isSetDvDuration()) document1C.setDvDuration(document1C.getDvDuration());
		if (!document1C.isSetDvBase64Binary()) document1C.setDvBase64Binary (document1C.getDvBase64Binary());
		if (!document1C.isSetDvHexBinary()) document1C.setDvHexBinary(document1C.getDvHexBinary());
		if (!document1C.isSetDvAnySimpleType()) document1C.setDvAnySimpleType(document1C.getDvAnySimpleType());

		// document1C: All attributes are set from the default values.
		document1C.setDaLimit(document1C.getDaLimit());
		document1C.setDaDow(document1C.getDaDow());
		document1C.setDaBoolean(document1C.isDaBoolean());
		document1C.setDaByte(document1C.getDaByte());
		document1C.setDaDecimal(document1C.getDaDecimal());
		document1C.setDaDouble(document1C.getDaDouble());
		document1C.setDaFloat(document1C.getDaFloat());
		document1C.setDaInt(document1C.getDaInt());
		document1C.setDaInteger(document1C.getDaInteger());
		document1C.setDaLong(document1C.getDaLong());
		document1C.setDaShort(document1C.getDaShort());
		document1C.setDaString(document1C.getDaString());
		document1C.setDaDateTime(document1C.getDaDateTime());
		document1C.setDaDuration(document1C.getDaDuration());
		document1C.setDaBase64Binary (document1C.getDaBase64Binary());
		document1C.setDaHexBinary(document1C.getDaHexBinary());
		document1C.setDaAnySimpleType(document1C.getDaAnySimpleType());
		
		if ( document1A.getDvAnySimpleType() instanceof Node)
			logNode(getLogger(), "A", (Node) document1A.getDvAnySimpleType());

		if ( document1C.getDvAnySimpleType() instanceof Node)
			logNode(getLogger(), "C", (Node) document1C.getDvAnySimpleType());

//		assertTrue(((Node) document1A.getDvAnySimpleType()).isEqualNode((Node) document1C.getDvAnySimpleType()));
		
		assertEquals(document1A, document1C, "document1A is set from XML and document1C is set from defaults");
	}
}
