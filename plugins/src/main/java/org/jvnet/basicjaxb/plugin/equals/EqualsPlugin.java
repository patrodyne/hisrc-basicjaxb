package org.jvnet.basicjaxb.plugin.equals;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.equals.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.util.AttributeWildcardArguments.FIELD_NAME;
import static org.jvnet.basicjaxb.plugin.util.AttributeWildcardArguments.HAS_SET_VALUE;
import static org.jvnet.basicjaxb.plugin.util.OutlineUtils.filter;
import static org.jvnet.basicjaxb.plugin.util.StrategyClassUtils.createStrategyInstanceExpression;
import static org.jvnet.basicjaxb.plugin.util.StrategyClassUtils.superClassImplements;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.lang.Equals;
import org.jvnet.basicjaxb.lang.EqualsStrategy;
import org.jvnet.basicjaxb.lang.JAXBEqualsStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.locator.util.LocatorUtils;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.plugin.util.AttributeWildcardArguments;
import org.jvnet.basicjaxb.plugin.util.OutlineUtils;
import org.jvnet.basicjaxb.util.ClassUtils;
import org.jvnet.basicjaxb.util.FieldAccessorFactory;
import org.jvnet.basicjaxb.util.PropertyFieldAccessorFactory;
import org.jvnet.basicjaxb.xjc.outline.FieldAccessorEx;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JOp;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * The <b>EqualsPlugin</b> implements the {@link Equals} interface on JAXB
 * generated classes. 
 */
