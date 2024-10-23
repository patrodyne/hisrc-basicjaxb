package org.swixml.examples;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.swixml.jsr296.SwingApplication;

import jakarta.el.ELException;

public class BindingExamplesApplication
	extends SwingApplication<BindingExamplesFrame>
{
	private static final BindingExamplesFrame WINDOW = new BindingExamplesFrame();
	
	@Override
	protected void initialize(String[] args)
	{
		// initializations that must occur before the GUI 
		// is constructed by {@code startup}.
		try
		{
			// Create the SwingEngine, ElContext, etc.
			setSwingEngine(createEngine(WINDOW));
			// Process other initial conditions.
			// getELProcessor().setVariable("var", "expression");
			// getELProcessor().setValue("expression", value);
			// getELProcessor().defineBean("name", bean);
			// getELProcessor().defineFunction("prefix", "function", method);
			// getELProcessor().defineFunction("prefix", "function", "className", "method");

			 getELProcessor().defineBean("el", getSwingEngine().getELMethods());
			 getELProcessor().defineBean("window", WINDOW);
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
			JFrame frame = render(WINDOW);
			frame.addWindowListener(new WindowListener());
			frame.setLocationRelativeTo(null);
			show(frame);
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
		SwingApplication.launch(BindingExamplesApplication.class, args);
	}
}
