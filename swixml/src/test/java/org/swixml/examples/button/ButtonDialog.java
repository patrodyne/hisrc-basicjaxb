package org.swixml.examples.button;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JToggleButton;

import org.jdesktop.application.Action;
import org.swixml.LogAware;
import org.swixml.jsr.widgets.JRadioButtonBind;

public class ButtonDialog extends JDialog implements LogAware
{
	private static final long serialVersionUID = 20240701L;
	
	private JToggleButton toggleButton;
	
	private JRadioButtonBind north;
	private JRadioButtonBind south;
	private JRadioButtonBind center;
	private JRadioButtonBind west;
	private JRadioButtonBind east;

	@Action
	public void onCLick(ActionEvent ae)
	{
		logger.info("ActionEvent: {}; JToggleButton: {}", ae, toggleButton.getName());
	}
	
	@Action
	public void onRadio(ActionEvent ae)
	{
		JToggleButton tb = null;
		if ( north.isSelected() )
			tb = north;
		else if ( south.isSelected() )
			tb = south;
		else if ( center.isSelected() )
			tb = center;
		else if ( west.isSelected() )
			tb = west;
		else if ( east.isSelected() )
			tb = east;
		logger.info("ActionEvent: {}; Selected: {}", ae, tb.getName());
	}

	@Action
	public void close()
	{
		logger.info("Action: close");
	}
}
