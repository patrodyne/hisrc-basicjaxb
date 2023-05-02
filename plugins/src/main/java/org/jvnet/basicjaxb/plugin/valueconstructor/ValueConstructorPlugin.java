package org.jvnet.basicjaxb.plugin.valueconstructor;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.locator.util.LocatorUtils.getLocation;
import static org.jvnet.basicjaxb.plugin.util.FieldOutlineUtils.filter;
import static org.jvnet.basicjaxb.plugin.valueconstructor.Customizations.IGNORED_ELEMENT_NAME;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * <p>
 * Generate two constructors for each generated class, one of which is a default
 * constructor, the other takes an argument for each field in the class and
 * Initializes the field with the argument value.
 * </p>
 * 
 * <p>
 * Without this plugin, XJC will not generate any explicit constructors.
 * </p>
 * 
 * <p>
 * Adds a default constructor and a constructor for all instance (non static)
 * fields.
 * </p>
 * 
 * <p>
 * Note: This plugin was derived from the original java EE jaxb2-commons project
 * at: https://github.com/javaee/jaxb2-commons. The original plugin used only the
 * {@link com.sun.codemodel} framework to collect the contructor's parameters and
 * initialize all fields. This derived plugin uses the {@link com.sun.tools.xjc.outline}
 * framework to include JAXB customization. Specifically, to use XML schema annotation
 * to ignore selected classes and/or selected fields.
 * </p>
 */
