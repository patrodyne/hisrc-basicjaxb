package org.example.msi;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

public class EqualsTest extends AbstractMessageTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		final Object lhs = getUnmarshaller().unmarshal(sample);
		final Object rhs = getUnmarshaller().unmarshal(sample);
		
		if ( lhs instanceof Message && rhs instanceof Message )
		{
			Message msi1 = (Message) lhs;
			Message msi2 = (Message) rhs;
			assertTrue(msi1.equals(msi2), "MSI1 should equal MSI2");
		}
		else
			fail("Sample is not a Message");
	}
}
