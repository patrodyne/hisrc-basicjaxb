package org.jvnet.basicjaxb.plugin;

import java.io.IOException;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Options;

/**
 * <p>
 * Abstract base class for parameterizable JAXB XJC plugins.
 * </p>
 * <p>
 * This plugin looks for the arguments of the form:
 * </p>
 * <code>-<em>myPlugin</em>-<em>name</em>=<em>value</em></code>
 * <p>where (<code><em>myPlugin</em></code> is the plugin option name)
 * and then invokes <code>set<em>Name</em>(<em>value</em>)</code> on itself.
 * </p>
 * 
 * <p>
 * For instance, the argument <code>-Xfoo-bar=test</code> triggers
 * <code>setBar("test")</code> invocation on the <code>foo</code> plugin.
 * </p>
 * <p>
 * Values are injected using Commons {@link BeanUtils} as bean properties, so
 * types will be converted correspondingly
 * </p>
 * 
 * @author valikov
 */
public abstract class AbstractParameterizablePlugin extends AbstractPlugin
{
	/** Represents the XJC plugin usage format.*/
	public static final String USAGE_FORMAT = "  -%-18s :  %s";

	/**
	 * Parses the arguments and injects values into the beans via properties.
	 */
	@Override
	public int parseArgument(Options opt, String[] args, int start)
		throws BadCommandLineException, IOException
	{
		int consumed = 0;
		final String optionPrefix = "-" + getOptionName() + "-";
		final int optionPrefixLength = optionPrefix.length();
		final String arg = args[start];
		final int equalsPosition = arg.indexOf('=');
		
		if (arg.startsWith(optionPrefix) && equalsPosition > optionPrefixLength)
		{
			final String propertyName = arg.substring(optionPrefixLength, equalsPosition);
			final String value = arg.substring(equalsPosition + 1);
			consumed++;
			try
			{
				BeanUtils.setProperty(this, propertyName, value);
			}
			catch (Exception ex)
			{
				String msg = "Error setting property [" + propertyName + "], value [" + value + "].";
				getLogger().error(msg, ex);
				throw new BadCommandLineException(msg);
			}
		}
		
		return consumed;
	}
}