package org.swixml.converters;

import static org.swixml.converters.PrimitiveConverter.getConstantValue;

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
 * <li>border="TitledBorder(TitledBorder, myTitle, TitledBorder.CENTER, TitledBorder.BELOW_BOTTOM, VERDANA-BOLD-18, blue)"</li>
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
	private static final Pattern compoundBorderPattern = Pattern
		.compile("CompoundBorder[(][\\s]*(.*)[\\s]*[,][\\s]+(.*)[\\s]*[)]");
	private static final Pattern borderPattern = Pattern.compile("(\\w+)(?:[(]([\\w, -]+)*[)])?");
	// private Pattern borderPattern =
	// Pattern.compile("(\\w*)(?:[(](.+)*[)])?");

	/**
	 * Returns a <code>javax.swing Border</code>
	 *
	 * @param type <code>Class</code> not used
	 * @param attr <code>Attribute</code> value needs to provide Border type
	 *            name and optional parameter
	 * @return <code>Object</code> runtime type is subclass of
	 *         <code>AbstractBorder</code>
	 */
	@Override
	public Border convert(String value, Class<Border> type, Attribute attr, SwingEngine<?> engine)
		throws Exception
	{
		Matcher m = compoundBorderPattern.matcher(value);
		if ( m.matches() )
		{
			Border outside = convert(type, m.group(1), engine);
			Border inside = convert(type, m.group(2), engine);
			return BorderFactory.createCompoundBorder(outside, inside);
		}
		return convert(type, value, engine);
	}

	/**
	 *
	 * @param type
	 * @param borderString
	 * @param engine
	 * 
	 * @return
	 */
	protected Border convert(final Class<Border> type, final String borderString, SwingEngine<?> engine)
	{
		Matcher m = borderPattern.matcher(borderString);
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
			logger.error("Couldn't create border, " + borderString, e);
		}
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
		for ( int i = 0; (method == null) && i < METHODS.length; i++ )
		{
			// Does this method have convertible parameters?
			if ( (METHODS[i].getParameterTypes().length == pLen) && METHODS[i].getName().endsWith(borderType) )
			{
				method = METHODS[i];
				for ( int j = 0; j < method.getParameterTypes().length; j++ )
				{
					// If parameter type is a String then continue to examine the next method.
					if ( String.class.equals(method.getParameterTypes()[j]) )
						continue;
					
					// When parameter type is not a String, then does it have a converter?
					if ( null == cvtlib.getConverter(method.getParameterTypes()[j]) )
					{
						method = null;
						break;
					}
				}
			}
		}
		
		if ( method != null )
		{
			Object[] args = new Object[pLen];
			for ( int i = 0; i < pLen; ++i )
			{
				// fill argument array
				final String value = params[i].trim();
				final Converter<?> converter = cvtlib.getConverter(method.getParameterTypes()[i]);
				if ( converter != null )
				{
					String title = String.class.equals(converter.convertsTo()) ? "title" : "NA";
					final Attribute attrib = new Attribute(title, value, Attribute.CDATA_TYPE);
					args[i] = converter.convert((Class) method.getParameterTypes()[i], attrib, engine);
				}
				else
					args[i] = value;
			}
			border = (Border) method.invoke(null, args);
		}
		
		return border;
	}

	private TitledBorder convertTitledBorder(ConverterLibrary cvtlib, SwingEngine<?> engine, String[] params)
		throws Exception
	{
		if ( params == null || params.length == 0 )
		{
			return new TitledBorder((Border) null);
		}
		Attribute attrib = new Attribute("title", params[0].trim(), Attribute.CDATA_TYPE);
		Converter<String> converter = cvtlib.getConverter(String.class);
		String title = (String) converter.convert(String.class, attrib, engine);
		switch (params.length)
		{
			case 1:
				return new TitledBorder(title);
			case 2:
			{
				int titleJustification = getConstantValue(TitledBorder.class, params[1], TitledBorder.DEFAULT_JUSTIFICATION);
				return new TitledBorder((Border) null, title, titleJustification, TitledBorder.DEFAULT_POSITION);
			}
			case 3:
			{
				int titleJustification = getConstantValue(TitledBorder.class, params[1], TitledBorder.DEFAULT_JUSTIFICATION);
				int textPosition = getConstantValue(TitledBorder.class, params[2], TitledBorder.DEFAULT_POSITION);
				return new TitledBorder((Border) null, title, titleJustification, textPosition);
			}
			default:
			{
				int titleJustification = getConstantValue(TitledBorder.class, params[1],
					TitledBorder.DEFAULT_JUSTIFICATION);
				int textPosition = getConstantValue(TitledBorder.class, params[2], TitledBorder.DEFAULT_POSITION);
				return new TitledBorder((Border) null, title, titleJustification, textPosition,
					java.awt.Font.decode(params[3]));
			}
		}
	}
}
