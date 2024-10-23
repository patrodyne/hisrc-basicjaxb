package org.swixml.legacy;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.swixml.SwingEngine;

import com.toedter.calendar.JCalendar;

public class CustomTags extends JFrame
{
	private static final long serialVersionUID = 20240701L;

	public CustomTags()
		throws Exception
	{
		SwingEngine<JFrame> swix = new SwingEngine<>(this);
		swix.getELProcessor().defineBean("el", swix.getELMethods());
		swix.getELProcessor().defineBean("window", this);
		swix.getTaglib().registerTag("Calendar", JCalendar.class);
		swix.render("org/swixml/legacy/customtags.xml").setVisible(true);
		setLocationRelativeTo(null);
	}

	/**
	 * Invoked when a window is in the process of being closed. The close
	 * operation can be overridden at this point.
	 */
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}

	//
	// Make the class bootable
	//
	public static void main(String[] args)
		throws Exception
	{
		new CustomTags();
	}
}
