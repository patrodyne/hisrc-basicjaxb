package org.jdesktop.beansbinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@code BindingGroup} allows you to create a group of {@code Bindings} and
 * operate on and/or track state changes to the {@code Bindings} as a group.
 *
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 *
 * @author Shannon Hickey
 */
public class BindingGroup
{
	private final List<Binding<?, ?, ?, ?>> unbound = new ArrayList<Binding<?, ?, ?, ?>>();
	private final List<Binding<?, ?, ?, ?>> bound = new ArrayList<Binding<?, ?, ?, ?>>();
	private List<BindingListener> listeners;
	private Handler handler;
	private Map<String, Binding<?, ?, ?, ?>> namedBindings;

	/**
	 * Creates an empty {@code BindingGroup}.
	 */
	public BindingGroup()
	{
	}

	/**
	 * Adds a {@code Binding} to this group.
	 *
	 * @param binding the {@code Binding} to add
	 * @throws IllegalArgumentException if the binding is null, is a managed
	 *             binding, if the group already contains this binding, or if
	 *             the group already contains a binding with the same
	 *             ({@code non-null}) name
	 */
	public final void addBinding(Binding<?, ?, ?, ?> binding)
	{
		if ( binding == null )
			throw new IllegalArgumentException("Binding must be non-null");
		if ( binding.isManaged() )
			throw new IllegalArgumentException("Managed bindings can't be in a group");
		if ( bound.contains(binding) || unbound.contains(binding) )
			throw new IllegalArgumentException("Group already contains this binding");
		
		String name = binding.getName();
		if ( name != null )
		{
			if ( getBinding(name) != null )
				throw new IllegalArgumentException("Context already contains a binding with name \"" + name + "\"");
			else
				putNamed(name, binding);
		}
		
		binding.addBindingListener(getHandler());
		
		if ( binding.isBound() )
			bound.add(binding);
		else
			unbound.add(binding);
	}

	/**
	 * Removes a {@code Binding} from this group.
	 *
	 * @param binding the {@code Binding} to remove
	 * @throws IllegalArgumentException if the binding is null or if the group
	 *             doesn't contain this binding
	 */
	public final void removeBinding(Binding<?, ?, ?, ?> binding)
	{
		if ( binding == null )
			throw new IllegalArgumentException("Binding must be non-null");
		
		if ( binding.isBound() )
		{
			if ( !bound.remove(binding) )
				throw new IllegalArgumentException("Unknown Binding");
		}
		else
		{
			if ( !unbound.remove(binding) )
				throw new IllegalArgumentException("Unknown Binding");
		}
		
		String name = binding.getName();
		if ( name != null )
		{
			assert namedBindings != null;
			namedBindings.remove(name);
		}
		
		binding.removeBindingListener(getHandler());
	}

	private void putNamed(String name, Binding<?, ?, ?, ?> binding)
	{
		if ( namedBindings == null )
			namedBindings = new HashMap<String, Binding<?, ?, ?, ?>>();
		namedBindings.put(name, binding);
	}

	/**
	 * Returns the {@code Binding} in this group with the given name, or
	 * {@code null} if this group doesn't contain a {@code Binding} with the
	 * given name.
	 *
	 * @param name the name of the {@code Binding} to fetch
	 * @return the {@code Binding} in this group with the given name, or
	 *         {@code null}
	 * @throws IllegalArgumentException if {@code name} is {@code null}
	 */
	public final Binding<?, ?, ?, ?> getBinding(String name)
	{
		if ( name == null )
			throw new IllegalArgumentException("cannot fetch unnamed bindings");
		
		return namedBindings == null ? null : namedBindings.get(name);
	}

	/**
	 * Returns a list of all {@code Bindings} in this group. Order is undefined.
	 * Returns an empty list if the group contains no {@code Bindings}.
	 *
	 * @return a list of all {@code Bindings} in this group.
	 */
	public final List<Binding<?, ?, ?, ?>> getBindings()
	{
		List<Binding<?, ?, ?, ?>> list = new ArrayList<>(bound);
		list.addAll(unbound);
		return Collections.unmodifiableList(list);
	}

	/**
	 * Calls {@code bind} on all unbound bindings in the group.
	 */
	public void bind()
	{
		List<Binding<?, ?, ?, ?>> toBind = new ArrayList<>(unbound);
		for ( Binding<?, ?, ?, ?> binding : toBind )
			binding.bind();
	}

	/**
	 * Calls {@code unbind} on all bound bindings in the group.
	 */
	public void unbind()
	{
		List<Binding<?, ?, ?, ?>> toUnbind = new ArrayList<>(bound);
		for ( Binding<?, ?, ?, ?> binding : toUnbind )
			binding.unbind();
	}
	
