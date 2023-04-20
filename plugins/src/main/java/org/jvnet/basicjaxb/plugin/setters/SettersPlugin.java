package org.jvnet.basicjaxb.plugin.setters;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.plugin.setters.Customizations.IGNORED_ELEMENT_NAME;

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
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.FieldAccessor;
import com.sun.tools.xjc.outline.FieldOutline;
import com.sun.tools.xjc.outline.Outline;

/**
 * This plugin generates missing <em>collection</em> setters.
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

	public static enum Mode
	{
		accessor
		{
			@Override
			public void generateSetter(FieldOutline fieldOutline, JDefinedClass theClass, JMethod setter, JVar value)
			{
				final FieldAccessor accessor = fieldOutline.create(JExpr._this());
				accessor.unsetValues(setter.body());
				accessor.fromRawValue(setter.body()._if(value.ne(JExpr._null()))._then(), "draft", value);
			}
		},
		direct
		{
			@Override
			public void generateSetter(FieldOutline fieldOutline, JDefinedClass theClass, JMethod setter, JVar value)
			{
				final JFieldVar field = theClass.fields().get(fieldOutline.getPropertyInfo().getName(false));
				if (field != null)
				{
					setter.body().assign(JExpr._this().ref(field), value);
				}
				else
				{
					// Fallback to the accessor
					Mode.accessor.generateSetter(fieldOutline, theClass, setter, value);
				}
			}
		};

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
				+ " and [direct] (assigns the value to the field directly).");
		}
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
			sb.append("\n  Mode.: " + getMode());
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
	 * Run the plugin with and XJC {@link Outline} and {@link Options}.
	 * </p>
	 * 
     * <p>
     * <b>Note:</b> This method is invoked only when a plugin is activated.
     * </p>
	 *
     * @param outline
     *      This object allows access to various generated code.
     * 
     * @param options
     * 		The invocation configuration for XJC.
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
	public boolean run(Outline outline, Options options) throws Exception
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
		final FieldOutline[] declaredFields = FieldOutlineUtils.filter(classOutline.getDeclaredFields(), getIgnoring());
		for (final FieldOutline fieldOutline : declaredFields)
		{
			final String publicName = fieldOutline.getPropertyInfo().getName(true);
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
				}
			}
		}
	}
}
