package org.swixml;

import static java.lang.reflect.Modifier.isTransient;
import static org.swixml.dom.DOMUtil.getDocumentBuilder;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.jvnet.basicjaxb.dom.DOMUtils;
import org.swixml.dom.DOMUtil;
import org.swixml.el.ELMethods;
import org.swixml.localization.LocalizerDefaultImpl;
import org.swixml.localization.LocalizerJSR296Impl;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import jakarta.el.ELContext;
import jakarta.el.ELManager;
import jakarta.el.ELProcessor;
import jakarta.el.ELResolver;
import jakarta.el.ExpressionFactory;
import jakarta.el.FunctionMapper;
import jakarta.el.ImportHandler;
import jakarta.el.StandardELContext;
import jakarta.el.VariableMapper;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * The SwingEngine class is the rendering engine able to convert an XML
 * descriptor into a java.swing UI.
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.5 $
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @apiNote A generic AWT container type.
 */
public class SwingEngine<T extends Container> implements LogAware
{
	public static interface Namespaces
	{
		final String main = "http://www.swixml.org/2007/Swixml";
		final String script = "http://www.swixml.org/2007/Swixml/script";
	}
	
	//
	// Static Constants
	//

	public static final String ENGINE_PROPERTY = "org.swixml.swingengine";
	
	/**
	 * Mac OSX locale variant to localize strings like quit etc.
	 */
	public static final String MAC_OSX_LOCALE_VARIANT = "mac";
	
	/**
	 * XML Error
	 */
	@SuppressWarnings("unused")
	private static final String XML_ERROR_MSG = "Invalid SwiXML Descriptor.";
	
	/**
	 * IO Error Message.
	 */
	private static final String IO_ERROR_MSG = "Resource could not be found ";
	
	/**
	 * Mapping Error Message.
	 */
	private static final String MAPPING_ERROR_MSG = " could not be mapped to any Object and remained un-initialized.";

	//
	// Static Member Variables
	//
	
	/**
	 * Debug / Release Mode
	 */
	public static boolean DEBUG_MODE = true;
	
	/**
	 * main frame
	 */
	// private static Frame appFrame;
	
	/**
	 * static resource bundle
	 */
	private static String default_resource_bundle_name = null;
	
	/**
	 * static locale
	 */
	private static Locale default_locale = Locale.getDefault();
	
	/**
	 * static Mac OS X Support, set to true to support Mac UI specialties
	 */
	private static boolean MAC_OSX_SUPPORTED = false;
	
	//
	// Static Initializer
	//
	
	/** Display the Swing release version to system out. */
	static
	{
		logger.info("HiSrc SwixML");
	}

	public static boolean isDesignTime()
	{
		return Boolean.getBoolean("org.swixml.designTime");
	}
	
	/**
	 * Factory returning initialized Validator instances based on the default
	 * Jakarta Bean Validation provider and following the XML configuration.
	 */
	public static ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();

	//
	// Member Variables
	//
	
	/**
	 * Swixml Parser.
	 */
	private Parser parser = new Parser(this);
	
	/**
	 * Client object hosting the SwingEngine, alternative to extending the
	 * SwingEngine Class. The object which instantiated this SwingEngine.
	 */
	private T client;
	public T getClient() { return client; }
	public void setClient(T client) { this.client = client; }
	
	private ELMethods<T> elMethods;
	public ELMethods<T> getELMethods()
	{
		if ( elMethods == null )
			setELMethods(new ELMethods<T>(this));
		return elMethods;
	}
	public void setELMethods(ELMethods<T> elMethods)
	{
		this.elMethods = elMethods;
	}

	/**
	 * Swing object map, contains only those object that were given an id
	 * attribute.
	 */
	private Map<String, Object> idmap = new HashMap<String, Object>();
	
	/**
	 * Flattened Swing object tree, contains all object, even the ones without
	 * an id.
	 */
	private Collection<Component> components = null;
	
	/**
	 * used for localization.
	 */
	private Localizer localizer = null;
	public Localizer getLocalizer() { return localizer; }
	public void setLocalizer(Localizer localizer) { this.localizer = localizer; }
	
