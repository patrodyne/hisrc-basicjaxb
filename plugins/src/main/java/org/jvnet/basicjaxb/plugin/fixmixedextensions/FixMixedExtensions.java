package org.jvnet.basicjaxb.plugin.fixmixedextensions;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.toUpperCase;
import static java.lang.String.format;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.reflection.util.Accessor;
import org.jvnet.basicjaxb.reflection.util.FieldAccessor;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.tools.xjc.generator.bean.field.DummyListField;
import com.sun.tools.xjc.generator.bean.field.IsSetField;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * <p>
 * This plugin fixes an issue when setting the <code>generateMixedExtensions="true"</code> in the
 * JAXB <code>globalBindings</code> configuration and the XML schema contains an abstract/concrete
 * pair of <code>complexType</code>s where both types declare mixed content, <code>mixed="true"</code>.
 * </p>
 * 
 * <p>
 * In this scenario, the {@link com.sun.tools.xjc.reader.xmlschema.ct.MixedExtendedComplexTypeBuilder}
 * creates a dummy extended mixed reference property with name prefixed by <code>contentOverrideFor</code>;
 * but, does not generate a <em>getter</em> for said property.
 * </p>
 * 
 * <p>
 * This plugin adds the missing method: <code>List&lt;Object&gt; getContentOverrideForNAME()</code>;
 * where <code>NAME</code> is the name of the containing complex type.
 * </p>
 * 
 * @see <a href="https://github.com/highsource/jaxb2-basics/issues/14">JAXB-1058</a>
 */
public class FixMixedExtensions extends AbstractPlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XfixMixedExtensions";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "adds missing 'getContentOverrideForNAME()' method";

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

	// Plugin Processing
	
	@Override
	protected void beforeRun(Outline outline) throws Exception
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Verbose.: " + isVerbose());
			sb.append("\n  Debug...: " + isDebug());
			info(sb.toString());
		}
	}
	
	@Override
	protected void afterRun(Outline outline) throws Exception
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_FINISH);
			sb.append("\nResults");
			sb.append("\n  HadError.: " + hadError(outline.getErrorReceiver()));
			info(sb.toString());
		}
	}
	
	/**
	 * <p>
	 * Run the plugin with and XJC {@link Outline}.
	 * </p>
	 * 
     * <p>
     * <b>Note:</b> This method is invoked only when a plugin is activated.
     * </p>
	 *
     * @param outline
     *      This object allows access to various generated code.
     * 
     * @return
     *      If the add-on executes successfully, return true.
     *      If it detects some errors but those are reported and
     *      recovered gracefully, return false.
     *
     * @throws Exception
     *      This 'run' method is a call-back method from {@link AbstractPlugin}
     *      and that method is responsible for handling all exceptions. It reports
     *      any exception to {@link ErrorHandler} and converts the exception to
     *      a {@link SAXException} for processing by {@link com.sun.tools.xjc.Plugin}.
	 */
	@Override
	public boolean run(Outline outline) throws Exception
	{
		DummyListField_$get = new FieldAccessor<JMethod>(DummyListField.class, "$get", JMethod.class);
		IsSetField_core = new FieldAccessor<FieldOutline>(IsSetField.class, "core", FieldOutline.class);
		AbstractListField_field = new FieldAccessor<JFieldVar>(DummyListField.class.getSuperclass(), "field", JFieldVar.class);
		AbstractListField_listT = new FieldAccessor<JClass>(DummyListField.class.getSuperclass(), "listT", JClass.class);
		DummyListField_coreList = new FieldAccessor<JClass>(DummyListField.class, "coreList", JClass.class);
		
		for (ClassOutline classOutline : outline.getClasses())
			processClassOutline(classOutline);
		
		return !hadError(outline.getErrorReceiver());
	}

	private void processClassOutline(ClassOutline classOutline)
	{
		for (FieldOutline fieldOutline : classOutline.getDeclaredFields())
			fixFieldOutline(fieldOutline);
		final JDefinedClass theClass = classOutline.implClass;
		debug("{}, processClassOutline; Class={}", toLocation(theClass.metadata), theClass.name());
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
			CPropertyInfo fieldInfo = fieldOutline.getPropertyInfo();
			final JMethod $get = fieldOutline.parent().implClass.method(JMod.PUBLIC, listT,
				"get" + fieldInfo.getName(true));
			JBlock block = $get.body();
			block._if(field.eq(JExpr._null()))._then().assign(field, JExpr._new(coreList));
			block._return(JExpr._this().ref(field));
			DummyListField_$get.set(fieldOutline, $get);
			trace("{}, fixDummyListField; Field={}", toLocation(fieldInfo.getLocator()), fieldInfo.getName(false));
		}
	}

	private void fixIsSetField(IsSetField isSetField)
	{
		fixIsSetMethod(isSetField);
		final FieldOutline core = IsSetField_core.get(isSetField);
		fixFieldOutline(core);
		CPropertyInfo fieldInfo = isSetField.getPropertyInfo();
		trace("{}, fixIsSetField; Field={}", toLocation(fieldInfo.getLocator()), fieldInfo.getName(false));
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
