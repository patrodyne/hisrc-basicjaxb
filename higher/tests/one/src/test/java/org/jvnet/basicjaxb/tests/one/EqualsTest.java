package org.jvnet.basicjaxb.tests.one;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

import jakarta.xml.bind.Unmarshaller;

public class EqualsTest extends AbstractSamplesTest
{
	@Override
	protected void checkSample(File sample)
		throws Exception
	{
		Unmarshaller unmarshaller = createContext().createUnmarshaller();
		final Object lhs = unmarshaller.unmarshal(sample);
		final Object rhs = unmarshaller.unmarshal(sample);
		
		JAXBEqualsStrategy strategy = JAXBEqualsStrategy.getInstance();
		
		ObjectLocator lhsLocator = new DefaultRootObjectLocator(lhs);
		ObjectLocator rhsLocator = new DefaultRootObjectLocator(rhs);
		boolean equate = strategy.equals(lhsLocator, rhsLocator, lhs, rhs, true, true);
		
		assertTrue(equate, "Values must be equal.");
	}
}
