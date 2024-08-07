package org.jdesktop.beansbinding;

import java.util.EventListener;

/**
 * {@code PropertyStateListeners} are registered on
 * {@link org.jdesktop.beansbinding.Property} instances, to be notified when the
 * state of the property changes.
 * 
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 *
 * @author Shannon Hickey
 */
public interface PropertyStateListener extends EventListener
{
	/**
	 * Called to notify the listener that a change of state has occurred to one
	 * of the {@code Property} instances upon which the listener is registered.
	 *
	 * @param pse an event describing the state change, {@code non-null}
	 */
	public void propertyStateChanged(PropertyStateEvent pse);
}
