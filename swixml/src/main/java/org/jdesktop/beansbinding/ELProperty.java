package org.jdesktop.beansbinding;

import static org.jdesktop.beansbinding.PropertyStateEvent.UNREADABLE;

import java.beans.BeanInfo;
import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdesktop.beansbinding.ext.BeanAdapterFactory;
import org.jdesktop.observablecollections.ObservableMap;
import org.jdesktop.observablecollections.ObservableMapListener;

import com.sun.el.ExpressionFactoryImpl;

import jakarta.el.ELContext;
import jakarta.el.ELException;
import jakarta.el.ValueExpression;

/**
 * An implementation of {@link Property} that allows Java Beans properties of
 * source objects to be addressed using a simple dot-separated path syntax
 * within an EL {@link ValueExpressionPlus}. For example, to create a simple property
 * representing a {@code Person} bean's mother's {@code firstName}:
 * 
 * <pre><code>ELProperty.create("${mother.firstName}")</code></pre>
 * 
 * <p>Note that {@link org.jdesktop.beansbinding.BeanProperty} is more suitable for
 * such a simple property.</p>
 * 
 * <p>
 * To create a property representing the concatenation of a {@code Person}
 * bean's {@code firstName} and {@code lastName} properties:
 * </p>
 * 
 * <pre><code>ELProperty.create("${firstName} ${lastName}");</code></pre>
 *
 * <p>
 * To create a property that is {@code true} or {@code false} depending on
 * whether or not the {@code Person's} mother is older than 65:
 * </p>
 * 
 * <pre><code>BeanProperty.create("${mother.age > 65}");</code></pre>
 * 
 * <p><em>
 * <b>Note:</b> Paths specified in the EL expressions are resolved against the source object
 * with which the property is being used.
 * </em></p>
 * 
 * <p>An instance of {@link ELProperty} is immutable and can be used with different
 * source objects. When a {@link PropertyStateListener} is added to an
 * {@link ELProperty} for a given source object, the {@link ELProperty} starts
 * listening to all objects along the paths in the {@link ValueExpressionPlus} 
 * (based on that source object) for change notification, and reflects any changes
 * by notifying the listener associated with the property for that source object.</p>
 * 
 * <p>For example, if a {@link PropertyStateListener} is added to the property
 * from the second example above for an object {@code Duke}, the
 * {@link PropertyStateListener} is notified when either {@code Duke's} first
 * name changes, or his last name changes. If a listener is added to the
 * property from the third example, the {@link PropertyStateListener} is
 * notified when either a change in {@code Duke's} mother or {@code Duke's}
 * mother's {@code age} results in a change to the result of the
 * {@link ValueExpressionPlus}.</p>
 * 
 * <p>
 * It is very important that any bean properties addressed via a
 * {@link ELProperty} follow the Java Beans specification, including firing
 * property change notification; otherwise, {@link ELProperty} cannot respond to
 * change. As some beans outside of your control may not follow the Java Beans
 * specification, {@link ELProperty} always checks the
 * {@link org.jdesktop.beansbinding.ext.BeanAdapterFactory} to see if a delegate
 * provider has been registered to provide a delegate bean to take the place of
 * an object for a given property. See the
 * <a href="ext/package-summary.html">ext package level</a> documentation for
 * more details.
 * </p>
 * 
 * <p>When there are no {@code PropertyStateListeners} installed on an
 * {@link ELProperty} for a given source, all {@link Property} methods act by
 * evaluating the full {@link ValueExpressionPlus}, thereby always providing "live" information.
 * On the contrary, when there are {@code PropertyStateListeners} installed, the
 * beans along the paths, and the final value, are cached, and only updated upon
 * notification of change from a bean. Again, this makes it very important that
 * any bean property that could change along the path fires property change
 * notification.</p>
 * 
 * <p><em><b>Note:</b> The {@code setValue} method is currently excluded from
 * the previous assertion; with the exception of checking the cache to determine
 * if the property is writable, it always evaluates the entire {@link ValueExpressionPlus}. The
 * result of this is that when working with paths containing beans that don't
 * fire property change notification, you can end up with all methods (including
 * {@code getValue}) working on cached information, but {@code setValue} working
 * on the live {@link ValueExpressionPlus}. There are plans to resolve this inconsistency in a
 * future release.</em></p>
 * 
 * <p><b>Readability</b> of an {@link ELProperty} for a given source is defined as follows:</p>
 * 
 * <p>An {@link ELProperty} is readable for a given source if and only if the following is
 * true for all paths used in the {@link ValueExpressionPlus}:</p>
 * 
 * <ol>
 * <li>Each bean in the path, starting with the source, defines a Java
 * Beans getter method for the the property to be read on it AND</li>
 * <li>Each bean in the path, starting with the source and ending with
 * the bean on which we read the final property, is {@code non-null}.</li>
 * </ol>
 * 
 * <p>The final value being {@code null} does not affect the readability.</p>
 * 
 * <p>In the third example given earlier, the {@link ELProperty} is readable
 * for {@code Duke} when all of the following are true: {@code Duke} defines a
 * Java Beans getter for {@code mother}, {@code Duke's mother} defines a Java
 * Beans getter for {@code age}, {@code Duke} is {@code non-null},
 * {@code Duke's mother} is {@code non-null}. The {@link ELProperty} is
 * therefore unreadable when any of the following is true: {@code Duke} does not
 * define a Java Beans getter for {@code mother}, {@code Duke's mother} does not
 * define a Java Beans getter for {@code age}, {@code Duke} is {@code null},
 * {@code Duke's mother} is {@code null}.</p>
 * 
 * <p><b>Writability</b> of an {@link ELProperty} for a given source is defined as follows:</p>
 * 
 * <p>An {@link ELProperty} is writable for a given source if and only if:</p>
 * 
 * <ol>
 * <li>The EL {@link ValueExpressionPlus} itself is not read-only (ie. it is a simple
 * {@link ValueExpressionPlus} involving one path such as {@code "${foo.bar.baz}"} AND</li>
 * <li>Each bean in the path, starting with the source and ending with the
 * bean on which we set the final property, defines a Java Beans getter method
 * for the property to be read on it AND</li>
 * <li>The bean on which we set the final property defines a Java Beans setter for the
 * property to be set on it</li>
 * <li>Each bean in the path, starting with the source and ending with the bean on
 * which we set the final property, is {@code non-null}.</li>
 * </ol>
 * 
 * <p>The final value being {@code null} does not affect the writability.</p>
 * 
 * <p>In the first example given earlier (a simple path), the {@link ELProperty}
 * is writable for {@code Duke} when all of the following are true:
 * {@code Duke} defines a Java Beans getter for {@code mother},
 * {@code Duke's mother} defines a Java Beans setter for {@code firstName},
 * {@code Duke} is {@code non-null}, {@code Duke's mother} is {@code non-null}.
 * The {@link ELProperty} is therefore unreadable when any of the following is
 * true: {@code Duke} does not define a Java Beans getter for {@code mother},
 * {@code Duke's mother} does not define a Java Beans setter for
 * {@code firstName}, {@code Duke} is {@code null}, {@code Duke's mother} is
 * {@code null}. The second and third examples above both represent read-only
 * {@code ELExpressions} and are therefore unwritable.</p>
 * 
 * <p>In addition to working on Java Beans properties, any object in the paths can
 * be an instance of {@code Map}. In this case, the {@code Map's get} method is
 * used with the property name as the getter, and the {@code Map's put} method
 * is used with the property name as the setter. {@link ELProperty} can only
 * respond to changes in {@code Maps} if they are instances of
 * {@link org.jdesktop.observablecollections.ObservableMap}.</p>
 * 
 * <p>Some methods in this class document that they can throw
 * {@link PropertyResolutionException} if an exception occurs while trying to
 * evaluate the {@link ValueExpressionPlus}. The throwing of this exception represents an
 * abnormal condition and if listeners are installed for the given source
 * object, leaves the {@link ELProperty} in an inconsistent state for that
 * source object. An {@link ELProperty} should not be used again for that same
 * source object after such an exception without first removing all listeners
 * associated with the {@link ELProperty} for that source object.</p>
 *
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 *
 * @author Shannon Hickey
 * @author Scott Violet
 * 
 * @apiNote {@code <S>} the type of source object that this {@link ELProperty} operates on
 * @apiNote {@code <V>} the type of value that this {@link ELProperty} represents
 */
