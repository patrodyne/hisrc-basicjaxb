package org.swixml.jgoodies;

import javax.swing.JFrame;

import org.swixml.SwingEngine;
import org.swixml.XTitledSeparator;

/**
 * The Layout class shows the use of layout managers
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 *
 * @since swixml (#151)
 */
public class FormLayout extends JFrame
{
	private static final long serialVersionUID = 20240701L;
	
	private static final String DESCRIPTOR = "org/swixml/jgoodies/formlayout.xml";

	private FormLayout()
		throws Exception
	{
		SwingEngine<JFrame> swix = new SwingEngine<JFrame>(this);
		
		// JGoodies tag and layout registration
		swix.getTaglib().
			registerTag("TitledSeparator", XTitledSeparator.class);
		swix.getLayoutLibrary().
			register("com.jgoodies.forms.layout.FormLayout", new FormLayoutConverter());
		
		swix.render(FormLayout.DESCRIPTOR);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		try
		{
			new FormLayout();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
}