package org.jdesktop.beansbinding;

/**
 * An immutable, read-only, {@code Property} implementation whose
 * {@code getValue} method returns the source object that it is given. This
 * class is useful when you want to configure a {@code Binding} to use its
 * source object directly, rather than some property of the source object. For
 * example:
 * 
 * <pre><code>
 *    new SomeBindingClass(sourceObject, ObjectProperty.create(), targetObject, targetProperty);
 * </code></pre>
 * 
 * <p>Explicitly using {@code ObjectProperty} isn't necessary when creating
 * {@code Bindings} from this package or the {@code SwingBindings} package, as
 * the set of static creation methods include versions that handle this for you.</p>
 *
 * @author Shannon Hickey
 *            
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 *
 * @apiNote {@code <S>} the type of source object that this {@code Property} operates
 *                      on and therefore the type of value that it represents
 */
public final class ObjectProperty<S>
	extends Property<S, S>
{
	/**
	 * Creates an instance of {@code ObjectProperty}.
	 */
	public static <S> ObjectProperty<S> create()
	{
		return new ObjectProperty<S>();
	}

	private ObjectProperty()
	{
	}

	/**
	 * Throws {@code UnsupportedOperationException}; {@code ObjectProperty} is
	 * never writable.
	 *
	 * @param source {@inheritDoc}
	 * @return never returns; always throws
	 *         {@code UnsupportedOperationException}; {@code ObjectProperty} is
	 *         never writable
	 * @throws UnsupportedOperationException always; {@code ObjectProperty} is
	 *             never writable
	 * @see #isWriteable
	 */
	@Override
	public Class<? extends S> getWriteType(S source)
	{
		throw new UnsupportedOperationException("Writable");
	}

	/**
	 * Returns the source object passed to the method.
	 *
	 * @return the value of the {@code source} argument
	 * @see #isReadable
	 */
	@Override
	public S getValue(S source)
	{
		return source;
	}

	/**
	 * Throws {@code UnsupportedOperationException}; {@code ObjectProperty} is
	 * never writable.
	 *
	 * @param source {@inheritDoc}
	 * @throws UnsupportedOperationException always; {@code ObjectProperty} is
	 *             never writable
	 * @see #isWriteable
	 * @see #getWriteType
	 */
	@Override
	public void setValue(S source, S value)
	{
		throw new UnsupportedOperationException("Unwritable");
	}

	/**
	 * Returns {@code true}; {@code ObjectProperty} is always readable.
	 *
	 * @return {@code true}; {@code ObjectPropert} is always readable
	 * @see #isWriteable
	 */
	@Override
	public boolean isReadable(Object source)
	{
		return true;
	}

	/**
	 * Returns {@code false}; {@code ObjectProperty} is never writable.
	 *
	 * @return {@code false}; {@code ObjectPropert} is never writable
	 * @see #isReadable
	 */
	@Override
	public boolean isWriteable(Object source)
	{
		return false;
	}

	/**
	 * Returns a string representation of the {@code ObjectProperty}. This
	 * method is intended to be used for debugging purposes only, and the
	 * content and format of the returned string may vary between
	 * implementations. The returned string may be empty but may not be
	 * {@code null}.
	 *
	 * @return a string representation of this {@code ObjectProperty}
	 */
	@Override
	public String toString()
	{
		return getClass().getName();
	}

	/**
	 * Does nothing; the state of an {@code ObjectProperty} never changes so
	 * listeners aren't useful.
	 */
	@Override
	public void addPropertyStateListener(S source, PropertyStateListener listener)
	{
	}

	/**
	 * Does nothing; the state of an {@code ObjectProperty} never changes so
	 * listeners aren't useful.
	 *
	 * @see #addPropertyStateListener
	 */
	@Override
	public void removePropertyStateListener(S source, PropertyStateListener listener)
	{
	}

	/**
	 * Returns an empty array; the state of an {@code ObjectProperty} never
	 * changes so listeners aren't useful.
	 *
	 * @return an empty array
	 * @see #addPropertyStateListener
	 */
	@Override
	public PropertyStateListener[] getPropertyStateListeners(S source)
	{
		return new PropertyStateListener[0];
	}
}
