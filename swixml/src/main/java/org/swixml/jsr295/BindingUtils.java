package org.swixml.jsr295;

import static java.beans.Introspector.getBeanInfo;
import static org.apache.commons.beanutils.MethodUtils.toNonPrimitiveClass;
import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ;
import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE;
import static org.jdesktop.beansbinding.BeanProperty.create;
import static org.jdesktop.beansbinding.Bindings.createAutoBinding;
import static org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding;
import static org.jdesktop.swingbinding.SwingBindings.createJListBinding;
import static org.jdesktop.swingbinding.SwingBindings.createJTableBinding;
import static org.swixml.LogUtil.logger;
import static org.swixml.SwingEngine.ENGINE_PROPERTY;
import static org.swixml.SwingEngine.isDesignTime;

import java.awt.Container;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.beanutils.PropertyUtils;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Converter;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.beansbinding.Property;
import org.jdesktop.beansbinding.Validator;
import org.jdesktop.swingbinding.ColumnBinding;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JListBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jvnet.basicjaxb.lang.FieldDescriptor;
import org.swixml.SwingEngine;
import org.swixml.jsr.widgets.TableColumnBind;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author sorrentino
 */
public class BindingUtils
{
	private static final String BOUND_SUFFIX = ".bound";

	private static final String EL_REGEX = "[$][{](.*)[}]";
	private static final Pattern EL_PATTERN = Pattern.compile(EL_REGEX);
	
	private BindingUtils()
	{
	}
	
	public static boolean isBound(JComponent comp)
	{
		if ( comp == null )
			throw new IllegalArgumentException("comp argument is null!");
		
		final String name = comp.getClass().getName().concat(BOUND_SUFFIX);
		return (Boolean.TRUE.equals(comp.getClientProperty(name)));
	}

	/**
	 * Bound check and set the given {@link JComponent} instance.
	 * Checks the component's client properties for a key matching
	 * the component's class name concatenated with the {@code BOUND_SUFFIX}.
	 * 
	 * <p>Note: This methods puts a bound check in the client's properties
	 * when it does not exists and then returns false.</p>
	 * 
	 * @param comp The component to check and set.
	 * 
	 * @return Return's true when the bound check exists; otherwise, false.
	 */
	public static boolean boundCheckAndSet(JComponent comp)
	{
		if ( comp == null )
			throw new IllegalArgumentException("comp argument is null!");
		
		final String name = comp.getClass().getName().concat(BOUND_SUFFIX);
		if ( Boolean.TRUE.equals(comp.getClientProperty(name)) )
			return true;
		
		comp.putClientProperty(name, Boolean.TRUE);
		return false;
	}

	/**
	 * Does the given value match the EL pattern?
	 * 
	 * @param value The value to match.
	 * 
	 * @return True, when the value matches the EL pattern; otherwise, false.
	 */
	public static boolean isELPattern(String value)
	{
		if ( null == value )
			return false;
		return EL_PATTERN.matcher(value).matches();
		// return Pattern.matches(EL_REGEX, value);
	}

	/**
	 *
	 * @param value
	 * @return
	 */
	public static Matcher getELMatcher(String value)
	{
		if ( null == value )
			throw new IllegalArgumentException("value is null!");
		return EL_PATTERN.matcher(value);
	}

	/**
	 *
	 * @param beanClass
	 * @param propertyName
	 * @return
	 */
	public static PropertyDescriptor findProperty(Class<?> beanClass, String propertyName)
	{
		if ( null == propertyName )
			throw new IllegalArgumentException("propertyName param is null!");
		PropertyDescriptor[] pp = PropertyUtils.getPropertyDescriptors(beanClass);
		for ( PropertyDescriptor pd : pp )
		{
			if ( propertyName.equalsIgnoreCase(pd.getName()) )
				return pd;
		}
		return null;
	}

	/**
	 *
	 * @param beanClass
	 * @return
	 */
	public static Map<String, PropertyDescriptor> getPropertyMap(Class<?> beanClass)
	{
		PropertyDescriptor pp[] = PropertyUtils.getPropertyDescriptors(beanClass);
		Map<String, PropertyDescriptor> map = new HashMap<String, PropertyDescriptor>(pp.length);
		for ( PropertyDescriptor pd : pp )
			map.put(pd.getName(), pd);
		return map;
	}
	
