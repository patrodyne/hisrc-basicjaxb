package org.swixml.converters;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

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
	 * and pointsize is a decimal representation of the point size. For example,
	 * if you want a font that is Serif, bold, and a point size of 18, you would
	 * call this method with: "Serif-BOLD-18".</p>
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
	 * @param value the name of the font, or null
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
	public Font convert(String value, Class<Font> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		return Font.decode(value);
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