public final class ELProperty<S, V> extends PropertyHelper<S, V>
{
	private final ValueExpressionPlus valueExpressionPlus;
	private IdentityHashMap<S, SourceEntry> map = new IdentityHashMap<S, SourceEntry>();
	private static final Object NOREAD = new Object();

	private ELContext elContext;
	public ELContext getElContext() { return elContext; }
	public void setElContext(ELContext elContext) { this.elContext = elContext; }

	private Property<S, ?> baseProperty;
	public Property<S, ?> getBaseProperty() { return baseProperty; }
	public void setBaseProperty(Property<S, ?> baseProperty) { this.baseProperty = baseProperty; }
	
	/**
	 * Private Constructor
	 * 
	 * @param elContext EL Context information for {@link ValueExpressionPlus} parsing and evaluation.
	 * @param baseProperty the base property for EL path.
	 * @param expression The EL {@link ValueExpressionPlus}.
	 */
	private ELProperty(ELContext elContext, Property<S, ?> baseProperty, String expression)
	{
		if ( expression == null || expression.length() == 0 )
			throw new IllegalArgumentException("valueExpressionPlus must be non-null and non-empty");
		
		try
		{
			ValueExpression ve = new ExpressionFactoryImpl().createValueExpression(elContext, expression, Object.class);
			this.valueExpressionPlus = new ValueExpressionPlus(ve);
		}
		catch (ELException ele)
		{
			throw new PropertyResolutionException(
				"Error creating EL valueExpressionPlus " + expression,
				ele);
		}
		
		setElContext(elContext);
		setBaseProperty(baseProperty);
	}
	
