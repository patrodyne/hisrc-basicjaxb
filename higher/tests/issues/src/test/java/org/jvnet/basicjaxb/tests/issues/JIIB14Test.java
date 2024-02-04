package org.jvnet.basicjaxb.tests.issues;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;

@Order(2)
public class JIIB14Test
{
	@Test
	public void testIssueJIIB14()
		throws Exception
	{
		assertEquals(IssueJIIB14BaseClass.class, IssueJIIB14Element.class.getSuperclass());
		assertTrue(IssueJIIB14BaseInterfaceOne.class.isAssignableFrom(IssueJIIB14Element.class));
		assertTrue(IssueJIIB14BaseInterfaceTwo.class.isAssignableFrom(IssueJIIB14Element.class));
		assertTrue(IssueJIIB14BaseInterfaceThree.class.isAssignableFrom(IssueJIIB14JAXBElement.class));
		assertTrue(IssueJIIB14BaseInterfaceFour.class.isAssignableFrom(IssueJIIB14JAXBElement.class));
	}
}
