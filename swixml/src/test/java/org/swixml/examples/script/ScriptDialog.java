package org.swixml.examples.script;

import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JToggleButton;

import org.jdesktop.application.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScriptDialog extends JDialog
{
	private static final long serialVersionUID = 20240701L;
	protected static final Logger logger = LoggerFactory.getLogger(ScriptDialog.class);

	// Represents a common radio spectrum.
	private static final String[] RADIO_BAND = new String[] { "AM", "FM" };
	
	// Field(s) bound by SWIXML
	protected String am;
	protected String fm;
	
	/**
	 * This static function is referenced in ScriptDialog.xml.
	 * 
	 * <p>Expression language functions are defined as a public
	 * static method in a public class.</p>
	 * 
	 * <pre>{@code 
	 * ${sd:radioText(0)}
	 * ${sd:radioText(1)}
	 * }</pre>
	 * 
	 * @param index
	 * @return
	 */
	public static String radioText(Integer index)
	{
		return RADIO_BAND[index];
	}
	
	// Bound by <button id="toggleButton" ... />
	public JToggleButton toggleButton;
	@Action
	public void onCLick(ActionEvent ae)
	{
		logger.info("onCLick: {}", ae);
	}
}
