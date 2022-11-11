package org.jvnet.basicjaxb.tests.issues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class JIIB36Test {

	@Test
	public void runsWithoutException() {

		final IssueJIIB36 one = new IssueJIIB36();
		final IssueJIIB36 two = new IssueJIIB36();

		assertTrue(one.equals(two));
		assertTrue(two.equals(one));
		assertEquals(one.hashCode(), two.hashCode());

	}
}