	/**
	 * Parse binding for owner using {@code UpdateStrategy.READ_WRITE} without a
	 * {@link BindingGroup} or a {@link Validator}.
	 * 
	 * <p>Bound check and set the owner then make it the target of an auto-binding.</p>
	 * 
	 * <p>The source object is obtained from the current {@link SwingEngine} instance as
	 * its client {@link Container} instance.</p>
	 *
	 * @param owner A {@link JComponent} instance to be the target of an auto-binding.
	 * @param targetProperty The target property name.
	 * @param bindProperty The source property name.
	 * @param converter An optional {@link Converter} instance.
	 * 
	 * @return The new {@link AutoBinding} instance.
	 */
	public static <SS, SV, TS, TV> AutoBinding<SS, SV, ?, TV>  parseBind(JComponent owner,
		String targetProperty, String bindProperty, Converter<SS, ?> converter)
	{
		return parseBind(null, owner, targetProperty, bindProperty, converter);
	}

	/**
	 * Parse binding for owner using {@code UpdateStrategy.READ} without a
	 * {@link BindingGroup} or a {@link Validator}.
	 * 
	 * <p>Bound check and set the owner then make it the target of an auto-binding.</p>
	 * 
	 * <p>The source object is obtained from the current {@link SwingEngine} instance as
	 * its client {@link Container} instance.</p>
	 *
	 * @param owner A {@link JComponent} instance to be the target of an auto-binding.
	 * @param targetProperty The target property name.
	 * @param bindProperty The source property name.
	 * @param converter An optional {@link Converter} instance.
	 * 
	 * @return The new {@link AutoBinding} instance.
	 */
	public static <SS, SV, TS, TV> AutoBinding<SS, SV, ?, TV> parseBindRead(JComponent owner,
		String targetProperty, String bindProperty, Converter<SS, ?> converter)
	{
		return parseBindRead(null, owner, targetProperty, bindProperty, converter);
	}
	
	/**
	 * Parse binding for owner using {@code UpdateStrategy.READ_WRITE} with a {@link BindingGroup}
	 * instance but without a {@link Validator}.
	 * 
	 * <p>Bound check and set the owner then make it the target of an auto-binding.</p>
	 * 
	 * <p>The source object is obtained from the current {@link SwingEngine} instance as
	 * its client {@link Container} instance.</p>
	 *
	 * @param bindingGroup A group of {@code Bindings} to operate on and/or track state changes.
	 * @param owner A {@link JComponent} instance to be the target of an auto-binding.
	 * @param targetProperty The target property name.
	 * @param bindProperty The source property name.
	 * @param converter An optional {@link Converter} instance.
	 * 
	 * @return The new {@link AutoBinding} instance.
	 */
	public static <SS, SV, TS, TV> AutoBinding<SS, SV, ?, TV>  parseBind(BindingGroup bindingGroup,
		JComponent owner, String targetProperty, String bindProperty, Converter<SS, ?> converter)
	{
		return parseBind(bindingGroup, READ_WRITE, owner, targetProperty, bindProperty, converter, null);
	}

	/**
	 * Parse binding for owner using {@code UpdateStrategy.READ} with a {@link BindingGroup}
	 * instance but without a {@link Validator}.
	 * 
	 * <p>Bound check and set the owner then make it the target of an auto-binding.</p>
	 * 
	 * <p>The source object is obtained from the current {@link SwingEngine} instance as
	 * its client {@link Container} instance.</p>
	 *
	 * @param bindingGroup A group of {@code Bindings} to operate on and/or track state changes.
	 * @param owner A {@link JComponent} instance to be the target of an auto-binding.
	 * @param targetProperty The target property name.
	 * @param bindProperty The source property name.
	 * @param converter An optional {@link Converter} instance.
	 * 
	 * @return The new {@link AutoBinding} instance.
	 */
	public static <SS, SV, TS, TV> AutoBinding<SS, SV, ?, TV> parseBindRead(BindingGroup bindingGroup,
		JComponent owner, String targetProperty, String bindProperty, Converter<SS, ?> converter)
	{
		return parseBind(bindingGroup, READ, owner, targetProperty, bindProperty, converter, null);
	}
	
