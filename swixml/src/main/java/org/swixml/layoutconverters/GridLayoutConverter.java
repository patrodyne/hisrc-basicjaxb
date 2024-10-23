package org.swixml.layoutconverters;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.StringTokenizer;

import org.swixml.LayoutConverter;
import org.swixml.SwingEngine;
import org.swixml.converters.Util;
import org.swixml.dom.Attribute;
import org.w3c.dom.Element;

/**
 * A layout converter for <code>java.awt.GridLayout</code>.
 * 
 * <p>
 * <b>Examples:</b>
 * </p>
 * 
 * <pre>
 * &lt;panel layout="GridLayout"&gt;
 *   &lt;panel ... /&gt;
 *   &lt;panel ... /&gt;
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel layout="GridLayout(2,4)"&gt;
 *   ...
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel layout="GridLayout(2,4,10,20)"&gt;
 *   ...
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel&gt;
 *   &lt;layout type="GridLayout" rows="2" columns="4"/&gt;
 *   ...
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel&gt;
 *   &lt;layout type="GridLayout" rows="2" columns="4" hgap="10" vgap="20"/&gt;
 *   ...
 * &lt;/panel&gt;
 * </pre>
 *
 * @author Karl Tauber
 * @author <a href="mailto:wolf@wolfpaulus.com">Wolf Paulus</a>
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class GridLayoutConverter implements LayoutConverter
{
	/**
	 * Returns "gridlayout".
	 */
	@Override
	public String getID()
	{
		return "gridlayout";
	}

	/**
	 * Returns the @{link GridLayout} class that knows how to lay out {@code Container}s.
	 * @return The @{link GridLayout} class that knows how to lay out {@code Container}s.
	 */
	@Override
	public Class<GridLayout> getLayoutManagerType()
	{
		return GridLayout.class;
	}
	
	/**
	 * <p>
	 * Creates a GridLayout instance.
	 * </p>
	 * 
	 * <p>
	 * <b>Examples for Valid XML attribute notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>layout="GridLayout"</code></li>
	 * <li><code>layout="GridLayout(int rows, int cols)"</code></li>
	 * <li><code>layout="GridLayout(int rows, int cols, int hgap, int vgap)"</code></li>
	 * </ul>
	 */
	@Override
	public LayoutManager convertLayoutAttribute(final Attribute attr, SwingEngine<?> engine)
	{
		StringTokenizer st = new StringTokenizer(attr.getValue(), "(,)");
		st.nextToken(); // skip layout type
		int[] para = Util.ia(st);
		if ( 4 <= para.length )
			return new GridLayout(para[0], para[1], para[2], para[3]);
		else if ( 2 <= para.length )
			return new GridLayout(para[0], para[1]);
		else
			return new GridLayout();
	}

	/**
	 * <p>
	 * Creates a GridLayout instance.
	 * </p>
	 * 
	 * <p>
	 * <b>Attributes:</b>
	 * </p>
	 * <ul>
	 * <li><code>rows</code> (optional): The number of rows.</li>
	 * <li><code>columns</code> (optional): The number of columns.</li>
	 * <li><code>hgap</code> (optional): The horizontal gap.</li>
	 * <li><code>vgap</code> (optional): The vertical gap.</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Examples for Valid XML element notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>&lt;layout type="GridLayout"/&gt;</code></li>
	 * <li><code>&lt;layout type="GridLayout" rows="4" columns="5"/&gt;</code></li>
	 * <li><code>&lt;layout type="GridLayout" rows="2" columns="4" hgap="10" vgap="20"/&gt;</code></li>
	 * </ul>
	 */
	@Override
	public LayoutManager convertLayoutElement(final Element element)
	{
		int rows = Util.getInteger(element, "rows", 1);
		int cols = Util.getInteger(element, "columns", 0);
		int hgap = Util.getInteger(element, "hgap", 0);
		int vgap = Util.getInteger(element, "vgap", 0);
		return new GridLayout(rows, cols, hgap, vgap);
	}

	/**
	 * Returns always <code>null</code>.
	 */
	@Override
	public Object convertConstraintsAttribute(final Attribute attr)
	{
		//
		// GridLayout does not use constraints.
		//
		// See java.awt.GridLayout.addLayoutComponent(String, Component)
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