	/**
	 * used for bean validation.
	 */
	private Validator beanValidator = null;
	public Validator getBeanValidator()
	{
		if ( beanValidator == null )
			setBeanValidator(VALIDATOR_FACTORY.getValidator());
		return beanValidator;
	}
	public void setBeanValidator(Validator beanValidator)
	{
		this.beanValidator = beanValidator;
	}	
	
	//
	// Private Constants
	//

	/**
	 * Classloader to load resources
	 */
	private final TagLibrary taglib = SwingTagLibrary.getInstance(this);
	
	private final LayoutConverterLibrary layoutlib = LayoutConverterLibrary.getInstance();
	public LayoutConverterLibrary getLayoutLibrary() { return layoutlib; }

	/**
	 * Localizer, setup by parameters found in the xml descriptor.
	 */
	protected ClassLoader cl = this.getClass().getClassLoader();

	// Expression Processor
	private ELProcessor elProcessor = null;
	/**
	 * Get an {@link ELProcessor} for using Jakarta Expression Language in a stand-alone environment.
	 * 
	 * @return An API for using Jakarta Expression Language with {@link ExpressionFactory}.
	 */
	public ELProcessor getELProcessor()
	{
		if ( elProcessor == null )
			setELProcessor(new ELProcessor());
		return elProcessor;
	}
	/**
	 * Set an {@link ELProcessor} for using Jakarta Expression Language in a stand-alone environment.
	 * 
	 * @param elProcessor The API for using Jakarta Expression Language.
	 */
	public void setELProcessor(ELProcessor elProcessor)
	{
		this.elProcessor = elProcessor;
	}
	
	/**
	 * Get the {@link ELManager} maintains an instance of {@link ExpressionFactory}
	 * and {@link StandardELContext}, for parsing and evaluating Jakarta Expression
	 * Language expressions.
	 * 
	 * @return The {@link ELManager} to manage Jakarta EL parsing and evaluation environment. 
	 */
	public ELManager getELManager()
	{
		return getELProcessor().getELManager();
	}
	
	/**
	 * Get {@link ELContext} information for expression parsing and evaluation.
	 * 
	 * @return The {@link ELContext} with {@link ELResolver}, {@link ImportHandler},
	 *         {@link FunctionMapper}, and {@link VariableMapper}.
	 */
	public ELContext getELContext()
	{
		return getELManager().getELContext();
	}
	
	/**
	 * Default constructor for a SwingEngine.
	 */
	public SwingEngine()
	{
		if ( Application.getBooleanProperty(Application.USE_COMMON_LOCALIZER) )
			setLocalizer(new LocalizerJSR296Impl());
		else
			setLocalizer(new LocalizerDefaultImpl());
		getLocalizer().setResourceBundle(SwingEngine.default_resource_bundle_name);
		setLocale(SwingEngine.default_locale);
		
		FontUIResource fontUIR = new FontUIResource(new Font("Dialog", Font.PLAIN, 12));
		UIManager.getDefaults().put("Default.font", fontUIR);
	}
	
	/**
	 * Constructor to be used if the SwingEngine is not extend but used through
	 * object composition.
	 *
	 * @param client <code>Container</code> owner of this instance
	 */
	public SwingEngine(T client)
	{
		this();
		setClient(client);
	}
	
	/**
	 * Gets the parsing of the XML started.
	 *
	 * @param url <code>URL</code> url pointing to an XML descriptor
	 * @return <code>Object</code>- instanced swing object tree root
	 * @throws Exception
	 */
	public T render(final URL url)
		throws Exception
	{
		Reader reader = null;
		T obj = null;
		try ( InputStream in = url.openStream() )
		{
			if ( in != null )
			{
				reader = new InputStreamReader(in);
				obj = render(reader);
			}
			else
				throw new IOException(IO_ERROR_MSG + url.toString());
		}
		return obj;
	}

