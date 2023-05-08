package org.jvnet.basicjaxb.plugin.mergeable;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.locator.util.LocatorUtils.getLocation;
import static org.jvnet.basicjaxb.plugin.mergeable.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.util.StrategyClassUtils.createStrategyInstanceExpression;
import static org.jvnet.basicjaxb.plugin.util.StrategyClassUtils.superClassImplements;

import java.util.Arrays;
import java.util.Collection;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.lang.JAXBMergeStrategy;
import org.jvnet.basicjaxb.lang.MergeFrom;
import org.jvnet.basicjaxb.lang.MergeStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.locator.util.LocatorUtils;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.plugin.util.FieldOutlineUtils;
import org.jvnet.basicjaxb.util.ClassUtils;
import org.jvnet.basicjaxb.util.FieldAccessorFactory;
import org.jvnet.basicjaxb.util.PropertyFieldAccessorFactory;
import org.jvnet.basicjaxb.xjc.outline.FieldAccessorEx;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JOp;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * With this plug-in you can "merge from" two source objects into the target
 * object, use strategies to customize merging (how exactly should this or that
 * be merged), use locators to check what exactly is being merged at the moment
 * and so on. But the main reason for dependency is to avoid generating the same
 * merging code all over the place for each of the fields of each of the
 * generated classes. The merging algorithm is held in merge strategies.
 * 
 * @author Alexey Valikov
 */
