package org.jvnet.basicjaxb.plugin.beaninfo;

import static com.sun.codemodel.JExpr.lit;
import static com.sun.xml.xsom.XSFacet.FACET_FRACTIONDIGITS;
import static com.sun.xml.xsom.XSFacet.FACET_LENGTH;
import static com.sun.xml.xsom.XSFacet.FACET_MAXEXCLUSIVE;
import static com.sun.xml.xsom.XSFacet.FACET_MAXINCLUSIVE;
import static com.sun.xml.xsom.XSFacet.FACET_MAXLENGTH;
import static com.sun.xml.xsom.XSFacet.FACET_MINEXCLUSIVE;
import static com.sun.xml.xsom.XSFacet.FACET_MININCLUSIVE;
import static com.sun.xml.xsom.XSFacet.FACET_MINLENGTH;
import static com.sun.xml.xsom.XSFacet.FACET_PATTERN;
import static com.sun.xml.xsom.XSFacet.FACET_TOTALDIGITS;
import static com.sun.xml.xsom.XSIdentityConstraint.KEY;
import static com.sun.xml.xsom.XSIdentityConstraint.KEYREF;
import static com.sun.xml.xsom.XSIdentityConstraint.UNIQUE;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static org.jvnet.basicjaxb.lang.Access.READ_WRITE;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.lang.Access;
import org.jvnet.basicjaxb.lang.Alignment;
import org.jvnet.basicjaxb.lang.DataBeanInfo;
import org.jvnet.basicjaxb.lang.DataDescriptor;
import org.jvnet.basicjaxb.lang.FieldDescriptor;
import org.jvnet.basicjaxb.lang.Width;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.beaninfo.model.Bean;
import org.jvnet.basicjaxb.plugin.beaninfo.model.Constraint;
import org.jvnet.basicjaxb.plugin.beaninfo.model.Property;
import org.jvnet.basicjaxb.plugin.beaninfo.model.Source;
import org.jvnet.basicjaxb.plugin.beaninfo.model.Target;
import org.jvnet.basicjaxb.plugin.util.Selector;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.sun.codemodel.JArray;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCatchBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JDocComment;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldRef;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JTryBlock;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.xml.xsom.XSFacet;
import com.sun.xml.xsom.XSIdentityConstraint;
import com.sun.xml.xsom.XSXPath;

/**
 * An XJC plugin to generate BeanInfo classes from XML Schema annotations.
 * The annotations provide appinfos containing elements from the
 * {@link Customizations} namespace.
 */
