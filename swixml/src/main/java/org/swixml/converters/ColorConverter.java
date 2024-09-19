package org.swixml.converters;

import static java.lang.Integer.parseInt;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.StringTokenizer;

import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * The ColorConverter class defines a Converter that turns the Strings into a
 * Color object.
 *
 * <p><b>The following example show valid xml attributes to create Color objects:</b></p>
 * 
 * <ul>
 * <li>background="3399CC"</li>
 * <li>background="red"</li>
 * <li>foreground="991144"</li>
 * </ul>
 * 
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @see java.awt.Color
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 *

 */
public class ColorConverter extends AbstractConverter<Color>
{
	/**
	 * converter's return type
	 */
	public static final Class<Color> TEMPLATE = Color.class;

	/**
	 * Returns a <code>java.awt.Color</code> runtime object
	 *
	 * @param type <code>Class</code> not used
	 * @param attr <code>Attribute</code> value needs to provide a String
	 * 
	 * @return runtime type is subclass of <code>java.awt.Color</code>
	 */
	@Override
	public Color convert(String value, Class<Color> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		return ColorConverter.conv(type, value);
	}

	/**
	 * Returns a <code>java.awt.Color</code> runtime object
	 *
	 * @param type <code>Class</code> not used
	 * @param value <code>Attribute</code> value needs to provide a String
	 * 
	 * @return runtime type is subclass of <code>java.awt.Color</code>
	 */
	public static Color conv(final Class<?> type, final String value)
	{
		try
		{
			Field field = Color.class.getField(value);
			if ( Color.class.equals(field.getType()) && Modifier.isStatic(field.getModifiers()) )
				return (Color) field.get(Color.class);
		}
		catch (NoSuchFieldException | SecurityException | IllegalAccessException ex)
		{
		}
		
		// Tokenize on comma.
		StringTokenizer st = new StringTokenizer(value, ",");
		
		// Parse a single token as a radix 16 integer.
		if ( 1 == st.countTokens() )
		{
			try
			{
				String rgb = st.nextToken().trim();
				if ( rgb.startsWith("#") )
					rgb = rgb.substring(1);
				return new Color(parseInt(rgb, 16));
			}
			catch (NumberFormatException e)
			{
				logger.warn("conv color", e);
				return null;
			}
		}
		
		// Parse the remaining tokens into an integer array.
		int[] para = Util.ia(st);
		
		// Parse four integers representing red, green and blue and alpha.
		if ( 4 <= para.length )
			return new Color(para[0], para[1], para[2], para[3]);
		
		// Parse three integers representing red, green and blue.
		if ( 3 <= para.length )
			return new Color(para[0], para[1], para[2]);
		
		// Parse a single integer representation of RGB.
		if ( 1 <= para.length )
			return new Color(para[0]);
		
		return null;
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 *
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<Color> convertsTo()
	{
		return TEMPLATE;
	}
}
