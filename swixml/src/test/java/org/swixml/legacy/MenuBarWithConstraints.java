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
	
	/**
	 * Default constructor
	 */
	public MenuBarWithConstraints() throws Exception
	{
		SwingEngine<JFrame> se = new SwingEngine<>(this);
		se.getELProcessor().defineBean("el", se.getELMethods());
		JFrame container = se.render("org/swixml/legacy/menu-bar.xml");
		container.setLocationRelativeTo(null);
	}
	
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
	 * Start application
	 * @param args Application arguments
	 */
	public static void main(String[] args)
	{
		SwingEngine.invokeLater(MenuBarWithConstraints.class);
	}
}
