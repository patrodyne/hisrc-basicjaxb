package org.jvnet.basicjaxb.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.glassfish.jaxb.core.api.impl.NameConverter;

import com.sun.codemodel.JAnnotationArrayMember;
import com.sun.codemodel.JAnnotationStringValue;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JAnnotationValue;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JFieldVar;
import com.sun.tools.xjc.generator.bean.ClassOutlineImpl;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.outline.PackageOutline;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

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

	/**
	 * Process XmlTransient fields to be JAXB exclusive and to be omitted
	 * from the XmlType property order.
	 *
	 * @param classOutline Provides per-{@link CClassInfo} information for a bean.
	 */
	public static void processXmlTransient(ClassOutline classOutline)
	{
		if ( classOutline instanceof ClassOutlineImpl )
		{
			ClassOutlineImpl coi = (ClassOutlineImpl) classOutline;
			// Create a map of the XmlTransient field(s) to be removed from the propOrder.
			Set<String> xmlTransientFieldSet = new HashSet<>();
			for ( Entry<String, JFieldVar> fieldEntry : coi.implClass.fields().entrySet() )
			{
				boolean isXmlTransientField = false;
				Set<JAnnotationUse> excludeAnnotations = new HashSet<>();
				JFieldVar fieldValue = fieldEntry.getValue();
				for ( JAnnotationUse fieldAnnotation : fieldValue.annotations() )
				{
					JClass fieldAnnotationClass = fieldAnnotation.getAnnotationClass();
					String fieldAnnotationClassFullName = fieldAnnotationClass.fullName();
					if ( XmlTransient.class.getName().equals(fieldAnnotationClassFullName) )
					{
						isXmlTransientField = true;
						xmlTransientFieldSet.add(fieldEntry.getKey());
					}
					else
					{
						// The field annotation is not XmlTransient. If it is a
						// JAXB annotation then add it to the list of annotations
						// that might be excluded.
						//
						// This set will be excluded ONLY when isXmlTransientField
						// is true; otherwise, this set will be discarded.
						if ( fieldAnnotationClassFullName.startsWith(XmlElement.class.getPackageName()))
							excludeAnnotations.add(fieldAnnotation);
					}
				}

				// @XmlTransient is mutually exclusive with all other
				// Jakarta XML Binding defined annotations.
				if ( isXmlTransientField )
				{
					for ( JAnnotationUse excludeAnnotation : excludeAnnotations )
						fieldValue.removeAnnotation(excludeAnnotation);
				}
			}

			// Prepare to remove XmlTransient field(s) from the XmlType's property order.
			boolean replaceXmlTypeAnnUse = false;
			List<String> auXmlTypePropOrderListNew = new ArrayList<>();
			JAnnotationUse auXmlTypeOld = null;
			for ( JAnnotationUse au : coi.implClass.annotations() )
			{
				// Is this annotation for XmlType?
				if ( XmlType.class.getName().equals(au.getAnnotationClass().fullName()) )
				{
					auXmlTypeOld = au;
					// Is there a property order member as an array.
					JAnnotationValue auPropOrderValueOld = auXmlTypeOld.getAnnotationMembers().get("propOrder");
					if ( auPropOrderValueOld instanceof JAnnotationArrayMember )
					{
						JAnnotationArrayMember auPropOrderMemberOld = (JAnnotationArrayMember) auPropOrderValueOld;
						for ( JAnnotationValue auPropOrderMemberValueOld : auPropOrderMemberOld.annotations2() )
						{
							if ( auPropOrderMemberValueOld instanceof JAnnotationStringValue )
							{
								String auPropOrderMemberStringOld = auPropOrderMemberValueOld.toString();
								// Exclude transient fields from the property order.
								if ( xmlTransientFieldSet.contains(auPropOrderMemberStringOld) )
									replaceXmlTypeAnnUse = true;
								else
									auXmlTypePropOrderListNew.add(auPropOrderMemberStringOld);
							}
						}
					}
				}
			}

			// Is there a XMlType annotation that must be replaced with
			// an XmlTransient exclusive property order?
			if ( replaceXmlTypeAnnUse )
			{
				coi.implClass.removeAnnotation(auXmlTypeOld);

				JAnnotationUse auXmlTypeNew =
					coi.implClass.annotate((auXmlTypeOld.getAnnotationClass()));

				JAnnotationValue namespace = auXmlTypeOld.getAnnotationMembers().get("namespace");
				if ( namespace != null )
					auXmlTypeNew.param("namespace", namespace);

				JAnnotationValue name = auXmlTypeOld.getAnnotationMembers().get("name");
				if ( name != null )
					auXmlTypeNew.param("name", name);

				JAnnotationArrayMember auPropOrderMemberNew = auXmlTypeNew.paramArray("propOrder");
				for ( String propOrderItem : auXmlTypePropOrderListNew )
					auPropOrderMemberNew.param(propOrderItem);

				JAnnotationValue factoryClass = auXmlTypeOld.getAnnotationMembers().get("factoryClass");
				if ( factoryClass != null )
					auXmlTypeNew.param("factoryClass", factoryClass);

				JAnnotationValue factoryMethod = auXmlTypeOld.getAnnotationMembers().get("factoryMethod");
				if ( factoryMethod != null )
					auXmlTypeNew.param("factoryMethod", factoryMethod);
			}
		}
	}
}
