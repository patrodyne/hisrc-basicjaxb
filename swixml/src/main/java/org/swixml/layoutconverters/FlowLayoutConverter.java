package org.swixml.layoutconverters;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.util.StringTokenizer;

import org.swixml.LayoutConverter;
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
	public LayoutManager convertLayoutAttribute(final Attribute attr)
	{
		StringTokenizer st = new StringTokenizer(attr.getValue(), "(,)");
		st.nextToken(); // skip layout type
		try
		{
			if ( st.hasMoreTokens() )
			{
				//
				// First FlowLayout parameter might be a pre-defined constant's
				// name
				//
				Object o = PrimitiveConverter.conv(null, new Attribute("NA", st.nextToken()), null);
				int[] para = Util.ia(st);
				//
				// Remaining paramters should be integer values
				//
				if ( para.length < 2 )
					return new FlowLayout(Integer.valueOf(o.toString()).intValue());
				else
					return new FlowLayout(Integer.valueOf(o.toString()).intValue(), para[0], para[1]);
			}
		}
		catch (Exception e)
		{
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
				Object o = PrimitiveConverter.conv(null, new Attribute("NA", value), null);
				align = Integer.valueOf(o.toString()).intValue();
			}
			catch (Exception ex)
			{
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
