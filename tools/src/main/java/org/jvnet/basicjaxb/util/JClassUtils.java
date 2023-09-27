package org.jvnet.basicjaxb.util;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import com.sun.codemodel.JClass;

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
}
