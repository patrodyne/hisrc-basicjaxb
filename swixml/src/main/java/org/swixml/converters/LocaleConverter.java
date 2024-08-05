package org.swixml.converters;

import java.util.Locale;
import java.util.StringTokenizer;

import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * The LocaleConverter class defines / describes
 *
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 */
public class LocaleConverter extends AbstractConverter<Locale>
{
	/** converter's return type */
	public static final Class<Locale> TEMPLATE = Locale.class;

	/**
	 * Convert the value of the given <code>Attribute</code> object into an
	 * output object of the specified type.
	 *
	 * @param attr <code>Attribute</code> the attribute, providing the value to
	 *            be converted.
	 */
	public static Locale conv(Attribute attr)
		throws Exception
	{
		if ( attr == null )
			return null;
		return conv(attr.getValue(), attr);
	}

	/**
	 * Obtains a locale from a language, country and/or variant codes.
     * This method normalizes the language value to lower case.
     * 
	 * @param csvCodes comma separated locale codes. 
	 * @param attr Value to be converted; but, not implemented!
	 * 
	 * @return An object represents a specific geographical, political, or cultural region. 
	 */
	protected static Locale conv(String csvCodes, Attribute attr)
	{
		// Locale.getDefault();
		Locale locale = null; 
		
		StringTokenizer st = new StringTokenizer(csvCodes, ",");
		int n = st.countTokens();
		
		StringBuilder sb = new StringBuilder();
		
		if ( n > 0 )
			sb.append(st.nextToken());
		
		if ( n > 1 )
			sb.append("-" + st.nextToken());
		
		if ( n > 2 )
			sb.append("-" + st.nextToken());
		
		if ( sb.length() > 0 )
			locale =  Locale.forLanguageTag(sb.toString());
		
		return locale;
	}

	/**
	 * Convert the value of the given <code>Attribute</code> object into an
	 * output object of the specified type.
	 *
	 * @param type <code>Class</code> Data type to which the Attribute's value
	 *            should be converted
	 * @param attr <code>Attribute</code> the attribute, providing the value to
	 *            be converted.
	 *
	 */
	@Override
	public Locale convert(String value, Class<Locale> type, Attribute attr, SwingEngine<?> engine)
	{
		return LocaleConverter.conv(value, attr);
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 * 
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<Locale> convertsTo()
	{
		return TEMPLATE;
	}
}
