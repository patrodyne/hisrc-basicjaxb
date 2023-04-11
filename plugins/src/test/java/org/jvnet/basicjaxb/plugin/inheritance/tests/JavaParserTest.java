package org.jvnet.basicjaxb.plugin.inheritance.tests;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.jupiter.api.Test;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.ClassOrInterfaceDeclaration;
import japa.parser.ast.body.TypeDeclaration;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.ReferenceType;
import japa.parser.ast.type.Type;

/**
 * Test (explore) {@link JavaParser} behavior.
 */
public class JavaParserTest
{
	private static final String TEST_CLASS =
		"public class Dummy implements java.util.Comparator<java.lang.Integer>{}";
	
	@Test
	public void testParse() throws Exception
	{
		final ByteArrayInputStream inputStream = new ByteArrayInputStream(TEST_CLASS.getBytes(UTF_8));
		final CompilationUnit compilationUnit = JavaParser.parse(inputStream, UTF_8.name());
		final List<TypeDeclaration> typeDeclarations = compilationUnit.getTypes();
		assertEquals(1, typeDeclarations.size());
		
		final TypeDeclaration typeDeclaration = typeDeclarations.get(0);
		assertTrue(typeDeclaration instanceof ClassOrInterfaceDeclaration);
		
		final ClassOrInterfaceDeclaration classDeclaration = (ClassOrInterfaceDeclaration) typeDeclaration;
		assertEquals("Dummy", classDeclaration.getName());
		
		final List<ClassOrInterfaceType> implementedInterfaces = classDeclaration.getImplements();
		assertEquals(1, implementedInterfaces.size());
		
		final ClassOrInterfaceType implementedInterface = implementedInterfaces.get(0);
		assertEquals("Comparator", implementedInterface.getName());
		
		final List<Type> typeArgs = implementedInterface.getTypeArgs();
		assertEquals(1, typeArgs.size());
		
		final Type typeArg = typeArgs.get(0);
		assertTrue(typeArg instanceof ReferenceType);
		
		final ReferenceType referenceTypeArg = (ReferenceType) typeArg;
		final Type referencedType = referenceTypeArg.getType();
		assertTrue(referencedType instanceof ClassOrInterfaceType);
		
		final ClassOrInterfaceType typeArgType = (ClassOrInterfaceType) referencedType;
		assertEquals("Integer", typeArgType.getName());
	}
}
