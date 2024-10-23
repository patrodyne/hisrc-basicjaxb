package org.swixml.converters;

import static java.lang.String.format;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.text.ParseException;

import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * The FontConverter class defines / describes
 *
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 *
 * @author <a href="mailto:wolf@wolfpaulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * @since swixml 1.0
 */
public class FontConverter extends AbstractConverter<Font>
{
	/**
	 * converter's return type
	 */
	public static final Class<Font> TEMPLATE = Font.class;
	
	/**
	 * Convert the value of the given <code>Attribute</code> object into an
	 * output object of the specified type. Returns the <code>Font</code> that
	 * the <code>str</code> argument describes. To ensure that this method
	 * returns the desired Font, format the <code>str</code> parameter in one of
	 * two ways:
	 * 
	 * <ul>
	 * <li>"fontfamilyname-style-pointsize"</li>
	 * <li>"fontfamilyname style pointsize"</li>
	 * </ul>
	 * 
	 * <p>In which <i>style</i> is one of the three case-insensitive strings:
	 * <code>"BOLD"</code>, <code>"BOLDITALIC"</code>, or <code>"ITALIC"</code>,
	 * and <code>pointsize</code> is a decimal representation of the point size.
	 * For example, if you want a font that is Serif, bold, and a point size of
	 * 18, you would call this method with: "Serif-BOLD-18".</p>
	 * 
	 * <p>The default size is 12 and the default style is PLAIN. If you don't
	 * specify a valid size, the returned <code>Font</code> has a size of 12. If
	 * you don't specify a valid style, the returned Font has a style of PLAIN.
	 * If you do not provide a valid font family name in the <code>str</code>
	 * argument, this method still returns a valid font with a family name of
	 * "dialog". To determine what font family names are available on your
	 * system, use the {@link GraphicsEnvironment#getAvailableFontFamilyNames()}
	 * method. If <code>str</code> is <code>null</code>, a new <code>Font</code>
	 * is returned with the family name "dialog", a size of 12 and a PLAIN
	 * style. If <code>str</code> is <code>null</code>, a new <code>Font</code>
	 * is returned with the name "dialog", a size of 12 and a PLAIN style.</p>
	 *
	 * @param fontSpec the name of the font, or null
	 * @param type <code>Class</code> Data type to which the Attribute's value
	 *            should be converted
	 * @param attr <code>Attribute</code> the attribute, providing the value to
	 *            be converted.
	 * @param engine The rendering engine able to convert an XML descriptor into a java.swing UI.
	 *            
	 * @return the <code>Font</code> object that <code>str</code> describes, or
	 *         a new default <code>Font</code> if <code>str</code> is
	 *         <code>null</code>.
	 */
	@Override
	public Font convert(String fontSpec, Class<Font> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		return convert(fontSpec, engine);
	}
	

	/**
	 * Convert the font specification value to a {@link Font} instance.
	 * 
	 * @param fontSpec The font specification value.
	 * @param engine The rendering engine able to convert an XML descriptor into a java.swing UI.
	 * 
	 * @return The {@link Font} instance for the given font specification.
	 */
	public static Font convert(String fontSpec, SwingEngine<?> engine)
	{
		return convert(fontSpec, engine.getELMethods().parentFont());
	}

	/**
	 * Convert the font specification value to a {@link Font} instance.
	 * 
	 * @param fontSpec The font specification value.
	 * @param parentFont The parent (default) font.
	 * 
	 * @return The {@link Font} instance for the given font specification.
	 */
	public static Font convert(String fontSpec, Font parentFont)
	{
		String value = null;
		if ( (fontSpec != null) && !fontSpec.isBlank() && parentFont != null)
		{
			String currentName = parentFont.getName();
			String currentStyle = getStyle(parentFont);
			int currentSize = parentFont.getSize();
			
			// Initialize, for guaranteed result.
			value = format("%s-%s-%02d", currentName, currentStyle, currentSize);
			
			String[] specParts = fontSpec.split("-");
			if ( specParts.length == 1)
				value = format("%s-%s-%02d", specParts[0], currentStyle, currentSize);
			else if ( specParts.length == 2)
			{
				String name = isWildPart(specParts[0]) ? currentName : specParts[0];
				String style = isWildPart(specParts[1]) ? currentStyle : specParts[1];
				value = format("%s-%s-%02d", name, style, currentSize);
			}
			else if ( specParts.length == 3)
			{
				String name = isWildPart(specParts[0]) ? currentName : specParts[0];
				String style = isWildPart(specParts[1]) ? currentStyle : specParts[1];
				if ( isWildPart(specParts[2]) )
					value = format("%s-%s-%02d", name, style, currentSize);
				else
				{
					if ( specParts[2].endsWith("%") )
					{
						try
						{
							long scaledSize = scaleByPercent(specParts[2], currentSize);
							value = format("%s-%s-%02d", name, style, scaledSize);
						}
						catch (ParseException pe)
						{
							logger.warn("cannot parse {}", specParts[2], pe);
						}
					}
					else
						value = format("%s-%s-%s", name, style, specParts[2]);
				}
			}
			return Font.decode(value);
		}
		else
			return parentFont;
	}
	
	public static String encode(Font font)
	{
		String name = font.getFontName();
		String style = getStyle(font);
		int size = font.getSize();
		return format("%s-%s-%02d", name, style, size);
	}

	private static String getStyle(Font font)
	{
		String style = "PLAIN";
		if ( font.isBold() )
		{
			if ( font.isItalic() )
				style = "BOLDITALIC";
			else
				style = "BOLD";
		}
		return style;
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 *
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<Font> convertsTo()
	{
		return TEMPLATE;
	}
}
