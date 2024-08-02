package org.swixml.legacy;

import javax.swing.JFrame;

import org.swixml.SwingEngine;

/**
 * The Layout class shows the use of layout managers
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml (#136)
 */
public class Layout extends JFrame
{
	private static final long serialVersionUID = 20240701L;
	private static final String DESCRIPTOR = "org/swixml/legacy/funlayout.xml";

	private Layout()
		throws Exception
	{
		new SwingEngine<JFrame>(this)
			.render(Layout.DESCRIPTOR);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		try
		{
			new Layout();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}
