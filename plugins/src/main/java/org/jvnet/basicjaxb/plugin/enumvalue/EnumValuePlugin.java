package org.jvnet.basicjaxb.plugin.enumvalue;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.equals.Customizations.IGNORED_ELEMENT_NAME;

import java.util.Arrays;
import java.util.Collection;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.lang.EnumValue;
import org.jvnet.basicjaxb.lang.StringUtils;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.util.ClassUtils;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.tools.xjc.model.CEnumLeafInfo;
import com.sun.tools.xjc.outline.Aspect;
import com.sun.tools.xjc.outline.EnumOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * The <b>EnumValue</b> plugin implements the {@link EnumValue} interface on <code>enum</code> types.
 * This provides generic access to the original <code>enum</code> values.
 */
public class EnumValuePlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XenumValue";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generated @XmlEnums implement generic EnumValue<T> interface";

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

	// Plugin Processing
	
	@Override
	protected void beforeRun(Outline outline) throws Exception
	{
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
		for (final EnumOutline enumOutline : outline.getEnums())
		{
			if (!getIgnoring().isIgnored(enumOutline))
				processEnumOutline(enumOutline);
		}
		return !hadError(outline.getErrorReceiver());
	}

	protected void processEnumOutline(EnumOutline enumOutline)
	{
		CEnumLeafInfo enumLeafInfo = enumOutline.target;
		JClass enumType = enumLeafInfo.base.toType(enumOutline.parent(), Aspect.EXPOSED).boxify();
		final JDefinedClass theClass = enumOutline.clazz;
		ClassUtils._implements(theClass, theClass.owner().ref(EnumValue.class).narrow(enumType));
		final JMethod enumValue$enumValue = theClass.method(JMod.PUBLIC, enumType, "enumValue");
		enumValue$enumValue.annotate(Override.class);
		enumValue$enumValue.body()._return(JExpr._this().invoke("value"));
		
		if ( isDebugEnabled() )
		{
			String location = StringUtils.toLocation(enumLeafInfo.getLocator());
			QName typeName = enumLeafInfo.getTypeName();
			JDefinedClass implClass = enumOutline.getImplClass();
			debug("{}, TypeName={}, ImplName={}", location, typeName, implClass.name());
		}
	}
}
