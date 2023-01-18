package org.jvnet.basicjaxb.tests.posimple;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import jakarta.xml.bind.JAXBElement;
import org.jvnet.basicjaxb.test.AbstractSamplesTest;

public class EqualsTest extends AbstractSamplesTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		final JAXBElement<?> lhs = (JAXBElement<?>) createContext().createUnmarshaller().unmarshal(sample);
		final JAXBElement<?> rhs = (JAXBElement<?>) createContext().createUnmarshaller().unmarshal(sample);

		getLogger().debug("LHS Value: " + lhs.getValue());
		getLogger().debug("RHS Value: " + rhs.getValue());

		assertTrue(lhs.getValue().equals(rhs.getValue()), "Values must be equal.");
	}
}
