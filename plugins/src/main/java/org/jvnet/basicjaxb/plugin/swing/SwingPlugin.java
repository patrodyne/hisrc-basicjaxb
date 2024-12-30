package org.jvnet.basicjaxb.plugin.swing;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.swing.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.util.OutlineUtils.filter;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.config.LocatorUnmarshaller;
import org.jvnet.basicjaxb.lang.ValueUtils;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.plugin.beaninfo.BeanInfoCustomization;
import org.jvnet.basicjaxb.plugin.beaninfo.BeanInfoCustomizationFactory;
import org.jvnet.basicjaxb.plugin.beaninfo.FieldInfo;
import org.swixml.SwingEngine;
import org.swixml.schema.model.JPanel;
import org.swixml.schema.model.JTableBind;
import org.swixml.schema.model.JTextAreaBind;
import org.swixml.schema.model.JTreeBind;
import org.swixml.schema.model.ObjectFactory;
import org.swixml.schema.model.Window;
import org.swixml.schema.model.XScrollPane;
import org.swixml.schema.model.XSplitPane;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
// Post process Model to convert any fixed property element names to 'constant' names
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JType;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.CAttributePropertyInfo;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CNonElement;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.xml.xsom.XSAttributeUse;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSTerm;
import com.sun.xml.xsom.XSType;
import com.sun.xml.xsom.XmlString;

import jakarta.xml.bind.JAXBException;

/**
 * An XJC plugin to generate SwiXML files for a Swing GUI
 */
public class SwingPlugin extends AbstractParameterizablePlugin
{
	/* Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xswing";
	
	/* Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generate SwiXML files to render a Swing GUI";

	/* Represents the arguments of a method without parameters. */
	private static final JType[] NO_ARGS = new JType[0];
	
	/* Represents the SWIXML schema model. */
	private static final ObjectFactory OF = new ObjectFactory();
	
	/** Creates a new <code>SwingPlugin</code> instance. */
	public SwingPlugin()
	{
	}

	/** SwingPlugin uses "-" + OPTION_NAME as the XJC argument */
	@Override
	public String getOptionName()
	{
		return OPTION_NAME;
	}

	/** Return usage information for plugin */
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
	
	private String source;
	public String getSource()
	{
		if ( source == null )
			setSource("classpath:/DefaultWindow.xml");
		return source;
	}
	public void setSource(String source) { this.source = source; }

	private String target;
	public String getTarget() { return target; }
	public void setTarget(String target) { this.target = target; }
	
	private File targetDir;
	public File getTargetDir() { return targetDir; }
	public void setTargetDir(File targetDir) { this.targetDir = targetDir; }
	
	// Plugin Processing

	@Override
	protected void init(Options options) throws Exception
	{
		super.init(options);
		setTargetDir(options.targetDir);
	}

	@Override
	protected void beforePostProcessModel(Model model)
	{

		if ( isDebugEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Source....: " + getSource());
			sb.append("\n  Target....: " + getTarget());
			sb.append("\n  TargetDir.: " + getTargetDir());
			sb.append("\n  Verbose...: " + isVerbose());
			sb.append("\n  Debug.....: " + isDebug());
			debug(sb.toString());
		}
	}
	
