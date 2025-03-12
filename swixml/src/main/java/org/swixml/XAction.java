package org.swixml;

import static java.lang.String.format;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;

import javax.swing.AbstractAction;

import org.jdesktop.application.ApplicationAction;
import org.jdesktop.application.ApplicationActionMap;
import org.jdesktop.application.ApplicationContext;
import org.swixml.jsr296.SwingApplication;

/**
 * XAction, Action Wrapper to generate Actions on the fly.
 * 
 * @author <a href="mailto:wolf@wolfpaulus.com">Wolf Paulus</a>
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 */
public class XAction
	extends AbstractAction
	implements LogAware
{
	private static final long serialVersionUID = 20240701L;
	Method method;
	Object client;
	ApplicationAction delegate;

	public XAction(Object client, String methodName)
		throws NoSuchMethodException
	{
		this.client = client;
		try
		{
			ApplicationContext ctx = SwingApplication.getInstance().getContext();
			ApplicationActionMap actionMap = ctx.getActionMap(client);
			delegate = (ApplicationAction) actionMap.get(methodName);
			if ( delegate == null )
			{
				method = client.getClass().getMethod(methodName);
			}
		}
		catch (Exception ex)
		{
			// @TODO
			// logger.log( Level.WARNING, "error on Action initialization", ex);
			// logger.warn(String.format("error on Action initialization [%s]", ex.getMessage()));
			throw new RuntimeException(format("error on Action initialization [%s]", ex.getMessage()), ex);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			if ( null == delegate )
			{
				if ( this.method != null )
					this.method.invoke(client);
			}
			else
			{
				delegate.actionPerformed(e);
			}
		}
		catch (Exception ex)
		{
			//logger.error("action performed error", ex);
			throw new RuntimeException("action performed error", ex);
		}
	}

	@Override
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener)
	{
		if ( null != delegate )
			delegate.addPropertyChangeListener(listener);
		super.addPropertyChangeListener(listener);
	}

	@Override
	public Object getValue(String key)
	{
		return (null != delegate) ? delegate.getValue(key) : super.getValue(key);
	}

	@Override
	public boolean isEnabled()
	{
		return (null != delegate) ? delegate.isEnabled() : super.isEnabled();
	}

	@Override
	public void putValue(String key, Object newValue)
	{
		if ( null != delegate )
			delegate.putValue(key, newValue);
		else
			super.putValue(key, newValue);
	}

	@Override
	public synchronized void removePropertyChangeListener(PropertyChangeListener listener)
	{
		if ( null != delegate )
			delegate.removePropertyChangeListener(listener);
		super.removePropertyChangeListener(listener);
	}

	@Override
	public void setEnabled(boolean newValue)
	{
		logger.debug(String.format("setEnabled(%s)\n", newValue));
		if ( null != delegate )
			delegate.setEnabled(newValue);
		super.setEnabled(newValue);
	}
}
