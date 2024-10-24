package org.swixml.examples.button;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;

import org.jdesktop.application.ResourceMap;
import org.swixml.SwingTagLibrary;
import org.swixml.jsr296.SwingApplication;

import jakarta.el.ELException;

public class ButtonExample extends SwingApplication<ButtonDialog>
{
	private static final ButtonDialog WINDOW = new ButtonDialog();

	@Override
	protected void initialize(String[] args)
	{
		// initializations that must occur before the GUI 
		// is constructed by {@code startup}.
		try
		{
			// Create the SwingEngine, ElContext, etc.
			setSwingEngine(createEngine(WINDOW));

			// initializations that must occur before the * GUI 
			// is constructed by {@code startup}.
			SwingTagLibrary.getInstance(getSwingEngine())
				.registerTag("toggleButton", JToggleButtonEx.class);
			
			//
			// ResourceMaps are typically obtained with the {@code ApplicationContext}
			// {@link ApplicationContext#getResourceMap getResourceMap} method which
			// lazily creates per Application.
			//
			// ResourceMaps can be used to "inject" resource values into Swing component
			// properties and into object fields.
			//
			ResourceMap rMap = getContext().getResourceManager().getResourceMap(WINDOW.getClass());

			if ( logger.isDebugEnabled() )
			{
				ResourceMap rMapChain = rMap;
				while ( rMapChain != null )
				{
					for ( String bundleName : rMapChain.getBundleNames() )
						logger.debug("Resource Bundle: " + bundleName + " ==> " + rMapChain.getResourcesDir());
					rMapChain = rMapChain.getParent();
				}
			}
			
			for ( String key : rMap.keySet() )
			{
				switch (key)
				{
					case "tb.text":
					case "tb.selectedText":
					case "Application.id":
						logger.info("	 " + key + " ==> " + rMap.getString(key));
						break;
					default:
						logger.debug("	  " + key + " ==> " + rMap.getString(key));
						break;
				}
			}
			
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
			JDialog dialog = render(WINDOW);
			dialog.addWindowListener(new WindowListener());
			// Center dialog on desktop.
			dialog.setLocationRelativeTo(null);
			show(dialog);
		}
		catch (Exception ex)
		{
			showErrorDialog(ex);
			logger.error("Cannot render window", ex);
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.setProperty(IGNORE_RESOURCES_PREFIX, "true");
		System.setProperty(AUTO_INJECTFIELD, "true");
		SwingApplication.launch(ButtonExample.class, args);
	}
}
