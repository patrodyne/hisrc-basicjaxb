package org.jdesktop.swingbinding.adapters;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.beansbinding.ext.BeanAdapterProvider;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @author Shannon Hickey
 */
public final class JSpinnerAdapterProvider implements BeanAdapterProvider
{
	private static final String VALUE_P = "value";

	public static final class Adapter extends BeanAdapterBase
	{
		private JSpinner spinner;
		private Handler handler;
		private Object cachedValue;

		private Adapter(JSpinner spinner)
		{
			super(VALUE_P);
			this.spinner = spinner;
		}

		public Object getValue()
		{
			return spinner.getValue();
		}

		public void setValue(Object value)
		{
			spinner.setValue(value);
		}

		@Override
		protected void listeningStarted()
		{
			handler = new Handler();
			cachedValue = getValue();
			spinner.addChangeListener(handler);
			spinner.addPropertyChangeListener("model", handler);
		}

		@Override
		protected void listeningStopped()
		{
			spinner.removeChangeListener(handler);
			spinner.removePropertyChangeListener("model", handler);
			handler = null;
		}

		private class Handler
			implements ChangeListener, PropertyChangeListener
		{
			private void spinnerValueChanged()
			{
				Object oldValue = cachedValue;
				cachedValue = getValue();
				firePropertyChange(oldValue, cachedValue);
			}

			@Override
			public void stateChanged(ChangeEvent ce)
			{
				spinnerValueChanged();
			}

			@Override
			public void propertyChange(PropertyChangeEvent pe)
			{
				spinnerValueChanged();
			}
		}
	}

	@Override
	public boolean providesAdapter(Class<?> type, String property)
	{
		return JSpinner.class.isAssignableFrom(type) && property == VALUE_P;
	}

	@Override
	public Object createAdapter(Object source, String property)
	{
		if ( !providesAdapter(source.getClass(), property) )
		{
			throw new IllegalArgumentException();
		}
		return new Adapter((JSpinner) source);
	}

	@Override
	public Class<?> getAdapterClass(Class<?> type)
	{
		return JSpinner.class.isAssignableFrom(type) ? JSpinnerAdapterProvider.Adapter.class : null;
	}
}
