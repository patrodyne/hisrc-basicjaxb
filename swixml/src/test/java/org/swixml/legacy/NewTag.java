package org.swixml.legacy;

import java.util.TimeZone;

import javax.swing.JFrame;

import org.swixml.ConverterLibrary;
import org.swixml.SwingEngine;

/**
 * Extend the TagLib with a new Class and a new Converter
 */
public class NewTag extends JFrame
{
	private static final long serialVersionUID = 20240701L;
	
	SwingEngine<JFrame> swix = new SwingEngine<JFrame>(this);

	public NewTag() throws Exception
	{
		//
		// Register a new new Converter,
		// Generally, Converters should be registered before Tags
		//
		ConverterLibrary.getInstance().register(TimeZone.class, new TimeZoneConverter());

		//
		// Register the 'xpanel' Tag that uses a SwingEngine itself ...
		//
		swix.getTaglib().registerTag("xpanel", XPanel.class);

		//
		// Register the 'redlabel' Tag that uses a SwingEngine itself ...
		//
		swix.getTaglib().registerTag("redlabel", RedLabel.class);
		
		//
		// Render this application
		// 
		swix.getELProcessor().defineBean("el", swix.getELMethods());
		swix.getELProcessor().defineBean("window", this);
		swix.render("org/swixml/legacy/newtag.xml");
		setLocationRelativeTo(null);
	}

	public static void main(String[] args)
	{
		SwingEngine.invokeLater(NewTag.class);
	}
}
