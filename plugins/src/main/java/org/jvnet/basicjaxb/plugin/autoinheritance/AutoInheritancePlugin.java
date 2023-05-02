package org.jvnet.basicjaxb.plugin.autoinheritance;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.locator.util.LocatorUtils.getLocation;

import java.util.LinkedList;
import java.util.List;

import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.CElementInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.ElementOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * <p>
 * This plugin provides parameters to globally extend elements and/or types from a
 * class or interface. The parameters are declared using <b>XJC</b> options and parsed,
 * internally, by the plugin to configure its properties. The plugin uses this
 * configuration to automatically add a <em>non-schema derived</em> super-class and/or interfaces(s).
 * </p>
 * 
 * <p>
 * Per parameters, all complex types, root and/or JAXB elements are scanned and
 * processed. Where appropriate, the given extension and/or interface as added to the
 * generated class. This avoids the need to provide bindings or modify the schema when
 * <em>global</em> enhancement is sufficient.
 * </p>
 * 
 * <b>Examples</b>
 * <ul>
 * <li><code>-XautoInheritance</code></li>
 * <li><code>-XautoInheritance-xmlTypesExtend=org.example.base.Stageable</code></li>
 * <li><code>-XautoInheritance-xmlTypesImplement=java.lang.Cloneable</code></li>
 * <li><code>-XautoInheritance-xmlRootElementsExtend=org.example.base.Jeopardy</code></li>
 * <li><code>-XautoInheritance-xmlRootElementsImplement=org.example.base.Nameable</code></li>
 * <li><code>-XautoInheritance-jaxbElementsImplement=java.lang.Cloneable</code></li>
 * </ul>
 * 
 * @author Alexey Valikov
 */
public class AutoInheritancePlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XautoInheritance";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "globally extend elements and/or types from a class or interface";

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

	// XJC Option Parameters
	
	private String xmlRootElementsExtend = null;
	public String getXmlRootElementsExtend()
	{
		return xmlRootElementsExtend;
	}
	public void setXmlRootElementsExtend(String globalElementsExtend)
	{
		this.xmlRootElementsExtend = globalElementsExtend;
	}

	private List<String> xmlRootElementsImplement = new LinkedList<String>();
	public String getXmlRootElementsImplement()
	{
		return xmlRootElementsImplement.toString();
	}
	public void setXmlRootElementsImplement(String xmlRootElementsImplement)
	{
		this.xmlRootElementsImplement.add(xmlRootElementsImplement);
	}

	private String xmlTypesExtend = null;
	public String getXmlTypesExtend()
	{
		return xmlTypesExtend;
	}
	public void setXmlTypesExtend(String globalComplexTypesExtend)
	{
		this.xmlTypesExtend = globalComplexTypesExtend;
	}

	private List<String> xmlTypesImplement = new LinkedList<String>();
	public String getXmlTypesImplement()
	{
		return xmlTypesImplement.toString();
	}
	public void setXmlTypesImplement(String xmlTypesImplement)
	{
		this.xmlTypesImplement.add(xmlTypesImplement);
	}

	private List<String> jaxbElementsImplement = new LinkedList<String>();
	public String getJaxbElementsImplement()
	{
		return jaxbElementsImplement.toString();
	}
	public void setJaxbElementsImplement(String jaxbElementsImplement)
	{
		this.jaxbElementsImplement.add(jaxbElementsImplement);
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
			sb.append("\n  XmlRootElementsExtend....: " + getXmlRootElementsExtend());
			sb.append("\n  XmlRootElementsImplement.: " + getXmlRootElementsImplement());
			sb.append("\n  XmlTypesExtend...........: " + getXmlTypesExtend());
			sb.append("\n  XmlTypesImplement........: " + getXmlTypesImplement());
			sb.append("\n  JaxbElementsImplement....: " + getJaxbElementsImplement());
			sb.append("\n  Verbose..................: " + isVerbose());
			sb.append("\n  Debug....................: " + isDebug());
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
     * Run the plug-in.
     *
     * <p>
     * This method is invoked after XJC has internally finished
     * the code generation. Plugins can tweak some of the generated
     * code (or add more code) by using {@link Outline} and {@link Options}.
     * </p>
     *
     * <p>
     * Note that this method is invoked only when a plugin is activated.
     * </p>
     * 
     * @param outline
     *      This object allows access to various generated code.
     *      
     * @param options
     *      The invocation configuration for XJC.
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
	public boolean run(Outline outline, Options options) throws Exception
	{
		for (final ClassOutline classOutline : outline.getClasses())
		{
			if (classOutline.target.getElementName() != null)
				processGlobalElement(classOutline);
			else
				processGlobalComplexType(classOutline);
		}
		
		for (final CElementInfo elementInfo : outline.getModel().getAllElements())
		{
			final ElementOutline elementOutline = outline.getElement(elementInfo);
			if (elementOutline != null)
				processGlobalJAXBElement(elementOutline);
		}
		
		return true;
	}

	protected void processGlobalElement(ClassOutline classOutline)
	{
		generateExtends(classOutline.implClass, xmlRootElementsExtend);
		generateImplements(classOutline.implClass, xmlRootElementsImplement);
	}

	protected void processGlobalJAXBElement(ElementOutline elementOutline)
	{
		generateImplements(elementOutline.implClass, jaxbElementsImplement);
	}

	protected void processGlobalComplexType(ClassOutline classOutline)
	{
		generateExtends(classOutline.implClass, xmlTypesExtend);
		generateImplements(classOutline.implClass, xmlTypesImplement);
	}

	private void generateExtends(JDefinedClass theClass, String name)
	{
		if (name != null)
		{
			final JClass targetClass = theClass.owner().ref(name);
			if (theClass._extends() == theClass.owner().ref(Object.class))
			{
				theClass._extends(targetClass);
				debug("{}, generateExtends; Class={}, Extension={}",
					getLocation(theClass.metadata), theClass.name(), name);
			}
		}
	}

	private void generateImplements(JDefinedClass theClass, List<String> names)
	{
		if (names != null && !names.isEmpty())
		{
			for (String name : names)
				generateImplements(theClass, name);
		}
	}

	private void generateImplements(JDefinedClass theClass, String name)
	{
		if (name != null)
		{
			final JClass targetClass = theClass.owner().ref(name);
			theClass._implements(targetClass);
			debug("{}, generateImplements; Class={}, Interface={}",
				getLocation(theClass.metadata), theClass.name(), name);
		}
	}
}
