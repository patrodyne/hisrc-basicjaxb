package org.swixml.examples.task;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.jdesktop.application.Application;
import org.swixml.jsr296.SwingApplication;

public class BackgroundTaskExample extends SwingApplication<BackgroundTaskDialog>
{
	public static final BackgroundTaskDialog WINDOW = new BackgroundTaskDialog();

	/**
	 * Initializations that must occur <em>before</em> the GUI 
	 * is constructed within the {@code startup} method.
	 */
	@Override
	protected void initialize(String[] args)
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

	@Override
	protected void startup()
	{
		try
		{
			// Use alternative SwiXML configuration.
			String windowXml = WINDOW.getClass().getName().replace('.', '/').concat("Alt.xml");
			BackgroundTaskDialog dialog = super.render(WINDOW, windowXml);
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
