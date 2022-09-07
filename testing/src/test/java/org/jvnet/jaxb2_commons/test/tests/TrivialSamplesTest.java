package org.jvnet.jaxb2_commons.test.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.jaxb2_commons.test.AbstractSamplesTest;

public class TrivialSamplesTest extends AbstractSamplesTest {

  @Override
  protected void checkSample(File sample) throws Exception {
    assertTrue(sample.getName().endsWith(".xml"), "Wrong extension.");
  }

}
