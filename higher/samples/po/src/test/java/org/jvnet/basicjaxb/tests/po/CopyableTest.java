package org.jvnet.basicjaxb.tests.po;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.basicjaxb.lang.JAXBCopyStrategy;
import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.test.AbstractSamplesTest;

public class CopyableTest extends AbstractSamplesTest {

	@Override
	protected void checkSample(File sample) throws Exception {

		final Object object = createContext().createUnmarshaller().unmarshal(
				sample);
		final Object copy = JAXBCopyStrategy.INSTANCE.copy(null, object);
		assertTrue(JAXBEqualsStrategy.INSTANCE.equals(null, null, object, copy),
			"Source and copy must be equal.");
	}

}
