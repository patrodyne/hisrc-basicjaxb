package org.swixml.converters;

import java.awt.Point;
import java.util.StringTokenizer;

import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * A Converter that turns a Strings into Point objects
 * 
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see java.awt.Point
 * @see org.swixml.ConverterLibrary
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class PointConverter extends AbstractConverter<Point>
{
	/** converter's return type */
	public static final Class<Point> TEMPLATE = Point.class;

	/**
	 * Converts a String into an Point object
	 * 
	 * @param type <code>Class</code> not used
	 * @param attr <code>Attribute</code> value fields needs provides
	 *            convertable String
	 * @return <code>Object</code> - runtime type is <code>Point</code>
	 */
	@Override
	public Point convert(String value, Class<Point> type, final Attribute attr, SwingEngine<?> engine)
	{
		StringTokenizer st = new StringTokenizer(value, ",");
		int x = 0;
		int y = 0;
		if ( st.hasMoreTokens() )
		{
			x = Integer.parseInt(st.nextToken().trim());
		}
		if ( st.hasMoreTokens() )
		{
			y = Integer.parseInt(st.nextToken().trim());
		}
		return new Point(x, y);
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 * 
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<Point> convertsTo()
	{
		return TEMPLATE;
	}
}