	/**
	 * Parse binding for owner using {@code UpdateStrategy.READ_WRITE} with an optional
	 * {@link BindingGroup} and optional {@link Validator}.
	 * 
	 * <p>Bound check and set the owner then make it the target of an auto-binding.</p>
	 * 
	 * <p>The source object is obtained from the current {@link SwingEngine} instance as
	 * its client {@link Container} instance.</p>
	 *
	 * @param bindingGroup A group of {@code Bindings} to operate on and/or track state changes.
	 * @param owner A {@link JComponent} instance to be the target of an auto-binding.
	 * @param targetProperty The target property name.
	 * @param bindProperty The source property name.
	 * @param converter An optional {@link Converter} instance.
	 * @param validator An optional {@link Validator} instance.
	 * 
	 * @return The new {@link AutoBinding} instance.
	 */
	public static <SS, SV, TS, TV> AutoBinding<SS, SV, ?, TV>  parseBind(BindingGroup bindingGroup,
		JComponent owner, String targetProperty, String bindProperty,
		Converter<SS, ?> converter, Validator<? super SV>  validator)
	{
		return parseBind(bindingGroup, READ_WRITE, owner, targetProperty, bindProperty, converter, validator);
	}

	/**
	 * Parse binding for owner using {@code UpdateStrategy.READ}.
	 * 
	 * <p>Bound check and set the owner then make it the target of an auto-binding.</p>
	 * 
	 * <p>The source object is obtained from the current {@link SwingEngine} instance as
	 * its client {@link Container} instance.</p>
	 *
	 * @param bindingGroup A group of {@code Bindings} to operate on and/or track state changes.
	 * @param owner A {@link JComponent} instance to be the target of an auto-binding.
	 * @param targetProperty The target property name.
	 * @param bindProperty The source property name.
	 * @param converter An optional {@link Converter} instance.
	 * @param validator An optional {@link Validator} instance.
	 * 
	 * @return The new {@link AutoBinding} instance.
	 */
	public static <SS, SV, TS, TV> AutoBinding<SS, SV, ?, TV> parseBindRead(BindingGroup bindingGroup,
		JComponent owner, String targetProperty, String bindProperty,
		Converter<SS, ?> converter, Validator<? super SV>  validator)
	{
		return parseBind(bindingGroup, READ, owner, targetProperty, bindProperty, converter, validator);
	}
	
	/**
	 * Parse binding for owner using {@code UpdateStrategy.READ_WRITE}.
	 * 
	 * <p>Bound check and set the owner then make it the target of an auto-binding.</p>
	 * 
	 * <p>The source object is obtained from the current {@link SwingEngine} instance as
	 * its client {@link Container} instance.</p>
	 * 
	 * <p>When a {@link BindingGroup} instance is provided a new {@link AutoBinding} instance is
	 * added to it and the {@code bind()} is not invoked; otherwise, the {@code bind()} is invoked
	 * on the new {@link AutoBinding} instance.</p>
	 *
	 * @param bindingGroup A group of {@code Bindings} to operate on and/or track state changes.
	 * @param strategy The {@link UpdateStrategy} to synchronize the source and target properties.
	 * @param owner A {@link JComponent} instance to be the target of an auto-binding.
	 * @param targetProperty The target property name.
	 * @param bindProperty The source property name.
	 * @param converter An optional {@link Converter} instance.
	 * @param validator An optional {@link Validator} instance.
	 * 
	 * @return The new {@link AutoBinding} instance.
	 */
	public static <SS, SV, TS, TV> AutoBinding<SS, SV, ?, TV>  parseBind(BindingGroup bindingGroup,
		UpdateStrategy strategy, JComponent owner, String targetProperty, String bindProperty,
		Converter<SS, ?> converter, Validator<? super SV>  validator)
	{
		if ( isDesignTime() )
			return null;
		
		if ( boundCheckAndSet(owner) )
			return null;
		
		SwingEngine<?> engine = (SwingEngine<?>) owner.getClientProperty(ENGINE_PROPERTY);
		
		@SuppressWarnings("unchecked")
		SS source = (SS) engine.getClient();
		JComponent target = owner;
		
		return addAutoBinding(engine, bindingGroup, strategy,
			source, bindProperty, target, targetProperty, converter, validator);
	}

