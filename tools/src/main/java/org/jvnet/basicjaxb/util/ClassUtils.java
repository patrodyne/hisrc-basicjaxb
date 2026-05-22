package org.jvnet.basicjaxb.util;

import static java.lang.Integer.toHexString;
import static java.lang.System.identityHashCode;

import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CEnumLeafInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;

public class ClassUtils
{
	public static boolean contains(JDefinedClass theClass, String innerClassName)
	{
		for (final Iterator<JDefinedClass> classes = theClass.classes(); classes.hasNext();)
		{
			final JDefinedClass innerClass = classes.next();
			if (innerClassName.equals(innerClass.name()))
				return true;
		}
		return false;
	}

	public static void _implements(JDefinedClass theClass, JClass theInterface)
	{
		if (!isImplementing(theClass, theInterface))
			theClass._implements(theInterface);
	}

	public static boolean isImplementing(JDefinedClass theClass, JClass theInterface)
	{
		for (Iterator<JClass> iterator = theClass._implements(); iterator.hasNext();)
		{
			final JClass implementedInterface = iterator.next();
			if (theInterface.equals(implementedInterface))
				return true;
		}
		return false;
	}

	public static List<ClassOutline> getAncestors(ClassOutline classOutline)
	{
		final List<ClassOutline> classOutlines = new LinkedList<ClassOutline>();
		addAncestors(classOutline, classOutlines);
		return classOutlines;
	}

	public static List<ClassOutline> getAncestorsAndSelf(ClassOutline classOutline)
	{
		final List<ClassOutline> classOutlines = new LinkedList<ClassOutline>();
		classOutlines.add(classOutline);
		addAncestors(classOutline, classOutlines);
		return classOutlines;
	}

	private static void addAncestors(ClassOutline classOutline, List<ClassOutline> classOutlines)
	{
		if (classOutline.getSuperClass() != null)
		{
			final ClassOutline superClassOutline = classOutline.getSuperClass();
			addAncestors(superClassOutline, classOutlines);
		}
	}

	public static FieldOutline[] getFields(ClassOutline classOutline)
	{
		final List<FieldOutline> fields = new ArrayList<FieldOutline>();
		fields.addAll(Arrays.asList(classOutline.getDeclaredFields()));
		if (classOutline.getSuperClass() != null)
			fields.addAll(Arrays.asList(getFields(classOutline.getSuperClass())));
		return fields.toArray(new FieldOutline[fields.size()]);
	}

	public static String getPackagedClassName(final CEnumLeafInfo enumLeafInfo)
	{
		if (enumLeafInfo.parent instanceof CClassInfo)
			return getPackagedClassName((CClassInfo) enumLeafInfo.parent) + '$' + enumLeafInfo.shortName;
		else
		{
			final String r = enumLeafInfo.parent.fullName();
			if (r.length() == 0)
				return enumLeafInfo.shortName;
			else
				return r + '.' + enumLeafInfo.shortName;
		}
	}

	public static String getPackagedClassName(final CClassInfo classInfo)
	{
		if (classInfo.parent() instanceof CClassInfo)
			return getPackagedClassName((CClassInfo) classInfo.parent()) + '$' + classInfo.shortName;
		else
		{
			final String r = classInfo.parent().fullName();
			if (r.length() == 0)
				return classInfo.shortName;
			else
				return r + '.' + classInfo.shortName;
		}
	}

    public static String identify(Object object)
    {
    	String identify = null;
    	if ( object != null )
    	{
            String objectId = toHexString(identityHashCode(object));
            return object.getClass().getName() + "@" + objectId;
    	}
    	return identify;
    }

    /**
     * Check if the class represents an annotation interface.
     *
     * @param clazz The {@code Class} to check.
     *
     * @return True when the {@code Class} object represents an
     *         annotation,
     */
    public static boolean isAnnotation(Class<?> clazz)
    {
        return clazz.isAnnotation();
    }

    /**
     * Check if the class represents an annotation interface then
     * check if the annotation class is marked with {@code @Repeatable}.
     *
     * @param clazz The {@code Class} to check.
     *
     * @return True when the {@code Class} object representing an
     *         annotation is repeatable,
     */
    public static boolean isRepeatableAnnotation(Class<?> clazz)
    {
    	if ( clazz != null && clazz.isAnnotation() )
    		return clazz.isAnnotationPresent(Repeatable.class);
    	else
    		return false;
    }

    /**
     * Check if the annotation class is marked with {@code @Repeatable}.
     *
     * @param clazz The {@code Class} to check.
     *
     * @return True when the {@code Class} object representing an
     *         annotation is repeatable,
     */
    public static boolean isRepeatable(Class<? extends java.lang.annotation.Annotation> clazz)
    {
        return clazz.isAnnotationPresent(Repeatable.class);
    }


    /**
     * Check if the {@code JClass} represents an annotation interface then
     * check if the annotation class is marked with {@code @Repeatable}.
     *
     * @param jClass The {@code JClass} to check.
     *
     * @return True when the {@code JClass} object representing an
     *         annotation is repeatable,
     */
	public static boolean isRepeatableAnnotation(JClass jClass)
	{
		try
		{
			// Get the fully qualified class name
			String className = jClass.fullName();
			// Load the actual class using reflection
			Class<?> clazz = Class.forName(className);
			// Check if the @Repeatable meta-annotation is present
			return isRepeatableAnnotation(clazz);
		}
		catch (ClassNotFoundException e)
		{
			// Handle case where the class is not on the current classpath
			return false;
		}
	}

	/**
	 * Check CodeModel's internal metadata for the @Repeatable annotation;
	 * or, use reflection for an existing class.
	 *
	 * @param jClass he {@code JClass} to check.
	 *
     * @return True when the {@code JClass} object representing an
     *         annotation is repeatable,
	 */
	public static boolean isClassRepeatable(JClass jClass)
	{
		if ( jClass instanceof JDefinedClass )
		{
			JDefinedClass definedClass = (JDefinedClass) jClass;
			// Loop through the annotations added to this defined class
			for ( JAnnotationUse annotation : definedClass.annotations() )
			{
				// Check if the annotation type matches
				// java.lang.annotation.Repeatable
				if ( annotation.getAnnotationClass().fullName().equals(Repeatable.class.getName()) )
					return true;
			}
		}
		else
			return isRepeatableAnnotation(jClass);
		return false;
	}
}
