package org.swixml.converters;

import static org.swixml.converters.Util.getLocalizer;
import static org.swixml.el.ELUtility.evaluateAttribute;

import org.swixml.Converter;
import org.swixml.LogAware;
import org.swixml.Parser;
import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * The StringConverter class defines / describes.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * 
 * @version $Revision: 1.1 $
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class StringConverter implements Converter<String>, LogAware
{
	/**
	 * converter's return type
	 */
	public static final Class<String> TEMPLATE = String.class;

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 *
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<String> convertsTo()
	{
		return TEMPLATE;
	}

	/**
	 * Convert the value of the given <code>Attribute</code> object into an
	 * output object of the specified type.
	 *
	 * @param type <code>Class</code> Data type to which the Attribute's value
	 *            should be converted
	 * @param attr <code>Attribute</code> the attribute, providing the value to
	 *            be converted.
	 */
	@Override
	public String convert(Class<String> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		final Object value = evaluateAttribute(engine.getELProcessor(), attr);
		if ( null == value )
			return null;
		//
		// Localize Strings but only if Attribute calls for localization.
		//
		if ( Parser.LOCALIZED_ATTRIBUTES.contains(attr.getLocalName().toLowerCase()) )
		{
			return getLocalizer(engine).getString(value.toString());
		}
		return value.toString();
	}
}
