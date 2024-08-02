package org.swixml.processor;

import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;

import org.swixml.Parser;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public class ButtonGroupTagProcessor implements TagProcessor
{
	public static final TagProcessor instance = new ButtonGroupTagProcessor();

	/**
	 * Recursively adds AbstractButtons into the given
	 *
	 * @param obj <code>Object</code> should be an AbstractButton or JComponent
	 *            containing AbstractButtons
	 * @param grp <code>ButtonGroup</code>
	 */
	private void putIntoBtnGrp(Component obj, ButtonGroup grp)
	{
		if ( AbstractButton.class.isAssignableFrom(obj.getClass()) )
		{
			grp.add((AbstractButton) obj);
		}
		else if ( JComponent.class.isAssignableFrom(obj.getClass()) )
		{
			JComponent jp = (JComponent) obj;
			for ( int i = 0; i < jp.getComponentCount(); ++i )
			{
				putIntoBtnGrp(jp.getComponent(i), grp);
			}
		} // otherwise just do nothing ...
	}

	@Override
	public boolean process(Parser parser, Object obj, org.w3c.dom.Element child, LayoutManager layoutMgr)
		throws Exception
	{
		if ( !Parser.TAG_BUTTONGROUP.equalsIgnoreCase(child.getLocalName()) )
			return false;
		org.w3c.dom.NodeList buttons = child.getChildNodes();
		if ( buttons == null )
			return false;
		ButtonGroup btnGroup = new ButtonGroup();
		if ( null != child.getAttributeNode(Parser.ATTR_ID) )
		{
			parser.getSwingEngine().getIdMap().put(child.getAttribute(Parser.ATTR_ID), btnGroup);
		}
		for ( int i = 0; i < buttons.getLength(); ++i )
		{
			org.w3c.dom.Node n = buttons.item(i);
			if ( n instanceof org.w3c.dom.Element )
			{
				org.w3c.dom.Element e = (org.w3c.dom.Element) buttons.item(i);
				Component b = ConstraintsTagProcessor.processComponent(parser, obj, e, layoutMgr);
				putIntoBtnGrp(b, btnGroup);
			}
		}
		
//		int k = JMenu.class.isAssignableFrom(obj.getClass())
//			? ((JMenu) obj).getItemCount()
//			: ((Container) obj).getComponentCount();
//		p.getSwing(child, obj);
//		int n = JMenu.class.isAssignableFrom(obj.getClass())
//			? ((JMenu) obj).getItemCount()
//			: ((Container) obj).getComponentCount();
//		// add the recently add container entries into the button group
//		ButtonGroup btnGroup = new ButtonGroup();
//		// in case the button group was given an id attr. it should also be
//		// put into the idmap.
//		if ( null != child.getAttribute(Parser.ATTR_ID) )
//			p.engine.getIdMap().put(child.getAttribute(Parser.ATTR_ID).getValue(), btnGroup);
//		while (k < n)
//		{
//			putIntoBtnGrp(JMenu.class.isAssignableFrom(obj.getClass())
//				? ((JMenu) obj).getItem(k++)
//				: ((Container) obj).getComponent(k++), btnGroup);
//		}
		
		return true;
	}
}
