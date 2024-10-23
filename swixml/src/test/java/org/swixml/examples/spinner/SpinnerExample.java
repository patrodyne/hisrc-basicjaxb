package org.swixml.examples.spinner;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.swixml.jsr296.SwingApplication;

import jakarta.el.ELException;

/**
 *
 * @author bsorrentino
 */
public class SpinnerExample extends SwingApplication<SpinnerDialog>
{
	private static final SpinnerDialog WINDOW = new SpinnerDialog();

	@Override
	protected void initialize(String[] args)
	{
		// initializations that must occur before the GUI 
		// is constructed by {@code startup}.
		try
		{
			// Create the SwingEngine, ElContext, etc.
			setSwingEngine(createEngine(WINDOW));
			getELProcessor().defineBean("el", getELMethods());
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
			SpinnerDialog dialog = render(WINDOW);
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
		SwingApplication.launch(SpinnerExample.class, args);
	}
}
