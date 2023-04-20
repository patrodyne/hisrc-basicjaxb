package org.jvnet.basicjaxb.plugin.simpletostring;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.tostring.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.util.FieldOutlineUtils.filter;
import static org.jvnet.basicjaxb.plugin.util.StrategyClassUtils.superClassNotIgnored;
import static org.jvnet.basicjaxb.util.FieldUtils.getPossibleTypes;

import java.util.Collection;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.codegenerator.AbstractCodeGeneratorPlugin;
import org.jvnet.basicjaxb.plugin.codegenerator.CodeGenerator;
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
import com.sun.tools.xjc.Options;
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
	
	protected void beforeRun(Outline outline, Options options) throws Exception
	{
		setOptions(options);
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  ShowFieldNames.: " + isShowFieldNames());
			sb.append("\n  ShowChildItems.: " + isShowChildItems());
			sb.append("\n  FullClassName..: " + isFullClassName());
			info(sb.toString());
		}
	}
	
	protected void afterRun(Outline outline, Options options) throws Exception
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
		final JMethod toStringFieldsMethod = generateToStringFieldsMethod(classOutline, theClass);
		generateToStringMethod(theClass, toStringFieldsMethod);
	}

	// Method: toString
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
	}

	// Method: toStringFields
	private JMethod generateToStringFieldsMethod(ClassOutline classOutline, JDefinedClass theClass)
	{
		final JCodeModel codeModel = theClass.owner();
		final JMethod toStringFieldsMethod = theClass.method(JMod.PROTECTED, codeModel.VOID, "toStringFields");
		toStringFieldsMethod.param(codeModel.ref(StringBuilder.class), "stringBuilder");
		{
			final JVar stringBuilder = toStringFieldsMethod.params().get(0);
			final JBlock body = toStringFieldsMethod.body();
			
			String fieldSeparator = null;
			if ( superClassNotIgnored(classOutline, getIgnoring()) != null )
			{
				body.add(JExpr._super().invoke(toStringFieldsMethod.name()).arg(stringBuilder));
				fieldSeparator = FIELD_SEPARATOR;
			}
			
			final FieldOutline[] declaredFields = filter(classOutline.getDeclaredFields(), getIgnoring());

			if (declaredFields.length > 0)
			{
				for (final FieldOutline fieldOutline : declaredFields)
				{
					final FieldAccessorEx fieldAccessor = getFieldAccessorFactory()
							.createFieldAccessor(fieldOutline, JExpr._this());
					
					if ( !fieldAccessor.isConstant() )
					{
						final JBlock block = body.block();

						String propertyName = fieldOutline.getPropertyInfo().getName(true);
						
						final JVar value = block.decl(fieldAccessor.getType(), "the" + propertyName);
						fieldAccessor.toRawValue(block, value);
						
						final JType exposedType = fieldAccessor.getType();

						final JExpression hasSetValue =
							( fieldAccessor.isAlwaysSet() || fieldAccessor.hasSetValue() == null )
								? JExpr.TRUE
								: fieldAccessor.hasSetValue();

						String fieldName = null;
						if ( isShowFieldNames() )
							fieldName = fieldOutline.getPropertyInfo().getName(false);
						
						CPropertyInfo propertyInfo = fieldAccessor.getPropertyInfo();
						CDefaultValue defaultValue = propertyInfo.defaultValue;
						
						ToStringArguments arguments = new ToStringArguments
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
				}
			}
		}
		return toStringFieldsMethod;
	}
}
