package org.jvnet.basicjaxb.tests.issues;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.lang.EnumValue;
import org.jvnet.basicjaxb.lang.JAXBToStringStrategy;
import org.jvnet.basicjaxb.lang.ToStringStrategy2;
import org.jvnet.basicjaxb.locator.ObjectLocator;

public class GH31Test {

	@Test
	public void considersDefaultValuesInMerge() {
		final IssueGH31ComplexType t = new IssueGH31ComplexType();

		final ToStringStrategy2 s = new JAXBToStringStrategy() {
			public boolean isUseIdentityHashCode() {
				return false;
			}

			public StringBuilder append(ObjectLocator locator,
					StringBuilder buffer, Object value) {

				if (value instanceof EnumValue<?>) {
					return super.append(locator, buffer,
							((EnumValue<?>) value).enumValue());
				} else {
					return super.append(locator, buffer, value);
				}
			};
		};
		assertEquals(
				"org.jvnet.basicjaxb.tests.issues.IssueGH31ComplexType[testEnum=Male(default)]",
				t.append(null, new StringBuilder(), s).toString());
	}
}
