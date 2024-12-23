package org.swixml.converters;

import static java.lang.Math.toIntExact;

import java.awt.Dimension;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	private static final Pattern TAG_PARM_PATTERN = Pattern.compile("(\\w+)(?:[(]([\\w,\\.%]+)*[)])?");
	
	private static final Dimension DEFAULT_DIMENSION = new Dimension(100,100);

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
		return convert(value, engine);
	}

	/**
	 * Converts a String into an Dimension object.
	 *
	 * @param value A comma separated pair representing width and height.
	 * 
	 * @return <code>Dimension</code> - runtime type is <code>Dimension</code>
	 */
	public static Dimension convert(String value)
	{
		int iw = 0;
		int ih = 0;
		
		StringTokenizer st = new StringTokenizer(value, ",");
		
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
	 * @param engine <code>SwingEngine</code> the Swing engine.
	 * 
	 * @return <code>Dimension</code> - runtime type is <code>Dimension</code>
	 */
	public static Dimension convert(String value, SwingEngine<?> engine)
		throws Exception
	{
		Dimension size = null;
		
		Matcher tpm = TAG_PARM_PATTERN.matcher(value);
		if ( tpm.matches() )
		{
			int groupCount = tpm.groupCount();
			String dimType = tpm.group(1);
			String[] parms = new String[0];
			if ( groupCount > 1 )
			{
				String g2 = tpm.group(2);
				if ( g2 != null )
					parms = g2.split(",");
				
				if ( "pageSize".equals(dimType) )
				{
					if ( parms.length > 1 )
					{
						int sw = Integer.parseInt(parms[0]);
						int sh = Integer.parseInt(parms[1]);
						size = engine.getELMethods().pageSize(sw, sh);
					}
				}
				else if ( "size".equals(dimType) )
				{
					if ( parms.length == 0 )
					{
						// circular logic
						// size = engine.getELMethods().size();
					}
					else if ( parms.length > 1 )
					{
						int sw = Integer.parseInt(parms[0]);
						int sh = Integer.parseInt(parms[1]);
						size = engine.getELMethods().size(sw, sh);
					}
				}
				else if ( "scaleSize".equals(dimType) )
				{
					if ( g2.contains("%") )
					{
						if ( parms.length == 1 )
							size = engine.getELMethods().scaleSize(parms[0]);
						else if ( parms.length > 1 )
							size = engine.getELMethods().scaleSize(parms[0], parms[1]);
					}
					else
					{
						if ( parms.length > 1 )
						{
							double sw = Double.valueOf(parms[0]);
							double sh = Double.valueOf(parms[1]);
							size = engine.getELMethods().scaleSize(sw, sh);
						}
					}
				}
			}
			
			if ( size == null )
			{
				if ( "size".equals(dimType) )
					size = engine.getELMethods().size();
				else
					size = DEFAULT_DIMENSION;
			}
		}
		else
			size = convert(value, engine.getELMethods().currentSize());
		
		return size;
	}
	
	/**
	 * Converts a String into an Dimension object.
	 *
	 * @param value A comma separated pair representing width and height.
	 * @param size <code>Dimension</code> the current size.
	 * 
	 * @return <code>Dimension</code> - runtime type is <code>Dimension</code>
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
