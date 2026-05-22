package org.jvnet.basicjaxb.util;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;
import org.jvnet.basicjaxb.lang.XmlIdReflector;
import org.jvnet.basicjaxb.xjc.outline.FieldAccessorEx;

import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.generator.bean.ClassOutlineImpl;
import com.sun.tools.xjc.generator.bean.field.FieldRendererFactory;
import com.sun.tools.xjc.model.CAttributePropertyInfo;
import com.sun.tools.xjc.model.CCustomizations;
import com.sun.tools.xjc.model.CElementInfo;
import com.sun.tools.xjc.model.CNonElement;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.CTypeInfo;
import com.sun.tools.xjc.outline.Aspect;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

import jakarta.xml.bind.annotation.XmlIDREF;

/**
 * Field utilities to create accessors and fields for the given implementation class,
 * get a {@link Set} of possible {@link JType} for the given XJC {@link Outline},
 * {@link Aspect} and {@link CTypeInfo} and determine the {@link FieldOutline} type.
 *
 * @author valikov
 */
public class FieldUtils
{
	/** Hidden constructor for static use only . */
	private FieldUtils() { }

	/**
	 * Generates accessors and fields for the given implementation
     * class, then return {@link FieldOutline} for accessing the generated field.
	 *
	 * @param classOutline The {@link ClassOutline} enhanced with schema2java specific information.
	 * @param name The public name.
	 * @param attName The attribute {@link PropertyInfo} name.
	 * @param typeInfo The Information about how another type is used (needs explicit type name)
	 * @param required The required attribute flag.
	 *
	 * @return The {@link FieldOutline} for accessing the generated field.
	 */
	public static FieldOutline createAttributeField(ClassOutlineImpl classOutline, String name,
		final QName attName, final CNonElement typeInfo, final boolean required)
	{
		final CPropertyInfo propertyInfo = new CAttributePropertyInfo
		(
			name, // name
			null, // source
			new CCustomizations(), // customizations
			null, // locator
			attName, // attName
			typeInfo, // typeUse
			typeInfo.getTypeName(), // typeName
			required // required
		);
		propertyInfo.realization = new FieldRendererFactory().getDefault();
		final FieldOutline fieldOutline = propertyInfo.realization.generate(classOutline, propertyInfo);
		return fieldOutline;
	}

	/**
	 * Get a {@link Set} of possible {@link JType} for the given XJC
	 * {@link FieldOutline} and {@link Aspect}.
	 *
	 * @param fieldOutline Representation of a field of {@link ClassOutline}.
	 * @param aspect The bean aspect (i.e. EXPOSED or IMPLEMENTATION).
	 *
	 * @return The set of possible types (custom or base), including substitution members.
	 */
	public static Set<JType> getPossibleTypes(FieldOutline fieldOutline, Aspect aspect)
	{
		requireNonNull(fieldOutline);
		final ClassOutline classOutline = fieldOutline.parent();
		final Outline outline = classOutline.parent();
		final CPropertyInfo propertyInfo = fieldOutline.getPropertyInfo();
		final Set<JType> types = new HashSet<JType>();

		if ( propertyInfo.getAdapter() != null )
			types.add(propertyInfo.getAdapter().customType.toType(fieldOutline.parent().parent(), aspect));
		else if ( propertyInfo.baseType != null )
			types.add(propertyInfo.baseType);
		else
		{
			Collection<? extends CTypeInfo> typeInfos = propertyInfo.ref();
			for ( CTypeInfo typeInfo : typeInfos )
				types.addAll(getPossibleTypes(outline, aspect, typeInfo));
		}
		return types;
	}

	/**
	 * Get a {@link Set} of possible {@link JType} for the given XJC
	 * {@link Outline}, {@link Aspect} and {@link CTypeInfo}.
	 *
	 * @param outline The XJC outline.
	 * @param aspect The bean aspect (i.e. EXPOSED or IMPLEMENTATION).
	 * @param typeInfo Then compile time type info.
	 *
	 * @return The set of possible types, including substitution members.
	 */
	public static Set<JType> getPossibleTypes(Outline outline, Aspect aspect, CTypeInfo typeInfo)
	{
		final Set<JType> types = new HashSet<JType>();
		types.add(typeInfo.getType().toType(outline, aspect));
		if ( typeInfo instanceof CElementInfo )
		{
			final CElementInfo elementInfo = (CElementInfo) typeInfo;
			for ( CElementInfo substitutionMember : elementInfo.getSubstitutionMembers() )
				types.addAll(getPossibleTypes(outline, aspect, substitutionMember));
		}
		return types;
	}

	public static boolean isConstantField(FieldOutline fieldOutline)
	{
		boolean isConstant = false;
		if ( fieldOutline.parent() != null )
		{
			JDefinedClass theClass = fieldOutline.parent().implClass;
			String publicName = fieldOutline.getPropertyInfo().getName(true);
			final JFieldVar fieldVar = theClass.fields().get(publicName);
			isConstant = isConstantField(fieldVar);
		}
		return isConstant;
	}

