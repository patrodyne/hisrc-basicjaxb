package org.jvnet.basicjaxb.tests.issues;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;

@Order(2)
public class GH22Test
{
	@Test
	public void testJAXBEqualsSymmetryConcreteClassAndEnum()
	{
		SomeConcreteClass lhs = new SomeConcreteClass();
		SomeEnum rhs = SomeEnum.ENUM;
		
		DefaultRootObjectLocator lhsLocator = new DefaultRootObjectLocator(lhs);
		DefaultRootObjectLocator rhsLocator = new DefaultRootObjectLocator(rhs);

		JAXBEqualsStrategy strategy = JAXBEqualsStrategy.getInstance();
		
		assertFalse(strategy.equals(lhsLocator, rhsLocator, lhs, rhs, true, true));
	}

	@Test
	public void testJAXBEqualsSymmetryEnumAndConcreteClass()
	{
		SomeEnum lhs = SomeEnum.ENUM;
		SomeConcreteClass rhs = new SomeConcreteClass();
		
		DefaultRootObjectLocator lhsLocator = new DefaultRootObjectLocator(lhs);
		DefaultRootObjectLocator rhsLocator = new DefaultRootObjectLocator(rhs);
		
		JAXBEqualsStrategy strategy = JAXBEqualsStrategy.getInstance();
		
		assertFalse(strategy.equals(lhsLocator, rhsLocator, lhs, rhs, true, true));
	}

	private static class SomeConcreteClass
	{
	}

	private enum SomeEnum
	{
		ENUM;
	}
}