	/**
	 * Gets the parsing of the XML file started.
	 *
	 * @param resource <code>String</code> xml-file path info
	 * @return <code>Object</code>- instanced swing object tree root
	 */
	public T render(final String resource)
		throws Exception
	{
		Reader reader = null;
		T obj = null;
		try ( InputStream in = cl.getResourceAsStream(resource) )
		{
			if ( in != null )
			{
				reader = new InputStreamReader(in);
				obj = render(reader);
			}
			else
				throw new IOException(IO_ERROR_MSG + resource);
		}
		return obj;
	}

	/**
	 * Gets the parsing of the XML file started.
	 *
	 * @param xml_file <code>File</code> xml-file
	 * @return <code>Object</code>- instanced swing object tree root
	 */
	public T render(final File xml_file)
		throws Exception
	{
		if ( xml_file != null )
		{
			try ( Reader reader = new FileReader(xml_file) )
			{
				return render(reader);
			}
		}
		else
			throw new IOException();
	}

	/**
	 * Gets the parsing of the XML file started.
	 *
	 * @param xml_reader <code>Reader</code> xml-file path info
	 * @return <code>Object</code>- instanced swing object tree root
	 */
	public T render(final Reader xml_reader)
		throws Exception
	{
		if ( xml_reader != null )
		{
			try
			{
				Document doc = getDocumentBuilder().parse(new InputSource(xml_reader));
				if ( logger.isDebugEnabled() )
				{
					String xml = DOMUtils.transformToString(doc);
					logger.debug("XML: \n" + xml);
				}
				return render(doc);
			}
			catch (Exception e)
			{
				logger.error("parse exception", e);
				throw e;
			}
			// throw new Exception(SwingEngine.XML_ERROR_MSG);
		}
		else
			throw new IllegalArgumentException("input reader is null!");
	}

	/**
	 * Gets the parsing of the XML file started.
	 *
	 * @param jdoc <code>Document</code> xml gui descriptor
	 * @return <code>Object</code>- instanced swing object tree root
	 */
	@SuppressWarnings({ "unchecked" })
	private T render(final Document jdoc)
		throws Exception
	{
		T result = null;
		idmap.clear();
		try
		{
			// Parse Document into the client window or alone.
			if ( getClient() != null )
			{
				parser.parse(jdoc, getClient());
				result = getClient();
			}
			else
				result = (T) parser.parse(jdoc, null);
		}
		catch (Exception e)
		{
			logger.error("error parsing XML document", e);
			throw (e);
		}
		
		// reset components collection
		components = null;
		
		// initialize all client fields with UI components by their id
		// Issue 44
		// mapMembers(result);
		// if (Frame.class.isAssignableFrom(client.getClass()))
		//     SwingEngine.setAppFrame((Frame) client);
		return result;
	}

	/**
	 * Inserts swing object rendered from an XML document into the given
	 * container.
	 * 
	 * <p>Differently to the render methods, insert does NOT consider the root
	 * node of the XML document.</p>
	 * 
	 * <p><b>NOTE:</b> <code>insert()</code> does NOT <code>clear()</code> the
	 * idmap before rendering. Therefore, if this SwingEngine's parser was used
	 * before, the idmap still contains (key/value) pairs (id, JComponent obj. references).
	 * If insert() is NOT used to insert in a previously (with this very
	 * SwingEngine) rendered UI, it is highly recommended to clear the idmap:</p>
	 * 
	 * <pre>
	 *    <code>mySwingEngine.getIdMap().clear()</code>
	 * </pre>
	 *
	 * @param url <code>URL</code> url pointing to an XML descriptor *
	 * @param container <code>Container</code> target, the swing obj, are added
	 *            to.
	 * @throws Exception
	 */
	public void insert(final URL url, final T container)
		throws Exception
	{
		Reader reader = null;
		try ( InputStream in = url.openStream() )
		{
			if ( in != null )
			{
				reader = new InputStreamReader(in);
				insert(reader, container);
			}
			else
				throw new IOException(IO_ERROR_MSG + url.toString());
		}
	}

