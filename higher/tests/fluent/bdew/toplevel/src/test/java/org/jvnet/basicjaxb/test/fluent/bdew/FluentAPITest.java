package org.jvnet.basicjaxb.test.fluent.bdew;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.jvnet.basicjaxb.lang.ContextUtils;
import org.jvnet.basicjaxb.test.AbstractSamplesTest;

import kwep_stammdaten._1._0.ObjectFactory;
import kwep_stammdaten._1._0.Stammdaten;

public class FluentAPITest extends AbstractSamplesTest
{
	private static final ObjectFactory OF = new ObjectFactory();
	
	@Override
	protected String getContextPath()
	{
		return ContextUtils.getContextPath(OF.getClass());
	}
	
	@Override
	protected void checkSample(File sample) throws Exception
	{
		final Object object = createContext().createUnmarshaller().unmarshal(sample);
		if ( object instanceof Stammdaten )
		{
			Stammdaten sd1 = (Stammdaten) object;
			Stammdaten sd2 = OF.createStammdaten();
			
			getLogger().debug("SD1: {}", sd1);
			getLogger().debug("SD2: {}", sd2);
			
			assertEquals(sd1, sd2, "Unmarshaled and Fluent Stammdatens are equal.");
		}
	}
}
