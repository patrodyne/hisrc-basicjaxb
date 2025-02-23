package org.jvnet.basicjaxb.plugin.swing;

import static com.sun.codemodel.JExpr._null;
import static com.sun.codemodel.JExpr._super;
import static com.sun.codemodel.JExpr.cast;
import static com.sun.codemodel.JExpr.invoke;
import static com.sun.codemodel.JExpr.lit;
import static com.sun.codemodel.JMod.FINAL;
import static com.sun.codemodel.JMod.PRIVATE;
import static com.sun.codemodel.JMod.PUBLIC;
import static com.sun.codemodel.JMod.STATIC;
import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static org.jvnet.basicjaxb.plugin.swing.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.util.OutlineUtils.filter;

import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.config.LocatorUnmarshaller;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.plugin.beaninfo.BeanInfoCustomization;
import org.jvnet.basicjaxb.plugin.beaninfo.BeanInfoCustomizationFactory;
import org.jvnet.basicjaxb.plugin.beaninfo.FieldInfo;
import org.swixml.SwingEngine;
import org.swixml.jsr.widgets.CardNodeInfo;
import org.swixml.jsr.widgets.MutableTreeModel;
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

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCast;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JType;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.outline.PackageOutline;
import com.sun.xml.xsom.XSType;

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
	