	/**
	 * Inserts swing objects rendered from an XML reader into the given
	 * container.
	 * 
	 * <p>Differently to the render methods, insert does NOT consider the
	 * root node of the XML document.</p>
	 * 
	 * <p><b>NOTE:</b> Insert() does NOT clear() the idmap before rendering.
	 * Therefore, if this SwingEngine's parser was used before, the idmap still
	 * contains (key/value) pairs (id, JComponent obj. references). If insert() is NOT
	 * used to insert in a previously (with this very SwingEngine) rendered UI, it is highly
	 * recommended to clear the idmap:</p>
	 * 
	 *    <pre><code>mySwingEngine.getIdMap().clear()</code></pre>
	 *
	 * @param reader <code>Reader</code> xml-file path info
	 * @param container <code>Container</code> target, the swing obj, are added to.
	 * @throws Exception
	 */
	public void insert(final Reader reader, final T container)
		throws Exception
	{
		if ( reader != null )
		{
			Document doc = DOMUtil.getDocumentBuilder().parse(new InputSource(reader));
			insert(doc, container);
		}
		else
			throw new IllegalArgumentException("input reader is null!");
	}

	/**
	 * Inserts swing objects rendered from an XML reader into the given
	 * container.
	 * 
	 * <p>Differently to the render methods, insert does NOT consider the
	 * root node of the XML document.</p>
	 * 
	 * <p><b>NOTE:</b> The insert() method does NOT clear() the idmap before rendering.
	 * Therefore, if this SwingEngine's parser was used before, the idmap still
	 * contains (key/value) pairs (id, JComponent obj. references).
	 * If insert() is NOT used to insert in a previously (with this very SwingEngine)
	 * rendered UI, it is highly recommended to clear the idmap:</p>
	 * 
	 *    <pre><code>mySwingEngine.getIdMap().clear()</code></pre>
	 *
	 * @param resource <code>String</code> xml-file path info
	 * @param container <code>Container</code> target, the swing obj, are added to.
	 * 
	 * @throws Exception
	 */
	public void insert(final String resource, final T container)
		throws Exception
	{
		Reader reader = null;
		try ( InputStream in = cl.getResourceAsStream(resource) )
		{
			if ( in != null )
			{
				reader = new InputStreamReader(in);
				insert(reader, container);
			}
			else
				throw new IOException(IO_ERROR_MSG + resource);
		}
	}

	/**
	 * Inserts swing objects rendered from an XML document into the given
	 * container.
	 * 
	 * <p>Differently to the parse methods, insert does NOT consider the
	 * root node of the XML document.</p>
	 * 
	 * <p><b>NOTE:</b> The insert() method does NOT clear() the idmap before rendering.
	 * Therefore, if this SwingEngine's parser was used before, the idmap still
	 * contains (key/value) pairs (id, JComponent obj. references).
	 * If insert() is NOT used to insert in a previously (with this very SwingEngine)
	 * rendered UI, it is highly recommended to clear the idmap:</p>
	 * 
	 *    <pre><code>mySwingEngine.getIdMap().clear()</code></pre>
	 *
	 * @param jdoc <code>Document</code> xml-doc path info
	 * @param container <code>Container</code> target, the swing obj, are added to
	 * 
	 * @throws Exception <code>Exception</code> exception thrown by the parser
	 */
	public void insert(final Document jdoc, final T container)
		throws Exception
	{
		setClient(container);
		try
		{
			parser.parse(jdoc, container);
		}
		catch (Exception e)
		{
			if ( SwingEngine.DEBUG_MODE )
				System.err.println(e);
			throw (e);
		}
		// reset components collection
		components = null;
		// initialize all client fields with UI components by their id
		mapMembers(getClient());
	}

	/**
	 * Sets the SwingEngine's global resource bundle name, to be used by all
	 * SwingEngine instances. This name can be overwritten however for a single
	 * instance, if a <code>bundle</code> attribute is places in the root tag of
	 * an XML descriptor.
	 *
	 * @param bundlename <code>String</code> the resource bundle name.
	 */
	public static void setResourceBundleName(String bundlename)
	{
		SwingEngine.default_resource_bundle_name = bundlename;
	}

