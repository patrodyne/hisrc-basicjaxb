package org.jvnet.basicjaxb.plugin.setters;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.locator.util.LocatorUtils.getLocation;
import static org.jvnet.basicjaxb.plugin.setters.Customizations.IGNORED_ELEMENT_NAME;
import static org.jvnet.basicjaxb.util.CodeModelUtils.groupMethods;

import java.util.Arrays;
import java.util.Collection;

import javax.xml.namespace.QName;

import org.apache.commons.lang3.Validate;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.plugin.util.FieldOutlineUtils;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldAccessor;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * <p>This plugin generates missing <em>collection</em> setters.</p>
 * 
 * <p>Supported <code>Xsetters-mode</code> values are:</p>
 * <ul>
 *   <li><code>accessor</code>: Uses JAXB-generated accessors (default)</li>
 *   <li><code>direct</code>: Assigns the value list to the field directly</li>
 * </ul>
 * 
 * <p>The <code>accessor</code> mode creates a new list to contain the supplied
 * items; while, the <code>direct</code> mode uses the supplied list, directly.</p>
 * 
 * <p><b>JAXB Specification:</b></p>
 * <p>
 * There is no setter method for a List property. The getter returns the List
 * by reference. An item can be added to the List returned by the getter method
 * using an appropriate method defined on {@link java.util.List}. Rationale for
 * this design in JAXB 1.0 was to enable the implementation to wrapper the list
 * and be able to perform checks as content was added or removed from the List.
 * </p>
 * 
 * @author Alexey Valikov
 */
public class SettersPlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "Xsetters";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "generate setters for collections";

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
	
	private Ignoring ignoring = new CustomizedIgnoring(IGNORED_ELEMENT_NAME);
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
		return Arrays.asList(IGNORED_ELEMENT_NAME);
	}

	/**
	 * An enumeration of this plug-in's setter modes.
	 */
	public static enum Mode
	{
		accessor
		{
			// Add values to a NEW list instance.
			@Override
			public void generateSetter(FieldOutline fieldOutline, JDefinedClass theClass, JMethod setter, JVar value)
			{
				final FieldAccessor accessor = fieldOutline.create(JExpr._this());
				accessor.unsetValues(setter.body());
				accessor.fromRawValue(setter.body()._if(value.ne(JExpr._null()))._then(), "draftRol", value);
			}
		},
		direct
		{
			// Use given list or create a new list.
			@Override
			public void generateSetter(FieldOutline fieldOutline, JDefinedClass theClass, JMethod setter, JVar value)
			{
				final JFieldVar field = theClass.fields().get(fieldOutline.getPropertyInfo().getName(false));
				// Use direct value list or fallback to the accessor.
				if (field != null)
					setter.body().assign(JExpr._this().ref(field), value);
				else
					Mode.accessor.generateSetter(fieldOutline, theClass, setter, value);
			}
		};

		/**
		 * Abstract method to generate a setter. Each <code>enum</code> declaration implements an
		 * appropriate override.
		 *  
		 * @param fieldOutline Representation of a field of {@link ClassOutline}.
		 * @param theClass A generated Java class.
		 * @param setter The setter method.
		 * @param value The setter value.
		 */
		public abstract void generateSetter(FieldOutline fieldOutline, JDefinedClass theClass, JMethod setter, JVar value);
	}

	private Mode mode = Mode.accessor;
	public String getMode()
	{
		return mode.name();
	}
	public void setMode(String mode)
	{
		Validate.notNull(mode);
		try
		{
			this.mode = Mode.valueOf(mode);
		}
		catch (IllegalArgumentException iaex)
		{
			throw new IllegalArgumentException("Unsupported mode [" + mode + "]."
				+ " Supported modes are [accessor] (uses JAXB-generated accessors, default)"
				+ " and [direct] (assigns the value list to the field directly).");
		}
	}

	// Plugin Processing
	
	@Override
	protected void beforeRun(Outline outline, Options options) throws Exception
	{
		setOptions(options);
		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Mode....: " + getMode());
			sb.append("\n  Verbose.: " + isVerbose());
			sb.append("\n  Debug...: " + isDebug());
			info(sb.toString());
		}
	}
	
	@Override
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
		for (final ClassOutline classOutline : outline.getClasses())
		{
			if (!getIgnoring().isIgnored(classOutline))
				processClassOutline(classOutline);
		}
		return true;
	}

	protected void processClassOutline(ClassOutline classOutline)
	{
		final JDefinedClass theClass = classOutline.implClass;
		generateSetters(classOutline, theClass);
	}

	private static final JType[] ABSENT = new JType[0];

	private void generateSetters(ClassOutline classOutline, JDefinedClass theClass)
	{
		boolean addedSetter = false;
		final FieldOutline[] declaredFields = FieldOutlineUtils.filter(classOutline.getDeclaredFields(), getIgnoring());
		for (final FieldOutline fieldOutline : declaredFields)
		{
			final CPropertyInfo fieldInfo = fieldOutline.getPropertyInfo();
			final String publicName = fieldInfo.getName(true);
			final String getterName = "get" + publicName;
			final JMethod getter = theClass.getMethod(getterName, ABSENT);
			if (getter != null)
			{
				final JType type = getter.type();
				final JType rawType = fieldOutline.getRawType();
				final String setterName = "set" + publicName;
				final JMethod boxifiedSetter = theClass.getMethod(setterName, new JType[] { rawType.boxify() });
				final JMethod unboxifiedSetter = theClass.getMethod(setterName, new JType[] { rawType.unboxify() });
				final JMethod setter = boxifiedSetter != null ? boxifiedSetter : unboxifiedSetter;
				if (setter == null)
				{
					final JMethod generatedSetter = theClass.method(JMod.PUBLIC, theClass.owner().VOID, setterName);
					final JVar value = generatedSetter.param(type, "value");
					mode.generateSetter(fieldOutline, theClass, generatedSetter, value);
					
					// Group the new setter with the existing getter.
					groupMethods(theClass, getter, generatedSetter);
					
					addedSetter = true;
					trace("{}, generateSetters; Class={}, Field={}",
						getLocation(fieldInfo.getLocator()), theClass.name(), fieldInfo.getName(false));

				}
			}
		}
		if ( addedSetter )
			debug("{}, generateSetters; Class={}", getLocation(theClass.metadata), theClass.name());
	}
}