public class MergeablePlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xmergeable";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generate reflection-free methods to merge data from two objects into a target object";

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

	private String mergeStrategyClass = JAXBMergeStrategy.class.getName();
	public String getMergeStrategyClass()
	{
		return mergeStrategyClass;
	}
	public void setMergeStrategyClass(final String mergeStrategyClass)
	{
		this.mergeStrategyClass = mergeStrategyClass;
	}

	public JExpression createMergeStrategy(JCodeModel codeModel)
	{
		return createStrategyInstanceExpression(codeModel, MergeStrategy.class, getMergeStrategyClass());
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
	protected void beforeRun(Outline outline, Options options) throws Exception
	{
		setOptions(options);
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  MergeStrategyClass.: " + getMergeStrategyClass());
			sb.append("\n  Verbose............: " + isVerbose());
			sb.append("\n  Debug..............: " + isDebug());
			info(sb.toString());
		}
	}
	
	@Override
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
		ClassUtils._implements(theClass, theClass.owner().ref(MergeFrom.class));
		
		@SuppressWarnings("unused")
		final JMethod mergeFrom$mergeFrom0 = generateMergeFrom$mergeFrom(classOutline, theClass);
		
		@SuppressWarnings("unused")
		final JMethod mergeFrom$mergeFrom = generateMergeFrom$mergeFrom1(classOutline, theClass);
		
		if (!classOutline.target.isAbstract())
		{
			@SuppressWarnings("unused")
			final JMethod createMergeFrom = generateMergeFrom$createNewInstance(classOutline, theClass);
		}
	}

	protected JMethod generateMergeFrom$createNewInstance(final ClassOutline classOutline, final JDefinedClass theClass)
	{
		final JMethod existingMethod = theClass.getMethod("createNewInstance", new JType[0]);
		if (existingMethod == null)
		{
			final JMethod newMethod = theClass.method(JMod.PUBLIC, theClass.owner().ref(Object.class), "createNewInstance");
			newMethod.annotate(Override.class);
			{
				final JBlock body = newMethod.body();
				body._return(JExpr._new(theClass));
				trace("{}, generateMergeFrom$createNewInstance; Class={}", getLocation(theClass.metadata), theClass.name());
			}
			return newMethod;
		}
		else
			return existingMethod;
	}
	
	protected JMethod generateMergeFrom$mergeFrom(final ClassOutline classOutline, final JDefinedClass theClass)
	{
		JCodeModel codeModel = theClass.owner();
		final JMethod mergeFrom$mergeFrom = theClass.method(JMod.PUBLIC, codeModel.VOID, "mergeFrom");
		mergeFrom$mergeFrom.annotate(Override.class);
		{
			final JVar lhs = mergeFrom$mergeFrom.param(Object.class, "lhs");
			final JVar rhs = mergeFrom$mergeFrom.param(Object.class, "rhs");
			final JBlock body = mergeFrom$mergeFrom.body();
			final JVar mergeStrategy = body.decl(JMod.FINAL, codeModel.ref(MergeStrategy.class), "strategy", createMergeStrategy(codeModel));
			
			final JInvocation lhsRootLocator = JExpr._new(codeModel.ref(DefaultRootObjectLocator.class)).arg(lhs);
			final JVar lhsLocator = body.decl(JMod.NONE, codeModel.ref(ObjectLocator.class), "lhsLocator", JExpr._null());
			
			final JInvocation rhsRootLocator = JExpr._new(codeModel.ref(DefaultRootObjectLocator.class)).arg(rhs);
			final JVar rhsLocator = body.decl(JMod.NONE, codeModel.ref(ObjectLocator.class), "rhsLocator", JExpr._null());
			
			JConditional ifDebugEnabled = body._if(mergeStrategy.invoke("isDebugEnabled"));
			ifDebugEnabled._then().assign(lhsLocator, lhsRootLocator);
			ifDebugEnabled._then().assign(rhsLocator, rhsRootLocator);
			
			body.invoke("mergeFrom")
				.arg(lhsLocator)
				.arg(rhsLocator)
				.arg(lhs)
				.arg(rhs)
				.arg(mergeStrategy);
			
			debug("{}, generateMergeFrom$mergeFrom; Class={}", getLocation(theClass.metadata), theClass.name());
		}
		return mergeFrom$mergeFrom;
	}

	protected JMethod generateMergeFrom$mergeFrom1(ClassOutline classOutline, final JDefinedClass theClass)
	{
		final JCodeModel codeModel = theClass.owner();
		final JMethod mergeFrom = theClass.method(JMod.PUBLIC, codeModel.VOID, "mergeFrom");
		mergeFrom.annotate(Override.class);
		{
			final JVar lhsLocator = mergeFrom.param(ObjectLocator.class, "lhsLocator");
			final JVar rhsLocator = mergeFrom.param(ObjectLocator.class, "rhsLocator");
			final JVar lhs = mergeFrom.param(Object.class, "lhs");
			final JVar rhs = mergeFrom.param(Object.class, "rhs");
			final JVar mergeStrategy = mergeFrom.param(MergeStrategy.class, "strategy");
			final JBlock methodBody = mergeFrom.body();
			Boolean superClassImplementsMergeFrom = superClassImplements(classOutline, getIgnoring(), MergeFrom.class);
			
			if (superClassImplementsMergeFrom == null)
			{
			}
			else if (superClassImplementsMergeFrom.booleanValue())
			{
				methodBody.invoke(JExpr._super(), "mergeFrom").arg(lhsLocator).arg(rhsLocator).arg(lhs).arg(rhs).arg(mergeStrategy);
			}
			else
			{
			}
			
			final FieldOutline[] declaredFields = FieldOutlineUtils.filter(classOutline.getDeclaredFields(), getIgnoring());
			if (declaredFields.length > 0)
			{
				final JBlock body = methodBody._if(rhs._instanceof(theClass))._then();
				JVar target = body.decl(JMod.FINAL, theClass, "target", JExpr._this());
				JVar lhsObject = body.decl(JMod.FINAL, theClass, "lhsObject", JExpr.cast(theClass, lhs));
				JVar rhsObject = body.decl(JMod.FINAL, theClass, "rhsObject", JExpr.cast(theClass, rhs));
				for (final FieldOutline fieldOutline : declaredFields)
				{
					final FieldAccessorEx lhsFieldAccessor = getFieldAccessorFactory().createFieldAccessor(fieldOutline, lhsObject);
					final FieldAccessorEx rhsFieldAccessor = getFieldAccessorFactory() .createFieldAccessor(fieldOutline, rhsObject);
					if (lhsFieldAccessor.isConstant() || rhsFieldAccessor.isConstant())
						continue;
					
					final JBlock block = body.block();
					
					final JExpression lhsFieldHasSetValueEx = (lhsFieldAccessor.isAlwaysSet() || lhsFieldAccessor.hasSetValue() == null)
						? JExpr.TRUE : lhsFieldAccessor.hasSetValue();
					
					final JExpression rhsFieldHasSetValueEx = (rhsFieldAccessor.isAlwaysSet() || rhsFieldAccessor.hasSetValue() == null)
						? JExpr.TRUE : rhsFieldAccessor.hasSetValue();
					
					final JVar lhsFieldIsSet = block.decl(codeModel.ref(Boolean.class).unboxify(), "lhsFieldIsSet", lhsFieldHasSetValueEx);
					final JVar rhsFieldIsSet = block.decl(codeModel.ref(Boolean.class).unboxify(), "rhsFieldIsSet", rhsFieldHasSetValueEx);
					
					final JVar shouldBeSet = block.decl
					(
						codeModel.ref(Boolean.class),
						fieldName("both") + "ShouldBeMergedAndSet",
						mergeStrategy.invoke("shouldBeMergedAndSet")
							.arg(lhsLocator)
							.arg(rhsLocator)
							.arg(lhsFieldIsSet)
							.arg(rhsFieldIsSet)
					);
					
					final JConditional ifShouldBeSetConditional = block
						._if(JOp.eq(shouldBeSet, codeModel.ref(Boolean.class).staticRef("TRUE")));
					
					final JBlock ifShouldBeSetBlock = ifShouldBeSetConditional._then();
					final JConditional ifShouldNotBeSetConditional = ifShouldBeSetConditional._elseif
					(
						JOp.eq
						(
							shouldBeSet,
							codeModel.ref(Boolean.class).staticRef("FALSE")
						)
					);
					
					final JBlock ifShouldBeUnsetBlock = ifShouldNotBeSetConditional._then();
					// final JBlock ifShouldBeIgnoredBlock = ifShouldNotBeSetConditional._else();
					
					final JVar lhsField = ifShouldBeSetBlock.decl(lhsFieldAccessor.getType(), fieldName("lhs"));
					lhsFieldAccessor.toRawValue(ifShouldBeSetBlock, lhsField);
					
					final JVar rhsField = ifShouldBeSetBlock.decl(rhsFieldAccessor.getType(), fieldName("rhs"));
					rhsFieldAccessor.toRawValue(ifShouldBeSetBlock, rhsField);
					
					String fieldName = fieldName(fieldOutline);
					
					final JExpression lhsFieldLocatorEx = codeModel.ref(LocatorUtils.class).staticInvoke("property")
						.arg(lhsLocator)
						.arg(fieldName)
						.arg(lhsField);
					
					final JExpression rhsFieldLocatorEx = codeModel.ref(LocatorUtils.class).staticInvoke("property")
						.arg(rhsLocator)
						.arg(fieldName)
						.arg(rhsField);
					
					final JVar lhsFieldLocator = ifShouldBeSetBlock.decl(lhsLocator.type(), "lhsFieldLocator", lhsFieldLocatorEx);
					final JVar rhsFieldLocator = ifShouldBeSetBlock.decl(lhsLocator.type(), "rhsFieldLocator", rhsFieldLocatorEx);
					
					final FieldAccessorEx targetFieldAccessor = getFieldAccessorFactory().createFieldAccessor(fieldOutline, target);
					
					final JExpression mergedValue = JExpr.cast
					(
						targetFieldAccessor.getType(),
						mergeStrategy.invoke("merge")
							.arg(lhsFieldLocator)
							.arg(rhsFieldLocator)
							.arg(lhsField)
							.arg(rhsField)
							.arg(lhsFieldIsSet)
							.arg(rhsFieldIsSet)
					);
					
					final JVar mergedField = ifShouldBeSetBlock.decl
					(
						rhsFieldAccessor.getType(),
						fieldName("merged"),
						mergedValue
					);
					final JType mergedFieldType = rhsFieldAccessor.getType();

					if (mergedFieldType instanceof JClass && ((JClass) mergedFieldType).isParameterized())
						mergedField.annotate(SuppressWarnings.class).param("value", "unchecked");
					
					targetFieldAccessor.fromRawValue
					(
						ifShouldBeSetBlock,
						fieldName("unique"),
						mergedField
					);
					
					targetFieldAccessor.unsetValues(ifShouldBeUnsetBlock);
					
					trace("{}, generateMergeFrom$mergeFrom1; Class={}, Field={}",
						getLocation(fieldOutline.getPropertyInfo().getLocator()), theClass.name(), fieldName);
				}
			}
		}
		return mergeFrom;
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
