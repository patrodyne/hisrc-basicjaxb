package org.swixml.factory;

import java.awt.LayoutManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;

import org.swixml.Factory;
import org.swixml.LogAware;
import org.swixml.Parser;
import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;
import org.w3c.dom.Element;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 */
public class ScriptFactory
	implements Factory, LogAware
{
	private SwingEngine<?> swingEngine;

	@Override
	public SwingEngine<?> getSwingEngine()
	{
		return swingEngine;
	}

	@Override
	public void setSwingEngine(SwingEngine<?> swingEngine)
	{
		this.swingEngine = swingEngine;
	}

	@Override
	public Object create(SwingEngine<?> engine, Element element)
		throws Exception
	{
		setSwingEngine(engine);
		return null;
	}

	@Override
	public Object newInstance(SwingEngine<?> engine, Object... parameter)
		throws InstantiationException, IllegalAccessException, InvocationTargetException
	{
		setSwingEngine(engine);
		return null;
	}

	@Override
	public Class<?> getTemplate()
	{
		return Void.class;
	}

	@Override
	public Collection<Method> getSetters()
	{
		return Collections.emptyList();
	}

	@Override
	public Class<?>[] getPropertyType(Object bean, String name)
	{
		return null;
	}

	@Override
	public void setProperty(Object bean, Attribute attr, Object value, Class<?> type)
		throws Exception
	{
	}

	@Override
	public boolean process(Parser p, Object parent, Element child, LayoutManager layoutMgr)
		throws Exception
	{
		return false;
	}
}
