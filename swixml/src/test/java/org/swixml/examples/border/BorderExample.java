package org.swixml.examples.border;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;

import org.swixml.jsr296.SwingApplication;

/**
 *
 * @author bsorrentino
 */
public class BorderExample extends SwingApplication<BorderDialog>
{
	private static final BorderDialog WINDOW = new BorderDialog();

	/**
	 * Initializations that must occur <em>before</em> the GUI 
	 * is constructed within the {@code startup} method.
	 */
	@Override
	protected void initialize(String[] args)
	{
		// Create the SwingEngine, ElContext, etc.
		setSwingEngine(createEngine(WINDOW));
		
		getELProcessor().defineBean("el", getELMethods());

		// Process other initial conditions.
		// getELProcessor().setVariable("var", "expression");
		// getELProcessor().setValue("expression", value);
		// getELProcessor().defineBean("name", bean);
		// getELProcessor().defineFunction("prefix", "function", method);
		// getELProcessor().defineFunction("prefix", "function", "className", "method");
	}

	@Override
	protected void startup()
	{
		try
		{
			JDialog dialog = render(WINDOW);
			dialog.addWindowListener(new WindowListener());
			// Center dialog on desktop.
			dialog.setLocationRelativeTo(null);
			show(dialog);
		}
		catch (Exception ex)
		{
			showErrorDialog(ex);
			logger.error("startup: ", ex);
			exit();
		}
	}
	
	/*
	 * Gracefully shutdown the application.
	 */
	private class WindowListener extends WindowAdapter
	{
		@Override
		public void windowClosing(WindowEvent we)
		{
			// Close tasks, etc.
			exit(we);
		}
	}

	public static void main(String args[])
	{
		SwingApplication.launch(BorderExample.class, args);
	}
}
