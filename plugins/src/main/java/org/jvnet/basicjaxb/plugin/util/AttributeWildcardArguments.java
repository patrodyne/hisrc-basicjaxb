package org.jvnet.basicjaxb.plugin.util;

import static org.jvnet.basicjaxb.plugin.util.AttributeWildcardArguments.FIELD_NAME;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.jvnet.basicjaxb.locator.util.LocatorUtils;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * Construct arguments for an attribute wildcard from an {@link ClassOutline}.
 */
public class AttributeWildcardArguments
{
	/** Represents the XJC generated name for a wildcard attribute map. */
	public static final String FIELD_NAME = "otherAttributes";
	
	/** Represents an empty JType array. **/
	public static final JType[] NOARGS = new JType[0];
	
	/** Represents that the attribute wildcard has set value to a {@link Map}{@literal <QName, String>} */
	public static final JExpression HAS_SET_VALUE = JExpr.TRUE;
	
	/** Represents that the attribute wildcard is always set to a {@link Map}{@literal <QName, String>} */
	public static final boolean IS_ALWAYS_SET = true;

	/** Represents that the attribute wildcard has default value for a {@link Map}{@literal <QName, String>} */
	public static final boolean HAS_DEFAULT_VALUE = false;
	
	private ClassOutline classOutline = null;
	public ClassOutline getClassOutline() { return classOutline; }
	public void setClassOutline(ClassOutline classOutline) { this.classOutline = classOutline; }

	private Outline outline = null;
	public Outline getOutline()
	{
		if ( outline == null )
			setOutline(getClassOutline().parent());
		return outline;
	}
	public void setOutline(Outline outline)
	{
		this.outline = outline;
	}
	
	private Model model = null;
	public Model getModel()
	{
		if ( model == null )
			setModel(getOutline().getModel());
		return model;
	}
	public void setModel(Model model)
	{
		this.model = model;
	}
	
	private String baseName = null;
	public String getBaseName()
	{
		if ( baseName == null )
			setBaseName(getModel().getNameConverter().toClassName(FIELD_NAME));
		return baseName;
	}
	public void setBaseName(String baseName)
	{
		this.baseName = baseName;
	}

	public String prefixBaseName(String prefix)
	{
		return prefix + getBaseName();
	}
	
	private JDefinedClass implClass = null;
	public JDefinedClass getImplClass()
	{
		if ( implClass == null )
			setImplClass(getClassOutline().getImplClass());
		return implClass;
	}
	public void setImplClass(JDefinedClass implClass)
	{
		this.implClass = implClass;
	}
	
	private JFieldVar field = null;
	public JFieldVar getField()
	{
		if ( field == null )
			setField(getImplClass().fields().get(FIELD_NAME));
		return field;
	}
	public void setField(JFieldVar field)
	{
		this.field = field;
	}
	
	private JMethod fieldAccessor = null;
	public JMethod getFieldAccessor()
	{
		if ( fieldAccessor == null )
			setFieldAccessor(getImplClass().getMethod(prefixBaseName("get"), NOARGS));
		return fieldAccessor;
	}
	public void setFieldAccessor(JMethod fieldAccessor)
	{
		this.fieldAccessor = fieldAccessor;
	}
	
	private JType exposedType = null;
	public JType getExposedType()
	{
		if ( exposedType == null )
			setExposedType(getField().type());
		return exposedType;
	}
	public void setExposedType(JType exposedType)
	{
		this.exposedType = exposedType;
	}
	
	private Collection<JType> possibleTypes = null;
	public Collection<JType> getPossibleTypes()
	{
		if ( possibleTypes == null )
			setPossibleTypes(Set.of(getExposedType()));
		return possibleTypes;
	}
	public void setPossibleTypes(Collection<JType> possibleTypes)
	{
		this.possibleTypes = possibleTypes;
	}
	
	public JInvocation invokeGetter(JExpression target)
	{
		return target.invoke(getFieldAccessor());
	}
	
	public JVar fieldVar(JBlock block, JExpression target, String prefix)
	{
		return block.decl(getExposedType(), prefixBaseName(prefix), invokeGetter(target));
	}
	
	public JVar fieldVar(JBlock block, JExpression target, String prefix, String baseName)
	{
		return block.decl(getExposedType(), prefix + baseName, invokeGetter(target));
	}
	
	public JExpression fieldLocatorValue(JVar locator, JVar value)
	{
		return getModel().codeModel.ref(LocatorUtils.class)
			.staticInvoke("property")
				.arg(locator)
				.arg(FIELD_NAME)
				.arg(value);
	}
	
	public JVar fieldLocator(JBlock block, JVar locator, JExpression fieldLocatorValue, String prefix)
	{
		return block.decl(locator.type(), prefix + "FieldLocator", fieldLocatorValue);
	}
	
	/**
	 * Construct arguments for a wildcard from an {@link ClassOutline}.
	 * 
	 * @param classOutline provides {@link CClassInfo} for filling in methods/fields of a bean.
	 */
	public AttributeWildcardArguments(ClassOutline classOutline)
	{
		setClassOutline(classOutline);
	}
}
