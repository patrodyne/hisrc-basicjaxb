package org.jvnet.basicjaxb.plugin.valueconstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JVar;
import com.sun.codemodel.writer.SingleStreamCodeWriter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Unit test for {@link ValueConstructorPlugin}.</p>
 */
public class ValueConstructorPluginTest
{
    private static Logger logger = LoggerFactory.getLogger(ValueConstructorPluginTest.class);
    protected static Logger getLogger() { return logger; }
	
	// Represent a model root of the Java code DOM. 
	private static JCodeModel aModel;

	// Represent a model for a declaration, and since a declaration can be always
	// used as a reference, it inherits {@link JClass}.
	private static JDefinedClass aClass;
	
	/**
	 * Model a CodeModel class and add some fields and methods.
	 * 
	 * @throws JClassAlreadyExistsException
	 */
	@BeforeAll
	public static void beforeAll() throws JClassAlreadyExistsException
	{
		// Create a model with a test package and a class.
		aModel = new JCodeModel();
		JPackage aPackage = aModel._package("test");
		aClass = aPackage._class("AClass");
		
		// Extend the class to a super class with a field of type double.
		JDefinedClass aSuperClass = aPackage._class("ASuperClass");
		aClass._extends(aSuperClass);
		aSuperClass.field(JMod.PRIVATE, aModel.DOUBLE, "superClassField");

		// Add a bean property of type int.
		JFieldVar aField = aClass.field(JMod.PRIVATE, aModel.INT, "field");
		JMethod aGetter = aClass.method(JMod.PUBLIC, aModel.INT, "getField");
		aGetter.body()._return(aField);
		JMethod aSetter = aClass.method(JMod.PUBLIC, aModel.VOID, "setField");
		final JVar setterParam = aSetter.param(aModel.INT, "field");
		aSetter.body().assign(aField, setterParam);
		
		// Add another private field of type boolean.
		aClass.field(JMod.PRIVATE, aModel.BOOLEAN, "anotherField");
		
		// Add a public static field of type short.
		aClass.field(JMod.STATIC | JMod.PUBLIC, aModel.SHORT, "staticField");
	}
	
	@Test
	public void testGenerateConstructors() throws IOException
	{
		logCodeModel("testGenerateConstructors BEFORE");
		
		int constructorCount = sizeOf(aClass.constructors());
		assertEquals(0, constructorCount);
		
		ValueConstructorPlugin plugin = new ValueConstructorPlugin();
		plugin.processDefinedClass(aClass);
		constructorCount = sizeOf(aClass.constructors());
		assertEquals(2, constructorCount);
		
		logCodeModel("testGenerateConstructors AFTER");
	}

	// Count the number of JMethods.
	private int sizeOf(Iterator<JMethod> iterator)
	{
		int size = 0;
		
		for (; iterator.hasNext(); ++size)
			iterator.next();
		
		return size;
	}

	// Log the output all source files into a single stream for human review.
	private void logCodeModel(String prefix)
		throws IOException
	{
		try ( ByteArrayOutputStream baos = new ByteArrayOutputStream() )
		{
			SingleStreamCodeWriter sscw = new SingleStreamCodeWriter(baos);
			aModel.build(sscw);
			getLogger().debug(prefix + ":\n" + baos.toString(StandardCharsets.UTF_8));
		}
	}
}
