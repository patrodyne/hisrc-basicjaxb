package org.jvnet.basicjaxb.xml.namespace.util.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.xml.namespace.QName;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.xml.namespace.util.QNameUtils;

public class QNameUtilsTest {

	@Test
	public void producesCorrectKey() {
		assertEquals(null, QNameUtils.toKey(null));
		assertEquals("a", QNameUtils.toKey(new QName("a")));
		assertEquals("{b}a", QNameUtils.toKey(new QName("b", "a")));
		assertEquals("{b}c:a", QNameUtils.toKey(new QName("b", "a", "c")));
		assertEquals("c:a", QNameUtils.toKey(new QName("", "a", "c")));
	}
}
