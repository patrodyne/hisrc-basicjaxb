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
	
	private static final String FIELD_PATTERN = "facet.pattern";
	private static final String FIELD_LENGTH = "facet.length";
	private static final String FIELD_MAX_LENGTH = "facet.maxLength";
	private static final String FIELD_MIN_LENGTH = "facet.minLength";
	private static final String FIELD_MAX_EXCLUSIVE = "facet.maxExclusive";
	private static final String FIELD_MIN_EXCLUSIVE = "facet.minExclusive";
	private static final String FIELD_MAX_INCLUSIVE = "facet.maxInclusive";
	private static final String FIELD_MIN_INCLUSIVE = "facet.minInclusive";
	private static final String FIELD_TOTAL_DIGITS = "facet.totalDigits";
	private static final String FIELD_FRACTION_DIGITS = "facet.fractionDigits";
	
	public static final int DEFAULT_MAX_WIDTH = 254;
	public static final int DEFAULT_MIN_WIDTH =  20;
	
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
		return (v != null) ? v
			: (getMaxLength() != null) ? getMaxLength()
			: (getTotalDigitsPlus() != null ) ? getTotalDigitsPlus()
			: DEFAULT_MAX_WIDTH;
	}
	public void setMaxWidth(int maxWidth)
	{
		setValue(FIELD_MAX_WIDTH, maxWidth);
	}
	
	public int getMinWidth()
	{
		Integer v = (Integer) getValue(FIELD_MIN_WIDTH);
		return (v != null) ? v
			: (getMinLength() != null) ? getMinLength()
			: DEFAULT_MIN_WIDTH;
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
	
	public String getPattern()
	{
		return (String) getValue(FIELD_PATTERN);
	}
	public void setPattern(String pattern)
	{
		setValue(FIELD_PATTERN, pattern);
	}
	
	public Integer getLength()
	{
		return (Integer) getValue(FIELD_LENGTH);
	}
	public void setLength(Integer length)
	{
		setValue(FIELD_LENGTH, length);
	}
	
	public Integer getMaxLength()
	{
		Integer v = (Integer) getValue(FIELD_MAX_LENGTH);
		return (v != null) ? v : getLength();
	}
	public void setMaxLength(Integer maxLength)
	{
		setValue(FIELD_MAX_LENGTH, maxLength);
	}
	
	public Integer getMinLength()
	{
		Integer v = (Integer) getValue(FIELD_MIN_LENGTH);
		return (v != null) ? v : getLength();
	}
	public void setMinLength(Integer minLength)
	{
		setValue(FIELD_MIN_LENGTH, minLength);
	}
	
	public Integer getMaxExclusive()
	{
		return (Integer) getValue(FIELD_MAX_EXCLUSIVE);
	}
	public void setMaxExclusive(Integer maxExclusive)
	{
		setValue(FIELD_MAX_EXCLUSIVE, maxExclusive);
	}
	
	public Integer getMinExclusive()
	{
		return (Integer) getValue(FIELD_MIN_EXCLUSIVE);
	}
	public void setMinExclusive(Integer minExclusive)
	{
		setValue(FIELD_MIN_EXCLUSIVE, minExclusive);
	}
	
	public Integer getMaxInclusive()
	{
		return (Integer) getValue(FIELD_MAX_INCLUSIVE);
	}
	public void setMaxInclusive(Integer maxInclusive)
	{
		setValue(FIELD_MAX_INCLUSIVE, maxInclusive);
	}
	
	public Integer getMinInclusive()
	{
		return (Integer) getValue(FIELD_MIN_INCLUSIVE);
	}
	public void setMinInclusive(Integer minInclusive)
	{
		setValue(FIELD_MIN_INCLUSIVE, minInclusive);
	}
	
	public Integer getTotalDigits()
	{
		return (Integer) getValue(FIELD_TOTAL_DIGITS);
	}
	public void setTotalDigits(Integer totalDigits)
	{
		setValue(FIELD_TOTAL_DIGITS, totalDigits);
	}
	
	public Integer getFractionDigits()
	{
		return (Integer) getValue(FIELD_FRACTION_DIGITS);
	}
	public void setFractionDigits(Integer fractionDigits)
	{
		setValue(FIELD_FRACTION_DIGITS, fractionDigits);
	}
	
	public Integer getTotalDigitsPlus()
	{
		Integer tdp = getTotalDigits();
		if ( tdp != null )
		{
			Integer wd = tdp;
			Integer fd = getFractionDigits();
			if ( fd != null )
				wd -= fd;
			// Plus commas
			tdp += (wd-1) / 3;
			// Plus sign and dot.
			tdp += 2;
		}
		return tdp;
	}
}