	/**
	 *
	 * @param bindingGroup
	 * @param strategy
	 * @param source
	 * @param beanProperty
	 * @param target
	 * @param targetProperty
	 */
	@SuppressWarnings("unchecked")
	private static <SS, SV, TS, TV> AutoBinding<SS, SV, TS, TV> addAutoBinding(
		SwingEngine<?> engine, BindingGroup bindingGroup,
		UpdateStrategy strategy,SS source, String beanProperty, TS target, String targetProperty,
		Converter<SS, ?> converter, Validator<? super SV> validator)
	{
		if ( null == source )
			throw new IllegalArgumentException("bean argument is null!");
		
		if ( null == target )
			throw new IllegalArgumentException("target argument is null!");
		
		if ( null == targetProperty )
			throw new IllegalArgumentException("targetProperty argument is null!");
		
		Property<TS,TV> tp = null;
		if ( targetProperty.startsWith("$") )
			tp = ELProperty.create(engine.getELContext(), targetProperty);
		else
			tp = BeanProperty.create(targetProperty);
		
		final AutoBinding<SS, SV, TS, TV> binding = createAutoBinding(strategy,
			source, create(beanProperty), target, tp);
		
		if ( converter != null )
			binding.setConverter((Converter<SV, TV>) converter);
		
		if ( validator != null )
			binding.setValidator((Validator<? super SV>) validator);
		
		if ( null != bindingGroup )
			bindingGroup.addBinding(binding);
		else
			binding.bind();
		
		return binding;
	}

	/**
	 * Initialize table binding from {@link TableColumnBind}
	 * 
	 * @param <E> The generic bean type.
	 * @param bindingGroup An optional {@link BindingGroup} instance.
	 * @param strategy The update strategy for an {@link AutoBinding}.
	 * @param table A {@link JTable} instance.
	 * @param beanList A list of beans (table rows).
	 * 
	 * @return A {@link JTableBinding} instance.
	 */
	public static <E> JTableBinding<E,List<E>,JTable> initTableBindingFromTableColumns(
		BindingGroup bindingGroup,	UpdateStrategy strategy, JTable table, List<E> beanList)
	{
		if ( null == table )
			throw new IllegalArgumentException("table argument is null!");
		
		if ( null == beanList )
			throw new IllegalArgumentException("beanList argument is null!");
		
		if ( isDesignTime() )
			return null;
		
		if ( boundCheckAndSet(table) )
			return null;
		
		final TableColumnModel columnModel = table.getColumnModel();
		if ( null == columnModel )
			throw new IllegalStateException("columnModel is not set!");
		
		Enumeration<TableColumn> tableColumns = columnModel.getColumns();
		if ( null == tableColumns )
			throw new IllegalStateException("columnModel hasn't not tableColumns!");
		
		JTableBinding<E,List<E>,JTable> binding = createJTableBinding(strategy, beanList, table);
		
		while (tableColumns.hasMoreElements())
		{
			TableColumn tc = tableColumns.nextElement();
			if ( !(tc instanceof TableColumnBind) )
			{
				logger.warn(
					String.format("column [%s] is not valid. It will be ignored in binding!", tc.getIdentifier()));
				continue;
			}
			
			TableColumnBind tcb = (TableColumnBind) tc;
			logger.info(String.format(
				"column [%s] header=[%s] modelIndex=[%d] resizable=[%b] minWidth=[%s] maxWidth=[%d] preferredWidth=[%d]",
				tcb.getIdentifier(), tcb.getHeaderValue(), tcb.getModelIndex(), tcb.getResizable(), tcb.getMinWidth(),
				tcb.getMaxWidth(), tcb.getPreferredWidth()));
			
			final String propertyName = tcb.getBindWith();
			if ( null == propertyName )
			{
				logger.warn(String.format("column [%s] has not set bindWith property. It will be ignored in binding!",
					tcb.getIdentifier()));
				continue;
			}
			
			//
			// Property Binding
			//
			Property<E, ?> bp = BeanProperty.create(propertyName);
			ColumnBinding<?, ?, ?> cb = binding.addColumnBinding(bp);
			
			//
			// set Header
			//
			final Object headerValue = tcb.getHeaderValue();
			if ( null == headerValue )
				tcb.setHeaderValue(propertyName);
			cb.setColumnName(tcb.getHeaderValue().toString());
			
			//
			// set Property type
			//
			final String typeName = tcb.getType();
			if ( null != typeName )
			{
				Class<?> typeClass = null;
				try
				{
					typeClass = Class.forName(typeName);
					cb.setColumnClass(typeClass);
				}
				catch (ClassNotFoundException e)
				{
					logger.warn(String.format("column type [%s] is not valid java type. It will be ignored in binding!",
						typeName));
				}
			}
			
			//
			// set Editable
			//
			cb.setEditable(tcb.isEditable());
		}
		
		if ( null != bindingGroup )
			bindingGroup.addBinding(binding);
		else
			binding.bind();
		
		return binding;
	}

