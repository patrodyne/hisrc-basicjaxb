package org.swixml.legacy;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.JFrame;

import org.swixml.SwingEngine;

/**
 * The GridBag class shows how to do a simple GridBag layout
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml 0.5
 */
public class GridBag extends JFrame
{
	private static final long serialVersionUID = 20240701L;

	/** Default ctor for a SwingEngine. */
	private GridBag()
	{
		try
		{
			SwingEngine<JFrame> swix = new SwingEngine<>(this);
			swix.getELProcessor().defineBean("el", swix.getELMethods());
			swix.getELProcessor().defineBean("window", this);
			swix.render("org/swixml/legacy/gridbag.xml");
			setLocationRelativeTo(null);
			setVisible(true);
		}
		catch (Exception ex)
		{
			showErrorDialog(ex);
		}
	}
	
	private static void showErrorDialog(Exception ex)
	{
		ex.printStackTrace();
		String msg = ex.getClass().getSimpleName() + ": " + ex.getMessage() +"\n";
		showMessageDialog(null, msg, "ERROR", ERROR_MESSAGE);
	}
	
	public static void main(String[] args)
	{
		new GridBag();
	}
}