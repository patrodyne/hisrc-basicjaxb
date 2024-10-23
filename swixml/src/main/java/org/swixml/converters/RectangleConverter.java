package org.swixml.converters;

import static java.lang.Math.toIntExact;

import java.awt.Dimension;
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
	 * @param attr <code>Attribute</code> value fields needs provides convertible string.
	 * 
	 * @return <code>Object</code> - runtime type is <code>Dimension</code>
	 */
	@Override
	public Rectangle convert(String value, Class<Rectangle> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		StringTokenizer st = new StringTokenizer(value, ",");
		Dimension size = engine.getELMethods().currentSize();
		
		int ix = 0;
		int iy = 0;
		int iw = 0;
		int ih = 0;
		
		if ( st.hasMoreTokens() )
		{
			String sx = st.nextToken().trim();
			if ( sx.endsWith("%") )
				ix = toIntExact(scaleByPercent(sx, size.getWidth()));
			else
				ix = Integer.parseInt(sx);
		}
		
		if ( st.hasMoreTokens() )
		{
			String sy = st.nextToken().trim();
			if ( sy.endsWith("%") )
				iy = toIntExact(scaleByPercent(sy, size.getHeight()));
			else
				iy = Integer.parseInt(sy);
		}
		
		if ( st.hasMoreTokens() )
		{
			String sw = st.nextToken().trim();
			if ( sw.endsWith("%") )
				iw = toIntExact(scaleByPercent(sw, size.getWidth()));
			else
				iw = Integer.parseInt(sw);
		}
		
		if ( st.hasMoreTokens() )
		{
			String sh = st.nextToken().trim();
			if ( sh.endsWith("%") )
				ih = toIntExact(scaleByPercent(sh, size.getHeight()));
			else
				ih = Integer.parseInt(sh);
		}
		
		return new Rectangle(ix, iy, iw, ih);
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
