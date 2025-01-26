package org.jvnet.basicjaxb.lang;

import static java.lang.Character.toUpperCase;
import static org.jvnet.basicjaxb.lang.ContextUtils.findJAXBElementMethod;
import static org.jvnet.basicjaxb.lang.ContextUtils.findObjectFactoryClass;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * A {@code DataDescriptor} extends {@link BeanDescriptor} and provides global
 * information about a "bean", including its Java class, its displayName, etc.
 * In this context <em>data</em> refers to a collection of fields.
 * 
 * <p>This class adds properties to configure the display of GUI <em>data field(s)</em>.</p>
 */
public class DataDescriptor
	extends BeanDescriptor
{
	private static final String DATA_OBJECT_FACTORY = "data.objectFactory";
	private static final String DATA_OBJECT_FACTORY_CLASS = "data.objectFactoryClass";
	private static final String DATA_JAXB_ELEMENT_WRAPPER_METHOD = "data.jaxbElementWrapperMethod";
	
    /**
     * Create a {@code DataDescriptor} for a bean that has a customizer.
     *
     * @param beanClass The class that implements the bean.
     * @param customizerClass  The class that implements the bean's Customizer.
     */
	public DataDescriptor(Class<?> beanClass, Class<?> customizerClass)
	{
		super(beanClass, customizerClass);
	}

    /**
     * Create a {@code DataDescriptor} for a bean that doesn't have a customizer.
     *
     * @param beanClass The class that implements the bean.
     */
	public DataDescriptor(Class<?> beanClass)
	{
		super(beanClass);
	}
	
    /**
     * Constructs a {@code DataDescriptor} from an existing {@link BeanDescriptor}.
     * A <em>field</em> is a display component.
     *
     * @param bd A description of a Java bean.
     */
	public DataDescriptor(BeanDescriptor bd)
	{
		super(bd.getBeanClass(), bd.getCustomizerClass());
		
		//
		// FeatureDescriptor
		//
		
		setName(bd.getName());
		// Capitalize the display name when it defaults to the name.
		if ( isDefaultDisplayName(bd) )
			setDisplayName(capitalize(bd.getName()));
		setExpert(bd.isExpert());
		setHidden(bd.isHidden());
		setPreferred(bd.isPreferred());
		setShortDescription(bd.getShortDescription());
		
		//
		// DataDescriptor
		//
		// See getObjectFactoryClass()
		// See getRootElementMethod()
		// See isRootElement()
	}
	
	// Derived from getBeanClass()
	public Class<?> getObjectFactoryClass()
	{
		if ( getValue(DATA_OBJECT_FACTORY_CLASS) == null )
			setObjectFactoryClass(findObjectFactoryClass(getBeanClass()));
		return (Class<?>) getValue(DATA_OBJECT_FACTORY_CLASS);
	}
	public void setObjectFactoryClass(Class<?> objectFactoryClass)
	{
		setValue(DATA_OBJECT_FACTORY_CLASS, objectFactoryClass);
	}
	
	// Derived from getObjectFactoryClass()
	public Object getObjectFactory() throws ReflectiveOperationException
	{
		if ( getValue(DATA_OBJECT_FACTORY) == null )
			setObjectFactory(getObjectFactoryClass().getDeclaredConstructor().newInstance());
		return getValue(DATA_OBJECT_FACTORY);
	}
	public void setObjectFactory(Object objectFactory)
	{
		setValue(DATA_OBJECT_FACTORY, objectFactory);
	}
	
	/**
	 * Get the method from the {@code ObjectFactory} to create a {@code JAXBElement}
	 * wrapper for an instance of this descriptor's bean class.
	 * 
	 * @return The method to create a {@code JAXBElement} for this bean's type, or null.
	 */
	public Method getJAXBElementWrapperMethod()
	{
		Object value = getValue(DATA_JAXB_ELEMENT_WRAPPER_METHOD);
		if ( value == null )
		{
			String typeName = getBeanClass().getSimpleName();
			Method jaxbElementMethod = findJAXBElementMethod(getObjectFactoryClass(), typeName);
			if ( jaxbElementMethod != null )
				value = jaxbElementMethod;
			else
				value = "NO_METHOD";
			setValue(DATA_JAXB_ELEMENT_WRAPPER_METHOD, value);
		}
		if ( "NO_METHOD".equals(value) )
			return null;
		else
			return (Method) value;
	}
	public void setJAXBElementWrapperMethod(Method jaxbElementWrapperMethod)
	{
		setValue(DATA_JAXB_ELEMENT_WRAPPER_METHOD, jaxbElementWrapperMethod);
	}
	
	/**
	 * Is this bean a top level XML root element.
	 * 
	 * <p>
	 * XJC generates an {@code @XmlRootElement} Java annotation when an
	 * XML schema contains a top level element with an anonymous complex
	 * type; otherwise, top level elements with a named complex type
	 * cause XJC to generate a {@code createXXX} method in the 
	 * {@code ObjectFactory} to wrap a bean object in a {@code JAXBElement}
	 * wrapper.
	 * </p>
	 * 
	 * <p>
	 * Any instance in the JAXB context with a {@code @XmlRootElement} can be
	 * marshaled directly, without the need for a {@code JAXBElement} wrapper.
	 * Otherwise, an instance with a corresponding {@code JAXBElement}
	 * wrapper can be wrapped and the marshaled.
	 * </p>
	 * 
	 * @return True, if this bean a top level XML root element.
	 */
	public boolean isXmlRootElement()
	{
		return getBeanClass().isAnnotationPresent(XmlRootElement.class);
	}
	
	/**
	 * This descriptor's bean is managed using a {@code JAXBElement}
	 * wrapper.
	 * 
	 * @return True, when this descriptor's bean is managed using a
	 *         {@code JAXBElement} wrapper; otherwise, false.
	 */
	public boolean isXmlWrappedElement()
	{
		return getJAXBElementWrapperMethod() != null;
	}
	
	/**
	 * This descriptor's bean is a JAXB root or wrapped element.
	 * 
	 * @return True, when this descriptor's bean is a JAXB root
	 *         or wrapped element.
	 */
	public boolean isElement()
	{
		return isXmlRootElement() || isXmlWrappedElement();
	}
	
	/**
	 * Promote a {@link BeanDescriptor} to a {@link DataDescriptor}.
	 * 
	 * <p>Note: When not set, the display name is set to the capitalized name.</p>
	 * 
	 * @param bd A {@link BeanDescriptor} instance of {@link DataDescriptor}.
	 * 
	 * @return The instance of {@link DataDescriptor}
	 */
	public static DataDescriptor promote(BeanDescriptor bd)
	{
		// Capitalize the display name when it defaults to the name.
		if ( isDefaultDisplayName(bd) )
			bd.setDisplayName(capitalize(bd.getName()));
		return (DataDescriptor) bd;
	}
	
	/**
	 * Does the display name accessor return the default name?
	 * 
	 * @param bd A {@link BeanDescriptor} instance.
	 * 
	 * @return True when the display name defaults to the name.
	 */
	public static boolean isDefaultDisplayName(BeanDescriptor bd)
	{
		return bd.getName().equals(bd.getDisplayName()) ;
	}

	/**
	 * Return a capitalized version of the specified property name.
	 *
	 * @param name <code>String</code> The property name
	 * 
	 * @return <code>String</code> given String with 1st letter capitalized
	 *        or blank/null.
	 */
	public static String capitalize(final String name)
	{
		String cs = null;
		if ( (name != null) && (name.length() > 0) )
		{
			final char[] chars = name.toCharArray();
			chars[0] = toUpperCase(chars[0]);
			cs = new String(chars);
		}
		return cs;
	}
	
	public static class Constraint
	{
		private String name;
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }
		
		private String owner;
		public String getOwner() { return owner; }
		public void setOwner(String owner) { this.owner = owner; }

		private Source source = new Source();
		public Source getSource() { return source; }
		public void setSource(Source source) { this.source = source; }

		private Target target = null;
		public Target getTarget() { return target; }
		public void setTarget(Target target) { this.target = target; }

		public static class Source
		{
			private String name;
			public String getName() { return name; }
			public void setName(String name) { this.name = name; }
			
			private List<String> fields = new ArrayList<>();
			public List<String> getFields() { return fields; }
			public void setFields(List<String> fields) { this.fields = fields; }
			
			private boolean unique = false;
			public boolean isUnique() { return unique; }
			public void setUnique(boolean unique) { this.unique = unique; }
		}
		
		public static class Target
		{
			private String name;
			public String getName() { return name; }
			public void setName(String name) { this.name = name; }
			
			private List<String> fields = new ArrayList<>();
			public List<String> getFields() { return fields; }
			public void setFields(List<String> fields) { this.fields = fields; }
		}
	}
	
	private List<Constraint> constraints = new ArrayList<>();
	public List<Constraint> getConstraints() { return constraints; }
	public void setConstraints(List<Constraint> constraints) { this.constraints = constraints; }
	
}