	public static boolean isConstantField(JFieldVar fieldVar)
	{
		boolean isConstant = false;
		if
		(
			(fieldVar != null) &&
			((fieldVar.mods().getValue() & JMod.PUBLIC) != 0) &&
			((fieldVar.mods().getValue() & JMod.STATIC) != 0) &&
			((fieldVar.mods().getValue() & JMod.FINAL) != 0)
		)
		{
			isConstant = true;
		}
		return isConstant;
	}

	public static boolean isPropertyField(FieldOutline fieldOutline)
	{
		boolean isProperty = false;
		if ( fieldOutline.parent() != null )
		{
			JDefinedClass theClass = fieldOutline.parent().implClass;
			String privateName = fieldOutline.getPropertyInfo().getName(false);
			final JFieldVar fieldVar = theClass.fields().get(privateName);
			isProperty = isPropertyField(fieldVar);
		}
		return isProperty;
	}

	public static boolean isPropertyField(JFieldVar fieldVar)
	{
		boolean isProperty = false;
		if
		(
			(fieldVar != null) &&
			((fieldVar.mods().getValue() & JMod.PROTECTED) != 0) &&
			((fieldVar.mods().getValue() & JMod.STATIC) == 0) &&
			((fieldVar.mods().getValue() & JMod.FINAL) == 0)
		)
		{
			isProperty = true;
		}
		return isProperty;
	}

	public static boolean isXmlIDREF(JVar fieldVar)
	{
		for ( JAnnotationUse annotationUse : fieldVar.annotations() )
		{
			JClass auClass = annotationUse.getAnnotationClass();
			if ( XmlIDREF.class.getName().equals(auClass.fullName()) )
				return true;
		}
		return false;
	}

	public static String getXmlIdGetterName(final JCodeModel codeModel, final String className)
	{
		String xmlIdGetterName = null;
		JDefinedClass valueClass = CodeModelUtils._getClass(codeModel, className);
		if ( valueClass != null )
		{
			JMethod xmlIdGetter = JClassUtils.getXmlIdGetter(valueClass);
			if ( xmlIdGetter != null )
				xmlIdGetterName = xmlIdGetter.name();
		}
		else
		{
			Class<?> valueClazz = CodeModelUtils.getClassForName(className);
			if ( valueClazz != null )
			{
				Method xmlIdGetter = XmlIdReflector.getXmlIdGetter(valueClazz);
				if ( xmlIdGetter != null )
					xmlIdGetterName = xmlIdGetter.getName();
			}
		}
		return xmlIdGetterName;
	}

	public static String getXmlIdGetterName(final JCodeModel codeModel, final JVar theValue)
	{
		return getXmlIdGetterName(codeModel, theValue.type().fullName());
	}

	public static ValueArguments getValueArguments(JCodeModel codeModel, ClassOutline classOutline,
		CPropertyInfo fieldInfo, FieldAccessorEx fieldAccessor, JVar value)
	{
		String xmlIdGetterName = null;
		boolean valueIsCollection = fieldInfo.isCollection();
		JType valueCollectionType = null;
		if ( fieldAccessor.isXmlIDREF() )
		{
			if ( fieldInfo.isCollection() )
			{
				// A java.util.Collection has exactly one generic parameter.
				if ( fieldInfo.ref().size() == 1 )
				{
					CTypeInfo fiRef = fieldInfo.ref().iterator().next();
					xmlIdGetterName = FieldUtils.getXmlIdGetterName(codeModel, fiRef.getType().fullName());
					valueCollectionType = fiRef.getType().toType(classOutline.parent(), Aspect.EXPOSED);
				}
			}
			else
				xmlIdGetterName = FieldUtils.getXmlIdGetterName(codeModel, value);
		}
		return new ValueArguments(xmlIdGetterName, valueIsCollection, valueCollectionType);
	}

	public static ValueArguments getValueArguments(JCodeModel codeModel, JVar theValue)
	{
		String xmlIdGetterName = null;
		if ( FieldUtils.isXmlIDREF(theValue) )
			xmlIdGetterName = FieldUtils.getXmlIdGetterName(codeModel, theValue);
		boolean valueIsCollection = false;
		JType valueCollectionType = null;
		if ( theValue.type() instanceof JClass theValueType )
		{
		    java.util.List<JClass> typeParams = theValueType.getTypeParameters();
		    valueIsCollection = codeModel.ref(Collection.class).isAssignableFrom(theValueType) ||
		    	codeModel.ref(Map.class).isAssignableFrom(theValueType);
		    if ( valueIsCollection && !typeParams.isEmpty() )
			    valueCollectionType = typeParams.get(0);
		}
		return new ValueArguments(xmlIdGetterName, valueIsCollection, valueCollectionType);
	}

	public static class ValueArguments
	{
		public String idGetterName = null;
		public Boolean valueIsCollection = null;
		public JType valueCollectionType = null;

		public ValueArguments(String xmlIdGetterName, boolean valueIsCollection, JType valueCollectionType)
		{
			this.idGetterName = xmlIdGetterName;
			this.valueIsCollection = valueIsCollection;
			this.valueCollectionType = valueCollectionType;
		}
	}
}
