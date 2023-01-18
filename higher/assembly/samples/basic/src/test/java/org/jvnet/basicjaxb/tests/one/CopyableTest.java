package org.jvnet.basicjaxb.tests.one;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.basicjaxb.lang.CopyStrategy;
import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.JAXBCopyStrategy;
import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.test.AbstractSamplesTest;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;

public class CopyableTest extends AbstractSamplesTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		final Object src = createContext().createUnmarshaller().unmarshal(sample);
		DefaultRootObjectLocator srcLocator = new DefaultRootObjectLocator(src);

		CopyStrategy copyStrategy = JAXBCopyStrategy.getInstance();
		final Object cpy = copyStrategy.copy(srcLocator, src, true);
		DefaultRootObjectLocator cpyLocator = new DefaultRootObjectLocator(cpy);

		EqualsStrategy equalsStrategy = JAXBEqualsStrategy.getInstance();
		assertTrue(equalsStrategy.equals(srcLocator, cpyLocator, src, cpy, true, true), "Source and copy must be equal.");
	}
}
