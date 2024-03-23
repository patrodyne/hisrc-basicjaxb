package org.jvnet.basicjaxb.plugin.inheritance.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.plugin.inheritance.util.JavaTypeParser;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;

/**
 * Test (explore) {@link JavaTypeParser} behavior.
 */
public class JavaTypeParserTest
{
	private static final JavaTypeParser JAVA_TYPE_PARSER = new JavaTypeParser();
	private static final JCodeModel CODE_MODEL = new JCodeModel();
	
	private static final String TEST_CLASS1 = "java.util.Comparator";
	private static final String TEST_CLASS2 = "java.util.Comparator<java.lang.Integer>";
	private static final String TEST_CLASS3 = "java.util.Comparator<? extends java.lang.Integer>";
	private static final String TEST_CLASS4 = "java.lang.Number";
	private static final String TEST_CLASS5 = "java.lang.Float";
	private static final String TEST_CLASS6 = "java.util.List<String>";
	private static final String TEST_CLASS7 = "java.util.ArrayList<String>";
	private static final String TEST_CLASS8 = "java.util.Map<String, String>";
	private static final String TEST_CLASS9 = "java.util.HashMap<String, String>";

	@Test
	public void testParse() throws Exception
	{
		JClass comparator = JAVA_TYPE_PARSER.parseClass(TEST_CLASS1, CODE_MODEL);
		assertNotNull(comparator);
		
		JClass integerComparator = JAVA_TYPE_PARSER.parseClass(TEST_CLASS2, CODE_MODEL);
		assertNotNull(integerComparator);
		
		JClass wildcardIntegerComparator = JAVA_TYPE_PARSER.parseClass(TEST_CLASS3, CODE_MODEL);
		assertNotNull(wildcardIntegerComparator);
	}

	@Test
	public void testIsAssignableFromFloat() throws Exception
	{
		// Determines if the class or interface represented by this Class object
		// is either the same as, or is a superclass or superinterface of, the
		// class or interface represented by the specified Class parameter.
		boolean assignable = false;
		
		// Double <- Float
		
		Class<?> clNumber = Class.forName(TEST_CLASS4);
		assertNotNull(clNumber);
		
		Class<?> clFloat = Class.forName(TEST_CLASS5);
		assertNotNull(clFloat);
		
		assignable = clNumber.isAssignableFrom(clFloat);
		assertTrue(assignable);
		
		JClass jcNumber = JAVA_TYPE_PARSER.parseClass(TEST_CLASS4, CODE_MODEL);
		assertNotNull(jcNumber);
		
		JClass jcFloat = JAVA_TYPE_PARSER.parseClass(TEST_CLASS5, CODE_MODEL);
		assertNotNull(jcFloat);
		
		assignable = jcNumber.isAssignableFrom(jcFloat);
		assertTrue(assignable);
	}
	
	@Test
	public void testIsAssignableFromArrayList() throws Exception
	{
		// Determines if the class or interface represented by this Class object
		// is either the same as, or is a superclass or superinterface of, the
		// class or interface represented by the specified Class parameter.
		boolean assignable = false;
		
		// List <- ArrayList
		
		Class<?> clList = Class.forName(erasure(TEST_CLASS6));
		assertNotNull(clList);
		
		Class<?> clArrayList = Class.forName(erasure(TEST_CLASS7));
		assertNotNull(clArrayList);
		
		assignable = clList.isAssignableFrom(clArrayList);
		assertTrue(assignable);
		
		JClass jcList = JAVA_TYPE_PARSER.parseClass(TEST_CLASS6, CODE_MODEL);
		assertNotNull(jcList);
		
		JClass jcArrayList = JAVA_TYPE_PARSER.parseClass(TEST_CLASS7, CODE_MODEL);
		assertNotNull(jcArrayList);
		
		assignable = jcList.isAssignableFrom(jcArrayList.erasure());
		assertFalse(assignable);
		
		assignable = jcList.erasure().isAssignableFrom(jcArrayList);
		assertTrue(assignable);
		
		assignable = jcList.erasure().isAssignableFrom(jcArrayList.erasure());
		assertTrue(assignable);
	}
	
	@Test
	public void testIsAssignableFromHashMap() throws Exception
	{
		// Determines if the class or interface represented by this Class object
		// is either the same as, or is a superclass or superinterface of, the
		// class or interface represented by the specified Class parameter.
		boolean assignable = false;
		
		// Map <- HashMap
		
		Class<?> clMap = Class.forName(erasure(TEST_CLASS8));
		assertNotNull(clMap);
		
		Class<?> clHashMap = Class.forName(erasure(TEST_CLASS9));
		assertNotNull(clHashMap);
		
		assignable = clMap.isAssignableFrom(clHashMap);
		assertTrue(assignable);
		
		JClass jcMap = JAVA_TYPE_PARSER.parseClass(TEST_CLASS8, CODE_MODEL);
		assertNotNull(jcMap);
		
		JClass jcHashMap = JAVA_TYPE_PARSER.parseClass(TEST_CLASS9, CODE_MODEL);
		assertNotNull(jcHashMap);
		
		assignable = jcMap.isAssignableFrom(jcHashMap.erasure());
		assertFalse(assignable);
		
		assignable = jcMap.erasure().isAssignableFrom(jcHashMap);
		assertTrue(assignable);
		
		assignable = jcMap.erasure().isAssignableFrom(jcHashMap.erasure());
		assertTrue(assignable);
	}

	private String erasure(String className)
	{
		return className.substring(0, className.indexOf('<'));
	}
}
