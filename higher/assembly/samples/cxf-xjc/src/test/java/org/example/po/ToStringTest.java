package org.example.po;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

public class ToStringTest extends AbstractSamplesTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		setFailFast(true);
		final Object src = getUnmarshaller().unmarshal(sample);
		if ( src instanceof PurchaseOrder )
		{
			PurchaseOrder po = (PurchaseOrder) src;
			String pos = po.toString();
			getLogger().info("PurchaseOrder: " + po.getOrderDate() + "\n\n" + pos + "\n");
			assertTrue(pos.matches(
				"PurchaseOrder@.*\\["+
					"USAddress@.*\\[Alice Smith, 123 Maple Street, Mill Valley, CA, 90952\\], "+
					"USAddress@.*\\[Robert Smith, 8 Oak Avenue, Old Town, PA, 95819\\], "+
					"Hurry, my lawn is going wild!, Items@.*\\[<size=2>\\], "+
					"Payments@.*\\[<size=3>\\], <size=2>, 1999-05-20, false\\]"));

		}
		else
			fail("Sample is not a PurchaseOrder");
	}
}
