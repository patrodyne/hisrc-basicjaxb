package org.swixml.layoutconverters;

import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.StringTokenizer;

import org.swixml.LayoutConverter;
import org.swixml.converters.PrimitiveConverter;
import org.swixml.converters.Util;
import org.swixml.dom.Attribute;
import org.w3c.dom.Element;

/**
 * A layout converter for <code>java.awt.BorderLayout</code>.
 * 
 * <p>
 * <b>Examples:</b>
 * </p>
 * 
 * <pre>
 * &lt;panel layout="BorderLayout"&gt;
 *   &lt;panel constraints="BorderLayout.NORTH" /&gt;
 *   &lt;panel constraints="BorderLayout.CENTER" /&gt;
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel layout="BorderLayout(10,20)"&gt;
 *   &lt;panel constraints="NORTH" /&gt;
 *   &lt;panel constraints="CENTER" /&gt;
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel&gt;
 *   &lt;layout type="BorderLayout" hgap="10" vgap="20"/&gt;
 *   &lt;panel constraints="NORTH" /&gt;
 *   &lt;panel constraints="CENTER" /&gt;
 * &lt;/panel&gt;
 * </pre>
 *
 * @author Karl Tauber
 * @author <a href="mailto:wolf@wolfpaulus.com">Wolf Paulus</a>
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class BorderLayoutConverter implements LayoutConverter
{
	/**
	 * Returns "borderlayout".
	 */
	@Override
	public String getID()
	{
		return "borderlayout";
	}

	/**
	 * <p>
	 * Creates a BorderLayout instance.
	 * </p>
	 * 
	 * <p>
	 * <b>Examples for Valid XML attribute notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>layout="BorderLayout"</code></li>
	 * <li><code>layout="BorderLayout(int hgap, int vgap)"</code></li>
	 * </ul>
	 */
	@Override
	public LayoutManager convertLayoutAttribute(final Attribute attr)
	{
		StringTokenizer st = new StringTokenizer(attr.getValue(), "(,)");
		st.nextToken(); // skip layout type
		int[] para = Util.ia(st);
		if ( para.length < 2 )
			return new BorderLayout();
		else
			return new BorderLayout(para[0], para[1]);
	}

	/**
	 * <p>
	 * Creates a BorderLayout instance.
	 * </p>
	 * 
	 * <p>
	 * <b>Attributes:</b>
	 * </p>
	 * <ul>
	 * <li><code>hgap</code> (optional): The horizontal gap.</li>
	 * <li><code>vgap</code> (optional): The vertical gap.</li>
	 * </ul>
	 * 
	 * <p>
	 * <b>Examples for Valid XML element notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>&lt;layout type="BorderLayout"/&gt;</code></li>
	 * <li><code>&lt;layout type="BorderLayout" hgap="10" vgap="20"/&gt;</code></li>
	 * </ul>
	 */
	@Override
	public LayoutManager convertLayoutElement(final Element element)
	{
		int hgap = Util.getInteger(element, "hgap", 0);
		int vgap = Util.getInteger(element, "vgap", 0);
		return new BorderLayout(hgap, vgap);
	}

	/**
	 * Converts BorderLayout constraints.
	 * 
	 * <p>
	 * <b>Examples for Valid XML attribute notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>constraints="BorderLayout.CENTER"</code></li>
	 * <li><code>constraints="BorderLayout.NORTH"</code></li>
	 * <li><code>constraints="EAST"</code></li>
	 * </ul>
	 */
	@Override
	public Object convertConstraintsAttribute(final Attribute attr)
	{
		String value = attr.getValue();
		
//		Field[] fields = BorderLayout.class.getFields();
//		for ( int i = 0; i < fields.length; i++ )
//		{
//			if ( value.endsWith(fields[i].getName()) )
//			{
//				try
//				{
//					return fields[i].get(BorderLayout.class);
//				}
//				catch (Exception e)
//				{
//				}
//				break;
//			}
//		}
//		return null;
		
		return PrimitiveConverter.getConstantValue(BorderLayout.class, value, null);
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
