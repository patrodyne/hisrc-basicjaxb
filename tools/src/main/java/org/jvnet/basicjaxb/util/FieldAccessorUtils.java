package org.jvnet.basicjaxb.util;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JType;
import com.sun.tools.xjc.outline.FieldOutline;

/**
 * Utilities to access a {@link JMethod} instance or a {@link JFieldVar} instance
 * from a given {@link FieldOutline} instance.
 */
public class FieldAccessorUtils
{
	// Represents an empty JType array.
	private static final JType[] NONE = new JType[0];

	/**
	 * Returns the <code>getProperty(...)</code> method for the given field
	 * outline or <code>null</code> if no such method exists.
	 * 
	 * @param fieldOutline the field outline.
	 * 
	 * @return The <code>getProperty(...)</code> method for the given field
	 *         outline or <code>null</code> if no such method exists.
	 */
	public static JMethod getter(FieldOutline fieldOutline)
	{
		final JDefinedClass theClass = fieldOutline.parent().implClass;
		final String publicName = fieldOutline.getPropertyInfo().getName(true);
		final JMethod getgetter = theClass.getMethod("get" + publicName, NONE);
		if ( getgetter != null )
			return getgetter;
		else
		{
			final JMethod isgetter = theClass.getMethod("is" + publicName, NONE);
			if ( isgetter != null )
				return isgetter;
			else
				return null;
		}
	}

	/**
	 * Returns the <code>setProperty(...)</code> method for the given field
	 * outline or <code>null</code> if no such method exists.
	 * 
	 * @param fieldOutline field outline.
	 * 
	 * @return The <code>setProperty(...)</code> method for the given field
	 *         outline or <code>null</code> if no such method exists.
	 */
	public static JMethod setter(FieldOutline fieldOutline)
	{
		final JMethod getter = getter(fieldOutline);
		final JType type = getter != null ? getter.type() : fieldOutline.getRawType();
		final JDefinedClass theClass = fieldOutline.parent().implClass;
		final String publicName = fieldOutline.getPropertyInfo().getName(true);
		final String name = "set" + publicName;
		return theClass.getMethod(name, new JType[] { type });
	}

	/**
	 * Returns the <code>isSetProperty()</code> method for the given field
	 * outline or <code>null</code> if no such method exists.
	 * 
	 * @param fieldOutline field outline.
	 * @return The <code>isSetProperty()</code> method for the given field
	 *         outline or <code>null</code> if no such method exists.
	 */
	public static JMethod issetter(FieldOutline fieldOutline)
	{
		final JDefinedClass theClass = fieldOutline.parent().implClass;
		final String publicName = fieldOutline.getPropertyInfo().getName(true);
		final String name = "isSet" + publicName;
		return theClass.getMethod(name, NONE);
	}

	/**
	 * Returns the field for the given field outline or <code>null</code> if no
	 * such field exists.
	 * 
	 * @param fieldOutline field outline.
	 * @return The field for the given field outline or <code>null</code> if no
	 *         such field exists.
	 */
	public static JFieldVar field(FieldOutline fieldOutline)
	{
		final JDefinedClass theClass = fieldOutline.parent().implClass;
		return theClass.fields().get(fieldOutline.getPropertyInfo().getName(false));
	}
}
