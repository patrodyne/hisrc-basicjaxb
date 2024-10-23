package org.swixml.processor;

import static org.swixml.Parser.ATTR_CONSTRAINTS;
import static org.swixml.Parser.TAG_CONSTRAINTS;
import static org.swixml.Parser.TAG_GRIDBAGCONSTRAINTS;
import static org.swixml.dom.DOMUtil.getChildByTagName;

import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;

import org.swixml.LayoutConverter;
import org.swixml.LayoutConverterLibrary;
import org.swixml.Parser;
import org.swixml.dom.Attribute;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public class ConstraintsTagProcessor implements TagProcessor
{
	public static final TagProcessor instance = new ConstraintsTagProcessor();

	@SuppressWarnings("unchecked")
	public static <T extends Component> T processComponent(Parser p, Object obj, org.w3c.dom.Element child,
		LayoutManager layoutMgr) throws Exception
	{
		T component = null;
		
		//
		// A constraints ATTRIBUTE is removed from the child tag then used to add
		// the child into the current object.
		//
		Attr constraintsAttr = child.getAttributeNode(ATTR_CONSTRAINTS);
		Object constraintsObj = null;
		if ( constraintsAttr != null && layoutMgr != null )
		{
			// ATTR_CONSTRAINTS won't be used in getSwing(child)
			child.removeAttribute(ATTR_CONSTRAINTS); 
			
			// ATTR_CONSTRAINTS may be converted to a layout specific object.
			LayoutConverter layoutConverter = LayoutConverterLibrary.getInstance()
				.getLayoutConverter(layoutMgr.getClass());
			if ( layoutConverter != null )
			{
				// Converts the given "constraints" attribute to a
				// layout manager specific constraints object.
				constraintsObj = layoutConverter.convertConstraintsAttribute(new Attribute(constraintsAttr));
			}
		}
		
		//
		// A constraints ELEMENT may be used to add the child into the current object.
		//
		Element constraintsElement = getChildByTagName(child, TAG_CONSTRAINTS); 
		if ( constraintsElement != null && layoutMgr != null )
		{
			LayoutConverter layoutConverter = LayoutConverterLibrary.getInstance()
				.getLayoutConverter(layoutMgr.getClass());
			if ( layoutConverter != null )
			{
				// Converts the given "constraints" element to a layout manager specific
				// constraints object. The element may have any layout manager specific
				// attributes or child elements.
				constraintsObj = layoutConverter.convertConstraintsElement(constraintsElement);
			}
		}
		
		//
		// A constraints or GridBagConstraints grand-childtag is not added at all;
		// but, used to add the child into this container.
		//
		// Fix Issue 74, https://github.com/bsorrentino/swixml2/issues/74
		org.w3c.dom.Element grandchild = getChildByTagName(child,
			TAG_GRIDBAGCONSTRAINTS, child.getNamespaceURI()); 
		if ( grandchild != null )
		{
			component = (T) p.getSwing(child, null);
			p.addChild((Container) obj, component, p.getSwing(grandchild, null));
		}
		else if ( !child.getLocalName().equals(TAG_CONSTRAINTS)
			&& !child.getLocalName().equals(TAG_GRIDBAGCONSTRAINTS) )
		{
			component = (T) p.getSwing(child, null);
			p.addChild((Container) obj, component, constraintsObj);
		}
		
		return component;
	}

	@Override
	public boolean process(Parser p, Object obj, Element child, LayoutManager layoutMgr)
		throws Exception
	{
		Component component = processComponent(p, obj, child, layoutMgr);
		return (component != null);
	}
}