	/**
	 * Creates an instance of {@link ELProperty} for the given {@link ValueExpressionPlus}.
	 *
	 * @param elContext EL Context information for {@link ValueExpressionPlus} parsing and evaluation.
	 * @param expression The EL value expression plus.
	 * 
	 * @return an instance of {@link ELProperty} for the given {@link ValueExpressionPlus}
	 * 
	 * @throws IllegalArgumentException if the path is null or empty
	 * @throws PropertyResolutionException if there's a problem with the {@link ValueExpressionPlus}
	 */
	public static final <S, V> ELProperty<S, V> create(ELContext elContext, String expression)
	{
		return new ELProperty<S, V>(elContext, null, expression);
	}

	/**
	 * Creates an instance of {@link ELProperty} for the given base property and
	 * {@link ValueExpressionPlus}. The {@link ValueExpressionPlus} is relative to the value of the base property.
	 *
	 * @param elContext EL Context information for {@link ValueExpressionPlus} parsing and evaluation.
	 * @param baseProperty the base property for EL path.
	 * @param expression the {@link ValueExpressionPlus}
	 * 
	 * @return an instance of {@link ELProperty} for the given base property and {@link ValueExpressionPlus}
	 * 
	 * @throws IllegalArgumentException if the path is null or empty
	 * @throws PropertyResolutionException if there's a problem with the {@link ValueExpressionPlus}
	 */
	public static final <S, V> ELProperty<S, V> create(ELContext elContext, Property<S, ?> baseProperty, String expression)
	{
		return new ELProperty<S, V>(elContext, baseProperty, expression);
	}
	
	public Result createResult(ValueExpression ve, boolean track)
	{
		List<ResolvedProperty> resolved = Collections.emptyList();
		return new Result(Result.Type.VALUE, ve.getValue(getElContext()), resolved);
	}

