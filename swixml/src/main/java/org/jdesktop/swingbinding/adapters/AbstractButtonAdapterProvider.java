package org.jdesktop.swingbinding.adapters;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;

import org.jdesktop.beansbinding.ext.BeanAdapterProvider;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @author Shannon Hickey
 */
public final class AbstractButtonAdapterProvider implements BeanAdapterProvider
{
	private static final String SELECTED_P = "selected";

	public static final class Adapter
		extends
		BeanAdapterBase
	{
		private AbstractButton button;
		private Handler handler;
		private boolean cachedSelected;

		private Adapter(AbstractButton button)
		{
			super(SELECTED_P);
			this.button = button;
		}

		public boolean isSelected()
		{
			return button.isSelected();
		}

		public void setSelected(boolean selected)
		{
			button.setSelected(selected);
		}

		@Override
		protected void listeningStarted()
		{
			handler = new Handler();
			cachedSelected = isSelected();
			button.addItemListener(handler);
			button.addPropertyChangeListener("model", handler);
		}

		@Override
		protected void listeningStopped()
		{
			button.removeItemListener(handler);
			button.removePropertyChangeListener("model", handler);
			handler = null;
		}

		private class Handler
			implements ItemListener, PropertyChangeListener
		{
			private void buttonSelectedChanged()
			{
				boolean oldSelected = cachedSelected;
				cachedSelected = isSelected();
				firePropertyChange(oldSelected, cachedSelected);
			}

			@Override
			public void itemStateChanged(ItemEvent ie)
			{
				buttonSelectedChanged();
			}

			@Override
			public void propertyChange(PropertyChangeEvent pe)
			{
				buttonSelectedChanged();
			}
		}
	}

	@Override
	public boolean providesAdapter(Class<?> type, String property)
	{
		return AbstractButton.class.isAssignableFrom(type) && property.intern() == SELECTED_P;
	}

	@Override
	public Object createAdapter(Object source, String property)
	{
		if ( !providesAdapter(source.getClass(), property) )
		{
			throw new IllegalArgumentException();
		}
		return new Adapter((AbstractButton) source);
	}

	@Override
	public Class<?> getAdapterClass(Class<?> type)
	{
		return AbstractButton.class.isAssignableFrom(type) ? AbstractButtonAdapterProvider.Adapter.class : null;
	}
}
