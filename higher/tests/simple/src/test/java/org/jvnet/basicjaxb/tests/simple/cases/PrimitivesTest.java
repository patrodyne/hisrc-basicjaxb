package org.jvnet.basicjaxb.tests.simple.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PrimitivesTest {

	@Test
	public void equalsPrimitives() {
		assertEquals(new Primitives(), new Primitives());
	}

}
