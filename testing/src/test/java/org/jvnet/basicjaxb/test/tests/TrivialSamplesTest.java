package org.jvnet.basicjaxb.test.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.jvnet.basicjaxb.test.AbstractSamplesTest;

public class TrivialSamplesTest extends AbstractSamplesTest {

  @Override
  protected void checkSample(File sample) throws Exception {
    assertTrue(sample.getName().endsWith(".xml"), "Wrong extension.");
  }

}
