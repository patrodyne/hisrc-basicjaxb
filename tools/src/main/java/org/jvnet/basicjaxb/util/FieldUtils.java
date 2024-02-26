package org.jvnet.basicjaxb.util;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.xml.namespace.QName;

import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
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
	
//	See https://download.oracle.com/javaee-archive/jaxb.java.net/users/2005/04/4054.html
//	See https://download.oracle.com/javaee-archive/jaxb.java.net/users/2005/04/4057.html
//	
//	Kohsuke Kawaguchi <kohsuke.kawaguchi_at_sun.com>:
//	The grammar model in 1.0 was much more expressive than that in 2.0. 1.0 code generators
//	were generally capable of taking a more expressive model, but in 2.0 it can no longer
//	do so. Hence the change in the model. 

//	public static FieldItem[] getFieldItems(ClassContext classContext)
//	{
//		final List fieldItems = (List) classContext.target.visit(new FieldGatheringVisitor(classContext));
//		return (FieldItem[]) fieldItems.toArray(new FieldItem[fieldItems.size()]);
//	}

//	public static String getFieldName(ClassContext classContext, FieldItem fieldItem)
//	{
//		return ClassUtils.getClassName(classContext.target) + "." + fieldItem.name;
//	}

//	public static String getFieldPropertyName(FieldItem fieldItem)
//	{
//		return fieldItem.name;
//	}

//	public static String getCommonFieldPropertyName(FieldItem fieldItem)
//	{
//		final String draftName = fieldItem.name;
//		return Introspector.decapitalize(draftName);
//	}

//	public static FieldItem getFieldItem(FieldUse fieldUse)
//	{
//		if ( fieldUse.items.isEmpty() )
//			return null;
//		else
//			return (FieldItem) fieldUse.items.iterator().next();
//	}

//	public static FieldUse getFieldUse(ClassContext classContext, FieldItem fieldItem)
//	{
//		return classContext.target.getField(fieldItem.name);
//	}

//	/**
//	 * Returns a new unique name of the field in the given class, based on the
//	 * given prefix. If required, the prefix will be appended with an integer to
//	 * make it unique
//	 *
//	 * @param theClass class to create field in.
//	 * @param prefix field name prefix.
//	 * @return Unique name of the new field.
//	 */
//	public static String generateUniqueFieldName(final JDefinedClass theClass, final String prefix)
//	{
//		final String name;
//		if ( null == getField(theClass, prefix) )
//		{
//			name = prefix;
//		}
//		else
//		{
//			int index = 0;
//			while (null != getField(theClass, prefix + index))
//			{
//				index++;
//			}
//			name = prefix + index;
//		}
//		return name;
//	}

//	/**
//	 * Retrieves a named field of the given class.
//	 *
//	 * @param theClass class to search a field in.
//	 * @param name name of the field.
//	 * @return Requested field of the given class.
//	 */
//	public static JVar getField(final JDefinedClass theClass, final String name)
//	{
//		JFieldVar result = null;
//		for ( Iterator iterator = theClass.fields(); iterator.hasNext(); )
//		{
//			final JFieldVar var = (JFieldVar) iterator.next();
//			if ( name.equals(var.name()) )
//				result = var;
//		}
//		// todo : if not found???
//		return result;
//	}
	
//	public static boolean isConstantField(ClassContext classContext, FieldItem fieldItem)
//	{
//		return AccessorUtils.get(classContext, fieldItem) == null;
//	}

//	public static FieldUse[] getFieldUses(final ClassItem classItem)
//	{
//		if ( classItem.getSuperClass() == null )
//			return classItem.getDeclaredFieldUses();
//		else
//		{
//			final FieldUse[] superFieldUses = FieldUtils.getFieldUses(classItem.getSuperClass());
//			final FieldUse[] declaredFieldUses = classItem.getDeclaredFieldUses();
//			final FieldUse[] fieldUses = new FieldUse[superFieldUses.length + declaredFieldUses.length];
//			System.arraycopy(superFieldUses, 0, fieldUses, 0, superFieldUses.length);
//			System.arraycopy(declaredFieldUses, 0, fieldUses, superFieldUses.length, declaredFieldUses.length);
//			return fieldUses;
//		}
//	}

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
}
