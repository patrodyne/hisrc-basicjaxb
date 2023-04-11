package org.jvnet.basicjaxb.plugin.fixmixedextensions;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.toUpperCase;
import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin.USAGE_FORMAT;

import org.jvnet.basicjaxb.reflection.util.Accessor;
import org.jvnet.basicjaxb.reflection.util.FieldAccessor;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.generator.bean.field.DummyListField;
import com.sun.tools.xjc.generator.bean.field.IsSetField;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * <p>This plugin fixes an issue when setting the <code>generateMixedExtensions="true"</code> in the
 * JAXB <code>globalBindings</code> configuration and the XML schema contains an abstract/concrete
 * pair of <code>complexType</code>s where both types declare mixed content, <code>mixed="true"</code>.</p>
 * 
 * <p>In this scenario, the {@link com.sun.tools.xjc.reader.xmlschema.ct.MixedExtendedComplexTypeBuilder}
 * creates a dummy extended mixed reference property with name prefixed by <code>contentOverrideFor</code>;
 * but, does not generate a <em>getter</em> for said property.</p>
 * 
 * <p>This plugin adds the missing method: <code>List&lt;Object&gt; getContentOverrideForNAME()</code>;
 * where <code>NAME</code> is the name of the containing complex type.</p>
 * 
 * @see <a href="https://github.com/highsource/jaxb2-basics/issues/14">JAXB-1058</a>
 */
public class FixMixedExtensions extends Plugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XfixMixedExtensions";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "Adds missing 'getContentOverrideForNAME()' method.";

	/** Represents a no-argument method signature */
	private static final JType[] NO_ARGS = new JType[0];

	@Override
	public String getOptionName()
	{
		return OPTION_NAME;
	}

	@Override
	public String getUsage()
	{
		return format(USAGE_FORMAT, OPTION_NAME, OPTION_DESC);
	}

	private Accessor<JMethod> DummyListField_$get;
	private Accessor<FieldOutline> IsSetField_core;
	private Accessor<JFieldVar> AbstractListField_field;
	private Accessor<JClass> AbstractListField_listT;
	private Accessor<JClass> DummyListField_coreList;

	@Override
	public boolean run(Outline outline, Options opt, ErrorHandler errorHandler)
		throws SAXException
	{
		try
		{
			DummyListField_$get = new FieldAccessor<JMethod>(DummyListField.class, "$get", JMethod.class);
			IsSetField_core = new FieldAccessor<FieldOutline>(IsSetField.class, "core", FieldOutline.class);
			AbstractListField_field = new FieldAccessor<JFieldVar>(DummyListField.class.getSuperclass(), "field",
				JFieldVar.class);
			AbstractListField_listT = new FieldAccessor<JClass>(DummyListField.class.getSuperclass(), "listT",
				JClass.class);
			DummyListField_coreList = new FieldAccessor<JClass>(DummyListField.class, "coreList", JClass.class);
		}
		catch (Exception ex)
		{
			throw new SAXException(
				"Could not create field accessors. " + "This plugin can not be used in this environment.", ex);
		}
		for (ClassOutline classOutline : outline.getClasses())
		{
			for (FieldOutline fieldOutline : classOutline.getDeclaredFields())
				fixFieldOutline(fieldOutline);
		}
		return false;
	}

	private void fixFieldOutline(FieldOutline fieldOutline)
	{
		fixPublicName(fieldOutline);
		if (fieldOutline instanceof DummyListField)
			fixDummyListField((DummyListField) fieldOutline);
		else if (fieldOutline instanceof IsSetField)
			fixIsSetField((IsSetField) fieldOutline);
	}
	
	private void fixDummyListField(DummyListField fieldOutline)
	{
		if (DummyListField_$get.get(fieldOutline) == null)
		{
			final JFieldVar field = AbstractListField_field.get(fieldOutline);
			final JType listT = AbstractListField_listT.get(fieldOutline);
			final JClass coreList = DummyListField_coreList.get(fieldOutline);
			// Add 'getContentOverrideForNAME()' method.
			final JMethod $get = fieldOutline.parent().implClass.method(JMod.PUBLIC, listT,
				"get" + fieldOutline.getPropertyInfo().getName(true));
			JBlock block = $get.body();
			block._if(field.eq(JExpr._null()))._then().assign(field, JExpr._new(coreList));
			block._return(JExpr._this().ref(field));
			DummyListField_$get.set(fieldOutline, $get);
		}
	}

	private void fixIsSetField(IsSetField isSetField)
	{
		fixIsSetMethod(isSetField);
		final FieldOutline core = IsSetField_core.get(isSetField);
		fixFieldOutline(core);
	}

	private void fixPublicName(FieldOutline fieldOutline)
	{
		CPropertyInfo fieldInfo = fieldOutline.getPropertyInfo();
		String pubName = fieldInfo.getName(true);
		if (isLowerCase(pubName.charAt(0)))
			fieldInfo.setName(true, toUpperCase(pubName.charAt(0)) + pubName.substring(1));
	}

	private void fixIsSetMethod(IsSetField isSetField)
	{
		CPropertyInfo fieldInfo = isSetField.getPropertyInfo();
		JMethod isSetMethod = isSetField.parent().implClass.getMethod("isSet" + fieldInfo.getName(false), NO_ARGS);
		if ( isSetMethod != null )
			isSetMethod.name("isSet" + fieldInfo.getName(true));
	}
}
