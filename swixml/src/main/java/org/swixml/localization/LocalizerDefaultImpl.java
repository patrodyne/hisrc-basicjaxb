package org.swixml.localization;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.swixml.Localizer;
import org.swixml.XTabbedPane;

/**
 * The {@link Localizer} class provides consumers with a simple localization
 * tools: <code>getString(key)</code>.
 * 
 * <p>
 * Locale and ResourceBundle need to be set to use it. Since some setters accept
 * comma separated lists of Strings; for example, a {@link XTabbedPane}'s
 * <code>setTitles()</code> methods, the Localizer will try to split a given key
 * by <i>commas</i> if the key doesn't resolve; for example, a
 * {@link MissingResourceException} is thrown.
 * </p>
 * 
 * <p>
 * For example, if the resource bundle contains strings for the following single
 * keys:
 * </p>
 * 
 * <pre>
 * a = Alpha
 * b = Bravo
 * c = Charlie
 * </pre>
 * 
 * <p>
 * Then calling <code>getString("a,b,c")</code> will result in a string containing
 * the comma separated values, like "Alpha,Brave,Charlie". Look at the provided
 * test case for more details.
 * </p>
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.2 $
 * 
 * @see XTabbedPane#setTitles(String)
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class LocalizerDefaultImpl extends Localizer
{
	private Locale locale = Locale.getDefault();
	private String bundleName;
	private ResourceBundle bundle;

	/**
	 * Returns the localized String based on the given key. If the key cannot
	 * be found, the key is returned instead.
	 *
	 * @param key <code>String</code>
	 * @return <code>String</code> - localized String , or key , if no
	 *         localization is found.
	 */
	@Override
	public String getString(final String key)
	{
		if ( !isUsable() )
			return key;
		String s = "";
		try
		{
			s = bundle.getString(key);
		}
		catch (MissingResourceException e)
		{
			String[] keys = key.split(Localizer.SEPARATOR);
			if ( 2 <= keys.length )
			{
				for ( int i = 0; i < keys.length; i++ )
				{
					s += (i == 0) ? getString(keys[i]) : "," + getString(keys[i]);
				}
			}
			else
				s = key;
		}
		catch (Exception e)
		{
			s = key; // key not found, return key
		}
		return s;
	}

	/**
	 * Sets this Localizer's locale.
	 *
	 * @param locale <code>Locale</code>
	 */
	@Override
	public void setLocale(Locale locale)
	{
		if ( locale == null )
		{
			this.locale = null;
			this.bundle = null;
			this.bundleName = null;
		}
		else if ( this.locale != locale )
		{
			this.locale = locale;
			setResourceBundle(bundleName);
		}
	}

	/**
	 * Sets this Localizer's ResourceBundle.
	 *
	 * @param bundleName <code>String</code>ResourceBundle file / class name
	 * @throws java.util.MissingResourceException - if no resource bundle for
	 *             the specified base name can be found
	 */
	@Override
	public void setResourceBundle(String bundleName)
		throws MissingResourceException
	{
		this.bundleName = bundleName;
		if ( locale == null )
		{
			locale = Locale.getDefault();
		}
		if ( bundleName != null )
		{
			bundle = ResourceBundle.getBundle(bundleName, locale, cl);
		}
		else
		{
			bundle = null;
		}
	}

	/**
	 * Informs about the usablility of this Localizer.
	 *
	 * @return <code>boolean</code> - true if Localizer is setup with Locale and
	 *         ResourceBundle.
	 */
	@Override
	public boolean isUsable()
	{
		return (locale != null && bundle != null);
	}
}
