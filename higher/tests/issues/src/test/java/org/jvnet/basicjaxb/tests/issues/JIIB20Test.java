package org.jvnet.basicjaxb.tests.issues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(2)
public class JIIB20Test {

	@Test
	public void testException() throws Exception {

		final List<String> strings = Arrays.asList("a", "b", "c");
		final IssueJIIB20 one = new IssueJIIB20();
		one.setStrings(strings);
		assertEquals(strings, one.getStrings());
		assertSame(strings, one.getStrings());

	}
}
