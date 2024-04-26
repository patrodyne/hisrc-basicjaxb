package org.jvnet.basicjaxb.plugin.classname;

import static java.lang.String.format;
import static org.jvnet.basicjaxb.lang.StringUtils.isBlank;
import static org.jvnet.basicjaxb.plugin.hashcode.Customizations.IGNORED_ELEMENT_NAME;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.SourceVersion;
import javax.xml.namespace.QName;

import org.glassfish.jaxb.core.api.impl.NameConverter;
import org.glassfish.jaxb.core.api.impl.NameConverter.Standard;
import org.jvnet.basicjaxb.plugin.AbstractParameterizablePlugin;
import org.jvnet.basicjaxb.plugin.AbstractPlugin;
import org.jvnet.basicjaxb.plugin.Customizations;
import org.jvnet.basicjaxb.plugin.CustomizedIgnoring;
import org.jvnet.basicjaxb.plugin.Ignoring;
import org.jvnet.basicjaxb.util.FieldAccessorFactory;
import org.jvnet.basicjaxb.util.PropertyFieldAccessorFactory;
import org.xml.sax.ErrorHandler;

import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Options;
import com.sun.tools.xjc.model.Model;
import com.sun.tools.xjc.outline.Outline;

/**
 * Modify all generated Java class names in the JAXB code model by
 * adding a prefix, a suffix or by changing the name by substitution.
 * 
 * <p><b>Note:</b> XJC also calls {@link NameConverter#toClassName(String)}
 * to derive field names, by default. Thus, field names and their property
 * method named will be customized per this plugin too.</p>
 * 
 * <p><b>Note:</b> For any single schema element, the JAXB {@code <class>}
 * binding binds a schema element to a Java class name.</p>
 * 
 * <p>You can use the declaration to customize the name for an interface
 * or the class that implements an interface. The JAXB class generator
 * supports the following syntax for {@code <class>} customizations:</p>
 *
 * <p>
 * {@code <class [ name = "className"] > }
 * </p>
 *
 * <p>The name attribute specifies the name of the derived Java interface.</p>
 */
public class ClassNamePlugin extends AbstractParameterizablePlugin
{
	/** Name of Option to enable this plugin. */
	private static final String OPTION_NAME = "XclassName";
	
	/** Description of Option to enable this plugin. */
	private static final String OPTION_DESC = "customize the generated class name";

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

	// -XclassName-prefix=Before
	private String prefix;
	public String getPrefix() { return prefix; }
	public void setPrefix(String prefix) { this.prefix = prefix; }

	// -XclassName-suffix=After
	private String suffix;
	public String getSuffix() { return suffix; }
	public void setSuffix(String suffix) { this.suffix = suffix; }

	// -XclassName-infix=Within
	private String infix;
	public String getInfix() { return infix; }
	public void setInfix(String infix) { this.infix = infix; }
	
	private String[] infixParts;
	public String[] getInfixParts()
	{
		if ( !isBlank(getInfix()) && (infixParts == null) )
		{
			// infix: /regex/replace/
			String dlm = getInfix().substring(0, 1);
			// Use a split limit of 4 to get 'replace',
			// even when it is blank, then:
			//   regex = parts[1]
			//   replace = parts[2]
			String[] parts = getInfix().split(dlm,4);
			if ( parts.length == 4 )
				setInfixParts(parts);
		}
		return infixParts;
	}
	public void setInfixParts(String[] infixParts)
	{
		this.infixParts = infixParts;
	}

	private Pattern infixPattern = null;
	public Pattern getInfixPattern()
	{
		if ( (getInfixParts() != null) && (infixPattern == null) )
			setInfixPattern(Pattern.compile(getInfixParts()[1]));
		return infixPattern;
	}
	public void setInfixPattern(Pattern infixPattern)
	{
		this.infixPattern = infixPattern;
	}
	
	private Map<String,String> toClassNameImplMap;
	public Map<String, String> getToClassNameImplMap()
	{
		if ( toClassNameImplMap == null )
			setToClassNameImplMap(new HashMap<>());
		return toClassNameImplMap;
	}
	public void setToClassNameImplMap(Map<String, String> toClassNameImplMap)
	{
		this.toClassNameImplMap = toClassNameImplMap;
	}

	private FieldAccessorFactory fieldAccessorFactory = PropertyFieldAccessorFactory.INSTANCE;
	public FieldAccessorFactory getFieldAccessorFactory()
	{
		return fieldAccessorFactory;
	}
	public void setFieldAccessorFactory(FieldAccessorFactory fieldAccessorFactory)
	{
		this.fieldAccessorFactory = fieldAccessorFactory;
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
	protected void beforePostProcessModel(Model model)
	{

		if ( isInfoEnabled() )
		{
			StringBuilder sb = new StringBuilder();
			sb.append(LOGGING_START);
			sb.append("\nParameters");
			sb.append("\n  Infix.....: " + getInfix());
			sb.append("\n  Prefix....: " + getPrefix());
			sb.append("\n  Suffix....: " + getSuffix());
			sb.append("\n  Verbose...: " + isVerbose());
			sb.append("\n  Debug.....: " + isDebug());
			info(sb.toString());
		}
	}
	
	@Override
	protected void afterPostProcessModel(Model model, ErrorHandler errorHandler)
	{
		if ( isInfoEnabled() )
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
     * @throws BadCommandLineException When there is a name converter conflict.
     */
	@Override
	protected void postProcessModel(Model model) throws BadCommandLineException
	{
	}
	
	@Override
	protected void init(Options options) throws Exception
	{
		super.init(options);
		NameConverter custom = null;
		switch ( options.getSchemaLanguage() )
		{
			case RELAXNG:
			case RELAXNG_COMPACT:
			    custom = new Standard()
			    {
			    	@Override
			    	public String toClassName(String s)
			    	{
			    		String className = super.toClassName(s);
			    		return toClassNameImpl(className);
			    	}
			        @Override
			        public String toConstantName( String token )
			        {
			            String name = super.toConstantName(token);
			            if(!SourceVersion.isKeyword(name))
			                return name;
			            else
			                return '_'+name;
			        }
			    };
				break;
			case DTD:
			case WSDL:
			case XMLSCHEMA:
			default:
			    custom = new Standard()
			    {
			    	@Override
			    	public String toClassName(String s)
			    	{
			    		String className = super.toClassName(s);
			    		return toClassNameImpl(className);
			    	}
			    };
		}
	    options.setNameConverter(custom, this);
	}

	// Customize a className with a infix, prefix, and/or suffix.
	private String toClassNameImpl(String oldClassName)
	{
		String newClassName = oldClassName;
		if ( getToClassNameImplMap().containsKey(oldClassName) )
			newClassName = getToClassNameImplMap().get(oldClassName);
		else
		{
			if ( !isBlank(getInfix()) )
			{
				Matcher matcher = getInfixPattern().matcher(newClassName);
				newClassName = matcher.replaceAll(getInfixParts()[2]);
			}
			if ( !isBlank(getPrefix()) )
				newClassName = getPrefix() + newClassName;
			if ( !isBlank(getSuffix()) )
				newClassName = newClassName + getSuffix();
			getToClassNameImplMap().put(oldClassName, newClassName);
			debug("toClassNameImpl; {} -> {}", oldClassName, newClassName);
		}
		return newClassName;
	}
}
