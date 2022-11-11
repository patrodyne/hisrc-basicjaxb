package org.jvnet.basicjaxb.tests.issues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.lang.JAXBToStringStrategy;
import org.jvnet.basicjaxb.lang.ToStringStrategy2;

public class GH37Test {

	@Test
	public void considersDefaultValuesInMerge() {
		final IssueGH37Type left = new IssueGH37Type();
		final IssueGH37Type right = new IssueGH37Type();
		final IssueGH37Type result = new IssueGH37Type();
		result.mergeFrom(left, right);
		assertTrue(result.isTestBoolean());
	}

	@Test
	public void considersDefaultValuesInCopyTo() {
		final IssueGH37Type original = new IssueGH37Type();
		final IssueGH37Type copy = (IssueGH37Type) original.clone();
		assertTrue(copy.isTestBoolean());
		assertFalse(copy.isSetTestBoolean());
		assertEquals(original, copy);
	}

	@Test
	public void considersDefaultValuesInToString() {
		final ToStringStrategy2 strategy = new JAXBToStringStrategy() {
			@Override
			public boolean isUseIdentityHashCode() {
				return false;
			}
		};
		IssueGH37Type a = new IssueGH37Type();
		assertEquals(
				"org.jvnet.basicjaxb.tests.issues.IssueGH37Type[testBoolean=true(default)]",
				a.append(null, new StringBuilder(), strategy).toString());
		IssueGH37Type b = new IssueGH37Type();
		b.setTestBoolean(true);
		assertEquals(
				"org.jvnet.basicjaxb.tests.issues.IssueGH37Type[testBoolean=true]",
				b.append(null, new StringBuilder(), strategy).toString());
	}

}
