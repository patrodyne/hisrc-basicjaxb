package org.swixml.layoutconverters;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.StringTokenizer;

import org.swixml.LayoutConverter;
import org.swixml.SwingEngine;
import org.swixml.converters.PrimitiveConverter;
import org.swixml.converters.Util;
import org.swixml.dom.Attribute;
import org.w3c.dom.Element;

/**
 * A layout converter for <code>java.awt.FlowLayout</code>.
 * 
 * <p>
 * <b>Examples:</b>
 * </p>
 * 
 * <pre>
 * &lt;panel layout="FlowLayout"&gt;
 *   &lt;panel ... /&gt;
 *   &lt;panel ... /&gt;
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel layout="FlowLayout(FlowLayout.RIGHT)"&gt;
 *   ...
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel layout="FlowLayout(FlowLayout.LEFT, 1, 2)"&gt;
 *   ...
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel&gt;
 *   &lt;layout type="FlowLayout" alignment="FlowLayout.RIGHT"/&gt;
 *   ...
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel&gt;
 *   &lt;layout type="FlowLayout" alignment="FlowLayout.LEFT" hgap="10" vgap="20"/&gt;
 *   ...
 * &lt;/panel&gt;
 * </pre>
 *
 * @author Karl Tauber
 * @author <a href="mailto:wolf@wolfpaulus.com">Wolf Paulus</a>
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class FlowLayoutConverter implements LayoutConverter
{
	/**
	 * Returns "flowlayout".
	 */
	@Override
	public String getID()
	{
		return "flowlayout";
	}

	/**
	 * Returns the @{link FlowLayout} class that knows how to lay out {@code Container}s.
	 * @return The @{link FlowLayout} class that knows how to lay out {@code Container}s.
	 */
	@Override
	public Class<FlowLayout> getLayoutManagerType()
	{
		return FlowLayout.class;
	}

	/**
	 * <p>
	 * Creates a FlowLayout instance.
	 * </p>
	 * 
	 * <p>
	 * <b>Examples for Valid XML attribute notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>layout="FlowLayout"</code></li>
	 * <li><code>layout="FlowLayout(int align)"</code></li>
	 * <li><code>layout="FlowLayout(int align, int hgap, int vgap)"</code></li>
	 * </ul>
	 */
	@Override
	public LayoutManager convertLayoutAttribute(final Attribute attr, SwingEngine<?> engine)
	{
		StringTokenizer st = new StringTokenizer(attr.getValue(), "(,)");
		st.nextToken(); // skip layout type
		try
		{
			if ( st.hasMoreTokens() )
			{
				//
				// The first parameter might be a pre-defined constant's name
				//
				Class<Object> type = Object.class;
				Attribute tokenAttr = new Attribute("align", st.nextToken());
				Object o = PrimitiveConverter.conv(type, tokenAttr, engine);
				int[] para = Util.ia(st);
				
				//
				// The remaining parameters should be integer values
				//
				if ( para.length < 2 )
					return new FlowLayout(Integer.valueOf(o.toString()).intValue());
				else
					return new FlowLayout(Integer.valueOf(o.toString()).intValue(), para[0], para[1]);
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return new FlowLayout();
	}

	/**
	 * <p>
	 * Creates a FlowLayout instance.
	 * </p>
	 * 
	 * <p>
	 * <b>Attributes:</b>
	 * </p>
	 * <ul>
	 * <li><code>alignment</code> (optional): The horizontal alignment.</li>
	 * <li><code>hgap</code> (optional): The horizontal gap.</li>
	 * <li><code>vgap</code> (optional): The vertical gap.</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Examples for Valid XML element notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>&lt;layout type="FlowLayout"/&gt;</code></li>
	 * <li><code>&lt;layout type="FlowLayout" alignment="FlowLayout.LEFT"/&gt;</code></li>
	 * <li><code>&lt;layout type="FlowLayout" alignment="FlowLayout.LEFT" hgap="10" vgap="20"/&gt;</code></li>
	 * </ul>
	 */
	@Override
	public LayoutManager convertLayoutElement(final Element element)
	{
		int align = FlowLayout.CENTER;
		if ( element.getAttributeNode("alignment") != null )
		{
			String value = element.getAttribute("alignment");
			try
			{
				Class<Object> type = Object.class;
				Attribute valueAttr = new Attribute("align", value);
				SwingEngine<?> engine = null;
				Object o = PrimitiveConverter.conv(type, valueAttr, engine);
				align = Integer.valueOf(o.toString()).intValue();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		int hgap = Util.getInteger(element, "hgap", 5);
		int vgap = Util.getInteger(element, "vgap", 5);
		return new FlowLayout(align, hgap, vgap);
	}

	/**
	 * Returns always <code>null</code>.
	 */
	@Override
	public Object convertConstraintsAttribute(final Attribute attr)
	{
		//
		// FlowLayout does not use constraints.
		//
		// See java.awt.FlowLayout.addLayoutComponent(String, Component)
		//
		return null;
	}

	/**
	 * Returns always <code>null</code>.
	 */
	@Override
	public Object convertConstraintsElement(final Element element)
	{
		return null;
	}
}
