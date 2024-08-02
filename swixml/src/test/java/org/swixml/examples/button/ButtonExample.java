package org.swixml.examples.button;

import javax.swing.JDialog;

import org.jdesktop.application.ResourceMap;
import org.swixml.SwingTagLibrary;
import org.swixml.jsr296.SwingApplication;

import jakarta.el.ELException;

public class ButtonExample extends SwingApplication<ButtonDialog>
{
	private static final ButtonDialog WINDOW = new ButtonDialog();

	@Override
	protected void initialize(String[] args)
	{
		// initializations that must occur before the GUI 
		// is constructed by {@code startup}.
		try
		{
			// Create the SwingEngine, ElContext, etc.
			setSwingEngine(createEngine(WINDOW));

			// initializations that must occur before the * GUI 
			// is constructed by {@code startup}.
			SwingTagLibrary.getInstance(getSwingEngine())
				.registerTag("toggleButton", JToggleButtonEx.class);
			ResourceMap rMap = getContext().getResourceManager().getResourceMap(WINDOW.getClass());
			String tbText = rMap.getString("tb.text");
			logger.info("==> " + tbText);
			
			// Process other initial conditions.
			// getELProcessor().setVariable("var", "expression");
			// getELProcessor().setValue("expression", value);
			// getELProcessor().defineBean("name", bean);
			// getELProcessor().defineFunction("prefix", "function", method);
			// getELProcessor().defineFunction("prefix", "function", "className", "method");
		}
		catch ( SecurityException ex)
		{
			throw new ELException("Cannot initialize EL context.", ex);
		}
	}
	
	@Override
	protected void startup()
	{
		try
		{
			JDialog dialog = render(WINDOW);
			// Center dialog on desktop.
			dialog.setLocationRelativeTo(null);
			show(dialog);
		}
		catch (Exception ex)
		{
			logger.error("Cannot render window", ex);
			exit();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.setProperty(IGNORE_RESOURCES_PREFIX, "true");
		System.setProperty(AUTO_INJECTFIELD, "true");
		SwingApplication.launch(ButtonExample.class, args);
	}
}
