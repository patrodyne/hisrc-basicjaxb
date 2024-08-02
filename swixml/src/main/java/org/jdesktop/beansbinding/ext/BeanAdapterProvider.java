package org.jdesktop.beansbinding.ext;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @author sky
 * @author Shannon Hickey
 */
public interface BeanAdapterProvider
{
	public abstract boolean providesAdapter(Class<?> type, String property);

	public abstract Object createAdapter(Object source, String property);

	public abstract Class<?> getAdapterClass(Class<?> type);
}
