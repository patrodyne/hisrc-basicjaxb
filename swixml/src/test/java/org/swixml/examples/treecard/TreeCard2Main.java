package org.swixml.examples.treecard;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.swixml.jsr296.SwingApplication;

import jakarta.el.ELException;

public class TreeCard2Main extends SwingApplication<TreeCard2Frame>
{
	private static final TreeCard2Frame WINDOW = new TreeCard2Frame();

	@Override
	protected void initialize(String[] args)
	{
		// initializations that must occur before the GUI 
		// is constructed by {@code startup}.
		try
		{
			// Create the SwingEngine, ElContext, etc.
			setSwingEngine(createEngine(WINDOW));
			
			// Define EL Beans, etc.
			getELProcessor().defineBean("window", WINDOW);
			getELProcessor().defineBean("el", getSwingEngine().getELMethods());
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
			// Center frame on desktop.
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
		SwingApplication.launch(TreeCard2Main.class, args);
	}
	
}