	/**
	 * Calls {@code refresh} on all bound bindings in the group.
	 */
	public void refresh()
	{
		List<Binding<?, ?, ?, ?>> toRefresh = new ArrayList<>(bound);
		for ( Binding<?, ?, ?, ?> binding : toRefresh )
			binding.refresh();
	}
	
	/**
	 * Calls {@code refreshAndNotify} on all bound bindings in the group.
	 */
	public void refreshAndNotify()
	{
		List<Binding<?, ?, ?, ?>> toRefresh = new ArrayList<>(bound);
		for ( Binding<?, ?, ?, ?> binding : toRefresh )
			binding.refreshAndNotify();
	}

	/**
	 * Calls {@code save} on all bound bindings in the group.
	 */
	public void save()
	{
		List<Binding<?, ?, ?, ?>> toRefresh = new ArrayList<>(bound);
		for ( Binding<?, ?, ?, ?> binding : toRefresh )
			binding.save();
	}
	
	/**
	 * Calls {@code saveAndNotify} on all bound bindings in the group.
	 */
	public void saveAndNotify()
	{
		List<Binding<?, ?, ?, ?>> toRefresh = new ArrayList<>(bound);
		for ( Binding<?, ?, ?, ?> binding : toRefresh )
			binding.saveAndNotify();
	}
	
	/**
	 * Adds a {@code BindingListener} to be notified of all
	 * {@code BindingListener} notifications fired by any {@code Binding} in the
	 * group. Does nothing if the listener is {@code null}. If a listener is
	 * added more than once, notifications are sent to that listener once for
	 * every time that it has been added. The ordering of listener notification
	 * is unspecified.
	 *
	 * @param listener the listener to add
	 */
	public final void addBindingListener(BindingListener listener)
	{
		if ( listener != null )
		{
			if ( listeners == null )
				listeners = new ArrayList<BindingListener>();
			listeners.add(listener);
		}
	}

	/**
	 * Removes a {@code BindingListener} from the group. Does nothing if the
	 * listener is {@code null} or is not one of those registered. If the
	 * listener being removed was registered more than once, only one occurrence
	 * of the listener is removed from the list of listeners. The ordering of
	 * listener notification is unspecified.
	 *
	 * @param listener the listener to remove
	 * @see #addBindingListener
	 */
	public final void removeBindingListener(BindingListener listener)
	{
		if ( listener != null )
		{
			if ( listeners != null )
				listeners.remove(listener);
		}
	}

	/**
	 * Returns the list of {@code BindingListeners} registered on this group.
	 * Order is undefined. Returns an empty array if there are no listeners.
	 *
	 * @return the list of {@code BindingListeners} registered on this group
	 * @see #addBindingListener
	 */
	public final BindingListener[] getBindingListeners()
	{
		BindingListener[] ret;
		if ( listeners != null )
		{
			ret = new BindingListener[listeners.size()];
			ret = listeners.toArray(ret);
		}
		else
			ret = new BindingListener[0];
		return ret;
	}

	private final Handler getHandler()
	{
		if ( handler == null )
			handler = new Handler();
		
		return handler;
	}

	private class Handler implements BindingListener
	{
		@Override
		public void syncFailed(Binding<?, ?, ?, ?> binding, Binding.SyncFailure failure)
		{
			if ( listeners != null )
			{
				for ( BindingListener listener : listeners )
					listener.syncFailed(binding, failure);
			}
		}

		@Override
		public void synced(Binding<?, ?, ?, ?> binding)
		{
			if ( listeners != null )
			{
				for ( BindingListener listener : listeners )
					listener.synced(binding);
			}
		}

		@Override
		public void sourceChanged(Binding<?, ?, ?, ?> binding, PropertyStateEvent event)
		{
			if ( listeners != null )
			{
				for ( BindingListener listener : listeners )
					listener.sourceChanged(binding, event);
			}
		}

		@Override
		public void targetChanged(Binding<?, ?, ?, ?> binding, PropertyStateEvent event)
		{
			if ( listeners != null )
			{
				for ( BindingListener listener : listeners )
					listener.targetChanged(binding, event);
			}
		}

		@Override
		public void bindingBecameBound(Binding<?, ?, ?, ?> binding)
		{
			unbound.remove(binding);
			bound.add(binding);
			
			if ( listeners != null )
			{
				for ( BindingListener listener : listeners )
					listener.bindingBecameBound(binding);
			}
		}

		@Override
		public void bindingBecameUnbound(Binding<?, ?, ?, ?> binding)
		{
			bound.remove(binding);
			unbound.add(binding);
			
			if ( listeners != null )
			{
				for ( BindingListener listener : listeners )
					listener.bindingBecameUnbound(binding);
			}
		}
	}
}
