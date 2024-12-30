package org.swixml.jsr.widgets;

import static java.lang.Math.max;
import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE;
import static org.jvnet.basicjaxb.lang.FieldDescriptor.alignByType;
import static org.jvnet.basicjaxb.lang.StringUtils.isBlank;
import static org.swixml.SwingEngine.ENGINE_PROPERTY;
import static org.swixml.el.ELMethods.AVERAGE_LETTER;
import static org.swixml.jsr295.BindingUtils.initTableBindingFromBeanInfo;
import static org.swixml.jsr295.BindingUtils.initTableBindingFromTableColumns;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Converter;
import org.jvnet.basicjaxb.lang.Alignment;
import org.jvnet.basicjaxb.lang.DataBeanInfo;
import org.jvnet.basicjaxb.lang.FieldDescriptor;
import org.swixml.SwingEngine;
import org.swixml.el.ELMethods;
import org.swixml.renderers.AlignableTableCellHeaderRenderer;
import org.swixml.renderers.AlignableTableCellRenderer;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.validation.metadata.BeanDescriptor;
import jakarta.validation.metadata.ConstraintDescriptor;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * @author sorrentino
 */
public class JTableBind
	extends JTable
	implements BindableListWidget
{
	private static final long serialVersionUID = 20240701L;
	
	private static final int DEFAULT_TABLECOLUMN_MINWIDTH = 15;
	private static final int DEFAULT_TABLECOLUMN_MAXWIDTH = Integer.MAX_VALUE;
	private static final int DEFAULT_TABLECOLUMN_PREFWIDTH = 75;
	
	private Class<?> bindClass;
	public Class<?> getBindClass()
	{
		return bindClass;
	}
	public void setBindClass(Class<?> bindClass)
	{
		this.bindClass = bindClass;
	}

	private List<?> bindList;
	@Override
	public List<?> getBindList()
	{
		return bindList;
	}
	@Override
	public void setBindList(List<?> bindList)
	{
		this.bindList = bindList;
	}

	@Override
	public String getBindWith()
	{
		return (String) getClientProperty(BINDWITH_PROPERTY);
	}
	@Override
	public void setBindWith(String bindWith)
	{
		putClientProperty(BINDWITH_PROPERTY, bindWith);
	}

	@Override
	public Converter<?, ?> getConverter()
	{
		return null;
	}
	@Override
	public void setConverter(Converter<?, ?> converter)
	{
	}
	
	@Override
	public Binding<?, ?, ?, ?> getBinding()
	{
		return (Binding<?, ?, ?, ?>) getClientProperty(BINDING_PROPERTY);
	}
	@Override
	public void setBinding(Binding<?, ?, ?, ?> binding)
	{
		putClientProperty(BINDING_PROPERTY, binding);
	}
	
	@Override
	public BindingGroup getBindingGroup()
	{
		BindingGroup bindingGroup = (BindingGroup) getClientProperty(BINDING_GROUP_PROPERTY);
		if ( bindingGroup == null )
		{
			bindingGroup = new BindingGroup();
			setBindingGroup(bindingGroup);
			return bindingGroup;
		}
		else
			return (BindingGroup) bindingGroup;
	}
	@Override
	public void setBindingGroup(BindingGroup bindingGroup)
	{
		putClientProperty(BINDING_GROUP_PROPERTY, bindingGroup);
	}
	
	private DataBeanInfo dataBeanInfo;
	public DataBeanInfo getDataBeanInfo()
	{
		if ( dataBeanInfo == null )
		{
			try
			{
				BeanInfo beanInfo = Introspector.getBeanInfo(getBindClass());
				if ( beanInfo instanceof DataBeanInfo )
					setDataBeanInfo((DataBeanInfo) beanInfo);
				else
					setDataBeanInfo(new DataBeanInfo(beanInfo));
			}
			catch (IntrospectionException e)
			{
				setDataBeanInfo(new DataBeanInfo());
			}
		}
		return dataBeanInfo;
	}
	public void setDataBeanInfo(DataBeanInfo dataBeanInfo)
	{
		this.dataBeanInfo = dataBeanInfo;
	}

	/**
	 * Get the array of {@link FieldDescriptor}(s) using
	 * Java Beans {@link Introspector}.
	 * 
	 * @return An array of {@link FieldDescriptor}(s).
	 */
	public FieldDescriptor[] getFieldDescriptors()
	{
		return getDataBeanInfo().getFieldDescriptors();
	}

	private Action action;
	public Action getAction()
	{
		return action;
	}
	public void setAction(Action action)
	{
		this.action = action;
	}

	private Action dblClickAction = null;
	public final Action getDblClickAction()
	{
		return dblClickAction;
	}
	public final void setDblClickAction(Action dblClickAction)
	{
		this.dblClickAction = dblClickAction;
	}

	private boolean allPropertiesBound = true;
	public final boolean isAllPropertiesBound()
	{
		return allPropertiesBound;
	}

	public final void setAllPropertiesBound(boolean allPropertyBound)
	{
		this.allPropertiesBound = allPropertyBound;
	}

	@Override
	public TableCellRenderer getCellRenderer(int row, int col)
	{
		if ( getBindClass() != null && getBindList() != null )
		{
			FieldDescriptor[] fds = getFieldDescriptors();
			FieldDescriptor fd = fds[col];
			TableCellRenderer tcr = fd.getRenderer();
			if ( tcr != null )
				return tcr;
		}
		return super.getCellRenderer(row, col);
	}
	
//	private static PropertyDescriptor[] getPropertyDescriptors(Class<?> beanClass)
//	{
//		PropertyDescriptor[] pdArray = null;
//		try
//		{
//			BeanInfo beanInfo = getBeanInfo(beanClass, Object.class);
//			pdArray = beanInfo.getPropertyDescriptors();
//		}
//		catch (IntrospectionException ie)
//		{
//			pdArray = new PropertyDescriptor[0];
//		}
//		return pdArray;
//	}
	
	private SwingEngine<?> getSwingEngine()
	{
		return (SwingEngine<?>) getClientProperty(ENGINE_PROPERTY);
	}
	
	private int fieldWidth(int repeat)
	{
		ELMethods<?> elm = getSwingEngine().getELMethods();
		int sw = elm.getFontMetrics(getFont()).stringWidth(AVERAGE_LETTER);
		// PAD = 4
		return repeat * sw + 4;
	}
	
	/**
	 * Create default columns from the model, or from the
	 * bind class when configured.
	 * 
	 * <p>When a bind class is configured, column properties are derived
	 * from multiple sources: SWIXML table column(s), validation metadata
	 * and BeanInfo property descriptor(s).</p>
	 */
	@Override
	public void createDefaultColumnsFromModel()
	{
		// If there a bind class then derive column properties
		// from it; otherwise, fall back to the super method.
		TableModel tm = getModel();
		if ( (tm != null) && (getBindClass() != null) )
		{
			// Cache any current columns from the SWIXML configuration.
			Map<String, TableColumnBind> tcMap = new HashMap<>();
			TableColumnModel cm = getColumnModel();
			Enumeration<TableColumn> cme = cm.getColumns();
			while ( cme.hasMoreElements() )
			{
				TableColumn tc = cme.nextElement();
				if ( tc instanceof TableColumnBind )
				{
					TableColumnBind tcb = (TableColumnBind) tc;
					tcMap.put(tcb.getBindWith(), tcb);
				}
			}
			
			// Remove any current columns before re-creating.
			while (cm.getColumnCount() > 0)
				cm.removeColumn(cm.getColumn(0));
			
			// Create a map of the validation descriptors by property name.
			// These descriptors may provide additional property constraints.
			BeanDescriptor vbd = getSwingEngine().getBeanValidator()
				.getConstraintsForClass(getBindClass());
			Set<jakarta.validation.metadata.PropertyDescriptor> vcpSet =
				vbd.getConstrainedProperties();
			Map<String, jakarta.validation.metadata.PropertyDescriptor> vpdMap = new HashMap<>();
			for ( jakarta.validation.metadata.PropertyDescriptor vcp : vcpSet )
				vpdMap.put(vcp.getPropertyName(), vcp);

			// Create new columns from properties of the bind class.
			//
			// Introspect on a bind class and learn all about its properties.
			// If the Introspector finds an associated BeanInfo class for the
			// bind class, then it will merge addition metadata from it.
			// A FieldDescriptor extends PropertyDescriptor and accumulates
			// display field level metadata.
			FieldDescriptor[] bfdArray = getFieldDescriptors();
			
			// Add a TableColumnBind for each non-hidden property of the bind class.
			for ( int index=0; index < bfdArray.length; ++index )
			{
				FieldDescriptor bfd = bfdArray[index];
				Class<?> type = bfd.getPropertyType();

				// Hidden: Use BeanInfo inferred values or 'by type'.
				boolean hidden = bfd.isHidden();
				if ( !hidden )
					hidden = FieldDescriptor.hideByType(type);
				if ( !hidden )
				{
					//
					// Get the Bean and the Validation property descriptors.
					//
					
					// Set the model index for this column. The model index is the
					// index of the column in the model that will be displayed by the
					// <code>TableColumn</code>. As the <code>TableColumn</code>
					// is moved around in the view the model index remains constant.
					Integer modelIndex = bfd.getIndex();
					
					// The programmatic name of this feature. For more,
					// see paragraph 8.3 of the Java Beans specification.
					String bpdName = bfd.getName();

					// Assign column default values
					String bindWith = bpdName;
					String headerValue = bfd.getDisplayName();
					String identifier = bindWith + "@" + getBindClass().getName();

					// Alignment: Use BeanInfo inferred values.
					Alignment alignment = Alignment.LEFT;
					if ( bfd.getAlignment() != null )
						alignment = bfd.getAlignment();
					else
						alignment = alignByType(type);
					TableCellRenderer headerRenderer =
						new AlignableTableCellHeaderRenderer(alignment.getConstant());
					TableCellRenderer cellRenderer =
						new AlignableTableCellRenderer(alignment.getConstant());
					
					// Editable: Use BeanInfo inferred values.
					boolean editable = true;
					if ( bfd.isEditable() != null )
						editable = bfd.isEditable();
					
					// Resizable: Use BeanInfo inferred values.
					boolean resizable = true;
					if ( bfd.isResizable() != null )
						resizable = bfd.isResizable();
					
					// Assign default values

					// Max/Min Width: Use BeanInfo inferred values.
					int maxWidth = bfd.getMaxWidth();
					int minWidth = bfd.getMinWidth();

					// Use validation inferred values.
					jakarta.validation.metadata.PropertyDescriptor vpd = vpdMap.get(bpdName);
					if ( vpd != null )
					{
						int minMaxLen = 0;
						for ( ConstraintDescriptor<?> vcd : vpd.getConstraintDescriptors() )
						{
							if ( vcd.getAnnotation() instanceof Size )
							{
								Size size = (Size) vcd.getAnnotation();
								minWidth = max(size.min(), 1);
								maxWidth = max(size.max(), 0);
							}
							else if ( vcd.getAnnotation() instanceof Min )
							{
								Min min = (Min) vcd.getAnnotation();
								Long minValue = min.value();
								if ( minValue >= 0 )
								{
									int minLen = minValue.toString().length();
									if ( minLen > minMaxLen )
										minMaxLen = minLen;
								}
							}
							else if ( vcd.getAnnotation() instanceof Max )
							{
								Max max = (Max) vcd.getAnnotation();
								Long maxValue = max.value();
								if ( maxValue >= 0 )
								{
									int maxLen = maxValue.toString().length();
									if ( maxLen > minMaxLen )
										minMaxLen = maxLen;
								}
							}
						}
						// Rough guess based on min and max length
						if ( minMaxLen > 0 )
							minWidth = minMaxLen+1;
					}

					// Use default values

					// Evaluate size (char) into Swing units (pixels).
					int minWidthField = fieldWidth(minWidth);
					int maxWidthField = fieldWidth(maxWidth);

					// Reuse or create table column and assign values.
					TableColumnBind tc1 = null;
					if ( tcMap.containsKey(bindWith) )
						tc1 = tcMap.get(bindWith);
					TableColumnBind tc2 = new TableColumnBind();
					{
						tc2.setModelIndex(modelIndex);
						tc2.setBindWith(bindWith);

						tc2.setAlignment(alignment.getConstant());
						tc2.setEditable(editable);
						tc2.setResizable(resizable);
						tc2.setType(type.getName());

						tc2.setWidth(minWidthField);
						tc2.setPreferredWidth(minWidthField);
						tc2.setMinWidth(minWidthField);
						tc2.setMaxWidth(maxWidthField);

						tc2.setIdentifier(identifier);

						tc2.setHeaderRenderer(headerRenderer);
						tc2.setHeaderValue(headerValue);

						tc2.setCellRenderer(cellRenderer);
						tc2.setCellEditor(null);

						//
						// Some table column values are reconfigured here.
						// These derived default values are overridden by
						// the SWIXML table column values, when present.
						//
						if ( tc1 != null )
						{
							if ( !isBlank(tc1.getType()) )
								tc2.setType(tc1.getType());
							tc2.setEditable(tc1.isEditable());
							tc2.setResizable(tc1.getResizable());

							if ( tc1.getMinWidth() > DEFAULT_TABLECOLUMN_MINWIDTH )
								tc2.setMinWidth(tc1.getMinWidth());
							if ( tc1.getMaxWidth() < DEFAULT_TABLECOLUMN_MAXWIDTH )
								tc2.setMaxWidth(tc1.getMaxWidth());

							// 'preferredWidth' and 'width' are constrained
							// by 'minWidth' and 'maxWidth'!
							if ( tc1.getPreferredWidth() > DEFAULT_TABLECOLUMN_PREFWIDTH )
								tc2.setPreferredWidth(tc1.getPreferredWidth());
							if ( tc1.getWidth() > DEFAULT_TABLECOLUMN_PREFWIDTH )
								tc2.setWidth(tc1.getWidth());

							if ( !isBlank((String) tc1.getHeaderValue()) )
								tc2.setIdentifier(tc1.getHeaderValue());
						}
					}

					// Appends the column to the end of the TableColumnModel array.
					cm.addColumn(tc2);
				}
			}
		}
		else
			super.createDefaultColumnsFromModel();
	}
	
	/**
	 * Create and add {@link AutoBinding} instance(s) to synchronize model
	 * properties with this {@link JTable}.
	 * 
	 * <p>Notifies this {@link Component} that it now has a parent component. It
	 * makes the {@link Container} displayable by connecting it to a native
	 * screen resource.</p>
	 */
	@Override
	public void addNotify()
	{
		if ( getBindList() == null )
		{
			if ( getBindWith() != null )
			{
				BeanProperty<Object, List<?>> beanProperty = BeanProperty.create(getBindWith());
				setBindList(beanProperty.getValue(getSwingEngine().getClient()));
			}
		}
		
		if ( getBindList() != null )
		{
			Binding<?, ?, ?, ?> itb = null;
			if ( getBindClass() != null )
			{
				// super.setAutoCreateColumnsFromModel(true);
				itb = initTableBindingFromBeanInfo(getBindingGroup(), READ_WRITE, this,
					getBindList(), getFieldDescriptors(), isAllPropertiesBound());
			}
			else
			{
				super.setAutoCreateColumnsFromModel(false);
				itb = initTableBindingFromTableColumns(getBindingGroup(), READ_WRITE, this, getBindList());
			}
			setBinding(itb);
			getBindingGroup().bind();
		}
		
		super.addNotify();
	}
	
	public JTableBind()
	{
		super();
		init();
	}

	public JTableBind(TableModel dm)
	{
		super(dm);
		init();
	}

	private void init()
	{
		// Row selection
		super.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent se)
			{
				onRowSelection(se);
			}
		});
		
		// Column selection
		super.getColumnModel().addColumnModelListener(new TableColumnModelListener()
		{

			@Override
			public void columnSelectionChanged(ListSelectionEvent se)
			{
				onColumnSelection(se);
			}
			
			@Override
			public void columnAdded(TableColumnModelEvent me)
			{
			}

			@Override
			public void columnRemoved(TableColumnModelEvent me)
			{
			}

			@Override
			public void columnMoved(TableColumnModelEvent me)
			{
			}

			@Override
			public void columnMarginChanged(ChangeEvent ce)
			{
			}
		});

		// https://github.com/bsorrentino/swixml2/issues/6
		super.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				if ( me.getClickCount() == 2 )
				{
					Action dcAction = getDblClickAction();
					if ( dcAction != null && dcAction.isEnabled() )
					{
						ActionEvent ev = new ActionEvent(me, 0, "dblClick");
						dcAction.actionPerformed(ev);
					}
				}
			}
		});
		
