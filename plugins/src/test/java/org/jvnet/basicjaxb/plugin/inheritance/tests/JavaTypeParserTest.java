package org.jvnet.basicjaxb.plugin.inheritance.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import org.jvnet.basicjaxb.plugin.inheritance.util.JavaTypeParser;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;

/**
 * Test (explore) {@link JavaTypeParser} behavior.
 */
public class JavaTypeParserTest
{
	private static final String TEST_CLASS1 = "java.util.Comparator";
	private static final String TEST_CLASS2 = "java.util.Comparator<java.lang.Integer>";
	private static final String TEST_CLASS3 = "java.util.Comparator<? extends java.lang.Integer>";

	@Test
	public void testParse() throws Exception
	{
		final JavaTypeParser javaTypeParser = new JavaTypeParser();
		final JCodeModel codeModel = new JCodeModel();
		
		JClass comparator = javaTypeParser.parseClass(TEST_CLASS1, codeModel);
		assertNotNull(comparator);
		
		JClass integerComparator = javaTypeParser.parseClass(TEST_CLASS2, codeModel);
		assertNotNull(integerComparator);
		
		JClass wildcardIntegerComparator = javaTypeParser.parseClass(TEST_CLASS3, codeModel);
		assertNotNull(wildcardIntegerComparator);
	}
}
