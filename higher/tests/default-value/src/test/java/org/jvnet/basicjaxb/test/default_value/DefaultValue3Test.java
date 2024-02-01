package org.jvnet.basicjaxb.test.default_value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.example.document.Document3;
import org.example.document.ObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

@Order(2)
public class DefaultValue3Test extends AbstractSamplesTest
{
	private Map<String, File> sampleMap = new HashMap<>();
	@Override
	protected Map<String, File> getSampleMap() { return sampleMap; }
	@Override
	protected void setSampleMap(Map<String, File> sampleMap) { this.sampleMap = sampleMap; }
	
	protected Document3 document3A = null;
	protected Document3 document3B = null;
	protected Document3 document3C = null;

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
		
		document3A = (Document3) unmarshaller.unmarshal(getSampleMap().get("document3.xml"));
		document3B = new Document3();
		document3C = new Document3();
	}

	@Test
	public void testDefaultValue3AB()
	{
		// document3A: All attributes and elements have set values, see documentN.xml.
		// document3B: All attributes and elements use the unset default values.
		assertNotEquals(document3A, document3B, "document3A is set but document3B is unset");
	}
	
	@Test
	public void testDefaultValue3AC()
	{
		// document3C: All unset elements are set from the default values.
		if (!document3C.isSetDvNormalizedString()) document3C.setDvNormalizedString(document3C.getDvNormalizedString());
		if (!document3C.isSetDvToken()) document3C.setDvToken(document3C.getDvToken());
		if (!document3C.isSetDvLanguage()) document3C.setDvLanguage(document3C.getDvLanguage());
		if (!document3C.isSetDvNmtoken()) document3C.setDvNmtoken(document3C.getDvNmtoken());
		if (!document3C.isSetDvNmtokens()) document3C.getDvNmtokens();
		if (!document3C.isSetDvInteger()) document3C.setDvInteger(document3C.getDvInteger());
		if (!document3C.isSetDvNonPositiveInteger()) document3C.setDvNonPositiveInteger(document3C.getDvNonPositiveInteger());
		if (!document3C.isSetDvNegativeInteger()) document3C.setDvNegativeInteger(document3C.getDvNegativeInteger());
		if (!document3C.isSetDvLong()) document3C.setDvLong(document3C.getDvLong());
		if (!document3C.isSetDvInt()) document3C.setDvInt(document3C.getDvInt());
		if (!document3C.isSetDvShort()) document3C.setDvShort(document3C.getDvShort());
		if (!document3C.isSetDvByte()) document3C.setDvByte(document3C.getDvByte());
		if (!document3C.isSetDvNonNegativeInteger()) document3C.setDvNonNegativeInteger(document3C.getDvNonNegativeInteger());
		if (!document3C.isSetDvUnsignedLong()) document3C.setDvUnsignedLong(document3C.getDvUnsignedLong());
		if (!document3C.isSetDvUnsignedInt()) document3C.setDvUnsignedInt(document3C.getDvUnsignedInt());
		if (!document3C.isSetDvUnsignedShort()) document3C.setDvUnsignedShort(document3C.getDvUnsignedShort());
		if (!document3C.isSetDvUnsignedByte()) document3C.setDvUnsignedByte(document3C.getDvUnsignedByte());
		if (!document3C.isSetDvPositiveInteger()) document3C.setDvPositiveInteger(document3C.getDvPositiveInteger());

		// document3C: All attributes are set from the default values.
		document3C.setDaNormalizedString(document3C.getDaNormalizedString());
		document3C.setDaToken(document3C.getDaToken());
		document3C.setDaLanguage(document3C.getDaLanguage());
		document3C.setDaNmtoken(document3C.getDaNmtoken());
		document3C.getDaNmtokens();
		document3C.setDaInteger(document3C.getDaInteger());
		document3C.setDaNonPositiveInteger(document3C.getDaNonPositiveInteger());
		document3C.setDaNegativeInteger(document3C.getDaNegativeInteger());
		document3C.setDaLong(document3C.getDaLong());
		document3C.setDaInt(document3C.getDaInt());
		document3C.setDaShort(document3C.getDaShort());
		document3C.setDaByte(document3C.getDaByte());
		document3C.setDaNonNegativeInteger(document3C.getDaNonNegativeInteger());
		document3C.setDaUnsignedLong(document3C.getDaUnsignedLong());
		document3C.setDaUnsignedInt(document3C.getDaUnsignedInt());
		document3C.setDaUnsignedShort(document3C.getDaUnsignedShort());
		document3C.setDaUnsignedByte(document3C.getDaUnsignedByte());
		document3C.setDaPositiveInteger(document3C.getDaPositiveInteger());
		
		assertEquals(document3A, document3C, "document3A is set from XML and document3C is set from defaults");
	}
}