	/**
	 * Sets the SwingEngine's global locale, to be used by all SwingEngine
	 * instances. This locale can be overwritten however for a single instance,
	 * if a <code>locale</code> attribute is places in the root tag of an XML
	 * descriptor.
	 *
	 * @param locale <code>Locale</code>
	 */
	public static void setDefaultLocale(Locale locale)
	{
		SwingEngine.default_locale = locale;
	}

	/**
	 * Use <code>Application.getInstance(SwingApplication.class).getMainFrame()</code>
	 * 
	 * @return <code>Window</code> a parent for all dialogs.
	 */
	public static Frame getAppFrame()
	{
		SingleFrameApplication app = Application.getInstance(SingleFrameApplication.class);
		if ( app == null )
		{
			logger.warn("Application getInstance() has returned null!");
			return null;
		}
		return app.getMainFrame();
	}

	/**
	 * Returns an Iterator for all parsed GUI components.
	 *
	 * @return <code>Iterator</code> GUI components iterator
	 */
	public Iterator<Component> getAllComponentItertor()
	{
		if ( components == null )
			traverse(getClient(), components = new ArrayList<Component>());
		return components.iterator();
	}

	/**
	 * Returns an Iterator for id-ed parsed GUI components.
	 *
	 * @return <code>Iterator</code> GUI components iterator
	 */
	public Iterator<?> getIdComponentItertor()
	{
		return idmap.values().iterator();
	}

	/**
	 * Returns the id map, containing all id-ed parsed GUI components.
	 *
	 * @return <code>Map</code> GUI components map
	 */
	public Map<String, Object> getIdMap()
	{
		return idmap;
	}

	/**
	 * Removes all un-displayable components from the id map and deletes the
	 * components collection (for recreation at the next request).
	 * 
	 * <p>A component is made un-displayable either when it is removed from a
	 * displayable containment hierarchy or when its containment hierarchy is
	 * made un-displayable. A containment hierarchy is made un-displayable when
	 * its ancestor window is disposed.</p>
	 *
	 * @return <code>int</code> number of removed components.
	 */
	public int cleanup()
	{
		List<Object> zombies = new ArrayList<Object>();
		Iterator<String> it = idmap.keySet().iterator();
		
		while (it != null && it.hasNext())
		{
			String key = it.next();
			Object obj = idmap.get(key);
			if ( obj instanceof Component && !((Component) obj).isDisplayable() )
				zombies.add(key);
		}
		
		for ( int i = 0; i < zombies.size(); i++ )
			idmap.remove(zombies.get(i));
		
		components = null;
		return zombies.size();
	}

	/**
	 * Removes the id from the internal from the id map, to make the given id
	 * available for re-use.
	 *
	 * @param id <code>String</code> assigned name
	 */
	public void forget(final String id)
	{
		idmap.remove(id);
	}

	/**
	 * Returns the UI component with the given name or null.
	 *
	 * @param id <code>String</code> assigned name
	 * @return <code>Component</code>- the GUI component with the given name or
	 *         null if not found.
	 */
	public Component find(final String id)
	{
		Object obj = idmap.get(id);
		
		if ( obj != null && !Component.class.isAssignableFrom(obj.getClass()) )
			obj = null;
		
		return (Component) obj;
	}

	/**
	 * Sets the locale to be used during parsing / String conversion
	 *
	 * @param l <code>Locale</code>
	 */
	public void setLocale(Locale l)
	{
		if ( SwingEngine.isMacOSXSupported() && SwingEngine.isMacOSX() )
		{
			String tag = l.getLanguage() + "-" + l.getCountry() + "-" + MAC_OSX_LOCALE_VARIANT;
			l = Locale.forLanguageTag(tag);
		}
		
		this.localizer.setLocale(l);
	}

	/**
	 * Sets the ResourceBundle to be used during parsing / String conversion
	 *
	 * @param bundlename <code>String</code>
	 */
	public void setResourceBundle(String bundlename)
	{
		this.localizer.setResourceBundle(bundlename);
	}

