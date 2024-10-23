package org.swixml.converters;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.tree.TreeSelectionModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swixml.Converter;
import org.swixml.Localizer;
import org.swixml.LogUtil;
import org.swixml.Parser;
import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;
import org.swixml.el.ELUtility;

/**
 * The <code>PrimitiveConverter</code> class defines a converter that creates
 * primitive objects (wrapper), based on a provided input Class and String.
 *
 * @author <a href="mailto:wolf@wolfpaulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see org.swixml.ConverterLibrary
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class PrimitiveConverter	implements Converter<Object>,
	ScrollPaneConstants, KeyEvent, InputEvent
{
	private static final Logger logger = LoggerFactory.getLogger(PrimitiveConverter.class);

	public static final PrimitiveConverter instance = new PrimitiveConverter();
	
	/** converter's return type */
	public static final Class<Object> TEMPLATE = Object.class;
	
	/** map contains all constant provider types */
	private static Map<String, Class<?>> dictionaries = new HashMap<String, Class<?>>();
	
	/**
	 * Static Initializer, setting up the initial constant providers
	 */
	static
	{
		addConstantProvider(JTabbedPane.class);
		addConstantProvider(JScrollPane.class);
		addConstantProvider(JSplitPane.class);
		addConstantProvider(GridBagConstraints.class);
		addConstantProvider(FlowLayout.class);
		addConstantProvider(ListSelectionModel.class);
		addConstantProvider(TreeSelectionModel.class);
		addConstantProvider(JDialog.class);
		addConstantProvider(JFrame.class);
		addConstantProvider(TitledBorder.class);
		addConstantProvider(JComponent.class);
		//
		// See also: PrimitiveConverter.convConstants(String)
		//
	}

	protected PrimitiveConverter()
	{
	}

	/**
	 * Converts {@link Attribute} value into java primitive type.
	 * 
	 * @param type <code>Class</code> target type
	 * @param attr <code>Attribute</code> value field needs to provide convertible String
	 * @param engine <code>SwingEngine</code> rendering swingEngine to convert an XML descriptor into a Swing UI.
	 * 
	 * @return Primitive wrapped into wrapper object
	 * 
	 * @throws NoSuchFieldException In case no class a field matching field name had been registered with this converter
	 * @throws IllegalAccessException If a matching field can not be accessed
	 */
	public static <P> P conv(final Class<P> type, final Attribute attr, final SwingEngine<?> engine)
		throws NoSuchFieldException, IllegalAccessException
	{
		if ( attr == null )
			return null;
		return conv(attr.getValue(), type, attr, engine);
	}

	/**
	 * Converts {@link String} into java primitive type.
	 * 
	 * @param value The evaluated value.
	 * @param type <code>Class</code> target type.
	 * @param attr <code>Attribute</code> value field needs to provide convertible String.
	 * @param engine <code>SwingEngine</code> rendering swingEngine to convert an XML descriptor into a Swing UI.
	 * 
	 * @return Primitive wrapped into wrapper object
	 * 
	 * @throws NoSuchFieldException In case no class a field matching field name had been registered with this converter
	 * @throws IllegalAccessException If a matching field can not be accessed
	 */
	@SuppressWarnings("unchecked")
	protected static <P> P conv(String value, final Class<P> type, final Attribute attr, final SwingEngine<?> engine)
		throws NoSuchFieldException, IllegalAccessException
	{
		final Localizer localizer = Util.getLocalizer(engine);
		final Attribute a = (Attribute) attr.clone();
		Object obj = null;
		
		a.setValue((Parser.LOCALIZED_ATTRIBUTES.contains(a.getLocalName().toLowerCase()))
			? localizer.getString(value)
			: value);
		
		try
		{
			if ( type.isPrimitive() )
			{
				if ( Boolean.TYPE.equals(type) )
					obj = Boolean.valueOf(a.getBooleanValue());
				else if ( Integer.TYPE.equals(type) )
					obj = Integer.valueOf(a.getIntValue());
				else if ( Long.TYPE.equals(type) )
					obj = Long.valueOf(a.getLongValue());
				else if ( Float.TYPE.equals(type) )
					obj = Float.valueOf(a.getFloatValue());
				else if ( Double.TYPE.equals(type) )
					obj = Double.valueOf(a.getDoubleValue());
			}
			else
			{
				if ( Boolean.class.equals(type) )
					obj = Boolean.valueOf(a.getBooleanValue());
				else if ( Integer.class.equals(type) )
					obj = Integer.valueOf(a.getIntValue());
				else if ( Long.class.equals(type) )
					obj = Long.valueOf(a.getLongValue());
				else if ( Float.class.equals(type) )
					obj = Float.valueOf(a.getFloatValue());
				else if ( Double.class.equals(type) )
					obj = Double.valueOf(a.getDoubleValue());
			}
		}
		catch (Exception ex)
		{
			// intent. empty
			logger.debug("Cannot convert attribute [{}] as [{}]", attr.getLocalName(), type, ex);
		}
		
		// Try to convert a reference to a constant value.
		if ( obj == null )
		{
			String av1 = a.getValue();
			String av2 = av1;
			try
			{
				int k = av1.indexOf('.');
				if ( k >= 0 )
				{
					av2 = av1.substring(k+1);
					Class<?> pp = dictionaries.get(av1.substring(0, k));
					obj = pp.getField(av2).get(pp);
				}
				else
				{
					//
					// Try to find the given value as a Constant in
					// the "this" bean in the EL context.
					//
					obj = engine.getELProcessor().getValue("this", Object.class);
					if ( obj != null )
					{
						Class<? extends Object> clz;
						if ( obj instanceof Class<?> )
							clz = (Class<? extends Object>) obj;
						else
							clz = obj.getClass();
						obj = clz.getField(av1).get(clz);
					}
					else
						obj = convConstants(av1);
				}
			}
			catch (Exception ex)
			{
				obj = convConstants(av2);
			}
		}
		
		return (P) obj;
	}

	//
	// Try to find the given value as a Constant in PrimitiveConverter:
	// SwingConstants, KeyEvent, InputEvent.
	//
	protected static Object convConstants(String constant)
	{
		Object obj = null;
		try
		{
			obj = SwingConstants.class.getField(constant).get(SwingConstants.class);
		}
		catch ( Exception ex1 )
		{
			try
			{
				obj = KeyEvent.class.getField(constant).get(KeyEvent.class);
			}
			catch ( Exception ex2 )
			{
				try
				{
					obj = InputEvent.class.getField(constant).get(InputEvent.class);
				}
				catch ( Exception ex3 )
				{
					logger.warn("cannot convert constant [{}]", constant, ex3);
				}
			}
		}
		return obj;
	}

	/**
	 * retrieve a constant field from class
	 *
	 * @param clazz Constant Provider
	 * @param value Constant name
	 * @param def default value
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T, P> T getConstantValue(Class<P> clazz, String value, T def)
	{
		if ( clazz == null )
			throw new IllegalArgumentException("class is null!");
		
		if ( value == null )
			throw new IllegalArgumentException("value is null!");
		
		Matcher m = p.matcher(value.trim());
		
		if ( !m.matches() )
		{
			LogUtil.logger
				.warn(String.format("value [%s] is not valid constant expression. Default is returned!", value));
			return def;
		}
		
		String cn = m.group(1);
		if ( cn != null && !clazz.getSimpleName().equalsIgnoreCase(cn) )
		{
			LogUtil.logger.warn(
				String.format("class defined in constant expression [%s] doesn't match with given class [%s]. Ignored!",
					cn, clazz.getSimpleName()));
		}
		String cv = m.group(2);
		
		try
		{
			Field f = clazz.getField(cv);
			Object vv = f.get(null);
			if ( vv == null )
			{
				LogUtil.logger.warn(String.format("constant field [%s] in class [%s] is null. Default is returned!",
					cv, clazz.getSimpleName()));
				return def;
			}
			return (T) vv;
		}
		catch (NoSuchFieldException ex)
		{
			LogUtil.logger.warn(String.format("constant field [%s] not found in class [%s] Default is returned!", cv,
				clazz.getSimpleName()));
			return def;
		}
		catch (Exception ex)
		{
			LogUtil.logger.warn(
				String.format("exception reading constant field [%s] in class [%s]. Default is returned!", cv,
					clazz.getSimpleName()), ex);
			return def;
		}
	}

	/**
	 * Converts String into java primitive type.
	 * 
	 * @param type <code>Class</code> target type
	 * @param attr <code>Attribute</code> value field needs to provide convertible String
	 * 
	 * @return <code>Object</code> primitive wrapped into wrapper object
	 * 
	 * @throws Exception
	 */
	@Override
	public Object convert(final Class<Object> type, final Attribute attr, final SwingEngine<?> engine)
		throws Exception
	{
		logger.trace("type: " + type + "; attr: " + attr + "; swingEngine: " + engine);
		
		Object value = null;
		if ( engine != null )
			value = ELUtility.evaluateAttribute(engine.getELProcessor(), attr);
		
		if ( value != null)
			return PrimitiveConverter.conv(value.toString(), type, attr, engine);
		else
			return null;
	}

	/**
	 * A <code>Converters</code> conversTo method informs about the Class type
	 * the converter is returning when its <code>convert</code> method is called
	 * 
	 * @return <code>Class</code> - the Class the converter is returning when its convert method is called
	 */
	@Override
	public Class<Object> convertsTo()
	{
		return TEMPLATE;
	}

	/**
	 * Adds a new class or interface to the dictionary of primitive providers.
	 * 
	 * @param clazz <code>Class</code> providing primitive constants / public (final) fields
	 */
	public static void addConstantProvider(final Class<?> clazz)
	{
		dictionaries.put(clazz.getSimpleName(), clazz);
	}

	private static Pattern p = Pattern.compile("(?:(\\w+)[.])?(\\w+)");
}
