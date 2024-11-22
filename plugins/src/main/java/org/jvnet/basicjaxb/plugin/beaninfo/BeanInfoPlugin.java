package org.jvnet.basicjaxb.plugin.beaninfo;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.util.CustomizationUtils.unmarshall;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.lang.FieldDescriptor;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.beaninfo.model.Bean;
import org.jvnet.basicjaxb.plugin.beaninfo.model.Property;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JArray;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JDocComment;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JTryBlock;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.model.CPluginCustomization;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;

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
		final BeanInfoCustomizationFactory bicf = new BeanInfoCustomizationFactory(outline);
		
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
		beanInfoClass._extends(java.beans.SimpleBeanInfo.class);
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
		if (bic.getBeanCustomization() != null)
		{
			Bean bd = (Bean) unmarshall(Customizations.getContext(), bic.getBeanCustomization());
			
			JType bdType = bic.getCodeModel().ref(BeanDescriptor.class);
			final JMethod bdm = bic.getBeanInfoClass().method(JMod.PUBLIC, bdType, "getBeanDescriptor");
			bdm.annotate(bic.getCodeModel().ref(Override.class));
			
			JDocComment bdmDoc = bdm.javadoc();
			bdmDoc.append("Get a {@code BeanDescriptor} providing overall information about the bean,");
			bdmDoc.append("\nsuch as its displayName, its customizer, etc.");
			bdmDoc.addReturn().append("A {@code BeanDescriptor} providing overall information about the bean.");
			
			final JBlock bdmBody = bdm.body();
			final JInvocation bdExp = JExpr._new(bic.getCodeModel().ref(BeanDescriptor.class)).arg(bic.getImplClass().dotclass());
			final JVar bdVar = bdmBody.decl(JMod.NONE, bic.getCodeModel().ref(BeanDescriptor.class), "bd", bdExp);
			
			if ( bd.getName() != null )
				bdmBody.add(bdVar.invoke("setName").arg(bd.getName()));
			if ( bd.getDisplayName() != null )
				bdmBody.add(bdVar.invoke("setDisplayName").arg(bd.getDisplayName()));
			if ( bd.getDescription() != null )
				bdmBody.add(bdVar.invoke("setShortDescription").arg(bd.getDescription()));
			if ( bd.isExpert() != null )
				bdmBody.add(bdVar.invoke("setExpert").arg(JExpr.lit(bd.isExpert())));
			if ( bd.isHidden() != null )
				bdmBody.add(bdVar.invoke("setHidden").arg(JExpr.lit(bd.isHidden())));
			if ( bd.isPreferred() != null )
				bdmBody.add(bdVar.invoke("setPreferred").arg(JExpr.lit(bd.isPreferred())));
			
			bdmBody._return(bdVar);
			
			trace("{}, generateBeanDescriptor; Class={}, Bean={}",
				toLocation(bic.getBeanCustomization().locator),
				bic.getBeanInfoClass().name(),
				bd);
		}
	}

	/* Generate getPropertyDescriptors list */
	private void generatePropertyDescriptors(BeanInfoCustomization bic)
	{	
		final List<Property> properties = new ArrayList<>();
		for ( Entry<CPropertyInfo, CPluginCustomization> entry : bic.getPropertyCustomizationMap().entrySet())
		{
			CPluginCustomization propertyCustomization = entry.getValue();
			if (propertyCustomization != null)
			{
				final Property property =
					(Property) unmarshall(Customizations.getContext(), propertyCustomization);
				
				if ( property != null )
				{
					if ( property.getName() == null )
						property.setName(entry.getKey().getName(false));
					properties.add(property);

					trace("{}, generatePropertyDescriptors; Class={}, Property={}",
						toLocation(propertyCustomization.locator),
						bic.getBeanInfoClass().name(),
						property.getName());
				}
			}
		}
		
		if ( !properties.isEmpty() )
			generatePropertyDescriptors(bic, properties);
	}

	/* Generate getPropertyDescriptors accessor with custom settings. */
	private void generatePropertyDescriptors(BeanInfoCustomization bic, List<Property> properties)
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
		
		JTryBlock pdmTryBlock = pdmBody._try();
		pdmTryBlock._catch(codeModel.ref(IntrospectionException.class));
		JBlock pdmTryBlockBody = pdmTryBlock.body();
		
		for ( Property property : properties )
		{
			JBlock pdmBlock = pdmTryBlockBody.block();
			
			JInvocation fdExp = JExpr._new(codeModel.ref(FieldDescriptor.class))
				.arg(property.getName())
				.arg(bic.getImplClass().dotclass());
			JVar fdVar = pdmBlock.decl(JMod.NONE, codeModel.ref(FieldDescriptor.class), "fd", fdExp);
			
			// FeatureDescriptor settings, set by construction (above)
			// if ( property.getName() != null )
			//     pdmBlock.add(fdVar.invoke("setName").arg(property.getName()));

			// FeatureDescriptor settings
			if ( property.getDisplayName() != null )
				pdmBlock.add(fdVar.invoke("setDisplayName").arg(property.getDisplayName()));
			if ( property.getDescription() != null )
				pdmBlock.add(fdVar.invoke("setShortDescription").arg(property.getDescription()));
			if ( property.isExpert() != null )
				pdmBlock.add(fdVar.invoke("setExpert").arg(JExpr.lit(property.isExpert())));
			if ( property.isHidden() != null )
				pdmBlock.add(fdVar.invoke("setHidden").arg(JExpr.lit(property.isHidden())));
			if ( property.isPreferred() != null )
				pdmBlock.add(fdVar.invoke("setPreferred").arg(JExpr.lit(property.isPreferred())));
			
			// PropertyDescriptor settings
			if ( property.isBound() != null )
				pdmBlock.add(fdVar.invoke("setBound").arg(JExpr.lit(property.isBound())));
			if ( property.isConstrained() != null )
				pdmBlock.add(fdVar.invoke("setConstrained").arg(JExpr.lit(property.isConstrained())));
			if ( property.getEditorClass() != null )
				pdmBlock.add(fdVar.invoke("setPropertyEditorClass").arg(JExpr.lit(property.getEditorClass())));
			
			// FieldDescriptor settings
			if ( property.getAlignment() != null )
				pdmBlock.add(fdVar.invoke("setAlignment").arg(JExpr.lit(property.getAlignment().name())));
			if ( property.isEditable() != null )
				pdmBlock.add(fdVar.invoke("setEditable").arg(JExpr.lit(property.isEditable())));
			if ( property.getIndex() != null )
				pdmBlock.add(fdVar.invoke("setIndex").arg(JExpr.lit(property.getIndex())));
			if ( property.getMaxWidth() != null )
				pdmBlock.add(fdVar.invoke("setMaxWidth").arg(JExpr.lit(property.getMaxWidth())));
			if ( property.getMinWidth() != null )
				pdmBlock.add(fdVar.invoke("setMinWidth").arg(JExpr.lit(property.getMinWidth())));
			if ( property.getPreferredWidth() != null )
				pdmBlock.add(fdVar.invoke("setPreferredWidth").arg(JExpr.lit(property.getPreferredWidth())));
			if ( property.getRendererClass() != null )
				pdmBlock.add(fdVar.invoke("setRendererClass").arg(JExpr.lit(property.getRendererClass())));
			if ( property.isResizable() != null )
				pdmBlock.add(fdVar.invoke("setResizable").arg(JExpr.lit(property.isResizable())));
			
			pdmBlock.add(fdListVar.invoke("add").arg(fdVar));
		}
		
		JClass fdClass = codeModel.ref(FieldDescriptor.class);
		JInvocation fdListSize = fdListVar.invoke("size");
		JArray fdArrayExp = JExpr.newArray(fdClass, fdListSize);
		JInvocation fdListToArray = fdListVar.invoke("toArray").arg(fdArrayExp);
		pdmBody._return(fdListToArray);
	}
}