public class BeanInfoPlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XbeanInfo";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generate BeanInfo classes from appinfos";

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
	public Collection<QName> getCustomizationElementNames()
	{
		return Arrays.asList
			(
				Customizations.BEAN_ELEMENT_NAME,
				Customizations.PROPERTY_ELEMENT_NAME
			);
	}
	
	// The package name that will be used for finding BeanInfo classes.
	private String searchPath;
	public String getSearchPath() { return searchPath; }
	public void setSearchPath(String searchPath) { this.searchPath = searchPath; }

	// Plugin Processing

	@Override
	protected void beforeRun(Outline outline) throws Exception
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  searchPath.: " + getSearchPath());
			sb.append("\n  Verbose....: " + isVerbose());
			sb.append("\n  Debug......: " + isDebug());
			info(sb.toString());
		}
		 
		// This plugin generates BeanInfo classes to the 'search path'.
		// Applications can configure the Introspector , as it is below,
		// to find the generated BeanInfo(s). This plugin does not use
		// this path for introspection, only for generation.
		if ( getSearchPath() != null )
			Introspector.setBeanInfoSearchPath(new String[] { getSearchPath() });
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
		// A factory to create BeanInfoCustomization instances.
		BeanInfoCustomizationFactory bicf = new BeanInfoCustomizationFactory(outline);
		// Process all XJC class outlines.
		for (final ClassOutline classOutline : outline.getClasses())
			processClassOutline(bicf, classOutline);
		
		return !hadError(outline.getErrorReceiver());
	}

	/* Process the current class outline to generate BeanInfo classes containing customizations. */
	private void processClassOutline(BeanInfoCustomizationFactory bicf, ClassOutline classOutline)
		throws JClassAlreadyExistsException
	{
		// Collect all bean and property CPluginCustomization instances in this
		// plugin's Customizations namespace.
		BeanInfoCustomization bic = bicf.createBeanInfoCustomization(classOutline);
		if ( bic.hasCustomizations() )
		{
			generateBeanInfo(bic);
			generateBeanDescriptor(bic);
			generatePropertyDescriptors(bic);
		}
	}

	/* Generate an empty BeanInfo class */
	private void generateBeanInfo(BeanInfoCustomization bic)
		throws JClassAlreadyExistsException
	{
		String beanInfoName = null;
		if ( getSearchPath() != null )
		{
			// SearchPath may be prefixed with dots. A single dot represents
			// that the path is relative to the current package. Additional
			// dots represent parent packages.
			JPackage pp = bic.getImplClass().getPackage();
			String sp = getSearchPath();
			while ( sp.startsWith("..") )
			{
				if ( pp.parent() != null )
					pp = pp.parent();
				sp = sp.substring(1);
			}
			// Use a relative or an absolute search path.
			String pkgName = sp.startsWith(".") ? pp.name() + sp : sp;
			// Generate the BeanInfo name.
			beanInfoName = pkgName + "." + bic.getImplClass().name() + "BeanInfo";
		}
		else
			beanInfoName = bic.getImplClass().fullName() + "BeanInfo";
		
		JDefinedClass beanInfoClass = bic.getCodeModel()._class(beanInfoName);
		beanInfoClass._extends(DataBeanInfo.class);
		JDocComment biDoc = beanInfoClass.javadoc();
		biDoc.append("A {@code BeanInfo} class to provide explicit information about the methods,");
		biDoc.append("\nproperties, events, and other features of {@code " + bic.getImplClass().name() + "}.");
		bic.setBeanInfoClass(beanInfoClass);
		debug("{}, generateBeanInfo; Class={}",
			toLocation(bic.getTargetClass().getLocator()),
			bic.getBeanInfoClass().name());
	}
	
	/* Generate BeanDescriptor accessor with custom settings. */
	private void generateBeanDescriptor(BeanInfoCustomization bic)
	{
		Bean bean = bic.getBean();
		
		if ( !bic.getSelectorList().isEmpty() )
		{
			if ( bean == null )
			{
				bean = new Bean();
				bean.setName(bic.getClassPublicName());
			}
			
			// Gather Constrain(s), if any
			for ( Selector selector : bic.getSelectorList() )
			{
				XSIdentityConstraint ic = selector.getIdentityConstraint();
				Map<JDefinedClass, List<JFieldVar>> sfm = selector.getSelectedFieldMap();
				List<List<String>> sfp = selector.getSelectedFieldPaths();
				List<List<String>> sp = selector.getSelectedPaths();
				
				Constraint constraint = new Constraint();
				constraint.setName(ic.getName());
				constraint.setOwner(ic.getParent().getName());
				// Source
				Source source = new Source();
				source.setName(ic.getSelector().getXPath().value);
				switch ( ic.getCategory() )
				{
					case KEY: source.setUnique(true); break;
					case KEYREF: source.setUnique(false); break;
					case UNIQUE: source.setUnique(true); break;
				}
				for ( XSXPath field : ic.getFields() )
					source.getFields().add(field.getXPath().value);
				constraint.setSource(source);
				// Target
				if ( (ic.getCategory() == KEYREF) && (ic.getReferencedKey() != null) )
				{
					XSIdentityConstraint icRef = ic.getReferencedKey();
					Target target = new Target();
					target.setName(icRef.getName());
					for ( XSXPath field : icRef.getFields() )
						target.getFields().add(field.getXPath().value);
					constraint.setTarget(target);
				}
				// Add constraint
				bean.getConstraint().add(constraint);
			}
		}
		
		if ( bean != null )
		{
			JType bdType = bic.getCodeModel().ref(BeanDescriptor.class);
			final JMethod bdm = bic.getBeanInfoClass().method(JMod.PUBLIC, bdType, "getBeanDescriptor");
			bdm.annotate(bic.getCodeModel().ref(Override.class));
			
			JDocComment bdmDoc = bdm.javadoc();
			bdmDoc.append("Get a {@code BeanDescriptor} providing overall information about the bean,");
			bdmDoc.append("\nsuch as its displayName, its description, etc.");
			bdmDoc.addReturn().append("A {@code BeanDescriptor} providing overall information about the bean.");
			
			final JBlock bdmBody = bdm.body();
			JClass ddRef = bic.getCodeModel().ref(DataDescriptor.class);
			final JInvocation bdExp = JExpr._new(ddRef).arg(bic.getImplClass().dotclass());
			final JVar ddVar = bdmBody.decl(JMod.NONE, ddRef, "dd", bdExp);
			
			if ( bean.getName() != null )
				bdmBody.add(ddVar.invoke("setName").arg(bean.getName()));
			if ( bean.getDisplayName() != null )
				bdmBody.add(ddVar.invoke("setDisplayName").arg(bean.getDisplayName()));
			if ( bean.getDescription() != null )
				bdmBody.add(ddVar.invoke("setShortDescription").arg(bean.getDescription()));
			if ( bean.isExpert() != null )
				bdmBody.add(ddVar.invoke("setExpert").arg(lit(bean.isExpert())));
			if ( bean.isHidden() != null )
				bdmBody.add(ddVar.invoke("setHidden").arg(lit(bean.isHidden())));
			if ( bean.isPreferred() != null )
				bdmBody.add(ddVar.invoke("setPreferred").arg(lit(bean.isPreferred())));
			
			JClass ddcRef = bic.getCodeModel().ref(DataDescriptor.Constraint.class);
			for ( Constraint bcon : bean.getConstraint() )
			{
				JBlock bdmBlock = bdmBody.block();
				
				JVar ddcVar = bdmBlock.decl(JMod.NONE, ddcRef, "ddc", JExpr._new(ddcRef));
				bdmBlock.add(ddcVar.invoke("setName").arg(lit(bcon.getName())));
				bdmBlock.add(ddcVar.invoke("setOwner").arg(lit(bcon.getOwner())));
				
				bdmBlock.add(ddcVar.invoke("getSource").invoke("setName").arg(lit(bcon.getSource().getName())));
				bdmBlock.add(ddcVar.invoke("getSource").invoke("setUnique").arg(lit(bcon.getSource().isUnique())));
				for ( String field : bcon.getSource().getFields() )
					bdmBlock.add(ddcVar.invoke("getSource").invoke("getFields").invoke("add").arg(lit(field)));
				
				if ( bcon.getTarget() != null )
				{
					bdmBlock.add(ddcVar.invoke("getTarget").invoke("setName").arg(lit(bcon.getTarget().getName())));
					for ( String field : bcon.getTarget().getFields() )
						bdmBlock.add(ddcVar.invoke("getTarget").invoke("getFields").invoke("add").arg(lit(field)));
				}
				
				bdmBlock.add(ddVar.invoke("getConstraints").invoke("add").arg(ddcVar));
			}
			
			bdmBody._return(ddVar);
			
			Locator locator = null;
			if ( bic.getBeanCustomization() != null )
				locator = bic.getBeanCustomization().locator;
			
			trace("{}, generateBeanDescriptor; Class={}, Bean={}",
				toLocation(locator),
				bic.getBeanInfoClass().name(),
				bean);
		}
	}

	/* Generate getPropertyDescriptors accessor with custom settings. */
	private void generatePropertyDescriptors(BeanInfoCustomization bic)
	{
		JCodeModel codeModel = bic.getCodeModel();
		JType pdType = codeModel.ref(PropertyDescriptor[].class);
		JMethod pdm = bic.getBeanInfoClass().method(JMod.PUBLIC, pdType, "getPropertyDescriptors");
		pdm.annotate(codeModel.ref(Override.class));
		
		JDocComment pdmDoc = pdm.javadoc();
		pdmDoc.append("Get an array of {@code PropertyDescriptor} to describe properties that a Java Bean");
		pdmDoc.append("\nexports via accessor/mutator methods.");
		pdmDoc.addReturn().append("An array {@code PropertyDescriptor} providing accessor/mutator methods.");
		
		JBlock pdmBody = pdm.body();
		
		JInvocation fdListExp = JExpr._new(codeModel.ref(ArrayList.class).narrow(FieldDescriptor.class));
		JVar fdListVar = pdmBody.decl(JMod.NONE, codeModel.ref(List.class)
			.narrow(FieldDescriptor.class), "fdList", fdListExp);
		
		// Create a 'try' statement and add it to this block.
		JTryBlock pdmTryBlock = pdmBody._try();
		// Create a 'catch' statement with an exception parameter named 'ex'.
		JCatchBlock pdmCatchBlock = pdmTryBlock._catch(codeModel.ref(IntrospectionException.class));
		JVar pdmCatchVar = pdmCatchBlock.param("ex");
		// Chain invocations to create a JMethod invocation for logging the exception.
		JInvocation pdmCatchInvokeError = JExpr.invoke("getLogger").invoke("error")
			.arg("Java Beans introspection failed")
			.arg(pdmCatchVar);
		// Add the logging invocation to the catch block's body.
		pdmCatchBlock.body().add(pdmCatchInvokeError);
		
		JBlock pdmTryBlockBody = pdmTryBlock.body();
		
		for ( FieldInfo fieldInfo : bic.getFieldInfoList() )
		{
			// Custom property
			Property fip = fieldInfo.getProperty();
			if ( fip == null )
				fip = new Property();
			
			// Create a block and initialize a FieldDescriptor variable.
			JBlock pdmBlock = pdmTryBlockBody.block();
			
			// Create a FieldDescriptor with two or four arguments
			JInvocation fdExp = JExpr._new(codeModel.ref(FieldDescriptor.class))
				.arg(fieldInfo.getFieldName())
				.arg(bic.getImplClass().dotclass());
			if ( (fip.getReadMethodName() == null) && (fip.getWriteMethodName() == null) )
			{
				JClass accessRef = codeModel.ref(Access.class);
				if ( !READ_WRITE.equals(fieldInfo.getFieldAccess()) )
					fdExp.arg(accessRef.staticRef(fieldInfo.getFieldAccess().name()));
			}
			else
			{
				fdExp.arg(fip.getReadMethodName());
				fdExp.arg(fip.getWriteMethodName());
			}
			JVar fdVar = pdmBlock.decl(JMod.NONE, codeModel.ref(FieldDescriptor.class), "fd", fdExp);
			
			// FieldDescriptor settings (Index)
			if ( fieldInfo.getFieldIndex() != null )
			{
				Integer index = fieldInfo.getFieldIndex();
				if ( fip.getIndex() != null )
					index = fip.getIndex();
				if ( index != null )
					pdmBlock.add(fdVar.invoke("setIndex").arg(lit(index)));
			}
			
			// FieldDescriptor settings (Property)
			generatePropertySetters(codeModel, fieldInfo, pdmBlock, fdVar);

			// FieldDescriptor settings (Facet)
			generateFacetSetters(fieldInfo, pdmBlock, fdVar);
			
			// Add FieldDescriptor to the list.
			pdmBlock.add(fdListVar.invoke("add").arg(fdVar));
		}
		
		JClass fdClass = codeModel.ref(FieldDescriptor.class);
		JInvocation fdListSize = fdListVar.invoke("size");
		JArray fdArrayExp = JExpr.newArray(fdClass, fdListSize);
		JInvocation fdListToArray = fdListVar.invoke("toArray").arg(fdArrayExp);
		pdmBody._return(fdListToArray);
	}
	
	private Map<QName, Alignment> alignmentMap;
	public Map<QName, Alignment> getAlignmentMap()
	{
		if ( alignmentMap == null )
			setAlignmentMap(FieldDescriptor.ALIGN_BY_QNAME_MAP);
		return alignmentMap;
	}
	public void setAlignmentMap(Map<QName, Alignment> alignmentMap)
	{
		this.alignmentMap = alignmentMap;
	}

	private Map<QName, Width> widthMap;
	public Map<QName, Width> getWidthMap()
	{
		if ( widthMap == null )
			setWidthMap(FieldDescriptor.WIDTH_BY_QNAME_MAP);
		return widthMap;
	}
	public void setWidthMap(Map<QName, Width> widthMap)
	{
		this.widthMap = widthMap;
	}

	/* FieldDescriptor Property */
	private void generatePropertySetters(JCodeModel codeModel, FieldInfo fieldInfo, JBlock pdmBlock, JVar fdVar)
	{
		Property fiProperty = fieldInfo.getProperty();
		if ( fiProperty == null )
			fiProperty = new Property();
		
		// FeatureDescriptor settings,
		// Name is set by construction (above)
		// if ( property.getName() != null )
		//     pdmBlock.add(fdVar.invoke("setName").arg(property.getName()));
	
		// FeatureDescriptor settings
		if ( fiProperty.getDisplayName() != null )
			pdmBlock.add(fdVar.invoke("setDisplayName").arg(fiProperty.getDisplayName()));
		if ( fiProperty.getDescription() != null )
			pdmBlock.add(fdVar.invoke("setShortDescription").arg(fiProperty.getDescription()));
		if ( fiProperty.isExpert() != null )
			pdmBlock.add(fdVar.invoke("setExpert").arg(lit(fiProperty.isExpert())));
		if ( fiProperty.isHidden() != null )
			pdmBlock.add(fdVar.invoke("setHidden").arg(lit(fiProperty.isHidden())));
		else
			pdmBlock.add(fdVar.invoke("setHidden").arg(lit(fieldInfo.isFieldHidden())));
		if ( fiProperty.isPreferred() != null )
			pdmBlock.add(fdVar.invoke("setPreferred").arg(lit(fiProperty.isPreferred())));
		
		// PropertyDescriptor settings
		if ( fiProperty.isBound() != null )
			pdmBlock.add(fdVar.invoke("setBound").arg(lit(fiProperty.isBound())));
		if ( fiProperty.isConstrained() != null )
			pdmBlock.add(fdVar.invoke("setConstrained").arg(lit(fiProperty.isConstrained())));
		if ( fiProperty.getEditorClass() != null )
			pdmBlock.add(fdVar.invoke("setPropertyEditorClass").arg(lit(fiProperty.getEditorClass())));
		
		// FieldDescriptor settings (Property)
		JClass alignmentRefClass = codeModel.ref(Alignment.class);
		JFieldRef alignmentRefField;
		if ( fiProperty.getAlignment() != null )
			alignmentRefField = alignmentRefClass.staticRef(fiProperty.getAlignment().name());
		else
			alignmentRefField = alignmentRefClass.staticRef(fieldInfo.getFieldAlignment().name());
		pdmBlock.add(fdVar.invoke("setAlignment").arg(alignmentRefField));
		
		if ( fiProperty.isEditable() != null )
			pdmBlock.add(fdVar.invoke("setEditable").arg(lit(fiProperty.isEditable())));
		else // TODO: FieldInfo
			pdmBlock.add(fdVar.invoke("setEditable").arg(lit(true))); 
		if ( fiProperty.getMaxWidth() != null )
			pdmBlock.add(fdVar.invoke("setMaxWidth").arg(lit(fiProperty.getMaxWidth())));
		if ( fiProperty.getMinWidth() != null )
			pdmBlock.add(fdVar.invoke("setMinWidth").arg(lit(fiProperty.getMinWidth())));
		else
			pdmBlock.add(fdVar.invoke("setMinWidth").arg(lit(fieldInfo.getFieldMinWidth())));
		if ( fiProperty.getPreferredWidth() != null )
			pdmBlock.add(fdVar.invoke("setPreferredWidth").arg(lit(fiProperty.getPreferredWidth())));
		if ( fiProperty.getRendererClass() != null )
			pdmBlock.add(fdVar.invoke("setRendererClass").arg(lit(fiProperty.getRendererClass())));
		if ( fiProperty.isResizable() != null )
			pdmBlock.add(fdVar.invoke("setResizable").arg(lit(fiProperty.isResizable())));
	}

	/* FieldDescriptor Facets */
	private void generateFacetSetters(FieldInfo fieldInfo, JBlock pdmBlock, JVar fdVar)
	{
		for ( XSFacet facet : fieldInfo.getFacets() )
		{	
			String strValue = facet.getValue().toString();
			switch ( facet.getName() )
			{
				case FACET_PATTERN:
					pdmBlock.add(fdVar.invoke("setPattern").arg(strValue));
					break;
				case FACET_LENGTH:
					pdmBlock.add(fdVar.invoke("setLength").arg(lit(parseInt(strValue))));
					break;
				case FACET_MAXLENGTH:
					pdmBlock.add(fdVar.invoke("setMaxLength").arg(lit(parseInt(strValue))));
					break;
				case FACET_MINLENGTH:
					pdmBlock.add(fdVar.invoke("setMinLength").arg(lit(parseInt(strValue))));
					break;
				case FACET_MAXEXCLUSIVE:
					pdmBlock.add(fdVar.invoke("setMaxExclusive").arg(lit(parseInt(strValue))));
					break;
				case FACET_MINEXCLUSIVE:
					pdmBlock.add(fdVar.invoke("setMinExclusive").arg(lit(parseInt(strValue))));
					break;
				case FACET_MAXINCLUSIVE:
					pdmBlock.add(fdVar.invoke("setMaxInclusive").arg(lit(parseInt(strValue))));
					break;
				case FACET_MININCLUSIVE:
					pdmBlock.add(fdVar.invoke("setMinInclusive").arg(lit(parseInt(strValue))));
					break;
				case FACET_TOTALDIGITS:
					pdmBlock.add(fdVar.invoke("setTotalDigits").arg(lit(parseInt(strValue))));
					break;
				case FACET_FRACTIONDIGITS:
					pdmBlock.add(fdVar.invoke("setFractionDigits").arg(lit(parseInt(strValue))));
					break;
			}
		}
	}
}
