package org.swixml.converters;

import static javax.swing.BorderFactory.createCompoundBorder;
import static javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION;
import static javax.swing.border.TitledBorder.DEFAULT_POSITION;
import static org.swixml.converters.PrimitiveConverter.getConstantValue;
import static org.swixml.dom.Attribute.CDATA_TYPE;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.swixml.Converter;
import org.swixml.ConverterLibrary;
import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;

/**
 * The <code>BorderConverter</code> class defines a converter that creates
 * Border objects based on a provided String.
 * 
 * <p>The BorderConverter internally uses the <code>javax.swing.BorderFactory</code>
 * and its static <i>create</i> methods to instantiate different kinds of borders,
 * based on the given String.</p>
 * 
 * <p>Additional parameters to create a border need to be comma separated and
 * enclosed in parentheses.</p>
 * 
 * <p>Parameter types get converted through the <code>ConverterLibrary</code>.
 * Therefore, only parameter classes are supported that have registered
 * converters in the ConverLibrary.</p>
 * 
 * <p><b>Examples for Valid XML attribute notations:</b></p>
 * 
 * <ul>
 * <li>border="MatteBorder(4,4,4,4,red)"</li>
 * <li>border="EmptyBorder(5,5,5,5)"</li>
 * <li>border="TitledBorder(My Title)"</li>
 * <li>border="RaisedBevelBorder"</li>
 * <li>border="TitledBorder(myTitle, CENTER, BELOW_BOTTOM, 'SansSerif-BOLD-18', BLUE)"</li>
 * </ul>
 *
 * @author <a href="mailto:wolf@wolfpaulus.com">Wolf Paulus</a>
 * 
 * @version $Revision: 1.1 $
 * 
 * @see javax.swing.BorderFactory
 * @see javax.swing.border.AbstractBorder
 * @see org.swixml.ConverterLibrary
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class BorderConverter extends AbstractConverter<Border>
{
	/**
	 * converter's return type
	 */
	public static final Class<Border> TEMPLATE = Border.class;
	
	/**
	 * all methods the BorderFactory provides
	 */
	private static final Method[] METHODS = BorderFactory.class.getMethods();
	private static final Pattern COMPOUND_BORDER_PATTERN = Pattern
		.compile("CompoundBorder[(][\\s]*(.*)[\\s]*[,][\\s]+(.*)[\\s]*[)]");
	private static final Pattern BORDER_PATTERN = Pattern.compile("(\\w+)(?:[(]([\\w, -\\\\*]+)*[)])?");

	/**
	 * Returns a <code>javax.swing Border</code>
	 *
	 * @param value The border specification.
	 * @param type Capable of rendering a border around a component.
	 * @param attr Not used.
	 * @param engine Rendering engine for an XML descriptor.
	 * 
	 * @return <code>Object</code> runtime type is subclass of
	 *		   <code>AbstractBorder</code>
	 */
	@Override
	public Border convert(String value, Class<Border> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		Matcher m = COMPOUND_BORDER_PATTERN.matcher(value);
		if ( m.matches() )
		{
			Border outside = convert(type, m.group(1), engine);
			Border inside = convert(type, m.group(2), engine);
			return createCompoundBorder(outside, inside);
		}
		return convert(type, value, engine);
	}

	/**
	 * Convert a border specification into a {@link Border}.
	 * 
	 * @param type capable of rendering a border around a component.
	 * @param borderSpec The border specification.
	 * @param engine rendering engine for an XML descriptor.
	 * 
	 * @return <code>Object</code> runtime type is subclass of
	 *		   <code>AbstractBorder</code>
	 */
	protected Border convert(final Class<Border> type, final String borderSpec, SwingEngine<?> engine)
	{
		Matcher m = BORDER_PATTERN.matcher(borderSpec);
		if ( !m.matches() )
		{
			return null;
		}
		
		int groupCount = m.groupCount();
		String borderType = m.group(1);
		String[] params = new String[0];
		if ( groupCount > 1 )
		{
			String p = m.group(2);
			if ( p != null )
				params = p.split(",");
		}
		
		ConverterLibrary cvtlib = ConverterLibrary.getInstance();
		try
		{
			if ( "TitledBorder".equalsIgnoreCase(borderType) )
				return convertTitledBorder(cvtlib, engine, params);
			else
				return convertBorder(cvtlib, engine, borderType, params);
		}
		catch (Exception e)
		{
			logger.error("Couldn't create border, " + borderSpec, e);
		}
		return null;
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 *
	 * @return <code>Class</code> - the Class the converter is returning when
	 *		   its convert method is called
	 */
	@Override
	public Class<Border> convertsTo()
	{
		return TEMPLATE;
	}

	/**
	 *
	 * @param cvtlib
	 * @param localizer
	 * @param borderType
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Border convertBorder(ConverterLibrary cvtlib, SwingEngine<?> engine, String borderType, String[] params)
		throws Exception
	{
		Border border = null;
		Method method = null;
		int pLen = params.length;
		
		//
		// Special case for single parameter construction, give priority to String Type
		//
		if ( pLen == 0 )
		{
			try
			{
				method = BorderFactory.class.getMethod("create" + borderType);
			}
			catch (NoSuchMethodException e)
			{
				// intent. empty
			}
			
			// if (method == null) pLen = 1 ; // try with empty string
			if ( method == null )
				return null;
		}
		
		/**
		 * Determine if each method the BorderFactory provides has convertible parameters.
		 */
		Object[] args = null;
		for ( int i = 0; (method == null) && i < METHODS.length; i++ )
		{
			// Does this method have match by name and parameter count?
			if ( METHODS[i].getName().endsWith(borderType) &&
				(METHODS[i].getParameterTypes().length == pLen)	)
			{
				args = new Object[pLen];
				method = METHODS[i];
				for ( int j = 0; j < method.getParameterTypes().length; j++ )
				{
					String value = params[j].trim();
					Class<?> parmType = method.getParameterTypes()[j];
					
					// If parameter type is a String then assign argument
					// without conversion.
					if ( String.class.equals(parmType) )
						args[j] = value;
					else
					{
						// When parameter type is not a String, then does it have a converter?
						Converter<?> converter = cvtlib.getConverter(parmType);
						if ( converter != null )
						{
							try
							{
								Class<?> ct = converter.convertsTo();
								String name = String.class.equals(ct) ? "title" : ct.getSimpleName();
								final Attribute attrib = new Attribute(name, value, CDATA_TYPE);
								Object arg = converter.convert((Class) parmType, attrib, engine);
								if ( arg != null )
									args[j] = arg;
								else
									method = null;
							}
							catch ( Exception ex )
							{
								method = null;
							}
						}
						else
							method = null;
						
						// If parameter cannot be converted, break to next method.
						if ( method == null )
							break;
					}
				}
			}
		}
		
		if ( method != null )
			border = (Border) method.invoke(null, args);
		
		return border;
	}

	/*
	 * Creates a TitledBorder instance with the default border,
	 * title, title-justification, title-position, and title-font.
	 * 
	 * param[0]: title
	 * param[1]: title-justification
	 * param[2]: title-position
	 * param[3]: title-font
	 * 
	 * border="TitledBorder(myTitle, CENTER, BELOW_BOTTOM, 'SansSerif-BOLD-18', blue)"
	 * 
	 */
	private TitledBorder convertTitledBorder(ConverterLibrary cvtlib, SwingEngine<?> engine, String[] params)
		throws Exception
	{
		TitledBorder titledBorder = null;
		
		// Default border
		Border border = null;
		
		if ( params == null || params.length == 0 )
			titledBorder = new TitledBorder(border);
		else
		{
			Attribute attrib = new Attribute("title", params[0].trim(), CDATA_TYPE);
			Converter<String> converter = cvtlib.getConverter(String.class);
			String title = (String) converter.convert(String.class, attrib, engine);
			switch (params.length)
			{
				case 1:
				{
					titledBorder = new TitledBorder(title);
					break;
				}
				case 2:
				{
					int titleJustification = getConstantValue(TitledBorder.class, params[1], DEFAULT_JUSTIFICATION);
					titledBorder = new TitledBorder(border, title, titleJustification, DEFAULT_POSITION);
					break;
				}
				case 3:
				{
					int titleJustification = getConstantValue(TitledBorder.class, params[1], DEFAULT_JUSTIFICATION);
					int textPosition = getConstantValue(TitledBorder.class, params[2], DEFAULT_POSITION);
					titledBorder = new TitledBorder(border, title, titleJustification, textPosition);
					break;
				}
				case 4:
				{
					int titleJustification = getConstantValue(TitledBorder.class, params[1], DEFAULT_JUSTIFICATION);
					int textPosition = getConstantValue(TitledBorder.class, params[2], DEFAULT_POSITION);
					Font titleFont = FontConverter.convert(params[3], engine);
					titledBorder = new TitledBorder(border, title, titleJustification, textPosition, titleFont);
					break;
				}
				default:
				{
					int titleJustification = getConstantValue(TitledBorder.class, params[1], DEFAULT_JUSTIFICATION);
					int textPosition = getConstantValue(TitledBorder.class, params[2], DEFAULT_POSITION);
					Font titleFont = FontConverter.convert(params[3], engine);
					Color titleColor = ColorConverter.convert(params[4], engine);
					titledBorder = new TitledBorder(border, title, titleJustification, textPosition, titleFont, titleColor);
					break;
				}
			}
		}
		
		return titledBorder;
	}
}
