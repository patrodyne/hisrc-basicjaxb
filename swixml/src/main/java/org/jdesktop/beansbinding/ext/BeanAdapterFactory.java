package org.jdesktop.beansbinding.ext;

import static jakarta.el.ELResolver.RESOLVABLE_AT_DESIGN_TIME;
import static jakarta.el.ELResolver.TYPE;
import static java.lang.Boolean.TRUE;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.jdesktop.application.ActionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public final class BeanAdapterFactory
{
	private static final Logger logger = LoggerFactory.getLogger(ActionManager.class);

	private static Logger getLogger()
	{
		return logger;
	}

	private static final BeanAdapterFactory INSTANCE = new BeanAdapterFactory();
	private final Map<Object, List<VendedAdapter>> vendedAdapters;
	private final List<BeanAdapterProvider> providers;
	private final Set<ClassLoader> classLoaders;
	private final Set<URL> serviceURLs;

	public static Object getAdapter(Object source, String property)
	{
		return INSTANCE.getAdapter0(source, property);
	}

	public static List<PropertyDescriptor> getAdapterPropertyDescriptors(Class<?> type)
	{
		return INSTANCE.getAdapterPropertyDescriptors0(type);
	}

	public BeanAdapterFactory()
	{
		this.providers = new ArrayList<BeanAdapterProvider>();
		classLoaders = new HashSet<ClassLoader>();
		serviceURLs = new HashSet<URL>();
		vendedAdapters = new WeakHashMap<Object, List<VendedAdapter>>();
	}

	private void loadProvidersIfNecessary()
	{
		ClassLoader currentLoader = Thread.currentThread().getContextClassLoader();
		if ( !classLoaders.contains(currentLoader) )
		{
			classLoaders.add(currentLoader);
			loadProviders(currentLoader);
		}
	}

	private void loadProviders(ClassLoader classLoader)
	{
		// PENDING: this needs to be rewritten in terms of ServiceLoader
		String serviceName = "META-INF/services/" + BeanAdapterProvider.class.getName();
		try
		{
			Enumeration<URL> urls = classLoader.getResources(serviceName);
			while (urls.hasMoreElements())
			{
				URL url = urls.nextElement();
				if ( !serviceURLs.contains(url) )
				{
					serviceURLs.add(url);
					addProviders(url);
				}
			}
		}
		catch (IOException ex)
		{
			getLogger().warn("loadProviders: {}", serviceName, ex);
		}
	}

	private void addProviders(URL url)
	{
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), UTF_8)))
		{
			String line;
			while ((line = reader.readLine()) != null)
			{
				try
				{
					if ( !line.trim().startsWith("#") && !line.isBlank() )
					{
						Object obj = Class.forName(line).getConstructor().newInstance();
						providers.add((BeanAdapterProvider) obj);
					}
				}
				catch (IllegalAccessException | InstantiationException | IllegalArgumentException
								| InvocationTargetException | NoSuchMethodException | SecurityException
								| ClassNotFoundException ex)
				{
					getLogger().warn("addProviders: {}", line, ex);
				}
			}
		}
		catch (IOException ex)
		{
			getLogger().warn("addProviders: {}", url, ex);
		}
	}

	public Object getAdapter0(Object source, String property)
	{
		if ( source == null || property == null )
		{
			throw new IllegalArgumentException();
		}
		loadProvidersIfNecessary();
		property = property.intern();
		BeanAdapterProvider provider = getProvider(source, property);
		if ( provider != null )
		{
			List<VendedAdapter> adapters = vendedAdapters.get(source);
			if ( adapters != null )
			{
				for ( int i = adapters.size() - 1; i >= 0; i-- )
				{
					VendedAdapter vendedAdapter = adapters.get(i);
					Object adapter = vendedAdapter.getAdapter();
					if ( adapter == null )
					{
						vendedAdapters.remove(i);
					}
					else if ( vendedAdapter.getProvider() == provider && vendedAdapter.getProperty() == property )
					{
						return adapter;
					}
				}
			}
			else
			{
				adapters = new ArrayList<VendedAdapter>(1);
				vendedAdapters.put(source, adapters);
			}
			Object adapter = provider.createAdapter(source, property);
			adapters.add(new VendedAdapter(property, provider, adapter));
			return adapter;
		}
		return null;
	}

	private BeanAdapterProvider getProvider(Object source, String property)
	{
		Class<?> type = source.getClass();
		for ( BeanAdapterProvider provider : providers )
		{
			if ( provider.providesAdapter(type, property) )
			{
				return provider;
			}
		}
		return null;
	}

	@SuppressWarnings("unused")
	private List<FeatureDescriptor> getDescriptors(Class<?> type)
	{
		BeanInfo info = null;
		try
		{
			info = Introspector.getBeanInfo(type);
		}
		catch (Exception ex)
		{
		}
		if ( info == null )
		{
			return Collections.emptyList();
		}
		ArrayList<FeatureDescriptor> list = new ArrayList<FeatureDescriptor>(info.getPropertyDescriptors().length);
		for ( PropertyDescriptor pd : info.getPropertyDescriptors() )
		{
			// PENDING: The following properties come from EL, are they
			// needed?
			if ( pd.getPropertyType() != null )
			{
				pd.setValue(TYPE, pd.getPropertyType());
			}
			pd.setValue(RESOLVABLE_AT_DESIGN_TIME, TRUE);
			list.add(pd);
		}
		return list;
	}

	private static BeanInfo getBeanInfo(Class<?> type)
	{
		try
		{
			return Introspector.getBeanInfo(type);
		}
		catch (IntrospectionException ie)
		{
			getLogger().warn("getBeanInfo: {}", type, ie);
			return null;
		}
	}

	private List<PropertyDescriptor> getAdapterPropertyDescriptors0(Class<?> type)
	{
		if ( type == null )
		{
			throw new IllegalArgumentException("Type must be non-null");
		}
		loadProvidersIfNecessary();
		ArrayList<PropertyDescriptor> des = new ArrayList<PropertyDescriptor>();
		for ( BeanAdapterProvider provider : providers )
		{
			Class<?> pdType = provider.getAdapterClass(type);
			if ( pdType != null )
			{
				BeanInfo info = getBeanInfo(pdType);
				if ( info != null )
				{
					PropertyDescriptor[] pds = info.getPropertyDescriptors();
					if ( pds != null )
					{
						for ( PropertyDescriptor pd : pds )
						{
							if ( provider.providesAdapter(type, pd.getName()) )
							{
								des.add(pd);
							}
						}
					}
				}
			}
		}
		return des;
	}

	private static final class VendedAdapter
	{
		private final BeanAdapterProvider provider;
		private final String property;
		private final WeakReference<Object> adapter;

		public VendedAdapter(String property, BeanAdapterProvider provider, Object adapter)
		{
			this.property = property;
			this.adapter = new WeakReference<Object>(adapter);
			this.provider = provider;
		}

		public Object getAdapter()
		{
			return adapter.get();
		}

		public String getProperty()
		{
			return property;
		}

		public BeanAdapterProvider getProvider()
		{
			return provider;
		}
	}
}
