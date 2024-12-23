package org.swixml.factory;

import static java.lang.reflect.Modifier.isAbstract;
import static java.lang.reflect.Modifier.isPublic;
import static org.swixml.LogUtil.logger;
import static org.swixml.jsr295.BindingUtils.isELPattern;

import java.awt.LayoutManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConstructorUtils;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.beansbinding.PropertyResolutionException;
import org.swixml.Factory;
import org.swixml.Parser;
import org.swixml.SwingEngine;
import org.swixml.dom.Attribute;
import org.swixml.processor.ButtonGroupTagProcessor;
import org.swixml.processor.ConstraintsTagProcessor;
import org.swixml.processor.ELTagProcessor;
import org.swixml.processor.TagProcessor;
import org.w3c.dom.Element;

import jakarta.el.ELContext;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author sorrentino
 */
public class BeanFactory implements Factory
{
	private SwingEngine<?> swingEngine;
	@Override
	public SwingEngine<?> getSwingEngine() { return swingEngine; }
	@Override
	public void setSwingEngine(SwingEngine<?> swingEngine) { this.swingEngine = swingEngine; }
	
	private class MethodMap
	{
		final java.util.Map<String, java.util.List<Method>> nameMap;

		public MethodMap(int capacity)
		{
			nameMap = new java.util.HashMap<String, java.util.List<Method>>(capacity);
		}

		public void put(String name, Method m)
		{
			java.util.List<Method> methods = nameMap.get(name);
			if ( methods == null )
			{
				methods = new java.util.ArrayList<Method>(2);
				nameMap.put(name, methods);
			}
			methods.add(m);
		}

		public java.util.List<Method> get(String name)
		{
			return nameMap.get(name);
		}

		public Method get(String name, Class<?> type)
		{
			java.util.List<Method> methods = nameMap.get(name);
			if ( methods == null || methods.isEmpty() )
				return null;
			
			// if( methods.size()==1 ) return methods.get(0);
			for ( Method m : methods )
			{
				if ( m.getParameterTypes()[0].equals(type) )
					return m;
			}
			
			return null;
		}

		public Collection<Method> values()
		{
			java.util.List<Method> result = new java.util.ArrayList<Method>();
			
			for ( java.util.List<Method> lm : nameMap.values() )
				result.addAll(lm);
			
			return result;
		}
	}

	private MethodMap nameMap;
	public MethodMap getNameMap() { return nameMap; }
	public void setNameMap(MethodMap nameMap) { this.nameMap = nameMap; }

	private Class<?> template;
	@Override
	public Class<?> getTemplate() { return template; }
	public void setTemplate(Class<?> template) { this.template = template; }

	private TagProcessor tagProcessor;
	public TagProcessor getTagProcessor() { return tagProcessor; }
	public void setTagProcessor(TagProcessor tagProcessor) { this.tagProcessor = tagProcessor; }
	
	/**
	 * Construct with bean class and no tag tagProcessor.
	 * 
	 * @param beanClass The bean to manage.
	 */
	public BeanFactory(Class<?> beanClass, SwingEngine<?> engine)
	{
		this(beanClass, null, engine);
	}

	/**
	 * Construct with bean class and tag tagProcessor.
	 * 
	 * @param beanClass The bean to manage.
	 * @param processor Interface for processing tags.
	 */
	public BeanFactory(Class<?> beanClass, TagProcessor processor, SwingEngine<?> engine)
	{
		setSwingEngine(engine);
		
		if ( null == beanClass )
			throw new IllegalArgumentException("beanClass is null!");
		
		setTemplate(beanClass);
		setTagProcessor(processor);
		
		Method[] mm = beanClass.getMethods();
		setNameMap(new MethodMap(mm.length));
		for ( Method m : mm )
		{
			int modifier = m.getModifiers();
			String name = m.getName();
			m.isAnnotationPresent(Deprecated.class);
			if
			(
				isPublic(modifier) && !isAbstract(modifier)
				&& name.startsWith("set")
				&& (m.getParameterTypes().length == 1)
			)
			{
				getNameMap().put(name.substring(3).toLowerCase(), m);
			}
		}
	}

