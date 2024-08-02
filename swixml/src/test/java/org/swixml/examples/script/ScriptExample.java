package org.swixml.examples.script;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.lang.reflect.Method;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.swixml.SwingEngine;
import org.swixml.jsr296.SwingApplication;

import jakarta.el.ELException;

/**
 * An {@link SwingApplication} example using Jakarta EL to resolve an
 * EL function.
 * 
 * <p>This implementation of SWIXML uses Jakarta Expression Language (EL)
 * to provide bindings in the XML to reference objects, methods and/or
 * functions in the JVM. It does not use and is not dependent on any
 * JavaScipt swingEngine. It uses "org.glassfish:jakarta.el", as a Maven
 * dependency.</p>
 *	
 * <p>This "script" demo has been refactored to demonstrate the usage of
 * an EL function to reference the radio band ("AM" or "FM") using an
 * EL function instead of a JavaScript function.</p>
 */
public class ScriptExample extends SwingApplication<ScriptDialog>
{
	private static final ScriptDialog WINDOW = new ScriptDialog();
	
	@Override
	protected void initialize(String[] args)
	{
		try
		{
			// Create the SwingEngine, ElContext, etc.
			setSwingEngine(createEngine(WINDOW));
			
			// getELProcessor().defineBean("window", WINDOW);
			
			// Add EL functions from static method(s)
			Method radioText = ScriptDialog.class.getMethod("radioText", new Class[] { Integer.class });
			getELProcessor().defineFunction("sd", "radioText", radioText);

			Method createEtchedBorder = BorderFactory.class.getMethod("createEtchedBorder", new Class[] { });
			getELProcessor().defineFunction("bf", "createEtchedBorder", createEtchedBorder);
			
			// Import a class or a package into the EL evaluation environment.
			// Package imports all the public, concrete classes in the package.
			// The static member name, including the full class name, to be imported.
			getELProcessor().getELManager().importStatic("javax.swing.BorderFactory.createEtchedBorder");
			
			// Process other initial conditions.
			// getELProcessor().setVariable("var", "expression");
			// getELProcessor().setValue("expression", value);
			// getELProcessor().defineBean("name", bean);
			// getELProcessor().defineFunction("prefix", "function", method);
			// getELProcessor().defineFunction("prefix", "function", "className", "method");
		}
		catch ( NoSuchMethodException | SecurityException ex)
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
			// Center dialog on desktop.
			dialog.setLocationRelativeTo(null);
			setup(dialog);
			show(dialog);
		}
		catch (Exception ex)
		{
			logger.error("startup: ", ex);
			exit();
		}
	}

	protected JDialog setup(JDialog dialog)
	{
		// Add AncestorListener predicate(s):
		// Interface to support notification when changes occur to a JComponent or one
		// of its ancestors.  These include movement and when the component becomes
		// visible or invisible, either by the setVisible() method or by being added
		// or removed from the component hierarchy.
		final SwingEngine.Predicate addAncestorListenerPredicate = new SwingEngine.Predicate()
		{
			@Override
			public boolean evaluate(JComponent c)
			{
				c.addAncestorListener(new AncestorListener()
				{
					@Override
					public void ancestorAdded(AncestorEvent ae)
					{
						logger.info("==> ancestorAdded: {}", ae.getComponent());
					}

					@Override
					public void ancestorRemoved(AncestorEvent ae)
					{
						logger.trace("==> ancestorRemoved: {}", ae.getComponent());
					}

					@Override
					public void ancestorMoved(AncestorEvent ae)
					{
						logger.trace("==> ancestorMoved: {}", ae.getComponent());
					}
				});
				
				return true;
			}
		};
		
		// ContainerListener:
		// The listener interface for receiving container events.
		// The class that is interested in processing a container event
		// either implements this interface (and all the methods it
		// contains) or extends the abstract {@code ContainerAdapter} class
		// (overriding only the methods of interest).
		SwingEngine.traverse(dialog, addAncestorListenerPredicate);
		dialog.addContainerListener(new ContainerListener()
		{
			@Override
			public void componentAdded(ContainerEvent ce)
			{
				logger.info("==> componentAdded: {}", ce.getComponent());
			}

			@Override
			public void componentRemoved(ContainerEvent ce)
			{
				logger.info("==> componentRemoved: {}", ce.getComponent());
			}
		});
		
		return dialog;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		SwingEngine.DEBUG_MODE=true;
		System.setProperty(IGNORE_RESOURCES_PREFIX, "true");
		System.setProperty(AUTO_INJECTFIELD, "true");
		SwingApplication.launch(ScriptExample.class, args);
	}
}
