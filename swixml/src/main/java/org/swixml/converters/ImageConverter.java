package org.swixml.converters;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * A Converter that turns a Strings in the form of a filename into an Image
 * objects.
 * 
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see java.awt.Dimension
 * @see org.swixml.ConverterLibrary
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class ImageConverter extends AbstractConverter<Image>
{
	/** converter's return type */
	public static final Class<Image> TEMPLATE = Image.class;

	/**
	 * Converts a String into an ImageIcon through a Resource lookup
	 * 
	 * @param value the localizer value
	 * @param type <code>Class</code> not used
	 * @param attr <code>Attribute</code> attribute provides the value to be converted
	 * @param engine Provides <code>Localizer</code> allow the use of resource lookups
	 * 
	 * @return <code>Object</code> - an <code>ImageIcon</code>
	 */
	@Override
	public Image convert(String value, Class<Image> type, final Attribute attr, SwingEngine<?> engine)
	{
		return ImageConverter.conv(value, type, attr, engine);
	}

	/**
	 * Converts a String into an ImageIcon through a Resource lookup
	 * 
	 * @param value the localizer value
	 * @param type <code>Class</code> not used
	 * @param attr <code>Attribute</code> attribute provides the value to be converted
	 * @param engine Provides <code>Localizer</code> allow the use of resource lookups
	 * 
	 * @return <code>Object</code> - an <code>ImageIcon</code>
	 */
	public static Image conv(String value, Class<?> type, final Attribute attr, SwingEngine<?> engine)
	{
		ImageIcon icon = (ImageIcon) ImageIconConverter.conv(value, type, attr, engine);
		return icon != null ? icon.getImage() : null;
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 * 
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<Image> convertsTo()
	{
		return TEMPLATE;
	}
}