	/**
	 * Initialize table binding from bean properties.
	 * 
	 * @param <E> The generic bean type.
	 * @param bindingGroup An optional {@link BindingGroup} instance.
	 * @param strategy The update strategy for an {@link AutoBinding}.
	 * @param table A {@link JTable} instance.
	 * @param beanList A list of beans (table rows).
	 * @param fdArray The {@link FieldDescriptor} array.
	 * @param isAllPropertiesBound Should all properties be bound?
	 * 
	 * @return A {@link JTableBinding} instance.
	 */
	@SuppressWarnings("unchecked")
	public static <E> JTableBinding<E,List<E>,JTable> initTableBindingFromBeanInfo(
		BindingGroup bindingGroup,	UpdateStrategy strategy, JTable table, List<?> beanList,
		FieldDescriptor[] fdArray,	boolean isAllPropertiesBound)
	{
		if ( null == table )
			throw new IllegalArgumentException("table argument is null!");
		
		if ( null == beanList )
			throw new IllegalArgumentException("beanList argument is null!");
		
		if ( isDesignTime() )
			return null;
		
		if ( boundCheckAndSet(table) )
			return null;

		JTableBinding<?, ?, JTable> binding =
			createJTableBinding(strategy, beanList, table);
					
		for ( FieldDescriptor fd : fdArray )
		{
			boolean isBound = fd.isBound();
			// skip property when it is not bound
			if ( !isBound && isAllPropertiesBound == false )
				continue; 
			
			final String name = fd.getName();
			// skip the class property
			if ( "class".equals(name) )
				continue;
			
			Property<?, ?> bp = BeanProperty.create(name);
			ColumnBinding<?, ?, ?> cb = binding.addColumnBinding(bp);
			
			final String displayName = fd.getDisplayName();
			cb.setColumnName((displayName == null) ? name : displayName);
			
			final Class<?> type = fd.getPropertyType();
			if ( type.isPrimitive() )
				cb.setColumnClass(toNonPrimitiveClass(type));
			else
				cb.setColumnClass(type);
			
			Boolean isEditable = fd.isEditable();
			cb.setEditable((isEditable != null) && isEditable);
		}
		
		if ( null != bindingGroup )
			bindingGroup.addBinding(binding);
		else
			binding.bind();
		
		return (JTableBinding<E, List<E>, JTable>) binding;
	}
	
	/**
	 * Re-index and sort an array of introspected {@link FieldDescriptor}s.
	 * 
	 * @param bfdArray1 An array of BeanInfo introspected field descriptors.
	 * 
	 * @return An index-ordered array of {@link FieldDescriptor}s.
	 */
	private static FieldDescriptor[] reindex(FieldDescriptor[] bfdArray1)
	{
		// Order FieldDescriptor(s) by existing column index, if any;
		// otherwise, define the index alphabetically by display name.
		TreeMap<Integer, FieldDescriptor> bfdMap1 = new TreeMap<>();
		TreeMap<String, FieldDescriptor> bfdMap2 = new TreeMap<>();
		for ( FieldDescriptor bfd : bfdArray1 )
		{
			if ( bfd.getIndex() != null  )
				bfdMap1.put(bfd.getIndex(), bfd);
			else
				bfdMap2.put(bfd.getDisplayName(), bfd);
		}
		
		// Index cached FieldDescriptor(s) alphabetically by display name.
		if ( !bfdMap2.isEmpty() )
		{
			Integer index = bfdMap1.isEmpty() ? 0 : bfdMap1.lastKey();
			for ( FieldDescriptor bfd2 : bfdMap2.values() )
				bfdMap1.put(++index, bfd2);
		}
		
		// Re-index, in sequence without gaps, zero-based.
		// Note: The bfdMap1's value iterator returns the values in
		//       ascending order of the corresponding keys.
		FieldDescriptor[] bdfArray2 = new FieldDescriptor[bfdMap1.size()];
		int index = 0;
		for ( FieldDescriptor bfd1 : bfdMap1.values() )
		{
			bfd1.setIndex(index);
			bdfArray2[index++] = bfd1;
		}
		return bdfArray2;
	}
	
