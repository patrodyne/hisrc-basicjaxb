package org.swixml.jgoodies;

import javax.swing.JDialog;

import org.swixml.SwingEngine;
import org.swixml.XTitledSeparator;

/**
 * The Form class shows how to do a simple JGoodies FormLayout
 */
public class Dialog extends JDialog
{
	private static final long serialVersionUID = 20240701L;

	/** Default ctor for a SwingEngine. */
	private Dialog()
	{
		try
		{
			SwingEngine<JDialog> swix = new SwingEngine<>(this);
			
			// JGoodies tag and layout registration
			swix.getTaglib().
				registerTag("TitledSeparator", XTitledSeparator.class);
			swix.getLayoutLibrary().
				register("com.jgoodies.forms.layout.FormLayout", new FormLayoutConverter());
			
			swix.render("org/swixml/jgoodies/dialog.xml");
			
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
		new Dialog();
	}
}