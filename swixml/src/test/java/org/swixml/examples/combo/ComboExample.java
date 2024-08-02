package org.swixml.examples.combo;

import java.awt.GraphicsEnvironment;

import javax.swing.JDialog;

import org.swixml.jsr296.SwingApplication;

import jakarta.el.ELException;

public class ComboExample extends SwingApplication<ComboDialog>
{
	private static final ComboDialog WINDOW = new ComboDialog();

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
			JDialog dialog = render(new ComboDialog());
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

	public static void main(String args[])
	{
		//logAvailableFontFamilyNames();
		SwingApplication.launch(ComboExample.class, args);
	}

	protected static void logAvailableFontFamilyNames()
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		for ( String fontName : ge.getAvailableFontFamilyNames() )
			logger.info("Font: {}", fontName);
	}
}
