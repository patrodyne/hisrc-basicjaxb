package org.jvnet.basicjaxb.lang;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reflection-free vetoPropertyChange(...) strategy to veto a Bean property change.
 * 
 * <p>Provides {@link Logger} for escalation and/or tracing.</p>
 */
public class DefaultVetoStrategy implements VetoStrategy
{
	private static final DefaultVetoStrategy INSTANCE = new DefaultVetoStrategy();
	public static DefaultVetoStrategy getInstance()
	{
		return INSTANCE;
	}

	private Logger logger = LoggerFactory.getLogger(VetoStrategy.class);
	@Override
	public Logger getLogger()
	{
		return logger;
	}
	
	/**
	 * Subclasses can override this method to log the warning message, as desired.
	 * 
	 * @param message The message format.
	 * @param args The message arguments plus the exception.
	 */
	public void warn(String message, Object... args)
	{
		getLogger().warn(message, args);
	}
	
	/**
	 * Subclasses can override this method to log the error message, as desired.
	 * 
	 * @param message The message format.
	 * @param args The message arguments plus the exception.
	 */
	public void error(String message, Object... args)
	{
		getLogger().warn(message, args);
	}
	
	/**
	 * Strategy to handle a property veto.
	 */
	@Override
	public boolean vetoPropertyChange(PropertyChangeEvent event, PropertyVetoException ex)
	{
		if ( getLogger().isWarnEnabled() )
			warn("Change vetoed of {} from {} to {} for {}", event.getPropertyName(), event.getOldValue(), event.getNewValue(), event.getSource());
		else
			error("Change vetoed of {} from {} to {} for {}", event.getPropertyName(), event.getOldValue(), event.getNewValue(), event.getSource(), ex);
		return false;
	}
}