	/**
	 * ConverterLibrary and TagLibrary need to be set up before rendering is called.
	 * 
	 * @return <code>TagLibrary</code>- the TagLibrary to insert custom tags.
	 */
	public TagLibrary getTaglib()
	{
		return taglib;
	}

	/**
	 * Sets a classloader to be used for all <i>getResourse..()</i> and <i>
	 * loadClass()</i> calls. If no class loader is set, the SwingEngine's
	 * loader is used.
	 *
	 * @param cl <code>ClassLoader</code>
	 * @see ClassLoader#loadClass
	 * @see ClassLoader#getResource
	 */
	public void setClassLoader(ClassLoader cl)
	{
		this.cl = cl;
		this.localizer.setClassLoader(cl);
	}

	/**
	 * @return <code>ClassLoader</code>- the Classloader used for all <i>
	 *         getResourse..()</i> and <i>loadClass()</i> calls.
	 */
	public ClassLoader getClassLoader()
	{
		return cl;
	}
	
	

	/**
	 * Recursively Sets an ActionListener
	 * 
	 * <p>Backtracking algorithm: if al was set for a child component, 
	 * its not being set for its parent</p>
	 * 
	 * @param c <code>Component</code> start component
	 * @param al <code>ActionListener</code>
	 * 
	 * @return <code>boolean</code> true, if ActionListener was set.
	 */
	public boolean setActionListener(final Component c, final ActionListener al)
	{
		boolean b = false;
		if ( c != null )
		{
			if ( Container.class.isAssignableFrom(c.getClass()) )
			{
				final Component[] s = ((Container) c).getComponents();
				for ( Component value : s )
					b = b | setActionListener(value, al);
			}
			
			if ( !b )
			{
				if ( JMenu.class.isAssignableFrom(c.getClass()) )
				{
					final JMenu m = (JMenu) c;
					final int k = m.getItemCount();
					for ( int i = 0; i < k; i++ )
						b = b | setActionListener(m.getItem(i), al);
				}
				else if ( AbstractButton.class.isAssignableFrom(c.getClass()) )
				{
					((AbstractButton) c).addActionListener(al);
					b = true;
				}
			}
		}
		return b;
	}

	/**
	 * Walks the whole tree to add all components into the
	 * <code>components</code> collection.
	 * 
	 * <p>Note: There is another collection available that only tracks those object
	 * that were provided with an <em>id</em> attribute, which hold an unique id</p>
	 *
	 * @param c <code>Component</code> recursive start component.
	 * 
	 * @return <code>Iterator</code> to walk all components, not just the id
	 *         components.
	 */
	public Iterator<Component> getDescendants(final Component c)
	{
		List<Component> list = new ArrayList<Component>(12);
		SwingEngine.traverse(c, list);
		return list.iterator();
	}

	protected void mapMember(Object widget, String fieldName)
	{
		if ( getClient() == null )
		{
			if ( isDesignTime() )
				return;
			throw new IllegalStateException("client obj is null!");
		}
		final Class<?> cls = getClient().getClass();
		mapMember(widget, fieldName, cls);
	}

