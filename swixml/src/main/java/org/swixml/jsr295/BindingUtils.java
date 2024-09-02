package org.swixml.jsr295;

import static org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptors;
import static org.jdesktop.beansbinding.BeanProperty.create;
import static org.jdesktop.beansbinding.Bindings.createAutoBinding;
import static org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding;
import static org.jdesktop.swingbinding.SwingBindings.createJListBinding;
import static org.jdesktop.swingbinding.SwingBindings.createJTableBinding;
import static org.swixml.LogUtil.logger;
import static org.swixml.SwingEngine.ENGINE_PROPERTY;
import static org.swixml.SwingEngine.isDesignTime;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.apache.commons.beanutils.MethodUtils;
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
import org.swixml.SwingEngine;
import org.swixml.jsr.widgets.TableColumnBind;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author sorrentino
 */
public class BindingUtils
{
	public static final String TABLE_COLUMN_EDITABLE = "column.editable";
	public static final String TABLE_COLUMN_IS_BOUND = "bind";
	public static final String TABLE_COLUMN_INDEX = "column.index";
	public static final String TABLE_COLUMN_RENDERER = "column.renderer";
	private static final String EL_REGEX = "[$][{](.*)[}]";
	private static final Pattern EL_PATTERN = Pattern.compile(EL_REGEX);

	private BindingUtils()
	{
	}
	
	public static boolean isBound(JComponent comp)
	{
		if ( comp == null )
			throw new IllegalArgumentException("comp argument is null!");
		
		final String name = comp.getClass().getName().concat(".bound");
		return (Boolean.TRUE.equals(comp.getClientProperty(name)));
	}

