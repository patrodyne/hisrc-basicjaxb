package org.jvnet.basicjaxb.plugin.swing;

import static com.sun.codemodel.JExpr._new;
import static com.sun.codemodel.JExpr._null;
import static com.sun.codemodel.JExpr._super;
import static com.sun.codemodel.JExpr._this;
import static com.sun.codemodel.JExpr.cast;
import static com.sun.codemodel.JExpr.invoke;
import static com.sun.codemodel.JExpr.lit;
import static com.sun.codemodel.JExpr.ref;
import static com.sun.codemodel.JMod.FINAL;
import static com.sun.codemodel.JMod.PRIVATE;
import static com.sun.codemodel.JMod.PUBLIC;
import static com.sun.codemodel.JMod.STATIC;
import static com.sun.codemodel.JOp._instanceof;
import static com.sun.codemodel.JOp.cond;
import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.jvnet.basicjaxb.plugin.swing.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.util.OutlineUtils.filter;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.IntrospectionException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
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
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.text.JTextComponent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.jdesktop.application.Action;
import org.jdesktop.application.Application;
import org.jdesktop.application.PopupDialogUtility;
import org.jdesktop.application.UndoableDocument;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jvnet.basicjaxb.config.LocatorUnmarshaller;
import org.jvnet.basicjaxb.dom.DOMUtils;
import org.jvnet.basicjaxb.lang.DataBeanInfo;
import org.jvnet.basicjaxb.lang.DataDescriptor;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.plugin.beaninfo.BeanInfoCustomization;
import org.jvnet.basicjaxb.plugin.beaninfo.BeanInfoCustomizationFactory;
import org.jvnet.basicjaxb.plugin.beaninfo.FieldInfo;
import org.jvnet.basicjaxb.xml.XmlContext;
import org.swixml.SwingEngine;
import org.swixml.jsr.widgets.CardNodeInfo;
import org.swixml.jsr.widgets.MutableTreeModel;
import org.swixml.schema.model.Dialog;
import org.swixml.schema.model.Frame;
import org.swixml.schema.model.JMenuItem;
import org.swixml.schema.model.JPanel;
import org.swixml.schema.model.JPopupMenu;
import org.swixml.schema.model.JSeparator;
import org.swixml.schema.model.JTableBind;
import org.swixml.schema.model.JTextAreaBind;
import org.swixml.schema.model.JTreeBind;
import org.swixml.schema.model.ObjectFactory;
import org.swixml.schema.model.Window;
import org.swixml.schema.model.XScrollPane;
import org.swixml.schema.model.XSplitPane;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCase;
import com.sun.codemodel.JCast;
import com.sun.codemodel.JCatchBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JExpression;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JForEach;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JSwitch;
import com.sun.codemodel.JTryBlock;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.outline.PackageOutline;

