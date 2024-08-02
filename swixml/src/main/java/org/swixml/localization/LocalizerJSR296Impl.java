package org.swixml.localization;

import java.util.Locale;
import java.util.MissingResourceException;

import org.jdesktop.application.Application;
import org.swixml.Localizer;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class LocalizerJSR296Impl extends Localizer
{
	private String bundleName;

	@Override
	public String getString(String key)
	{
		String result = null;
		if ( isUsable() )
		{
			result = Application.getInstance().getContext().getResourceMap().getString(key);
		}
		return (result == null) ? key : result;
	}

	@Override
	public boolean isUsable()
	{
		return bundleName != null;
	}

	@Override
	public void setLocale(Locale locale)
	{
		// Ignored
	}

	@Override
	public void setResourceBundle(String bundleName)
		throws MissingResourceException
	{
		this.bundleName = bundleName;
	}
}
