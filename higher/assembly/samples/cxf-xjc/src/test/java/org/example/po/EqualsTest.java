package org.example.po;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

public class EqualsTest extends AbstractSamplesTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		final Object lhs = getUnmarshaller().unmarshal(sample);
		DefaultRootObjectLocator lhsLocator = new DefaultRootObjectLocator(lhs);

		final Object rhs = getUnmarshaller().unmarshal(sample);
		DefaultRootObjectLocator rhsLocator = new DefaultRootObjectLocator(rhs);

		EqualsStrategy equalsStrategy = JAXBEqualsStrategy.getInstance();
		assertTrue(equalsStrategy.equals(lhsLocator, rhsLocator, lhs, rhs, true, true), "Values must be equal.");
		
		if ( lhs instanceof PurchaseOrder && rhs instanceof PurchaseOrder )
		{
			PurchaseOrder po1 = (PurchaseOrder) lhs;
			PurchaseOrder po2 = (PurchaseOrder) rhs;
			assertTrue(po1.equals(po2), "PO1 should equal PO2");
		}
	}
}
