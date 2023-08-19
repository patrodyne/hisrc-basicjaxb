package org.jvnet.basicjaxb.plugin.fluentapi;

import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;

/**
 * Information used to generate a fluent API method.
 * 
 * @author Hanson Char
 */
public class FluentMethodInfo
{
	// Original method to used to derive the fluent API method.
	private final JMethod originalMethod;
	/** Returns the original method for which a fluent API method will be generated. */
	public JMethod getOriginalMethod() { return originalMethod; }
	
	// Type of fluent API method to be generated.
	private final FluentMethodType fluentMethodType;
	public FluentMethodType getFluentMethodType() { return fluentMethodType; }
	
	// True if this method will be overriding a parent method; false otherwise.
	private final boolean isOverride;
	/** Returns true if the fluent API method is one overriding a parent method. */
	public boolean isOverride() { return isOverride; }
	
	// Represents the XJC Fluent API plugin.
	private FluentApiPlugin plugin;
	public FluentApiPlugin getPlugin() { return plugin; }

	/**
	 * Construct with original method, type of fluent API method and parent method override.
	 * 
	 * @param originalMethod Original method to used to derive the fluent API method.
	 * @param fluentMethodType Type of fluent API method to be generated.
	 * @param isOverride True if this method will be overriding a parent method; false otherwise.
	 * @param plugin The XJC Fluent API plugin.
	 */
	public FluentMethodInfo(JMethod originalMethod, FluentMethodType fluentMethodType, boolean isOverride, FluentApiPlugin plugin)
	{
		this.originalMethod = originalMethod;
		this.fluentMethodType = fluentMethodType;
		this.isOverride = isOverride;
		this.plugin = plugin;
	}

	/**
	 * Creates a fluent API method in the given class.
	 * 
	 * @param implClass The class to receive a fluent API method.
	 */
	public void createFluentMethod(JDefinedClass implClass)
	{
		getFluentMethodType().createFluentMethod(implClass, this);
	}
}
