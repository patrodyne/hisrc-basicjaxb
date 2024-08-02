package org.swixml.jsr296;

import java.util.Set;
import org.jdesktop.application.ResourceMap;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public class SystemResourceMap extends ResourceMap
{
	public SystemResourceMap()
	{
		super((ResourceMap) null, ClassLoader.getSystemClassLoader(), "system");
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Set<String> getResourceKeySet()
	{
		return (Set<String>)(Set<?>) System.getProperties().keySet();
	}

	@Override
	protected boolean containsResourceKey(String key)
	{
		return System.getProperties().containsKey(key);
	}

	@Override
	protected Object getResource(String key)
	{
		return System.getProperty(key);
	}

	@Override
	protected void putResource(String key, Object value)
	{
		// Read Only
	}
}
