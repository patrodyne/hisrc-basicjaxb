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

	public Layout()
		throws Exception
	{
		SwingEngine<JFrame> swix = new SwingEngine<>(this);
		swix.getELProcessor().defineBean("el", swix.getELMethods());
		swix.getELProcessor().defineBean("window", this);
		swix.render(DESCRIPTOR);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args)
	{
		SwingEngine.invokeLater(Layout.class);
	}
}
