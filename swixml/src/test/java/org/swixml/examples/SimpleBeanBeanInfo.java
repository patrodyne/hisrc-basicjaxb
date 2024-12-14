package org.swixml.examples;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

import org.jvnet.basicjaxb.lang.FieldDescriptor;

/**
 * Extends {@link SimpleBeanInfo} and implements the {@link BeanInfo} interface
 * to provide explicit information about the methods, properties, events, and
 * other features of {@link SimpleBean}.
 * 
 * <p>
 * When developing a bean {@code MyPojo}, you can provide the bean features
 * required for an application by implementing a {@code BeanInfo} class using
 * the standard naming convention {@code MyPojoBeanInfo}. They will be obtained
 * through the automatic analysis by using the low-level reflection of the bean
 * methods and applying standard design patterns. You have an opportunity to
 * provide additional bean information through various descriptor classes.
 * </p>
 * 
 * @author sorrentino
 */
public class SimpleBeanBeanInfo extends SimpleBeanInfo
{
	private static final int PROPERTY_age = 0;
	private static final int PROPERTY_name = 1;
	private static final int PROPERTY_field3 = 2;
	private static final int PROPERTY_field4 = 3;

	@Override
	public PropertyDescriptor[] getPropertyDescriptors()
	{
		try
		{
			FieldDescriptor[] properties = new FieldDescriptor[]
			{
			 	new FieldDescriptor("age", org.swixml.examples.SimpleBean.class, "getAge", "setAge"),
			 	new FieldDescriptor("name", org.swixml.examples.SimpleBean.class, "getName", "setName"),
			 	new FieldDescriptor("field3", org.swixml.examples.SimpleBean.class, "getField3", "setField3"),
			 	new FieldDescriptor("field4", org.swixml.examples.SimpleBean.class, "getField4", "setField4")
			};
			// SWIXML2 extension
			properties[PROPERTY_age].setIndex(2);
			properties[PROPERTY_name].setIndex(1);
			properties[PROPERTY_name].setEditable(true);
			properties[PROPERTY_name].setDisplayName("Name");
			properties[PROPERTY_age].setDisplayName("Age");
			properties[PROPERTY_field3].setDisplayName("3rd field");
			properties[PROPERTY_field4].setDisplayName("4th field");
			return properties;
		}
		catch (IntrospectionException e)
		{
			e.printStackTrace();
		}
		return new PropertyDescriptor[0];
	}

	@Override
	public BeanDescriptor getBeanDescriptor()
	{
		return new BeanDescriptor(org.swixml.examples.SimpleBean.class, null);
	}
}
