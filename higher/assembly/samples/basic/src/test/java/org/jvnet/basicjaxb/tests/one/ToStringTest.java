package org.jvnet.basicjaxb.tests.one;

import java.io.File;

import org.jvnet.basicjaxb.testing.AbstractSamplesTest;
import org.jvnet.basicjaxb.lang.JAXBToStringStrategy;
import org.jvnet.basicjaxb.lang.ToStringStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;

public class ToStringTest extends AbstractSamplesTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
    	final Object object = createContext().createUnmarshaller().unmarshal(sample);
		DefaultRootObjectLocator rootLocator = new DefaultRootObjectLocator(object);

		ToStringStrategy toStringStrategy = JAXBToStringStrategy.getInstance();
		getLogger().debug(toStringStrategy.append(rootLocator, new StringBuilder(), object).toString());
  	}
}