	@Override
	protected void afterPostProcessModel(Model model, ErrorHandler errorHandler)
	{
		if ( isDebugEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_FINISH);
			sb.append("\nResults");
			sb.append("\n  HadError.: " + hadError(errorHandler));
			debug(sb.toString());
		}
	}

    /**
     * Performs the post-processing of the {@link Model}.
     *
     * <p>
     * This method is invoked after XJC has internally finished
     * the model construction. This is a chance for a plugin to
     * affect the way code generation is performed.
     * </p>
     *
     * <p>
     * Compared to the {@link #run(Outline, Options, ErrorHandler)}
     * method, this method allows a plugin to work at the higher level
     * conceptually closer to the abstract JAXB model, as opposed to
     * Java syntax level.
     * </p>
     *
     * <p>
     * This 'postProcessModel' method is a call-back method from
     * {@link AbstractPlugin} and that method is responsible for handling
     * all exceptions. It reports any exception to {@link ErrorHandler}
     * for processing by {@link com.sun.tools.xjc.Plugin}.
     * </p>
     *
     * <p>
     * <b>Note:</b> This method is invoked only when a plugin is activated.
     * </p>
     *
     * @param model
     *      The object that represents the classes/properties to
     *      be generated.
     */
	@Override
	protected void postProcessModel(Model model)
	{
		for (final CClassInfo classInfo : model.beans().values())
		{
			if ( !getIgnoring().isIgnored(classInfo) )
				postProcessClassInfo(model, classInfo);
		}
	}

	private void postProcessClassInfo(Model model, CClassInfo classInfo)
	{
		for (CPropertyInfo propertyInfo : classInfo.getProperties())
		{
			if ( !getIgnoring().isIgnored(propertyInfo) )
				postProcessPropertyInfo(model, classInfo, propertyInfo);
		}
	}

	// Post process Model to convert any fixed property element names to
	// 'constant' names
	private void postProcessPropertyInfo(Model model, CClassInfo classInfo, CPropertyInfo propertyInfo)
	{
	}
	
	@Override
	protected void beforeRun(Outline outline) throws Exception
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Source....: " + getSource());
			sb.append("\n  Target....: " + getTarget());
			sb.append("\n  TargetDir.: " + getTargetDir());
			sb.append("\n  Verbose...: " + isVerbose());
			sb.append("\n  Debug.....: " + isDebug());
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
	 * Run the plugin. We perform the following steps:
	 * </p>
	 *
	 * <ul>
	 *   <li>Look for qualifying fields, fields qualify that:
	 *     <ul>
	 *       <li>Are generated from XSD description</li>
	 *       <li>The XSD description is of type <code>xsd:element</code>
	 *           (code level fixed values are not necessary for fields 
	 *           generated from attributes)</li>
	 *       <li>A fixed value is specified</li>
	 *       <li>Map to one of the supported types</li>
	 *     </ul>
	 *   </li>
	 *   <li>Add a new initialization expression to every qualifying BOUND or field:
	 *     <ul>
	 *       <li>An element BOUND qualifies when the field is nullable;</li>
	 *       <li>Otherwise, the field qualifies to receive the initialization expression</li>
	 *     </ul>
	 *   </li>
	 *   
	 * </ul>
	 * 
     * <p>
     * Note that this method is invoked only when a plugin is activated.
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
     *      After an error is reported to {@link ErrorHandler}, a
     *      {@link SAXException} may be thrown to indicate a fatal unrecoverable
     *      error. {@link ErrorHandler} itself may throw it, if it chooses
     *      not to recover from the error.
	 */
	@Override
	public boolean run(Outline outline) throws Exception
	{
		try
		{
			// Set the JAXB context path.
			setContextPath(Window.class.getPackageName());
			// Create an unmarshaller to parse the source SWIXML configuration.
			LocatorUnmarshaller<Window> windowUnmarshaller =
				new LocatorUnmarshaller<>(getUnmarshaller());
			// Unmarshal the source SWIXML configuration.
			Window window = windowUnmarshaller.unmarshal(getSource(), Window.class);
			
			// Process the XJC Outline and Swing (Model) Window instances.
			processWindow(outline, window);
			
			// Marshal the enriched SWIXML configuration to the target location.
			File targetFile = new File(getTargetDir().getPath(), getTarget());
			targetFile.getParentFile().mkdirs();
			getMarshaller().marshal(window, targetFile);
		}
		catch (IOException | JAXBException ex)
		{
			error("Cannot run swing plugin: ", ex);
		}
		
		return !hadError(outline.getErrorReceiver());
	}
	
	/**
	 * Process the XJC {@link Outline} instance for the given
	 * {@link Window} instance. The goal is to configure the
	 * {@link Window} to view/edit all non-ignored/hidden fields.
	 * 
	 * <p>The {@link Window} was marshaled from a XML file that can be
	 * provided to a SWIXML {@link SwingEngine} to render a GUI.</p>
	 * 
	 * @param outline An outline from the XJC framework.
	 * @param window The SWIXML {@code Window} to enrich.
	 * 
	 * @throws IntrospectionException cannot map class name to a class object.
	 * @throws ClassNotFoundException no definition for the class with the specified name could be found.
	 */
	protected void processWindow(Outline outline, Window window)
		throws IntrospectionException, ClassNotFoundException
	{
		XSplitPane mainSplitPane = OF.createXSplitPane();
		mainSplitPane.setName("Main: [Tree|Work]");
		mainSplitPane.setOrientation("HORIZONTAL_SPLIT");
		mainSplitPane.setOneTouchExpandable(true);
		mainSplitPane.setSize("${el.size()}");
		mainSplitPane.setDividerLocation("0.20");
		window.getContent().add(OF.createSplitpane(mainSplitPane));
		
		JTreeBind treeBind = OF.createJTreeBind();
		treeBind.setId("mainTree");
		treeBind.setModel("${window.mainTreeModel}");
		treeBind.setCellRenderer("${window.mainTreeCellRenderer}");
		treeBind.setAction("selectNode");
		treeBind.setBackground("*-20-100");
		
		XScrollPane treePane = OF.createXScrollPane();
		treePane.getContent().add(OF.createTree(treeBind));
		
		JPanel cardLayoutPanel = OF.createJPanel();
		cardLayoutPanel.setId("mainPanel");
		cardLayoutPanel.setLayout("CardLayout");
		
		XSplitPane siftSplitPane = OF.createXSplitPane();
		siftSplitPane.setName("Sift: [Query|Result]");
		siftSplitPane.setOrientation("HORIZONTAL_SPLIT");
		siftSplitPane.setOneTouchExpandable(true);
		
		JTextAreaBind queryTextArea = OF.createJTextAreaBind();
		queryTextArea.setBackground("*-10-100");
		JTextAreaBind resultTextArea = OF.createJTextAreaBind();
		resultTextArea.setBackground("*-10-100");
		
		siftSplitPane.getContent().add(OF.createTextarea(queryTextArea));
		siftSplitPane.getContent().add(OF.createTextarea(resultTextArea));
		
		XSplitPane workSplitPane = OF.createXSplitPane();
		workSplitPane.setName("Work: [Query|Result]/Cards");
		workSplitPane.setOrientation("VERTICAL_SPLIT");
		workSplitPane.setOneTouchExpandable(true);
		
		workSplitPane.getContent().add(OF.createSplitpane(siftSplitPane));
		workSplitPane.getContent().add(OF.createPanel(cardLayoutPanel));

		mainSplitPane.getContent().add(OF.createScrollpane(treePane));
		mainSplitPane.getContent().add(OF.createSplitpane(workSplitPane));
		
		BeanInfoCustomizationFactory bicf = new BeanInfoCustomizationFactory(outline);

		// Filter ignored class outlines
		for (final ClassOutline classOutline : filter(outline, getIgnoring()))
			processClassOutline(bicf, classOutline, cardLayoutPanel);
	}
	
	/**
	 * Process the XJC {@link Outline} instance for the given
	 * {@link Window} instance. The goal is to configure the
	 * {@link Window} to view/edit all non-ignored/hidden fields
	 * for the given {@link ClassOutline} instance.
	 * 
	 * @param bicf A factory to create {@link BeanInfoCustomization} instances.
	 * @param classOutline A class outline from the XJC framework.
	 * @param cardLayoutPanel The card layout panel to contain the card tables.
	 */
	protected void processClassOutline(BeanInfoCustomizationFactory bicf, ClassOutline classOutline, JPanel cardLayoutPanel)
	{
		// Filter an array of {@link FieldOutline} to omit ignored or constant fields.
		FieldOutline[] declaredFilteredFields = filter(classOutline.getDeclaredFields(), getIgnoring());
		if ( declaredFilteredFields.length > 0 )
		{
			// Collect all bean and property CPluginCustomization instances in this
			// plugin's Customizations namespace.
			BeanInfoCustomization bic = bicf.createBeanInfoCustomization(classOutline);
			
			// Prepare a declared field name set
			Set<String> declaredFieldNameSet = new HashSet<>();
			for ( FieldOutline dff : declaredFilteredFields )
			{
				CPropertyInfo pi = dff.getPropertyInfo();
				declaredFieldNameSet.add(pi.getName(false));
			}
			
			// Count visible fields
			int fieldCount = 0;
			for (FieldInfo fieldInfo : bic.getFieldInfoList() )
			{
				String fiFieldName = fieldInfo.getFieldName();
				if ( !declaredFieldNameSet.contains(fiFieldName) )
					fieldInfo.setFieldHidden(true);
				if ( !fieldInfo.isFieldHidden() )
					++fieldCount;
			}
			
			// Are there any visible fields?
			if ( fieldCount > 0 )
			{
				// Format the EL for the current bindings.
				String bindClass = format("${window.%sClass}", bic.getClassPrivateName());
				String bindList = format("${window.%s}", bic.getClassListName());
				
				// Create table for current bind class.
				JTableBind table = OF.createJTableBind();
//				table.setBindClass(bic.getClassFullName());
				table.setBindClass(bindClass);
				table.setBindList(bindList);
				table.setAutoCreateColumnsFromModel(true);
				table.setAutoResizeMode("AUTO_RESIZE_OFF");
				table.setFont("Monospaced");
				table.setBackground("*-10-100");
				
				// Add the table to a new scroll pane.
				XScrollPane scrollPane = OF.createXScrollPane();
				scrollPane.setConstraints(bic.getClassFullName());
				scrollPane.getContent().add(OF.createTable(table));
				
				// Add the scroll pane to the card layout panel.
				cardLayoutPanel.getContent().add(OF.createScrollpane(scrollPane));
			}
		}
		
		boolean noop = false;
		if ( noop )
		{
			// Check all Fields in Class
			for (FieldOutline fieldOutline : declaredFilteredFields)
			{
				// Handle primitive types via boxed representation (treat boolean as java.lang.Boolean)
				JType fieldRawType = fieldOutline.getRawType();
				boolean fieldIsPrimitive = fieldRawType.isPrimitive();
				JType fieldType = (fieldIsPrimitive) ? fieldRawType.boxify() : fieldRawType;
				
				// Get the field type's full name.
				String typeFullName = fieldType.fullName();

				// Represent the XML schema element's or attribute's fixed value.
				XmlString fixedValue = null;
				
				// Do nothing if Field is not created from an or XSAttributeUse an XSParticle
				// Set the fixedValue ONLY when this plugin needs to provide the initialization.
				CPropertyInfo fieldInfo = fieldOutline.getPropertyInfo();
				QName schemaType = fieldInfo.getSchemaType();
				if ( fieldInfo.getSchemaComponent() instanceof XSAttributeUse )
				{
					// An XSAttributeUse provides isRequired, defaultValue and fixedValue for an XSAttributeDecl.
					XSAttributeUse attribute = (XSAttributeUse) fieldInfo.getSchemaComponent();
					if (attribute.getFixedValue() != null)
					{
						// Get the fixed value from the XSD attribute as a String.
						fixedValue = attribute.getFixedValue();
						if ( schemaType == null )
						{
							if ( fieldInfo instanceof CAttributePropertyInfo)
							{
								CAttributePropertyInfo attrInfo = (CAttributePropertyInfo) fieldInfo;
								CNonElement nonElement = attrInfo.getTarget();
								schemaType = nonElement.getTypeName();
							}
						}
					}
				}
				else if ( fieldInfo.getSchemaComponent() instanceof XSParticle )
				{
					// An XSParticle provides min/max occurs cardinality for an XSTerm.
					XSParticle particle = (XSParticle) fieldInfo.getSchemaComponent();
					
					// Fixed values only necessary for fields derived from an xsd:element
					XSTerm term = particle.getTerm();
					if (term.isElementDecl())
					{
						// Do nothing if no fixed value.
						// Continue loop to next FieldOutline instance.
						XSElementDecl element = term.asElementDecl();
						if (element.getFixedValue() != null)
						{
							// Get the fixed value from the XSD element as a String.
							fixedValue = element.getFixedValue();
							if ( schemaType == null )
							{
								XSType elementType = element.getType();
								if ( (elementType != null) && (elementType.getName() != null) )
									schemaType = new QName(elementType.getTargetNamespace(), elementType.getName());
							}
						}
					}
				}
				
				// Provide initialization for the fixed value, when non-null.
				if ( fixedValue != null )
					processSwing(bicf.getOutline(), classOutline, fieldOutline, fieldType, fieldIsPrimitive, typeFullName, fixedValue, schemaType);
			} // for FieldOutline
		}
	}

	private void processSwing(Outline outline, ClassOutline classOutline,
		FieldOutline fieldOutline, JType fieldType, boolean fieldIsPrimitive,
		String typeFullName, XmlString fixedValue, QName schemaType)
	{
		// Get handle to implementation class containing the field
		JDefinedClass theClass = classOutline.implClass;
		
		// Get the property info from the outline.
		CPropertyInfo fieldInfo = fieldOutline.getPropertyInfo();
		
		// Get the field variable for the given fieldInfo private name
		// from the map of fields for theClass.
		Map<String, JFieldVar> fields = theClass.fields();
		JFieldVar fieldVar = fields.get(fieldInfo.getName(false));
		
		// Get the BOUND for the given fieldInfo public name
		// from the list of methods for theClass.
		String publicName = fieldInfo.getName(true);
		
		// Collect the property methods bound to the field variable.
		List<JMethod> propMethods = collectPropertyMethods(theClass, publicName, fieldOutline);
		
		// Get handle to JCodeModel owning this class.
		JCodeModel theCodeModel = theClass.owner();
		
		// Get reference to ValueUtils to invoke its static methods.
		JClass refValueUtils = theCodeModel.ref(ValueUtils.class);
		
		String fieldLoc = toLocation(fieldInfo.getLocator());
		String fieldName = fieldInfo.displayName();
	}

	private List<JMethod> collectPropertyMethods(JDefinedClass theClass, String publicName, FieldOutline fieldOutline)
	{
		List<JMethod> propMethods = new ArrayList<>();
		
		JMethod accessor = theClass.getMethod("get" + publicName, NO_ARGS);
		if ( accessor == null )
			accessor = theClass.getMethod("is" + publicName, NO_ARGS);
		if ( accessor != null )
			propMethods.add(accessor);
		
		String mutatorName = "set" + publicName;
		JType mutatorType = fieldOutline.getRawType();
        JMethod boxifiedMutator = theClass.getMethod(mutatorName, new JType[] { mutatorType.boxify() });
        JMethod unboxifiedMutator = theClass.getMethod(mutatorName, new JType[] { mutatorType.unboxify() });
        JMethod mutator = boxifiedMutator != null ? boxifiedMutator : unboxifiedMutator;
		if ( mutator != null )
			propMethods.add(mutator);        

		JMethod detector = theClass.getMethod("isSet" + publicName, NO_ARGS);
		if ( detector != null )
			propMethods.add(detector);
		
		JMethod reverter = theClass.getMethod("unset" + publicName, NO_ARGS);
		if ( reverter != null )
			propMethods.add(reverter);
		
		return propMethods;
	}
}
