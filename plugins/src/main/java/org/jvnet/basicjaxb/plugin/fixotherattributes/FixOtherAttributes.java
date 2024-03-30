package org.jvnet.basicjaxb.plugin.fixotherattributes;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.util.LocatorUtils.toLocation;

import java.lang.reflect.Field;
import java.util.Map.Entry;

import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JType;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * <p>
 * This plugin fixes an issue when using episodes and extending <code>xs:anyType</code>. An extension of
 * <code>xs:anyType</code> generates a field named <code>otherAttributes</code> with the annotation
 * <code>@XmlAnyAttribute</code>. An extension of the extension should inherit the field and its
 * getter/setter methods and this is the behavior when XJC is run in a single episode. But, as of about
 * XJC 4.0.2, binding with an episode causes the second extension to duplicate and override the first
 * <code>otherAttributes</code> field and its annotation <code>@XmlAnyAttribute</code>. This leads to:
 * </p>
 * 
 * {@code}<pre>
 * org.glassfish.jaxb.runtime.v2.runtime.IllegalAnnotationsException:
 * Can't have @XmlAnyAttribute when a base class has it already.
 * </pre>{@code}
 * 
 * <p>
 * This plugin iterates over the class outlines and removes duplicate <code>otherAttributes</code>
 * field(s) plus the field's associated <code>@XmlAnyAttribute</code>, <code>getter</code> and
 * <code>setter</code> methods.
 * </p>
 * 
 * <p>
 * <b>Note:</b> This plugin requires JAR(s) from previous episodes be added as dependencies
 * to the XJC classpath. In particular, as dependencies to the Maven plugin used to run XJC.
 * </p>
 * 
 * @see <a href="https://github.com/eclipse-ee4j/jaxb-ri/issues/1735">JAXB-RI-1735</a>
 * @see <a href="https://github.com/eclipse-ee4j/jaxb-ri/issues/1356">JAXB-RI-1356</a>
 */
public class FixOtherAttributes extends AbstractPlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XfixOtherAttributes";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "removes duplicate otherAttributes property";

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

	/** Represents the JAXB/XJC generated  "otherAttributes" field */
	private static final String OTHER_ATTRIBUTES = "otherAttributes";

	/** Represents the JAXB/XJC generated  "getOtherAttributes" method */
	private static final String GET_OTHER_ATTRIBUTES = "getOtherAttributes";

	/** Represents a no-argument method signature */
	private static final JType[] NO_ARGS = new JType[0];

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
		for (ClassOutline classOutline : outline.getClasses())
			processClassOutline(classOutline);
		
		return !hadError(outline.getErrorReceiver());
	}

	private void processClassOutline(ClassOutline classOutline) throws ClassNotFoundException
	{
		final JDefinedClass theClass = classOutline.implClass;
		for ( Entry<String, JFieldVar> fieldEntry : theClass.fields().entrySet() )
		{
			if ( OTHER_ATTRIBUTES.equals(fieldEntry.getValue().name()) )
			{
				// Requires that JAR(s) from previous episodes be on the XJC classpath!
				Class<?> superClass = Class.forName(theClass._extends().fullName());
				if ( hasField(superClass, OTHER_ATTRIBUTES) )
				{
					JMethod method = theClass.getMethod(GET_OTHER_ATTRIBUTES, NO_ARGS);
					if ( method != null )
						removeFieldAndMethod(theClass, fieldEntry, method, classOutline.target);
				}
			}
		}
		debug("{}, processClassOutline; Class={}", toLocation(theClass.metadata), theClass.name());
	}

	private void removeFieldAndMethod(JDefinedClass theClass, Entry<String, JFieldVar> fieldEntry, JMethod method, CClassInfo target)
	{
		theClass.methods().remove(method);
		theClass.removeField(fieldEntry.getValue());
		target.hasAttributeWildcard(false);
		trace("{}, removeFieldAndMethod; Class={}; Field={}", toLocation(theClass.metadata), theClass.name(), fieldEntry.getValue().name());
	}
	
	// Search the declared fields of the given class and
	// of all its superclasses and superinterfaces.
    private boolean hasField(Class<?> clazz, String name)
    {
    	if ( clazz != null )
    	{
	        for ( Field field : clazz.getDeclaredFields() )
	        {
	            if (field.getName().equals(name))
	                return true;
	        }
	        return hasField(clazz.getSuperclass(), name);
    	}
    	else
    		return false;
    }

}
