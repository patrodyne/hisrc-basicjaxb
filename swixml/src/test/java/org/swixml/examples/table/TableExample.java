package org.swixml.examples.table;

import java.awt.Color;

import javax.swing.JDialog;

import org.swixml.jsr296.SwingApplication;

import jakarta.el.ELException;

public class TableExample extends SwingApplication<TableDialog>
{
	private static final String SWIXML_SOURCE = "org/swixml/examples/table/TableDialog.xml";

	private static final TableDialog WINDOW = new TableDialog();

	@Override
	protected void initialize(String[] args)
	{
		// initializations that must occur before the GUI 
		// is constructed by {@code startup}.
		try
		{
			// Create the SwingEngine, ElContext, etc.
			setSwingEngine(createEngine(WINDOW, 24));
			
			// Process other initial conditions.
			// getELProcessor().setVariable("var", "expression");
			// getELProcessor().setValue("expression", value);
			// getELProcessor().defineBean("name", bean);
			// getELProcessor().defineFunction("prefix", "function", method);
			// getELProcessor().defineFunction("prefix", "function", "className", "method");
			
			getELProcessor().defineBean("el", getSwingEngine().getELMethods());
			getELProcessor().defineBean("window", WINDOW);
			
			getELProcessor().defineFunction("color", "rgb", Color.class.getMethod("decode", String.class));
			getELProcessor().defineFunction("color", "hsb", Color.class.getMethod("getHSBColor", float.class, float.class, float.class));

			// Object result = getSwingEngine().getELProcessor().eval("window.myData");
			// int x = 0;
			// getELProcessor().setVariable("myData", "window.myData");
		}
		catch ( SecurityException | NoSuchMethodException ex)
		{
			throw new ELException("Cannot initialize EL context.", ex);
		}
	}

	@Override
	protected void startup()
	{
		try
		{
			JDialog dialog = render(WINDOW, SWIXML_SOURCE);
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
		SwingApplication.launch(TableExample.class, args);
	}
}
