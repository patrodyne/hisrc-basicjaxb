package org.swixml.examples.treecard;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.swixml.jsr296.SwingApplication;

public class TreeCard2Main extends SwingApplication<TreeCard2Frame>
{
	private static final TreeCard2Frame WINDOW = new TreeCard2Frame();

	/**
	 * Initializations that must occur <em>before</em> the GUI 
	 * is constructed within the {@code startup} method.
	 */
	@Override
	protected void initialize(String[] args)
	{
		// Create the SwingEngine, ElContext, etc.
		setSwingEngine(createEngine(WINDOW));
		
		// Define EL Beans, etc.
		getELProcessor().defineBean("window", WINDOW);
		getELProcessor().defineBean("el", getSwingEngine().getELMethods());
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
