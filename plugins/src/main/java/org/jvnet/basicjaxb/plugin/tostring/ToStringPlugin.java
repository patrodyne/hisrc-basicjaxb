package org.jvnet.basicjaxb.plugin.tostring;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.tostring.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.util.Arrays;
import java.util.Collection;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.lang.JAXBToStringStrategy;
import org.jvnet.basicjaxb.lang.ToString;
import org.jvnet.basicjaxb.lang.ToStringStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.plugin.util.FieldOutlineUtils;
import org.jvnet.basicjaxb.plugin.util.StrategyClassUtils;
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
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * The <b>ToStringPlugin</b> implements the {@link ToString} interface on JAXB
 * generated classes using {@link JAXBToStringStrategy}. 
 */
public class ToStringPlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XtoString";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generate reflection-free 'toString' methods";

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

	private String toStringStrategyClass = JAXBToStringStrategy.class.getName();
	public String getToStringStrategyClass()
	{
		return toStringStrategyClass;
	}
	public void setToStringStrategyClass(String toStringStrategy)
	{
		this.toStringStrategyClass = toStringStrategy;
	}

	public JExpression createToStringStrategy(JCodeModel codeModel)
	{
		return StrategyClassUtils.createStrategyInstanceExpression(codeModel, ToStringStrategy.class,
			getToStringStrategyClass());
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
		return Arrays.asList(IGNORED_ELEMENT_NAME, Customizations.IGNORED_ELEMENT_NAME, Customizations.GENERATED_ELEMENT_NAME);
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
			sb.append("\n  ToStringStrategyClass.: " + getToStringStrategyClass());
			sb.append("\n  Verbose...............: " + isVerbose());
			sb.append("\n  Debug.................: " + isDebug());
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
		for (final ClassOutline classOutline : outline.getClasses())
		{
			if (!getIgnoring().isIgnored(classOutline))
				processClassOutline(classOutline);
		}
		return true;
	}

	protected void processClassOutline(ClassOutline classOutline)
	{
		final JDefinedClass theClass = classOutline.implClass;
		ClassUtils._implements(theClass, theClass.owner().ref(ToString.class));
		@SuppressWarnings("unused")
		final JMethod object$toString = generateObject$toString(classOutline, theClass);
		@SuppressWarnings("unused")
		final JMethod toString$append = generateToString$append(classOutline, theClass);
		@SuppressWarnings("unused")
		final JMethod toString$appendFields = generateToString$appendFields(classOutline, theClass);
	}

	protected JMethod generateObject$toString(final ClassOutline classOutline, final JDefinedClass theClass)
	{
		final JCodeModel codeModel = theClass.owner();
		final JMethod object$toString = theClass.method(JMod.PUBLIC, codeModel.ref(String.class), "toString");
		object$toString.annotate(Override.class);
		{
			final JBlock body = object$toString.body();
			final JVar theLocator = body.decl(JMod.NONE, codeModel.ref(ObjectLocator.class), "theLocator", JExpr._null());
			final JVar toStringStrategy = body.decl(JMod.FINAL, codeModel.ref(ToStringStrategy.class), "strategy", createToStringStrategy(codeModel));
			final JInvocation theRootLocator = JExpr._new(codeModel.ref(DefaultRootObjectLocator.class)) .arg(JExpr._this());
			JConditional ifTraceEnabled = body._if(toStringStrategy.invoke("isTraceEnabled"));
			ifTraceEnabled._then().assign(theLocator, theRootLocator);
			final JVar buffer = body.decl(JMod.FINAL, codeModel.ref(StringBuilder.class), "buffer", JExpr._new(codeModel.ref(StringBuilder.class)));
			body.invoke("append").arg(theLocator).arg(buffer).arg(toStringStrategy);
			body._return(buffer.invoke("toString"));
			debug("{}, generateObject$toString; Class={}", toLocation(theClass.metadata), theClass.name());
		}
		return object$toString;
	}

	protected JMethod generateToString$append(final ClassOutline classOutline, final JDefinedClass theClass)
	{
		final JCodeModel codeModel = theClass.owner();
		final JMethod toString$append = theClass.method(JMod.PUBLIC, codeModel.ref(StringBuilder.class), "append");
		toString$append.annotate(Override.class);
		{
			final JVar locator = toString$append.param(ObjectLocator.class, "locator");
			final JVar buffer = toString$append.param(StringBuilder.class, "buffer");
			final JVar toStringStrategy = toString$append.param(ToStringStrategy.class, "strategy");
			final JBlock body = toString$append.body();
			body.invoke(toStringStrategy, "appendStart").arg(locator).arg(JExpr._this()).arg(buffer);
			body.invoke("appendFields").arg(locator).arg(buffer).arg(toStringStrategy);
			body.invoke(toStringStrategy, "appendEnd").arg(locator).arg(JExpr._this()).arg(buffer);
			body._return(buffer);
		}
		return toString$append;
	}

	protected JMethod generateToString$appendFields(ClassOutline classOutline, final JDefinedClass theClass)
	{
		final JCodeModel codeModel = theClass.owner();
		final JMethod toString$appendFields = theClass.method(JMod.PUBLIC, codeModel.ref(StringBuilder.class), "appendFields");
		toString$appendFields.annotate(Override.class);
		{
			final JVar locator = toString$appendFields.param(ObjectLocator.class, "locator");
			final JVar buffer = toString$appendFields.param(StringBuilder.class, "buffer");
			final JVar toStringStrategy = toString$appendFields.param(ToStringStrategy.class, "strategy");
			final JBlock body = toString$appendFields.body();
			final Boolean superClassImplementsToString = StrategyClassUtils.superClassImplements(classOutline, ignoring, ToString.class);
			
			if (superClassImplementsToString == null)
			{
				// No superclass
			}
			else if (superClassImplementsToString.booleanValue())
			{
				body.invoke(JExpr._super(), "appendFields").arg(locator).arg(buffer).arg(toStringStrategy);
			}
			else
			{
				// Superclass does not implement ToString
			}
			
			final FieldOutline[] declaredFields = FieldOutlineUtils.filter(classOutline.getDeclaredFields(), getIgnoring());
			
			if (declaredFields.length > 0)
			{
				for (final FieldOutline fieldOutline : declaredFields)
				{
					final JBlock block = body.block();

					final FieldAccessorEx fieldAccessor = getFieldAccessorFactory().createFieldAccessor(fieldOutline, JExpr._this());

					final JExpression theFieldIsSetEx = (fieldAccessor.isAlwaysSet() || fieldAccessor.hasSetValue() == null)
						? JExpr.TRUE : fieldAccessor.hasSetValue();
					final JVar theFieldIsSet = block.decl(codeModel.ref(Boolean.class).unboxify(), "theFieldIsSet", theFieldIsSetEx);

					final JVar theField = block.decl(fieldAccessor.getType(), fieldName("the"));
					fieldAccessor.toRawValue(block, theField);
					
					String fieldName = fieldName(fieldOutline);
					
					block.invoke(toStringStrategy, "appendField")
						.arg(locator)
						.arg(JExpr._this())
						.arg(JExpr.lit(fieldName))
						.arg(buffer)
						.arg(theField)
						.arg(theFieldIsSet);
					
					trace("{}, generateHashCode$hashCode; Class={}, Field={}",
						toLocation(fieldOutline.getPropertyInfo().getLocator()), theClass.name(), fieldName);
				}
			}
			
			body._return(buffer);
		}
		return toString$appendFields;
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
