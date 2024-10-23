package org.swixml.examples.slider;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import org.jdesktop.application.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swixml.jsr.widgets.JSliderBind;

public class SliderDialog extends JDialog
{
	private static final long serialVersionUID = 20240501L;
	private static final Logger logger = LoggerFactory.getLogger(SliderDialog.class);
	
	private JSliderBind transparency;
	public JSliderBind getTransparency() { return transparency; }
	public void setTransparency(JSliderBind transparency) { this.transparency = transparency; }

	// Represents the slider value.
	private int transparencyValue;
	public final int getTransparencyValue()
	{
		return transparencyValue;
	}
	public final void setTransparencyValue(int transparencyValue)
	{
		this.transparencyValue = transparencyValue;
		logger.debug("Transparency Value Set....: {}", transparencyValue);
	}
	
	// OLD: Implement transparencyAction using javax.swing.Action.
	public javax.swing.Action transparencyAction = new AbstractAction()
	{
		private static final long serialVersionUID = SliderDialog.serialVersionUID;

		@Override
		public void actionPerformed(ActionEvent e)
		{
			logger.info("Transparency Value Action.: {}", getTransparencyValue());
		}
	};
	
	// NEW: Implement transparencyAction using org.jdesktop.application.Action
//	@Action
//	public void transparencyAction()
//	{
//		logger.info("Action: transparencyAction = {}", getTransparencyValue());
//	}
	
	@Action
	public void test()
	{
		logger.info("Action: test = {}", getTransparencyValue());
	}
	
	@Action
	public void submit()
	{
		logger.info("Action: submit = {}", getTransparencyValue());
	}
	
	@Action
	public void close()
	{
		logger.info("Action: close");
	}

}