	private final class SourceEntry
		implements PropertyChangeListener, ObservableMapListener, PropertyStateListener
	{
		private S source;
		private Object cachedValue;
		private boolean cachedIsWriteable;
		private Class<?> cachedWriteType;
		private boolean ignoreChange;
		private Set<RegisteredListener> registeredListeners;
		private Set<RegisteredListener> lastRegisteredListeners;

		private SourceEntry(S source)
		{
			this.source = source;
			if ( getBaseProperty() != null )
			{
				getBaseProperty().addPropertyStateListener(source, this);
			}
			registeredListeners = new HashSet<RegisteredListener>(1);
			updateCachedBean();
			updateCache();
		}

		private void cleanup()
		{
			for ( RegisteredListener rl : registeredListeners )
			{
				unregisterListener(rl, this);
			}
			if ( getBaseProperty() != null )
			{
				getBaseProperty().removePropertyStateListener(source, this);
			}
			registeredListeners = null;
			cachedValue = null;
		}

		private boolean cachedIsReadable()
		{
			return cachedValue != NOREAD;
		}

		private void updateCachedBean()
		{
			//cachedBean = getBeanFromSource(source, true);
		}
		
		private void updateCache()
		{
			lastRegisteredListeners = registeredListeners;
			registeredListeners = new HashSet<RegisteredListener>(lastRegisteredListeners.size());
			List<ResolvedProperty> resolvedProperties = null;
			try
			{
				valueExpressionPlus.setSource(getBeanFromSource(source, true));
				Result result = createResult(valueExpressionPlus.getValueExpression(), true);
				if ( result.getType() == Result.Type.UNRESOLVABLE )
				{
					log("updateCache()", "valueExpressionPlus is unresolvable");
					cachedValue = NOREAD;
					cachedIsWriteable = false;
					cachedWriteType = null;
				}
				else
				{
					cachedValue = result.getResult();
					cachedIsWriteable = !valueExpressionPlus.getValueExpression().isReadOnly(elContext);
					cachedWriteType = cachedIsWriteable ? valueExpressionPlus.getValueExpression().getType(elContext) : null;
				}
				resolvedProperties = result.getResolvedProperties();
			}
			catch (ELException ele)
			{
				throw new PropertyResolutionException(
					"Error evaluating EL valueExpressionPlus " + valueExpressionPlus + " on " + source,
					ele);
			}
			finally
			{
				valueExpressionPlus.setSource(null);
			}
			for ( ResolvedProperty prop : resolvedProperties )
			{
				registerListener(prop, this);
			}
			// Uninstall all listeners that are no longer along the path.
			for ( RegisteredListener listener : lastRegisteredListeners )
			{
				unregisterListener(listener, this);
			}
			lastRegisteredListeners = null;
		}

		// flag -1 - validate all
		// flag 0 - source property changed value or readability
		// flag 1 - something else changed
		private void validateCache(int flag)
		{
			/* In the future, this debugging code can be enabled via a flag */
//			if ( flag != 0 && getBeanFromSource(source, false) != cachedBean )
//				log("validateCache()", "concurrent modification");
//			if ( flag != 1 )
//			{
//				try
//				{
//					valueExpressionPlus.setSource(getBeanFromSource(source, true));
//					Expression.Result result = valueExpressionPlus.getResult(elContext, false);
//					Object currValue;
//					boolean currIsWriteable;
//					Class<?> currWriteType;
//					if ( result.getType() == Expression.Result.Type.UNRESOLVABLE )
//					{
//						currValue = NOREAD;
//						currIsWriteable = false;
//						currWriteType = null;
//					}
//					else
//					{
//						currValue = result.getResult();
//						currIsWriteable = !valueExpressionPlus.isReadOnly(elContext);
//						currWriteType = currIsWriteable ? valueExpressionPlus.getType(elContext) : null;
//					}
//					if ( !match(currValue, cachedValue) || currIsWriteable != cachedIsWriteable
//							|| currWriteType != cachedWriteType )
//					{
//						log("validateCache()", "concurrent modification");
//					}
//				}
//				catch (ELException ele)
//				{
//					throw new PropertyResolutionException(
//						"Error evaluating EL valueExpressionPlus " + valueExpressionPlus + " on " + source, ele);
//				}
//				finally
//				{
//					valueExpressionPlus.setSource(null);
//				}
//			}
		}

		@Override
		public void propertyStateChanged(PropertyStateEvent pe)
		{
			if ( !pe.getValueChanged() )
			{
				return;
			}
			validateCache(0);
			Object oldValue = cachedValue;
			boolean wasWriteable = cachedIsWriteable;
			updateCachedBean();
			updateCache();
			notifyListeners(wasWriteable, oldValue, this);
		}

		private void processSourceChanged()
		{
			validateCache(1);
			boolean wasWriteable = cachedIsWriteable;
			Object oldValue = cachedValue;
			updateCache();
			notifyListeners(wasWriteable, oldValue, this);
		}

		private void sourceChanged(Object source, String property)
		{
			if ( ignoreChange )
			{
				return;
			}
			if ( property != null )
			{
				property = property.intern();
			}
			for ( RegisteredListener rl : registeredListeners )
			{
				if ( rl.getSource() == source && (property == null || rl.getProperty() == property) )
				{
					processSourceChanged();
					break;
				}
			}
		}

		@Override
		public void propertyChange(PropertyChangeEvent e)
		{
			sourceChanged(e.getSource(), e.getPropertyName());
		}

		@Override
		public void mapKeyValueChanged(ObservableMap<?, ?> map, Object key, Object lastValue)
		{
			if ( key instanceof String )
			{
				sourceChanged(map, (String) key);
			}
		}

		@Override
		public void mapKeyAdded(ObservableMap<?, ?> map, Object key)
		{
			if ( key instanceof String )
			{
				sourceChanged(map, (String) key);
			}
		}

		@Override
		public void mapKeyRemoved(ObservableMap<?, ?> map, Object key, Object value)
		{
			if ( key instanceof String )
			{
				sourceChanged(map, (String) key);
			}
		}
	}

