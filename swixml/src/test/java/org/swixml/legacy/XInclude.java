package org.swixml.legacy;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;

import org.swixml.SwingEngine;

/**
 * The XInclude class shows in simple way how to use xml includes.
 * <code>XInclude</code> extends the WindowAdapter and uses a SwingEngine
 * to render the GUI.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml 0.95
 */
public class XInclude extends JFrame
{
	private static final long serialVersionUID = 20240701L;
	
	private SwingEngine<JFrame> swix = new SwingEngine<>(this);

	private XInclude()
	{
		try
		{
			swix.render("org/swixml/legacy/xinclude.xml");
			setLocationRelativeTo(null);
			setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Action okAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("ok");
		}
	};

	public static void main(String[] args)
	{
		new XInclude();
	}
}
