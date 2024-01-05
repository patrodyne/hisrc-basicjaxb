package org.jvnet.basicjaxb.testing.foo;

import static java.lang.String.format;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;

import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CPluginCustomization;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;
import com.sun.tools.xjc.util.ErrorReceiverFilter;

public class FooPlugin extends Plugin
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/** Represents the XJC plugin usage format.*/
	public static final String USAGE_FORMAT = "  -%-20s : %s";

	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xfoo";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "does nothing";

	@Override
	public String getOptionName()
	{
		return "Xfoo";
	}

	@Override
	public String getUsage()
	{
		return format(USAGE_FORMAT, OPTION_NAME, OPTION_DESC);
	}

	@Override
	public List<String> getCustomizationURIs()
	{
		return Collections.emptyList();
	}

	@Override
	public boolean isCustomizationTagName(String nsUri, String localName)
	{
		return true;
	}
	
	@Override
	public boolean run(Outline outline, Options opt, ErrorHandler errorHandler)
	{
		for (final ClassOutline classOutline : outline.getClasses())
		{
			for (final CPluginCustomization pluginCustomization : classOutline.target.getCustomizations())
				pluginCustomization.markAsAcknowledged();
			
			final CClassInfo classInfo = classOutline.target;
			logger.debug("Class:" + classInfo.getName());
			
			for (final FieldOutline fieldOutline : classOutline.getDeclaredFields())
			{
				final CPropertyInfo propertyInfo = fieldOutline.getPropertyInfo();
				logger.debug("Property:" + propertyInfo.getName(true));

				for (final CPluginCustomization pluginCustomization : fieldOutline.getPropertyInfo().getCustomizations())
					pluginCustomization.markAsAcknowledged();
			}
		}
		
		return hadError(outline.getErrorReceiver());
	}
	
	private boolean hadError(ErrorHandler errorHandler)
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
