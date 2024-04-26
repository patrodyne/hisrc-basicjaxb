package org.example.modulo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.example.modulo.pgr.Root;

import jakarta.xml.bind.JAXBElement;

public class ToStringTest extends AbstractRootTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		setFailFast(true);
		final Object src = getUnmarshaller().unmarshal(sample);
		if ( src instanceof JAXBElement )
		{
			@SuppressWarnings("unchecked")
			Root root = ((JAXBElement<Root>) src).getValue();
			String rootStr = root.toString();
			getLogger().info("Root: \n" + rootStr + "\n");
			assertNotNull(rootStr);
		}
		else
			fail("Sample is not a Root: " + src);
	}
}
