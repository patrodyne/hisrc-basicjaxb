package org.swixml.legacy;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;

import org.swixml.SwingEngine;

/**
 * The XInclude class shows in simple way how to use XML includes.
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
			swix.getELProcessor().defineBean("el", swix.getELMethods());
			swix.getELProcessor().defineBean("window", this);
			swix.render("org/swixml/legacy/xinclude.xml");
			setLocationRelativeTo(null);
			setVisible(true);
		}
		catch (Exception ex)
		{
			showErrorDialog(ex);
		}
	}

	public Action cancelAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			showMessageDialog(XInclude.this, "Sorry, 'Cancel' is not implemented yet.");
		}
	};
	
	public Action okAction = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			showMessageDialog(XInclude.this, "Sorry, 'OK' is not implemented yet.");
		}
	};
	
	private static void showErrorDialog(Exception ex)
	{
		ex.printStackTrace();
		String msg = ex.getClass().getSimpleName() + ": " + ex.getMessage() +"\n";
		showMessageDialog(null, msg, "ERROR", ERROR_MESSAGE);
	} 
	
	public static void main(String[] args)
	{
		new XInclude();
	}
}
