package org.swixml.processor;

import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;

import org.swixml.LayoutConverter;
import org.swixml.LayoutConverterLibrary;
import org.swixml.Parser;
import org.swixml.dom.Attribute;
import org.swixml.dom.DOMUtil;
import org.w3c.dom.Attr;

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
		LayoutManager layoutMgr)
		throws Exception
	{
		T result = null;
		//
		// A CONSTRAINTS attribute is removed from the childtag but used to add
		// the child into the currrent obj
		//
		Attr constrnAttr = child.getAttributeNode(Parser.ATTR_CONSTRAINTS);
		Object constrains = null;
		if ( constrnAttr != null && layoutMgr != null )
		{
			child.removeAttribute(Parser.ATTR_CONSTRAINTS); // therefore it
															// won't be used in
															// getSwing(child)
			LayoutConverter layoutConverter = LayoutConverterLibrary.getInstance()
				.getLayoutConverter(layoutMgr.getClass());
			if ( layoutConverter != null )
				constrains = layoutConverter.convertConstraintsAttribute(new Attribute(constrnAttr));
		}
		//
		// A CONSTRAINTS element is used to add the child into the currrent obj
		//
		org.w3c.dom.Element constrnElement = DOMUtil.getChildByTagName(child, Parser.TAG_CONSTRAINTS); // child.getChild(Parser.TAG_CONSTRAINTS);
		if ( constrnElement != null && layoutMgr != null )
		{
			LayoutConverter layoutConverter = LayoutConverterLibrary.getInstance()
				.getLayoutConverter(layoutMgr.getClass());
			if ( layoutConverter != null )
				constrains = layoutConverter.convertConstraintsElement(constrnElement);
		}
		//
		// A constraints or GridBagConstraints grand-childtag is not added at
		// all ..
		// .. but used to add the child into this container
		//
		org.w3c.dom.Element grandchild = DOMUtil.getChildByTagName(child, Parser.TAG_GRIDBAGCONSTRAINTS,
			child.getNamespaceURI()); // child.getChild(Parser.TAG_GRIDBAGCONSTRAINTS,
										// child.getNamespace()); // Fix Issue
										// 74
		if ( grandchild != null )
		{
			result = (T) p.getSwing(child, null);
			p.addChild((Container) obj, result, p.getSwing(grandchild, null));
		}
		else if ( !child.getLocalName().equals(Parser.TAG_CONSTRAINTS)
					&& !child.getLocalName().equals(Parser.TAG_GRIDBAGCONSTRAINTS) )
		{
			result = (T) p.getSwing(child, null);
			p.addChild((Container) obj, result, constrains);
		}
		return result;
	}

	@Override
	public boolean process(Parser p, Object obj, org.w3c.dom.Element child, LayoutManager layoutMgr)
		throws Exception
	{
		Component c = processComponent(p, obj, child, layoutMgr);
		return c != null;
	}
}
