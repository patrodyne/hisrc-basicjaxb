package org.swixml.converters;

import java.awt.Rectangle;
import java.util.StringTokenizer;

import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * A Converter that turns a Strings in the form: width,height into Rectangle
 * objects.
 * 
 * <p><b>Examples for Valid XML attribute notations:</b></p>
 * 
 * <ul>
 * <li>bounds="10,10,250,200"</li>
 * </ul>
 * 
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see java.awt.Rectangle
 * @see org.swixml.ConverterLibrary
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public final class RectangleConverter extends AbstractConverter<Rectangle>
{
	/** converter's return type */
	public static final Class<Rectangle> TEMPLATE = Rectangle.class;

	/**
	 * Converts a String into a Rectangle object
	 * 
	 * @param type <code>Class</code> not used
	 * @param attr <code>Attribute</code> value fields needs provides
	 *            convertable String
	 * @return <code>Object</code> - runtime type is <code>Dimension</code>
	 */
	@Override
	public Rectangle convert(String value, Class<Rectangle> type, Attribute attr, SwingEngine<?> engine)
	{
		StringTokenizer st = new StringTokenizer(value, ",");
		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;
		if ( st.hasMoreTokens() )
		{
			x = Integer.parseInt(st.nextToken().trim());
		}
		if ( st.hasMoreTokens() )
		{
			y = Integer.parseInt(st.nextToken().trim());
		}
		if ( st.hasMoreTokens() )
		{
			width = Integer.parseInt(st.nextToken().trim());
		}
		if ( st.hasMoreTokens() )
		{
			height = Integer.parseInt(st.nextToken().trim());
		}
		return new Rectangle(x, y, width, height);
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 * 
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<Rectangle> convertsTo()
	{
		return TEMPLATE;
	}
}
