package org.jvnet.basicjaxb.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.util.ErrorReceiverFilter;

/**
 * <p>An abstract XJC plugin to add or modify the XJC outline. An outline captures
 * which code is generated for which model component. A {@link Model} is a
 * schema language neutral representation of the result of a schema parsing. XJC
 * uses this model to turn this into a series of Java source code.</p>
 * 
 * <p>The XJC tool loads extensions using these code points:</p>
 * 
 * <pre>
 * package com.sun.tools.xjc
 *     ModelLoader.load(Options, JCodeModel, ErrorReceiver)
 *     // Parses a set of XML Schema files into an annotated grammar.
 *     XSSchemaSet ModelLoader.loadXMLSchema()
 *     // Parses a <b>DOMForest</b> into a <b>XSSchemaSet</b>
 *     DOMForest forest = buildDOMForest( new XMLSchemaInternalizationLogic() );
 *     public XSSchemaSet ModelLoader.createXSOM(DOMForest forest, SCDBasedBindingSet scdBasedBindingSet)
 * 
 * package com.sun.tools.xjc.reader.internalizer
 *     // Internalizes external binding declarations.
 *     String EXTENSION_PREFIXES = "extensionBindingPrefixes"
 *     Internalizer.declareExtensionNamespace( Element target, String nsUri )
 *     // Applies the additional binding customizations.
 *     public void SCDBasedBindingSet.apply(XSSchemaSet schema, ErrorReceiver errorReceiver)
 * </pre>
 */
public abstract class AbstractPlugin extends Plugin
{
	/**
	 * Plugin logger.
	 */
	private Logger logger = LoggerFactory.getLogger(getClass());
	protected Logger getLogger() { return logger; }
	
	/** Represents the XJC plugin usage format.*/
	public static final String USAGE_FORMAT = "  -%-20s : %s";
	
	/** Represents the XJC plugin logging prefix.*/
	public static final String LOGGING_PREFIX = "XJC> ";

	/** Represents the XJC plugin logging start message.*/
	public static final String LOGGING_START = ": Start";

	/** Represents the XJC plugin logging finish message.*/
	public static final String LOGGING_FINISH = ": Finish";
	
	private Options options = new Options();
	protected Options getOptions() { return options; }
	protected void setOptions(Options options)
	{
		this.options = options;
		setDebug(options.debugMode);
		setQuiet(options.quiet);
		setVerbose(options.verbose);
	}

	private boolean debug = false;
	public boolean isDebug() { return debug; }
	public void setDebug(boolean debug) { this.debug = debug; }
	
	private boolean quiet = false;
	public boolean isQuiet() { return quiet; }
	public void setQuiet(boolean quiet)	{ this.quiet = quiet; }

	private boolean verbose = false;
	public boolean isVerbose() { return verbose || debug; }
	public void setVerbose(boolean verbose) { this.verbose = verbose; }
	
	@Override
	public void onActivated(Options options) throws BadCommandLineException
	{
		super.onActivated(options);
		try
		{
			init(options);
		}
		catch (Exception ex)
		{
			throw new BadCommandLineException( "Could not initialize the plugin [" + getOptionName() + "].", ex);
		}
	}

