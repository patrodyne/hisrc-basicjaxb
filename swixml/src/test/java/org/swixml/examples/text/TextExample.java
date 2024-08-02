package org.swixml.examples.text;

import javax.swing.JDialog;

import org.swixml.jsr296.SwingApplication;

import jakarta.el.ELException;

public class TextExample extends SwingApplication<TextDialog>
{
	private static final TextDialog WINDOW = new TextDialog();

	@Override
	protected void initialize(String[] args)
	{
		// initializations that must occur before the GUI 
		// is constructed by {@code startup}.
		try
		{
			// Create the SwingEngine, ElContext, etc.
			setSwingEngine(createEngine(WINDOW));
			
			getELProcessor().defineBean("window", WINDOW);

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
			JDialog dialog = render(new TextDialog());
			// Center dialog on desktop.
			dialog.setLocationRelativeTo(null);
			show(dialog);
		}
		catch (Exception ex)
		{
			logger.error("startup: ", ex);
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
		SwingApplication.launch(TextExample.class, args);
	}
}
