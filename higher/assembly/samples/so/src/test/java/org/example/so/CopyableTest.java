package org.example.so;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.basicjaxb.lang.CopyStrategy;
import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.JAXBCopyStrategy;
import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

public class CopyableTest extends AbstractSamplesTest
{
	/**
	 * Configure the JAXB context path in AbstractSamplesTest
	 * to include all classes from two packages.
	 */
	@Override
	protected String getContextPath()
	{
		return ShipOrder.class.getPackage().getName();
	}

	@Override
	protected void checkSample(File sample) throws Exception
	{
		Object src = getUnmarshaller().unmarshal(sample);
		DefaultRootObjectLocator srcLocator = new DefaultRootObjectLocator(src);

		CopyStrategy copyStrategy = JAXBCopyStrategy.getInstance();
		Object cpy = copyStrategy.copy(srcLocator, src, true);
		DefaultRootObjectLocator cpyLocator = new DefaultRootObjectLocator(cpy);

		EqualsStrategy equalsStrategy = JAXBEqualsStrategy.getInstance();
		assertTrue(equalsStrategy.equals(srcLocator, cpyLocator, src, cpy, true, true), "Source and copy must be equal.");
	}
}
