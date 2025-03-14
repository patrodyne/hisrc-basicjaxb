package org.jdesktop.swingbinding.adapters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComboBox;
import javax.swing.JList;

import org.jdesktop.beansbinding.ext.BeanAdapterProvider;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @author Shannon Hickey
 */
public final class JComboBoxAdapterProvider implements BeanAdapterProvider
{
	private static final String SELECTED_ITEM_P = "selectedItem";

	public static final class Adapter extends BeanAdapterBase
	{
		private JComboBox<?> combo;
		private Handler handler;
		private Object cachedItem;

		private Adapter(JComboBox<?> combo)
		{
			super(SELECTED_ITEM_P);
			this.combo = combo;
		}

		public Object getSelectedItem()
		{
			return combo.getSelectedItem();
		}

		public void setSelectedItem(Object item)
		{
			combo.setSelectedItem(item);
		}

		@Override
		protected void listeningStarted()
		{
			handler = new Handler();
			cachedItem = combo.getSelectedItem();
			combo.addActionListener(handler);
			combo.addPropertyChangeListener("model", handler);
		}

		@Override
		protected void listeningStopped()
		{
			combo.removeActionListener(handler);
			combo.removePropertyChangeListener("model", handler);
			handler = null;
			cachedItem = null;
		}

		private class Handler implements ActionListener, PropertyChangeListener
		{
			private void comboSelectionChanged()
			{
				Object oldValue = cachedItem;
				cachedItem = getSelectedItem();
				firePropertyChange(oldValue, cachedItem);
			}

			@Override
			public void actionPerformed(ActionEvent ae)
			{
				comboSelectionChanged();
			}

			@Override
			public void propertyChange(PropertyChangeEvent pce)
			{
				comboSelectionChanged();
			}
		}
	}

	@Override
	public boolean providesAdapter(Class<?> type, String property)
	{
		return JComboBox.class.isAssignableFrom(type) && property.intern() == SELECTED_ITEM_P;
	}

	@Override
	public Object createAdapter(Object source, String property)
	{
		if ( !providesAdapter(source.getClass(), property) )
		{
			throw new IllegalArgumentException();
		}
		return new Adapter((JComboBox<?>) source);
	}

	@Override
	public Class<?> getAdapterClass(Class<?> type)
	{
		return JList.class.isAssignableFrom(type) ? JComboBoxAdapterProvider.Adapter.class : null;
	}
}
