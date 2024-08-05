package org.swixml;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

import org.swixml.converters.ActionConverter;
import org.swixml.converters.BorderConverter;
import org.swixml.converters.ColorConverter;
import org.swixml.converters.ComponentConverter;
import org.swixml.converters.DimensionConverter;
import org.swixml.converters.FontConverter;
import org.swixml.converters.ImageConverter;
import org.swixml.converters.ImageIconConverter;
import org.swixml.converters.InsetsConverter;
import org.swixml.converters.KeyStrokeConverter;
import org.swixml.converters.LocaleConverter;
import org.swixml.converters.PointConverter;
import org.swixml.converters.PrimitiveConverter;
import org.swixml.converters.RectangleConverter;
import org.swixml.converters.StringConverter;

/**
 * <p>
 * The <code>ConverterLibrary</code> contains all available Converters,
 * converting Strings.
 * </p>
 * <p>
 * General purpose data type converters are able to convert Strings into objects
 * that are usually as parameters when setters are called on javax.swing
 * objects.
 * </p>
 * 
 * <p><b>Available Converter include converters able to produce:</b></p>
 * <ul>
 * <li>Primitives</li>
 * <li>Dimension</li>
 * <li>Color</li>
 * <li>Border</li>
 * <li>etc.</li>
 * </ul>
 * 
 * <p><b>Example String inputs could look like this:</b></p>
 * <ul>
 * <li>MatteBorder(4,4,4,4,red)</li>
 * <li>FFCCEE</li>
 * <li>BorderLayout.CENTER</li>
 * <li>2,2,2,2</li>
 * </ul>
 * 
 * <p>Date: Dec 16, 2002</p>
 *
 * @author <a href="mailto:wolf@wolfpaulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see org.swixml.Converter
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class ConverterLibrary
{
	private static ConverterLibrary instance = new ConverterLibrary();
	private Map<Class<?>, Converter<?>> converters = new HashMap<Class<?>, Converter<?>>();

	/**
	 * The only available Ctor is private to make this a Singleton
	 */
	private ConverterLibrary()
	{
		registerConverters();
	}

	/**
	 * @return <code>ConverterLibrary</code> the single instance of the
	 *         ConverterLibrary.
	 */
	public static synchronized ConverterLibrary getInstance()
	{
		return instance;
	}

	/**
	 * @return <code>Map</code> - all registered converters.
	 * 
	 *         <pre>
	 * Use a class to get to the converters
	 *         </pre>
	 */
	public Map<Class<?>, Converter<?>> getConverters()
	{
		return converters;
	}

	/**
	 * Registers <code>Converters</code> with the ConverterLibrary.
	 */
	private void registerConverters()
	{
		register(Action.class, new ActionConverter());
		register(Border.class, new BorderConverter());
		register(Color.class, new ColorConverter());
		register(Component.class, new ComponentConverter());
		register(Dimension.class, new DimensionConverter());
		register(Font.class, new FontConverter());
		register(Image.class, new ImageConverter());
		register(Icon.class, new ImageIconConverter());
		register(ImageIcon.class, new ImageIconConverter());
		register(Insets.class, new InsetsConverter());
		register(KeyStroke.class, new KeyStrokeConverter());
		register(Locale.class, new LocaleConverter());
		register(Point.class, new PointConverter());
		register(Rectangle.class, new RectangleConverter());
		register(String.class, new StringConverter());
		//
		// Register the PrimitiveConverter class for java primitive types
		//
		register(Boolean.TYPE, PrimitiveConverter.instance);
		register(Integer.TYPE, PrimitiveConverter.instance);
		register(Long.TYPE, PrimitiveConverter.instance);
		register(Float.TYPE, PrimitiveConverter.instance);
		register(Double.TYPE, PrimitiveConverter.instance);
		register(Boolean.class, PrimitiveConverter.instance);
		register(Integer.class, PrimitiveConverter.instance);
		register(Long.class, PrimitiveConverter.instance);
		register(Float.class, PrimitiveConverter.instance);
		register(Double.class, PrimitiveConverter.instance);
	}

	/**
	 * Registers a Converter with the ConverterLibrary
	 *
	 * @param converter <code>Converter</code> Instance of Converter able to
	 *            convert Strings into objects of the given type
	 */
	public void register(Converter<?> converter)
	{
		converters.put(converter.convertsTo(), converter);
	}

	/**
	 * Registers a Converter with the ConverterLibrary
	 *
	 * @param template <code>Class</code> type of the objects the Converter
	 *            creates
	 * @param converter <code>Converter</code> Instance of Converter able to
	 *            convert Strings into objects of the given type
	 */
	public void register(Class<?> template, Converter<?> converter)
	{
		converters.put(template, converter);
	}

	/**
	 * Indicates if a the ConverterLibary has a Converter producing instances of
	 * the given Class.
	 *
	 * @param template <code>Class</code>
	 * @return <code>boolean</code> true, if the ConverterLibrary has a
	 *         Converter to produce an instances of the given class.
	 */
	public boolean hasConverter(Class<?> template)
	{
		boolean found = converters.keySet().contains(template);
		Iterator<Converter<?>> it = converters.values().iterator();
		while (!found && it != null && it.hasNext())
		{
			found = template.isAssignableFrom((it.next()).convertsTo());
		}
		return found;
	}

	/**
	 * Returns a <code>Converter</code> instance, able to produce objects of the
	 * given <code>class</code>
	 *
	 * @param template <code>Class</code> Class of the object the
	 *            <code>Converter</code> needs to produce.
	 * 
	 * @return <code>Converter</code> - instance of the given Converter class.
	 */
	@SuppressWarnings("unchecked")
	public <T> Converter<T> getConverter(Class<T> template)
	{
		return (Converter<T>) converters.get(template);
	}
}
