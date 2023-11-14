package org.jvnet.basicjaxb.plugin.valueconstructor;

import static com.sun.xml.xsom.XSIdentityConstraint.KEY;
import static com.sun.xml.xsom.XSIdentityConstraint.KEYREF;
import static com.sun.xml.xsom.XSIdentityConstraint.UNIQUE;
import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.util.OutlineUtils.filter;
import static org.jvnet.basicjaxb.plugin.valueconstructor.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.util.FieldAccessorUtils.field;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.plugin.util.Selector;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.xml.xsom.XSAttributeUse;
import com.sun.xml.xsom.XSComplexType;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSIdentityConstraint;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSSchemaSet;
import com.sun.xml.xsom.XSType;

/**
 * <p>
 * Generate constructors for each generated class, one of which is a default
 * constructor, the others take an argument for each field in the class and
 * initializes the field with the argument value.
 * </p>
 * 
 * <p>
 * Constructor can be created for complete, required or selector fields using these
 * XJC option arguments:
 * </p>
 * <ul>
 * 	<li>XvalueConstructor-complete=[true|false]</li>
 * 	<li>XvalueConstructor-required=[true|false]</li>
 * 	<li>XvalueConstructor-selected=[true|false]</li>
 * 	<li>XvalueConstructor-selected-key=[true|false]</li>
 * 	<li>XvalueConstructor-selected-keyref=[true|false]</li>
 * 	<li>XvalueConstructor-selected-unique=[true|false]</li>
 * </ul>
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
	
	// XvalueConstructor-complete
	private boolean complete = true;
	public boolean isComplete() { return complete; }
	public void setComplete(boolean complete) { this.complete = complete; }
	
	// XvalueConstructor-required
	private boolean required = true;
	public boolean isRequired() { return required; }
	public void setRequired(boolean required) { this.required = required; }
	
	// XvalueConstructor-selected
	private boolean selected = true;
	public boolean isSelected() { return selected; }
	public void setSelected(boolean selected) { this.selected = selected; }
	
	// XvalueConstructor-selected-key
	private boolean selectedKey = true;
	public boolean isSelectedKey() { return selectedKey; }
	public void setSelectedKey(boolean selectedKey) { this.selectedKey = selectedKey; }
	
	// XvalueConstructor-selected-keyref
	private boolean selectedKeyRef = false;
	public boolean isSelectedKeyRef() { return selectedKeyRef; }
	public void setSelectedKeyRef(boolean selectedKeyRef) { this.selectedKeyRef = selectedKeyRef; }
	
	// XvalueConstructor-selected-unique
	private boolean selectedUnique = true;
	public boolean isSelectedUnique() { return selectedUnique; }
	public void setSelectedUnique(boolean selectedUnique) { this.selectedUnique = selectedUnique; }
	
	// Plugin Processing
	
	@Override
	protected void beforeRun(Outline outline) throws Exception
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Complete.......: " + isComplete());
			sb.append("\n  Required.......: " + isRequired());
			sb.append("\n  Selected.......: " + isSelected());
			sb.append("\n  SelectedKey....: " + isSelectedKey());
			sb.append("\n  SelectedKeyRef.: " + isSelectedKeyRef());
			sb.append("\n  SelectedUnique.: " + isSelectedUnique());
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
	
	/**
	 * <p>
	 * Run the plugin with and XJC {@link Outline}.
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
	public boolean run(Outline outline)
		throws Exception
	{
		ClassOutline[] filteredClassOutlines = filter(outline, getIgnoring());
		
		// Process filtered selectors
		List<Selector> selectors = new ArrayList<>();
		if ( isSelected() )
			selectors = processSelectors(outline, filteredClassOutlines);
		
		// Filter ignored class outlines
		for ( final ClassOutline classOutline : filteredClassOutlines )
			processClassOutline(classOutline, selectors);

		return !hadError(outline.getErrorReceiver());
	}

	/**
	 * Process the XJC {@link ClassOutline} instance. The goal is to add a constructor to
	 * initialize all non-ignored fields from the given {@link ClassOutline} instance and
	 * its superclass hierarchy. A default constructor is added when required.
	 * 
	 * @param theClassOutline A class outline from the XJC framework.
	 */
	protected void processClassOutline(ClassOutline theClassOutline, List<Selector> selectors)
	{
		JDefinedClass theDefinedClass = theClassOutline.implClass;
		
		FieldOutline[] superFields = getSuperClassFilteredFields(theClassOutline);
		FieldOutline[] localFields = filter(theClassOutline.getDeclaredFields(), getIgnoring());
		
		List<ConstructorArgs> constructorArgsList = new ArrayList<>();
		
		if ( isComplete() )
		{
			if ( (superFields.length != 0) || (localFields.length != 0)  )
			{
				String javadoc = "Complete field constructor";
				ConstructorArgs constructorArgs = new ConstructorArgs(theDefinedClass, superFields, localFields, javadoc);
				if ( !constructorArgsList.contains(constructorArgs) )
					constructorArgsList.add(constructorArgs);
			}
		}

		if ( isRequired() )
		{
			List<FieldOutline> reqSuperFields = new ArrayList<>();
			List<FieldOutline> reqLocalFields = new ArrayList<>();
			
			filterRequired(superFields, reqSuperFields);
			filterRequired(localFields, reqLocalFields);
			
			if ( !reqSuperFields.isEmpty() || !reqLocalFields.isEmpty()  )
			{
				String javadoc = "Required field constructor";
				ConstructorArgs constructorArgs = new ConstructorArgs(theDefinedClass, reqSuperFields, reqLocalFields, javadoc);
				if ( !constructorArgsList.contains(constructorArgs) )
					constructorArgsList.add(constructorArgs);
			}
		}

		if ( isSelected() )
		{
			for ( Selector selector : selectors )
			{
				List<FieldOutline> selSuperFields = new ArrayList<>();
				List<FieldOutline> selLocalFields = new ArrayList<>();
				List<JFieldVar> selFieldList = selector.getSelectedFieldMap().get(theDefinedClass);
				
				if ( selFieldList != null )
				{
					for ( FieldOutline superField : superFields )
					{
						if ( selFieldList.contains(field(superField)) )
							selSuperFields.add(superField);
					}

					for ( FieldOutline localField : localFields )
					{
						if ( selFieldList.contains(field(localField)) )
							selLocalFields.add(localField);
					}

					if ( !selSuperFields.isEmpty() || !selLocalFields.isEmpty()  )
					{
						String javadoc = "Selector field constructor: " + selector.getIdentityConstraint().getName();
						ConstructorArgs constructorArgs = new ConstructorArgs(theDefinedClass, selSuperFields, selLocalFields, javadoc);
						if ( !constructorArgsList.contains(constructorArgs) )
							constructorArgsList.add(constructorArgs);
					}
				}
			}
		}
		
		for ( ConstructorArgs constructorArgs : constructorArgsList )
			processFieldOutlines(constructorArgs);
		
		if ( !constructorArgsList.isEmpty() )
			processFieldOutlines(theDefinedClass, "Default constructor");
	}

	private void filterRequired(FieldOutline[] theFields, List<FieldOutline> reqFields)
	{
		for ( FieldOutline theField : theFields )
		{
			CPropertyInfo fieldInfo = theField.getPropertyInfo();
		    if ( fieldInfo.getSchemaComponent() instanceof XSAttributeUse )
		    {
		        // An XSAttributeUse provides isRequired, defaultValue and fixedValue for an XSAttributeDecl.
		        XSAttributeUse attribute = (XSAttributeUse) fieldInfo.getSchemaComponent();
		        if ( attribute.isRequired() )
					reqFields.add(theField);
		    }
		    else if ( fieldInfo.getSchemaComponent() instanceof XSParticle )
		    {
		    	XSParticle particle = (XSParticle) fieldInfo.getSchemaComponent();
		    	if ( particle.getMinOccurs() != null )
		    	{
		        	if ( particle.getMinOccurs().compareTo(BigInteger.ZERO) > 0 )
						reqFields.add(theField);
		    	}
		    }
		}
	}

	// Create the default, no-arg constructor
	private void processFieldOutlines(JDefinedClass theDefinedClass, String javadoc)
	{
		final JMethod defaultConstructor = theDefinedClass.constructor(JMod.PUBLIC);
		defaultConstructor.javadoc().add(javadoc);
		defaultConstructor.body().invoke("super");
		debug("{}, processFieldOutlines; Class={}, JavaDoc={}", toLocation(theDefinedClass.metadata), theDefinedClass.name(), javadoc);
	}

	private void processFieldOutlines(ConstructorArgs args)
	{
		processFieldOutlines(args.getDefinedClass(), args.getSuperFieldList(), args.getLocalFieldList(), args.getJavaDoc());
	}

	private void processFieldOutlines(JDefinedClass theDefinedClass,
		List<FieldOutline> superFieldList, List<FieldOutline> localFieldList, String javadoc)
	{
		FieldOutline[] superFields = superFieldList.toArray(new FieldOutline[superFieldList.size()]);
		FieldOutline[] localFields = localFieldList.toArray(new FieldOutline[localFieldList.size()]);
		processFieldOutlines(theDefinedClass, superFields, localFields, javadoc);
	}
	
	private void processFieldOutlines(JDefinedClass theDefinedClass,
		FieldOutline[] superFields, FieldOutline[] localFields, String javadoc)
	{
		// Create the skeleton of the value constructor
		final JMethod valueConstructor = theDefinedClass.constructor(JMod.PUBLIC);
		valueConstructor.javadoc().add(javadoc);

		// If our superclass is also being generated, then we can assume it will also
		// have its own value constructor, so we add an invocation of that constructor.
		if (theDefinedClass._extends() instanceof JDefinedClass)
			generateSuperArgs(valueConstructor, theDefinedClass, superFields);

		// Now add constructor parameters for each field in "this" class, 
		// and assign them to our fields.
		generateLocalArgs(valueConstructor, theDefinedClass, localFields);
		
		debug("{}, processFieldOutlines; Class={}, JavaDoc={}", toLocation(theDefinedClass.metadata), theDefinedClass.name(), javadoc);
	}

	private List<Selector> processSelectors(Outline outline, ClassOutline[] filteredClassOutlines)
	{
		Model model = outline.getModel();
		XSSchemaSet modelSchemaSet = model.schemaComponent;
		List<Selector> selectors = new ArrayList<>();
		for ( final ClassOutline classOutline : filteredClassOutlines )
		{
			if ( classOutline.getTarget() instanceof CClassInfo  )
			{
				CClassInfo ciTarget = (CClassInfo) classOutline.getTarget();
				if ( ciTarget.getSchemaComponent() instanceof XSElementDecl )
				{
					XSElementDecl ciTargetED = (XSElementDecl) ciTarget.getSchemaComponent();
					for ( XSIdentityConstraint ic : ciTargetED.getIdentityConstraints() )
					{
						if ( isSelectedCategory(ic.getCategory()) )
						{
							selectors.add(new Selector(classOutline, ic));
							trace("{}, processFieldOutlines; Class={}, Selector={}", toLocation(ciTarget), ciTarget.getName(), ic.getName());
						}
					}
				}
				else
				{
					XSComplexType ciTargetCT = (XSComplexType) ciTarget.getSchemaComponent();
					Iterator<XSIdentityConstraint> idConstraintsIter = modelSchemaSet.iterateIdentityConstraints();
					while ( idConstraintsIter.hasNext() )
					{
						XSIdentityConstraint ic = idConstraintsIter.next();
						XSElementDecl icParent = ic.getParent();
						XSType icParentType = icParent.getType();
						if ( ciTargetCT == icParentType )
						{
							if ( isSelectedCategory(ic.getCategory()) )
							{
								selectors.add(new Selector(classOutline, ic));
								trace("{}, processFieldOutlines; Class={}, Selector={}", toLocation(ciTarget), ciTarget.getName(), ic.getName());
							}
						}
					}
				}
			}
		}
		return selectors;
	}
	
	private boolean isSelectedCategory(short category)
	{
		boolean isSelectedCategory = false;
		switch ( category )
		{
			case KEY: isSelectedCategory = isSelectedKey(); break;
			case KEYREF: isSelectedCategory = isSelectedKeyRef(); break;
			case UNIQUE: isSelectedCategory = isSelectedUnique(); break;
		}
		return isSelectedCategory;
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
					toLocation(propertyInfo.getLocator()), theDefinedClass.name(), fieldName);
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
					toLocation(propertyInfo.getLocator()), theDefinedClass.name(), fieldName);
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
