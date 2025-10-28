package org.example.swing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.Introspector;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.example.PurchaseOrder.infos.PurchaseOrderBeanInfo;
import org.swixml.jsr296.SwingApplication;

import jakarta.el.ELException;

public class SchemasTool
	extends SwingApplication<SchemasWindow>
{
	public static final SchemasWindow WINDOW = new SchemasWindow();
	public static final String SAMPLES_SUB_PATH = "src/test/samples/";

	@Override
	protected void initialize(String[] args)
	{
		// initializations that must occur before the GUI
		// is constructed by {@code startup}.
		try
		{
			// Configure the Beans Introspector
			String poBeanInfoPath = PurchaseOrderBeanInfo.class.getPackageName();
			Introspector.setBeanInfoSearchPath(new String[] { poBeanInfoPath });

			// Create the SwingEngine, ElContext, etc.
			setSwingEngine(createEngine(WINDOW));

			getELProcessor().defineBean("el", getSwingEngine().getELMethods());
			getELProcessor().defineBean("window", WINDOW);

			// Process other initial conditions.
			//
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
			// Initialize the window, model, etc.
			// WINDOW.initialize();

			// Parse the inferred XML resource. The inferred file is
			// read as a resource stream using the window's fully
			// qualified name: "a.b.c.Name" -> "a/b/c/Name.xml".
			// is an input stream for reading the specified resource
			JFrame frame = render(WINDOW);
			WINDOW.setFileChooser(new JFileChooser(new File(SAMPLES_SUB_PATH)));

			// Listen for window closing event, etc.
			frame.addWindowListener(new WindowListener());

			// Center window on desktop.
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
		SwingApplication.launch(SchemasTool.class, args);
	}
}
