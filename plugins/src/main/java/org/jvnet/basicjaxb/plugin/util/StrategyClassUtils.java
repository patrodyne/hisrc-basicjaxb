package org.jvnet.basicjaxb.plugin.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.util.JClassUtils;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CClassRef;
import com.sun.tools.xjc.outline.ClassOutline;

public class StrategyClassUtils
{
	public static <T> JExpression createStrategyInstanceExpression(JCodeModel codeModel,
		final Class<? extends T> strategyInterface, final String strategyClassName)
	{
		try
		{
			final Class<?> strategyClass = Class.forName(strategyClassName);
			final JClass strategyJClass = codeModel.ref(strategyClass);
			try
			{
				final Method getInstanceMethod = strategyClass.getMethod("getInstance", new Class<?>[0]);
				if (getInstanceMethod != null	&& strategyInterface.isAssignableFrom(getInstanceMethod.getReturnType())
					&& Modifier.isStatic(getInstanceMethod.getModifiers())
					&& Modifier.isPublic(getInstanceMethod.getModifiers()))
				{
					return strategyJClass.staticInvoke("getInstance");
				}
			}
			catch (Exception ignored)
			{
				// Nothing to do
			}
			try
			{
				final Field instance2Field = strategyClass.getField("INSTANCE");
				if (instance2Field != null	&& strategyInterface.isAssignableFrom(instance2Field.getType())
					&& Modifier.isStatic(instance2Field.getModifiers())
					&& Modifier.isPublic(instance2Field.getModifiers()))
				{
					return strategyJClass.staticRef("INSTANCE");
				}
			}
			catch (Exception ignored)
			{
				// Nothing to do
			}
			return JExpr._new(strategyJClass);
		}
		catch (ClassNotFoundException cnfex)
		{
			final JClass strategyJClass = codeModel.ref(strategyClassName);
			return JExpr._new(strategyJClass);
		}
	}

	public static <T> Boolean superClassImplements(ClassOutline classOutline, Ignoring ignoring,
		Class<? extends T> theInterface, Boolean unknown)
	{
		Boolean sci = superClassImplements(classOutline, ignoring, theInterface);
		return ( sci != null ) ? sci : unknown;
	}
	
	/**
	 * Determine if a superclass of the given {@link ClassOutline} is a 
	 * non-ignored implementation of the given interface.
	 * 
	 * @param <T> The interface type.
	 * @param classOutline The {@link ClassOutline} to examine.
	 * @param ignoring Interface to filter out ignored classes.
	 * @param theInterface The interface to verify.
	 * 
	 * @return True when a superclass is a non-ignored implementation of the given interface;
	 *         otherwise, false or null.
	 */
	public static <T> Boolean superClassImplements(ClassOutline classOutline, Ignoring ignoring,
		Class<? extends T> theInterface)
	{
		// If there is a implementation superclass.
		if ( (classOutline.implClass != null) && (classOutline.implClass._extends() != null) )
		{
			// The JClass representing the superclass of the entity.
			JClass implSuperclass = classOutline.implClass._extends();
			// Determine if the given {@link JClass} is an instance of the given interface.
			if ( JClassUtils.isInstanceOf(implSuperclass, theInterface) )
				return Boolean.TRUE;
		}
		
		// Examine the base class info of the current target class info.
		CClassInfo baseClassInfo = classOutline.target.getBaseClass();
		if ( baseClassInfo != null )
		{
			ClassOutline baseClassOutline = classOutline.parent().getClazz(baseClassInfo);
			if ( ignoring.isIgnored(baseClassOutline) )
				return Boolean.FALSE;
			else
				return Boolean.TRUE;
		}
		
		// Examine the Reference to an existing base class info of the current target class info.
		CClassRef refBaseClassInfo = classOutline.target.getRefBaseClass();
		if ( refBaseClassInfo != null )
		{
			try
			{
				// Load the existing base class to determines if it is either the same as,
				// or is a superclass or super-interface of, the class or interface represented
				// by the specified Class parameter. 
				if ( theInterface.isAssignableFrom(Class.forName(refBaseClassInfo.fullName())) )
					return Boolean.TRUE;
				else
					return Boolean.FALSE;
			}
			catch (ClassNotFoundException ignored)
			{
				// If the base class exists in this project's sources then it will
				// not be on the classpath during the 'generate-sources' phase.
				// Returning false will cause the caller to implement the desired
				// interface on the current classOutline which is better than not
				// implementing it at all; thus, we'll assume it does not implement.
				return Boolean.FALSE;
			}
		}
		
		// Unknown
		return null;
	}

	public static <T> Boolean superClassNotIgnored(ClassOutline classOutline, Ignoring ignoring)
	{
		// Examine the base class info of the current target class info.
		CClassInfo baseClassInfo = classOutline.target.getBaseClass();
		
		// Examine the Reference to an existing base class info of the current target class info.
		CClassRef refBaseClassInfo = classOutline.target.getRefBaseClass();
		
		if ( baseClassInfo != null )
		{
			if ( ignoring.isIgnored(classOutline.parent().getClazz(baseClassInfo)) )
				return Boolean.FALSE;
			else
				return Boolean.TRUE;
		}
		else if ( refBaseClassInfo != null )
			return Boolean.TRUE;
		else
			return null;
	}
}
