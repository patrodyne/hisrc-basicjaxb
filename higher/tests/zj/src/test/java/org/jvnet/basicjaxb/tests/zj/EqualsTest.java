package org.jvnet.basicjaxb.tests.zj;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;
import org.jvnet.basicjaxb.util.ClassUtils;

import com.oce.obis.sei.api.data.MediaInfo;
import com.oce.obis.sei.api.data.ObisMediaCatalogue;

import jakarta.xml.bind.Unmarshaller;

public class EqualsTest extends AbstractSamplesTest
{
	@Override
	public String getContextPath()
	{
		return "com.oce.obis.sei.api.data";
	}

	@Override
	protected void checkSample(File sample)
		throws Exception
	{
		Unmarshaller unmarshaller = createContext().createUnmarshaller();
		
		ObisMediaCatalogue lhs = (ObisMediaCatalogue) unmarshaller.unmarshal(sample);
		ObisMediaCatalogue rhs = (ObisMediaCatalogue) unmarshaller.unmarshal(sample);
		
		String lhsIdentity = ClassUtils.identify(lhs);
		String rhsIdentity = ClassUtils.identify(rhs);
		assertNotEquals(lhsIdentity, rhsIdentity, "Unmarshaling creates unique objects.");

		DefaultRootObjectLocator lhsLocator = new DefaultRootObjectLocator(lhs);
		DefaultRootObjectLocator rhsLocator = new DefaultRootObjectLocator(rhs);
		JAXBEqualsStrategy strategy = JAXBEqualsStrategy.getInstance();
		
		boolean sidesEquate = strategy.equals(lhsLocator, rhsLocator, lhs, rhs, true, true);
		assertTrue(sidesEquate, "LHS and RHS values must be equal.");
		
		for ( MediaInfo rollInfo : rhs.getRollInfoList().getRollInfo() )
		{
			String shortDescription = rollInfo.getShortDescription();
			rollInfo.setShortDescription("CHAOS: " + shortDescription);
		}
		
		boolean sidesDiffer = !strategy.equals(lhsLocator, rhsLocator, lhs, rhs, true, true);
		assertTrue(sidesDiffer, "LHS and RHS values must differ.");
	}
}
