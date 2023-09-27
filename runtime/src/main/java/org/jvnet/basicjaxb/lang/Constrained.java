package org.jvnet.basicjaxb.lang;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;

/**
 * Interface for VetoableChangeSupport.
 * 
 * <p><b>Constrained Property</b></p>
 * <p>Sometimes when a property change occurs some other bean may wish to validate the
 * change and reject it if it is inappropriate. We refer to properties that undergo this
 * kind of checking as <em>constrained</em> properties.</p>
 */
public interface Constrained
{
	abstract VetoableChangeSupport getVetoableChangeSupport();

	public default void addVetoableChangeListener(VetoableChangeListener listener)
	{
		getVetoableChangeSupport().addVetoableChangeListener(listener);
	}
	
    public default void addVetoableChangeListener(String name, VetoableChangeListener listener)
    {
    	getVetoableChangeSupport().addVetoableChangeListener(name, listener);
    }

	public default void removeVetoableChangeListener(VetoableChangeListener listener)
	{
		getVetoableChangeSupport().removeVetoableChangeListener(listener);
	}
	
    public default void removeVetoableChangeListener(String name, VetoableChangeListener listener)
    {
    	getVetoableChangeSupport().removeVetoableChangeListener(name, listener);
    }
    
    public default boolean vetoPropertyChange(PropertyChangeEvent event, VetoStrategy vetoStrategy)
    {
        try
        {
            getVetoableChangeSupport().fireVetoableChange(event);
            // No exception equals No veto equals false
            return false;
        }
        catch (PropertyVetoException ex)
        {
        	return vetoStrategy.vetoPropertyChange(event, ex);
        }
    }
}
