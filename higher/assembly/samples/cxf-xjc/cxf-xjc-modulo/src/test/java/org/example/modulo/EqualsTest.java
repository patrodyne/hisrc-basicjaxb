package org.example.modulo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.example.modulo.pgr.Root;

import jakarta.xml.bind.JAXBElement;

public class EqualsTest extends AbstractRootTest
{
	@SuppressWarnings("unchecked")
	@Override
	protected void checkSample(File sample) throws Exception
	{
		final Object lhs = getUnmarshaller().unmarshal(sample);
		final Object rhs = getUnmarshaller().unmarshal(sample);
		
		if ( lhs instanceof JAXBElement && rhs instanceof JAXBElement )
		{
			Root root1 = ((JAXBElement<Root>) lhs).getValue();
			Root root2 = ((JAXBElement<Root>) rhs).getValue();
			assertTrue(root1.equals(root2), "Root1 should equal Root2");
		}
		else
			fail("Sample is not a Root: " + lhs);
	}
}
