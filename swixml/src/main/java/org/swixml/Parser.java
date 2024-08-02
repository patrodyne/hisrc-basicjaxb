package org.swixml;

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

import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.beansbinding.PropertyResolutionException;
import org.swixml.annotation.SchemaAware;
import org.swixml.converters.LocaleConverter;
import org.swixml.converters.PrimitiveConverter;
import org.swixml.dom.Attribute;
import org.swixml.dom.DOMUtil;
import org.swixml.jsr295.BindingUtils;
import org.swixml.el.ELUtility;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jakarta.el.ELContext;

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
	@Deprecated
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
	 * Attribute name that flags an Action as the default Re-Open Applicaiton
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

	//
	// Private Members
	//
	
	/**
	 * ConverterLib, to access COnverters, converting String in all kinds of
	 * things
	 */
	private ConverterLibrary cvtlib = ConverterLibrary.getInstance();
	
	/**
	 * map to store id-id components, needed to support labelFor attributes
	 */
	private Map<JLabel, String> lbl_map = new HashMap<JLabel, String>(10);
	
	/**
	 * map to store specific Mac OS actions mapping
	 */
	private Map<String, Action> mac_map = new HashMap<String, Action>();
	
	/**
	 * docoument, to be parsed
	 */
	private Document jdoc;
	
	/**
	 * Static Initializer adds Attribute Names into the LOCALIZED_ATTRIBUTES
	 * Vector Needs to be inserted all lowercase.
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
		Object result = getSwing(processCustomAttributes(jdoc.getDocumentElement()), container);
		linkLabels();
		supportMacOS();
		this.lbl_map.clear();
		this.mac_map.clear();
		return result;
	}

	/**
	 * Looks for custom attributes to be proccessed.
	 *
	 * @param element <code>Element</code> custom attr. tag are looked for in
	 *            this jdoc element
	 * @return <code>Element</code> - passed in (and maybe modified) element
	 *         <br />
	 *         <b>Note:</b> <br />
	 *         Successfully proccessed custom attributes will be removed from
	 *         the jdoc element.
	 * @throws Exception if parsing fails
	 */
	private Element processCustomAttributes(Element element)
		throws Exception
	{
		//
		// set Locale
		//
		Attr locale = element.getAttributeNode(Parser.ATTR_LOCALE);
		if ( locale != null && locale.getValue() != null )
		{
			getSwingEngine().setLocale(LocaleConverter.conv(new Attribute(locale)));
			element.removeAttribute(Parser.ATTR_LOCALE);
		}
		//
		// set ResourceBundle
		//
		Attr bundle = element.getAttributeNode(Parser.ATTR_BUNDLE);
		if ( bundle != null && bundle.getValue() != null )
		{
			getSwingEngine().getLocalizer().setResourceBundle(bundle.getValue());
			element.removeAttribute(Parser.ATTR_BUNDLE);
		}
		//
		// Set Look and Feel based on ATTR_PLAF
		//
		Attr plaf = element.getAttributeNode(Parser.ATTR_PLAF);
		if ( plaf != null && plaf.getValue() != null && 0 < plaf.getValue().length() )
		{
			try
			{
				UIManager.setLookAndFeel(plaf.getValue());
			}
			catch (Exception e)
			{
				if ( SwingEngine.DEBUG_MODE )
				{
					logger.error("processCustomAttributes", e);
				}
			}
			element.removeAttribute(Parser.ATTR_PLAF);
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
	 * <code>javax.swing</code> or <code>java.awt</code> objects
	 *
	 * @param element <code>org.jdom.Element</code> XML tag
	 * @param obj <code>Object</code> if not null, only this elements children
	 *            will be processed, not the element itself
	 * @return <code>java.awt.Container</code> representing the GUI
	 *         impementation of the XML tag.
	 * @throws Exception - if parsing fails
	 */
	public Object getSwing(Element element, Object obj)
		throws Exception
	{
		Factory factory = getSwingEngine().getTaglib().getFactory(element.getLocalName());
		factory.setSwingEngine(getSwingEngine());
		
		// look for <id> attribute value
		final String id = element.getAttributeNode(Parser.ATTR_ID) != null	? element.getAttribute(Parser.ATTR_ID).trim()
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
		if ( element.getAttributeNode(Parser.ATTR_INCLUDE) != null )
		{
			StringTokenizer st = new StringTokenizer(element.getAttribute(Parser.ATTR_INCLUDE), "#");
			element.removeAttribute(Parser.ATTR_INCLUDE);
			// Document doc = new
			// org.jdom.input.SAXBuilder().build(this.engine.getClassLoader().getResourceAsStream(st.nextToken()));
			final String token = st.nextToken();
			Document doc = DOMUtil.getDocumentBuilder().parse(getSwingEngine().getClassLoader().getResourceAsStream(token));
			Element xelem = find(doc.getDocumentElement(), st.nextToken().trim());
			if ( xelem != null )
			{
				moveContent(xelem, element);
			}
		}
		//
		// clone attribute if <em>refid</em> attribute is available
		//
		if ( element.getAttributeNode(Parser.ATTR_REFID) != null )
		{
			element = (Element) element.cloneNode(true);
			cloneAttributes(element);
			element.removeAttribute(Parser.ATTR_REFID);
		}
		else if ( element.getAttributeNode(Parser.ATTR_USE) != null )
		{
			logger.warn(String.format("Attribute [%s] for element [%s] is deprecated!", Parser.ATTR_USE,
				element.getLocalName()));
			element = (Element) element.cloneNode(true);
			cloneAttributes(element);
			element.removeAttribute(Parser.ATTR_USE);
		}
		//
		// let the factory instantiate a new object
		//
		NamedNodeMap attributes = element.getAttributes();
		if ( obj == null )
		{
			Object initParameter = null;
			if ( element.getAttributeNode(Parser.ATTR_INITCLASS) != null )
			{
				StringTokenizer st = new StringTokenizer(element.getAttribute(Parser.ATTR_INITCLASS), "( )");
				element.removeAttribute(Parser.ATTR_INITCLASS);
				// try {
				try
				{
					if ( st.hasMoreTokens() )
					{
						// load update type
						Class<?> initClass = Class.forName(st.nextToken()); 
						try
						{ // look for a getInstance() methode
							Method factoryMethod = initClass.getMethod(Parser.GETINSTANCE);
							if ( Modifier.isStatic(factoryMethod.getModifiers()) )
							{
								initParameter = factoryMethod.invoke(null);
							}
						}
						catch (NoSuchMethodException nsme)
						{
							// really nothing to do here
						}
						
						if ( initParameter == null && st.hasMoreTokens() )
						{ // now try to instantiate with String taking ctor
							try
							{
								Constructor<?> ctor = initClass.getConstructor(new Class[] { String.class });
								String pattern = st.nextToken();
								initParameter = ctor.newInstance(new Object[] { pattern });
							}
							catch (NoSuchMethodException e)
							{ // intentionally empty
							}
							catch (SecurityException e)
							{ // intentionally empty
							}
							catch (InstantiationException e)
							{ // intentionally empty
							}
							catch (IllegalAccessException e)
							{ // intentionally empty
							}
							catch (IllegalArgumentException e)
							{ // intentionally empty
							}
							catch (InvocationTargetException e)
							{ // intentionally empty
							}
						}
						
						if ( initParameter == null )
						{
							// now try to instantiate with default ctor
							initParameter = initClass.getDeclaredConstructor().newInstance();
						}
						
						if ( Action.class.isInstance(initParameter) )
						{
							for ( int i = 0, n = attributes.getLength(); i < n; i++ )
							{
								Attr attrib = (Attr) attributes.item(i);
								String attribName = attrib.getLocalName();
								if ( attribName != null && attribName.startsWith(ATTR_MACOS_PREFIX) )
								{
									mac_map.put(attribName, (Action) initParameter);
								}
							}
						}
					}
				}
				catch (ClassNotFoundException e)
				{
					logger.error(Parser.ATTR_INITCLASS + " not instantiated : " + e.getLocalizedMessage(),
						e);
				}
				catch (SecurityException e)
				{
					logger.error(Parser.ATTR_INITCLASS + " not instantiated : " + e.getLocalizedMessage(),
						e);
				}
				catch (IllegalAccessException e)
				{
					logger.error(Parser.ATTR_INITCLASS + " not instantiated : " + e.getLocalizedMessage(),
						e);
				}
				catch (IllegalArgumentException e)
				{
					logger.error(Parser.ATTR_INITCLASS + " not instantiated : " + e.getLocalizedMessage(),
						e);
				}
				catch (InvocationTargetException e)
				{
					logger.error(Parser.ATTR_INITCLASS + " not instantiated : " + e.getLocalizedMessage(),
						e);
				}
				catch (InstantiationException e)
				{
					logger.error(Parser.ATTR_INITCLASS + " not instantiated : " + e.getLocalizedMessage(),
						e);
				}
				catch (RuntimeException re)
				{
					throw re;
				}
				catch (Exception e)
				{
					throw new Exception(Parser.ATTR_INITCLASS + " not instantiated : " + e.getLocalizedMessage(), e);
				}
			}
			obj = initParameter != null ? factory.newInstance(getSwingEngine(), initParameter)
										: factory.create(getSwingEngine(), element);
			constructed = true;
		}
		//
		// put newly created object in the map if it has an <id> attribute
		// (uniqueness is given att this point)
		//
		if ( id != null )
		{
			getSwingEngine().getIdMap().put(id, obj);
			getSwingEngine().mapMember(obj, id);
		}
		//
		// add extra property
		//
		if ( obj instanceof JComponent )
		{
			((JComponent) obj).putClientProperty(SwingEngine.CLIENT_PROPERTY, getSwingEngine().getClient());
		}
		//
		// handle "layout" element or attribute
		//
		if ( obj instanceof Container )
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
				{
					lm = layoutConverter.convertLayoutElement(layoutElement);
				}
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
						layoutType = layoutType.substring(0, index); // strip
																		// parameters
					LayoutConverter layoutConverter = LayoutConverterLibrary.getInstance()
						.getLayoutConverterByID(layoutType);
					if ( layoutConverter != null )
					{
						lm = layoutConverter.convertLayoutAttribute(new Attribute(layoutAttr));
					}
				}
			}
			if ( lm != null )
				setLayoutManager(obj, lm);
		}
		////////////////////////////////////////////////////
		// process tag
		//
		////////////////////////////////////////////////////
		List<Attribute> remainingAttrs = new java.util.ArrayList<Attribute>(attributes.getLength() + 2);
		////////////////////////////////////////////////////
		// 1st attempt to apply attributes (call setters on the objects)
		// put an action attribute at the beginning of the attribute list
		////////////////////////////////////////////////////
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
			if ( text != null && !text.isEmpty() )
			{
				remainingAttrs.add(new Attribute(ATTR_TEXT, text));
			}
		}
		remainingAttrs = applyAttributes(obj, factory, Attribute.asList(remainingAttrs, attributes));
		////////////////////////////////////////////////////
		// process child tags
		//
		////////////////////////////////////////////////////
		LayoutManager layoutMgr = getLayoutManager(obj);
		NodeList children = element.getChildNodes();
		for ( int i = 0; i < children.getLength(); ++i )
		{
			Node n = children.item(i);
			if ( n instanceof Element )
			{
				Element child = (Element) n;
				factory.process(this, obj, child, layoutMgr);
			}
		}
		//
		// 2nd attempt to apply attributes (call setters on the objects)
		//
		if ( remainingAttrs != null && !remainingAttrs.isEmpty() )
		{
			remainingAttrs = applyAttributes(obj, factory, remainingAttrs);
			if ( remainingAttrs != null )
			{
				for ( Attribute attr : remainingAttrs )
				{
					if ( JComponent.class.isAssignableFrom(obj.getClass()) )
					{
						((JComponent) obj).putClientProperty(attr.getLocalName(), attr.getValue());
						logger.debug("putClientProperty: id={}, class={}, attr={}, value={}",
							id, obj.getClass().getName(), attr.getLocalName(),
							(attr.getValue() != null) ? attr.getValue().replaceAll("\n", "") : "");
					}
					else
						logger.debug("{} not applied for tag: {}", attr.getLocalName(), element.getLocalName());
				}
			}
		}
		return (constructed ? obj : null);
	}

	/**
	 * 
	 * @return
	 */
	private boolean isVariable(Attribute attr)
	{
		if ( ELUtility.isELAttribute(attr) )
			return false;
		// IS BOUNDED
		if ( ATTR_BIND_WITH.equalsIgnoreCase(attr.getLocalName()) )
			return false;
		return BindingUtils.isVariablePattern(attr.getValue());
	}

	/**
	 * Creates an object and sets properties based on the XML tag's attributes
	 *
	 * @param obj <code>Object</code> object representing a tag found in the
	 *            SWIXML descriptor document
	 * @param factory <code>Factory</code> factory to instantiate a new object
	 * @param attributes <code>List</code> attribute list
	 * @return <code>List</code> - list of attributes that could not be applied.
	 * @throws Exception
	 *             <p/>
	 *             <ol>
	 *             <li>For every attribute, createContainer() 1st tries to find
	 *             a setter in the given factory.<br>
	 *             if a setter can be found and converter exists to convert the
	 *             parameter string into a type that fits the setter method, the
	 *             setter gets invoked.</li>
	 *             <li>Otherwise, createContainer() looks for a public field
	 *             with a matching name.</li>
	 *             </ol>
	 *             </p>
	 *             <p>
	 *             <b>Example:</b><br>
	 *             <br>
	 *             1.) try to create a parameter obj using the ParameterFactory:
	 *             i.e. <br>
	 *             background="FFC9AA" = container.setBackground ( new
	 *             Color(attr.value) ) <br>
	 *             2.) try to find a simple setter taking a primitive or String:
	 *             i.e. <br>
	 *             width="25" container.setWidth( new Interger( attr.
	 *             getIntValue() ) ) <br>
	 *             3.) try to find a public field, <br>
	 *             container.BOTTOM_ALIGNMENT
	 *             </p>
	 */
	@SuppressWarnings("unchecked")
	private List<Attribute> applyAttributes(Object obj, Factory factory, List<Attribute> attributes)
		throws Exception
	{
		//
		// pass 1: Make an 'action' the 1st attribute to be processed -
		// otherwise the action would override already applied attributes like
		// text etc.
		//
		/*
		 * for ( int i = 0; i < attributes.size(); i++) { Attribute attr =
		 * (Attribute) attributes.get(i); if
		 * (Parser.ATTR_ACTION.equalsIgnoreCase(attr.getLocalName())) {
		 * attributes.remove(i); attributes.add(0, attr); break; } }
		 */
		
		//
		// pass 2: process the attributes
		//
		
		// remember not applied attributes
		final List<Attribute> notAppliedAttrList = new ArrayList<Attribute>(attributes.size()); 

		// used to insert an action into the macmap
		Action action = null; 
		Attribute attr_id = null;
		Attribute attr_name = null;
		for ( Attribute attr : attributes )
		{
			// loop through all available attributes
			if ( Parser.ATTR_ID.equals(attr.getLocalName()) )
			{
				attr_id = attr;
				continue;
			}
			if ( "name".equals(attr.getLocalName()) )
			{
				attr_name = attr;
			}
			if ( Parser.ATTR_REFID.equals(attr.getLocalName()) )
				continue;
			if ( Parser.ATTR_USE.equals(attr.getLocalName()) )
			{
				continue;
			}
			if ( action != null && attr.getLocalName().startsWith(Parser.ATTR_MACOS_PREFIX) )
			{
				mac_map.put(attr.getLocalName(), action);
				continue;
			}
			if ( JLabel.class.isAssignableFrom(obj.getClass()) && attr.getLocalName().equalsIgnoreCase("LabelFor") )
			{
				lbl_map.put((JLabel) obj, attr.getValue());
				continue;
			}
			// Method method = null;
			Object para = null;
			/////////////////////////
			if ( isVariable(attr) )
			{
				try
				{
					// we can use also Application.getInstance();
					Object owner = getSwingEngine().getClient(); 
					ELContext elContext = getSwingEngine().getELContext();
					ELProperty<Object, Object> elp = ELProperty.create(elContext, attr.getValue());
					if ( !elp.isReadable(owner) )
					{
						logger.warn("property " + attr.getValue() + " is not readable!");
						continue;
					}
					para = elp.getValue(owner);
					if ( null != para )
					{
						BeanProperty<Object, Object> bp = BeanProperty.create(attr.getLocalName());
						if ( bp.isWriteable(obj) )
						{
							// factory.setSimpleProperty(obj, attr.getLocalName(), para);
							bp.setValue(obj, para);
						}
						else
						{
							logger.warn("property " + attr.getLocalName() + " is not writable!");
						}
						continue;
					}
					else
					{
						logger.warn("value of " + attr.getLocalName() + "=" + attr.getValue() + " is null! ignored!");
					}
				}
				catch (PropertyResolutionException ex)
				{
					logger.warn("variable " + attr.getValue() + " doesn't exist!", ex);
					continue;
				}
			}
			
			////////////////////////
			Class<?>[] paraTypes = factory.getPropertyType(obj, attr.getLocalName());
			if ( null != paraTypes )
			{
				@SuppressWarnings("rawtypes")
				Class paraType = paraTypes[0];
				
				// A setter method has successfully been identified.
				Converter<?> converter = cvtlib.getConverter(paraType);
				
				if ( converter != null )
				{
					// call setter with a newly instanced parameter
					try
					{
						if ( null == para )
						{
							para = converter.convert(paraType, attr, getSwingEngine());
						}
						if ( para instanceof Action )
						{
							action = (Action) para;
						}
						// ATTR SET
						factory.setProperty(obj, attr, para, paraType); 
					}
					catch (NoSuchFieldException e)
					{
						// useful for extra attributes
						final String msg = String.format("property [%s] doesn't exist!", attr.getLocalName());
						if ( logger.isDebugEnabled() )
							logger.warn(msg, e);
						else
							logger.warn(msg);
						notAppliedAttrList.add(attr);
					}
					catch (InvocationTargetException e)
					{
						Throwable cause = e.getCause();
						if ( cause != null )
						{
							if ( logger.isDebugEnabled() )
								logger.warn("exception during invocation of "	+ attr.getLocalName() + ": "
												+ cause.getMessage());
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
						// displayed by the JFrame. The JFrame class is slightly
						// incompatible with Frame.
						//
						if ( obj instanceof RootPaneContainer )
						{
							Container rootpane = ((RootPaneContainer) obj).getContentPane();
							Factory f = getSwingEngine().getTaglib().getFactory(rootpane.getClass());
							try
							{
								// ATTR SET
								f.setProperty(rootpane, attr, para, paraType);
							}
							catch (Exception ex)
							{
								notAppliedAttrList.add(attr);
							}
						}
						else
						{
							notAppliedAttrList.add(attr);
						}
					}
					catch (Exception e)
					{
						throw new Exception(e + ":" + attr.getLocalName() + ":" + para, e);
					}
					continue;
				}
				//
				// try this: call the setter with an Object.class Type
				//
				if ( paraType.equals(Object.class) )
				{
					try
					{
						String s = attr.getValue();
						if ( Parser.LOCALIZED_ATTRIBUTES.contains(attr.getLocalName().toLowerCase()) )
						{
							s = getSwingEngine().getLocalizer().getString(s);
						}
						// ATTR SET
						factory.setProperty(obj, attr, s, paraType);
					}
					catch (Exception e)
					{
						notAppliedAttrList.add(attr);
					}
					continue;
				}
				//
				// try this: call the setter with a primitive
				//
				if ( paraType.isPrimitive() )
				{
					try
					{
						// ATTR SET
						factory.setProperty(obj, attr, PrimitiveConverter.conv(paraType, attr, getSwingEngine()), paraType);
					}
					catch (Exception e)
					{
						notAppliedAttrList.add(attr);
					}
					continue;
				}
				//
				// try again later
				//
				notAppliedAttrList.add(attr);
			}
			else
			{
				//
				// Search for a public field in the obj.class that matches the
				// attribute name
				//
				try
				{
					Field field = obj.getClass().getField(attr.getLocalName());
					if ( field != null )
					{
						Converter<?> converter = cvtlib.getConverter(field.getType());
						if ( converter != null )
						{
							@SuppressWarnings("rawtypes")
							Class fieldType = field.getType();
							//
							// Localize Strings
							//
							Object fieldValue = converter.convert(fieldType, attr, getSwingEngine());
							if ( String.class.equals(converter.convertsTo()) )
							{
								fieldValue = getSwingEngine().getLocalizer().getString((String) fieldValue);
							}
							field.set(obj, fieldValue); // ATTR SET
						}
						else
						{
							notAppliedAttrList.add(attr);
						}
					}
					else
					{
						notAppliedAttrList.add(attr);
					}
				}
				catch (Exception e)
				{
					notAppliedAttrList.add(attr);
				}
			}
		} // end_while
		if ( attr_id != null && attr_name == null )
		{
			notAppliedAttrList.add(new Attribute("name", attr_id.getValue()));
		}
		return notAppliedAttrList;
	}

	/**
	 * Copies attributes that element doesn't have yet form element[id]
	 *
	 * @param target <code>Element</code> target to receive more attributes
	 */
	private void cloneAttributes(Element target)
	{
		Element source = null;
		if ( target.getAttributeNode(Parser.ATTR_REFID) != null )
		{
			source = find(jdoc.getDocumentElement(), target.getAttribute(Parser.ATTR_REFID).trim());
		}
		else if ( target.getAttributeNode(Parser.ATTR_USE) != null )
		{
			logger.warn(String.format("Attribute [%s] for Element [%s] is deprecated!", Parser.ATTR_USE,
				target.getLocalName()));
			source = find(jdoc.getDocumentElement(), target.getAttribute(Parser.ATTR_USE).trim());
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
				if ( !Parser.ATTR_ID.equals(name) && target.getAttributeNode(name) == null )
				{
					target.setAttribute(attr.getName(), attr.getValue());
				}
			} // end while
		}
		else
		{
			logger.warn(String.format("source element [%s] not found!", target.getAttribute(Parser.ATTR_REFID)));
		}
	}

	/**
	 * Adds a child component to a parent component considering many differences
	 * between the Swing containers
	 *
	 * @param parent <code>Component</code>
	 * @param component <code>Component</code> child to be added to the parent
	 * @param constrains <code>Object</code> contraints
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
				{
					parent.add(component);
				}
				else
				{
					parent.add(component, constrains);
				}
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
			{
				rpc.getContentPane().setLayout((LayoutManager) component);
			}
			else
			{
				rpc.getContentPane().add(component, constrains);
			}
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
				{
					splitPane.setTopComponent(component);
				}
				else
				{
					splitPane.setBottomComponent(component);
				}
			}
			else
			{
				//
				// Vertical SplitPane
				//
				if ( splitPane.getLeftComponent() == null )
				{
					splitPane.setLeftComponent(component);
				}
				else
				{
					splitPane.setRightComponent(component);
				}
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
			// add compontent into container
			//
			if ( constrains == null )
			{
				parent.add(component);
			}
			else
			{
				parent.add(component, constrains);
			}
		}
		return component;
	}

	/**
	 * Moves the content from the source into the traget <code>Element</code>
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
		return DOMUtil.findByAttribute(element, Parser.ATTR_ID, id);
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
