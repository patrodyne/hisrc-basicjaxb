package org.swixml;

import java.util.HashMap;
import java.util.Map;

import org.swixml.factory.BeanFactory;
import org.swixml.processor.TagProcessor;

/**
 * A skeletal implementation of a TagLibrary.
 * 
 * <p>A TagLibrary has a collection of Factories. Every Tag is mapped to a Factory
 * which is used to build the java object during document parsing. Date: Dec 9,
 * 2002</p>
 *
 * @author <a href="mailto:wolf@paulus.com">Wolf Paulus</a>
 * @version $Revision: 1.1 $
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public abstract class TagLibrary
{
	private SwingEngine<?> swingEngine;

	public SwingEngine<?> getSwingEngine()
	{
		return swingEngine;
	}

	public void setSwingEngine(SwingEngine<?> swingEngine)
	{
		this.swingEngine = swingEngine;
	}

	private Map<String, Factory> tags = new HashMap<String, Factory>(100);

	/**
	 * Constructs a new TagLibrary and registers all factories.
	 */
	public TagLibrary()
	{
		registerTags();
	}

	/**
	 * Registers all factories for the TagLibrary.
	 */
	abstract protected void registerTags();

	/**
	 * 
	 * @param name
	 * @param template
	 * @param processor
	 */
	public void registerTag(String name, Class<?> template, TagProcessor processor)
	{
		// registerTag( name, new DefaultFactory( template ) );
		registerTag(name, new BeanFactory(template, processor, getSwingEngine()));
	}

	/**
	 * Registers a class for the given tag name
	 *
	 * @param name <code>String</code> the tag's name
	 * @param template <code>Class</code> the java class that represents the tag
	 */
	public void registerTag(String name, Class<?> template)
	{
		// registerTag( name, new DefaultFactory( template ) );
		registerTag(name, new BeanFactory(template, getSwingEngine()));
	}

	/**
	 * Registers a factory for the given tag name
	 *
	 * @param name <code>String</code> the tag's name
	 * @param factory <code>FactoryFactory</code> factory to create an Instance
	 *            of the tag
	 */
	public void registerTag(String name, Factory factory)
	{
		tags.put(name.toLowerCase(), factory);
	}

	/**
	 * Un-registers (removes) a registered tag.
	 *
	 * @param name <code>String</code> the tag's name
	 * @return <code>boolean</code> true if tag was registered befoire and now
	 *         successfuly removed.
	 */
	public boolean unregisterTag(String name)
	{
		return (null != tags.remove(name));
	}

	/**
	 * @return <code>Map</code> - all registered tags.
	 * 
	 *         <pre>
	 * Use athe tag names to get to the factories
	 *         </pre>
	 */
	public Map<String, Factory> getTagClasses()
	{
		return tags;
	}

	/**
	 * Returns the Factory that is currently registered for the given Tag name
	 * 
	 * @param name <code>String</code>
	 * @return <code>Factory</code> - regsitered for the given tag name
	 */
	public Factory getFactory(String name)
	{
		return (Factory) tags.get(name.toLowerCase());
	}

	/**
	 * Returns the Factory that is currently registered for the given Tag name
	 * 
	 * @param template <code>Class</code>
	 * @return <code>Factory</code> - regsitered for the given tag name
	 */
	public Factory getFactory(Class<?> template)
	{
		for ( Factory f : tags.values() )
		{
			if ( f.getTemplate().equals(template) )
			{
				return f;
			}
		}
		return null;
	}
}
