package org.swixml.jgoodies;

import javax.swing.JFrame;

import org.swixml.SwingEngine;
import org.swixml.XTitledSeparator;

/**
 * The Form class shows how to do a simple JGoodies FormLayout
 */
public class Form extends JFrame
{
	private static final long serialVersionUID = 20240701L;

	/** Default ctor for a SwingEngine. */
	public Form() throws Exception
	{
		SwingEngine<JFrame> swix = new SwingEngine<>(this);
		
		// JGoodies tag and layout registration
		swix.getTaglib().
			registerTag("TitledSeparator", XTitledSeparator.class);
		swix.getLayoutLibrary().
			register("com.jgoodies.forms.layout.FormLayout", new FormLayoutConverter());
		
		swix.render("org/swixml/jgoodies/form.xml");
		
		setLocationRelativeTo(null);
	}

	public static void main(String[] args)
	{
		SwingEngine.invokeLater(Form.class);
	}
}