	/**
	 * @param widget
	 * @param fieldName
	 * @param cls
	 */
	protected void mapMember(Object widget, String fieldName, Class<?> cls)
	{
		// TODO Issue 44
		if ( cls == null )
			return;
		if ( widget == null )
			throw new IllegalArgumentException("parameter widget is null!");
		if ( fieldName == null )
			throw new IllegalArgumentException("parameter fieldName is null!");
		if ( getClient() == null )
			throw new IllegalStateException("client obj is null!");
		
		boolean fullaccess = true;
		Field field = null;
		try
		{
			try
			{
				field = cls.getDeclaredField(fieldName);
			}
			catch (SecurityException e)
			{
				fullaccess = false; // applet or otherwise restricted environment
				field = cls.getField(fieldName);
			}
		}
		catch (NoSuchFieldException e)
		{
			logger.debug(String.format("field [%s] in class [%s] doesn't exist! Ignored", fieldName, cls.getName()));
			// Since getDeclaredFields() only works on the class itself, not the
			// super class,
			// we need to make this recursive down to the object.class
			if ( fullaccess )
			{
				// only if we have access to the declared fields do we need to
				// visit the whole tree.
				mapMember(widget, fieldName, cls.getSuperclass());
			}
			return;
		}
		
		// field and object type need to be compatible and field must not be
		// declared Transient
		if ( field.getType().isAssignableFrom(widget.getClass()) && !isTransient(field.getModifiers()) )
		{
			try
			{
				// boolean accessible = field.isAccessible();
				boolean accessible = field.canAccess(getClient());
				field.setAccessible(true);
				field.set(getClient(), widget);
				field.setAccessible(accessible);
				logger.info(String.format("field [%s] mapped in class [%s]", fieldName, cls.getName()));
			}
			catch (IllegalArgumentException e)
			{
				// intentionally empty
			}
			catch (IllegalAccessException e)
			{
				// intentionally empty
			}
		}
		else
		{
			if ( !field.getType().isAssignableFrom(widget.getClass()) )
			{
				logger.warn(String.format("field [%s] not mapped in class [%s] because field type [%s] is not assignable from [%s].",
					fieldName, cls.getName(), field.getType(), widget.getClass()));
			}
			else if ( isTransient(field.getModifiers()) )
			{
				logger.warn(String.format("field [%s] not mapped in class [%s] because field type [%s] is transient.",
					fieldName, cls.getName(), field));
			}
		}
	}

	/**
	 * Introspect the given object's class and initializes its non-transient
	 * fields with objects that have been instanced during parsing. Mapping
	 * happens based on type and field name: the fields name has to be equal to
	 * the tag id, specified in the XML descriptor. The fields class has to be
	 * assignable (equals or super class..) from the class that was used to
	 * instance the tag.
	 *
	 * @param obj <code>Object</code> target object to be mapped with instanced
	 *            tags
	 */
	protected void mapMembers(Object obj)
	{
		if ( obj != null )
			mapMembers(obj, obj.getClass());
	}

	private void mapMembers(Object obj, Class<?> cls)
	{
		boolean fullaccess = true;
		if ( obj != null && cls != null && !Object.class.equals(cls) )
		{
			Field[] flds = null;
			try
			{
				flds = cls.getDeclaredFields();
			}
			catch (SecurityException e)
			{
				fullaccess = false; // applet or otherwise restricted environment
				flds = cls.getFields();
			}
			
			//
			// loops through class' declared fields and try to find a matching widget.
			//
			for ( int i = 0; i < flds.length; i++ )
			{
				Object widget = idmap.get(flds[i].getName());
				if ( widget != null )
				{
					// field and object type need to be compatible and field
					// must not be declared Transient
					if ( flds[i].getType().isAssignableFrom(widget.getClass())
							&& !Modifier.isTransient(flds[i].getModifiers()) )
					{
						try
						{
							//boolean accessible = flds[i].isAccessible();
							Object clsInstance = cls.getConstructor().newInstance();
							boolean accessible = flds[i].canAccess(clsInstance);
							flds[i].setAccessible(true);
							flds[i].set(obj, widget);
							flds[i].setAccessible(accessible);
						}
						catch (NoSuchMethodException | InvocationTargetException | InstantiationException e)
						{
							try
							{
								fullaccess = false;
								flds[i].set(obj, widget);
							}
							catch (IllegalAccessException e1)
							{
							}
						}
						catch (IllegalArgumentException e)
						{
							// intentionally empty
						}
						catch (IllegalAccessException e)
						{
							// intentionally empty
						}
					}
				}
				
				//
				// If an intended mapping didn't work out the objects member
				// would remain un-initialized.
				// To prevent this, we try to instantiate with a default ctor.
				//
				if ( flds[i] == null )
				{
					if ( !SwingEngine.DEBUG_MODE )
					{
						try
						{
							flds[i].set(obj, flds[i].getType().getDeclaredConstructor().newInstance());
						}
						catch (IllegalArgumentException | IllegalAccessException | InstantiationException
							| InvocationTargetException | NoSuchMethodException | SecurityException ex)
						{
							ex.printStackTrace();
						}
					}
					else
					{ 
						// SwingEngine.DEBUG_MODE)
						System.err.println(flds[i].getType() + " : " + flds[i].getName() + SwingEngine.MAPPING_ERROR_MSG);
					}
				}
			}
			
			// Since getDeclaredFields() only works on the class itself, not the
			// super class,
			// we need to make this recursive down to the object.class
			if ( fullaccess )
			{
				// only if we have access to the declared fields do we need to
				// visit the whole tree.
				mapMembers(obj, cls.getSuperclass());
			}
		}
	}

