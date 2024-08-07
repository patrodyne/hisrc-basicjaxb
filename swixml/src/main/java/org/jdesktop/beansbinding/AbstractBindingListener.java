package org.jdesktop.beansbinding;

/**
 * An abstract subclass of {@code BindingListener} that simplifies writing
 * {@code BindingListeners} by allowing you to extend this class and
 * re-implement only the methods you care about.
 *
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 *
 * @author Shannon Hickey
 */
public abstract class AbstractBindingListener implements BindingListener
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bindingBecameBound(Binding<?, ?, ?, ?> binding)
	{
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bindingBecameUnbound(Binding<?, ?, ?, ?> binding)
	{
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syncFailed(Binding<?, ?, ?, ?> binding, Binding.SyncFailure failure)
	{
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void synced(Binding<?, ?, ?, ?> binding)
	{
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This implementation calls {@code sourceEdited} if the provided event
	 * returns {@code true} from {@code getValueChanged}.
	 */
	@Override
	public void sourceChanged(Binding<?, ?, ?, ?> binding, PropertyStateEvent event)
	{
		if ( event.getValueChanged() )
		{
			sourceEdited(binding);
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * This implementation calls {@code targetEdited} if the provided event
	 * returns {@code true} from {@code getValueChanged}.
	 */
	@Override
	public void targetChanged(Binding<?, ?, ?, ?> binding, PropertyStateEvent event)
	{
		if ( event.getValueChanged() )
		{
			targetEdited(binding);
		}
	}

	/**
	 * Notification that the source property of a {@code Binding} has fired a
	 * {@code PropertyStateEvent} indicating that its <b>value or
	 * readability</b> has changed for the {@code Binding's} source object.
	 * Called by the default {@code AbstractBindingListener's} implementation of
	 * {@code sourceChanged}.
	 *
	 * @param binding the {@code Binding}
	 * @deprecated This method has been replaced by {@link #sourceChanged} and
	 *             it will go away soon. It is being kept for a short period
	 *             only, to assist in migration.
	 */
	@Deprecated
	public void sourceEdited(Binding<?, ?, ?, ?> binding)
	{
	}

	/**
	 * Notification that the target property of a {@code Binding} has fired a
	 * {@code PropertyStateEvent} indicating that its <b>value or
	 * readability</b> has changed for the {@code Binding's} target object.
	 * Called by the default {@code AbstractBindingListener's} implementation of
	 * {@code targetChanged}.
	 *
	 * @param binding the {@code Binding}
	 * @deprecated This method has been replaced by {@link #targetChanged} and
	 *             it will go away soon. It is being kept for a short period
	 *             only, to assist in migration.
	 */
	@Deprecated
	public void targetEdited(Binding<?, ?, ?, ?> binding)
	{
	}
}
