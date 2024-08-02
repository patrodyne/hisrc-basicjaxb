package org.swixml;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import org.swixml.layoutconverters.BorderLayoutConverter;
import org.swixml.layoutconverters.CardLayoutConverter;
import org.swixml.layoutconverters.FlowLayoutConverter;
import org.swixml.layoutconverters.GridBagLayoutConverter;
import org.swixml.layoutconverters.GridLayoutConverter;

/**
 * <p>
 * The <code>LayoutConverterLibrary</code> contains all available
 * {@link LayoutConverter}s.
 * </p>
 *
 * @author Karl Tauber
 * @see LayoutConverter
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class LayoutConverterLibrary
{
	private static LayoutConverterLibrary instance = new LayoutConverterLibrary();
	private Map<String, LayoutConverter> layoutConverters = new HashMap<String, LayoutConverter>();
	private Map<String, LayoutConverter> layoutIDs = new HashMap<String, LayoutConverter>();

	/**
	 * The only available Ctor is private to make this a Singleton
	 */
	private LayoutConverterLibrary()
	{
		registerLayoutConverters();
	}

	/**
	 * Returns the single instance of the LayoutConverterLibrary.
	 */
	public static synchronized LayoutConverterLibrary getInstance()
	{
		return instance;
	}

	/**
	 * Returns all registered layout converters.
	 */
	public Map<String, LayoutConverter> getLayoutConverters()
	{
		return layoutConverters;
	}

	/**
	 * Registers <code>LayoutConverters</code> with the LayoutConverterLibrary.
	 */
	private void registerLayoutConverters()
	{
		register(BorderLayout.class, new BorderLayoutConverter());
		register(CardLayout.class, new CardLayoutConverter());
		register(FlowLayout.class, new FlowLayoutConverter());
		register(GridBagLayout.class, new GridBagLayoutConverter());
		register(GridLayout.class, new GridLayoutConverter());
		
		// TODO: Add GroupLayout, implement GroupLayoutConverter
		// register(GroupLayout.class, new GroupLayoutConverter());
		
		// 3rd party layout managers
		// register("com.jgoodies.forms.layout.FormLayout", new FormLayoutConverter());
	}

	/**
	 * Registers a LayoutConverter with the LayoutConverterLibrary.
	 * 
	 * @param layoutClass Type of the layout manager the LayoutConverter
	 *            creates.
	 * @param layoutConverter Instance of LayoutConverter able to convert
	 *            Strings into layout managers or layout constraints.
	 */
	public void register(Class<?> layoutClass, LayoutConverter layoutConverter)
	{
		register(layoutClass.getName(), layoutConverter);
	}

	/**
	 * Registers a LayoutConverter with the LayoutConverterLibrary.
	 * 
	 * @param layoutClassName Type name of the layout manager the
	 *            LayoutConverter creates.
	 * @param layoutConverter Instance of LayoutConverter able to convert
	 *            Strings into layout managers or layout constraints.
	 */
	public void register(String layoutClassName, LayoutConverter layoutConverter)
	{
		layoutConverters.put(layoutClassName, layoutConverter);
		layoutIDs.put(layoutConverter.getID().toLowerCase(), layoutConverter);
	}

	/**
	 * Returns a <code>LayoutConverter</code> instance, able to produce objects
	 * for the given layout manager <code>class</code>.
	 * 
	 * @param layoutClass Class of the object the <code>LayoutConverter</code>
	 *            needs to produce.
	 * @return Instance of the LayoutConverter class.
	 */
	public LayoutConverter getLayoutConverter(Class<?> layoutClass)
	{
		return layoutConverters.get(layoutClass.getName());
	}

	/**
	 * Returns a <code>LayoutConverter</code> instance, able to produce objects
	 * for the given layout manager <code>id</code> (see
	 * {@link LayoutConverter#getID()}).
	 * 
	 * @param id Identifier of the layout manager the
	 *            <code>LayoutConverter</code> needs to produce.
	 * @return Instance of the LayoutConverter class.
	 */
	public LayoutConverter getLayoutConverterByID(String id)
	{
		return layoutIDs.get(id.toLowerCase());
	}
}
