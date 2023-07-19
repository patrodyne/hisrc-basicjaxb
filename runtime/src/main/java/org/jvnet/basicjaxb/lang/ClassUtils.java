package org.jvnet.basicjaxb.lang;

import static java.lang.Class.forName;
import static java.lang.ClassLoader.getSystemClassLoader;
import static java.util.stream.Collectors.toSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

public class ClassUtils
{
	public static final char PACKAGE_SEPARATOR_CHAR = '.';
	public static final char INNER_CLASS_SEPARATOR_CHAR = '$';

	private ClassUtils()
	{
	}

	/**
	 * <p>
	 * Gets the class name minus the package name from a <code>Class</code>.
	 * </p>
	 * 
	 * @param cls the class to get the short name for.
	 * @return the class name without the package name or an empty string
	 */
	public static String getShortClassName(@SuppressWarnings("rawtypes") Class cls)
	{
		if (cls == null)
			return "";
		else
			return getShortClassName(cls.getName());
	}

	/**
	 * <p>
	 * Gets the class name minus the package name from a String.
	 * </p>
	 * 
	 * <p>
	 * The string passed in is assumed to be a class name - it is not checked.
	 * </p>
	 * 
	 * @param className the className to get the short name for
	 * @return the class name of the class without the package name or an empty string
	 */
	public static String getShortClassName(String className)
	{
		if (className == null)
			return "";
		
		if (className.length() == 0)
			return "";
		
		char[] chars = className.toCharArray();
		int lastDot = 0;
		for (int i = 0; i < chars.length; i++)
		{
			if (chars[i] == PACKAGE_SEPARATOR_CHAR)
				lastDot = i + 1;
			else if (chars[i] == INNER_CLASS_SEPARATOR_CHAR)
			{ 
				// handle inner classes
				chars[i] = PACKAGE_SEPARATOR_CHAR;
			}
		}
		return new String(chars, lastDot, chars.length - lastDot);
	}
	
	public static Set<Class<?>> findClasses(Package pkg) throws IOException
	{
		String pkgName = pkg.getName();
		try ( InputStream stream = getSystemClassLoader().getResourceAsStream(pkgName.replaceAll("[.]", "/")) )
		{
			try ( BufferedReader reader = new BufferedReader(new InputStreamReader(stream)) )
			{
				return reader.lines()
					.filter(line -> line.endsWith(".class")).map(line -> getClass(line, pkgName)).collect(toSet());
			}
		}
	}
	
	private static Class<?> getClass(String className, String pkgName)
	{
		try
		{
			return forName(pkgName + "." + className.substring(0, className.lastIndexOf('.')));
		}
		catch (ClassNotFoundException e)
		{
			// handle the exception
			return null;
		}
	}
}
