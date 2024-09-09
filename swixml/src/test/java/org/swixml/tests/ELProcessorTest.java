package org.swixml.tests;

import static java.lang.Boolean.parseBoolean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.el.BeanNameResolver;
import jakarta.el.ELManager;
import jakarta.el.ELProcessor;
import jakarta.el.LambdaExpression;

/**
 * Unit tests for {@link ELProcessor} processing of expression language syntax.
 * 
 * @see <a href="https://www.slideserve.com/mirit/what-s-new-with-bean-validation-and-expression-language-in-java-ee-7">SlideServe</a>
 */
public class ELProcessorTest
{
	private static ELProcessor elProcessor;
	public static ELProcessor getELProcessor()
	{
		if ( elProcessor == null )
			setELProcessor(new ELProcessor());
		return elProcessor;
	}
	public static void setELProcessor(ELProcessor elProcessor)
	{
		ELProcessorTest.elProcessor = elProcessor;
	}

	@BeforeEach
	protected void setUp()
		throws Exception
	{
		setELProcessor(new ELProcessor());
	}

	@AfterEach
	protected void tearDown()
		throws Exception
	{
		setELProcessor(null);
	}

	@Test
	protected void testDirectVariableExpression()
	{
		getELProcessor().setVariable("x", "123");
		getELProcessor().setVariable("y", "456");
		
		// Evaluation adds bracket '${}' auto-magically.
		Object result = getELProcessor().eval("x+y");
		assertNotNull(result, "direct variable expression eval");
		
		if ( result instanceof Long )
		{
			Long value = (Long) result;
			assertEquals(579, value, "direct variable expression equals");
		}
		else
			fail("expected Long result");
	}
	
	@Test
	protected void testStaticFunction1() throws ClassNotFoundException, NoSuchMethodException
	{
		getELProcessor().defineFunction("test", "sayHelloWorld", User.class.getName(), "sayHelloWorld");
		
		Object result = getELProcessor().eval("test:sayHelloWorld()");
		assertNotNull(result, "static function eval");
		
		if ( result instanceof String )
		{
			String value = (String) result;
			assertEquals("Hello, World", value, "static function eval equals");
		}
		else
			fail("expected String result");
	}
	
	@Test
	protected void testStaticFunction2() throws ClassNotFoundException, NoSuchMethodException
	{
        Method sayHello = User.class.getMethod("sayHello", new Class[] { String.class });
		getELProcessor().defineFunction("test", "sayHello", sayHello);
		
		Object result = getELProcessor().eval("test:sayHello('Earth')");
		assertNotNull(result, "static function eval arg");
		
		if ( result instanceof String )
		{
			String value = (String) result;
			assertEquals("Hello, Earth", value, "static function eval arg equals");
		}
		else
			fail("expected String result");
	}

	@Test
	protected void testDirectPropertyExpression()
	{
		// Direct property expression evaluation
		User user = new User();
		user.setName("Arthur");
		
		getELProcessor().defineBean("user", user);
		Object result = getELProcessor().eval("'Welcome, ' += user.name");
		assertNotNull(result, "direct property expression eval");
		
		if ( result instanceof String )
		{
			String value = (String) result;
			assertEquals("Welcome, Arthur", value, "direct property expression eval equals");
		}
		else
			fail("expected String result");
	}

	@Test
	protected void testDirectMethodExpression()
	{
		// Direct method expression evaluation
		getELProcessor().defineBean("user", new User());
		Object result = getELProcessor()
			.eval("'Welcome, ' += user.shout('Arthur') += ' (' += user.name += ')'");
		assertNotNull(result, "direct method expression eval");
		
		if ( result instanceof String )
		{
			String value = (String) result;
			assertEquals("Welcome, ARTHUR (Arthur)", value, "direct method expression eval equals");
		}
		else
			fail("expected String result");
	}
	