//	private String beanInfoPackage;
//	public String getBeanInfoPackage() { return beanInfoPackage; }
//	public void setBeanInfoPackage(String beanInfoPackage) { this.beanInfoPackage = beanInfoPackage; }

	private String sourceWindow;
	public String getSourceWindow()
	{
		if ( sourceWindow == null )
			setSourceWindow("classpath:/SchemasWindow.xml");
		return sourceWindow;
	}
	public void setSourceWindow(String sourceWindow) { this.sourceWindow = sourceWindow; }

	private String targetPackage;
	public String getTargetPackage() { return targetPackage; }
	public void setTargetPackage(String targetPackage) { this.targetPackage = targetPackage; }
	
	private File targetDir;
	public File getTargetDir() { return targetDir; }
	public void setTargetDir(File targetDir) { this.targetDir = targetDir; }
	
	private Map<String, List<FieldInfo>> hiddenFieldInfoMap;
	protected Map<String, List<FieldInfo>> getHiddenFieldInfoMap()
	{
		if ( hiddenFieldInfoMap == null )
			setHiddenFieldInfoMap(new HashMap<>());
		return hiddenFieldInfoMap;
	}
	protected void setHiddenFieldInfoMap(Map<String, List<FieldInfo>> hiddenFieldInfoMap)
	{
		this.hiddenFieldInfoMap = hiddenFieldInfoMap;
	}
	
	private Map<String, List<FieldInfo>> visibleFieldInfoMap;
	protected Map<String, List<FieldInfo>> getVisibleFieldInfoMap()
	{
		if ( visibleFieldInfoMap == null )
			setVisibleFieldInfoMap(new HashMap<>());
		return visibleFieldInfoMap;
	}
	protected void setVisibleFieldInfoMap(Map<String, List<FieldInfo>> visibleFieldInfoMap)
	{
		this.visibleFieldInfoMap = visibleFieldInfoMap;
	}

	private JExpression litSerialVersionUID;
	protected JExpression getLitSerialVersionUID()
	{
		if ( litSerialVersionUID == null )
		{
			Calendar cal = Calendar.getInstance();
			long sv = parseLong(format("%04d%02d%02d", cal.get(YEAR), cal.get(MONTH)+1, cal.get(DAY_OF_MONTH)));
			setLitSerialVersionUID(lit(sv));
		}
		return litSerialVersionUID;
	}
	protected void setLitSerialVersionUID(JExpression litSerialVersionUID)
	{
		this.litSerialVersionUID = litSerialVersionUID;
	}
	
	private Map<Class<?>,JClass> refClasses;
	protected Map<Class<?>, JClass> getRefClasses()
	{
		if ( refClasses == null )
			setRefClasses(new HashMap<>());
		return refClasses;
	}
	protected void setRefClasses(Map<Class<?>, JClass> refClasses)
	{
		this.refClasses = refClasses;
	}
	
	private void putRefClass(JCodeModel cm, Class<?> cls)
	{
		getRefClasses().put(cls, cm.ref(cls));
	}
	
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
//			sb.append("\n  BeanInfoPackage.: " + getBeanInfoPackage());
			sb.append("\n  SourceWindow....: " + getSourceWindow());
			sb.append("\n  TargetPackage...: " + getTargetPackage());
			sb.append("\n  TargetDir.......: " + getTargetDir());
			sb.append("\n  Verbose.........: " + isVerbose());
			sb.append("\n  Debug...........: " + isDebug());
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
//			sb.append("\n  BeanInfoPackage.: " + getBeanInfoPackage());
			sb.append("\n  SourceWindow....: " + getSourceWindow());
			sb.append("\n  TargetPackage...: " + getTargetPackage());
			sb.append("\n  TargetDir.......: " + getTargetDir());
			sb.append("\n  Verbose.........: " + isVerbose());
			sb.append("\n  Debug...........: " + isDebug());
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
			BeanInfoCustomizationFactory bicf = new BeanInfoCustomizationFactory(outline);
			
			JCodeModel cm = outline.getCodeModel();
			putRefClass(cm, MutableTreeNode.class);
			putRefClass(cm, DefaultMutableTreeNode.class);
			putRefClass(cm, CardNodeInfo.class);
			
			generateWindow(bicf);
			generateTreeModel(bicf);
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
	 * <p>The {@link Window} is marshaled from a XML file that can be
	 * provided to a SWIXML {@link SwingEngine} to render a GUI.</p>
	 * 
	 * @param bicf A factory to create {@link BeanInfoCustomization} instances.
	 * 
	 * @throws IntrospectionException When cannot map class name to a class object.
	 * @throws ClassNotFoundException When no definition for the class with the specified name could be found.
	 * @throws URISyntaxException When a string could not be parsed as a URI reference.
	 */
	public void generateWindow(BeanInfoCustomizationFactory bicf)
		throws JAXBException, IOException, IntrospectionException, ClassNotFoundException, URISyntaxException
	{
		// Set the JAXB context path.
		setContextPath(Window.class.getPackageName());
		// Create an unmarshaller to parse the sourceWindow SWIXML configuration.
		LocatorUnmarshaller<Window> windowUnmarshaller =
			new LocatorUnmarshaller<>(getUnmarshaller());
		// Unmarshal the sourceWindow SWIXML configuration.
		Window window = windowUnmarshaller.unmarshal(getSourceWindow(), Window.class);
		
		// Process the XJC Outline and Swing (Model) Window instances.
		enrichWindow(bicf, window);
		
		// Marshal the enriched SWIXML configuration to the target location.
		File targetFile = targetFile(getTargetDir(), getTargetPackage(), getSourceWindow());
		getMarshaller().marshal(window, targetFile);
	}
	
	/**
	 * Derive an absolute target file for the given target directory, 
	 * target path and source window file location.
	 * 
	 * @param targetDir The XJC target directory.
	 * @param targetPackage A possibly relative target package name.
	 * @param sourceWindow The source window file location. 
	 * 
	 * @return An absolute target file.
	 * 
	 * @throws IOException An I/O exception of some sort has occurred.
	 * @throws URISyntaxException A string could not be parsed as a URI reference.
	 */
	protected File targetFile(File targetDir, String targetPackage, String sourceWindow)
		throws IOException, URISyntaxException
	{
		URL sourceWindowURL = new URI(sourceWindow).toURL();
		File sourceWindowFile = new File(sourceWindowURL.getFile());
		String targetDirPath = targetDir.getPath();
		String targetPathName = targetPackage.replace('.', '/');
		String sourceWindowFileName = sourceWindowFile.getName();
		String targeFullPath =
			targetDirPath + "/" + targetPathName + "/" + sourceWindowFileName;
		File targetFile = new File(targeFullPath);
		targetFile.getParentFile().mkdirs();
		return targetFile;
	}
	
	/**
	 * Enrich the XJC {@link Outline} instance for the given
	 * {@link Window} instance. The goal is to configure the
	 * {@link Window} to view/edit all non-ignored/hidden fields.
	 * 
	 * <p>The {@link Window} was marshaled from a XML file that can be
	 * provided to a SWIXML {@link SwingEngine} to render a GUI.</p>
	 * 
	 * @param bicf A factory to create {@link BeanInfoCustomization} instances.
	 * @param window The SWIXML {@code Window} to enrich.
	 * 
	 * @throws IntrospectionException cannot map class name to a class object.
	 * @throws ClassNotFoundException no definition for the class with the specified name could be found.
	 */
	protected void enrichWindow(BeanInfoCustomizationFactory bicf, Window window)
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
		treeBind.setId("schemasTree");
		treeBind.setModel("${window.schemasTreeModel}");
		treeBind.setCellRenderer("${window.schemasTreeCellRenderer}");
		treeBind.setAction("selectNode");
		treeBind.setBackground("*-20-100");
		
		XScrollPane treePane = OF.createXScrollPane();
		treePane.getContent().add(OF.createTree(treeBind));
		
		JPanel cardLayoutPanel = OF.createJPanel();
		cardLayoutPanel.setId("cardPanel");
		cardLayoutPanel.setLayout("CardLayout");
		
		XSplitPane siftSplitPane = OF.createXSplitPane();
		siftSplitPane.setName("Sift: [Query|Result]");
		siftSplitPane.setOrientation("HORIZONTAL_SPLIT");
		siftSplitPane.setOneTouchExpandable(true);
		siftSplitPane.setSize("${el.size()}");
		siftSplitPane.setDividerLocation("0.50");
		
		JTextAreaBind queryTextArea = OF.createJTextAreaBind();
		queryTextArea.setBindWith("query");
		queryTextArea.setBackground("*-10-100");
		
		XScrollPane queryScrollPane = OF.createXScrollPane();
		queryScrollPane.getContent().add(OF.createTextarea(queryTextArea));
		
		JTextAreaBind resultTextArea = OF.createJTextAreaBind();
		resultTextArea.setBindWith("result");
		resultTextArea.setBackground("*-10-100");

		XScrollPane resultScrollPane = OF.createXScrollPane();
		resultScrollPane.getContent().add(OF.createTextarea(resultTextArea));

		siftSplitPane.getContent().add(OF.createScrollpane(queryScrollPane));
		siftSplitPane.getContent().add(OF.createScrollpane(resultScrollPane));
		
		XSplitPane workSplitPane = OF.createXSplitPane();
		workSplitPane.setName("Work: [Query|Result]/Cards");
		workSplitPane.setOrientation("VERTICAL_SPLIT");
		workSplitPane.setOneTouchExpandable(true);
		workSplitPane.setSize("${el.scaleSize(0.80)}");
		workSplitPane.setDividerLocation("0.50");
		
		workSplitPane.getContent().add(OF.createSplitpane(siftSplitPane));
		workSplitPane.getContent().add(OF.createPanel(cardLayoutPanel));

		mainSplitPane.getContent().add(OF.createScrollpane(treePane));
		mainSplitPane.getContent().add(OF.createSplitpane(workSplitPane));
		
		// Filter ignored class outlines
		for (final ClassOutline classOutline : filter(bicf.getOutline(), getIgnoring()))
			processCardClassOutline(bicf, classOutline, cardLayoutPanel);
	}
	
	private class ClassName
	{
		public String pkg, name, full, nameClass, fullClass;
		public ClassName(ClassOutline classOutline)
		{
			JDefinedClass ic = classOutline.getImplClass();
			this.pkg = ic.getPackage().name();
			this.name = ic.name();
			this.full = this.pkg + "." + this.name;
			this.nameClass = this.name + ".class";
			this.fullClass = this.full + ".class";
		}
		public ClassName(String fullName)
		{
			this.full = fullName;
			int dot = fullName.lastIndexOf('.');
			this.pkg = fullName.substring(0, dot);
			this.name = fullName.substring(dot+1);
			this.nameClass = this.name + ".class";
			this.fullClass = this.full + ".class";
		}
	}
