package org.jvnet.basicjaxb.plugin.wildcard;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.wildcard.Customizations.LAX_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.wildcard.Customizations.SKIP_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.wildcard.Customizations.STRICT_ELEMENT_NAME;
import static org.jvnet.basicjaxb.util.CustomizationUtils.containsCustomization;

import java.util.Arrays;
import java.util.Collection;

import javax.xml.namespace.QName;

import org.glassfish.jaxb.core.v2.model.core.WildcardMode;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.xml.sax.ErrorHandler;

import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.CReferencePropertyInfo;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.Outline;

/**
 * This plugin specifies wildcard mode for the wildcard property.
 * 
 * @author Alexey Valikov
 */
public class WildcardPlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xwildcard";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "specify the wildcard mode for wildcard properties";

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
			sb.append("\n  Verbose.: " + isVerbose());
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
	public void postProcessModel(Model model)
	{
		final boolean laxModel = containsCustomization(model, LAX_ELEMENT_NAME);
		final boolean skipModel = containsCustomization(model, SKIP_ELEMENT_NAME);
		final boolean strictModel = containsCustomization(model, STRICT_ELEMENT_NAME);
		
		for (CClassInfo classInfo : model.beans().values())
		{
			final boolean laxClassInfo = containsCustomization(classInfo, LAX_ELEMENT_NAME);
			final boolean skipClassInfo = containsCustomization(classInfo, SKIP_ELEMENT_NAME);
			final boolean strictClassInfo = containsCustomization(classInfo, STRICT_ELEMENT_NAME);
			
			for (CPropertyInfo propertyInfo : classInfo.getProperties())
			{
				if (propertyInfo instanceof CReferencePropertyInfo)
				{
					final CReferencePropertyInfo referencePropertyInfo = (CReferencePropertyInfo) propertyInfo;
					final boolean laxPropertyInfo = containsCustomization(referencePropertyInfo, LAX_ELEMENT_NAME);
					final boolean skipPropertyInfo = containsCustomization(referencePropertyInfo, SKIP_ELEMENT_NAME);
					final boolean strictPropertyInfo = containsCustomization(referencePropertyInfo, STRICT_ELEMENT_NAME);
					
					// Model
					if (laxModel)
						referencePropertyInfo.setWildcard(WildcardMode.LAX);
					else if (skipModel)
						referencePropertyInfo.setWildcard(WildcardMode.SKIP);
					else if (strictModel)
						referencePropertyInfo.setWildcard(WildcardMode.STRICT);

					// ClassInfo
					if (laxClassInfo)
						referencePropertyInfo.setWildcard(WildcardMode.LAX);
					else if (skipClassInfo)
						referencePropertyInfo.setWildcard(WildcardMode.SKIP);
					else if (strictClassInfo)
						referencePropertyInfo.setWildcard(WildcardMode.STRICT);

					// PropertyInfo
					if (laxPropertyInfo)
						referencePropertyInfo.setWildcard(WildcardMode.LAX);
					else if (skipPropertyInfo)
						referencePropertyInfo.setWildcard(WildcardMode.SKIP);
					else if (strictPropertyInfo)
						referencePropertyInfo.setWildcard(WildcardMode.STRICT);
				}
			}
		}
	}

	@Override
	public Collection<QName> getCustomizationElementNames()
	{
		return Arrays.asList(LAX_ELEMENT_NAME, SKIP_ELEMENT_NAME, STRICT_ELEMENT_NAME);
	}
}
