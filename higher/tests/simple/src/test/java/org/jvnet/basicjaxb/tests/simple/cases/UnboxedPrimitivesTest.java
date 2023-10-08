package org.jvnet.basicjaxb.tests.simple.cases;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UnboxedPrimitivesTest {

	@Test
	public void equalsPrimitives() {
		assertEquals(new UnboxedPrimitives(), new UnboxedPrimitives());
	}

}
