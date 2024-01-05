package org.jvnet.basicjaxb.tests.po;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;

public class EqualsTest extends AbstractSamplesTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		final Object lhs = createContext().createUnmarshaller().unmarshal(sample);
		DefaultRootObjectLocator lhsLocator = new DefaultRootObjectLocator(lhs);

		final Object rhs = createContext().createUnmarshaller().unmarshal(sample);
		DefaultRootObjectLocator rhsLocator = new DefaultRootObjectLocator(rhs);

		EqualsStrategy equalsStrategy = JAXBEqualsStrategy.getInstance();
		assertTrue(equalsStrategy.equals(lhsLocator, rhsLocator, lhs, rhs, true, true), "Values must be equal.");
	}
}
