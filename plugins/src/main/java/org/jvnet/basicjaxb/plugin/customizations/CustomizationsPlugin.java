package org.jvnet.basicjaxb.plugin.customizations;

import static java.lang.String.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.util.ClassUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CEnumConstant;
import com.sun.tools.xjc.model.CEnumLeafInfo;
import com.sun.tools.xjc.model.CPluginCustomization;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.Outline;

/**
 * Read and add customizations from files.
 * 
 * <ul>
 *   <li><code>-Xcustomization</code></li>
 *   <li><code>-Xcustomizations-directory=${basedir}/src/main/resources</code></li>
 *   <li><code>-Xcustomizations-verbose=true</code></li>
 * </ul>
 *
 * <p>
 * Put the <code>JavaFilename.xml</code> in the resource path in the <em>same package</em>
 * as the target Java file.
 * </p>
 *
 * <b>JavaFilename.xml</b>
 * <pre>
 * &lt;cus:customizations xmlns:cus="http://jvnet.org/basicjaxb/xjc/customizations"&gt;
 *   &lt;inh:implements xmlns:inh="http://jvnet.org/basicjaxb/xjc/inheritance"&gt;
 *     java.lang.Cloneable
 *   &lt;/inh:implements&gt;
 * &lt;/cus:customizations&gt;

 * </pre>
 *
 */
public class CustomizationsPlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xcustomizations";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "read and add customizations from files";

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
	
	private final DocumentBuilderFactory documentBuilderFactory;
	{
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
	}
	
	private File directory;
	public File getDirectory() { return directory; }
	public void setDirectory(File customizationsDirectory) { this.directory = customizationsDirectory; }

	private boolean verbose;
	public boolean isVerbose() { return verbose; }
	public void setVerbose(boolean verbose) { this.verbose = verbose; }

	// Plugin Processing

	@Override
	protected void beforePostProcessModel(Model model)
	{
		if ( isInfoEnabled(isVerbose()) )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Directory.: " + getDirectory());
			sb.append("\n  Verbose...: " + isVerbose());
			info(sb.toString());
		}
	}
	
	@Override
	protected void afterPostProcessModel(Model model, ErrorHandler errorHandler)
	{
		if ( isInfoEnabled(isVerbose()) )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_FINISH);
			sb.append("\nResults");
			sb.append("\n  HadError.: " + hadError(errorHandler));
			info(sb.toString());
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
		if (getDirectory() == null)
			getLogger().warn("Customizations directory is not provided, please use the -Xcustomizations-directory=<directory> command line argument to provide it.");
		else if (!getDirectory().exists())
			getLogger().warn(MessageFormat.format("Customizations directory [{0}] does not exist.", getDirectory().getAbsolutePath()));
		else if (!getDirectory().isDirectory())
			getLogger().warn(MessageFormat.format("Customizations directory [{0}] is not a directory.", getDirectory().getAbsolutePath()));
		else
		{
			for (final CClassInfo classInfo : model.beans().values())
				postProcessClassInfo(model, classInfo);
			
			for (final CEnumLeafInfo enumLeafInfo : model.enums().values())
				postProcessEnumLeafInfo(model, enumLeafInfo);
		}
	}

	private void postProcessClassInfo(Model model, CClassInfo classInfo)
	{
		final String packagedClassName = ClassUtils.getPackagedClassName(classInfo);
		final String customizationsFileName = packagedClassName.replace(".", "/") + ".xml";
		final List<CPluginCustomization> customizations = readCustomizations(customizationsFileName);
		classInfo.getCustomizations().addAll(customizations);
		
		for (CPropertyInfo propertyInfo : classInfo.getProperties())
			postProcessPropertyInfo(model, classInfo, propertyInfo);
	}

	private void postProcessPropertyInfo(Model model, CClassInfo classInfo, CPropertyInfo propertyInfo)
	{
		final String packagedClassName = ClassUtils.getPackagedClassName(classInfo);
		final String customizationsFileName = packagedClassName.replace(".", "/")	+ "." + propertyInfo.getName(false) + ".xml";
		final List<CPluginCustomization> customizations = readCustomizations(customizationsFileName);
		propertyInfo.getCustomizations().addAll(customizations);
	}

	private void postProcessEnumLeafInfo(Model model, CEnumLeafInfo enumLeafInfo)
	{
		final String packagedClassName = ClassUtils.getPackagedClassName(enumLeafInfo);
		final String customizationsFileName = packagedClassName.replace(".", "/") + ".xml";
		final List<CPluginCustomization> customizations = readCustomizations(customizationsFileName);
		enumLeafInfo.getCustomizations().addAll(customizations);
		
		for (CEnumConstant enumConstant : enumLeafInfo.getConstants())
			postProcessEnumConstant(model, enumLeafInfo, enumConstant);
	}

	private void postProcessEnumConstant(Model model, CEnumLeafInfo enumLeafInfo, CEnumConstant enumConstant)
	{
		final String packagedClassName = ClassUtils.getPackagedClassName(enumLeafInfo);
		final String customizationsFileName = packagedClassName.replace(".", "/")	+ "." + enumConstant.getName()
												+ ".xml";
		final List<CPluginCustomization> customizations = readCustomizations(customizationsFileName);
		enumConstant.getCustomizations().addAll(customizations);
	}

	private List<CPluginCustomization> readCustomizations(String fileName)
	{
		final List<CPluginCustomization> customizations = new LinkedList<CPluginCustomization>();
		DocumentBuilder documentBuilder = null;
		try
		{
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException pcex)
		{
			throw new UnsupportedOperationException("Could not created the DOM parser.", pcex);
		}
		
		final File file = new File(getDirectory(), fileName);
		if (!file.exists())
		{
			if (isVerbose())
				getLogger().debug(MessageFormat.format("File [{0}] does not exist.", file.getAbsolutePath()));
		}
		else if (!file.isFile())
		{
			getLogger().warn(MessageFormat.format("File [{0}] is not a file.", file.getAbsolutePath()));
		}
		else
		{
			try ( InputStream is = new FileInputStream(file) )
			{
				final Document document = documentBuilder.parse(is);
				final Element documentElement = document.getDocumentElement();
				getLogger().debug(MessageFormat.format("Loaded customizations from [{0}].", file.getAbsolutePath()));
				final QName documentElementName = new QName(documentElement.getNamespaceURI(), documentElement.getLocalName());
				
				if (Customizations.CUSTOMIZATIONS_ELEMENT_NAME.equals(documentElementName))
				{
					final NodeList childNodes = documentElement.getChildNodes();
					for (int index = 0; index < childNodes.getLength(); index++)
					{
						final Node childNode = childNodes.item(index);
						if (childNode.getNodeType() == Node.ELEMENT_NODE)
						{
							final Element childElement = (Element) childNode;
							customizations.add(new CPluginCustomization(childElement, null));
						}
					}
				}
				else
					customizations.add(new CPluginCustomization(documentElement, null));
			}
			catch (IOException ioex)
			{
				getLogger().warn(MessageFormat.format("Could not parse [{0}].", file.getAbsolutePath()), ioex);
			}
			catch (SAXException saxex)
			{
				getLogger().warn(MessageFormat.format("Could not parse [{0}].", file.getAbsolutePath()), saxex);
			}
		}
		return customizations;
	}
}
