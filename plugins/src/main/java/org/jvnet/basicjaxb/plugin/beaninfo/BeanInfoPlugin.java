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
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static org.jvnet.basicjaxb.lang.FieldDescriptor.DEFAULT_ALIGNMENT;
import static org.jvnet.basicjaxb.lang.FieldDescriptor.DEFAULT_MIN_WIDTH;
import static org.jvnet.basicjaxb.lang.FieldDescriptor.alignByType;
import static org.jvnet.basicjaxb.lang.FieldDescriptor.widthByType;
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
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.lang.Alignment;
import org.jvnet.basicjaxb.lang.FieldDescriptor;
import org.jvnet.basicjaxb.lang.Width;
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
import com.sun.xml.xsom.XSFacet;
import com.sun.xml.xsom.XSType;

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
			generateFieldDescriptors(bic);
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
			bdmDoc.append("\nsuch as its displayName, its description, etc.");
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
				bdmBody.add(bdVar.invoke("setExpert").arg(lit(bd.isExpert())));
			if ( bd.isHidden() != null )
				bdmBody.add(bdVar.invoke("setHidden").arg(lit(bd.isHidden())));
			if ( bd.isPreferred() != null )
				bdmBody.add(bdVar.invoke("setPreferred").arg(lit(bd.isPreferred())));
			
			bdmBody._return(bdVar);
			
			trace("{}, generateBeanDescriptor; Class={}, Bean={}",
				toLocation(bic.getBeanCustomization().locator),
				bic.getBeanInfoClass().name(),
				bd);
		}
	}

	/* Generate getPropertyDescriptors list */
	private void generateFieldDescriptors(BeanInfoCustomization bic)
	{
		final Map<String, FieldInfo> fieldInfoMap = new TreeMap<>();
		
		// Properties with Customizations
		for ( Entry<CPropertyInfo, CPluginCustomization> entry : bic.getPropertyCustomizationMap().entrySet())
		{
			CPropertyInfo propertyInfo = entry.getKey();

			FieldInfo fieldInfo = new FieldInfo(propertyInfo);
			fieldInfo.setFieldType(bic.getType(propertyInfo));
			fieldInfo.setFacets(bic.getPropertyFacetMap().get(propertyInfo));
			
			CPluginCustomization propertyCustomization = entry.getValue();
			if (propertyCustomization != null)
			{
				final Property property =
					(Property) unmarshall(Customizations.getContext(), propertyCustomization);
				
				if ( property != null )
				{
					if ( property.getName() == null )
						property.setName(fieldInfo.getFieldName());
					if ( property.getDisplayName() == null )
						property.setDisplayName(fieldInfo.getFieldDisplayName());
					fieldInfo.setProperty(property);

					trace("{}, generateFieldDescriptors; Class={}, Property={}",
						toLocation(propertyCustomization.locator),
						bic.getBeanInfoClass().name(),
						property.getName());
				}
			}
			
			if ( fieldInfo.getProperty() != null )
				fieldInfoMap.put(fieldInfo.getFieldDisplayName(), fieldInfo);
		}
		
		// Properties with Facets
		for ( Entry<CPropertyInfo, List<XSFacet>> entry: bic.getPropertyFacetMap().entrySet() )
		{
			CPropertyInfo propertyInfo = entry.getKey();
			String propertyInfoName = propertyInfo.getName(false);
			
			if ( !fieldInfoMap.containsKey(propertyInfoName) )
			{
				FieldInfo fieldInfo = new FieldInfo(propertyInfo);
				fieldInfo.setFieldType(bic.getType(propertyInfo));
				fieldInfo.setFacets(bic.getPropertyFacetMap().get(propertyInfo));
				fieldInfoMap.put(propertyInfoName, fieldInfo);
			}
		}
		
		// Properties without Customizations and without Facets
		for ( CPropertyInfo propertyInfo : bic.getTargetProperties() )
		{
			if
			(
				!bic.getPropertyCustomizationMap().containsKey(propertyInfo) &&
				!bic.getPropertyFacetMap().containsKey(propertyInfo)
			)
			{
				FieldInfo fieldInfo = new FieldInfo(propertyInfo);
				fieldInfo.setFieldType(bic.getType(propertyInfo));
				fieldInfoMap.put(fieldInfo.getFieldDisplayName(), fieldInfo);
			}
		}
		
		if ( !fieldInfoMap.isEmpty() )
		{
			// Order by index or alphabetically.
			TreeMap<Integer, FieldInfo> fiTreeMap1 = new TreeMap<>();
			TreeMap<String, FieldInfo> fiTreeMap2 = new TreeMap<>();
			
			// Index by given index or cache until later
			for ( FieldInfo fieldInfo : fieldInfoMap.values() )
			{
				Integer fiIndex = bic.getPropertyIndexMap().get(fieldInfo.getFieldName());
				if ( fiIndex != null )
					fiTreeMap1.put(fiIndex, fieldInfo);
				else
					fiTreeMap2.put(fieldInfo.getFieldDisplayName(), fieldInfo);
			}
			
			// Index cached FieldInfo(s) alphabetically by display name.
			if ( !fiTreeMap2.isEmpty() )
			{
				Integer index = fiTreeMap1.isEmpty() ? 0 : fiTreeMap1.lastKey();
				for ( FieldInfo fi2 : fiTreeMap2.values() )
					fiTreeMap1.put(++index, fi2);
			}
			
			// Re-index, in sequence without gaps, zero-based.
			// Note: The fiTreeMap1's value iterator returns the values in
			//       ascending order of the corresponding keys.
			List<FieldInfo> fiList = new ArrayList<>(fiTreeMap1.values());
			for ( int index = 0; index < fiList.size(); ++index )
				fiList.get(index).setFieldIndex(index);
			
			generatePropertyDescriptors(bic, fiList);
		}
	}

	/* Generate getPropertyDescriptors accessor with custom settings. */
	private void generatePropertyDescriptors(BeanInfoCustomization bic, List<FieldInfo> fieldInfos)
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
		
		for ( FieldInfo fieldInfo : fieldInfos )
		{
			// Create a block and initialize a FieldDescriptor variable.
			JBlock pdmBlock = pdmTryBlockBody.block();
			JInvocation fdExp = JExpr._new(codeModel.ref(FieldDescriptor.class))
				.arg(fieldInfo.getFieldName())
				.arg(bic.getImplClass().dotclass());
			JVar fdVar = pdmBlock.decl(JMod.NONE, codeModel.ref(FieldDescriptor.class), "fd", fdExp);
			
			// FieldDescriptor settings (Index)
			if ( fieldInfo.getFieldIndex() != null )
			{
				Integer index = fieldInfo.getFieldIndex();
				Property fip = fieldInfo.getProperty();
				if ( (fip != null) && (fip.getIndex() != null) )
					index = fip.getIndex();
				if ( index != null )
					pdmBlock.add(fdVar.invoke("setIndex").arg(lit(index)));
			}
			
			// FieldDescriptor settings (Property)
			generatePropertySetters(fieldInfo, pdmBlock, fdVar);

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
	private void generatePropertySetters(FieldInfo fieldInfo, JBlock pdmBlock, JVar fdVar)
	{
		boolean isHidden = false;
		Integer minWidth = null;
		String alignmentName = null;
		
		XSType fiType = fieldInfo.getFieldType();
		if ( fiType != null )
		{
			if ( fiType.isSimpleType() )
			{
				QName typeName = fieldInfo.getFieldTypeName();
				minWidth = widthByType(typeName).getMin();
				alignmentName = alignByType(typeName).name();
			}
			else
				isHidden = true;
			
			if ( minWidth == null )
				minWidth = DEFAULT_MIN_WIDTH;
			if ( alignmentName == null )
				alignmentName = DEFAULT_ALIGNMENT.name();
		}
		
		Property fiProperty = fieldInfo.getProperty();
		if ( fiProperty == null )
			fiProperty = new Property();
		
		if ( fiProperty != null )
		{
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
			else if ( isHidden )
				pdmBlock.add(fdVar.invoke("setHidden").arg(lit(isHidden)));
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
			if ( fiProperty.getAlignment() != null )
				pdmBlock.add(fdVar.invoke("setAlignment").arg(lit(fiProperty.getAlignment().name())));
			else if ( alignmentName != null )
				pdmBlock.add(fdVar.invoke("setAlignment").arg(lit(alignmentName)));
			if ( fiProperty.isEditable() != null )
				pdmBlock.add(fdVar.invoke("setEditable").arg(lit(fiProperty.isEditable())));
			if ( fiProperty.getMaxWidth() != null )
				pdmBlock.add(fdVar.invoke("setMaxWidth").arg(lit(fiProperty.getMaxWidth())));
			if ( fiProperty.getMinWidth() != null )
				pdmBlock.add(fdVar.invoke("setMinWidth").arg(lit(fiProperty.getMinWidth())));
			else
				pdmBlock.add(fdVar.invoke("setMinWidth").arg(lit(minWidth)));
			if ( fiProperty.getPreferredWidth() != null )
				pdmBlock.add(fdVar.invoke("setPreferredWidth").arg(lit(fiProperty.getPreferredWidth())));
			if ( fiProperty.getRendererClass() != null )
				pdmBlock.add(fdVar.invoke("setRendererClass").arg(lit(fiProperty.getRendererClass())));
			if ( fiProperty.isResizable() != null )
				pdmBlock.add(fdVar.invoke("setResizable").arg(lit(fiProperty.isResizable())));
		}
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
