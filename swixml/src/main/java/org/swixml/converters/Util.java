package org.swixml.converters;

import static java.lang.Integer.parseInt;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.StringTokenizer;

import org.swixml.Localizer;
import org.swixml.SwingEngine;

/**
 * Utility class with static helper methods
 * 
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public final class Util
{
	private static final Localizer defaultLocalizer = new Localizer()
	{
		@Override
		public String getString(String key)
		{
			return key;
		}

		@Override
		public boolean isUsable()
		{
			return true;
		}

		@Override
		public void setLocale(Locale locale)
		{
		}

		@Override
		public void setResourceBundle(String bundleName)
			throws MissingResourceException
		{
		}
	};

	public static Localizer getLocalizer(SwingEngine<?> engine)
	{
		return (engine == null) ? defaultLocalizer : engine.getLocalizer();
	}

	/**
	 * Returns the remaining tokens of a StringTokenizer in an int-Array
	 * 
	 * @param st <code>StringTokenizer</code>
	 * @return <code>int[]</code> array containing the remaining tokens
	 *         converted into int(s)
	 */
	public static int[] ia(StringTokenizer st)
	{
		int size = st != null ? st.countTokens() : 0;
		int[] a = new int[size];
		int i = 0;
		while (st != null && st.hasMoreTokens())
		{
			try
			{
				a[i] = Integer.parseInt(st.nextToken().trim());
				i++;
			}
			catch (NumberFormatException e)
			{
				// no exception handling required
			}
		}
		int[] b = new int[i];
		System.arraycopy(a, 0, b, 0, i);
		return b;
	}

	/**
	 * Returns the remaining tokens of a StringTokenizer in a double-Array
	 * 
	 * @param st <code>StringTokenizer</code>
	 * @return <code>double[]</code> array containing the remaining tokens
	 *         converted into double(s)
	 */
	public static double[] da(StringTokenizer st)
	{
		int size = st != null ? st.countTokens() : 0;
		double[] a = new double[size];
		int i = 0;
		while (st != null && st.hasMoreTokens())
		{
			try
			{
				a[i] = Double.parseDouble(st.nextToken().trim());
				i++;
			}
			catch (NumberFormatException e)
			{
				// no exception handling required
			}
		}
		double[] b = new double[i];
		System.arraycopy(a, 0, b, 0, i);
		return b;
	}
	
	/**
	 * Returns the remaining tokens of a StringTokenizer in a float-Array
	 * 
	 * @param st <code>StringTokenizer</code>
	 * @return <code>float[]</code> array containing the remaining tokens
	 *         converted into float(s)
	 */
	public static float[] fa(StringTokenizer st)
	{
		int size = st != null ? st.countTokens() : 0;
		float[] a = new float[size];
		int i = 0;
		while (st != null && st.hasMoreTokens())
		{
			try
			{
				a[i] = Float.parseFloat(st.nextToken().trim());
				i++;
			}
			catch (NumberFormatException e)
			{
				// no exception handling required
			}
		}
		float[] b = new float[i];
		System.arraycopy(a, 0, b, 0, i);
		return b;
	}

	/**
	 * Returns the remaining tokens of a StringTokenizer in a String-Array
	 * 
	 * @param st <code>StringTokenizer</code>
	 * @return <code>String[]</code> array containing the remaining tokens
	 *         converted into String(s)
	 */
	public static String[] sa(StringTokenizer st)
	{
		int size = st != null ? st.countTokens() : 0;
		String[] a = new String[size];
		int i = 0;
		while (st != null && st.hasMoreTokens())
		{
			a[i] = st.nextToken().trim();
			i++;
		}
		String[] b = new String[i];
		System.arraycopy(a, 0, b, 0, i);
		return b;
	}

	/**
	 * Returns the integer value of the given XML attribute; or the default
	 * value.
	 */
	public static int getInteger(final org.w3c.dom.Element element, final String attr, int def)
	{
		if ( element.getAttributeNode(attr) == null )
			return def;
		try
		{
			return parseInt(element.getAttribute(attr).trim());
		}
		catch (NumberFormatException e)
		{
			// no exception handling required
			return def;
		}
	}
}
