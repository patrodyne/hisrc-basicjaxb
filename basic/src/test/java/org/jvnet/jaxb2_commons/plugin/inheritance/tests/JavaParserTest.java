package org.jvnet.jaxb2_commons.plugin.inheritance.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import org.jvnet.jaxb2_commons.plugin.inheritance.util.JavaTypeParser;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;

public class JavaParserTest {

	@Test
	public void testParse() throws Exception {

		final JavaTypeParser javaTypeParser = new JavaTypeParser();
		final JCodeModel codeModel = new JCodeModel();
		JClass comparator = javaTypeParser.parseClass("java.util.Comparator",
				codeModel);
		assertNotNull(comparator);
		JClass integerComparator = javaTypeParser.parseClass(
				"java.util.Comparator<java.lang.Integer>", codeModel);
		assertNotNull(integerComparator);
		JClass wildcardIntegerComparator = javaTypeParser.parseClass(
				"java.util.Comparator<? extends java.lang.Integer>", codeModel);
		assertNotNull(wildcardIntegerComparator);
	}
}
