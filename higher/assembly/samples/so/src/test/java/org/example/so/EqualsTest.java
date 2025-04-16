package org.example.so;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

public class EqualsTest extends AbstractSamplesTest
{
	/**
	 * Configure the JAXB context path in AbstractSamplesTest
	 * to include all classes from two packages.
	 */
	@Override
	protected String getContextPath()
	{
		return ShipOrder.class.getPackage().getName();
	}

	@Override
	protected void checkSample(File sample) throws Exception
	{
		Object lhs = getUnmarshaller().unmarshal(sample);
		DefaultRootObjectLocator lhsLocator = new DefaultRootObjectLocator(lhs);

		Object rhs = getUnmarshaller().unmarshal(sample);
		DefaultRootObjectLocator rhsLocator = new DefaultRootObjectLocator(rhs);

		EqualsStrategy equalsStrategy = JAXBEqualsStrategy.getInstance();
		assertTrue(equalsStrategy.equals(lhsLocator, rhsLocator, lhs, rhs, true, true), "Values must be equal.");
	}
}