	public static boolean boundCheckAndSet(JComponent comp)
	{
		if ( comp == null )
			throw new IllegalArgumentException("comp argument is null!");
		
		final String name = comp.getClass().getName().concat(".bound");
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

	public static void setTableColumnRenderer(PropertyDescriptor pd, TableCellRenderer renderer)
	{
		if ( pd == null )
			throw new IllegalArgumentException("parameter pd is null!");
		pd.setValue(BindingUtils.TABLE_COLUMN_RENDERER, renderer);
	}

	public static void setTableColumnIndex(PropertyDescriptor pd, int index)
	{
		if ( pd == null )
			throw new IllegalArgumentException("parameter pd is null!");
		pd.setValue(BindingUtils.TABLE_COLUMN_INDEX, index);
	}

	public static void setTableColumnIsBound(PropertyDescriptor pd, boolean bind)
	{
		if ( pd == null )
			throw new IllegalArgumentException("parameter pd is null!");
		pd.setValue(BindingUtils.TABLE_COLUMN_IS_BOUND, bind);
	}

	public static void setTableColumnEditable(PropertyDescriptor pd, boolean editable)
	{
		if ( pd == null )
			throw new IllegalArgumentException("parameter pd is null!");
		pd.setValue(BindingUtils.TABLE_COLUMN_EDITABLE, editable);
	}

	/**
	 * parse bind for UpdateStrategy.READ_WRITE
	 *
	 * @param owner
	 * @param property
	 * @param converter
	 */
	public static <SS, SV, TS, TV> AutoBinding<SS, SV, ?, TV>  parseBind(JComponent owner,
		String property, String bindProperty, Converter<SS, ?> converter)
	{
		if ( isDesignTime() )
			return null;
		
		if ( boundCheckAndSet(owner) )
			return null;
		
		SwingEngine<?> engine = (SwingEngine<?>) owner.getClientProperty(ENGINE_PROPERTY);
		
		BindingGroup bindingGroup = null;
		UpdateStrategy strategy = UpdateStrategy.READ_WRITE;
		@SuppressWarnings("unchecked")
		SS source = (SS) engine.getClient();
		String beanProperty = bindProperty;
		JComponent target = owner;
		String targetProperty = property;
		Validator<? super SV>  validator = null;
		
		return addAutoBinding(engine, bindingGroup, strategy,
			source, beanProperty, target, targetProperty, converter, validator);
	}

	/**
	 * parse bind for UpdateStrategy.READ
	 *
	 * @param owner
	 * @param property
	 * @param converter
	 */
	public static <SS, SV, TS, TV> AutoBinding<SS, SV, ?, TV> parseBindRead(JComponent owner,
		String property, String bindProperty, Converter<SS, ?> converter)
	{
		if ( isDesignTime() )
			return null;
		
		if ( boundCheckAndSet(owner) )
			return null;

		SwingEngine<?> engine = (SwingEngine<?>) owner.getClientProperty(ENGINE_PROPERTY);

		BindingGroup bindingGroup = null;
		UpdateStrategy strategy = UpdateStrategy.READ;
		@SuppressWarnings("unchecked")
		SS source = (SS) engine.getClient();
		String beanProperty = bindProperty;
		JComponent target = owner;
		String targetProperty = property;
		Validator<? super SV>  validator = null;
		
		return addAutoBinding(engine, bindingGroup, strategy,
			source, beanProperty, target, targetProperty, converter, validator);
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
	 *
	 * @param p
	 * @return
	 */
	private static int getColumnIndex(PropertyDescriptor p)
	{
		final Object i = p.getValue(TABLE_COLUMN_INDEX);
		return (i instanceof Integer) ? (Integer) i : Integer.MAX_VALUE;
	}

	/**
	 * Initialize table binding from {@link TableColumnBind}
	 * 
	 * @param <E> The generic bean type.
	 * @param group An optional {@link BindingGroup} instance.
	 * @param strategy The update strategy for an {@link AutoBinding}.
	 * @param table A {@link JTable} instance.
	 * @param beanList A list of beans (table rows).
	 * 
	 * @return A {@link JTableBinding} instance.
	 */
	public static <E> JTableBinding<E,List<E>,JTable> initTableBindingFromTableColumns(
		BindingGroup group,	UpdateStrategy strategy, JTable table, List<E> beanList)
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
		
		if ( null != group )
			group.addBinding(binding);
		else
			binding.bind();
		
		return binding;
	}

	/**
	 * Initialize table binding from bean properties.
	 * 
	 * @param <E> The generic bean type.
	 * @param group An optional {@link BindingGroup} instance.
	 * @param strategy The update strategy for an {@link AutoBinding}.
	 * @param table A {@link JTable} instance.
	 * @param beanList A list of beans (table rows).
	 * @param beanClass The bean's class.
	 * @param isAllPropertiesBound Should all properties be bound?
	 * 
	 * @return A {@link JTableBinding} instance.
	 */
	@SuppressWarnings("unchecked")
	public static <E> JTableBinding<E,List<E>,JTable> initTableBindingFromBeanInfo(
		BindingGroup group,	UpdateStrategy strategy, JTable table, List<?> beanList,
		Class<E> beanClass,	boolean isAllPropertiesBound)
	{
		if ( null == table )
			throw new IllegalArgumentException("table argument is null!");
		
		if ( null == beanList )
			throw new IllegalArgumentException("beanList argument is null!");
		
		if ( null == beanClass )
			throw new IllegalArgumentException("beanClass argument is null!");
		
		if ( isDesignTime() )
			return null;
		
		if ( boundCheckAndSet(table) )
			return null;
		
		PropertyDescriptor[] pp = getPropertyDescriptors(beanClass);
		
		JTableBinding<?, ?, JTable> binding =
			createJTableBinding(strategy, beanList, table);
		
		if ( null == pp )
		{
			logger.warn("getPropertyDescriptors has returned null!");
			return null;
		}
		
		// Sort property descriptors into column index order.
		Arrays.sort(pp, new Comparator<PropertyDescriptor>()
		{
			@Override
			public int compare(PropertyDescriptor p1, PropertyDescriptor p2)
			{
				final int i1 = getColumnIndex(p1);
				final int i2 = getColumnIndex(p2);
				return (i1 - i2);
			}
		});
		
		for ( PropertyDescriptor p : pp )
		{
			Boolean isBinded = (Boolean) p.getValue(TABLE_COLUMN_IS_BOUND);
			if ( null == isBinded && isAllPropertiesBound == false )
				continue;
			
			if ( (null != isBinded && Boolean.FALSE.equals(isBinded)) )
				continue; // skip property
			
			final String name = p.getName();
			
			if ( "class".equals(name) )
				continue;
			
			Property<?, ?> bp = BeanProperty.create(name);
			ColumnBinding<?, ?, ?> cb = binding.addColumnBinding(bp);
			
			final String displayName = p.getDisplayName();
			cb.setColumnName((null == displayName) ? name : displayName);
			
			final Class<?> type = p.getPropertyType();
			if ( type.isPrimitive() )
				cb.setColumnClass(MethodUtils.toNonPrimitiveClass(type));
			else
				cb.setColumnClass(type);
			
			Boolean isEditable = (Boolean) p.getValue(TABLE_COLUMN_EDITABLE);
			cb.setEditable((null != isEditable && Boolean.TRUE.equals(isEditable)));
		}
		
		if ( null != group )
			group.addBinding(binding);
		else
			binding.bind();
		
		return (JTableBinding<E, List<E>, JTable>) binding;
	}

	/**
	 *
	 * @param group
	 * @param strategy
	 * @param combo
	 * @param beanList
	 */
	@SuppressWarnings("unchecked")
	public static <E> JComboBoxBinding<E, List<E>, JComboBox<?>> initComboBinding(
		BindingGroup group,	UpdateStrategy strategy, JComboBox<?> combo, 
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
		
		if ( null != group )
			group.addBinding(binding);
		else
			binding.bind();
		
		return binding;
	}

	/**
	 *
	 * @param group
	 * @param strategy
	 * @param list
	 * @param beanList
	 */
	@SuppressWarnings("unchecked")
	public static <E> JListBinding<E, List<E>, JList<?>> initListBinding(
		BindingGroup group, UpdateStrategy strategy, JList<?> list,
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
		
		if ( null != group )
			group.addBinding(binding);
		else
			binding.bind();
		
		return binding;
	}
}