public class EqualsPlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xequals";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generate reflection-free 'equals' methods";

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

	private FieldAccessorFactory fieldAccessorFactory = PropertyFieldAccessorFactory.INSTANCE;
	public FieldAccessorFactory getFieldAccessorFactory()
	{
		return fieldAccessorFactory;
	}
	public void setFieldAccessorFactory(FieldAccessorFactory fieldAccessorFactory)
	{
		this.fieldAccessorFactory = fieldAccessorFactory;
	}

	private String equalsStrategyClass = JAXBEqualsStrategy.class.getName();
	public String getEqualsStrategyClass()
	{
		return equalsStrategyClass;
	}
	public void setEqualsStrategyClass(String equalsStrategyClass)
	{
		this.equalsStrategyClass = equalsStrategyClass;
	}

	public JExpression createEqualsStrategy(JCodeModel codeModel)
	{
		return createStrategyInstanceExpression(codeModel, EqualsStrategy.class, getEqualsStrategyClass());
	}

	private Ignoring ignoring = new CustomizedIgnoring
	(
		IGNORED_ELEMENT_NAME,
		Customizations.IGNORED_ELEMENT_NAME,
		Customizations.GENERATED_ELEMENT_NAME
	);
	public Ignoring getIgnoring()
	{
		return ignoring;
	}
	public void setIgnoring(Ignoring ignoring)
	{
		this.ignoring = ignoring;
	}

	@Override
	public Collection<QName> getCustomizationElementNames()
	{
		return Arrays.asList
		(
			IGNORED_ELEMENT_NAME,
			Customizations.IGNORED_ELEMENT_NAME,
			Customizations.GENERATED_ELEMENT_NAME
		);
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
			sb.append("\n  EqualsStrategyClass.: " + getEqualsStrategyClass());
			sb.append("\n  Verbose.............: " + isVerbose());
			sb.append("\n  Debug...............: " + isDebug());
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
		for (final ClassOutline classOutline : filter(outline, getIgnoring()))
			processClassOutline(classOutline);

		return !hadError(outline.getErrorReceiver());
	}

	protected void processClassOutline(ClassOutline classOutline)
	{
		final JDefinedClass theClass = classOutline.implClass;
		
		if ( !superClassImplements(classOutline, getIgnoring(), Equals.class, false) )
		{
			ClassUtils._implements(theClass, theClass.owner().ref(Equals.class));
			generateObject$equals(classOutline, theClass);
		}
		
		generateEquals$equals(classOutline, theClass);
	}

	protected JMethod generateObject$equals(final ClassOutline classOutline, final JDefinedClass theClass)
	{
		final JCodeModel codeModel = theClass.owner();
		final JMethod objectEquals = theClass.method(JMod.PUBLIC, codeModel.BOOLEAN, "equals");
		objectEquals.annotate(Override.class);
		{
			final JVar that = objectEquals.param(Object.class, "object");
			final JBlock body = objectEquals.body();
			final JVar thisLocator = body.decl(JMod.NONE, codeModel.ref(ObjectLocator.class), "thisLocator", JExpr._null());
			final JVar thatLocator = body.decl(JMod.NONE, codeModel.ref(ObjectLocator.class), "thatLocator", JExpr._null());
			final JVar equalsStrategy = body.decl(JMod.FINAL, codeModel.ref(EqualsStrategy.class),
				"strategy", createEqualsStrategy(codeModel));
			final JInvocation thisRootLocator = JExpr._new(codeModel.ref(DefaultRootObjectLocator.class)).arg(JExpr._this());
			final JInvocation thatRootLocator = JExpr._new(codeModel.ref(DefaultRootObjectLocator.class)).arg(that);
			JConditional ifDebugEnabled = body._if(equalsStrategy.invoke("isDebugEnabled"));
			ifDebugEnabled._then().assign(thisLocator, thisRootLocator).assign(thatLocator, thatRootLocator);
			body._return(JExpr.invoke("equals")
				.arg(thisLocator)
				.arg(thatLocator)
				.arg(that)
				.arg(equalsStrategy));
			debug("{}, generateObject$equals; Class={}", toLocation(theClass.metadata), theClass.name());
		}
		return objectEquals;
	}

	protected JMethod generateEquals$equals(ClassOutline classOutline, final JDefinedClass theClass)
	{
		final JCodeModel codeModel = theClass.owner();
		final JMethod equals = theClass.method(JMod.PUBLIC, codeModel.BOOLEAN, "equals");
		equals.annotate(Override.class);
		{
			final JBlock body = equals.body();
			final JVar lhsLocator = equals.param(ObjectLocator.class, "thisLocator");
			final JVar rhsLocator = equals.param(ObjectLocator.class, "thatLocator");
			final JVar object = equals.param(Object.class, "object");
			final JVar equalsStrategy = equals.param(EqualsStrategy.class, "strategy");
			JExpression objectIsNull = object.eq(JExpr._null());
			JExpression notTheSameType = JExpr._this().invoke("getClass").ne(object.invoke("getClass"));
			body._if(JOp.cor(objectIsNull, notTheSameType))._then()._return(JExpr.FALSE);
			body._if(JExpr._this().eq(object))._then()._return(JExpr.TRUE);
			final Boolean superClassImplementsEquals = superClassImplements(classOutline, getIgnoring(), Equals.class);

			if (superClassImplementsEquals == null)
			{
				// No superclass
			}
			else if (superClassImplementsEquals.booleanValue())
			{
				body._if(JOp.not(JExpr._super().invoke("equals").arg(lhsLocator).arg(rhsLocator).arg(object).arg(equalsStrategy)))
					._then()._return(JExpr.FALSE);
			}
			else
			{
				body._if(JOp.not(JExpr._super().invoke("equals").arg(object)))
					._then()._return(JExpr.FALSE);
			}
			
			final JExpression _this = JExpr._this();
			final FieldOutline[] declaredFields = OutlineUtils.filter(classOutline.getDeclaredFields(), getIgnoring());
			
			// Filter out constant fields
			Map<FieldOutline, FieldAccessorEx> lhsFieldAccessorMap = new LinkedHashMap<>();
			for (final FieldOutline fieldOutline : declaredFields)
			{
				final FieldAccessorEx lhsFieldAccessor = getFieldAccessorFactory().createFieldAccessor(fieldOutline, _this);
				if ( !lhsFieldAccessor.isConstant() )
					lhsFieldAccessorMap.put(fieldOutline, lhsFieldAccessor);
			}
			
			if ( (lhsFieldAccessorMap.size() > 0) || classOutline.target.declaresAttributeWildcard() )
			{
				final JVar _that = body.decl(JMod.FINAL, theClass, "that", JExpr.cast(theClass, object));
				
				for (final FieldOutline fieldOutline : lhsFieldAccessorMap.keySet())
				{
					final FieldAccessorEx lhsFieldAccessor = lhsFieldAccessorMap.get(fieldOutline);
					final FieldAccessorEx rhsFieldAccessor = getFieldAccessorFactory().createFieldAccessor(fieldOutline, _that);
					
					if (lhsFieldAccessor.isConstant() || rhsFieldAccessor.isConstant())
						continue;
					
					final JBlock block = body.block();
					
					final JExpression lhsFieldHasSetValueEx = (lhsFieldAccessor.isAlwaysSet() || lhsFieldAccessor.hasSetValue() == null)
						? JExpr.TRUE : lhsFieldAccessor.hasSetValue();
					final JExpression rhsFieldHasSetValueEx = (rhsFieldAccessor.isAlwaysSet() || rhsFieldAccessor.hasSetValue() == null)
						? JExpr.TRUE : rhsFieldAccessor.hasSetValue();
								
					final JVar lhsFieldIsSet = block.decl(codeModel.ref(Boolean.class).unboxify(), "lhsFieldIsSet", lhsFieldHasSetValueEx);
					final JVar rhsFieldIsSet = block.decl(codeModel.ref(Boolean.class).unboxify(), "rhsFieldIsSet", rhsFieldHasSetValueEx);
					
					final JVar lhsValue = block.decl(lhsFieldAccessor.getType(), fieldName("lhs"));
					lhsFieldAccessor.toRawValue(block, lhsValue);
					
					final JVar rhsValue = block.decl(rhsFieldAccessor.getType(), fieldName("rhs"));
					rhsFieldAccessor.toRawValue(block, rhsValue);
					
					String fieldName = fieldName(fieldOutline);
					
					final JExpression lhsFieldLocatorEx = codeModel.ref(LocatorUtils.class).staticInvoke("property")
						.arg(lhsLocator)
						.arg(fieldName)
						.arg(lhsValue);
					
					final JExpression rhsFieldLocatorEx = codeModel.ref(LocatorUtils.class).staticInvoke("property")
						.arg(rhsLocator)
						.arg(fieldName)
						.arg(rhsValue);
					
					final JVar lhsFieldLocator = block.decl(lhsLocator.type(), "lhsFieldLocator", lhsFieldLocatorEx);
					final JVar rhsFieldLocator = block.decl(lhsLocator.type(), "rhsFieldLocator", rhsFieldLocatorEx);
					
					block
						._if(JOp.not(JExpr.invoke(equalsStrategy, "equals")
							.arg(lhsFieldLocator)
							.arg(rhsFieldLocator)
							.arg(lhsValue)
							.arg(rhsValue)
							.arg(lhsFieldIsSet)
							.arg(rhsFieldIsSet)))
						._then()._return(JExpr.FALSE);
					
					trace("{}, generateEquals$equals; Class={}, Field={}",
						toLocation(fieldOutline.getPropertyInfo().getLocator()), theClass.name(), fieldName);
				}
				
				if ( classOutline.target.declaresAttributeWildcard() )
				{
					final AttributeWildcardArguments awa =
						new AttributeWildcardArguments(classOutline);
					
					final JBlock block = body.block();
					final JVar lhsField = awa.fieldVar(block, _this, "lhs", "Field");
					final JVar rhsField = awa.fieldVar(block, _that, "rhs", "Field");

					final JExpression lhsFieldLocatorValue = awa.fieldLocatorValue(lhsLocator, lhsField);
					final JVar lhsFieldLocator = awa.fieldLocator(block, lhsLocator, lhsFieldLocatorValue, "lhs");
					
					final JExpression rhsFieldLocatorValue = awa.fieldLocatorValue(rhsLocator, rhsField);
					final JVar rhsFieldLocator = awa.fieldLocator(block, rhsLocator, rhsFieldLocatorValue, "rhs");
					
					block
						._if(JOp.not(JExpr.invoke(equalsStrategy, "equals")
							.arg(lhsFieldLocator)
							.arg(rhsFieldLocator)
							.arg(lhsField)
							.arg(rhsField)
							.arg(HAS_SET_VALUE)
							.arg(HAS_SET_VALUE)))
						._then()._return(JExpr.FALSE);
					
					trace("{}, generateEquals$equals; Class={}, Field={}",
						toLocation(classOutline), theClass.name(), FIELD_NAME);
				}
			}

			body._return(JExpr.TRUE);
		}
		return equals;
	}
	
	private String fieldName(FieldOutline fieldOutline)
	{
		return fieldOutline.getPropertyInfo().getName(false);
	}

	private String fieldName(String prefix)
	{
		return prefix + "Field";
	}
	
	@SuppressWarnings("unused")
	private String fieldName(String prefix, FieldOutline fieldOutline)
	{
		return fieldName(prefix) + fieldOutline.getPropertyInfo().getName(true);
	}
}