	@Test
	protected void testBeanNameResolver()
	{
		// Bean name resolver evaluation
		ELManager elm = getELProcessor().getELManager();
		elm.addBeanNameResolver
		(
			new BeanNameResolver()
			{
				@Override public Object getBean(String nameIn)
				{
					// WARN: Does not appear to be called?
					String nameOut = null;
					switch ( nameIn )
					{
						case "usuario":
							nameOut = "user";
							break;
						default:
							nameOut = "user";
							break;
					}
					return nameOut;
				}
			}
		);
		
		// Direct property expression evaluation
		User user = new User();
		user.setName("Arthur");
		
		// "usuario" -> "user": Pass
		// "user" -> "usuario": Fail
		getELProcessor().defineBean("usuario", user);
		Object result = getELProcessor().eval("'Welcome, ' += usuario.name");
		assertNotNull(result, "bean name resolver eval");
		assertEquals("Welcome, Arthur", result, "bean name resolver eval equals");
	}

	@Test
	protected void testResolverSetValue()
	{
		User user = new User();
		user.setName("Arthur");
		getELProcessor().defineBean("user", user);
		// ELResolver set value: xx
		// Attempts to set the value of the given property
		// object on the given base (elResolveMap ??) object.
		Object result = null;
		result = getELProcessor().eval("xx = user.name");
		result = getELProcessor().eval("'Welcome, ' += xx");
		assertNotNull(result, "resolver set value eval");
		assertEquals("Welcome, Arthur", result, "resolver set value eval equals");
	}
	
	@Test
	protected void testSideEffect()
	{
		User user = new User();
		user.setName("Arthur");
		getELProcessor().defineBean("user", user);
		Object result = getELProcessor().eval("xx = user.name; 'Welcome, ' += xx");
		assertEquals("Welcome, Arthur", result, "resolver side effect eval equals");
	}
	
	@Test
	protected void testPreImport()
	{
		// package 'java.lang' is pre-imported
		Object result = null;
		result = getELProcessor().eval("'xx = ' += Boolean.TRUE");
		assertNotNull(result, "process pre-import eval");
		
		result = getELProcessor().eval("yy = Integer.numberOfTrailingZeros(16); 'yy = ' += yy");
		assertNotNull(result, "process pre-import eval");
	}
	
	/**
	 * @see <a href="https://docs.oracle.com/javaee/7/tutorial/jsf-el.htm>Java EE Tutorial: EL</a>
	 */
	@SuppressWarnings("unchecked")
	@Test
	protected void testLambdaExpression01()
	{
		LambdaExpression le = (LambdaExpression) getELProcessor().eval("x -> x+1");
		Object result = le.invoke(getELProcessor().getELManager().getELContext(), 10);
		assertNotNull(result, "programmatic lambda expression invocation");
		
		result = getELProcessor().eval("[1,2,3].stream().map(x->x+1).toList()");
		assertNotNull(result, "programmatic lambda expression invocation");
		assertTrue(result instanceof List);
		for ( Long value : (List<Long>) result )
			assertTrue((value == 2) || (value == 3) || (value == 4));
		
		assertFalse(parseBoolean(getELProcessor().eval("1 > (4/2)").toString()));
		assertFalse(parseBoolean(getELProcessor().eval("(10*10) ne 100").toString()));
		assertFalse(parseBoolean(getELProcessor().eval("'a' > 'b'").toString()));
		
		assertTrue(parseBoolean(getELProcessor().eval("4.0 >= 3").toString()));
		assertTrue(parseBoolean(getELProcessor().eval("100.0 == 100").toString()));
		assertTrue(parseBoolean(getELProcessor().eval("'hip' lt 'hit'").toString()));
		assertTrue(parseBoolean(getELProcessor().eval("4 > 3").toString()));
		
		assertEquals(getELProcessor().eval("1.2E4 + 1.4").toString(), "12001.4");
		assertEquals(getELProcessor().eval("3 div 4").toString(), "0.75");
		assertEquals(getELProcessor().eval("10 mod 4").toString(), "2");
		assertEquals(getELProcessor().eval("((x, y) -> x + y)(3, 5.5)").toString(), "8.5");
		assertEquals(getELProcessor().eval("[1,2,3,4].stream().sum()").toString(), "10");
		assertEquals(getELProcessor().eval("[1,3,5,2].stream().sorted().toList()").toString(), "[1, 2, 3, 5]");
		
		result = getELProcessor().eval("['item1','item2','item3'].stream().toList()");
		assertEquals("[item1, item2, item3]", result.toString() );
	}
	
}
