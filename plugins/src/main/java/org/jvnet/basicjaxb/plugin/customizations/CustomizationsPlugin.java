package org.jvnet.basicjaxb.plugin.customizations;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.dom.DOMUtils.getLocator;
import static org.jvnet.basicjaxb.dom.DOMUtils.saxParseDocument;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.locator.util.LocatorBean;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.util.ClassUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.Locator;
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
 */
public class CustomizationsPlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xcustomizations";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "read and add customizations from files";

	/** Define the prefix for DOM locator attributes. */
	private static final String LOCATOR_PREFIX = "loc";

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
	
	private File directory = null;
	public File getDirectory() { return directory; }
	public void setDirectory(File directory) { this.directory = directory; }

	// Plugin Processing

	@Override
	protected void beforePostProcessModel(Model model)
	{
		String error = null;
		if (getDirectory() == null)
			error = "Customizations directory is not provided, please use the -Xcustomizations-directory=<directory> command line argument to provide it.";
		else if (!getDirectory().exists())
			error = "Customizations directory [" + getDirectory().getAbsolutePath() + "] does not exist.";
		else if (!getDirectory().isDirectory())
			error = "Customizations directory [" + getDirectory().getAbsolutePath() + "] is not a directory.";
		
		if ( error != null )
			throw new IllegalArgumentException(error);
		
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Directory.: " + getDirectory());
			sb.append("\n  Verbose...: " + isVerbose());
			sb.append("\n  Debug.....: " + isDebug());
			info(sb.toString());
		}
	}
	
	@Override
	protected void afterPostProcessModel(Model model, ErrorHandler errorHandler)
	{
		if ( isInfoEnabled() )
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
		for (final CClassInfo classInfo : model.beans().values())
			postProcessClassInfo(model, classInfo);
		
		for (final CEnumLeafInfo enumLeafInfo : model.enums().values())
			postProcessEnumLeafInfo(model, enumLeafInfo);
	}

	private void postProcessClassInfo(Model model, CClassInfo classInfo)
	{
		final String packagedClassName = ClassUtils.getPackagedClassName(classInfo);
		final String customizationsFileName = packagedClassName.replace(".", "/") + ".xml";
		final List<CPluginCustomization> customizations =
			readCustomizations(classInfo.getLocator(), customizationsFileName);
		classInfo.getCustomizations().addAll(customizations);
		
		for (CPropertyInfo propertyInfo : classInfo.getProperties())
			postProcessPropertyInfo(model, classInfo, propertyInfo);
	}

	private void postProcessPropertyInfo(Model model, CClassInfo classInfo, CPropertyInfo propertyInfo)
	{
		final String packagedClassName = ClassUtils.getPackagedClassName(classInfo);
		final String customizationsFileName =
			packagedClassName.replace(".", "/")	+ "." + propertyInfo.getName(false) + ".xml";
		final List<CPluginCustomization> customizations =
			readCustomizations(classInfo.getLocator(), customizationsFileName);
		propertyInfo.getCustomizations().addAll(customizations);
	}

	private void postProcessEnumLeafInfo(Model model, CEnumLeafInfo enumLeafInfo)
	{
		final String packagedClassName = ClassUtils.getPackagedClassName(enumLeafInfo);
		final String customizationsFileName = packagedClassName.replace(".", "/") + ".xml";
		final List<CPluginCustomization> customizations =
			readCustomizations(enumLeafInfo.getLocator(), customizationsFileName);
		enumLeafInfo.getCustomizations().addAll(customizations);
		
		for (CEnumConstant enumConstant : enumLeafInfo.getConstants())
			postProcessEnumConstant(model, enumLeafInfo, enumConstant);
	}

	private void postProcessEnumConstant(Model model, CEnumLeafInfo enumLeafInfo, CEnumConstant enumConstant)
	{
		final String packagedClassName = ClassUtils.getPackagedClassName(enumLeafInfo);
		final String customizationsFileName =
			packagedClassName.replace(".", "/")	+ "." + enumConstant.getName() + ".xml";
		final List<CPluginCustomization> customizations =
			readCustomizations(enumLeafInfo.getLocator(), customizationsFileName);
		enumConstant.getCustomizations().addAll(customizations);
	}

	private List<CPluginCustomization> readCustomizations(Locator locator, String fileName)
	{
		final List<CPluginCustomization> customizations = new LinkedList<CPluginCustomization>();
		final String location = toLocation(locator);
		final File file = new File(getDirectory(), fileName);
		final String systemId = file.getAbsolutePath();
		if (!file.exists())
			debug("{}, readCustomizations; Skipping customizations from [{}]; file does not exist.", location, systemId);
		else if (!file.isFile())
			warn("{}, readCustomizations; This [{}] is not a file.", location, systemId);
		else
		{
			try ( InputStream is = new FileInputStream(file) )
			{
				// Use SAX to parse the input stream into a DOM document, enhanced with
				// locator attributes with the given prefix.
				final Document document = saxParseDocument(is, systemId, LOCATOR_PREFIX);
				
				// Check the DOM element for 'customizations'
				final Element element = document.getDocumentElement();
				String elNamespaceURI = element.getNamespaceURI();
				String elLocalName = element.getLocalName();
				final QName elName = new QName(elNamespaceURI, elLocalName);
				if (Customizations.CUSTOMIZATIONS_ELEMENT_NAME.equals(elName))
				{
					final NodeList childNodes = element.getChildNodes();
					for (int index = 0; index < childNodes.getLength(); index++)
					{
						final Node childNode = childNodes.item(index);
						if (childNode.getNodeType() == Node.ELEMENT_NODE)
						{
							final Element ce = (Element) childNode;
							LocatorBean cl = getLocator(ce, LOCATOR_PREFIX);
							customizations.add(new CPluginCustomization(ce, cl));
							debug("{}, readCustomizations; {{}}{}", cl, ce.getNamespaceURI(), ce.getNodeName());
						}
					}
				}
				else
					customizations.add(new CPluginCustomization(element, locator));
				debug("{}, readCustomizations; Loaded customizations from [{}].", location, systemId);
			}
			catch (IOException ioex)
			{
				warn(format("%s, readCustomizations; Could not parse [%s].", location, systemId), ioex);
			}
			catch (SAXException saxex)
			{
				warn(format("%s, readCustomizations; Could not parse [%s].", location, systemId), saxex);
			}
		}
		return customizations;
	}
}
