package org.jvnet.basicjaxb.lang;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import jakarta.xml.bind.annotation.XmlID;

public class XmlIdReflector
{
	/**
     * Gets the getter method whose field is annotated with {@code @XmlID}
     * in the given class.
     *
     * @param clazz The class to inspect.
     *
     * @return The read method of the {@code @XmlID}, or null if not found.
     */
    public static Method getXmlIdGetter(Class<?> clazz)
    {
        while ( clazz != null )
        {
            for ( Field field : clazz.getDeclaredFields() )
            {
                // 1. Check if the @XmlID annotation is present on this field
                if ( field.isAnnotationPresent(XmlID.class) )
                {
    				// 2. Use PropertyDescriptor to get the getX() or isX() method
    				try
    				{
    					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
    					return pd.getReadMethod();
    				}
    				catch ( IntrospectionException ex )
    				{
    					// TODO: WARN
    					ex.printStackTrace();
    				}
                }
            }
        	clazz = clazz.getSuperclass();
        }
        return null; // Return null if no field has @XmlID
    }

	/**
     * Gets the value of the field annotated with {@code @XmlID} in the given object.
     *
     * @param obj The object instance to inspect.
     *
     * @return The String value of the {@code @XmlID}, or null if not found.
     */
    public static String getXmlIdValue(Object obj)
    {
    	if ( obj != null )
    	{
            Class<?> clazz = obj.getClass();
            do
            {
                for (Field field : clazz.getDeclaredFields())
                {
                    // 1. Check if the @XmlID annotation is present on this field
                    if (field.isAnnotationPresent(XmlID.class))
                    {
                        // 2. Make private fields accessible
                        field.setAccessible(true);
        				try
        				{
        	                // 3. Return the value of this field from the specific object instance
        	                Object value = field.get(obj);
        	                return value != null ? value.toString() : null;
        				}
        				catch (IllegalArgumentException | IllegalAccessException e1)
        				{
        					// 2. Or use PropertyDescriptor to get the getX() or isX() method
            				try
            				{
            					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
            					Method getter = pd.getReadMethod();
            	                // 3. Return the value of this field from the specific object instance
            					Object value = getter.invoke(obj);
            	                return value != null ? value.toString() : null;
            				}
            				catch (IntrospectionException | IllegalAccessException | InvocationTargetException e2)
            				{
            					// TODO: WARN
            					e2.printStackTrace();
            				}
        				}
                    }
                }
            	clazz = clazz.getSuperclass();
            } while ( clazz != null );
    	}
        return null; // Return null if no field has @XmlID
    }
}