	/**
	 * An Expression that can get or set a value plus an object source.
	 */
	public static class ValueExpressionPlus extends ValueExpression
	{
		private static final long serialVersionUID = 20240701L;
		private ValueExpression valueExpression;
		public ValueExpression getValueExpression() { return valueExpression; }
		public void setValueExpression(ValueExpression valueExpression) { this.valueExpression = valueExpression; }

		public ValueExpressionPlus(ValueExpression valueExpression)
		{
			setValueExpression(valueExpression);
		}
		
	    private Object source;
	    /**
	     * Returns the source of the {@link ValueExpressionPlus}.
	     *
	     * @return the source of the {@link ValueExpressionPlus}
	     */
	    public Object getSource()
	    {
	    	return source;
	    }
	    /**
	     * Sets the source of the {@link ValueExpressionPlus}. For ValueExpressions that have a 
	     * source, any identifiers are evaluated relative to the source. For
	     * example, if the {@link ValueExpressionPlus} {@code "${first.name}"} has a source,
	     * then {@code "first"} is evaluated relative to the source.
	     *
	     * @param source the initial source for identifiers; may be {@code null}
	     */
	    public void setSource(Object source)
	    {
	    	this.source = source;
	    }

		@Override
		public Object getValue(ELContext context)
		{
			return getValueExpression().getValue(context);
		}

		@Override
		public void setValue(ELContext context, Object value)
		{
			getValueExpression().setValue(context, value);
		}

		@Override
		public boolean isReadOnly(ELContext context)
		{
			return getValueExpression().isReadOnly(context);
		}

		@Override
		public Class<?> getType(ELContext context)
		{
			return getValueExpression().getType(context);
		}

		@Override
		public Class<?> getExpectedType()
		{
			return getValueExpression().getExpectedType();
		}

		@Override
		public String getExpressionString()
		{
			return getValueExpression().getExpressionString();
		}

		@Override
		public boolean equals(Object obj)
		{
			return getValueExpression().equals(obj);
		}

		@Override
		public int hashCode()
		{
			return getValueExpression().hashCode();
		}

