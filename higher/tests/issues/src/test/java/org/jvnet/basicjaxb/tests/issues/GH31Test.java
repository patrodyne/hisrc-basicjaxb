package org.jvnet.basicjaxb.tests.issues;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.lang.EnumValue;
import org.jvnet.basicjaxb.lang.JAXBToStringStrategy;
import org.jvnet.basicjaxb.lang.ToStringStrategy;
import org.jvnet.basicjaxb.locator.ObjectLocator;

@Order(2)
public class GH31Test
{
	@Test
	public void considersDefaultValuesInMerge()
	{
		final IssueGH31ComplexType t = new IssueGH31ComplexType();
		final ToStringStrategy s = new JAXBToStringStrategy()
		{
			@Override
			public boolean isUseIdentityHashCode()
			{
				return false;
			}

			@Override
			public StringBuilder append(ObjectLocator locator, StringBuilder buffer, Object value)
			{
				if ( value instanceof EnumValue<?> )
					return super.append(locator, buffer, ((EnumValue<?>) value).enumValue());
				else
					return super.append(locator, buffer, value);
			};
		};
		
		assertEquals("org.jvnet.basicjaxb.tests.issues.IssueGH31ComplexType[testEnum=Male(default)]",
			t.append(null, new StringBuilder(), s).toString());
	}
}
