package org.jvnet.basicjaxb.util;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Iterator;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JType;

/**
 * Utility methods for JClass instances.
 */
public class JClassUtils
{
	/**
	 * Determine if the given {@link JClass} is an instance of the given interface.
	 * 
	 * @param <T> The interface type.
	 * @param _class The {@link JClass} to examine.
	 * @param _interface The interface to verify.
	 * 
	 * @return True when the given {@link JClass} is an instance of the given interface;
	 *         otherwise, false.
	 */
	public static <T> boolean isInstanceOf(JClass _class, Class<? extends T> _interface)
	{
		// Require the JClass and the interface.
		requireNonNull(_class);
		requireNonNull(_interface);
		
		final String className = _class.fullName();
		
		try
		{
			// Load the Class object from the class name and determine if the class 
			// represented by the object is either the same as, or is a superclass
			// of the class represented by the specified Class parameter. 
			if ( _interface.isAssignableFrom(Class.forName(className)) )
				return true;
		}
		catch (ClassNotFoundException cnfex)
		{
			// Not yet generated.
		}
		
		// Is the defined superclass an instance of the interface?
		final JClass superClass = _class._extends();
		if ( superClass != null )
		{
			if ( isInstanceOf(superClass, _interface) )
				return true;
		}
		
		// Iterate over the list of defined implementations to determine if any
		// implementation is an instance of the given interface.
		for (final Iterator<? extends JClass> implementsIterator = _class._implements(); implementsIterator.hasNext();)
		{
			final JClass superInterface = implementsIterator.next();
			if ( isInstanceOf(superInterface, _interface) )
				return true;
		}
		
		// The given JClass is not an instance of the given interface.
		return false;
	}
	
	/**
	 * Does the given defined class have the given method signature.
	 * 
	 * @param theClass The defined class to examine.
	 * @param methodName The method name.
	 * @param parmTypeNames The method's parameter type names.
	 * 
	 * @return True when the given defined class contains the given method signature; otherwise, false.
	 */
	public static boolean classHasMethod(JDefinedClass theClass, String methodName, String[] parmTypeNames)
	{
		boolean baseClassHasMethod = false;
		for ( JMethod method : theClass.methods() )
		{
			if ( methodName.equals(method.name()) )
			{
				if ( method.params().size() == parmTypeNames.length )
				{
					for ( int index = 0; index < parmTypeNames.length; ++index )
					{
						JType paramType = method.params().get(index).type();
						if ( paramType.binaryName().equals(parmTypeNames[index]) )
						{
							baseClassHasMethod = true;
							break;
						}
					}
					
				}
			}
		}
		return baseClassHasMethod;
	}

	/**
	 * Does the given class have the given method signature.
	 * 
	 * @param theClass The class to examine.
	 * @param methodName The method name.
	 * @param parmTypeNames The method's parameter type names.
	 * 
	 * @return True when the given class contains the given method signature; otherwise, false.
	 */
	public static boolean classHasMethod(Class<?> theClass, String methodName, String[] parmTypeNames)
	{
		boolean baseClassHasMethod = false;
		for ( Method method : theClass.getMethods() )
		{
			if ( methodName.equals(method.getName()) )
			{
				if ( method.getParameterCount() == parmTypeNames.length )
				{
					for ( int index = 0; index < parmTypeNames.length; ++index )
					{
						Type paramType = method.getParameters()[index].getParameterizedType();
						if ( paramType.getTypeName().equals(parmTypeNames[index]) )
						{
							baseClassHasMethod = true;
							break;
						}
					}
					
				}
			}
		}
		return baseClassHasMethod;
	}

}
