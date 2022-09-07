package org.jvnet.jaxb2_commons.tests.issues;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.jaxb2_commons.lang.CopyStrategy2;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.ExtendedJAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBCopyStrategy;
import org.jvnet.jaxb2_commons.test.AbstractSamplesTest;

@SuppressWarnings("deprecation")
public class CopyableTest extends AbstractSamplesTest {

	@Override
	protected void checkSample(File sample) throws Exception {

		final Object original = createContext().createUnmarshaller().unmarshal(
				sample);
		final CopyStrategy2 copyStrategy = new JAXBCopyStrategy();
		final Object copy = copyStrategy.copy(null, original, true);
		final EqualsStrategy equalsStrategy = new ExtendedJAXBEqualsStrategy();
		assertTrue(equalsStrategy.equals(null, null, original, copy), "Source and copy must be equal.");
	}

}
