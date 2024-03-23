package org.jvnet.basicjaxb.plugin.simpleequals;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.equals.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.util.OutlineUtils.filter;
import static org.jvnet.basicjaxb.plugin.util.StrategyClassUtils.superClassNotIgnored;
import static org.jvnet.basicjaxb.util.FieldUtils.getPossibleTypes;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.util.Collection;
import java.util.Set;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.codegenerator.AbstractCodeGeneratorPlugin;
import org.jvnet.basicjaxb.plugin.codegenerator.CodeGenerator;
import org.jvnet.basicjaxb.xjc.outline.FieldAccessorEx;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JOp;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.Aspect;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * Generate reflection-free runtime-free 'equals' methods.
 */
public class SimpleEqualsPlugin extends AbstractCodeGeneratorPlugin<EqualsArguments>
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XsimpleEquals";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generate reflection-free runtime-free 'equals' methods";

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

	@Override
	protected CodeGenerator<EqualsArguments> createCodeGenerator(JCodeModel codeModel)
	{
		return new EqualsCodeGenerator(codeModel);
	}

	@Override
	protected void generate(ClassOutline classOutline, JDefinedClass theClass)
	{
		final JCodeModel codeModel = theClass.owner();
		final JMethod objectEquals = theClass.method(JMod.PUBLIC, codeModel.BOOLEAN, "equals");
		objectEquals.annotate(Override.class);
		{
			final JBlock body = objectEquals.body();
			final JVar object = objectEquals.param(Object.class, "object");
			JExpression objectIsNull = object.eq(JExpr._null());
			JExpression notTheSameType = JExpr._this().invoke("getClass").ne(object.invoke("getClass"));
			body._if(JOp.cor(objectIsNull, notTheSameType))._then()._return(JExpr.FALSE);
			body._if(JExpr._this().eq(object))._then()._return(JExpr.TRUE);
			
			final Boolean superClassNotIgnored = superClassNotIgnored(classOutline, getIgnoring());
			if (superClassNotIgnored != null)
				body._if(JOp.not(JExpr._super().invoke("equals").arg(object)))._then()._return(JExpr.FALSE);
			
			final JExpression _this = JExpr._this();
			final FieldOutline[] declaredFields = filter(classOutline.getDeclaredFields(), getIgnoring());
			if ( (declaredFields.length > 0) || classOutline.target.declaresAttributeWildcard() )
			{
				final JVar _that = body.decl(JMod.FINAL, theClass, "that", JExpr.cast(theClass, object));
				for (final FieldOutline fieldOutline : declaredFields)
				{
					final FieldAccessorEx lhsFieldAccessor = getFieldAccessorFactory().createFieldAccessor(fieldOutline, _this);
					final FieldAccessorEx rhsFieldAccessor = getFieldAccessorFactory().createFieldAccessor(fieldOutline, _that);
					
					final CPropertyInfo fieldInfo = fieldOutline.getPropertyInfo();
					if ( !(lhsFieldAccessor.isConstant() || rhsFieldAccessor.isConstant()) )
					{
						final JBlock block = body.block();
						final String propertyName = fieldInfo.getName(true);
						
						final JType lhsExposedType = lhsFieldAccessor.getType();
						final JVar lhsValue = block.decl(lhsExposedType, "lhs" + propertyName);
						lhsFieldAccessor.toRawValue(block, lhsValue);
						
						final JType rhsExposedType = rhsFieldAccessor.getType();
						final JVar rhsValue = block.decl(rhsExposedType, "rhs" + propertyName);
						rhsFieldAccessor.toRawValue(block, rhsValue);
						
						final JExpression lhsHasSetValue = (lhsFieldAccessor.isAlwaysSet() || lhsFieldAccessor.hasSetValue() == null)
							? JExpr.TRUE : lhsFieldAccessor.hasSetValue();
						
						final JExpression rhsHasSetValue = (rhsFieldAccessor .isAlwaysSet() || rhsFieldAccessor.hasSetValue() == null)
							? JExpr.TRUE : rhsFieldAccessor.hasSetValue();
						
						final EqualsArguments arguments = new EqualsArguments
						(
							codeModel,
							lhsValue,
							lhsHasSetValue,
							rhsValue,
							rhsHasSetValue
						);
						
						final Collection<JType> possibleTypes = getPossibleTypes(fieldOutline, Aspect.EXPOSED);
						final boolean isAlwaysSet = lhsFieldAccessor.isAlwaysSet();
						
						getCodeGenerator().generate
						(
							block,
							lhsExposedType,
							possibleTypes,
							isAlwaysSet,
							arguments
						);
					}

					trace("{}, generate; Class={}, Field={}",
						toLocation(fieldOutline.getPropertyInfo().getLocator()), theClass.name(), fieldInfo.getName(false));
				}

				if ( classOutline.target.declaresAttributeWildcard() )
				{
					final Outline outline = classOutline.parent();
					final Model model = outline.getModel();
					
					final JBlock block = body.block();

					final String FIELD_NAME = "otherAttributes";
					final String METHOD_SEED = model.getNameConverter().toClassName(FIELD_NAME);
					final String METHOD_NAME = "get" + METHOD_SEED;
					
					final JDefinedClass coi = classOutline.implClass;
					final JFieldVar field = coi.fields().get(FIELD_NAME);
					final JMethod getter = coi.getMethod(METHOD_NAME, NOARGS);

					final JType exposedType = field.type();
					
					final JInvocation lhsInvokeGetter = _this.invoke(getter);
					final JVar lhsValue = block.decl(exposedType, "lhs" + METHOD_SEED, lhsInvokeGetter);
					final JExpression lhsHasSetValue = JExpr.TRUE;

					final JInvocation rhsInvokeGetter = _that.invoke(getter);
					final JVar rhsValue = block.decl(exposedType, "rhs" + METHOD_SEED, rhsInvokeGetter);
					final JExpression rhsHasSetValue = JExpr.TRUE;
					
					final EqualsArguments arguments = new EqualsArguments
					(
						codeModel,
						lhsValue,
						lhsHasSetValue,
						rhsValue,
						rhsHasSetValue
					);
					
					final Collection<JType> possibleTypes = Set.of(exposedType);
					final boolean isAlwaysSet = true;
					
					getCodeGenerator().generate
					(
						block,
						exposedType,
						possibleTypes,
						isAlwaysSet,
						arguments
					);
					
					trace("{}, generate; Class={}, Field={}",
						toLocation(classOutline), theClass.name(), theClass.name(), FIELD_NAME);
				}
			}
			
			body._return(JExpr.TRUE);
		}
		debug("{}, generate; Class={}", toLocation(theClass.metadata), theClass.name());
	}
}
