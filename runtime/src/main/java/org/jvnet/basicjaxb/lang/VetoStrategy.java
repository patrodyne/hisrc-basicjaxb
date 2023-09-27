package org.jvnet.basicjaxb.lang;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;

import org.slf4j.Logger;

/**
 * Reflection-free vetoPropertyChange(...) strategy to veto a Bean property change.
 * 
 * <p>Provides {@link Logger} for escalation and/or tracing.</p>
 */
public interface VetoStrategy
{
	public Logger getLogger();
	
	public boolean vetoPropertyChange(PropertyChangeEvent event, PropertyVetoException ex);
}
