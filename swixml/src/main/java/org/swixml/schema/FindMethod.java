package org.swixml.schema;

import java.lang.reflect.Method;

import java.util.Vector;

/**
 * @author 
 * <a href="https://groups.google.com/g/comp.lang.java.programmer/c/khq5GGXIzC4">Jon Skeet</a>
 */
@SuppressWarnings("rawtypes")
public class FindMethod
{
	/**
	 * Finds the most specific applicable method
	 *
	 * @param source Class to find method in
	 * @param name Name of method to find
	 * @param parameterTypes Parameter types to search for
	 */
	public static Method findMethod(Class<?> source, String name, Class[] parameterTypes)
		throws NoSuchMethodException
	{
		return internalFind(source.getMethods(), name, parameterTypes);
	}

	/**
	 * Finds the most specific applicable declared method
	 *
	 * @param source Class to find method in
	 * @param name Name of method to find
	 * @param parameterTypes Parameter types to search for
	 */
	public static Method findDeclaredMethod(Class<?> source, String name, Class[] parameterTypes)
		throws NoSuchMethodException
	{
		return internalFind(source.getDeclaredMethods(), name, parameterTypes);
	}

	/**
	 * Internal method to find the most specific applicable method
	 */
	@SuppressWarnings("unchecked")
	private static Method internalFind(Method[] toTest, String name, Class[] parameterTypes)
		throws NoSuchMethodException
	{
		int l = parameterTypes.length;
		// First find the applicable methods
		Vector<Method> applicableMethods = new Vector<Method>();
		for ( int i = 0; i < toTest.length; i++ )
		{
			// Check the name matches
			if ( !toTest[i].getName().equals(name) )
				continue;
			// Check the parameters match
			Class[] params = toTest[i].getParameterTypes();
			if ( params.length != l )
				continue;
			int j;
			for ( j = 0; j < l; j++ )
				if ( !params[j].isAssignableFrom(parameterTypes[j]) )
					break;
			// If so, add it to the list
			if ( j == l )
				applicableMethods.add(toTest[i]);
		}
		/*
		 * If we've got one or zero methods, we can finish the job now.
		 */
		int size = applicableMethods.size();
		if ( size == 0 )
			throw new NoSuchMethodException("No such method: " + name);
		if ( size == 1 )
			return (Method) applicableMethods.elementAt(0);
		/*
		 * Now find the most specific method. Do this in a very primitive way -
		 * check whether each method is maximally specific. If more than one
		 * method is maximally specific, we'll throw an exception. For a
		 * definition of maximally specific, see JLS section 15.11.2.2.
		 *
		 * I'm sure there are much quicker ways - and I could probably set the
		 * second loop to be from i+1 to size. I'd rather not though, until I'm
		 * sure...
		 */
		int maximallySpecific = -1; // Index of maximally specific method
		for ( int i = 0; i < size; i++ )
		{
			int j;
			// In terms of the JLS, current is T
			Method current = (Method) applicableMethods.elementAt(i);
			Class[] currentParams = current.getParameterTypes();
			Class<?> currentDeclarer = current.getDeclaringClass();
			for ( j = 0; j < size; j++ )
			{
				if ( i == j )
					continue;
				// In terms of the JLS, test is U
				Method test = (Method) applicableMethods.elementAt(j);
				Class[] testParams = test.getParameterTypes();
				Class<?> testDeclarer = test.getDeclaringClass();
				// Check if T is a subclass of U, breaking if not
				if ( !testDeclarer.isAssignableFrom(currentDeclarer) )
					break;
				// Check if each parameter in T is a subclass of the
				// equivalent parameter in U
				int k;
				for ( k = 0; k < l; k++ )
					if ( !testParams[k].isAssignableFrom(currentParams[k]) )
						break;
				if ( k != l )
					break;
			}
			// Maximally specific!
			if ( j == size )
			{
				if ( maximallySpecific != -1 )
					throw new NoSuchMethodException(
						"Ambiguous method search - more " + "than one maximally specific method");
				maximallySpecific = i;
			}
		}
		if ( maximallySpecific == -1 )
			throw new NoSuchMethodException("No maximally specific method.");
		return (Method) applicableMethods.elementAt(maximallySpecific);
	}
}
