package org.swixml.examples.table;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;

import org.swixml.jsr296.SwingApplication;

public class TableExample extends SwingApplication<TableDialog>
{
	private static final String SWIXML_SOURCE = "org/swixml/examples/table/TableDialog.xml";
	private static final TableDialog WINDOW = new TableDialog();

	/**
	 * Initializations that must occur <em>before</em> the GUI 
	 * is constructed within the {@code startup} method.
	 */
	@Override
	protected void initialize(String[] args) throws Exception
	{
		// Create the SwingEngine, ElContext, etc.
		setSwingEngine(createEngine(WINDOW));
		
		getELProcessor().defineBean("el", getSwingEngine().getELMethods());
		getELProcessor().defineBean("window", WINDOW);
		
		// Process other initial conditions.
		// getELProcessor().setVariable("var", "expression");
		// getELProcessor().setValue("expression", value);
		// getELProcessor().defineBean("name", bean);
		// getELProcessor().defineFunction("prefix", "function", method);
		// getELProcessor().defineFunction("prefix", "function", "className", "method");
		
		getELProcessor().defineFunction("color", "rgb", Color.class.getMethod("decode", String.class));
		getELProcessor().defineFunction("color", "hsb", Color.class.getMethod("getHSBColor", float.class, float.class, float.class));

		// Object result = getSwingEngine().getELProcessor().eval("window.myData");
		// int x = 0;
		// getELProcessor().setVariable("myData", "window.myData");
	}

	@Override
	protected void startup()
	{
		try
		{
			JDialog dialog = render(WINDOW, SWIXML_SOURCE);
			dialog.addWindowListener(new WindowListener());
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
		SwingApplication.launch(TableExample.class, args);
	}
}
