package org.jvnet.basicjaxb.tests.one;

import java.io.File;

import org.jvnet.basicjaxb.lang.JAXBToStringStrategy;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

public class ToStringTest extends AbstractSamplesTest
{
	@Override
	protected void checkSample(File sample)
		throws Exception
	{
		final Object object = createContext().createUnmarshaller().unmarshal(sample);
		getLogger().debug(JAXBToStringStrategy.getInstance().append(null, new StringBuilder(), object).toString());
	}
}
