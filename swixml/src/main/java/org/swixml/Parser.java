package org.swixml;

import static jakarta.el.ELManager.getExpressionFactory;
import static org.jdesktop.beansbinding.ELProperty.create;
import static org.swixml.SwingEngine.ENGINE_PROPERTY;
import static org.swixml.jsr295.BindingUtils.isELPattern;

import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.RootPaneContainer;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.beansbinding.PropertyResolutionException;
import org.swixml.annotation.SchemaAware;
import org.swixml.converters.LocaleConverter;
import org.swixml.converters.PrimitiveConverter;
import org.swixml.converters.Util;
import org.swixml.dom.Attribute;
import org.swixml.dom.DOMUtil;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jakarta.el.ELContext;
import jakarta.el.ELProcessor;
import jakarta.el.ValueExpression;

/**
 * Singleton Parser to render XML for Swing Documents
 * 
 * @author <a href="mailto:wolf@wolfpaulus.com">Wolf Paulus</a>
 * @author <a href="mailto:fm@authentidate.de">Frank Meissner</a>
 * 
 * @version $Revision: 1.5 $
 * 
 * @see org.swixml.SwingTagLibrary
 * @see org.swixml.ConverterLibrary
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class Parser implements LogAware
{
	public static final String ATTR_TEXT = "text";
	public static final String TAG_GRIDBAGCONSTRAINTS = "gridbagconstraints";
	
	//
	// Custom TAGs
	//
	public static final String TAG_CONSTRAINTS = "constraints";
	public static final String TAG_BUTTONGROUP = "buttongroup";
	public static final String TAG_TABLECOLUMN = "tablecolumn";
	public static final String TAG_TABLEHEADER = "tableheader";
	
	//
	// Custom Attributes
	//
	
	/**
	 * Additional attribute to collect layout constrain information
	 */
	@SchemaAware
	public static final String ATTR_CONSTRAINTS = TAG_CONSTRAINTS;
	
	/**
	 * Additional attribute to collect information about the PLAF implementation
	 */
	@SchemaAware
	public static final String ATTR_PLAF = "plaf";
	
	/**
	 * Additional attribute to collect information about the PLAF theme implementation
	 */
	@SchemaAware
	public static final String ATTR_PLAF_THEME = "plafTheme";
	
	/**
	 * Additional attribute to collect layout constrain information
	 */
	@SchemaAware
	public static final String ATTR_BUNDLE = "bundle";
	
	/**
	 * Additional attribute to collect layout constrain information
	 */
	@SchemaAware
	public static final String ATTR_LOCALE = "locale";
	
	/**
	 * Allows to provides swixml tags with an unique id
	 */
	@SchemaAware
	public static final String ATTR_ID = "id";
	
	/**
	 * Allows to provides swixml tags with a reference to another tag
	 */
	@SchemaAware
	public static final String ATTR_REFID = "refid";
	
	/**
	 * Allows to provides swixml tags with a reference to another tag
	 *
	 * @see #ATTR_REFID
	 * @deprecated use refid instead
	 */
	@SchemaAware
	@Deprecated
	public static final String ATTR_USE = "use";
	
	/**
	 * Allows to provides swixml tags with an unique id
	 */
	@SchemaAware
	public static final String ATTR_INCLUDE = "include";
	
	/**
	 * Allows to provides swixml tags with a dynamic update class
	 */
	@SchemaAware
	public static final String ATTR_INITCLASS = "initclass";
	
	/**
	 * Allows to provides swixml tags with a dynamic update class
	 */
	public static final String ATTR_ACTION = "action";
	
	/**
	 * Prefix for all MAC OS X related attributes
	 */
	public static final String ATTR_MACOS_PREFIX = "macos_";
	
	/**
	 * Attribute name that flags an Action as the default Preferences handler on
	 * a Mac
	 */
	public static final String ATTR_MACOS_PREF = ATTR_MACOS_PREFIX + "preferences";
	
	/**
	 * Attribute name that flags an Action as the default Aboutbox handler on a
	 * Mac
	 */
	public static final String ATTR_MACOS_ABOUT = ATTR_MACOS_PREFIX + "about";
	
	/**
	 * Attribute name that flags an Action as the default Quit handler on a Mac
	 */
	public static final String ATTR_MACOS_QUIT = ATTR_MACOS_PREFIX + "quit";
	
	/**
	 * Attribute name that flags an Action as the default Open Application
	 * handler on a Mac
	 */
	public static final String ATTR_MACOS_OPENAPP = ATTR_MACOS_PREFIX + "openapp";
	
	/**
	 * Attribute name that flags an Action as the default Open File handler on a
	 * Mac
	 */
	public static final String ATTR_MACOS_OPENFILE = ATTR_MACOS_PREFIX + "openfile";
	
	/**
	 * Attribute name that flags an Action as the default Print handler on a Mac
	 */
	public static final String ATTR_MACOS_PRINT = ATTR_MACOS_PREFIX + "print";
	
	/**
	 * Attribute name that flags an Action as the default Re-Open Application
	 * handler on a Mac
	 */
	public static final String ATTR_MACOS_REOPEN = ATTR_MACOS_PREFIX + "reopen";
	
	/**
	 * Method name used with initclass - if this exit, the update class will no
	 * be instanced but getInstance is called
	 */
	public static final String GETINSTANCE = "getInstance";
	
	/**
	 * Localized Attributes
	 */
	public static final Vector<String> LOCALIZED_ATTRIBUTES = new Vector<String>();
	
	/**
	 * set the bind target
	 */
	public static final String ATTR_BIND_WITH = "bindWith";
	public static final String TAG_SCRIPT = "script";
	
	/**
	 * Expression Language variable names
	 */
	public static final String ELVAR_DOM_ELEMENT = "domElement";
	public static final String ELVAR_DOM_ATTRIBUTE = "domAttribute";
	public static final String ELVAR_DOM_ATTR_CONSTRAINTS = "domAttrConstraints";
	
	/**
	 * the calling swingEngine
	 */
	private SwingEngine<?> swingEngine;
	public SwingEngine<?> getSwingEngine()
	{
		return swingEngine;
	}
	public void setSwingEngine(SwingEngine<?> swingEngine)
	{
		this.swingEngine = swingEngine;
	}
	
	public ELContext getElContext()
	{
		return getSwingEngine().getELContext();
	}

	public ELProcessor getElProcessor()
	{
		return getSwingEngine().getELProcessor();
	}

	//
	// Private Members
	//
	
	/**
	 * ConverterLib, to access COnverters, converting String in all kinds of
	 * things
	 */
	private static final ConverterLibrary CVTLIB = ConverterLibrary.getInstance();
	
	/**
	 * map to store id-id components, needed to support labelFor attributes
	 */
	private Map<JLabel, String> lbl_map = new HashMap<JLabel, String>(10);
	
	/**
	 * map to store specific Mac OS actions mapping
	 */
	private Map<String, Action> mac_map = new HashMap<String, Action>();
	
	/**
	 * Document, to be parsed
	 */
	private Document jdoc;
	
	/**
	 * Static Initializer adds Attribute Names into the LOCALIZED_ATTRIBUTES
	 * Vector Needs to be inserted all lower case.
	 */
	static
	{
		LOCALIZED_ATTRIBUTES.add("accelerator");
		LOCALIZED_ATTRIBUTES.add("disabledicons");
		LOCALIZED_ATTRIBUTES.add("displayedmnemonics");
		LOCALIZED_ATTRIBUTES.add("icon");
		LOCALIZED_ATTRIBUTES.add("icons");
		LOCALIZED_ATTRIBUTES.add("iconimage");
		LOCALIZED_ATTRIBUTES.add("label");
		LOCALIZED_ATTRIBUTES.add("mnemonic");
		LOCALIZED_ATTRIBUTES.add("mnemonics");
		LOCALIZED_ATTRIBUTES.add("name");
		LOCALIZED_ATTRIBUTES.add(ATTR_TEXT);
		LOCALIZED_ATTRIBUTES.add("title");
		LOCALIZED_ATTRIBUTES.add("titleat");
		LOCALIZED_ATTRIBUTES.add("titles");
		LOCALIZED_ATTRIBUTES.add("tooltiptext");
		LOCALIZED_ATTRIBUTES.add("tooltiptexts");
	}

	/**
	 * Constructs a new SwixML Parser for the provided swingEngine.
	 *
	 * @param engine <code>SwingEngine</code>
	 */
	public Parser(SwingEngine<?> engine)
	{
		setSwingEngine(engine);
	}

	/**
	 * Converts XML into a javax.swing object tree.
	 * 
	 * <pre>
	 * Note: This parse method does not return a swing object but converts all <b>sub</b> nodes
	 * of the xml documents root into seeing objects and adds those into the provided container.
	 * This is useful when a JApplet for instance already exists and need to get some gui inserted.
	 * </pre>
	 *
	 * @param jdoc <code>Document</code> providing the XML document
	 * @param container <code>Container</code> container for the XML root's
	 *            children
	 * @throws Exception if parsing fails
	 */
	public Object parse(Document jdoc, Container container)
		throws Exception
	{
		this.jdoc = jdoc;
		this.lbl_map.clear();
		this.mac_map.clear();
		
		Element element = processCustomAttributes(jdoc.getDocumentElement());
		Object result = getSwing(element, container);
		
		linkLabels();
		supportMacOS();
		this.lbl_map.clear();
		this.mac_map.clear();
		return result;
	}

	/**
	 * Looks for custom attributes to be processed.
	 *
	 * @param element <code>Element</code> custom attr. tag are looked for in
	 *            this jdoc element
	 * @return <code>Element</code> - passed in (and maybe modified) element
	 *         <br />
	 *         <b>Note:</b> <br />
	 *         Successfully processed custom attributes will be removed from
	 *         the jdoc element.
	 * @throws Exception if parsing fails
	 */
	private Element processCustomAttributes(Element element)
		throws Exception
	{
		//
		// set Locale
		//
		Attr locale = element.getAttributeNode(ATTR_LOCALE);
		if ( locale != null && locale.getValue() != null )
		{
			getSwingEngine().setLocale(LocaleConverter.conv(new Attribute(locale)));
			element.removeAttribute(ATTR_LOCALE);
		}
		//
		// set ResourceBundle
		//
		Attr bundle = element.getAttributeNode(ATTR_BUNDLE);
		if ( bundle != null && bundle.getValue() != null )
		{
			getSwingEngine().getLocalizer().setResourceBundle(bundle.getValue());
			element.removeAttribute(ATTR_BUNDLE);
		}
		//
		// Set Look and Feel based on ATTR_PLAF
		//
		Attr plaf = element.getAttributeNode(ATTR_PLAF);
		if ( plaf != null && plaf.getValue() != null && 0 < plaf.getValue().length() )
		{
			try
			{
				String plafClassName = plaf.getValue();
				// Set Theme before LAF!
				if ( MetalLookAndFeel.class.getName().equals(plafClassName) )
				{
					Attr plafTheme = element.getAttributeNode(ATTR_PLAF_THEME);
					if ( plafTheme != null && plafTheme.getValue() != null && 0 < plafTheme.getValue().length() )
					{
						try
						{
							StringTokenizer plafThemeTokens = new StringTokenizer(plafTheme.getValue(), "(,)");
							String plafThemeClassName = plafThemeTokens.nextToken(); 

							Class<?> plafThemeClass = getClass().getClassLoader()
								.loadClass(plafThemeClassName);
							
							Object[] plafThemeArgs = Util.sa(plafThemeTokens);
							
							Object theme = null;
							if ( plafThemeArgs.length > 0 )
							{
								Class<?>[] argTypes = new Class<?>[plafThemeArgs.length];
								for ( int index=0; index < argTypes.length; ++index )
									argTypes[index] = String.class;
								theme = plafThemeClass.getConstructor(argTypes).newInstance(plafThemeArgs);
							}
							else
								theme = plafThemeClass.getConstructor().newInstance();
							
							if ( theme instanceof MetalTheme )
								MetalLookAndFeel.setCurrentTheme((MetalTheme) theme);
						}
						catch (Exception ex)
						{
							if ( SwingEngine.DEBUG_MODE )
								logger.error("processCustomAttributes", ex);
						}
						element.removeAttribute(ATTR_PLAF_THEME);
					}
				}
				
				// Load the LAF for the given class name.
				UIManager.setLookAndFeel(plafClassName);
				
				// Reset the EL methods font cache.
				getSwingEngine().getELMethods().getFontMap().clear();
			}
			catch (Exception e)
			{
				if ( SwingEngine.DEBUG_MODE )
					logger.error("processCustomAttributes", e);
			}
			element.removeAttribute(ATTR_PLAF);
		}
		return element;
	}

	/**
	 * Helper Method to Link Labels to InputFields etc.
	 */
	private void linkLabels()
	{
		for ( JLabel lbl : lbl_map.keySet() )
		{
			String id = lbl_map.get(lbl).toString();
			try
			{
				lbl.setLabelFor((Component) getSwingEngine().getIdMap().get(id));
			}
			catch (ClassCastException e)
			{
				// intent. empty.
				if ( logger.isWarnEnabled() )
					logger.warn("linkLabels: class cast exception");
			}
		}
	}

	/**
	 * Link actions with the MacOS' system menu bar
	 */
	private void supportMacOS()
	{
	}

	/**
	 * Recursively converts <code>org.jdom.Element</code>s into
	 * <code>javax.swing</code> or <code>java.awt</code> objects.
	 *
	 * @param element <code>org.jdom.Element</code> XML tag
	 * @param tag <code>Object</code> if not null, only this elements children
	 *            will be processed, not the element itself
	 *            
	 * @return A <code>javax.swing</code> or <code>java.awt</code> object
	 *         representing Swing/AWT implementation of the XML tag.
	 *         
	 * @throws Exception - if parsing fails
	 */
	public Object getSwing(Element element, Object tag)
		throws Exception
	{
		Factory factory = getSwingEngine().getTaglib().getFactory(element.getLocalName());
		factory.setSwingEngine(getSwingEngine());
		
		// look for <id> attribute value
		final String id = element.getAttributeNode(ATTR_ID) != null
			? element.getAttribute(ATTR_ID).trim()
			: null;
		// either there is no id or the id is not user so far
		boolean unique = !getSwingEngine().getIdMap().containsKey(id);
		boolean constructed = false;
		if ( !unique )
		{
			throw new IllegalStateException(
				"id already in use: " + id + " : " + getSwingEngine().getIdMap().get(id).getClass().getName());
		}

		//
		// XInclude
		//
		if ( element.getAttributeNode(ATTR_INCLUDE) != null )
		{
			StringTokenizer st = new StringTokenizer(element.getAttribute(ATTR_INCLUDE), "#");
			element.removeAttribute(ATTR_INCLUDE);
			// Document doc = new
			// org.jdom.input.SAXBuilder().build(this.engine.getClassLoader().getResourceAsStream(st.nextToken()));
			final String token = st.nextToken();
			Document doc = DOMUtil.getDocumentBuilder().parse(getSwingEngine().getClassLoader().getResourceAsStream(token));
			Element xelem = find(doc.getDocumentElement(), st.nextToken().trim());
			if ( xelem != null )
				moveContent(xelem, element);
		}
		
		//
		// clone attribute if <em>refid</em> attribute is available
		//
		if ( element.getAttributeNode(ATTR_REFID) != null )
		{
			element = (Element) element.cloneNode(true);
			cloneAttributes(element);
			element.removeAttribute(ATTR_REFID);
		}
		else if ( element.getAttributeNode(ATTR_USE) != null )
		{
			logger.warn(String.format("Attribute [%s] for element [%s] is deprecated!", ATTR_USE,
				element.getLocalName()));
			element = (Element) element.cloneNode(true);
			cloneAttributes(element);
			element.removeAttribute(ATTR_USE);
		}
		
		//
		// let the factory instantiate a new object
		//
		NamedNodeMap attributes = element.getAttributes();
		if ( tag == null )
		{
			tag = fromFactory(factory, element, attributes);
			constructed = true;
		}
		
		//
		// put newly created object in the map if it has an <id> attribute
		// (uniqueness is given att this point)
		//
		if ( id != null )
		{
			getSwingEngine().getIdMap().put(id, tag);
			getSwingEngine().mapMember(tag, id);
		}
		//
		// add extra property
		//
		if ( tag instanceof JComponent )
			((JComponent) tag).putClientProperty(ENGINE_PROPERTY, getSwingEngine());
		
		////////////////////////////////////////////////
		// handle "layout" container and tag attributes.
		////////////////////////////////////////////////
		
		// Process container
		processContainer(tag, element, attributes);
		
		// Set up the EL processor for this tag.
		getElProcessor().defineBean("this", tag);
		// Process tag
		processTag(id, tag, factory, element, attributes);
		// Unset the EL processor for this tag.
		getElProcessor().defineBean("this", null);
		
		return (constructed ? tag : null);
	}
	
	protected void processContainer(Object tag, Element element, NamedNodeMap attributes)
	{
		if ( tag instanceof Container )
		{
			LayoutManager lm = null;
			// element.getChild("layout");
			Element layoutElement = DOMUtil.getChildByTagName(element, "layout"); 
			if ( layoutElement != null )
			{
				element.removeChild(layoutElement);
				String layoutType = layoutElement.getAttribute("type");
				LayoutConverter layoutConverter = LayoutConverterLibrary.getInstance()
					.getLayoutConverterByID(layoutType);
				if ( layoutConverter != null )
					lm = layoutConverter.convertLayoutElement(layoutElement);
			}
			if ( lm == null )
			{
				// search for case-insensitive "layout" attribute to ensure
				// compatibility
				Attr layoutAttr = null;
				for ( int i = 0; i < attributes.getLength(); i++ )
				{
					Attr attr = (Attr) attributes.item(i);
					if ( "layout".equalsIgnoreCase(attr.getLocalName()) )
					{
						layoutAttr = attr;
						// TODO VERIFY IF THIS REMOVE ALSO FROM DOCUMENT
						// attributes.removeNamedItem("layout");
						break;
					}
				}
				if ( layoutAttr != null )
				{
					element.removeAttributeNode(layoutAttr);
					String layoutType = layoutAttr.getValue();
					int index = layoutType.indexOf('(');
					if ( index > 0 )
					{
						// strip parameters
						layoutType = layoutType.substring(0, index); 
					}
					LayoutConverter layoutConverter = LayoutConverterLibrary.getInstance()
						.getLayoutConverterByID(layoutType);
					if ( layoutConverter != null )
					{
						// Set up the EL processor for this tag.
						getElProcessor().defineBean("this", layoutConverter.getLayoutManagerType());
						// Convert layout attribute to a LayoutManager
						lm = layoutConverter.convertLayoutAttribute
						(
							new Attribute(layoutAttr),
							getSwingEngine()
						);
						// Unset the EL processor for this tag.
						getElProcessor().defineBean("this", null);
					}
				}
			}
			if ( lm != null )
				setLayoutManager(tag, lm);
		}
	}
	
	protected void processTag(final String id, Object tag, Factory factory, Element element, NamedNodeMap attributes)
		throws Exception
	{
		List<Attribute> remainingAttrs = new ArrayList<Attribute>(attributes.getLength() + 2);
		
		/////////////////////////////////////////////////////////////////
		// 1st attempt to apply attributes (call setters on the objects)
		// put an action attribute at the beginning of the attribute list
		/////////////////////////////////////////////////////////////////
		Attr actionAttr = element.getAttributeNode(ATTR_ACTION);
		if ( actionAttr != null )
		{
			element.removeAttributeNode(actionAttr);
			//
			// Make an 'action' the 1st attribute to be processed -
			// otherwise the action would override already applied attributes
			// like text etc.
			//
			remainingAttrs.add(new Attribute(actionAttr));
		}
		
		////////////////////////////////////////////////////
		// put Tag's Text content into Text Attribute
		////////////////////////////////////////////////////
		if ( element.getAttributeNode(ATTR_TEXT) == null )
		{
			String text = DOMUtil.getText(element);
			if ( text != null && !text.isBlank() )
				remainingAttrs.add(new Attribute(ATTR_TEXT, text));
		}
		
		////////////////////////////////////////////////////
		// Apply tag's attributes
		////////////////////////////////////////////////////
		remainingAttrs = applyAttributes(tag, factory, Attribute.asList(remainingAttrs, attributes));
		
		////////////////////////////////////////////////////
		// process child tags
		////////////////////////////////////////////////////
		LayoutManager layoutMgr = getLayoutManager(tag);
		NodeList children = element.getChildNodes();
		for ( int i = 0; i < children.getLength(); ++i )
		{
			Node n = children.item(i);
			if ( n instanceof Element )
			{
				Element child = (Element) n;
				factory.process(this, tag, child, layoutMgr);
			}
		}
		
		////////////////////////////////////////////////////////////////
		// 2nd attempt to apply attributes (call setters on the objects)
		////////////////////////////////////////////////////////////////
		if ( remainingAttrs != null && !remainingAttrs.isEmpty() )
		{
			remainingAttrs = applyAttributes(tag, factory, remainingAttrs);
			if ( remainingAttrs != null )
			{
				for ( Attribute attr : remainingAttrs )
				{
					if ( JComponent.class.isAssignableFrom(tag.getClass()) )
					{
						((JComponent) tag).putClientProperty(attr.getLocalName(), attr.getValue());
						logger.debug("putClientProperty: id={}, class={}, attr={}, value={}",
							id, tag.getClass().getName(), attr.getLocalName(),
							(attr.getValue() != null) ? attr.getValue().replaceAll("\n", "") : "");
					}
					else
						logger.debug("{} not applied for tag: {}", attr.getLocalName(), element.getLocalName());
				}
			}
		}
	}
	
	/**
	 * Instantiate an instance of the registered tag element's class using the given factory.
	 * 
	 * <p>Optionally, an initial parameter class can be used as a constructor argument:</p>
	 * 
	 * <ol>
	 * <li>Attempt invoke a {@code initClass.getInstance()} method to get tag constructor argument.</li>
	 * <li>Attempt to construct {@code initClass} with a String parameter.</li>
	 * <li>Attempt to instantiate {@code initClass} with default constructor.</li>
	 * </ol>
	 * 
	 * <p>If an initial parameter is provided, then initialize a new tag object whose type is determined by
	 * the factory's backing class template; otherwise, create a new instance of a template class.</p>
	 * 
	 * @param tagFactory A factory to produce registered tags from a backing template.
	 * @param element The tag element to resolve.
	 * @param attributes The tag's attributes (obsolete/redundant?)
	 * 
	 * @return An instance of the registered tag's class.
	 * 
	 * @throws Exception When an initial parameter cannot be resolved.
	 */
	protected Object fromFactory(Factory tagFactory, Element element, NamedNodeMap attributes)
		throws Exception
	{
		Object obj;
		Object initParameter = null;
		if ( element.getAttributeNode(ATTR_INITCLASS) != null )
		{
			StringTokenizer st = new StringTokenizer(element.getAttribute(ATTR_INITCLASS), "( )");
			element.removeAttribute(ATTR_INITCLASS);
			
			try
			{
				if ( st.hasMoreTokens() )
				{
					// load update type
					Class<?> initClass = Class.forName(st.nextToken());
					
					// 1) Attempt invoke a getInstance() method.
					try
					{ 
						// look for a getInstance() method
						Method factoryMethod = initClass.getMethod(GETINSTANCE);
						if ( Modifier.isStatic(factoryMethod.getModifiers()) )
						{
							initParameter = factoryMethod.invoke(null);
						}
					}
					catch (NoSuchMethodException nsme)
					{
						// really nothing to do here
					}
					
					// 2) Attempt to construct with a String parameter.
					if ( initParameter == null && st.hasMoreTokens() )
					{
						try
						{
							// now try to construct with a String parameter.
							Constructor<?> ctor = initClass.getConstructor(new Class[] { String.class });
							String pattern = st.nextToken();
							initParameter = ctor.newInstance(new Object[] { pattern });
						}
						catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
						{
							// intentionally empty
						}
					}
					
					// 3) Attempt to instantiate with default constructor.
					if ( initParameter == null )
					{
						// now try to instantiate with default constructor
						initParameter = initClass.getDeclaredConstructor().newInstance();
					}
					
					// 4) TODO: Obsolete?
					if ( Action.class.isInstance(initParameter) )
					{
						for ( int i = 0, n = attributes.getLength(); i < n; i++ )
						{
							Attr attrib = (Attr) attributes.item(i);
							String attribName = attrib.getLocalName();
							if ( attribName != null && attribName.startsWith(ATTR_MACOS_PREFIX) )
								mac_map.put(attribName, (Action) initParameter);
						}
					}
				}
			}
			catch (ClassNotFoundException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException ex)
			{
				logger.error(ATTR_INITCLASS + " not instantiated : " + ex.getLocalizedMessage(), ex);
			}
			catch (RuntimeException re)
			{
				throw re;
			}
			catch (Exception ex)
			{
				throw new Exception(ATTR_INITCLASS + " not instantiated : " + ex.getLocalizedMessage(), ex);
			}
		}
		
		// If initParameter is not null, then initialize a new object
		// whose type is determined by the factory's backing class template;
		// otherwise, create a new instance of a template class.
		if ( initParameter != null )
			obj = tagFactory.newInstance(getSwingEngine(), initParameter);
		else
			obj = tagFactory.create(getSwingEngine(), element);
		return obj;
	}

	/**
	 * Is the given attribute an EL variable.
	 * 
	 * @param attr The given attribute.
	 * 
	 * @return True when the given attribute is
	 *         an EL variable.
	 */
	private boolean isELVariable(Attribute attr)
	{
		// Is binded variable, return no EL variable.
		if ( ATTR_BIND_WITH.equalsIgnoreCase(attr.getLocalName()) )
			return false;
		
		// Is EL variable?
		return isELPattern(attr.getValue());
	}

	/**
	 * Creates an object and sets properties based on the XML tag's attributes
	 *
	 * <ol>
	 *   <li><p>For every attribute, <code>createContainer()</code> first tries to find a setter in the given factory.</p>
	 *       <p>If a setter can be found and the converter exists to convert the parameter string into a type that fits the setter method, then the setter gets invoked.</p></li>
	 *   <li>Otherwise, <code>createContainer()</code> looks for a public field with a matching name.</li>
	 * </ol>
	 * 
	 * <p><b>Example:</b></p>
	 * <ol>
	 * <li>Try to create a parameter obj using the ParameterFactory: <p><code>background="FFC9AA" = container.setBackground ( new Color(attr.value) )</code></p></li>
	 * <li>Try to find a simple setter taking a primitive or String: <p><code>width="25" container.setWidth( new Integer( attr.  getIntValue() ) )</code></p></li>
	 * <li>Try to find a public field: <code>container.BOTTOM_ALIGNMENT</code></li>
	 * </ol>
	 * 
	 * @param tag <code>Object</code> object representing a tag found in the SWIXML descriptor document
	 * @param factory <code>Factory</code> factory to instantiate a new object
	 * @param attributes <code>List</code> attribute list
	 * 
	 * @return <code>List</code> - list of attributes that could not be applied.
	 * 
	 * @throws Exception
	 */
	private List<Attribute> applyAttributes(Object tag, Factory factory, List<Attribute> attributes)
		throws Exception
	{
		//
		// pass 1: Make an 'action' the 1st attribute to be processed -
		// otherwise the action would override already applied attributes like
		// text etc.
		//
//		for ( int i = 0; i < attributes.size(); i++ )
//		{
//			Attribute attr = (Attribute) attributes.get(i);
//			if ( ATTR_ACTION.equalsIgnoreCase(attr.getLocalName()) )
//			{
//				attributes.remove(i);
//				attributes.add(0, attr);
//				break;
//			}
//		}
		
		//
		// pass 2: process the attributes
		//

		// Prioritize attributes for EL processing, etc..
		prioritize(attributes);

		// Create an ApplyAttribute to store loop-wide results.
		ApplyAttribute aa = new ApplyAttribute();
		
		// Apply attributes
		for ( Attribute attr : attributes )
		{
			// Set up the EL context for this attribute.
			Attr domAttribute = attr.getDomAttribute();
			if ( domAttribute != null )
			{
				setVariable(ELVAR_DOM_ATTRIBUTE, domAttribute);
				setVariable(ELVAR_DOM_ELEMENT, domAttribute.getOwnerElement());
			}
			else
				logger.debug("Missing DOM attr: " + attr.getLocalName() +" = " + attr.getValue());
			
			// loop through all available attributes
			applyAttribute(getElContext(), tag, factory, attr, aa);
			
			// Unset the EL context for this attribute.
			unsetVariable(ELVAR_DOM_ELEMENT);
			unsetVariable(ELVAR_DOM_ATTRIBUTE);
		}
		
		// Some custom attributes may have been cached, clear them.
		// Ref: org.swixml.processor.ConstraintsTagProcessor.processComponent(Parser, Object, Element, LayoutManager)
		unsetVariable(ELVAR_DOM_ATTR_CONSTRAINTS);

		if ( aa.attr_id != null && aa.attr_name == null )
		{
			// Originally created just the Attribute without the DOM Attr.
			// Attribute nameAttribute = new Attribute("name", aa.attr_id.getValue());
			
			Document idDoc = aa.attr_id.getDomAttribute().getOwnerDocument();
			Attr nameDomAttr = idDoc.createAttribute("name");
			nameDomAttr.setValue(aa.attr_id.getValue());
			Attribute nameAttribute = new Attribute(nameDomAttr);
			
			aa.notAppliedAttrList.add(nameAttribute);
		}
		
		return aa.notAppliedAttrList;
	}
	
	private void prioritize(List<Attribute> attributes)
	{
		Attribute attr_plaf = null, attr_font = null;
		Attribute attr_size = null, attr_type = null;
		Attribute attr_bindWith = null, attr_bindList = null, attr_bindClass = null;
		for ( Attribute attr : attributes )
		{
			if ( "plaf".equalsIgnoreCase(attr.getLocalName()) )
				attr_plaf = attr;
			else if ( "font".equalsIgnoreCase(attr.getLocalName()) )
				attr_font = attr;
			else if ( "size".equalsIgnoreCase(attr.getLocalName()) )
				attr_size = attr;
			else if ( "type".equalsIgnoreCase(attr.getLocalName()) )
				attr_type = attr;
			else if ( "bindWith".equalsIgnoreCase(attr.getLocalName()) )
				attr_bindWith = attr;
			else if ( "bindList".equalsIgnoreCase(attr.getLocalName()) )
				attr_bindList = attr;
			else if ( "bindClass".equalsIgnoreCase(attr.getLocalName()) )
				attr_bindClass = attr;
		}
		// Order invocations by reverse priority
		// to achieve forward priority!
		prioritize(attributes, attr_bindClass);
		prioritize(attributes, attr_bindList);
		prioritize(attributes, attr_bindWith);
		prioritize(attributes, attr_type);
		prioritize(attributes, attr_size);
		prioritize(attributes, attr_font);
		prioritize(attributes, attr_plaf);
	}
	
	private void prioritize(List<Attribute> attributes, Attribute attr)
	{
		if ( attr != null )
		{
			attributes.remove(attr);
			attributes.add(0, attr);
		}
	}
	
	private class ApplyAttribute
	{
		Attribute attr_id = null;
		Attribute attr_name = null;
		// remember not applied attributes
		List<Attribute> notAppliedAttrList = new ArrayList<Attribute>(); 
		// used to insert an action into the macmap
		Action action = null;
	}
	
	/* applyAttribute: May be an EL parameter */
	private void applyAttribute(ELContext elContext, Object tag, Factory factory, Attribute attr, ApplyAttribute aa)
		throws Exception
	{
		// loop through all available attributes
		if ( ATTR_ID.equals(attr.getLocalName()) )
		{
			aa.attr_id = attr;
			return;
		}
		
		if ( ATTR_REFID.equals(attr.getLocalName()) )
			return;
		
		if ( ATTR_USE.equals(attr.getLocalName()) )
			return;
		
		if ( aa.action != null && attr.getLocalName().startsWith(ATTR_MACOS_PREFIX) )
		{
			mac_map.put(attr.getLocalName(), aa.action);
			return;
		}
		
		if ( JLabel.class.isAssignableFrom(tag.getClass()) && attr.getLocalName().equalsIgnoreCase("LabelFor") )
		{
			lbl_map.put((JLabel) tag, attr.getValue());
			return;
		}
		
		if ( "name".equals(attr.getLocalName()) )
			aa.attr_name = attr;
		
		// Resolve the parameter type, if possible.
		Class<?>[] paraTypes = factory.getPropertyType(tag, attr);
		Class<?> paraType = null;
		//
		// Warn: Some components like JSplitPane overload some methods like
		// 'setDividerLocation(int val)' and 'setDividerLocation(double val)'.
		// In this case, the first paraType is used.
		//
		if ( (paraTypes != null) && (paraTypes.length > 0) )
			paraType = paraTypes[0];
		
		// The attribute property's parameter value.
		Object para = null;
		
		/////////////////////////
		// Check for an EL parameter
		/////////////////////////
		if ( isELVariable(attr) )
		{
			try
			{
				try
				{
					// Use EL to get the attribute property's parameter value.
					ELProperty<Object, Object> elp = create(elContext, attr.getValue());
					para = elp.getValue(getSwingEngine().getClient());
				}
				catch ( UnsupportedOperationException ex )
				{
					logger.warn("property " + attr.getValue() + " is not readable!");
					return;
				}
				
				if ( para != null )
				{
					BeanProperty<Object, Object> bp = BeanProperty.create(attr.getLocalName());
					if ( bp.isWriteable(tag) )
					{
						// factory.setSimpleProperty(obj, attr.getLocalName(), para);
						// bp.setValue(tag, para);
						if ( paraType.isAssignableFrom(para.getClass()) )
							factory.setProperty(tag, attr, para, paraType);
						else
							applyAttribute(tag, factory, attr, aa, para, paraType);
					}
					else
						logger.warn("property " + attr.getLocalName() + " is not writable!");
					
					return;
				}
				else
					logger.warn("value of " + attr.getLocalName() + "=" + attr.getValue() + " is null! ignored!");
			}
			catch (PropertyResolutionException ex)
			{
				logger.warn("EL variable " + attr.getValue() + " cannot be resolved!", ex);
				return;
			}
		}
		
		////////////////////////
		// Not an EL parameter
		////////////////////////
		applyAttribute(tag, factory, attr, aa, para, paraType);
	}
	
	/* applyAttribute: Not an EL parameter */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void applyAttribute(Object tag, Factory factory, Attribute attr, ApplyAttribute aa, Object para, Class<?> paraType)
		throws Exception
	{
		if ( paraType != null )
		{
			/////////////////////////
			// A setter method has successfully been identified.
			// Is there a SwingEngine data type converter.
			/////////////////////////
			Converter<?> converter = CVTLIB.getConverter(paraType);
			if ( converter != null )
			{
				// call setter with a newly instanced parameter
				try
				{
					if ( para == null )
						para = converter.convert((Class) paraType, attr, getSwingEngine());
					else if ( para instanceof String )
					{
						attr.setValue((String) para);
						para = converter.convert((Class) paraType, attr, getSwingEngine());
					}
					
					if ( para instanceof Action )
						aa.action = (Action) para;
					
					// ATTR SET
					factory.setProperty(tag, attr, para, paraType); 
				}
				catch (NoSuchFieldException e)
				{
					// useful for extra attributes
					final String msg = String.format("property [%s] doesn't exist!", attr.getLocalName());
					if ( logger.isDebugEnabled() )
						logger.warn(msg, e);
					else
						logger.warn(msg);
					aa.notAppliedAttrList.add(attr);
				}
				catch (InvocationTargetException e)
				{
					Throwable cause = e.getCause();
					if ( cause != null )
					{
						if ( logger.isDebugEnabled() )
							logger.warn("exception during invocation of " + attr.getLocalName() + ": " + cause.getMessage());
						else
							logger.warn("exception during invocation of " + attr.getLocalName(),
								cause);
					}
					//
					// The JFrame class is slightly incompatible with Frame.
					// Like all other JFC/Swing top-level containers, a
					// JFrame contains a JRootPane as its only child.
					// The content pane provided by the root pane should, as
					// a rule, contain all the non-menu components
					// displayed by the JFrame.
					//
					if ( tag instanceof RootPaneContainer )
					{
						Container rootpane = ((RootPaneContainer) tag).getContentPane();
						Factory rpFactory = getSwingEngine().getTaglib().getFactory(rootpane.getClass());
						try
						{
							// ATTR SET
							rpFactory.setProperty(rootpane, attr, para, paraType);
						}
						catch (Exception ex)
						{
							aa.notAppliedAttrList.add(attr);
						}
					}
					else
						aa.notAppliedAttrList.add(attr);
				}
				catch (Exception e)
				{
					throw new Exception(e + ":" + attr.getLocalName() + ":" + para, e);
				}
				return;
			}
			
			//////////////////////////////////////
			// No SwingEngine data type converter.
			//////////////////////////////////////

			//
			// try this: call the setter with an Object.class Type
			//
			if ( paraType.equals(Object.class) )
			{
				try
				{
					String s = attr.getValue();
					if ( LOCALIZED_ATTRIBUTES.contains(attr.getLocalName().toLowerCase()) )
						s = getSwingEngine().getLocalizer().getString(s);
					
					// ATTR SET
					factory.setProperty(tag, attr, s, paraType);
				}
				catch (Exception e)
				{
					aa.notAppliedAttrList.add(attr);
				}
				return;
			}
			
			//
			// try this: call the setter with a primitive
			//
			if ( paraType.isPrimitive() )
			{
				try
				{
					// ATTR SET
					factory.setProperty(tag, attr, PrimitiveConverter.conv(paraType, attr, getSwingEngine()), paraType);
				}
				catch (Exception e)
				{
					aa.notAppliedAttrList.add(attr);
				}
				return;
			}
			
			//
			// try again later
			//
			aa.notAppliedAttrList.add(attr);
		}
		else
		{
			//////////////////////////////////////////
			// No parameter types; search for a field.
			//////////////////////////////////////////
			
			// Search for a public field in the obj.class that matches the
			// attribute name
			try
			{
				Field field = tag.getClass().getField(attr.getLocalName());
				if ( field != null )
				{
					Converter<?> converter = CVTLIB.getConverter(field.getType());
					if ( converter != null )
					{
						Class fieldType = field.getType();
						//
						// Localize Strings
						//
						Object fieldValue = converter.convert(fieldType, attr, getSwingEngine());
						if ( String.class.equals(converter.convertsTo()) )
							fieldValue = getSwingEngine().getLocalizer().getString((String) fieldValue);
						
						// ATTR SET
						field.set(tag, fieldValue);
					}
					else
						aa.notAppliedAttrList.add(attr);
				}
				else
					aa.notAppliedAttrList.add(attr);
			}
			catch (Exception e)
			{
				aa.notAppliedAttrList.add(attr);
			}
		}
	}
	
	public void setVariable(String name, Object value)
	{
		setVariable(getElContext(), name, value);
	}

	public void unsetVariable(String name)
	{
		unsetVariable(getElContext(), name);
	}
	
	private void setVariable(ELContext elContext, String name, Object value)
	{
		if ( value != null )
		{
			ValueExpression ve = getExpressionFactory().createValueExpression(value, value.getClass());
			elContext.getVariableMapper().setVariable(name, ve);
		}
	}

	private void unsetVariable(ELContext elContext, String name)
	{
		elContext.getVariableMapper().setVariable(name, null);
	}

	/**
	 * Copies attributes that element doesn't have yet form element[id]
	 *
	 * @param target <code>Element</code> target to receive more attributes
	 */
	private void cloneAttributes(Element target)
	{
		Element source = null;
		if ( target.getAttributeNode(ATTR_REFID) != null )
		{
			source = find(jdoc.getDocumentElement(), target.getAttribute(ATTR_REFID).trim());
		}
		else if ( target.getAttributeNode(ATTR_USE) != null )
		{
			logger.warn(String.format("Attribute [%s] for Element [%s] is deprecated!", ATTR_USE,
				target.getLocalName()));
			source = find(jdoc.getDocumentElement(), target.getAttribute(ATTR_USE).trim());
		}
		if ( source != null )
		{
			NamedNodeMap attributes = source.getAttributes();
			for ( int i = 0; i < attributes.getLength(); ++i )
			{
				final Attr attr = (Attr) attributes.item(i);
				String name = attr.getName().trim();
				//
				// copy but don't overwrite an attr.
				// also, don't copy the id attr.
				//
				if ( !ATTR_ID.equals(name) && target.getAttributeNode(name) == null )
				{
					target.setAttribute(attr.getName(), attr.getValue());
				}
			} // end while
		}
		else
		{
			logger.warn(String.format("source element [%s] not found!", target.getAttribute(ATTR_REFID)));
		}
	}

	/**
	 * Adds a child component to a parent component considering many differences
	 * between the Swing containers
	 *
	 * @param parent <code>Component</code>
	 * @param component <code>Component</code> child to be added to the parent
	 * @param constrains <code>Object</code> constraints
	 * @return <code>Component</code> - the passed in component
	 */
	public Component addChild(Container parent, Component component, Object constrains)
	{
		if ( component == null )
			return null;
		//
		// Set a JMenuBar for JFrames, JDialogs, etc.
		//
		if ( component instanceof JMenuBar )
		{
			try
			{
				Method m = parent.getClass().getMethod("setJMenuBar", JMenuBar.class);
				m.invoke(parent, component);
			}
			catch (NoSuchMethodException e)
			{
				if ( constrains == null )
					parent.add(component);
				else
					parent.add(component, constrains);
			}
			catch (Exception e)
			{
				// intentionally empty
			}
		}
		else if ( parent instanceof RootPaneContainer )
		{
			//
			// add component into RootContainr
			// All Swing top-level containers contain a JRootPane as their only
			// child.
			// The content pane provided by the root pane should contain all the
			// non-menu components.
			//
			RootPaneContainer rpc = (RootPaneContainer) parent;
			if ( component instanceof LayoutManager )
				rpc.getContentPane().setLayout((LayoutManager) component);
			else
				rpc.getContentPane().add(component, constrains);
		}
		else if ( parent instanceof JScrollPane )
		{
			//
			// add component into a ScrollPane
			//
			JScrollPane scrollPane = (JScrollPane) parent;
			scrollPane.setViewportView(component);
		}
		else if ( parent instanceof JSplitPane )
		{
			//
			// add component into a SplitPane
			//
			JSplitPane splitPane = (JSplitPane) parent;
			if ( splitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT )
			{
				//
				// Horizontal SplitPane
				//
				if ( splitPane.getTopComponent() == null )
					splitPane.setTopComponent(component);
				else
					splitPane.setBottomComponent(component);
			}
			else
			{
				//
				// Vertical SplitPane
				//
				if ( splitPane.getLeftComponent() == null )
					splitPane.setLeftComponent(component);
				else
					splitPane.setRightComponent(component);
			}
		}
		else if ( parent instanceof JMenuBar && component instanceof JMenu )
		{
			//
			// add Menu into a MenuBar
			//
			JMenuBar menuBar = (JMenuBar) parent;
			menuBar.add(component, constrains);
		}
		else if ( JSeparator.class.isAssignableFrom(component.getClass()) )
		{
			//
			// add Separator to a Menu, Toolbar or PopupMenu
			//
			try
			{
				if ( JToolBar.class.isAssignableFrom(parent.getClass()) )
					((JToolBar) parent).addSeparator();
				else if ( JPopupMenu.class.isAssignableFrom(parent.getClass()) )
					((JPopupMenu) parent).addSeparator();
				else if ( JMenu.class.isAssignableFrom(parent.getClass()) )
					((JMenu) parent).addSeparator();
				else if ( constrains != null )
					parent.add(component, constrains);
				else
					parent.add(component);
			}
			catch (ClassCastException e)
			{
				parent.add(component);
			}
		}
		else if ( parent instanceof Container )
		{
			//
			// add component into container
			//
			if ( constrains == null )
				parent.add(component);
			else
				parent.add(component, constrains);
		}
		return component;
	}

	/**
	 * Moves the content from the source into the target <code>Element</code>
	 *
	 * @param source <code>Element</code> Content provider
	 * @param target <code>Element</code> Content receiver
	 */
	private static void moveContent(Element source, Element target)
	{
		DOMUtil.moveContent(source, target);
	}

	/**
	 * Recursive element by id finder
	 *
	 * @param element <code>Element</code> start node
	 * @param id <code>String</code> id to look for
	 * @return <code>Element</code> - with the given id in the id attribute or
	 *         null if not found
	 */
	private static Element find(Element element, String id)
	{
		return DOMUtil.findByAttribute(element, ATTR_ID, id);
	}

	/**
	 * 
	 * @param obj
	 * @param lm
	 */
	private static void setLayoutManager(Object obj, LayoutManager lm)
	{
		if ( obj == null )
			throw new IllegalArgumentException("obj is null");
		if ( lm == null )
			throw new IllegalArgumentException("lm is null");
		if ( obj instanceof RootPaneContainer )
		{
			((RootPaneContainer) obj).getContentPane().setLayout(lm);
		}
		else if ( obj instanceof Container )
		{
			((Container) obj).setLayout(lm);
		}
	}

	/**
	 *
	 * @param obj
	 * @return
	 */
	private LayoutManager getLayoutManager(Object obj)
	{
		if ( obj == null )
			throw new IllegalArgumentException("obj is null");
		if ( obj instanceof RootPaneContainer )
		{
			return ((RootPaneContainer) obj).getContentPane().getLayout();
		}
		else if ( obj instanceof Container )
		{
			return ((Container) obj).getLayout();
		}
		return null;
	}
}
