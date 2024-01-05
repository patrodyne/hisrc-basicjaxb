package org.jvnet.basicjaxb.testing;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

public class TrivialSamplesTest extends AbstractSamplesTest
{
  @Override
  protected void checkSample(File sample) throws Exception
  {
    assertTrue(sample.getName().endsWith(".xml"), "Wrong extension.");
  }
}
