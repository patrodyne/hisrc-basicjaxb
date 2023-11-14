package org.jvnet.basicjaxb.xjc.outline;

import com.sun.codemodel.JType;
import com.sun.tools.xjc.outline.FieldAccessor;

/**
 * Interface to extend {@link FieldAccessor} encapsulation with additional
 * <em>type</em> methods.
 */
public interface FieldAccessorEx extends FieldAccessor
{
	/**
	 * Access the {@link JType} for this field accessor.
	 * 
	 * @return The {@link JType} for this field accessor.
	 */
	public JType getType();

	/**
	 * Is this a constant field type.
	 * 
	 * @return True when the field is a constant type; otherwise, false.
	 */
	public boolean isConstant();

	/**
	 * Is this a virtual field type.
	 * 
	 * @return True when the field is a virtual type; otherwise, false.
	 */
	public boolean isVirtual();

	/**
	 * Is this field value always set.
	 * 
	 * @return True when the field value is always set; otherwise, false.
	 */
	public boolean isAlwaysSet();
}
