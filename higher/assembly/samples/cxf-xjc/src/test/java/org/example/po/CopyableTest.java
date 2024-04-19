package org.example.po;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

public class CopyableTest extends AbstractSamplesTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		final Object src = getUnmarshaller().unmarshal(sample);
		if ( src instanceof PurchaseOrder )
		{
			PurchaseOrder po1 = (PurchaseOrder) src;
			PurchaseOrder po2 = new PurchaseOrder();
			po1.copyTo(po2);
			assertTrue(po1.equals(po2), "PO1 should equal PO2");
		}
		else
			fail("Sample is not a PurchaseOrder");
	}
}
