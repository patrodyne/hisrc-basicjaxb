package org.swixml.layoutconverters;

import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.lang.reflect.Field;
import java.util.StringTokenizer;

import org.swixml.LayoutConverter;
import org.swixml.SwingEngine;
import org.swixml.converters.Util;
import org.swixml.dom.Attribute;
import org.w3c.dom.Element;

/**
 * A layout converter for <code>java.awt.GridBagLayout</code>.
 * 
 * <p>
 * <b>Examples:</b>
 * </p>
 * 
 * <pre>
 * &lt;panel layout="GridBagLayout"&gt;
 *   &lt;button&gt;
 *     &lt;gridbagconstraints
 *         gridx="1" gridy="2" gridwidth="3" gridheight="4" weightx="0.1" weighty="1"
 *         anchor="GridBagConstraints.NORTH" fill="GridBagConstraints.HORIZONTAL"
 *         insets="1,2,3,4" ipadx="5" ipady="6"/&gt;
 *   &lt;/button&gt;
 *   &lt;button&gt;
 *     &lt;gridbagconstraints gridx="2" gridy="3"/&gt;
 *   &lt;/button&gt;
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel&gt;
 *   &lt;layout type="GridBagLayout" columnWidths="200, 400, 150"&gt;
 *   &lt;button&gt;
 *     &lt;gridbagconstraints gridx="1" gridy="2"/&gt;
 *   &lt;/button&gt;
 *   &lt;button&gt;
 *     &lt;gridbagconstraints gridx="2" gridy="3"/&gt;
 *   &lt;/button&gt;
 * &lt;/panel&gt;
 * </pre>
 *
 * @author Karl Tauber
 * @author <a href="mailto:wolf@wolfpaulus.com">Wolf Paulus</a>
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class GridBagLayoutConverter implements LayoutConverter
{
	/**
	 * Returns "gridbaglayout".
	 */
	@Override
	public String getID()
	{
		return "gridbaglayout";
	}
	
	/**
	 * Returns the @{link GridBagLayout} class that knows how to lay out {@code Container}s.
	 * @return The @{link GridBagLayout} class that knows how to lay out {@code Container}s.
	 */
	@Override
	public Class<GridBagLayout> getLayoutManagerType()
	{
		return GridBagLayout.class;
	}

	/**
	 * <p>
	 * Creates a GridBagLayout instance.
	 * </p>
	 * 
	 * <p>
	 * <b>Examples for Valid XML attribute notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>layout="GridBagLayout"</code></li>
	 * <li><code>layout="GridBagLayout(rowWeights(0,0,1.0,0))"</code></li>
	 * <li><code>layout="GridBagLayout(columnWeights(0.5, 0.5, 1.0, 99.9))"</code></li>
	 * <li><code>layout="GridBagLayout(columnWidths(5, 5, 10, 33))"</code></li>
	 * <li><code>layout="GridBagLayout(rowHeights(5, 5, 10, 33))"</code></li>
	 * </ul>
	 */
	@Override
	public LayoutManager convertLayoutAttribute(final Attribute attr, SwingEngine<?> engine)
	{
		StringTokenizer st = new StringTokenizer(attr.getValue(), "(,)");
		st.nextToken(); // skip layout type
		//
		// Gridbag Layouts have some public arrays, accept one but only one:
		//
		// public double[] rowWeights
		// public double[] colWeights
		//
		GridBagLayout lm = new GridBagLayout();
		if ( st.hasMoreTokens() )
		{
			try
			{
				String fieldname = st.nextToken();
				Field field = GridBagLayout.class.getField(fieldname);
				if ( field != null )
				{
					Class<?> fieldtype = field.getType();
					if ( int[].class.equals(fieldtype) )
					{
						field.set(lm, Util.ia(st));
					}
					else if ( double[].class.equals(fieldtype) )
					{
						field.set(lm, Util.da(st));
					}
				}
			}
			catch (NoSuchFieldException e)
			{
				if ( SwingEngine.DEBUG_MODE )
					System.err.println(e.getMessage());
			}
			catch (SecurityException e)
			{
				if ( SwingEngine.DEBUG_MODE )
					System.err.println(e.getMessage());
			}
			catch (IllegalArgumentException e)
			{
				if ( SwingEngine.DEBUG_MODE )
					System.err.println(e.getMessage());
			}
			catch (IllegalAccessException e)
			{
				if ( SwingEngine.DEBUG_MODE )
					System.err.println(e.getMessage());
			}
		}
		return lm;
	}

	/**
	 * <p>
	 * Creates a GridBagLayout instance.
	 * </p>
	 * 
	 * <p>
	 * <b>Attributes:</b>
	 * </p>
	 * <ul>
	 * <li><code>columnWidths</code> (optional): The minimum column widths.</li>
	 * <li><code>rowHeights</code> (optional): The minimum row heights.</li>
	 * <li><code>columnWeights</code> (optional): The column weights.</li>
	 * <li><code>rowWeights</code> (optional): The row weights.</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Examples for Valid XML element notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>&lt;layout type="GridBagLayout"/&gt;</code></li>
	 * <li><code>&lt;layout type="GridBagLayout" columnWidths="5, 5, 10, 33" rowWeights="0,0,1.0,0"/&gt;</code></li>
	 * </ul>
	 */
	@Override
	public LayoutManager convertLayoutElement(final Element element)
	{
		GridBagLayout lm = new GridBagLayout();
		if ( element.getAttributeNode("columnWidths") != null )
			lm.columnWidths = Util.ia(new StringTokenizer(element.getAttribute("columnWidths"), ","));
		if ( element.getAttributeNode("rowHeights") != null )
			lm.rowHeights = Util.ia(new StringTokenizer(element.getAttribute("rowHeights"), ","));
		if ( element.getAttributeNode("columnWeights") != null )
			lm.columnWeights = Util.da(new StringTokenizer(element.getAttribute("columnWeights"), ","));
		if ( element.getAttributeNode("rowWeights") != null )
			lm.rowWeights = Util.da(new StringTokenizer(element.getAttribute("rowWeights"), ","));
		return lm;
	}

	/**
	 * Returns always <code>null</code>.
	 */
	@Override
	public Object convertConstraintsAttribute(final Attribute attr)
	{
		//
		// GridBagLayout does not use a per-component string.
		//
		// See java.awt.GridBagLayout.addLayoutComponent(String, Component)
		//
		return null;
	}

	/**
	 * Returns always <code>null</code>.
	 */
	@Override
	public Object convertConstraintsElement(final Element element)
	{
		//
		// Has no effect, since GridBagLayout does not use a per-component string.
		//
		// See java.awt.GridBagLayout.addLayoutComponent(String, Component)
		//
		return null;
	}
}
