package org.swixml.examples.button;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JToggleButton;

import org.jdesktop.application.Action;
import org.swixml.LogAware;

public class ButtonDialog extends JDialog implements LogAware
{
	private static final long serialVersionUID = 20240701L;
	JToggleButton toggleButton;

	@Action
	public void onCLick(ActionEvent ae)
	{
		logger.info("ActionEvent: {}", ae);
	}

	@Action
	public void close()
	{
		logger.info("Action: close");
	}
}