	/**
	 * Promote an array of {@link PropertyDescriptor} to an array of
	 * {@link FieldDescriptor}.
	 * 
	 * @param <E> The generic type of the bean class.
	 * @param pdArray The array of property descriptors to promote.
	 * @param beanClass The bean class owning the field.
	 * 
	 * @return An index-ordered array of field descriptors.
	 * 
	 * @throws IntrospectionException When the bean class cannot be introspected.
	 */
	public static <E> FieldDescriptor[] toFieldDescriptors(PropertyDescriptor[] pdArray, Class<E> beanClass)
		throws IntrospectionException
	{
		FieldDescriptor[] fdArray = new FieldDescriptor[pdArray.length];
		for ( int index=0; index < pdArray.length; ++index )
		{
			PropertyDescriptor pd = pdArray[index];
			if ( pd instanceof FieldDescriptor )
				fdArray[index] = FieldDescriptor.promote(pd);
			else
				fdArray[index] = new FieldDescriptor(pd);
		}
		// Re-index and sort an array of introspected {@link FieldDescriptor}s.
		return reindex(fdArray);
	}
	
	/**
	 * Introspect on a Java bean and learn all about its properties, exposed
     * methods, below a given "stop" point. If the Introspector finds an associated
	 * BeanInfo class for the bean, then it will merge addition metadata from it.
	 * 
	 * @param beanClass The bean class to be introspected.
	 * 
	 * @return An array of {@link FieldDescriptor} ordered by index.
	 */
	public static FieldDescriptor[] getFieldDescriptors(Class<?> beanClass)
	{
		FieldDescriptor[] fdArray = null;
		try
		{
			BeanInfo beanInfo = getBeanInfo(beanClass, Object.class);
			PropertyDescriptor[] pdArray = beanInfo.getPropertyDescriptors();
			fdArray = toFieldDescriptors(pdArray, beanClass);
		}
		catch (IntrospectionException ie)
		{
			fdArray = new FieldDescriptor[0];
			logger.error("getFieldDescriptors: ", ie);
		}
		return fdArray;
	}

	/**
	 *
	 * @param bindingGroup
	 * @param strategy
	 * @param combo
	 * @param beanList
	 */
	@SuppressWarnings("unchecked")
	public static <E> JComboBoxBinding<E, List<E>, JComboBox<?>> initComboBinding(
		BindingGroup bindingGroup,	UpdateStrategy strategy, JComboBox<?> combo, 
		List<E> beanList, Converter<?, ?> converter)
	{
		if ( null == combo )
			throw new IllegalArgumentException("combo argument is null!");
		
		if ( null == beanList )
			throw new IllegalArgumentException("beanList argument is null!");
		
		if ( isDesignTime() )
			return null;
		
		if ( boundCheckAndSet(combo) )
			return null;
		
		JComboBoxBinding<E, List<E>, JComboBox<?>> binding =
			createJComboBoxBinding(strategy, beanList, combo);
		
		if ( converter != null )
			binding.setConverter((Converter<List<E>, List<?>>) converter);
		
		if ( null != bindingGroup )
			bindingGroup.addBinding(binding);
		else
			binding.bind();
		
		return binding;
	}

	/**
	 *
	 * @param bindingGroup
	 * @param strategy
	 * @param list
	 * @param beanList
	 */
	@SuppressWarnings("unchecked")
	public static <E> JListBinding<E, List<E>, JList<?>> initListBinding(
		BindingGroup bindingGroup, UpdateStrategy strategy, JList<?> list,
		List<E> beanList, Converter<?, ?> converter)
	{
		if ( null == list )
			throw new IllegalArgumentException("list argument is null!");
		
		if ( null == beanList )
			throw new IllegalArgumentException("beanList argument is null!");
		
		if ( isDesignTime() )
			return null;
		
		if ( boundCheckAndSet(list) )
			return null;
		
		JListBinding<E, List<E>, JList<?>> binding =
			createJListBinding(strategy, beanList, list);
		
		if ( converter != null )
			binding.setConverter((Converter<List<E>, List<?>>) converter);
		
		if ( null != bindingGroup )
			bindingGroup.addBinding(binding);
		else
			binding.bind();
		
		return binding;
	}
}
