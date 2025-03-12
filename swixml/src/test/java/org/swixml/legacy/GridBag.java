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

	/** Default constructor for a SwingEngine. 
	 * @throws Exception */
	public GridBag() throws Exception
	{
		SwingEngine<JFrame> swix = new SwingEngine<>(this);
		swix.getELProcessor().defineBean("el", swix.getELMethods());
		swix.getELProcessor().defineBean("window", this);
		swix.render("org/swixml/legacy/gridbag.xml");
		setLocationRelativeTo(null);
	}
	
	public static void main(String[] args)
	{
		SwingEngine.invokeLater(GridBag.class);
	}
}