	private SAXException handleException(ErrorHandler errorHandler, Exception ex, String msg)
	{
		SAXException saxex = null;
		try
		{
			SAXParseException saxpex = null;
			if ( ex instanceof SAXParseException )
				saxpex = (SAXParseException) ex;
			else
				saxpex = new SAXParseException(msg, null, ex);
			errorHandler.error(saxpex);
			saxex = saxpex;
		}
		catch (SAXException sex)
		{
			saxex = sex;
		}
		return saxex;
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
     *
     * <p>
     * <b>Note:</b> This method is invoked only when a plugin is activated.
     * </p>
     *
     * @param model
     *      The object that represents the classes/properties to
     *      be generated.
     *
     * @param errorHandler
     *      Errors should be reported to this handler.
     *
     * @since JAXB 2.0.2
     */
	@Override
	public void postProcessModel(Model model, ErrorHandler errorHandler)
	{
		try
		{
			beforePostProcessModel(model);
			postProcessModel(model);
		}
		catch (Exception ex)
		{
			error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage() );
			handleException(errorHandler, ex, "Error during plugin postProcessModel.");
		}
		finally
		{
			try
			{
				afterPostProcessModel(model, errorHandler);
			}
			catch (Exception ex)
			{
				error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage() );
				handleException(errorHandler, ex, "Error after plugin postProcessModel.");
			}
		}
	}

	protected void beforePostProcessModel(Model model) throws Exception
	{
		// Sub-class may override.
	}

	protected void postProcessModel(Model model) throws Exception
	{
		// Sub-class may override.
	}

	protected void afterPostProcessModel(Model model, ErrorHandler errorHandler) throws Exception
	{
		// Sub-class may override.
	}
	
    /**
     * Run the add-on.
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
     * @param errorHandler
     *      Errors should be reported to this handler.
     * 
     * @return
     *      If the add-on executes successfully, return true.
     *      If it detects some errors but those are reported and
     *      recovered gracefully, return false.
     *
     * @throws SAXException
     *      After an error is reported to {@link ErrorHandler}, the
     *      same exception can be thrown to indicate a fatal unrecoverable
     *      error. {@link ErrorHandler} itself may throw it, if it chooses
     *      not to recover from the error.
     */
	@Override
	public boolean run(Outline outline, Options options, ErrorHandler errorHandler)
		throws SAXException
	{
		try
		{
			beforeRun(outline, options);
			return run(outline, options);
		}
		catch (Exception ex)
		{
			error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage() );
			throw handleException(errorHandler, ex, "Error during plugin execution.");
		}
		finally
		{
			try
			{
				afterRun(outline, options);
			}
			catch (Exception ex)
			{
				error("{}: {}", ex.getClass().getSimpleName(), ex.getMessage() );
				throw handleException(errorHandler, ex, "Error after plugin execution.");
			}
		}
	}
	
	protected void beforeRun(Outline outline, Options options) throws Exception
	{
		// Sub-class may override.
	}

	protected boolean run(Outline outline, Options options) throws Exception
	{
		// Sub-class may implement.
		return true;
	}

	protected void afterRun(Outline outline, Options options) throws Exception
	{
		// Sub-class may override.
	}

	protected void init(Options options) throws Exception
	{
		// Sub-class may override.
	}

	public Collection<QName> getCustomizationElementNames()
	{
		return Collections.<QName> emptyList();
	}

	private List<String> customizationURIs;
	@Override
	public List<String> getCustomizationURIs()
	{
		if (this.customizationURIs == null)
		{
			final Collection<QName> customizationElementNames = getCustomizationElementNames();
			this.customizationURIs = new ArrayList<String>(customizationElementNames.size());
			for (QName customizationElementName : customizationElementNames)
			{
				final String namespaceURI = customizationElementName .getNamespaceURI();
				
				if (!(namespaceURI== null || namespaceURI.length() == 0))
					this.customizationURIs.add(namespaceURI);
			}
		}
		return this.customizationURIs;
	}

	private Set<QName> customizationElementNames;
	@Override
	public boolean isCustomizationTagName(String namespaceURI, String localName)
	{
		if (this.customizationElementNames == null)
			this.customizationElementNames = new HashSet<QName>(getCustomizationElementNames());
		return this.customizationElementNames.contains(new QName(namespaceURI, localName));
	}
	
	// Logger: enabled, options

	protected boolean isTraceEnabled()
	{
		return ( !isQuiet() && isVerbose() && isDebug() && getLogger().isTraceEnabled() );
	}
	
	protected boolean isDebugEnabled()
	{
		return ( !isQuiet() && isVerbose() && isDebug() && getLogger().isDebugEnabled() );
	}
	
	protected boolean isInfoEnabled()
	{
		return ( !isQuiet() && isVerbose() && getLogger().isInfoEnabled() );
	}
	
	protected boolean isWarnEnabled()
	{
		return ( !isQuiet() && getLogger().isDebugEnabled() );
	}
	
	protected boolean isErrorEnabled()
	{
		return ( !isQuiet() && getLogger().isErrorEnabled() );
	}
	
	// Logger: trace
	
	protected void trace(String msg, Object... args)
	{
		if ( !isQuiet() && isVerbose() && isDebug() )
			getLogger().trace(LOGGING_PREFIX + getOptionName() + ": " + msg, args);
	}

	protected void trace(String msg, Throwable th)
	{
		if ( !isQuiet() && isVerbose() && isDebug() )
			getLogger().trace(LOGGING_PREFIX + getOptionName() + ": " + msg, th);
	}

	// Logger: debug
	
	protected void debug(String msg, Object... args)
	{
		if ( !isQuiet() && isVerbose() && isDebug() )
			getLogger().debug(LOGGING_PREFIX + getOptionName() + ": " + msg, args);
	}

	protected void debug(boolean isVerbose, String msg, Throwable th)
	{
		if ( !isQuiet() && isVerbose() && isDebug() )
			getLogger().debug(LOGGING_PREFIX + getOptionName() + ": " + msg, th);
	}

	// Logger: info
	
	protected void info(String msg, Object... args)
	{
		if ( !isQuiet() && isVerbose() )
			getLogger().info(LOGGING_PREFIX + getOptionName() + ": " + msg, args);
	}

	protected void info(String msg, Throwable th)
	{
		if ( !isQuiet() && isVerbose() )
			getLogger().info(LOGGING_PREFIX + getOptionName() + ": " + msg, th);
	}

	// Logger: warn
	
	protected void warn(String msg, Object... args)
	{
		if ( !isQuiet() )
			getLogger().warn(LOGGING_PREFIX + getOptionName() + ": " + msg, args);
	}

	protected void warn(String msg, Throwable th)
	{
		if ( !isQuiet() )
			getLogger().warn(LOGGING_PREFIX + getOptionName() + ": " + msg, th);
	}
	
	// Logger: error
	
	protected void error(String msg, Object... args)
	{
		if ( !isQuiet() )
			getLogger().error(LOGGING_PREFIX + getOptionName() + ": " + msg, args);
	}

	protected void error(String msg, Throwable th)
	{
		if ( !isQuiet() )
			getLogger().error(LOGGING_PREFIX + getOptionName() + ": " + msg, th);
	}
	
	protected boolean hadError(ErrorHandler errorHandler)
	{
		boolean hadError = false;
		if ( errorHandler instanceof ErrorReceiverFilter )
		{
			ErrorReceiverFilter errorReceiverFilter = (ErrorReceiverFilter) errorHandler;
			hadError = errorReceiverFilter.hadError();
		}
		return hadError;
	}
}
