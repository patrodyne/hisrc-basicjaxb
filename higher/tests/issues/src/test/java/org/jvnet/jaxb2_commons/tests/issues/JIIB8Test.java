package org.jvnet.jaxb2_commons.tests.issues;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class JIIB8Test {

	@Test
	public void testCollectionSetters() throws Exception {

		final IssueJIIB8Type one = new IssueJIIB8Type();

		one.setValue(Arrays.asList("1", "2", "3"));

		assertEquals(3, one.getValue().size());

	}
}
