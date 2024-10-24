package org.swixml.el;

import static jakarta.el.ELManager.getExpressionFactory;
import static java.lang.String.format;
import static java.lang.reflect.Modifier.isPublic;
import static java.lang.reflect.Modifier.isStatic;
import static org.swixml.jsr295.BindingUtils.getELMatcher;

import java.lang.reflect.Method;
import java.util.regex.Matcher;

import org.swixml.LogAware;
import org.swixml.dom.Attribute;

import jakarta.el.ELException;
import jakarta.el.ELManager;
import jakarta.el.ELProcessor;
import jakarta.el.ExpressionFactory;
import jakarta.el.MethodExpression;
import jakarta.el.StandardELContext;

/**
 * Expression Language Utility
 * 
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 */
public class ELUtility implements LogAware
{
	public final static String PREFIX = "el";

	// Sealed constructor
	protected ELUtility()
	{
	}
	
	/**
	 * Is the given attribute in the namespace prefixed for
	 * expression language.
	 * 
	 * A SWIXML attribute wraps a {@link org.w3c.dom.Attr}.
	 * 
	 * @param attr A SWIXML attribute (wraps {@link org.w3c.dom.Attr})
	 * 
	 * @return true, if attr is a "el" attribute.
	 */
	public static boolean isELAttribute( Attribute attr )
	{
		return PREFIX.equalsIgnoreCase(attr.getPrefix());
	}
	
   /**
	 * Evaluate a SWIXML attribute and return its value.
	 *	
	 * @param elProcessor An API for using Jakarta EL in a stand-alone environment.
	 * @param attr A SWIXML attribute (wraps {@link org.w3c.dom.Attr})
	 * 
	 * @return The attribute value
	 */
	public static Object evaluateAttribute( ELProcessor elProcessor, Attribute attr )
	{
		Object result = null;

		Matcher elMatcher = getELMatcher(attr.getValue());
		if ( elMatcher.matches() )
			result = evalSafe(elProcessor, elMatcher.group(1) );
		else
		{
			if ( isELAttribute(attr) )
				result = invokeFunctionSafe(elProcessor, attr.getValue());
			else
				result = attr.getValue();
		}

		return result;
	}
	
	/**
	 * Load declared, static, public methods from the given class.
	 * 
	 * <p>Add static methods that can be used as EL functions.</p>
	 * 
	 * @param elProcessor An API for using Jakarta EL in a stand-alone environment.
	 * @param clazz The class providing methods to be mapped to EL functions.
	 */
	public static void loadStaticMethods(ELProcessor elProcessor, Class<?> clazz)
	{
		for ( Method dm : clazz.getDeclaredMethods() )
		{
			if ( isStatic(dm.getModifiers()) && isPublic(dm.getModifiers())
				&& (dm.getReturnType() != Void.TYPE) )
			{
				String prefix = clazz.getSimpleName();
				String localname = dm.getName();
				StandardELContext elContext = elProcessor.getELManager().getELContext();
				elContext.getFunctionMapper().mapFunction(prefix, localname, dm);
			}
		}
	}
	
	/**
	 * EL method invocation and safely ignoring any exception, safely.
	 * 
	 * <p>EL method example: <code>"#{myprefix:hello('Dave')}"</code></p>
	 * 
	 * @param elProcessor An API for using Jakarta EL in a stand-alone environment.
	 * @param elMethod An EL method name.
	 * @param args An EL method arguments.
	 * 
	 * @return The function return object.
	 */
	public static Object invokeFunctionSafe( ELProcessor elProcessor, String elMethod, Object... args )
	{
		Object result = null;
		try
		{
			// Derive types from args.
			Class<?>[] types = new Class<?>[args.length];
			for ( int index=0; index < args.length; ++index )
				types[index] = args[index].getClass();
			
			// Create and invoke a MethodExpression
			ExpressionFactory expressionFactory = getExpressionFactory();
			StandardELContext elContext = elProcessor.getELManager().getELContext();
			MethodExpression me = expressionFactory
				.createMethodExpression(elContext, elMethod, Object.class, types);
			result = me.invoke(elContext, args);
		}
		catch (Exception ex)
		{
			logger.warn(String.format( "invocation of method [%s] has failed!", elMethod), ex);
		}
		return result;
	}
	
	/**
	 * EL method invocation and escalate any exception.
	 * 
	 * <p>EL method example: <code>"#{myprefix:hello('Dave')}"</code></p>
	 * 
	 * @param elProcessor An API for using Jakarta EL in a stand-alone environment.
	 * @param elMethod An EL method name.
	 * @param args An EL method arguments.
	 * 
	 * @return The function return object.
	 * 
	 * @throws ELException When evaluation of expression has failed.
	 */
	public static Object invokeFunction( ELProcessor elProcessor, String elMethod, Object... args )
		throws ELException
	{
		Object result = null;
		try
		{
			// Derive types from args.
			Class<?>[] types = new Class<?>[args.length];
			for ( int index=0; index < args.length; ++index )
				types[index] = args[index].getClass();
			
			// Create and invoke a MethodExpression
			ExpressionFactory expressionFactory = ELManager.getExpressionFactory();
			StandardELContext elContext = elProcessor.getELManager().getELContext();
			MethodExpression me = expressionFactory
				.createMethodExpression(elContext, elMethod, Object.class, types);
			result = me.invoke(elContext, args);
		}
		catch (Exception ex)
		{
			throw new ELException(String.format( "invocation of method [%s] has failed!", elMethod), ex);
		}
		return result;
	}
	
	/**
	 * Evaluates an Jakarta Expression Language expression.
	 * 
	 * @param elProcessor An API for using Jakarta EL in a stand-alone environment.
	 * @param expression The Jakarta EL expression to be evaluated.
	 * 
	 * @return The result of the expression evaluation.
	 */
	public static Object evalSafe(ELProcessor elProcessor, String expression)
	{
		Object result = null;
		try
		{
			result = elProcessor.eval(expression);
		}
		catch (Exception ex)
		{
			logger.warn(format( "evaluation of expression [%s] has failed!", expression), ex);
		}
		return result;
	}
	
	/**
	 * Evaluates an Jakarta Expression Language expression.
	 * 
	 * @param elProcessor An API for using Jakarta EL in a stand-alone environment.
	 * @param expression The Jakarta EL expression to be evaluated.
	 * 
	 * @return The result of the expression evaluation.
	 * 
	 * @throws ELException When evaluation of expression has failed.
	 */
	public static Object eval(ELProcessor elProcessor, String expression)
	{
		Object result = null;
		try
		{
			result = elProcessor.eval(expression);
		}
		catch (Exception ex)
		{
			throw new ELException(format( "evaluation of expression [%s] has failed!", expression), ex);
		}
		return result;
	}
}
