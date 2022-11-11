package org.jvnet.basicjaxb.tests.issues;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;

public class GH22Test {
	
	@Test
	public void testJAXBEqualsSymmetryConcreteClassAndEnum() {
		assertFalse(JAXBEqualsStrategy.getInstance().equals(null, null,
				new SomeConcreteClass(), SomeEnum.ENUM));
	}

	@Test
	public void testJAXBEqualsSymmetryEnumAndConcreteClass() {
		// This test fails and throws a ClassCastException
		assertFalse(JAXBEqualsStrategy.getInstance().equals(null, null,
				SomeEnum.ENUM, new SomeConcreteClass()));
	}

	private static class SomeConcreteClass {
	}

	private enum SomeEnum {
		ENUM;
	}
}