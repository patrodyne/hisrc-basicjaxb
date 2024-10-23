package org.swixml.legacy;

import java.awt.Color;
import java.awt.Font;
import java.util.TimeZone;

import javax.swing.JLabel;

public class RedLabel extends JLabel
{
	private static final long serialVersionUID = 20240701L;

	public RedLabel()
	{
		this.setForeground(Color.red);
		this.setFont(Font.decode("SansSerif-BOLD-24"));
	}

	// See TimeZoneConverter
	public void setTimeZone(TimeZone tz)
	{
		this.setText(tz.getDisplayName());
	}
}
