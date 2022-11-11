package org.jvnet.basicjaxb.test.superclass.b.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.test.superclass.b.AnotherObjectType;

public class CopyToTest {

	@Test
	public void correctlyCopies() {
		final AnotherObjectType source = new AnotherObjectType();
		source.setId("Id");
		source.setData("Data");
		final AnotherObjectType target = (AnotherObjectType) source.clone();
		assertEquals("Id", target.getId());
		assertEquals("Data", target.getData());
		assertEquals(source, target);
		assertEquals(source.hashCode(), target.hashCode());
	}
}
