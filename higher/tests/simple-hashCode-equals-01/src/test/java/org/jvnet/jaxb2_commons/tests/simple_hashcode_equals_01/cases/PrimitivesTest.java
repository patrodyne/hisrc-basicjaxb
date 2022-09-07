package org.jvnet.jaxb2_commons.tests.simple_hashcode_equals_01.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PrimitivesTest {

	@Test
	public void equalsPrimitives() {
		assertEquals(new Primitives(), new Primitives());
	}

}