	@Override
	public Object create(SwingEngine<?> engine, Element element)
		throws Exception
	{
		setSwingEngine(engine);
		return getTemplate().getDeclaredConstructor().newInstance();
	}

	/**
	 * 
	 */
	@Override
	public Object newInstance(SwingEngine<?> engine, Object... parameter)
		throws InstantiationException, IllegalAccessException, InvocationTargetException
	{
		setSwingEngine(engine);
		/*
		 * Class<?> types[] = new Class<?>[ parameter.length ]; int i=0; for(
		 * Object p : parameter ) { types[i++] = p.getClass(); }
		 */
		try
		{
			// get runtime class of the parameter
			// return template.getConstructor(types).newInstance(parameter);
			return ConstructorUtils.invokeConstructor(getTemplate(), parameter);
		}
		catch (NoSuchMethodException ex)
		{
			logger.error("newInstance", ex);
		}
		catch (SecurityException ex)
		{
			logger.error("newInstance", ex);
		}
		return null;
	}

	@Override
	public Collection<Method> getSetters()
	{
		return getNameMap().values();
	}

	@Override
	public Class<?>[] getPropertyType(Object bean, Attribute attr)
	{
		String name = attr.getLocalName();
		java.util.List<Method> methods = getNameMap().get(name.toLowerCase());
		if ( null == methods || methods.isEmpty() )
		{
			return null;
		}
		Class<?> result[] = new Class<?>[methods.size()];
		int i = 0;
		for ( Method m : methods )
		{
			result[i++] = m.getParameterTypes()[0];
		}
		return result;
	}

	@Override
	public void setProperty(Object bean, Attribute attr, Object value, Class<?> type)
		throws Exception
	{
		if ( null == attr )
		{
			throw new IllegalArgumentException("attr is null!");
		}
		if ( null == bean )
		{
			throw new IllegalArgumentException("bean is null!");
		}
		if ( null == value )
		{
			return;
		}
		final String name = attr.getLocalName();
		Method m = getNameMap().get(name.toLowerCase(), type);
		if ( null == m )
		{
			BeanUtils.setProperty(bean, name, value);
			// throw new NoSuchMethodException(name);
		}
		else
			m.invoke(bean, value);
	}

	/**
	 * 
	 * @param parser
	 * @param parent
	 * @param child
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean process(Parser parser, Object parent, Element child, LayoutManager layoutMgr)
		throws Exception
	{
		boolean result = false;
		if ( null != getTagProcessor() )
		{
			result = getTagProcessor().process(parser, parent, child, layoutMgr);
		}
		if ( !result )
		{
			result = ButtonGroupTagProcessor.instance.process(parser, parent, child, layoutMgr);
			if ( !result )
			{
				result = ELTagProcessor.instance.process(parser, parent, child, layoutMgr);
			}
			if ( !result )
			{
				result = ConstraintsTagProcessor.instance.process(parser, parent, child, layoutMgr);
			}
		}
		return result;
	}

	public final Object getAttributeValue(ELContext elContext, Object owner, Attribute attr)
	{
		Object result = null;

		if ( isELPattern(attr.getValue()) )
		{
			try
			{
				ELProperty<Object, Object> p = ELProperty.create(elContext, attr.getValue());
				if ( !p.isReadable(owner) )
				{
					logger.warn("property " + attr.getValue() + " is not readable!");
					return result;
				}
				result = p.getValue(owner);
			}
			catch (PropertyResolutionException ex)
			{
				logger.warn("EL variable " + attr.getValue() + " doesn't exist!", ex);
			}
		}
		else
			return attr.getValue();
		
		return result;
	}
}