public class ValueConstructorPlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XvalueConstructor";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "enable generation of value constructor(s)";

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
	
	protected void beforeRun(Outline outline, Options options) throws Exception
	{
		setOptions(options);
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
	 * Run the plugin with and XJC {@link Outline} and {@link Options}.
	 * </p>
	 * 
	 * <p>
	 * Run an XJC plugin to add or modify the XJC {@link Outline}. An {@link Outline}
	 * captures which code is generated for which model component. A {@link Model} is
	 * a schema language neutral representation of the result of a schema parsing. XJC
	 * uses this model to turn this into a series of Java source code.
	 * </p>
	 * 
     * <p>
     * <b>Note:</b> This method is invoked only when a plugin is activated.
     * </p>
	 *
     * @param outline
     *      This object allows access to various generated code.
     * 
     * @param options
     * 		The invocation configuration for XJC.
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
	public boolean run(Outline outline, Options options)
		throws Exception
	{
		for (final ClassOutline classOutline : outline.getClasses())
		{
			if (!getIgnoring().isIgnored(classOutline))
				processClassOutline(classOutline);
		}
		return true;
	}
	
	/**
	 * Process the XJC {@link ClassOutline} instance. The goal is to add a constructor to
	 * initialize all non-ignored fields from the given {@link ClassOutline} instance and
	 * its superclass hierarchy. A default constructor is added when required.
	 * 
	 * @param theClassOutline A class outline from the XJC framework.
	 */
	protected void processClassOutline(ClassOutline theClassOutline)
	{
		JDefinedClass theDefinedClass = theClassOutline.implClass;

		FieldOutline[] superClassFilteredFields = getSuperClassFilteredFields(theClassOutline);
		FieldOutline[] theClassFilteredFields = filter(theClassOutline.getDeclaredFields(), getIgnoring());
		
		// If the class or its (generated) superclass has fields after filtering
		// then generate a value constructor
		if ( (superClassFilteredFields.length != 0) || (theClassFilteredFields.length != 0)  )
		{
			// Create the default, no-arg constructor
			final JMethod defaultConstructor = theDefinedClass.constructor(JMod.PUBLIC);
			defaultConstructor.javadoc().add("Default no-arg constructor");
			defaultConstructor.body().invoke("super");
			
			// Create the skeleton of the value constructor
			final JMethod valueConstructor = theDefinedClass.constructor(JMod.PUBLIC);
			valueConstructor.javadoc().add("Field-initializing value constructor");

			// If our superclass is also being generated, then we can assume it will also
			// have its own value constructor, so we add an invocation of that constructor.
			if (theDefinedClass._extends() instanceof JDefinedClass)
				generateSuperArgs(valueConstructor, theDefinedClass, superClassFilteredFields);

			// Now add constructor parameters for each field in "this" class, 
			// and assign them to our fields.
			generateLocalArgs(valueConstructor, theDefinedClass, theClassFilteredFields);
		}
		debug("{}, processClassOutline; Class={}", getLocation(theDefinedClass.metadata), theDefinedClass.name());
	}

	private void generateSuperArgs(final JMethod valueConstructor, JDefinedClass theDefinedClass,
		FieldOutline[] superClassFilteredFields)
	{
		final JInvocation superInvocation = valueConstructor.body().invoke("super");
		
		// Add each argument to the super constructor.
		for (FieldOutline superClassDeclaredField : superClassFilteredFields)
		{
			if (generateConstructorParameter(superClassDeclaredField))
			{
				CPropertyInfo propertyInfo = superClassDeclaredField.getPropertyInfo();
				String fieldName = propertyInfo.getName(false);
				JType fieldType = superClassDeclaredField.getRawType();
				JVar arg = valueConstructor.param(JMod.NONE, fieldType, fieldName);
				superInvocation.arg(arg);
				
				trace("{}, generateSuperArgs; Class={}, Field={}",
					getLocation(propertyInfo.getLocator()), theDefinedClass.name(), fieldName);
			}
		}
	}

	private void generateLocalArgs(final JMethod valueConstructor, JDefinedClass theDefinedClass,
		FieldOutline[] theClassFilteredFields)
	{
		for (FieldOutline theClassDeclaredField : theClassFilteredFields)
		{
			if (generateConstructorParameter(theClassDeclaredField))
			{
				CPropertyInfo propertyInfo = theClassDeclaredField.getPropertyInfo();
				String fieldName = propertyInfo.getName(false);
				JType fieldType = theClassDeclaredField.getRawType();
				JVar arg = valueConstructor.param(JMod.NONE, fieldType, fieldName);
				valueConstructor.body().assign(JExpr.refthis(fieldName), arg);
				
				trace("{}, generateLocalArgs; Class={}, Field={}",
					getLocation(propertyInfo.getLocator()), theDefinedClass.name(), fieldName);
			}
		}
	}
	
	/**
	 * Whether or not to generate a constructor parameter for the given
	 * {@link FieldOutline}.
	 */
	protected boolean generateConstructorParameter(FieldOutline fieldOutline)
	{
		// AFAIK, FieldOutline does not offer modifiers like "static", etc.
		// JType fieldType = fieldOutline.getRawType();
		return true;
	}

	/**
	 * Retrieve a List of the fields of each ancestor class. I walk up the class
	 * hierarchy until I reach a class that isn't being generated by JAXB. The filtered
	 * fields from each class in the hierarchy is added to the beginning of the result
	 * list in order to preserve the <code>super(...)</code> method's parameter order.
	 */
	protected FieldOutline[] getSuperClassFilteredFields(final ClassOutline classOutline)
	{
		List<FieldOutline> fieldOutlineList = new LinkedList<FieldOutline>();

		// Walk up the class hierarchy.
		for ( ClassOutline sco = classOutline.getSuperClass(); sco != null; sco = sco.getSuperClass() )
		{
			FieldOutline[] superFieldOutlines = filter(sco.getDeclaredFields(), getIgnoring());
			// Accumulate the super-field outline array into a list.
			List<FieldOutline> superFieldOutlineList = new ArrayList<FieldOutline>();
			for ( FieldOutline superFieldOutline : superFieldOutlines )
				superFieldOutlineList.add(superFieldOutline);
			// Add the list of super-field outlines to the start of
			// the linked cumulative field outline list.
			fieldOutlineList.addAll(0, superFieldOutlineList);
		}

		// Return an array of the cumulative field outline list from the super hierarchy.
		return fieldOutlineList.toArray(new FieldOutline[fieldOutlineList.size()]);
	}
}