import jakarta.xml.bind.JAXBElement;
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
	@SuppressWarnings("unused")
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

	private Window window;
	protected Window getWindow() { return window; }
	protected void setWindow(Window window) { this.window = window; }

	private File windowFile;
	protected File getWindowFile() { return windowFile; }
	protected void setWindowFile(File windowFile) { this.windowFile = windowFile; }

	private Map<PackageOutline, List<ClassName>> cardClassMap;
	protected Map<PackageOutline, List<ClassName>> getCardClassMap()
	{
		if ( cardClassMap == null )
			setCardClassMap(new HashMap<>());
		return cardClassMap;
	}
	protected void setCardClassMap(Map<PackageOutline, List<ClassName>> cardClassMap)
	{
		this.cardClassMap = cardClassMap;
	}

	private JDefinedClass schemasTreeModelClass;
	protected JDefinedClass getSchemasTreeModelClass() { return schemasTreeModelClass; }
	protected void setSchemasTreeModelClass(JDefinedClass schemasTreeModelClass) { this.schemasTreeModelClass = schemasTreeModelClass; }

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

			generateWindowFile(bicf);
			generateTreeModel(bicf);
			generateWindowClass(bicf);
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
	 * @throws JAXBException Any Jakarta XML Binding exception.
	 * @throws IOException An Input/Output exception of some sort has occurred.
	 * @throws IntrospectionException When cannot map class name to a class object.
	 * @throws ClassNotFoundException When no definition for the class with the specified name could be found.
	 * @throws URISyntaxException When a string could not be parsed as a URI reference.
	 * @throws JClassAlreadyExistsException When the specified class was already created.
	 */
	public void generateWindowFile(BeanInfoCustomizationFactory bicf)
		throws JAXBException, IOException, IntrospectionException, ClassNotFoundException,
		URISyntaxException, JClassAlreadyExistsException
	{
		// Set the JAXB context path for the SWIXML model.
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

		setWindow(window);
		setWindowFile(targetFile);
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
		siftSplitPane.setName("Sift: [Merge|Query]");
		siftSplitPane.setOrientation("HORIZONTAL_SPLIT");
		siftSplitPane.setOneTouchExpandable(true);
		siftSplitPane.setSize("${el.size()}");
		siftSplitPane.setDividerLocation("0.50");

		JTextAreaBind mergeTextArea = OF.createJTextAreaBind();
		mergeTextArea.setBindWith("merge");
		mergeTextArea.setBackground("*-10-100");
		mergeTextArea.setFont("Monospaced");
		mergeTextArea.setDocument("${window.mergeDocument}");
		mergeTextArea.getContent().add(createPopupMenu("editMerge"));

		XScrollPane mergeScrollPane = OF.createXScrollPane();
		mergeScrollPane.getContent().add(OF.createTextarea(mergeTextArea));

		JTextAreaBind queryTextArea = OF.createJTextAreaBind();
		queryTextArea.setBindWith("query");
		queryTextArea.setBackground("*-10-100");
		queryTextArea.setFont("Monospaced");
		queryTextArea.setDocument("${window.queryDocument}");
		queryTextArea.getContent().add(createPopupMenu("editQuery"));

		XScrollPane queryScrollPane = OF.createXScrollPane();
		queryScrollPane.getContent().add(OF.createTextarea(queryTextArea));

		siftSplitPane.getContent().add(OF.createScrollpane(mergeScrollPane));
		siftSplitPane.getContent().add(OF.createScrollpane(queryScrollPane));

		XSplitPane workSplitPane = OF.createXSplitPane();
		workSplitPane.setName("Work: [Merge|Query]/Cards");
		workSplitPane.setOrientation("VERTICAL_SPLIT");
		workSplitPane.setOneTouchExpandable(true);
		workSplitPane.setSize("${el.scaleSize(0.80)}");
		workSplitPane.setDividerLocation("0.50");

		workSplitPane.getContent().add(OF.createSplitpane(siftSplitPane));
		workSplitPane.getContent().add(OF.createPanel(cardLayoutPanel));

		mainSplitPane.getContent().add(OF.createScrollpane(treePane));
		mainSplitPane.getContent().add(OF.createSplitpane(workSplitPane));

		// Add the scroll pane(s) containing a JTableBind instance(s) to the card layout panel.
		// Process each class outline by wrapping a table in a scroll pane and adding it to the
		// card layout panel. Filter ignored class outlines.
		for (final ClassOutline classOutline : filter(bicf.getOutline(), getIgnoring()))
			processCardClassOutline(bicf, classOutline, cardLayoutPanel);
	}

	private JAXBElement<JPopupMenu> createPopupMenu(String action)
	{
		JAXBElement<JSeparator> separator = OF.createSeparator(OF.createJSeparator());
		JAXBElement<JPopupMenu> popupMenu = OF.createPopupmenu(OF.createJPopupMenu());
		List<Object> pmList = popupMenu.getValue().getContent();
		pmList.add(createMenuItem(action, "ctrl Z", "Undo"));
		pmList.add(createMenuItem(action, "ctrl Y", "Redo"));
		pmList.add(separator);
		pmList.add(createMenuItem(action, "ctrl X", "Cut"));
		pmList.add(createMenuItem(action, "ctrl C", "Copy"));
		pmList.add(createMenuItem(action, "ctrl V", "Paste"));
		pmList.add(separator);
		pmList.add(createMenuItem(action, "ctrl A", "Select All"));
		pmList.add(separator);
		pmList.add(createMenuItem(action, "VK_O", "Open"));
		pmList.add(createMenuItem(action, "VK_S", "Save"));
		switch ( action )
		{
			case "editMerge": pmList.add(createMenuItem(action, "VK_M", "Merge")); break;
			case "editQuery": pmList.add(createMenuItem(action, "VK_Q", "Query")); break;
		}
		return popupMenu;
	}

	private JAXBElement<JMenuItem> createMenuItem(String action, String accel, String text)
	{
		JMenuItem menuItem = OF.createJMenuItem();
		menuItem.setAction(action);
		menuItem.setAccelerator(accel);
		menuItem.setText(text);
		return OF.createMenuitem(menuItem);
	}

	class ClassName implements Comparable<ClassName>
	{
		public String pkg, pkgAbbr, name, qual, full, clas, list, nameClass, fullClass;
		public ClassName(ClassOutline classOutline)
		{
			JDefinedClass ic = classOutline.getImplClass();
			this.pkg = ic.getPackage().name();
			this.pkgAbbr = abbrPackage(this.pkg);
			this.name = ic.name();
			this.qual = this.pkgAbbr + this.name;
			this.full = this.pkg + "." + this.name;
			this.clas = this.pkgAbbr + this.name + "Class";
			this.list = this.pkgAbbr + this.name + "List";
			this.nameClass = this.name + ".class";
			this.fullClass = this.full + ".class";
		}
		public ClassName(String fullName)
		{
			this.full = fullName;
			int dot = fullName.lastIndexOf('.');
			this.pkg = fullName.substring(0, dot);
			this.pkgAbbr = abbrPackage(this.pkg);
			this.name = fullName.substring(dot+1);
			this.qual = this.pkgAbbr + this.name;
			this.clas = this.pkgAbbr + this.name + "Class";
			this.list = this.pkgAbbr + this.name + "List";
			this.nameClass = this.name + ".class";
			this.fullClass = this.full + ".class";
		}
		private String abbrPackage(String pkg)
		{
			StringBuilder abbr = new StringBuilder();
			for ( String part : pkg.split("\\.") )
				abbr.append(part.substring(0,1));
			return abbr.toString().toLowerCase();
		}
		@Override
		public int compareTo(ClassName that)
		{
			return (that != null) ? this.full.compareTo(that.full) : 1;
		}
		@Override
		public String toString()
		{
			return full;
		}
	}

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

			// Create a ClassName reference for the current ClassOutline.
			ClassName className = new ClassName(classOutline);

			// Count visible fields
			int fieldCount = 0;
			for (FieldInfo fieldInfo : bic.getFieldInfoList() )
			{
				String fiFieldName = fieldInfo.getFieldName();
				if ( !declaredFieldNameSet.contains(fiFieldName) )
					fieldInfo.setFieldHidden(true);

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
				String bindClass = format("${window.%sClass}", className.qual);
				String bindList = format("${window.%s}", className.list);

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

				// Add the scroll pane containing a JTableBind instance to the card layout panel.
				cardLayoutPanel.getContent().add(OF.createScrollpane(scrollPane));
			}
		}
	}

	/**
	 * Generate the Window class for the given window (SWIXML) instance and file.
	 *
	 * @param bicf The bean info customization factory.
	 *
	 * @throws JClassAlreadyExistsException When the specified class was already created.
	 */
	public void generateWindowClass(BeanInfoCustomizationFactory bicf)
		throws JClassAlreadyExistsException
	{
		JCodeModel cm = bicf.getOutline().getCodeModel();

		String windowBaseName = getWindowFile().getName();
		int index = windowBaseName.lastIndexOf('.');
		if ( index > 0 )
			windowBaseName = windowBaseName.substring(0, index);
		String windowFullName = getTargetPackage() + "." + windowBaseName;

		JDefinedClass windowClass = cm._class(windowFullName);
		if ( getWindow() instanceof Dialog )
			windowClass._extends(JDialog.class);
		else if ( getWindow() instanceof Frame )
			windowClass._extends(JFrame.class);
		windowClass.field(PRIVATE+STATIC+FINAL, long.class, "serialVersionUID", getLitSerialVersionUID());

		// Card lists.
		List<JMethod[]> cardMethodsList = new ArrayList<>();
		for ( Entry<PackageOutline, List<ClassName>> cnEntry : getCardClassMap().entrySet() )
		{
			List<ClassName> cnList = cnEntry.getValue();
			for ( ClassName cn : cnList )
			{
				JClass cnType = cm.directClass(cn.full);
				JClass cnTypeClass = cm.ref(Class.class).narrow(cnType);
				JMethod cnGetClass = windowClass.method(JMod.PUBLIC, cnTypeClass, "get"+capitalize(cn.clas));
				cnGetClass.body()._return(cnType.dotclass());
				JClass cnTypeList = cm.ref(List.class).narrow(cnType);
				JClass cnTypeArrayList = cm.ref(ArrayList.class).narrow(cnType);
				JFieldVar cnField = windowClass.field(PRIVATE, cnTypeList, cn.list);
				JMethod cnGetList = windowClass.method(JMod.PUBLIC, cnTypeList, "get"+capitalize(cn.list));
				JInvocation cnInvokeOL = cm.ref(ObservableCollections.class).staticInvoke("observableList");
				cnGetList.body()
					._if(cnField.eq(_null()))
					._then().assign(cnField, cnInvokeOL.arg(_new(cnTypeArrayList)));
				cnGetList.body()._return(cnField);
				// Cache card methods for later
				cardMethodsList.add(new JMethod[] {cnGetClass, cnGetList});
			}
		}

		// cardPanel: Bound by id in SchemasWindow.xml
		JFieldVar cpField = windowClass.field(PRIVATE, javax.swing.JPanel.class, "cardPanel");
		JMethod cpGetter = windowClass.method(JMod.PUBLIC, javax.swing.JPanel.class, "getCardPanel");
		JMethod cpSetter = windowClass.method(JMod.PUBLIC, cm.VOID, "setCardPanel");
		JVar cpParm = cpSetter.param(javax.swing.JPanel.class, "cardPanel");
		cpSetter.body().assign(_this().ref(cpField), cpParm);
		cpGetter.body()._return(cpField);

		// cardNodeInfo: Set in selectNode action.
		JFieldVar cniField = windowClass.field(PRIVATE, CardNodeInfo.class, "cardNodeInfo");
		JMethod cniGetter = windowClass.method(JMod.PUBLIC, CardNodeInfo.class, "getCardNodeInfo");
		JMethod cniSetter = windowClass.method(JMod.PUBLIC, cm.VOID, "setCardNodeInfo");
		JVar cniParm = cniSetter.param(CardNodeInfo.class, "cardNodeInfo");
		cniSetter.body().assign(_this().ref(cniField), cniParm);
		cniGetter.body()._return(cniField);

		// schemasTree: Bound by id in SchemasWindow.xml
		JFieldVar stField = windowClass.field(PRIVATE, JTree.class, "schemasTree");
		JMethod stGetter = windowClass.method(JMod.PUBLIC, JTree.class, "getSchemasTree");
		JMethod stSetter = windowClass.method(JMod.PUBLIC, cm.VOID, "setSchemasTree");
		JVar stParm = stSetter.param(JTree.class, "schemasTree");
		stSetter.body().assign(_this().ref(stField), stParm);
		stGetter.body()._return(stField);

		// schemasTree: Initialized using SchemasTreeModel
		JFieldVar stmField = windowClass.field(PRIVATE, TreeModel.class, "schemasTreeModel");
		JMethod stmGetter = windowClass.method(JMod.PUBLIC, TreeModel.class, "getSchemasTreeModel");
		JMethod stmSetter = windowClass.method(JMod.PUBLIC, cm.VOID, "setSchemasTreeModel");
		JVar stmParm = stmSetter.param(TreeModel.class, "schemasTreeModel");
		stmSetter.body().assign(_this().ref(stmField), stmParm);
		stmGetter.body()
			._if(stmField.eq(_null()))
			._then().assign(stmField, _new(getSchemasTreeModelClass() ));
		stmGetter.body()._return(stmField);

		// schemasTreeCellRenderer: Bound by cellRenderer in SchemasWindow.xml
		JFieldVar stcrField = windowClass.field(PRIVATE, TreeCellRenderer.class, "schemasTreeCellRenderer");
		JMethod stcrGetter = windowClass.method(JMod.PUBLIC, TreeCellRenderer.class, "getSchemasTreeCellRenderer");
		JMethod stcrSetter = windowClass.method(JMod.PUBLIC, cm.VOID, "setSchemasTreeCellRenderer");
		JVar stcrParm = stcrSetter.param(TreeCellRenderer.class, "schemasTreeCellRenderer");
		stcrSetter.body().assign(_this().ref(stcrField), stcrParm);
		JDefinedClass stcrac = cm.anonymousClass(DefaultTreeCellRenderer.class);
		stcrac.field(PRIVATE+STATIC+FINAL, long.class, "serialVersionUID", getLitSerialVersionUID());
		JMethod stcracm = stcrac.method(JMod.PUBLIC, cm.ref(Color.class), "getBackgroundNonSelectionColor");
		stcracm.annotate(Override.class);
		stcracm.body()._return(invoke(stGetter).invoke("getBackground"));
		JInvocation stcri = _new(stcrac);
		stcrGetter.body()
			._if(stcrField.eq(_null()))
			._then().assign(stcrField, stcri);
		stcrGetter.body()._return(stcrField);

		// merge: Bound by (textarea) bindsWith in SchemasWindow.xml
		JClass tamFieldType = cm.ref(StringBuilder.class);
		JFieldVar tamField = windowClass.field(PRIVATE, tamFieldType, "merge", _new(tamFieldType));
		JMethod tamGetter = windowClass.method(JMod.PUBLIC, String.class, "getMerge");
		tamGetter.body()._return(tamField.invoke("toString"));

		JMethod tamSetter1 = windowClass.method(JMod.PUBLIC, cm.VOID, "setMerge");
		JVar tamParm1 = tamSetter1.param(tamFieldType, "merge");
		tamSetter1.body().assign(_this().ref(tamField), cond(tamParm1.ne(_null()), tamParm1, _new(tamFieldType)));

		JMethod tamSetter2 = windowClass.method(JMod.PUBLIC, cm.VOID, "setMerge");
		JVar tamParm2 = tamSetter2.param(String.class, "merge");
		tamSetter2.body().assign(_this().ref(tamField), _new(tamFieldType).arg(tamParm2));
		tamSetter2.body().invoke("firePropertyChange").arg("merge").arg(_null()).arg(_null());

		JMethod tamAppend = windowClass.method(JMod.PUBLIC, cm.VOID, "appendMerge");
		JVar tamValue = tamAppend.param(String.class, "value");
		tamAppend.body().invoke(tamField, "append").arg(tamValue.plus(JExpr.lit("\n")));
		tamAppend.body().invoke("firePropertyChange").arg("merge").arg(_null()).arg(_null());

		// query: Bound by (textarea) bindsWith in SchemasWindow.xml
		JClass taqFieldType = cm.ref(StringBuilder.class);
		JFieldVar taqField = windowClass.field(PRIVATE, taqFieldType, "query", _new(taqFieldType).arg("/"));
		JMethod taqGetter = windowClass.method(JMod.PUBLIC, String.class, "getQuery");
		taqGetter.body()._return(taqField.invoke("toString"));

		JMethod taqSetter1 = windowClass.method(JMod.PUBLIC, cm.VOID, "setQuery");
		JVar taqParm1 = taqSetter1.param(taqFieldType, "query");
		taqSetter1.body().assign(_this().ref(taqField), cond(taqParm1.ne(_null()), taqParm1, _new(taqFieldType)));

		JMethod taqSetter2 = windowClass.method(JMod.PUBLIC, cm.VOID, "setQuery");
		JVar taqParm2 = taqSetter2.param(String.class, "query");
		taqSetter2.body().assign(_this().ref(taqField), _new(taqFieldType).arg(taqParm2));
		taqSetter2.body().invoke("firePropertyChange").arg("query").arg(_null()).arg(_null());

		JMethod taqAppend = windowClass.method(JMod.PUBLIC, cm.VOID, "appendQuery");
		JVar taqValue = taqAppend.param(String.class, "value");
		taqAppend.body().invoke(taqField, "append").arg(taqValue.plus(JExpr.lit("\n")));
		taqAppend.body().invoke("firePropertyChange").arg("query").arg(_null()).arg(_null());

		// mergeDocument: Bound by (textarea) document in SchemasWindow.xml
		JFieldVar mdmField = windowClass.field(PRIVATE, UndoableDocument.class, "mergeDocument");
		JMethod mdmGetter = windowClass.method(JMod.PUBLIC, UndoableDocument.class, "getMergeDocument");
		JMethod mdmSetter = windowClass.method(JMod.PUBLIC, cm.VOID, "setMergeDocument");
		JVar mdmParm = mdmSetter.param(UndoableDocument.class, "mergeDocument");
		mdmSetter.body().assign(_this().ref(mdmField), mdmParm);
		mdmGetter.body()
			._if(mdmField.eq(_null()))
			._then().assign(mdmField, _new(cm.ref(UndoableDocument.class)));
		mdmGetter.body()._return(mdmField);

		// queryDocument: Bound by (textarea) document in SchemasWindow.xml
		JFieldVar qdmField = windowClass.field(PRIVATE, UndoableDocument.class, "queryDocument");
		JMethod qdmGetter = windowClass.method(JMod.PUBLIC, UndoableDocument.class, "getQueryDocument");
		JMethod qdmSetter = windowClass.method(JMod.PUBLIC, cm.VOID, "setQueryDocument");
		JVar qdmParm = qdmSetter.param(UndoableDocument.class, "queryDocument");
		qdmSetter.body().assign(_this().ref(qdmField), qdmParm);
		qdmGetter.body()
			._if(qdmField.eq(_null()))
			._then().assign(qdmField, _new(cm.ref(UndoableDocument.class)));
		qdmGetter.body()._return(qdmField);

		// fileChooser: Used by doCommonPopupMenuAction(...)
		JFieldVar fcmField = windowClass.field(PRIVATE, JFileChooser.class, "fileChooser");
		JMethod fcmGetter = windowClass.method(JMod.PUBLIC, JFileChooser.class, "getFileChooser");
		JMethod fcmSetter = windowClass.method(JMod.PUBLIC, cm.VOID, "setFileChooser");
		JVar fcmParm = fcmSetter.param(JFileChooser.class, "fileChooser");
		fcmSetter.body().assign(_this().ref(fcmField), fcmParm);
		fcmGetter.body()
			._if(fcmField.eq(_null()))
			._then().assign(fcmField, _new(cm.ref(JFileChooser.class)));
		fcmGetter.body()._return(fcmField);

		// xmlContext: Used by select(ActionEvent ae)
		JFieldVar xcmField = windowClass.field(PRIVATE, XmlContext.class, "xmlContext");
		JMethod xcmGetter = windowClass.method(JMod.PUBLIC, XmlContext.class, "getXmlContext");
		JMethod xcmSetter = windowClass.method(JMod.PUBLIC, cm.VOID, "setXmlContext");
		JVar xcmParm = xcmSetter.param(XmlContext.class, "xmlContext");
		xcmSetter.body().assign(_this().ref(xcmField), xcmParm);
		xcmGetter.body()
			._if(xcmField.eq(_null()))._then().
				_if( invoke(cniGetter).ne(_null()))
				._then().invoke(xcmSetter).arg( _new(cm.ref(XmlContext.class)).arg
					(
						invoke(cniGetter)
						.invoke("getDataBeanInfo")
						.invoke("getDataDescriptor")
						.invoke("getObjectFactoryClass")
					));
		xcmGetter.body()._return(xcmField);

		// hide components: Used by selectNode
		JMethod hcm = windowClass.method(JMod.PRIVATE, cm.VOID, "hide");
		JVar hcmParm = hcm.param(Component[].class, "comps");
		JClass hcmCompType = cm.ref(Component.class);
		hcm.body()
			.forEach(hcmCompType, "comp", hcmParm).body()
				.invoke(ref("comp"),"setVisible").arg(JExpr.FALSE);

		// selectNode: Bound by tree action in SchemasWindow.xml
		JMethod snm = windowClass.method(JMod.PUBLIC, cm.VOID, "selectNode");
		JVar snmParm = snm.param(ActionEvent.class, "ae");
		snm._throws(JAXBException.class);
		snm.annotate(Action.class);
		JClass tseType = cm.ref(TreeSelectionEvent.class);
		JVar tseVar = snm.body().decl(tseType, "ev", cast(tseType, snmParm.invoke("getSource")));
		JClass dmtnType = cm.ref(DefaultMutableTreeNode.class);
		JVar dmtnVar = snm.body().decl(dmtnType, "treeNode", cast(dmtnType, tseVar.invoke("getPath").invoke("getLastPathComponent")));

		JClass cniType = cm.ref(CardNodeInfo.class);
		JConditional ifcni = snm.body()._if(_instanceof(dmtnVar.invoke("getUserObject"), cniType));
		JVar cniVar = ifcni._then().decl(cniType, "cardNodeInfo", cast(cniType, dmtnVar.invoke("getUserObject")));
		JConditional ifcn = ifcni._then()._if(cniVar.invoke("getCardName").ne(_null()));
		JClass clType = cm.ref(CardLayout.class);
		JVar clVar = ifcn._then().decl(clType, "cardLayout", cast(clType, invoke(cpGetter).invoke("getLayout")));
		ifcn._then().add(clVar.invoke("show").arg(invoke(cpGetter)).arg(cniVar.invoke("getCardName")));
		ifcn._then().add(cniVar.invoke("setCardTableComponents").arg(invoke(cpGetter)));
		ifcn._then().add(invoke(cniSetter).arg(cniVar));

		// Set the XML context for the selected node.
		ifcn._then().invoke(xcmSetter).arg(_null());
		JClass clzType = cm.ref(Class.class).narrow(cm.wildcard());
		JClass lisType = cm.ref(List.class).narrow(cm.wildcard());
		JClass hmpType = cm.ref(HashMap.class).narrow(cm.wildcard());
		JVar clmVar = ifcn._then().decl(cm.ref(Map.class).narrow(clzType,lisType), "cardListMap", _new(hmpType));
		JClass sulClass = cm.ref(getTargetPackage() + ".SchemasUnmarshalListener");
		for ( JMethod[] cardMethods : cardMethodsList )
			ifcn._then().add(clmVar.invoke("put").arg(invoke(cardMethods[0])).arg(invoke(cardMethods[1])));
		ifcn._then().add(invoke(xcmGetter).invoke("getUnmarshaller").invoke("setListener").arg(_new(sulClass).arg(clmVar)));
		ifcn._else().add(invoke("hide").arg(invoke(cpGetter).invoke("getComponents")));

		// select: Bound by table action in SchemasWindow.xml
		JMethod sm = windowClass.method(JMod.PUBLIC, cm.VOID, "select");
		JVar smParm = sm.param(ActionEvent.class, "ae");
		sm.annotate(Action.class);
		JSwitch sms = sm.body()._switch(smParm.invoke("getActionCommand"));
		JCase csCase = sms._case(lit("colSelection"));
		csCase.body()._break();
		JCase rsCase = sms._case(lit("rowSelection"));
		JBlock rsBody = rsCase.body();
		JClass dbiType = cm.ref(DataBeanInfo.class);
		JVar dbiVar = rsBody.decl(dbiType, "dbi", cast(dbiType, smParm.invoke("getSource")));
		JClass ddType = cm.ref(DataDescriptor.class);
		JVar ddVar = rsBody.decl(ddType, "dd", cast(ddType, dbiVar.invoke("getDataDescriptor")));
		JClass jewnType = cm.ref(Method.class);
		JVar jewmVar = rsBody.decl(jewnType, "jewm", ddVar.invoke("getJAXBElementWrapperMethod"));
		JClass objType = cm.ref(Object.class);
		JClass rowsType = cm.ref(List.class).narrow(objType);
		JVar rowsVar = rsBody.decl(rowsType, "rows", cast(rowsType, dbiVar.invoke("getData")));
		rowsVar.annotate(SuppressWarnings.class).param("value", "unchecked");
		rsBody.invoke("setMerge").arg(cast(cm.ref(StringBuilder.class), _null()));
		JForEach rsForEach = rsBody.forEach(objType, "row", rowsVar);
		JTryBlock rsForEachTry = rsForEach.body()._try();
		JConditional rsIfXRE = rsForEachTry.body()._if( ddVar.invoke("isXmlRootElement"));
		rsIfXRE._then().invoke(tamAppend).arg(invoke("getXmlContext").invoke("marshalToString").arg(rsForEach.var()));
		JConditional rsIfJEWM = rsIfXRE._elseif(jewmVar.ne(_null()));
		JClass jeType = cm.ref(JAXBElement.class).narrow(cm.wildcard());
		JInvocation jeRowObj = jewmVar.invoke("invoke").arg(ddVar.invoke("getObjectFactory")).arg(rsForEach.var());
		JVar jeRowVar = rsIfJEWM._then().decl(jeType, "jeRow", cast(jeType, jeRowObj));
		rsIfJEWM._then().invoke(tamAppend).arg(invoke("getXmlContext").invoke("marshalToString").arg(jeRowVar));
		rsIfJEWM._else().invoke(tamAppend).arg(rsForEach.var().invoke("toString"));
		JCatchBlock rsForEachCatch = rsForEachTry._catch(cm.ref(Exception.class));
		rsForEachCatch.param("ex");
		rsForEachCatch.body().invoke(tamAppend).arg(rsForEach.var().invoke("toString"));
		rsBody._break();

		// editMerge: Bound by menuItem action in SchemasWindow.xml
		JMethod emm = windowClass.method(JMod.PUBLIC, cm.VOID, "editMerge");
		JVar emmParm = emm.param(ActionEvent.class, "ae");
		emm.annotate(Action.class);
		emm._throws(JAXBException.class);
		JInvocation emmAction = cm.ref(PopupDialogUtility.class).staticInvoke("doCommonPopupMenuAction");
		JVar tcm = emm.body().decl(cm.ref(JTextComponent.class), "tc", emmAction.arg(emmParm).arg(invoke(fcmGetter)));
		JConditional ifma = emm.body()._if(lit("Merge").invoke("equals").arg(emmParm.invoke("getActionCommand")));
		JConditional ifmacni = ifma._then()._if( invoke(cniGetter).ne(_null()));
		ifmacni._then().add(invoke("getXmlContext").invoke("unmarshalFromString").arg(tcm.invoke("getText")));

		JClass sidRef = cm.ref(Application.class);
		ifmacni._else().add(sidRef.staticInvoke("showInformationDialog")
			.arg(invoke("getOwner"))
			.arg("Select a target from the tree.")
			.arg("No Target Selected"));

		// editQuery: Bound by menuItem action in SchemasWindow.xml

		JMethod eqm = windowClass.method(JMod.PUBLIC, cm.VOID, "editQuery");
		eqm._throws(ParserConfigurationException.class);
		eqm._throws(SAXException.class);
		eqm._throws(IOException.class);
		eqm._throws(XPathExpressionException.class);
		eqm._throws(TransformerException.class);
		JVar eqmParm = eqm.param(ActionEvent.class, "ae");
		eqm.annotate(Action.class);
		JInvocation eqmAction = cm.ref(PopupDialogUtility.class).staticInvoke("doCommonPopupMenuAction");
		eqm.body().add(eqmAction.arg(eqmParm).arg(invoke(fcmGetter)));
		JClass dbfRef = cm.ref(DocumentBuilderFactory.class);
		JClass dbRef = cm.ref(DocumentBuilder.class);
		JVar dbfVar = eqm.body().decl(dbfRef, "dbf", dbfRef.staticInvoke("newInstance"));
		JVar dbVar = eqm.body().decl(dbRef, "db", dbfVar.invoke("newDocumentBuilder"));

		JClass srRef = cm.ref(StringReader.class);
		JVar srVar = eqm.body().decl(srRef, "sr", _new(srRef).arg(invoke(tamGetter)));
		JTryBlock tryBlock = eqm.body()._try();
		JClass docRef = cm.ref(Document.class);
		JClass isRef = cm.ref(InputSource.class);
		JVar mdVar = tryBlock.body().decl(docRef, "md", dbVar.invoke("parse").arg(_new(isRef).arg(srVar)));
		JClass nodeRef = cm.ref(Node.class);
		JClass xpcRef = cm.ref(XPathConstants.class);
		JClass xpRef = cm.ref(XPath.class);
		JClass xpfRef = cm.ref(XPathFactory.class);
		JVar xpVar = tryBlock.body().decl(xpRef, "xp", xpfRef.staticInvoke("newInstance").invoke("newXPath"));
		JVar nodeVar = tryBlock.body().decl(nodeRef, "node", cast(nodeRef,xpVar.invoke("evaluate")
			.arg(invoke(taqGetter))
			.arg(mdVar)
			.arg(xpcRef.staticRef("NODE"))));
		JVar evalVar = tryBlock.body().decl(cm.ref(String.class), "eval",
			cm.ref(DOMUtils.class).staticInvoke("transformToString").arg(nodeVar));
		JClass jopRef = cm.ref(JOptionPane.class);
		JClass pduRef = cm.ref(PopupDialogUtility.class);
		JClass psRef = cm.ref(PopupDialogUtility.PaneSize.class);

		tryBlock.body().staticInvoke(jopRef,"showMessageDialog").arg(_this())
			.arg(pduRef.staticInvoke("toScrollableTextArea").arg(evalVar).arg(psRef.staticRef("SMALL")));
		tryBlock._finally().add(srVar.invoke("close"));
	}

	/**
	 * Generate a tree model.
	 *
	 * @param bicf A bean info customization factory.
	 *
	 * @throws JClassAlreadyExistsException When the specified class was already created.
	 */
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

		// Used by generateWindowClass(...)
		setSchemasTreeModelClass(stmClass);

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
			Set<ClassName> classNames = new TreeSet<>();
			for ( ClassOutline co : pc.getClasses() )
				classNames.add(new ClassName(co));
			for ( ClassName cn : classNames )
			{
				if ( generateTreeModelPackageNode(stmConBody, cn, cm) )
				{
					List<ClassName> cnList = getCardClassMap().get(pc);
					if ( cnList == null )
					{
						cnList = new ArrayList<>();
						getCardClassMap().put(pc, cnList);
					}
					cnList.add(cn);
				}
			}

			// Pop tree node for this package.
			stmConBody.invoke("pop");
		}
	}

	private boolean generateTreeModelPackageNode(JBlock stmConBody, ClassName className, JCodeModel cm)
	{
		boolean nodeGenerated = false;
		String fcn = className.full;
		List<FieldInfo> vfiList = getVisibleFieldInfoMap().get(fcn);

		// Generate tree node for this class name.
		if ( vfiList != null )
		{
			JExpression dotCardNodeClass = cm.directClass(fcn).dotclass();
			stmConBody.invoke("peekAdd").arg(className.name).arg(dotCardNodeClass);
			nodeGenerated = true;
		}
		return nodeGenerated;
	}
}
