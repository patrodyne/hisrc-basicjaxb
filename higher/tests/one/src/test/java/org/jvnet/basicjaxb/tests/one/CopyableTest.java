package org.jvnet.basicjaxb.tests.one;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Order;
import org.jvnet.basicjaxb.lang.CopyStrategy;
import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.JAXBCopyStrategy;
import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

@Order(2)
public class CopyableTest extends AbstractSamplesTest
{
	@Override
	protected void checkSample(File sample)
		throws Exception
	{
		final Object original = createContext().createUnmarshaller().unmarshal(sample);
		DefaultRootObjectLocator origLocator = new DefaultRootObjectLocator(original);
		
		CopyStrategy copyStrategy = JAXBCopyStrategy.getInstance();
		
		final Object copy = copyStrategy.copy(origLocator, original, true);
		DefaultRootObjectLocator copyLocator = new DefaultRootObjectLocator(copy);
		
		EqualsStrategy equalsStrategy = JAXBEqualsStrategy.getInstance();
		boolean equate = equalsStrategy.equals(origLocator, copyLocator, original, copy, true, true);
		assertTrue(equate, "Source and copy must be equal.");
	}
}
