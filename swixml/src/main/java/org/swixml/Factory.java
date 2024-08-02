package org.swixml;

import java.awt.Container;
import java.awt.LayoutManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import org.swixml.dom.Attribute;
import org.w3c.dom.Element;

/**
 * An interface to represent a generic factory
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public interface Factory
{
	/** Specifies the prefix string for all setter methods */
	public static final String SETTER_ID = "set";
	public static final String ADDER_ID = "add";
	
	/**
	 * Get the rendering engine able to convert an SwixML
	 * descriptor into a <code>java.swing</code> UI.
	 * 
	 * @return The rendering engine to convert an SwixML
	 *         descriptor into a <code>java.swing</code> UI.
	 */
	public SwingEngine<?> getSwingEngine();
	/**
	 * Set rendering engine to convert SwixML into a Swing GUI.
	 * 
	 * @param swingEngine A rendering engine to convert SwixML into a Swing GUI.
	 */
	public void setSwingEngine(SwingEngine<?> swingEngine);

	/**
	 * Create a new component instance
	 *
	 * @return instance <code>Object</code> a new instance of a template class
	 * 
	 * @throws Exception
	 */
	Object create(SwingEngine<?> swingEngine, Element element)
		throws Exception;

	/**
	 * Creates a new Object which class is {@link #getTemplate()} and the
	 * constructor parameter are <code>parameter</code>.
	 *
	 * @param parameter <code>Object[]</code> the parameter array to be passed into the constructor
	 * 
	 * @return <code>Object</code>, the created object, an instance of the template class
	 * 
	 * @throws InstantiationException if the creation of the object failed
	 * @throws IllegalAccessException if the constructor is either private or protected.
	 * @throws InvocationTargetException if the constructor invoked throws an exception
	 */
	Object newInstance(SwingEngine<?> swingEngine, Object... parameter)
		throws InstantiationException, IllegalAccessException, InvocationTargetException;

	/**
	 * @return class - <code>Class</code> the backing class template
	 */
	Class<?> getTemplate();

	/**
	 * Get all setter property methods - useful for debug
	 * 
	 * @return <code>Collection</code> - containing all available setter methods
	 */
	Collection<Method> getSetters();

	/**
	 * Get an array of classes representing the property's parameter types.
	 * 
	 * @param bean Object representing a tag found in the SWIXML descriptor document.
	 * @param name The {@link Attribute}'s local name.
	 * 
	 * @return An array of classes representing the property's parameter types.
	 */
	Class<?>[] getPropertyType(Object bean, String name);

	/**
	 * Set a property value on a bean using the XML attribute value.
	 * 
	 * @param bean Object representing a tag found in the SWIXML descriptor document.
	 * @param attr An XML tag's attribute.
	 * @param value An XML tag's attribute bean parameter value.
	 * @param type An XML tag's attribute bean parameter type.
	 * 
	 * @throws Exception
	 */
	void setProperty(Object bean, Attribute attr, Object value, Class<?> type)
		throws Exception;

	/**
	 * Process a parent and/or its child elements.
	 * 
	 * @param parser Parser to render XML for Swing Documents
	 * @param parent If not null, only this elements children will be processed.
	 * @param child Represents an element in an XML document.
	 * @param layoutMgr Interface for classes that know how to lay out {@link Container}s
	 * 
	 * @return True, when successful.
	 * 
	 * @throws Exception When the process aborts.
	 */
	boolean process(Parser parser, Object parent, Element child, LayoutManager layoutMgr)
		throws Exception;
}
