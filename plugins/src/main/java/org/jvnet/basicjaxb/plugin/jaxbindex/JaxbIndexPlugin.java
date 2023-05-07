package org.jvnet.basicjaxb.plugin.jaxbindex;

import static java.lang.String.format;

import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.util.CodeModelUtils;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JPackage;
import com.sun.codemodel.fmt.JTextFile;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.outline.PackageOutline;

import jakarta.xml.bind.JAXBContext;

/**
 * <p>
 * Programmer annotated Jakarta XML Binding mapped classes can be listed in a
 * <code>jaxb.index</code> resource file. The <code>jaxb.index</code> file is a
 * listing of the classes in the containing package that have JAXB annotations.
 * Each line in the file is a class's simple (non-fully-qualified) name.
 * </p>
 * 
 * <p>
 * Every package listed on the context path must meet <b>one or both</b> of the
 * following conditions; otherwise, a {@link jakarta.xml.bind.JAXBException}
 * will be thrown:
 * </p>
 * 
 * <ul>
 * <li>it must contain ObjectFactory.class</li>
 * <li>it must contain jaxb.index</li>
 * </ul>
 * 
 * <p>
 * The JavaDoc for {@link JAXBContext#newInstance(String, ClassLoader)} provides
 * more detail.
 * </p>
 */
public class JaxbIndexPlugin extends AbstractPlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xjaxbindex";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generate per-package 'jaxb.index' file";

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

	// Plugin Processing
	
	protected void beforeRun(Outline outline, Options options) throws Exception
	{
		setOptions(options);
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Verbose.: " + isVerbose());
			sb.append("\n  Debug...: " + isDebug());
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
		for (final PackageOutline packageOutline : outline.getAllPackageContexts())
		{
			JPackage _package = packageOutline._package();
			final JTextFile indexFile = new JTextFile("jaxb.index");
			final StringBuilder sb = new StringBuilder();
			for (final ClassOutline classOutline : packageOutline.getClasses())
			{
				String simpleType = CodeModelUtils.getLocalClassName(classOutline.ref);
				sb.append(simpleType);
				sb.append("\n");
				trace("run; Package={}, File={}, Type={}", _package.name(), indexFile.name(), simpleType);
			}
			indexFile.setContents(sb.toString());
			_package.addResourceFile(indexFile);
			debug("run; Package={}, File={}", _package.name(), indexFile.name());
		}
		return true;
	}
}