//		super.getModel().addTableModelListener(new TableModelListener()
//		{
//			public void tableChanged(TableModelEvent e)
//			{
//				throw new UnsupportedOperationException("Not supported yet.");
//			}
//		});
	}

	private void onRowSelection(ListSelectionEvent se)
	{
		if ( !se.getValueIsAdjusting() )
		{
			ListSelectionModel sm = (ListSelectionModel) se.getSource();
			if ( !sm.isSelectionEmpty() )
			{
				int minIndex = sm.getMinSelectionIndex();
				int maxIndex = sm.getMaxSelectionIndex();
				List<Object> rows = new ArrayList<>();
				for ( int index=minIndex; index <= maxIndex; ++index)
				{
					if ( sm.isSelectedIndex(index) )
						rows.add(getBindList().get(index));
				}
				if ( !rows.isEmpty() )
				{
					ActionEvent ae = new ActionEvent(rows, 0, "rowSelection");
					getAction().actionPerformed(ae);
				}	
			}
		}
	}

	private void onColumnSelection(ListSelectionEvent se)
	{
		if ( !se.getValueIsAdjusting() )
		{
			ListSelectionModel sm = (ListSelectionModel) se.getSource();
			if ( !sm.isSelectionEmpty() )
			{
				int minIndex = sm.getMinSelectionIndex();
				int maxIndex = sm.getMaxSelectionIndex();
				List<TableColumn> cols = new ArrayList<>();
				for ( int index=minIndex; index <= maxIndex; ++index)
				{
					if ( sm.isSelectedIndex(index) )
						cols.add(getColumnModel().getColumn(index));
				}
				if ( !cols.isEmpty() )
				{
					ActionEvent ae = new ActionEvent(cols, 0, "colSelection");
					getAction().actionPerformed(ae);
				}	
			}

		}
	}
}
