package org.jvnet.basicjaxb.tests.simple_hashcode_equals_01.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UnboxedPrimitivesTest {

	@Test
	public void equalsPrimitives() {
		assertEquals(new UnboxedPrimitives(), new UnboxedPrimitives());
	}

}