//	private String fullClassName(ClassOutline classOutline)
//	{
//		JDefinedClass ic = classOutline.getImplClass();
//		return ic.getPackage().name() + "." + ic.name();
//	}
	
	/**
	 * Process the XJC {@link ClassOutline} instance for the given
	 * {@link JPanel} instance. The goal is to configure the
	 * {@link JPanel} to view/edit all non-ignored/hidden fields
	 * for the given {@link ClassOutline} instance.
	 * 
	 * @param bicf A factory to create {@link BeanInfoCustomization} instances.
	 * @param classOutline A class outline from the XJC framework.
	 * @param cardLayoutPanel The card layout panel to contain the card tables.
	 */
	protected void processCardClassOutline(BeanInfoCustomizationFactory bicf, ClassOutline classOutline, JPanel cardLayoutPanel)
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
				
				ClassName className = new ClassName(classOutline);
				if ( !fieldInfo.isFieldHidden() )
				{
					++fieldCount;
					List<FieldInfo> visibleFieldInfoList = getVisibleFieldInfoMap().get(className.full);
					if ( visibleFieldInfoList == null )
					{
						visibleFieldInfoList = new ArrayList<>();
						getVisibleFieldInfoMap().put(className.full, visibleFieldInfoList);
					}
					visibleFieldInfoList.add(fieldInfo);
				}
				else
				{
					List<FieldInfo> hiddenFieldInfoList = getHiddenFieldInfoMap().get(className.full);
					if ( hiddenFieldInfoList == null )
					{
						hiddenFieldInfoList = new ArrayList<>();
						getHiddenFieldInfoMap().put(className.full, hiddenFieldInfoList);
					}
					hiddenFieldInfoList.add(fieldInfo);
				}
			}
			
			// Are there any visible fields?
			if ( fieldCount > 0 )
			{
				// Format the EL for the current bindings.
				String bindClass = format("${window.%sClass}", bic.getClassPrivateName());
				String bindList = format("${window.%s}", bic.getClassListName());
				
				// Create table for current bind class.
				JTableBind table = OF.createJTableBind();
				table.setBindClass(bindClass);
				table.setBindList(bindList);
				table.setAction("select");
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
				
//				CardNodeInfo cardNodeInfo = new CardNodeInfo(bic.getClassPublicName(), bic.getClassFullName());
//				MutableTreeNode schemasTreeNode = new DefaultMutableTreeNode(cardNodeInfo);
//				Map<String,MutableTreeNode> treeNodeMap = new TreeMap<>();
//				treeNodeMap.put(cardNodeInfo.getCardName(), schemasTreeNode);
			}
		}
	}

	public void generateTreeModel(BeanInfoCustomizationFactory bicf)
		throws JClassAlreadyExistsException
	{
		JCodeModel cm = bicf.getOutline().getCodeModel();
		
		// Generate SchemasTreeModel class.
		String stmClassName = getTargetPackage() + ".SchemasTreeModel";
		JDefinedClass stmClass = cm._class(stmClassName);
		stmClass._extends(MutableTreeModel.class);
		stmClass.field(PRIVATE+STATIC+FINAL, long.class, "serialVersionUID", getLitSerialVersionUID());
		
		// Generate constructor
		JMethod stmCon = stmClass.constructor(PUBLIC);
		JBlock stmConBody = stmCon.body();
		
		// super("Schemas");
		stmConBody.invoke("super").arg("Schemas");
		
		JClass mtnRef = getRefClasses().get(MutableTreeNode.class);
		JCast superRootCast = cast(mtnRef, _super().invoke("getRoot"));
		stmConBody.invoke("setRoot").arg(superRootCast);
		
		Iterable<? extends PackageOutline> apc = bicf.getOutline().getAllPackageContexts();
		for ( PackageOutline pc : apc)
		{
			// Generate tree node for this package.
			String pkgn = pc._package().getPackage().name();
			stmConBody.invoke("push").arg(lit(pkgn));
			stmConBody.add(invoke("getRoot").invoke("insert").arg(invoke("peek")).arg(lit(0)));
			
			// Generate tree nodes for the card's hidden fields.
			for ( ClassOutline co : pc.getClasses() )
				generateTreeModelPackage(stmConBody, new ClassName(co), cm);
			
			// Pop tree node for this package.
			stmConBody.invoke("pop");
		}
	}

	private void generateTreeModelPackage(JBlock stmConBody, ClassName className, JCodeModel cm)
	{
		String fcn = className.full;
		List<FieldInfo> hfiList = getHiddenFieldInfoMap().get(fcn);
		List<FieldInfo> vfiList = getVisibleFieldInfoMap().get(fcn);
		JExpression dotCardNodeClass = (vfiList != null) ? cm.directClass(fcn).dotclass() : _null();

		// Generate tree node for this class name.
		if ( hfiList == null )
			stmConBody.invoke("peekAdd").arg(className.name).arg(dotCardNodeClass);
		else
		{
			stmConBody.invoke("pushAdd").arg(className.name).arg(dotCardNodeClass);
			
			for ( FieldInfo hfi : hfiList )
			{
				String pcn = hfi.getParentClassName();
				
				String hftn = hfi.getFieldType().getName();
				if ( hftn == null )
					hftn = "ANON";
				XSType hfift = hfi.getFieldType();
				boolean isgl = hfift.isGlobal();
				boolean isct = hfift.isComplexType();
				boolean isst = hfift.isSimpleType();
				String hfity = "gl="+ isgl + ", ct=" + isct + ", st=" + isst;
				System.err.println("PARENT: (" + hfity + ") " + "[" + hftn + "] "  + hfi.getFieldName() + " of " + pcn);
				
				JClass fieldClass = hfi.getFieldRawType().boxify();
				
				List<String> frcnList = hfi.getFieldRefClassNameList();
				for ( String frcn : frcnList )
				{
					List<FieldInfo> hfiRefList = getHiddenFieldInfoMap().get(frcn);
					List<FieldInfo> vfiRefList = getVisibleFieldInfoMap().get(frcn);
					JExpression dotFieldClass = (vfiRefList != null) ? cm.directClass(frcn).dotclass() : _null();
					
					boolean fip = fieldClass.isParameterized();
					boolean fia = fieldClass.isArray();
					
					if ( hfiRefList == null )
						stmConBody.invoke("peekAdd").arg(hfi.getFieldName()).arg(dotFieldClass);
					else
					{
						stmConBody.invoke("pushAdd").arg(hfi.getFieldName()).arg(dotFieldClass);
						for ( FieldInfo hfiRef : hfiRefList)
						{
							JClass hfiRefField = hfiRef.getFieldRawType().boxify();
							if ( hfiRefField.isParameterized() )
							{
								for ( JClass hfiRefFieldTypeParm : hfiRefField.getTypeParameters() )
								{
									System.err.println("  REFERENCE PARM: " + hfiRefFieldTypeParm);
									generateTreeModelPackage(stmConBody, new ClassName(hfiRefFieldTypeParm.fullName()), cm);
								}
							}
							else
							{
								for ( String hfiRefClassName : hfiRef.getFieldRefClassNameList() )
								{
									System.err.println("  REFERENCE: " + hfiRefClassName);
									generateTreeModelPackage(stmConBody, new ClassName(hfiRefClassName), cm);
								}
							}
						}
						stmConBody.invoke("pop");
					}
				}
			}
			stmConBody.invoke("pop");
		}
	}
}
