package org.swixml.examples.dialog;

import javax.swing.JOptionPane;

import org.jdesktop.application.Application;
import org.swixml.jsr296.SwingApplication;

import jakarta.el.ELException;

public class LoginExample extends SwingApplication<LoginDialog>
{
	private static final LoginDialog WINDOW = new LoginDialog();

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
			LoginDialog dialog = super.render(new LoginDialog());
			// Center dialog on desktop.
			dialog.setLocationRelativeTo(null);
			super.show(dialog);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "error on startup " + e.getMessage(), "ERROR",
				JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Application.launch(LoginExample.class, args);
	}
}
