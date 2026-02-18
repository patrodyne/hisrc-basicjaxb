package org.jvnet.basicjaxb.lang;

import java.lang.reflect.Field;

import jakarta.xml.bind.annotation.XmlID;

public class XmlIdReflector
{
    /**
     * Finds the value of the field annotated with @XmlID in the given object.
     * @param obj The object instance to inspect.
     * @return The String value of the ID, or null if not found.
     */
    public static String getXmlIdValue(Object obj)
    {
    	if ( obj != null )
    	{
            Class<?> clazz = obj.getClass();
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
    	                Object value= field.get(obj);
    	                return value != null ? value.toString() : null;
    				}
    				catch (IllegalArgumentException | IllegalAccessException e)
    				{
    					// TODO: WARN
    					e.printStackTrace();
    				}
                }
            }
    	}
        return null; // Return null if no field has @XmlID
    }
}
