package org.swixml;

import java.util.Locale;
import java.util.MissingResourceException;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public abstract class Localizer
{
	protected static String SEPARATOR = ",";
	protected ClassLoader cl = Localizer.class.getClassLoader();

	/**
	 * Sets the regular expression used to split a key, that could not be found
	 * in the resource bundle.
	 *
	 * @param regExp <code>String</code>
	 * @see String#split
	 * @see #getString
	 */
	public static void setSeparator(String regExp)
	{
		Localizer.SEPARATOR = regExp;
	}

	public abstract String getString(final String key);

	public abstract boolean isUsable();

	public abstract void setLocale(Locale locale);

	public abstract void setResourceBundle(String bundleName)
		throws MissingResourceException;

	/**
	 * @return <code>ClassLoader</code> returns the classloader attribute, which
	 *         has probably been set by the SwingEngine
	 */
	public ClassLoader getClassLoader()
	{
		return cl;
	}

	/**
	 * Sets the ClassLoader attribute. The Localizer does not use the provided
	 * classloader directly but return it when asked for.
	 *
	 * @param cl <code>ClassLoader</code> - custom classloader
	 */
	public void setClassLoader(ClassLoader cl)
	{
		this.cl = cl;
	}
}
