package org.jvnet.basicjaxb.tests.issues;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Order;
import org.jvnet.basicjaxb.lang.CopyStrategy;
import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.ExtendedJAXBEqualsStrategy;
import org.jvnet.basicjaxb.lang.JAXBCopyStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

@Order(2)
public class CopyableTest extends AbstractSamplesTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		final Object original = createContext().createUnmarshaller().unmarshal(sample);
		final CopyStrategy copyStrategy = new JAXBCopyStrategy();
		final Object copy = copyStrategy.copy(new DefaultRootObjectLocator(this), original, true);
		final EqualsStrategy equalsStrategy = new ExtendedJAXBEqualsStrategy();
		
		DefaultRootObjectLocator lhsLocator = new DefaultRootObjectLocator(this);
		DefaultRootObjectLocator rhsLocator = new DefaultRootObjectLocator(this);
		assertTrue(equalsStrategy.equals(lhsLocator, rhsLocator, original, copy, true, true), "Source and copy must be equal.");
	}
}
