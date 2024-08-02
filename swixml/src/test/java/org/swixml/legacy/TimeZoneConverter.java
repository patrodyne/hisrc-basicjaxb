package org.swixml.legacy;

import java.util.SimpleTimeZone;

import org.swixml.Converter;
import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

public class TimeZoneConverter implements Converter<SimpleTimeZone>
{
	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 * 
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<SimpleTimeZone> convertsTo()
	{
		return SimpleTimeZone.class;
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
	public SimpleTimeZone convert(Class<SimpleTimeZone> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		SimpleTimeZone tz = null;
		if ( attr != null && attr.getValue() != null )
			tz = new SimpleTimeZone(0, attr.getValue());
		
		return tz;
	}
}
