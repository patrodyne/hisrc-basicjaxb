package org.jvnet.basicjaxb.plugin.hashcode;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.hashcode.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.util.StrategyClassUtils.createStrategyInstanceExpression;
import static org.jvnet.basicjaxb.plugin.util.StrategyClassUtils.superClassImplements;

import java.util.Arrays;
import java.util.Collection;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.lang.HashCode;
import org.jvnet.basicjaxb.lang.HashCodeStrategy;
import org.jvnet.basicjaxb.lang.JAXBHashCodeStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.locator.ObjectLocator;
import org.jvnet.basicjaxb.locator.util.LocatorUtils;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.plugin.util.FieldOutlineUtils;
import org.jvnet.basicjaxb.util.ClassUtils;
import org.jvnet.basicjaxb.util.FieldAccessorFactory;
import org.jvnet.basicjaxb.util.PropertyFieldAccessorFactory;
import org.jvnet.basicjaxb.xjc.outline.FieldAccessorEx;
import org.xml.sax.ErrorHandler;

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
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * <p>Interface to generate a hash code using {@link JAXBHashCodeStrategy} and preserve an
 * {@link ObjectLocator} path.</p>
 * 
 * <p>
 * With this plug-in you can generate a hash code to map an object into an integer
 * value, use strategies to customize hashing (how exactly should this object
 * be hashed), use locators to check what exactly is being hashed at the moment
 * and so on. But the main reason for dependency is to avoid generating the same
 * hashing code all over the place for each of the fields of each of the
 * generated classes. The hashing algorithm is held in hash strategies.
 * </p>
 * 
 * <p>
 * Objects that are equal (according to their <code>equals()</code>) must return
 * the same hash code. Different objects do not need to return different hash codes.
 * </p>
 */
public class HashCodePlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XhashCode";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generates reflection-free 'hashCode' methods";

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

	private String hashCodeStrategyClass = JAXBHashCodeStrategy.class.getName();
	public String getHashCodeStrategyClass()
	{
		return hashCodeStrategyClass;
	}
	public void setHashCodeStrategyClass(String hashCodeStrategy)
	{
		this.hashCodeStrategyClass = hashCodeStrategy;
	}

	public JExpression createHashCodeStrategy(JCodeModel codeModel)
	{
		return createStrategyInstanceExpression(codeModel, HashCodeStrategy.class, getHashCodeStrategyClass());
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

	@Override
	public boolean run(Outline outline, Options opt, ErrorHandler errorHandler)
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
		ClassUtils._implements(theClass, theClass.owner().ref(HashCode.class));
		@SuppressWarnings("unused")
		final JMethod hashCode$hashCode = generateHashCode$hashCode(classOutline, theClass);
		@SuppressWarnings("unused")
		final JMethod object$hashCode = generateObject$hashCode(classOutline, theClass);
	}

	protected JMethod generateObject$hashCode(final ClassOutline classOutline, final JDefinedClass theClass)
	{
		return generateObject$hashCode(theClass);
	}

	private JMethod generateObject$hashCode(final JDefinedClass theClass)
	{
		final JCodeModel codeModel = theClass.owner();
		final JMethod object$hashCode = theClass.method(JMod.PUBLIC, theClass.owner().INT, "hashCode");
		object$hashCode.annotate(Override.class);
		{
			final JBlock body = object$hashCode.body();
			final JVar theLocator = body.decl(JMod.NONE, codeModel.ref(ObjectLocator.class), "theLocator", JExpr._null());
			final JVar hashCodeStrategy = body.decl(JMod.FINAL, theClass.owner().ref(HashCodeStrategy.class),
				"strategy", createHashCodeStrategy(theClass.owner()));
			final JInvocation theRootLocator = JExpr._new(codeModel.ref(DefaultRootObjectLocator.class)).arg(JExpr._this());
			JConditional ifDebugEnabled = body._if(hashCodeStrategy.invoke("isDebugEnabled"));
			ifDebugEnabled._then().assign(theLocator, theRootLocator);
			body._return(JExpr._this().invoke("hashCode").arg(theLocator).arg(hashCodeStrategy));
		}
		return object$hashCode;
	}

	protected JMethod generateHashCode$hashCode(ClassOutline classOutline, final JDefinedClass theClass)
	{
		JCodeModel codeModel = theClass.owner();
		final JMethod hashCode$hashCode = theClass.method(JMod.PUBLIC, codeModel.INT, "hashCode");
		hashCode$hashCode.annotate(Override.class);
		{
			final JVar locator = hashCode$hashCode.param(ObjectLocator.class, "locator");
			final JVar hashCodeStrategy = hashCode$hashCode.param(HashCodeStrategy.class, "strategy");
			final JBlock body = hashCode$hashCode.body();
			final JExpression currentHashCodeExpression;
			final Boolean superClassImplementsHashCode = superClassImplements(classOutline, ignoring, HashCode.class);
			
			if (superClassImplementsHashCode == null)
				currentHashCodeExpression = JExpr.lit(1);
			else if (superClassImplementsHashCode.booleanValue())
				currentHashCodeExpression = JExpr._super().invoke("hashCode").arg(locator).arg(hashCodeStrategy);
			else
				currentHashCodeExpression = JExpr._super().invoke("hashCode");
			
			final JVar currentHashCode = body.decl(codeModel.INT, "currentHashCode", currentHashCodeExpression);
			final FieldOutline[] declaredFields = FieldOutlineUtils.filter(classOutline.getDeclaredFields(), getIgnoring());
			
			for (final FieldOutline fieldOutline : declaredFields)
			{
				final FieldAccessorEx fieldAccessor = getFieldAccessorFactory().createFieldAccessor(fieldOutline, JExpr._this());
				if (fieldAccessor.isConstant())
					continue;
				
				final JBlock block = body.block();

				final JExpression theFieldIsSetEx = (fieldAccessor.isAlwaysSet() || fieldAccessor.hasSetValue() == null)
					? JExpr.TRUE : fieldAccessor .hasSetValue();
				final JVar theFieldIsSet = block.decl(codeModel.ref(Boolean.class).unboxify(), "theFieldIsSet", theFieldIsSetEx);
				
				final JVar theField = block.decl(fieldAccessor.getType(), fieldName("the"));
				fieldAccessor.toRawValue(block, theField);
				
				final JExpression theFieldLocatorEx = codeModel.ref(LocatorUtils.class).staticInvoke("property")
					.arg(locator)
					.arg(fieldName(fieldOutline))
					.arg(theField);
							
				final JVar theFieldLocator = block.decl(locator.type(), "theFieldLocator", theFieldLocatorEx);
				
				block.assign
				(
					currentHashCode,
					hashCodeStrategy.invoke("hashCode")
					.arg(theFieldLocator)
					.arg(currentHashCode)
					.arg(theField)
					.arg(theFieldIsSet)
				);
			}
			
			body._return(currentHashCode);
		}
		return hashCode$hashCode;
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
