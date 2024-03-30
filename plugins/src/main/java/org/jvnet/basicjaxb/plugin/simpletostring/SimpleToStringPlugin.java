package org.jvnet.basicjaxb.plugin.simpletostring;

import static com.sun.codemodel.JExpr._this;
import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.tostring.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.util.AttributeWildcardArguments.FIELD_NAME;
import static org.jvnet.basicjaxb.plugin.util.AttributeWildcardArguments.HAS_DEFAULT_VALUE;
import static org.jvnet.basicjaxb.plugin.util.AttributeWildcardArguments.HAS_SET_VALUE;
import static org.jvnet.basicjaxb.plugin.util.AttributeWildcardArguments.IS_ALWAYS_SET;
import static org.jvnet.basicjaxb.plugin.util.OutlineUtils.filter;
import static org.jvnet.basicjaxb.plugin.util.StrategyClassUtils.superClassImplements;
import static org.jvnet.basicjaxb.util.FieldUtils.getPossibleTypes;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.util.Collection;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.codegenerator.AbstractCodeGeneratorPlugin;
import org.jvnet.basicjaxb.plugin.codegenerator.CodeGenerator;
import org.jvnet.basicjaxb.plugin.util.AttributeWildcardArguments;
import org.jvnet.basicjaxb.xjc.outline.FieldAccessorEx;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.model.CDefaultValue;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.Aspect;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * Generate reflection-free runtime-free 'toString' methods.
 */
public class SimpleToStringPlugin extends AbstractCodeGeneratorPlugin<ToStringArguments>
{
	private static final String CONTENT_START = "[";
	private static final String CONTENT_END = "]";
	private static final String FIELD_SEPARATOR = ", ";

	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XsimpleToString";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generate reflection-free runtime-free 'toString' method";

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

	@Override
	protected QName getSpecialIgnoredElementName()
	{
		return IGNORED_ELEMENT_NAME;
	}

	private boolean showFieldNames = false;
	public boolean isShowFieldNames() { return showFieldNames; }
	public void setShowFieldNames(boolean showFieldNames) { this.showFieldNames = showFieldNames; }

	private boolean showChildItems = false;
	public boolean isShowChildItems() { return showChildItems; }
	public void setShowChildItems(boolean showChildItems) { this.showChildItems = showChildItems; }

	private boolean fullClassName = false;
	public boolean isFullClassName() { return fullClassName; }
	public void setFullClassName(boolean fullClassName) { this.fullClassName = fullClassName; }

	// Plugin Processing
	
	@Override
	protected void beforeRun(Outline outline) throws Exception
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  ShowFieldNames.: " + isShowFieldNames());
			sb.append("\n  ShowChildItems.: " + isShowChildItems());
			sb.append("\n  FullClassName..: " + isFullClassName());
			sb.append("\n  Verbose........: " + isVerbose());
			sb.append("\n  Debug..........: " + isDebug());
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

	@Override
	protected CodeGenerator<ToStringArguments> createCodeGenerator(JCodeModel codeModel)
	{
		return new ToStringCodeGenerator(codeModel);
	}

	@Override
	protected void generate(ClassOutline classOutline, JDefinedClass theClass)
	{
		String methodName = "toStringFields";
		String[] methodParms = { "java.lang.StringBuilder" };
		final Boolean sciToStringFields = superClassImplements(classOutline, getIgnoring(), methodName, methodParms, false);
		final JMethod toStringFieldsMethod = generateToStringFieldsMethod(classOutline, theClass, sciToStringFields);
		// This plugin needs to override the toString() method, once, on the base of its
		// class hierarchy. If the current superclass implements the toStringFields(...)
		// then we can assume the custom toString() method exists on the base class and
		// we do not need to generate the toString() method on the current class because
		// it will be inherited from the base.
		if ( !sciToStringFields )
			generateToStringMethod(theClass, toStringFieldsMethod);
	}

	// Add Method: toString
	private void generateToStringMethod(JDefinedClass theClass,	final JMethod toStringFieldsMethod)
	{
		final JCodeModel codeModel = theClass.owner();
		final JMethod toStringMethod = theClass.method(JMod.PUBLIC, codeModel.ref(String.class), "toString");
		{
			toStringMethod.annotate(Override.class);
			final JBlock body = toStringMethod.body();

			final JVar stringBuilder = body.decl
			(
				JMod.FINAL, codeModel.ref(StringBuilder.class), "stringBuilder",
				JExpr._new(codeModel.ref(StringBuilder.class))
			);
			
			if ( isFullClassName() )
				body.add(stringBuilder.invoke("append").arg(JExpr._this().invoke("getClass").invoke("getName")));
			else
				body.add(stringBuilder.invoke("append").arg(JExpr._this().invoke("getClass").invoke("getSimpleName")));
			body.add(stringBuilder.invoke("append").arg(JExpr.lit('@')));
			body.add(stringBuilder.invoke("append").arg
			(
				codeModel.ref(Integer.class).staticInvoke("toHexString").arg
				(
					codeModel.ref(System.class).staticInvoke("identityHashCode").arg(JExpr._this()))
				)
			);

			body.add(stringBuilder.invoke("append").arg(JExpr.lit(CONTENT_START)));
			body.invoke(toStringFieldsMethod.name()).arg(stringBuilder);
			body.add(stringBuilder.invoke("append").arg(JExpr.lit(CONTENT_END)));

			body._return(stringBuilder.invoke("toString"));
		}
		debug("{}, generateToStringMethod; Class={}", toLocation(theClass.metadata), theClass.name());
	}

