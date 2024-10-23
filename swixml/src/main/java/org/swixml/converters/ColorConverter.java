package org.swixml.converters;

import static java.awt.Color.RGBtoHSB;
import static java.awt.Color.getHSBColor;
import static java.lang.Integer.parseInt;
import static java.util.regex.Pattern.compile;

import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	// HSV regex pattern: "180-50-50"
	private static final Pattern HSV_PATTERN = compile("([as]*[0-9]+|\\*)-([as]*[0-9]+|\\*)-([as]*[0-9]+|\\*)");
	
	private static float[] defaultHSV(Color dc)
	{
		float[] hsv = new float[3];
		RGBtoHSB(dc.getRed(), dc.getGreen(), dc.getBlue(), hsv );
		return hsv;
	}

	/**
	 * Returns a <code>java.awt.Color</code> runtime object
	 *
	 * @param value {@link String} The evaluated value.
	 * @param type <code>Class</code> not used
	 * @param attr {@link Attribute} not used, in favor of the default color.
	 * @param engine {@link SwingEngine} the rendering swingEngine to convert an XML descriptor a GUI.
	 * 
	 * @return runtime type is subclass of <code>java.awt.Color</code>
	 */
	@Override
	public Color convert(String value, Class<Color> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		return convert(value, engine);
	}
	

	/**
	 * Returns a <code>java.awt.Color</code> runtime object
	 *
	 * @param value <code>Attribute</code> value needs to provide a String
	 * @param engine {@link SwingEngine} the rendering swingEngine to convert an XML descriptor a GUI.
	 * 
	 * @return runtime type is subclass of <code>java.awt.Color</code>
	 */
	public static Color convert(final String value, SwingEngine<?> engine)
	{
		return convert(value, defaultHSV(engine.getELMethods().getDefaultColor()));
	}

	/**
	 * Returns a <code>java.awt.Color</code> runtime object
	 *
	 * @param value <code>Attribute</code> value needs to provide a String
	 * @param defaultHSV The default HSV parts.
	 * 
	 * @return runtime type is subclass of <code>java.awt.Color</code>
	 */
	public static Color convert(final String value, float[] defaultHSV)
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
		
		// Parse a single token as a HEX or a H-S-V representation.
		if ( 1 == st.countTokens() )
		{
			Color color = null;
			try
			{
				String clr = st.nextToken().trim();
				if ( clr.startsWith("#") )
				{
					clr = clr.substring(1);
					color = new Color(parseInt(clr, 16));
				}
				else
				{
					Matcher hsvMatcher = HSV_PATTERN.matcher(clr);
					if ( hsvMatcher.find() )
					{
						String huePart = hsvMatcher.group(1);
						String satPart = hsvMatcher.group(2);
						String valPart = hsvMatcher.group(3);
						float hue = parseFloat(huePart, defaultHSV[0], 360f);
						float sat = parseFloat(satPart, defaultHSV[1], 100f);
						float val = parseFloat(valPart, defaultHSV[2], 100f);
						color = getHSBColor(hue, sat, val);
					}
					else
						color = new Color(parseInt(clr));
				}
				return color;
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
	
	private static float parseFloat(String specPart, float defaultValue, float range)
	{
		float value = defaultValue;
		if ( !isWildPart(specPart) )
		{
			if ( isVaryPart(specPart) )
			{
				float mod = Float.parseFloat(specPart.substring(1)) % range;
				if ( specPart.startsWith("a") )
					value += ( mod / range );
				else if ( specPart.startsWith("s") )
					value -= ( mod / range );
			}
			else
				value = Float.parseFloat(specPart) / range;
		}
		return value;
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
