package org.swixml.converters;

import javax.swing.ImageIcon;
import org.swixml.Localizer;

import org.swixml.Parser;
import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * A Converter that turns a Strings in the form of a filename into an ImageIcon
 * objects.
 * 
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see java.awt.Dimension
 * @see org.swixml.ConverterLibrary
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class ImageIconConverter extends AbstractConverter<ImageIcon>
{
	/** converter's return type */
	public static final Class<ImageIcon> TEMPLATE = ImageIcon.class;

	// current classloader
	
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
	public ImageIcon convert(String value, Class<ImageIcon> type, final Attribute attr, SwingEngine<?> engine)
	{
		return ImageIconConverter.conv(value, type, attr, engine);
	}

	/**
	 * Converts a String into an ImageIcon through a Resource lookup
	 * 
	 * @param type <code>Class</code> not used
	 * @param attr <code>Attribute</code> attribute provides the value to be converted
	 * @param engine Provides <code>Localizer</code> allow the use of resource lookups

	 * 
	 * @return <code>Object</code> - an <code>ImageIcon</code>
	 */
	public static ImageIcon conv(final Class<?> type, final Attribute attr, SwingEngine<?> engine)
	{
		if ( attr == null )
			return null;
		return conv(attr.getValue(), type, attr, engine);
	}

	/**
	 * 
	 * @param value the localizer value
	 * @param type <code>Class</code> not used
	 * @param attr <code>Attribute</code> attribute provides the value to be converted
	 * @param engine Provides <code>Localizer</code> allow the use of resource lookups
	 * 
	 * @return
	 */
	protected static ImageIcon conv(String value, final Class<?> type, final Attribute attr, SwingEngine<?> engine)
	{
		ImageIcon icon = null;
		final Localizer localizer = Util.getLocalizer(engine);
		if ( Parser.LOCALIZED_ATTRIBUTES.contains(attr.getLocalName().toLowerCase()) )
		{
			attr.setValue(localizer.getString(value));
		}
		try
		{
			// java.net.URL imgURL = Converter.class.getResource(
			// attr.getValue() );
			// icon = new ImageIcon( imgURL );
			icon = new ImageIcon(localizer.getClassLoader().getResource(value));
		}
		catch (Exception e)
		{
			// intentionally empty
		}
		return icon;
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 * 
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<ImageIcon> convertsTo()
	{
		return TEMPLATE;
	}
}
