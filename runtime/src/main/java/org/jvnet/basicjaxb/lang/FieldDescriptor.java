package org.jvnet.basicjaxb.lang;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

public class FieldDescriptor
	extends PropertyDescriptor
{
	private static final String FIELD_INDEX = "column.index";
	private static final String FIELD_ALIGNMENT = "column.alignment";
	private static final String FIELD_EDITABLE = "column.editable";
	private static final String FIELD_RESIZEABLE = "column.resizable";
	private static final String FIELD_EDITOR_CLASS = "column.editorClass";
	private static final String FIELD_RENDERER_CLASS = "column.rendererClass";
	
	private static final String FIELD_MAX_WIDTH = "column.maxWidth";
	private static final String FIELD_MIN_WIDTH = "column.minWidth";
	private static final String FIELD_PREFERRED_WIDTH = "column.preferredWidth";
	private static final String FIELD_WIDTH = "column.width";
	
	/**
     * Constructs a FieldDescriptor for a property that follows
     * the standard Java convention by having getFoo and setFoo
     * accessor methods.
     *
     * @param propertyName The programmatic name of the property.
     * @param beanClass The Class object for the target bean.
     *          
     * @throws IntrospectionException if an exception occurs during introspection.
     */
	public FieldDescriptor(String propertyName, Class<?> beanClass)
		throws IntrospectionException
	{
		super(propertyName, beanClass);
	}
	
	//
	// FieldDescriptor: properties
	//
	
	public Integer getIndex()
	{
		return (Integer) getValue(FIELD_INDEX);
	}
	public void setIndex(int index)
	{
		setValue(FIELD_INDEX, index);
	}
	
	public String getAlignment()
	{
		return (String) getValue(FIELD_ALIGNMENT);
	}
	public void setAlignment(String alignment)
	{
		setValue(FIELD_ALIGNMENT, alignment);
	}
	
	public Boolean isEditable()
	{
		return (Boolean) getValue(FIELD_EDITABLE);
	}
	public void setEditable(boolean editable)
	{
		setValue(FIELD_EDITABLE, editable);
	}
	
	public Boolean isResizable()
	{
		return (Boolean) getValue(FIELD_RESIZEABLE);
	}
	public void setResizable(boolean resizable)
	{
		setValue(FIELD_RESIZEABLE, resizable);
	}

	public Class<?> getRendererClass()
	{
		return (Class<?>) getValue(FIELD_RENDERER_CLASS);
	}
	public void setRendererClass(Class<?> rendererClass)
	{
		setValue(FIELD_RENDERER_CLASS, rendererClass);
	}

	public Class<?> getEditorClass()
	{
		return (Class<?>) getValue(FIELD_EDITOR_CLASS);
	}
	public void setEditorClass(Class<?> editorClass)
	{
		setValue(FIELD_EDITOR_CLASS, editorClass);
	}
	
	public int getMaxWidth()
	{
		Integer v = (Integer) getValue(FIELD_MAX_WIDTH);
		return (v != null) ? v : Integer.MAX_VALUE;
	}
	public void setMaxWidth(int maxWidth)
	{
		setValue(FIELD_MAX_WIDTH, maxWidth);
	}
	
	public int getMinWidth()
	{
		Integer v = (Integer) getValue(FIELD_MIN_WIDTH);
		return (v != null) ? v : 1;
	}
	public void setMinWidth(int minWidth)
	{
		setValue(FIELD_MIN_WIDTH, minWidth);
	}
	
	public int getPreferredWidth()
	{
		Integer v = (Integer) getValue(FIELD_PREFERRED_WIDTH);
		return (v != null) ? v : getMinWidth();
	}
	public void setPreferredWidth(int preferredWidth)
	{
		setValue(FIELD_PREFERRED_WIDTH, preferredWidth);
	}
	
	public int getWidth()
	{
		Integer v = (Integer) getValue(FIELD_WIDTH);
		return (v != null) ? v : getMaxWidth();
	}
	public void setWidth(int width)
	{
		setValue(FIELD_WIDTH, width);
	}
}
