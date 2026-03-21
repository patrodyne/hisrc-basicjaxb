package org.jvnet.basicjaxb.plugin.privatemember;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.plugin.util.OutlineUtils.filter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMod;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * An XJC plugin to change members visibility to private.
 *
 * <ul>
 * <li>Source - <a href="https://stackoverflow.com/a/9391159">SO #9391159SO</a></li>
 * <li>Posted by tom, modified by community. See post 'Timeline' for change history</li>
 * <li>Retrieved 2026-03-21, License - CC BY-SA 3.0</li>
 * <li> Tom wrote, "Feel free to use this if you want."</li>
 * </ul>
 */
public class PrivateMemberPlugin
	extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XprivateMember";

	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "change member(s) visibility to private";

	/** Creates a new <code>PrivateMemberPlugin</code> instance. */
	public PrivateMemberPlugin()
	{
	}

	/** PrivateMemberPlugin uses "-" + OPTION_NAME as the XJC argument */
	@Override
	public String getOptionName()
	{
		return "XprivateMember";
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

	// Plugin Processing

	@Override
	protected void beforeRun(Outline outline) throws Exception
	{
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Verbose..: " + isVerbose());
			sb.append("\n  Debug....: " + isDebug());
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
	 *	 <li>Look for qualifying fields, fields qualify that:
	 *	   <ul>
	 *		 <li>Are generated from XSD description</li>
	 *		 <li>The XSD description is of type <code>xsd:element</code>
	 *			 (code level default values are not necessary for fields
	 *			 generated from attributes)</li>
	 *		 <li>A default value is specified</li>
	 *		 <li>Map to one of the supported types</li>
	 *	   </ul>
	 *	 </li>
	 *	 <li>Add a new initialization expression to every qualifying BOUND or field:
	 *	   <ul>
	 *		 <li>An element BOUND qualifies when the field is nullable;</li>
	 *		 <li>Otherwise, the field qualifies to receive the initialization expression</li>
	 *	   </ul>
	 *	 </li>
	 *
	 * </ul>
	 *
	 * <p>
	 * Note that this method is invoked only when a plugin is activated.
	 * </p>
	 *
	 * @param outline
	 *		This object allows access to various generated code.
	 *
	 * @return
	 *		If the add-on executes successfully, return true.
	 *		If it detects some errors but those are reported and
	 *		recovered gracefully, return false.
	 *
	 * @throws Exception
	 *		After an error is reported to {@link ErrorHandler}, a
	 *		{@link SAXException} may be thrown to indicate a fatal unrecoverable
	 *		error. {@link ErrorHandler} itself may throw it, if it chooses
	 *		not to recover from the error.
	 */
	@Override
	public boolean run(Outline outline) throws Exception
	{
		// Filter ignored class outlines
		for (final ClassOutline classOutline : filter(outline, getIgnoring()))
			processClassOutline(outline, classOutline);

		return !hadError(outline.getErrorReceiver());
	}

	/**
	 * Process the XJC {@link Outline} instance. The goal is to add a default value to
	 * initialize all non-ignored fields from the given {@link Outline} instance.
	 *
	 * @param outline An outline from the XJC framework.
	 * @param classOutline A class outline from the XJC framework.
	 */
	protected void processClassOutline(Outline outline, ClassOutline classOutline)
	{
        // Get handle to JModel representing the field
        JDefinedClass theClass = classOutline.implClass;

        // Get the field variable for the given fieldInfo private name
        // from the map of fields for theClass. .
        Map<String, JFieldVar> fields = theClass.fields();

		// Filter out the ignored class outline's fields.
		FieldOutline[] declaredFilteredFields = filter(classOutline.getDeclaredFields(), getIgnoring());

		// check all Fields in Class
		for (FieldOutline fieldOutline : declaredFilteredFields)
		{
			// Get the code model field for the current property name.
	        JFieldVar field = fields.get(fieldOutline.getPropertyInfo().getName(false));

			// never do something with serialVersionUID if it exists.
			if (!field.name().equals("serialVersionUID"))
			{
				// only try to change members that are not private
				if (field.mods().getValue() != JMod.PRIVATE)
					field.mods().setPrivate();
			}
		}
	}
}
