package org.swixml.converters;

import static java.lang.Math.toIntExact;

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
	 * @param value A comma separated pair representing width and height.
	 * @param type <code>Class</code> not used.
	 * @param attr <code>Attribute</code> not used.
	 * @param engine <code>SwingEngine</code> used for current size.
	 * 
	 * @return <code>Object</code> - runtime type is <code>Dimension</code>
	 */
	@Override
	public Dimension convert(String value, Class<Dimension> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		Dimension size = engine.getELMethods().currentSize();
		return convert(value, size);
	}

	/**
	 * Converts a String into an Dimension object.
	 *
	 * @param value A comma separated pair representing width and height.
	 * 
	 * @return <code>Object</code> - runtime type is <code>Dimension</code>
	 */
	public static Dimension convert(String value)
	{
		StringTokenizer st = new StringTokenizer(value, ",");
		
		int iw = 0;
		int ih = 0;
		
		if ( st.hasMoreTokens() )
			iw = Integer.parseInt(st.nextToken().trim());
		
		if ( st.hasMoreTokens() )
			ih = Integer.parseInt(st.nextToken().trim());
		else
			ih = iw;
		
		return new Dimension(iw, ih);
	}
	
	/**
	 * Converts a String into an Dimension object.
	 *
	 * @param value A comma separated pair representing width and height.
	 * @param size <code>Dimension</code> the current size.
	 * 
	 * @return <code>Object</code> - runtime type is <code>Dimension</code>
	 */
	public static Dimension convert(String value, Dimension size)
		throws Exception
	{
		StringTokenizer st = new StringTokenizer(value, ",");
		
		String sw = "100%", sh = "100%";
		int iw = 0, ih = 0;
		
		if ( st.hasMoreTokens() )
		{
			sw = st.nextToken().trim();
			if ( sw.endsWith("%") && size != null )
				iw = toIntExact(scaleByPercent(sw, size.getWidth()));
			else
				iw = Integer.parseInt(sw);
		}
		
		if ( st.hasMoreTokens() )
		{
			sh = st.nextToken().trim();
			if ( sh.endsWith("%") && size != null )
				ih = toIntExact(scaleByPercent(sh, size.getHeight()));
			else
				ih = Integer.parseInt(sh);
		}
		else
		{
			if ( sw.endsWith("%") && size != null )
				ih = toIntExact(scaleByPercent(sw, size.getHeight()));
			else
				ih = iw;
		}
		
		return new Dimension(iw, ih);
	}
	
	public static boolean isZero(Dimension dim)
	{
		return (dim == null) || ((dim.getWidth() == 0) && (dim.getHeight() == 0));
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
