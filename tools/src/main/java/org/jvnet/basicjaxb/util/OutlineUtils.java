package org.jvnet.basicjaxb.util;

import java.util.Iterator;

import org.glassfish.jaxb.core.api.impl.NameConverter;

import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.outline.PackageOutline;

/**
 * Utility methods to get class, field or property info from
 * a given {@link ClassOutline}
 */
public class OutlineUtils
{
	/* Seal this utility class */
	private OutlineUtils()
	{
	}

	public static Class<?> getPackagedClass(ClassOutline classOutline)
		throws ClassNotFoundException
	{
		return Class.forName(getPackagedClassName(classOutline));
	}

	public static String getPackagedClassName(ClassOutline classOutline)
	{
		return CodeModelUtils.getPackagedClassName(classOutline.implClass);
	}

	public static String getClassName(ClassOutline classOutline)
	{
		return CodeModelUtils.getClassName(classOutline.implClass);
	}

	public static String getLocalClassName(ClassOutline classOutline)
	{
		return CodeModelUtils.getLocalClassName(classOutline.implClass);
	}

	public static String getFieldName(FieldOutline fieldOutline)
	{
		return getPackagedClassName(fieldOutline.parent()) + "." + fieldOutline.getPropertyInfo().getName(true);
	}

	public static String getPropertyName(FieldOutline fieldOutline)
	{
		return NameConverter.standard.toVariableName(fieldOutline.getPropertyInfo().getName(true));
	}

	public static String getContextPath(Outline context)
	{
		final StringBuilder sb = new StringBuilder();
		for ( final Iterator<? extends PackageOutline> packageOutlines = context.getAllPackageContexts()
			.iterator(); packageOutlines.hasNext(); )
		{
			final PackageOutline packageOutline = packageOutlines.next();
			final String packageName = packageOutline._package().name();
			sb.append(packageName);
			if ( packageOutlines.hasNext() )
				sb.append(':');
		}
		return sb.toString();
	}
}
