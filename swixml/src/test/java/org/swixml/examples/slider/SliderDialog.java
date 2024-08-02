package org.swixml.examples.slider;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SliderDialog extends JDialog
{
	private static final long serialVersionUID = 20240501L;
	private static final Logger logger = LoggerFactory.getLogger(SliderDialog.class);
	
	// Represents the slider value.
	int transparency;
	public final int getTransparency()
	{
		return transparency;
	}
	public final void setTransparency(int value)
	{
		this.transparency = value;
		logger.debug("Transparency Value Set....: {}", value);
	}
	
	// Read the transparency to use.
	public Action transparencyAction = new AbstractAction()
	{
		private static final long serialVersionUID = SliderDialog.serialVersionUID;

		@Override
		public void actionPerformed(ActionEvent e)
		{
			logger.debug("Transparency Value Action.: {}", getTransparency());
		}
	};

}
