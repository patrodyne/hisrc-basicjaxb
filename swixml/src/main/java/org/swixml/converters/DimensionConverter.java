package org.swixml.converters;

import java.awt.Dimension;
import java.util.StringTokenizer;

import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * A Converter that turns a Strings in the form: width,height into Dimension
 * objects. <br>
 * 
 * <p><b>Examples for Valid XML attribute notations:</b></p>
 * 
 * <ul>
 * <li>size="500,300"</li>
 * </ul>
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see java.awt.Dimension
 * @see org.swixml.ConverterLibrary
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public final class DimensionConverter extends AbstractConverter<Dimension>
{
	/**
	 * converter's return type
	 */
	public static final Class<Dimension> TEMPLATE = Dimension.class;

	/**
	 * Converts a String into an Dimension object
	 *
	 * @param type <code>Class</code> not used
	 * @param attr <code>Attribute</code> value fields needs provides convertible string.
	 * 
	 * @return <code>Object</code> - runtime type is <code>Dimension</code>
	 */
	@Override
	public Dimension convert(String value, Class<Dimension> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		StringTokenizer st = new StringTokenizer(value, ",");
		int width = 0;
		int height = 0;
		if ( st.hasMoreTokens() )
		{
			width = Integer.parseInt(st.nextToken().trim());
		}
		if ( st.hasMoreTokens() )
		{
			height = Integer.parseInt(st.nextToken().trim());
		}
		return new Dimension(width, height);
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 *
	 * @return <code>Class</code> - the Class the converter is returning when
	 *         its convert method is called
	 */
	@Override
	public Class<Dimension> convertsTo()
	{
		return TEMPLATE;
	}
}