	/**
	 * Evaluate Swing component
	 */
	public static interface Predicate
	{
		public boolean evaluate(javax.swing.JComponent c);
	}

	/**
	 * Walks the whole tree and evaluate each JComponent using predicate.
	 *
	 * @param container <code>container</code> recursive start component.
	 * @param predicate evaluate component. Return false avoid to navigate it
	 * 
	 */
	public static void traverse(final java.awt.Container container, final Predicate predicate)
	{
		if ( container == null )
			return;
		
		if ( predicate == null )
			return;
		
		for ( int i = 0; i < container.getComponentCount(); ++i )
		{
			final java.awt.Component c = container.getComponent(i);
			if ( !(c instanceof javax.swing.JComponent) )
				continue;
			javax.swing.JComponent cc = (javax.swing.JComponent) c;
			if ( predicate.evaluate(cc) )
				traverse(cc, predicate);
		}
	}

	/**
	 * Walks the whole tree to add all components into the
	 * <code>components</code> collection.
	 * 
	 * <p>Note: There is another collection available that only tracks
	 * those object that were provided with an <em>id</em> attribute,
	 * which hold an unique id</p>
	 *
	 * @param c          <code>Component</code> recursive start component.
	 * @param collection <code>Collection</code> target collection.
	 */
	protected static void traverse(final Component c, Collection<Component> collection)
	{
		if ( c != null )
		{
			collection.add(c);
			if ( c instanceof JMenu )
			{
				final JMenu m = (JMenu) c;
				final int k = m.getItemCount();
				for ( int i = 0; i < k; i++ )
					traverse(m.getItem(i), collection);
			}
			else if ( c instanceof Container )
			{
				final Component[] s = ((Container) c).getComponents();
				for ( Component value : s )
					traverse(value, collection);
			}
		}
	}

	/**
	 * Enables or disables support of Mac OS X GUIs
	 *
	 * @param osx <code>boolean</code>
	 */
	public static void setMacOSXSuport(boolean osx)
	{
		SwingEngine.MAC_OSX_SUPPORTED = osx;
	}

	/**
	 * Indicates state of Mac OS X support (default is true = ON).
	 *
	 * @return <code>boolean</code>- indicating MacOS support is enabled
	 */
	public static boolean isMacOSXSupported()
	{
		return SwingEngine.MAC_OSX_SUPPORTED;
	}

	/**
	 * Indicates if currently running on Mac OS X
	 * 
	 * use Application.getInstance().isMacOSX();
	 *
	 * @return <code>boolean</code>- indicating if currently running on a MAC
	 */
	@Deprecated
	public static boolean isMacOSX()
	{
		return Application.getInstance().isMacOSX();
	}

	/**
	 * Displays the GUI during a RAD session. If the root component is neither a
	 * JFrame nor a JDialog, the a JFrame is instantiated and the root is added
	 * into the new frames content pane.
	 */
	public void test()
	{
		WindowListener wl = new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				super.windowClosing(e);
				System.exit(0);
			}
		};
		
		if ( getClient() != null )
		{
			Class<? extends Container> clientClass = getClient().getClass();
			if ( JFrame.class.isAssignableFrom(clientClass) || JDialog.class.isAssignableFrom(clientClass) )
			{
				((Window) getClient()).addWindowListener(wl);
				getClient().setVisible(true);
			}
			else
			{
				JFrame jf = new JFrame("SwiXml Test");
				jf.getContentPane().add(getClient());
				jf.pack();
				jf.addWindowListener(wl);
				jf.setVisible(true);
			}
		}
	}
}
