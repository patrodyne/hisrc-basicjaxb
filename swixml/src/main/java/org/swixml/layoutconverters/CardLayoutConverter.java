package org.swixml.layoutconverters;

import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.util.StringTokenizer;

import org.swixml.LayoutConverter;
import org.swixml.SwingEngine;
import org.swixml.converters.Util;
import org.swixml.dom.Attribute;
import org.w3c.dom.Element;

/**
 * A layout converter for <code>java.awt.CardLayout</code>.
 * 
 * <p>
 * <b>Examples:</b>
 * </p>
 * 
 * <pre>
 * &lt;panel layout="CardLayout"&gt;
 *   &lt;panel constraints="card1" /&gt;
 *   &lt;panel constraints="card2" /&gt;
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel layout="CardLayout(10,20)"&gt;
 *   &lt;panel constraints="firstCard" /&gt;
 *   &lt;panel constraints="secondCard" /&gt;
 * &lt;/panel&gt;
 * </pre>
 *
 * <pre>
 * &lt;panel&gt;
 *   &lt;layout type="CardLayout" hgap="10" vgap="20"/&gt;
 *   &lt;panel constraints="firstCard" /&gt;
 *   &lt;panel constraints="secondCard" /&gt;
 * &lt;/panel&gt;
 * </pre>
 * 
 * <p>
 * Here is how to access the card layout manager of a component installed by
 * SwixML
 * <code>(CardLayout)((Container)swingEngine.find("id_of_my_CLed_comp")).getLayout()</code>
 * </p>
 *
 * @author Karl Tauber
 * @author <a href="mailto:wolf@wolfpaulus.com">Wolf Paulus</a>
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class CardLayoutConverter implements LayoutConverter
{
	/**
	 * Returns "cardlayout".
	 */
	@Override
	public String getID()
	{
		return "cardlayout";
	}

	/**
	 * Returns the @{link CardLayout} class that knows how to lay out {@code Container}s.
	 * @return The @{link CardLayout} class that knows how to lay out {@code Container}s.
	 */
	@Override
	public Class<CardLayout> getLayoutManagerType()
	{
		return CardLayout.class;
	}
	
	/**
	 * <p>
	 * Creates a CardLayout instance.
	 * </p>
	 * 
	 * <p>
	 * <b>Examples for Valid XML attribute notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>layout="CardLayout"</code></li>
	 * <li><code>layout="CardLayout(int hgap, int vgap)"</code></li>
	 * </ul>
	 */
	@Override
	public LayoutManager convertLayoutAttribute(final Attribute attr, SwingEngine<?> engine)
	{
		StringTokenizer st = new StringTokenizer(attr.getValue(), "(,)");
		st.nextToken(); // skip layout type
		int[] para = Util.ia(st);
		if ( para.length < 2 )
			return new CardLayout();
		else
			return new CardLayout(para[0], para[1]);
	}

	/**
	 * <p>
	 * Creates a CardLayout instance.
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
	 * <li><code>&lt;layout type="CardLayout"/&gt;</code></li>
	 * <li><code>&lt;layout type="CardLayout" hgap="10" vgap="20"/&gt;</code></li>
	 * </ul>
	 */
	@Override
	public LayoutManager convertLayoutElement(final Element element)
	{
		int hgap = Util.getInteger(element, "hgap", 0);
		int vgap = Util.getInteger(element, "vgap", 0);
		return new CardLayout(hgap, vgap);
	}

	/**
	 * Converts CardLayout constraints. The attribute value is used as card
	 * name.
	 * 
	 * <p>
	 * <b>Examples for Valid XML attribute notations:</b>
	 * </p>
	 * <ul>
	 * <li><code>constraints="cardname"</code></li>
	 * </ul>
	 */
	@Override
	public Object convertConstraintsAttribute(final Attribute attr)
	{
		//
		// CardLayout accepts only constraints of type String.
		// The card layout stores this string and the card as a key-value
	    // pair that can be used for random access to a particular card.
		//
		// See java.awt.CardLayout.addLayoutComponent(Component, Object).
		//
		return attr.getValue();
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
