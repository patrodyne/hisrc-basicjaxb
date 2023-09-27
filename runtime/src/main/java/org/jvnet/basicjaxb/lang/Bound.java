package org.jvnet.basicjaxb.lang;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Interface for PropertyChangeSupport.
 * 
 * <p><b>Bound Property</b></p>
 * <p>A bean can choose to provide a change notification service for some or all of its
 * properties. Such properties are commonly known as <em>bound</em> properties, as they
 * allow other beans to bind special behavior to property changes.</p>
 */
public interface Bound
{
	abstract PropertyChangeSupport getPropertyChangeSupport();

	public default void addPropertyChangeListener(PropertyChangeListener listener)
	{
		getPropertyChangeSupport().addPropertyChangeListener(listener);
	}
	
    public default void addPropertyChangeListener(String name, PropertyChangeListener listener)
    {
    	getPropertyChangeSupport().addPropertyChangeListener(name, listener);
    }

	public default void removePropertyChangeListener(PropertyChangeListener listener)
	{
		getPropertyChangeSupport().removePropertyChangeListener(listener);
	}
	
    public default void removePropertyChangeListener(String name, PropertyChangeListener listener)
    {
    	getPropertyChangeSupport().removePropertyChangeListener(name, listener);
    }
    
    public default void firePropertyChange(PropertyChangeEvent event)
    {
    	getPropertyChangeSupport().firePropertyChange(event);
    }
}
