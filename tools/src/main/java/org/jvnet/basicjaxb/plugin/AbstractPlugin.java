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

	@Override
	public boolean run(Outline outline, Options options, ErrorHandler errorHandler) throws SAXException
	{
		try
		{
			beforeRun(outline, options);
			return run(outline, options);
		}
		catch (Exception ex)
		{
			final SAXParseException saxex = new SAXParseException( "Error during plugin execution.", null, ex);
			errorHandler.error(saxex);
			throw saxex;
		}
		finally
		{
			try
			{
				afterRun(outline, options);
			}
			catch (Exception ex)
			{
				final SAXParseException saxex = new SAXParseException("Error after plugin execution.", null, ex);
				errorHandler.error(saxex);
				throw saxex;

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
}
