package org.jvnet.basicjaxb.tests.issues;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import jakarta.xml.bind.annotation.XmlAnyElement;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@Order(2)
public class JIIB10Test
{
	@Test
	public void testXmlAnyElementLax()
		throws Exception
	{
		final Field contentField = IssueJIIB10Type.class.getDeclaredField("content");
		final XmlAnyElement xmlAnyElementAnnotation = contentField.getAnnotation(XmlAnyElement.class);
		assertTrue(xmlAnyElementAnnotation.lax());
	}
}
