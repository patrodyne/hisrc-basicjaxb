package org.jdesktop.beansbinding;

import static java.lang.Boolean.TRUE;
import static org.jdesktop.beansbinding.ext.BeanAdapterFactory.getAdapter;
import static org.jdesktop.beansbinding.ext.BeanAdapterFactory.getAdapterPropertyDescriptors;

import java.beans.FeatureDescriptor;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jakarta.el.BeanELResolver;
import jakarta.el.ELContext;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class BeanAdapterELResolver extends BeanELResolver
{
	/**
	 * Iterate over the set of JavaBeans properties available on the base
	 * object.
	 * 
	 * <p>Uses <code>swixml/src/main/resources/META-INF/services</code></p>
	 */
	@Override
	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context, Object base)
	{
		Iterator<FeatureDescriptor> superDescriptors = super.getFeatureDescriptors(context, base);
		if ( base != null )
		{
			List<PropertyDescriptor> pds = getAdapterPropertyDescriptors(base.getClass());
			if ( pds.size() > 0 )
			{
				Map<String, FeatureDescriptor> fdMap = new HashMap<String, FeatureDescriptor>();
				while (superDescriptors.hasNext())
				{
					FeatureDescriptor fd = superDescriptors.next();
					fdMap.put(fd.getName(), fd);
				}
				for ( PropertyDescriptor pd : pds )
				{
					if ( pd.getPropertyType() != null )
					{
						pd.setValue(TYPE, pd.getPropertyType());
						pd.setValue(RESOLVABLE_AT_DESIGN_TIME, TRUE);
						fdMap.put(pd.getName(), pd);
					}
				}
				return fdMap.values().iterator();
			}
		}
		return superDescriptors;
	}

	@Override
	public void setValue(ELContext context, Object base, Object property, Object val)
	{
		super.setValue(context, baseOrAdapter(base, property), property, val);
	}

	@Override
	public boolean isReadOnly(ELContext context, Object base, Object property)
	{
		return super.isReadOnly(context, baseOrAdapter(base, property), property);
	}

	@Override
	public Object getValue(ELContext context, Object base, Object property)
	{
		return super.getValue(context, baseOrAdapter(base, property), property);
	}

	@Override
	public Class<?> getType(ELContext context, Object base, Object property)
	{
		return super.getType(context, baseOrAdapter(base, property), property);
	}

	private Object baseOrAdapter(Object base, Object property)
	{
		if ( base != null && property instanceof String )
		{
			Object adapter = getAdapter(base, (String) property);
			if ( adapter != null )
				return adapter;
		}
		return base;
	}
}
