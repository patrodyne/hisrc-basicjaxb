package org.jvnet.basicjaxb.lang;

import static java.lang.Character.toUpperCase;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;

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
     * 
     * @throws IntrospectionException if an exception occurs during introspection.
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
	
}
