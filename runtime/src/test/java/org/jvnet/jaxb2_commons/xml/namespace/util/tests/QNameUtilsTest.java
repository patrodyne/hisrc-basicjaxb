package org.jvnet.jaxb2_commons.xml.namespace.util.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.xml.namespace.QName;

import org.junit.jupiter.api.Test;
import org.jvnet.jaxb2_commons.xml.namespace.util.QNameUtils;

public class QNameUtilsTest {

	@Test
	public void producesCorrectKey() {
		assertEquals(null, QNameUtils.getKey(null));
		assertEquals("a", QNameUtils.getKey(new QName("a")));
		assertEquals("{b}a", QNameUtils.getKey(new QName("b", "a")));
		assertEquals("{b}c:a", QNameUtils.getKey(new QName("b", "a", "c")));
		assertEquals("c:a", QNameUtils.getKey(new QName("", "a", "c")));
	}
}
