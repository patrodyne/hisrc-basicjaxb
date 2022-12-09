package org.jvnet.basicjaxb.tests.issues;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.basicjaxb.lang.CopyStrategy;
import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.ExtendedJAXBEqualsStrategy;
import org.jvnet.basicjaxb.lang.JAXBCopyStrategy;
import org.jvnet.basicjaxb.test.AbstractSamplesTest;

public class CopyableTest extends AbstractSamplesTest {

	@Override
	protected void checkSample(File sample) throws Exception {

		final Object original = createContext().createUnmarshaller().unmarshal(sample);
		final CopyStrategy copyStrategy = new JAXBCopyStrategy();
		final Object copy = copyStrategy.copy(null, original, true);
		final EqualsStrategy equalsStrategy = new ExtendedJAXBEqualsStrategy();
		assertTrue(equalsStrategy.equals(null, null, original, copy, true, true), "Source and copy must be equal.");
	}

}
