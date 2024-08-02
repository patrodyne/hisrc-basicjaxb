package org.swixml.legacy;

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
			new SwingEngine<JFrame>(this)
				.render("org/swixml/legacy/gridbag.xml");
			setLocationRelativeTo(null);
			setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		new GridBag();
	}
}