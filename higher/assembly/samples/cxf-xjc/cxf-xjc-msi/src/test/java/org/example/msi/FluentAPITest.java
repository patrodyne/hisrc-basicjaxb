package org.example.msi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

public class FluentAPITest extends AbstractMessageTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		setFailFast(true);
		final Object object = getUnmarshaller().unmarshal(sample);
		if ( object instanceof Message )
		{
			Message msi1 = (Message) object;
			Message msi2 = OF.createMessage()
				.useStatus("PASS")
				.useId(1001);
			
			getLogger().debug("MSI1: {}", msi1);
			getLogger().debug("MSI2: {}", msi2);
			
			assertEquals(msi1, msi2, "Unmarshaled and Fluent MSIs are equal.");
		}
	}
}
