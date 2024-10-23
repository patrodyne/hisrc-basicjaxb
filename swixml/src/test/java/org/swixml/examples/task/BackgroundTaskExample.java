package org.swixml.examples.task;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.jdesktop.application.Application;
import org.swixml.jsr296.SwingApplication;

import jakarta.el.ELException;

public class BackgroundTaskExample extends SwingApplication<BackgroundTaskDialog>
{
	private static final BackgroundTaskDialog WINDOW = new BackgroundTaskDialog();

	@Override
	protected void initialize(String[] args)
	{
		// initializations that must occur before the GUI 
		// is constructed by {@code startup}.
		try
		{
			// Create the SwingEngine, ElContext, etc.
			setSwingEngine(createEngine(WINDOW));
			
			getELProcessor().defineBean("el", getSwingEngine().getELMethods());

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
			BackgroundTaskDialog dialog = super.render(WINDOW);
			dialog.addWindowListener(new WindowListener());
			// Center dialog on desktop.
			dialog.setLocationRelativeTo(null);
			super.show(dialog);
		}
		catch (Exception ex)
		{
			showErrorDialog(ex);
			logger.error("startup: ", ex);
			exit();
		}
	}
	
	private class WindowListener extends WindowAdapter
	{
        @Override
        public void windowClosing(WindowEvent we)
        {
        	BackgroundTaskDialog window =
        		(BackgroundTaskDialog) we.getWindow();
        	if ( window.getRetrieveTimeTask() != null )
        		window.getRetrieveTimeTask().cancel(true);
        	if ( window.getScanDirTask() != null )
        		window.getScanDirTask().cancel(true);
        	exit(we);
        }
        
        @Override
        public void windowClosed(WindowEvent we)
        {
        }
	}

	public static void main(String[] args)
	{
		Application.launch(BackgroundTaskExample.class, args);
	}
}