		@Override
		public boolean isLiteralText()
		{
			return getValueExpression().isLiteralText();
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * See the class level documentation for the definition of
	 * <a href="#WRITABILITY">writability</a>.
	 *
	 * @throws UnsupportedOperationException {@inheritDoc}
	 * @throws PropertyResolutionException if an exception occurs while
	 *             evaluating the {@link ValueExpressionPlus}
	 * @see #setValue
	 * @see #isWriteable
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Class<? extends V> getWriteType(S source)
	{
		SourceEntry entry = map.get(source);
		if ( entry != null )
		{
			entry.validateCache(-1);
			if ( !entry.cachedIsWriteable )
			{
				throw new UnsupportedOperationException("Unwritable");
			}
			return (Class<? extends V>) entry.cachedWriteType;
		}
		try
		{
			valueExpressionPlus.setSource(getBeanFromSource(source, true));
			Result result = createResult(valueExpressionPlus.getValueExpression(), false);
			if ( result.getType() == Result.Type.UNRESOLVABLE )
			{
				log("getWriteType()", "valueExpressionPlus is unresolvable");
				throw new UnsupportedOperationException("Unwritable");
			}
			if ( valueExpressionPlus.getValueExpression().isReadOnly(elContext) )
			{
				log("getWriteType()", "property is unwritable");
				throw new UnsupportedOperationException("Unwritable");
			}
			return (Class<? extends V>) valueExpressionPlus.getValueExpression().getType(elContext);
		}
		catch (ELException ele)
		{
			throw new PropertyResolutionException(
				"Error evaluating EL valueExpressionPlus " + valueExpressionPlus + " on " + source,
				ele);
		}
		finally
		{
			valueExpressionPlus.setSource(null);
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * See the class level documentation for the definition of
	 * <a href="#READABILITY">readability</a>.
	 *
	 * @throws UnsupportedOperationException {@inheritDoc}
	 * @throws PropertyResolutionException if an exception occurs while
	 *             evaluating the {@link ValueExpressionPlus}
	 * @see #isReadable
	 */
	@Override
	@SuppressWarnings("unchecked")
	public V getValue(S source)
	{
		SourceEntry entry = map.get(source);
		if ( entry != null )
		{
			entry.validateCache(-1);
			if ( entry.cachedValue == NOREAD )
			{
				throw new UnsupportedOperationException("Unreadable");
			}
			return (V) entry.cachedValue;
		}
		try
		{
			valueExpressionPlus.setSource(getBeanFromSource(source, true));
			Result result = createResult(valueExpressionPlus.getValueExpression(), false);
			if ( result.getType() == Result.Type.UNRESOLVABLE )
			{
				log("getValue()", "valueExpressionPlus is unresolvable");
				throw new UnsupportedOperationException("Unreadable");
			}
			return (V) result.getResult();
		}
		catch (ELException ele)
		{
			throw new PropertyResolutionException(
				"Error evaluating EL valueExpressionPlus " + valueExpressionPlus + " on " + source,
				ele);
		}
		finally
		{
			valueExpressionPlus.setSource(null);
		}
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * See the class level documentation for the definition of
	 * <a href="#WRITABILITY">writability</a>.
	 *
	 * @throws UnsupportedOperationException {@inheritDoc}
	 * @throws PropertyResolutionException if an exception occurs while
	 *             evaluating the {@link ValueExpressionPlus}
	 * @see #isWriteable
	 * @see #getWriteType
	 */
	@Override
	public void setValue(S source, V value)
	{
		SourceEntry entry = map.get(source);
		if ( entry != null )
		{
			entry.validateCache(-1);
			if ( !entry.cachedIsWriteable )
			{
				throw new UnsupportedOperationException("Unwritable");
			}
			try
			{
				entry.ignoreChange = true;
				valueExpressionPlus.setSource(getBeanFromSource(source, false));
				valueExpressionPlus.getValueExpression().setValue(elContext, value);
			}
			catch (ELException ele)
			{
				throw new PropertyResolutionException(
					"Error evaluating EL valueExpressionPlus " + valueExpressionPlus + " on " + source,
					ele);
			}
			finally
			{
				entry.ignoreChange = false;
				valueExpressionPlus.setSource(null);
			}
			Object oldValue = entry.cachedValue;
			// PENDING(shannonh) - too heavyweight; should just update cached
			// value
			entry.updateCache();
			notifyListeners(entry.cachedIsWriteable, oldValue, entry);
			return;
		}
		try
		{
			valueExpressionPlus.setSource(getBeanFromSource(source, true));
			Result result = createResult(valueExpressionPlus.getValueExpression(), false);
			if ( result.getType() == Result.Type.UNRESOLVABLE )
			{
				log("setValue()", "valueExpressionPlus is unresolvable");
				throw new UnsupportedOperationException("Unwritable");
			}
			if ( valueExpressionPlus.getValueExpression().isReadOnly(elContext) )
			{
				log("setValue()", "property is unwritable");
				throw new UnsupportedOperationException("Unwritable");
			}
			valueExpressionPlus.getValueExpression().setValue(elContext, value);
		}
		catch (ELException ele)
		{
			throw new PropertyResolutionException(
				"Error evaluating EL valueExpressionPlus " + valueExpressionPlus + " on " + source,
				ele);
		}
		finally
		{
			valueExpressionPlus.setSource(null);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>See the class level documentation for the definition of
	 * <a href="#READABILITY">readability</a>.</p>
	 *
	 * @throws PropertyResolutionException if an exception occurs while
	 *                                     evaluating the {@link ValueExpressionPlus}
	 * @see #isWriteable
	 */
	@Override
	public boolean isReadable(S source)
	{
		SourceEntry entry = map.get(source);
		if ( entry != null )
		{
			entry.validateCache(-1);
			return entry.cachedIsReadable();
		}
		try
		{
			valueExpressionPlus.setSource(getBeanFromSource(source, true));
			Result result = createResult(valueExpressionPlus.getValueExpression(), false);
			if ( result.getType() == Result.Type.UNRESOLVABLE )
			{
				log("isReadable()", "valueExpressionPlus is unresolvable");
				return false;
			}
			return true;
		}
		catch (ELException ele)
		{
			throw new PropertyResolutionException(
				"Error evaluating EL valueExpressionPlus " + valueExpressionPlus + " on " + source,
				ele);
		}
		finally
		{
			valueExpressionPlus.setSource(null);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>See the class level documentation for the definition of
	 * <a href="#WRITABILITY">writability</a>.</p>
	 *
	 * @throws PropertyResolutionException if an exception occurs while
	 *                                     evaluating the {@link ValueExpressionPlus}
	 * 
	 * @see #isReadable
	 */
	@Override
	public boolean isWriteable(S source)
	{
		SourceEntry entry = map.get(source);
		if ( entry != null )
		{
			entry.validateCache(-1);
			return entry.cachedIsWriteable;
		}
		try
		{
			valueExpressionPlus.setSource(getBeanFromSource(source, true));
			Result result = createResult(valueExpressionPlus.getValueExpression(), false);
			if ( result.getType() == Result.Type.UNRESOLVABLE )
			{
				log("isWriteable()", "valueExpressionPlus is unresolvable");
				return false;
			}
			if ( valueExpressionPlus.getValueExpression().isReadOnly(elContext) )
			{
				log("isWriteable()", "property is unwritable");
				return false;
			}
			return true;
		}
		catch (ELException ele)
		{
			throw new PropertyResolutionException(
				"Error evaluating EL valueExpressionPlus " + valueExpressionPlus + " on " + source,
				ele);
		}
		finally
		{
			valueExpressionPlus.setSource(null);
		}
	}

	private Object getBeanFromSource(S source, boolean logErrors)
	{
		if ( getBaseProperty() == null )
		{
			if ( source == null )
			{
				if ( logErrors )
				{
					log("getBeanFromSource()", "source is null");
				}
			}
			return source;
		}
		if ( !getBaseProperty().isReadable(source) )
		{
			if ( logErrors )
			{
				log("getBeanFromSource()", "unreadable source property");
			}
			return NOREAD;
		}
		Object bean = getBaseProperty().getValue(source);
		if ( bean == null )
		{
			if ( logErrors )
			{
				log("getBeanFromSource()", "source property returned null");
			}
			return null;
		}
		return bean;
	}

	@Override
	protected final void listeningStarted(S source)
	{
		SourceEntry entry = map.get(source);
		if ( entry == null )
		{
			entry = new SourceEntry(source);
			map.put(source, entry);
		}
	}

	@Override
	protected final void listeningStopped(S source)
	{
		SourceEntry entry = map.remove(source);
		if ( entry != null )
		{
			entry.cleanup();
		}
	}

	private static boolean didValueChange(Object oldValue, Object newValue)
	{
		return oldValue == null || newValue == null || !oldValue.equals(newValue);
	}

	private void notifyListeners(boolean wasWriteable, Object oldValue, SourceEntry entry)
	{
		PropertyStateListener[] listeners = getPropertyStateListeners(entry.source);
		if ( listeners == null || listeners.length == 0 )
		{
			return;
		}
		oldValue = toUNREADABLE(oldValue);
		Object newValue = toUNREADABLE(entry.cachedValue);
		boolean valueChanged = didValueChange(oldValue, newValue);
		boolean writeableChanged = (wasWriteable != entry.cachedIsWriteable);
		if ( !valueChanged && !writeableChanged )
		{
			return;
		}
		PropertyStateEvent pse = new PropertyStateEvent(this, entry.source, valueChanged, oldValue, newValue,
			writeableChanged, entry.cachedIsWriteable);
		this.firePropertyStateChange(pse);
	}

	/**
	 * Returns a string representation of the {@link ELProperty}. This method is
	 * intended to be used for debugging purposes only, and the content and
	 * format of the returned string may vary between implementations. The
	 * returned string may be empty but may not be {@code null}.
	 *
	 * @return a string representation of this {@link ELProperty}
	 */
	@Override
	public String toString()
	{
		return getClass().getName() + "[" + valueExpressionPlus.getValueExpression() + "]";
	}

	/**
	 * @throws PropertyResolutionException
	 */
	private static BeanInfo getBeanInfo(Object object)
	{
		assert object != null;
		try
		{
			return Introspector.getBeanInfo(object.getClass(), Introspector.IGNORE_ALL_BEANINFO);
		}
		catch (IntrospectionException ie)
		{
			throw new PropertyResolutionException("Exception while introspecting " + object.getClass().getName(), ie);
		}
	}

	private static EventSetDescriptor getEventSetDescriptor(Object object)
	{
		assert object != null;
		EventSetDescriptor[] eds = getBeanInfo(object).getEventSetDescriptors();
		for ( EventSetDescriptor ed : eds )
		{
			if ( ed.getListenerType() == PropertyChangeListener.class )
			{
				return ed;
			}
		}
		return null;
	}

	/**
	 * @throws PropertyResolutionException
	 */
	private static Object invokeMethod(Method method, Object object, Object... args)
	{
		Exception reason = null;
		try
		{
			return method.invoke(object, args);
		}
		catch (IllegalArgumentException ex)
		{
			reason = ex;
		}
		catch (IllegalAccessException ex)
		{
			reason = ex;
		}
		catch (InvocationTargetException ex)
		{
			reason = ex;
		}
		throw new PropertyResolutionException("Exception invoking method " + method + " on " + object, reason);
	}

	private static Object toUNREADABLE(Object src)
	{
		return src == NOREAD ? UNREADABLE : src;
	}

	private void registerListener(ResolvedProperty resolved, SourceEntry entry)
	{
		Object source = resolved.getSource();
		Object property = resolved.getProperty();
		if ( source != null && property instanceof String )
		{
			String sProp = (String) property;
			if ( source instanceof ObservableMap )
			{
				RegisteredListener rl = new RegisteredListener(source, sProp);
				if ( !entry.registeredListeners.contains(rl) )
				{
					if ( !entry.lastRegisteredListeners.remove(rl) )
					{
						((ObservableMap<?, ?>) source).addObservableMapListener(entry);
					}
					entry.registeredListeners.add(rl);
				}
			}
			else if ( !(source instanceof Map) )
			{
				source = getAdapter(source, sProp);
				RegisteredListener rl = new RegisteredListener(source, sProp);
				if ( !entry.registeredListeners.contains(rl) )
				{
					if ( !entry.lastRegisteredListeners.remove(rl) )
					{
						addPropertyChangeListener(source, entry);
					}
					entry.registeredListeners.add(rl);
				}
			}
		}
	}

	private void unregisterListener(RegisteredListener rl, SourceEntry entry)
	{
		Object source = rl.getSource();
		if ( source instanceof ObservableMap )
		{
			((ObservableMap<?, ?>) source).removeObservableMapListener(entry);
		}
		else if ( !(source instanceof Map) )
		{
			removePropertyChangeListener(source, entry);
		}
	}

	/**
	 * @throws PropertyResolutionException
	 */
	private static void addPropertyChangeListener(Object object, PropertyChangeListener listener)
	{
		EventSetDescriptor ed = getEventSetDescriptor(object);
		Method addPCMethod = null;
		if ( ed == null || (addPCMethod = ed.getAddListenerMethod()) == null )
		{
			log("addPropertyChangeListener()", "can't add listener");
			return;
		}
		invokeMethod(addPCMethod, object, listener);
	}

	/**
	 * @throws PropertyResolutionException
	 */
	private static void removePropertyChangeListener(Object object, PropertyChangeListener listener)
	{
		EventSetDescriptor ed = getEventSetDescriptor(object);
		Method removePCMethod = null;
		if ( ed == null || (removePCMethod = ed.getRemoveListenerMethod()) == null )
		{
			log("removePropertyChangeListener()", "can't remove listener from source");
			return;
		}
		invokeMethod(removePCMethod, object, listener);
	}

	private static boolean wrapsLiteral(Object o)
	{
		assert o != null;
		return o instanceof String	|| o instanceof Byte || o instanceof Character || o instanceof Boolean
				|| o instanceof Short || o instanceof Integer || o instanceof Long || o instanceof Float
				|| o instanceof Double;
	}

	// need special match method because when using reflection
	// to get a primitive value, the value is always wrapped in
	// a new object
	@SuppressWarnings("unused")
	private static boolean match(Object a, Object b)
	{
		if ( a == b )
		{
			return true;
		}
		if ( a == null )
		{
			return false;
		}
		if ( wrapsLiteral(a) )
		{
			return a.equals(b);
		}
		return false;
	}

	private Object getAdapter(Object o, String property)
	{
		Object adapter = null;
		adapter = BeanAdapterFactory.getAdapter(o, property);
		return adapter == null ? o : adapter;
	}

	private static final boolean LOG = false;

	private static void log(String method, String message)
	{
		if ( LOG )
		{
			System.err.println("LOG: " + method + ": " + message);
		}
	}

	private static final class RegisteredListener
	{
		private final Object source;
		private final String property;

		@SuppressWarnings("unused")
		RegisteredListener(Object source)
		{
			this.source = source;
			this.property = null;
		}
		
		RegisteredListener(Object source, String property)
		{
			this.source = source;
			if ( property != null )
			{
				property = property.intern();
			}
			this.property = property;
		}

		public Object getSource()
		{
			return source;
		}

		public String getProperty()
		{
			return property;
		}

		@Override
		public boolean equals(Object obj)
		{
			if ( obj == this )
			{
				return true;
			}
			if ( obj instanceof RegisteredListener )
			{
				RegisteredListener orl = (RegisteredListener) obj;
				return (orl.source == source && orl.property == property);
			}
			return false;
		}

		@Override
		public int hashCode()
		{
			int result = 17;
			result = 37 * result + source.hashCode();
			if ( property != null )
			{
				result = 37 * result + property.hashCode();
			}
			return result;
		}

		@Override
		public String toString()
		{
			return "RegisteredListener [" + " source=" + source + " property=" + property + "]";
		}
	}
}
