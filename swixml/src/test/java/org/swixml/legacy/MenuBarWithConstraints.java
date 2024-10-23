package org.swixml.legacy;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;

import org.swixml.SwingEngine;

// $Id: MenuBarWithConstraints.java,v 1.1 2005/08/22 21:20:01 tichy Exp $

/**
 * Sample program to show a menubar with constraints attribute in it.
 * 
 */
public class MenuBarWithConstraints extends JFrame
{
	private static final long serialVersionUID = 20240701L;
	
	public Action exitAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	};

	/**
	 * @param args
	 * @throws Exception if something goes wrong
	 */
	public static void main(String[] args)
		throws Exception
	{
		SwingEngine<JFrame> se = new SwingEngine<>(new MenuBarWithConstraints());
		se.getELProcessor().defineBean("el", se.getELMethods());
		JFrame container = se.render("org/swixml/legacy/menu-bar.xml");
		container.setLocationRelativeTo(null);
		container.setVisible(true);
	}
}
