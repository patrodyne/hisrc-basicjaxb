package org.jvnet.basicjaxb.tests.issues;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JIIB38Test {

	@Test
	public void testException() throws Exception {

		final IssueJIIB38 one = new IssueJIIB38();
		final IssueJIIB38Type two = IssueJIIB38Type.A;

		assertTrue(one instanceof Cloneable);
		assertTrue(two instanceof Cloneable);

	}
}
