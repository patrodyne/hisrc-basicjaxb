package org.jvnet.basicjaxb.plugin.simplehashcode;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.hashcode.Customizations.IGNORED_ELEMENT_NAME;

import java.util.Collection;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.codegenerator.AbstractCodeGeneratorPlugin;
import org.jvnet.basicjaxb.plugin.codegenerator.CodeGenerator;
import org.jvnet.basicjaxb.plugin.util.FieldOutlineUtils;
import org.jvnet.basicjaxb.plugin.util.StrategyClassUtils;
import org.jvnet.basicjaxb.util.FieldUtils;
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
import com.sun.tools.xjc.outline.Aspect;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * Generate reflection-free runtime-free 'hashCode' methods.
 */
public class SimpleHashCodePlugin extends AbstractCodeGeneratorPlugin<HashCodeArguments>
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XsimpleHashCode";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generate reflection-free runtime-free 'hashCode' methods";

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
	// Plugin Processing
	
	protected void beforeRun(Outline outline, Options options) throws Exception
	{
		setOptions(options);
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  None");
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
	protected CodeGenerator<HashCodeArguments> createCodeGenerator(JCodeModel codeModel)
	{
		return new HashCodeCodeGenerator(codeModel);
	}

	@Override
	protected void generate(ClassOutline classOutline, JDefinedClass theClass)
	{
		final JCodeModel codeModel = theClass.owner();
		final JMethod object$hashCode = theClass.method(JMod.PUBLIC, codeModel.INT, "hashCode");
		object$hashCode.annotate(Override.class);
		{
			final JBlock body = object$hashCode.body();
			final JExpression currentHashCodeExpression = JExpr.lit(1);
			final JVar currentHashCode = body.decl(codeModel.INT, "currentHashCode", currentHashCodeExpression);
			final Boolean superClassImplementsHashCode = StrategyClassUtils.superClassNotIgnored(classOutline,
				getIgnoring());
			if (superClassImplementsHashCode != null)
			{
				body.assign(currentHashCode,
					currentHashCode.mul(JExpr.lit(getMultiplier())).plus(JExpr._super().invoke("hashCode")));
			}
			final FieldOutline[] declaredFields = FieldOutlineUtils.filter(classOutline.getDeclaredFields(),
				getIgnoring());
			if (declaredFields.length > 0)
			{
				for (final FieldOutline fieldOutline : declaredFields)
				{
					final FieldAccessorEx fieldAccessor = getFieldAccessorFactory().createFieldAccessor(fieldOutline,
						JExpr._this());
					if (fieldAccessor.isConstant())
					{
						continue;
					}
					final JBlock block = body.block();
					block.assign(currentHashCode, currentHashCode.mul(JExpr.lit(getMultiplier())));
					String propertyName = fieldOutline.getPropertyInfo().getName(true);
					final JVar value = block.decl(fieldAccessor.getType(), "the" + propertyName);
					fieldAccessor.toRawValue(block, value);
					final JType exposedType = fieldAccessor.getType();
					final Collection<JType> possibleTypes = FieldUtils.getPossibleTypes(fieldOutline, Aspect.EXPOSED);
					final boolean isAlwaysSet = fieldAccessor.isAlwaysSet();
					// final JExpression hasSetValue = exposedType.isPrimitive()
					// ? JExpr.TRUE
					// : value.ne(JExpr._null());
					final JExpression hasSetValue = (fieldAccessor.isAlwaysSet()
														|| fieldAccessor.hasSetValue() == null) ? JExpr.TRUE
																								: fieldAccessor
																									.hasSetValue();
					getCodeGenerator().generate(block, exposedType, possibleTypes, isAlwaysSet,
						new HashCodeArguments(codeModel, currentHashCode, getMultiplier(), value, hasSetValue));
				}
			}
			body._return(currentHashCode);
		}
	}

	private int multiplier = 31;
	private int getMultiplier()
	{
		return multiplier;
	}
}
