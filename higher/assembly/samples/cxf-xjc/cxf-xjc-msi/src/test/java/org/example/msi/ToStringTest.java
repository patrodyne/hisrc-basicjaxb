package org.example.msi;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

public class ToStringTest extends AbstractMessageTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		setFailFast(true);
		final Object src = getUnmarshaller().unmarshal(sample);
		if ( src instanceof Message )
		{
			Message msi = (Message) src;
			String msis = msi.toString();
			getLogger().info("Message: " + msi.getId() + "\n\n" + msis + "\n");
			assertNotNull(msis);
		}
		else
			fail("Sample is not a Message");
	}
}