	// Add Method: toStringFields
	private JMethod generateToStringFieldsMethod(ClassOutline classOutline, JDefinedClass theClass, Boolean sciToStringFields)
	{
		final JCodeModel codeModel = theClass.owner();
		final JMethod toStringFieldsMethod = theClass.method(JMod.PUBLIC, codeModel.VOID, "toStringFields");
		if ( sciToStringFields )
			toStringFieldsMethod.annotate(Override.class);
		toStringFieldsMethod.param(codeModel.ref(StringBuilder.class), "stringBuilder");
		{
			final JBlock body = toStringFieldsMethod.body();
			final JVar stringBuilder = toStringFieldsMethod.params().get(0);
			
			String fieldSeparator = null;
			if ( sciToStringFields )
			{
				body.add(JExpr._super().invoke(toStringFieldsMethod.name()).arg(stringBuilder));
				fieldSeparator = FIELD_SEPARATOR;
			}
			
			final FieldOutline[] declaredFields = filter(classOutline.getDeclaredFields(), getIgnoring());
			for (final FieldOutline fieldOutline : declaredFields)
			{
				final FieldAccessorEx fieldAccessor = getFieldAccessorFactory()
					.createFieldAccessor(fieldOutline, JExpr._this());
				
				final CPropertyInfo fieldInfo = fieldOutline.getPropertyInfo();
				if ( !fieldAccessor.isConstant() )
				{
					final JBlock block = body.block();
					final String propertyName = fieldInfo.getName(true);
					
					final JType exposedType = fieldAccessor.getType();
					final JVar value = block.decl(exposedType, "the" + propertyName);
					fieldAccessor.toRawValue(block, value);

					final JExpression hasSetValue = ( fieldAccessor.isAlwaysSet() || fieldAccessor.hasSetValue() == null )
						? JExpr.TRUE : fieldAccessor.hasSetValue();
					final String fieldName = isShowFieldNames() ? fieldInfo.getName(false) : null;
					final CDefaultValue defaultValue = fieldInfo.defaultValue;
					
					final ToStringArguments arguments = new ToStringArguments
					(
						codeModel,
						stringBuilder,
						value,
						hasSetValue,
						fieldSeparator,
						fieldName,
						isShowChildItems(),
						(defaultValue != null)
					);

					final Collection<JType> possibleTypes =	getPossibleTypes(fieldOutline, Aspect.EXPOSED);
					final boolean isAlwaysSet = fieldAccessor.isAlwaysSet();
					
					getCodeGenerator().generate
					(
						block,
						exposedType,
						possibleTypes,
						isAlwaysSet,
						arguments
					);
					
					fieldSeparator = FIELD_SEPARATOR;
				}
				
				trace("{}, generateToStringFieldsMethod; Class={}, Field={}",
					toLocation(fieldOutline.getPropertyInfo().getLocator()), theClass.name(), fieldInfo.getName(false));
			}

			if ( classOutline.target.declaresAttributeWildcard() )
			{
				final AttributeWildcardArguments awa =
					new AttributeWildcardArguments(classOutline);
				
				final JBlock block = body.block();
				final JVar theValue = awa.fieldVar(block, _this(), "the");
				final String fieldName = isShowFieldNames() ? FIELD_NAME : null;
				
				final ToStringArguments arguments = new ToStringArguments
				(
					codeModel,
					stringBuilder,
					theValue,
					HAS_SET_VALUE,
					fieldSeparator,
					fieldName,
					isShowChildItems(),
					HAS_DEFAULT_VALUE
				);
				
				getCodeGenerator().generate
				(
					block,
					awa.getExposedType(),
					awa.getPossibleTypes(),
					IS_ALWAYS_SET,
					arguments
				);
				
				trace("{}, generateToStringFieldsMethod; Class={}, Field={}",
					toLocation(classOutline), theClass.name(), FIELD_NAME);
			}
		}
		
		return toStringFieldsMethod;
	}
}
