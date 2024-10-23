package org.swixml.jsr296;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Component;
import java.awt.Window;
import java.io.File;
import java.io.Reader;
import java.net.URL;
import java.util.EventObject;

import org.jdesktop.application.SingleFrameApplication;
import org.swixml.SwingEngine;
import org.swixml.el.ELMethods;

import jakarta.el.ELProcessor;

/**
 * Abstract Swing Application
 * 
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author Sorrentino
 */
public abstract class SwingApplication<T extends Window>
	extends SingleFrameApplication
{
	// Seal this class.
	protected SwingApplication()
	{
	}
	
	private SwingEngine<T> swingEngine;
	/**
	 * Get the rendering engine able to convert an SwixML
	 * descriptor into a <code>java.swing</code> UI.
	 * 
	 * @return The rendering engine to convert an SwixML
	 *         descriptor into a <code>java.swing</code> UI.
	 */
	public SwingEngine<T> getSwingEngine()
	{
		return swingEngine;
	}
	/**
	 * Set rendering engine to convert SwixML into a Swing GUI.
	 * 
	 * @param swingEngine A rendering engine to convert SwixML into a Swing GUI.
	 */
	public void setSwingEngine(SwingEngine<T> swingEngine)
	{
		this.swingEngine = swingEngine;
	}
	
	/**
	 * Get API for using Jakarta Expression Language in a stand-alone environment.
	 * 
	 * @return The {@link ELProcessor} for using Jakarta EL in a stand-alone environment.
	 */
	public ELProcessor getELProcessor()
	{
		return getSwingEngine().getELProcessor();
	}
	
	/**
	 * Get API for using Jakarta Expression Language in a stand-alone environment.
	 * 
	 * @return The {@link ELMethods} for using Jakarta EL in a stand-alone environment.
	 */
	public ELMethods<T> getELMethods()
	{
		return getSwingEngine().getELMethods();
	}
	
	/**
	 * Create a {@link SwingEngine} for the given container.
	 * 
	 * @param container A target container.
	 * 
	 * @return A @link SwingEngine} instance with injected fields.
	 * 
	 * @apiNote {@code <T>} The container type.
	 */
	protected SwingEngine<T> createEngine(T container)
	{
		final SwingEngine<T> engine = new SwingEngine<T>(container);
		engine.setClassLoader(getClass().getClassLoader());
		if ( getBooleanProperty(AUTO_INJECTFIELD) )
			getContext().getResourceMap().injectFields(container);
		// Conveniently put a reference to this application in the EL context.
		engine.getELContext().putContext(SwingApplication.class, this);
		engine.getELMethods().setSwingEngine(engine);
		return engine;
	}
	
	/**
	 * Start the parsing of the inferred XML file.
	 * 
	 * <p>The resource is located using the following convention:</p>
	 * 
	 * <pre>
	 * container class a.b.c.Name ==> a/b/c/Name.xml
	 * </pre>
	 * 
	 * @param container A target container.
	 * 
	 * @return Instantiated Swing object tree root.
	 * 
	 * @apiNote {@code <T>} The container type.
	 */
	public final T render(T container)
		throws Exception
	{
		Class<? extends Window> containerClass = container.getClass();
		String resource = containerClass.getName().replace('.', '/').concat(".xml");
		logger.info("render resource {}", resource);
		if ( getSwingEngine() == null )
			setSwingEngine(createEngine(container));
		return getSwingEngine().render(resource);
	}

	/**
	 * Start the parsing of the XML file path.
	 * 
	 * @param container A target container.
	 * @param resource An XML file path reference.
	 * 
	 * @return Instantiated Swing object tree root.
	 * 
	 * @throws Exception When the resource cannot be parsed.
	 * 
	 * @apiNote {@code <T>} The container type.
	 */
	public final T render(T container, String resource)
		throws Exception
	{
		if ( null != resource )
		{
			if ( getSwingEngine() == null )
				setSwingEngine(createEngine(container));
			return getSwingEngine().render(resource);
		}
		else
			throw new IllegalArgumentException("resource is null!");
	}

	/**
	 * Start the parsing of the XML file.
	 * 
	 * @param container A target container.
	 * @param reader An XML file I/O reader.
	 * 
	 * @return Instantiated Swing object tree root.
	 * 
	 * @throws Exception When the resource cannot be parsed.
	 * 
	 * @apiNote {@code <T>} The container type.
	 */
	public final T render(T container, Reader reader)
		throws Exception
	{
		if ( null != reader )
		{
			if ( getSwingEngine() == null )
				setSwingEngine(createEngine(container));
			return getSwingEngine().render(reader);
		}
		else
			throw new IllegalArgumentException("reader is null!");
	}
	
	/**
	 * Start the parsing of the XML {@link File} instance.
	 * 
	 * @param container A target container.
	 * @param xmlFile An XML {@link File} instance.
	 * 
	 * @return Instantiated Swing object tree root.
	 * 
	 * @throws Exception When the resource cannot be parsed.
	 * 
	 * @apiNote {@code <T>} The container type.
	 */
	public final T render(T container, File xmlFile)
		throws Exception
	{
		if ( null != xmlFile )
		{
			if ( getSwingEngine() == null )
				setSwingEngine(createEngine(container));
			return getSwingEngine().render(xmlFile);
		}
		else
			throw new IllegalArgumentException("xmlFile is null!");
	}

	/**
	 * Start the parsing of the XML {@link URL} instance.
	 * 
	 * @param container A target container.
	 * @param url An XML {@link URL} instance.
	 * 
	 * @return Instantiated Swing object tree root.
	 * 
	 * @throws Exception When the resource cannot be parsed.
	 * 
	 * @apiNote {@code <T>} The container type.
	 */
	public final T render(T container, java.net.URL url)
		throws Exception
	{
		if ( null != url )
		{
			if ( getSwingEngine() == null )
				setSwingEngine(createEngine(container));
			return getSwingEngine().render(url);
		}
		else
			throw new IllegalArgumentException("url is null!");
	}
	
	public class ConfirmExit implements ExitListener
	{
		private String message;
		public String getMessage() { return message; }
		public void setMessage(String message) { this.message = message; }
		
		public ConfirmExit()
		{
			this("Confirm Exit?");
		}
		
		public ConfirmExit(String message)
		{
			setMessage(message);
		}

		@Override
		public boolean canExit(EventObject eo)
		{
			Object source = (eo != null) ? eo.getSource() : null;
			Component owner = (source instanceof Component) ? (Component) source : null;
			int option = showConfirmDialog(owner, getMessage());
			return option == YES_OPTION;
		}

		@Override
		public void willExit(EventObject e)
		{
		}
	}
	
	public static void showErrorDialog(Exception ex)
	{
		String msg = ex.getClass().getSimpleName() + ": " + ex.getMessage();
		showMessageDialog(null, msg, "ERROR", ERROR_MESSAGE);
	}
}
