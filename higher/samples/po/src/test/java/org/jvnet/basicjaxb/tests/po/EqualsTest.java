package org.jvnet.basicjaxb.tests.po;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.test.AbstractSamplesTest;

public class EqualsTest extends AbstractSamplesTest {

  @Override
  protected void checkSample(File sample) throws Exception {

    final Object lhs = createContext().createUnmarshaller().unmarshal(sample);
    final Object rhs = createContext().createUnmarshaller().unmarshal(sample);
    assertTrue(JAXBEqualsStrategy.INSTANCE.equals(null, null, lhs, rhs),
    	"Values must be equal.");
  }
}
