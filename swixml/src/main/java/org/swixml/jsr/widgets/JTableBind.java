package org.swixml.jsr.widgets;

import static java.lang.Math.max;
import static org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptors;
import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE;
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
import java.beans.PropertyDescriptor;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import org.apache.commons.beanutils.PropertyUtils;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Converter;
import org.swixml.SwingEngine;
import org.swixml.el.ELMethods;
import org.swixml.jsr295.BindingUtils;

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
			PropertyDescriptor[] pp = PropertyUtils.getPropertyDescriptors(getBindClass());
			PropertyDescriptor p = pp[col];
			Object r = p.getValue(BindingUtils.TABLE_COLUMN_RENDERER);
			if ( r instanceof TableCellRenderer )
				return (TableCellRenderer) r;
		}
		return super.getCellRenderer(row, col);
	}
	
	private SwingEngine<?> getSwingEngine()
	{
		return (SwingEngine<?>) getClientProperty(ENGINE_PROPERTY);
	}
	
	private int fieldWidth(int repeat)
	{
		ELMethods<?> elm = getSwingEngine().getELMethods();
		int sw = elm.getFontMetrics(getFont()).stringWidth(AVERAGE_LETTER);
		return repeat * sw;
	}
	
	@Override
	public void createDefaultColumnsFromModel()
	{
		// If there a bind class then derive column properties
		// from it; otherwise, fall back to the super method.
		TableModel tm = getModel();
		if ( (tm != null) && (getBindClass() != null) )
		{
			// Cache any current columns
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
			// Remove any current columns
			while (cm.getColumnCount() > 0)
				cm.removeColumn(cm.getColumn(0));
			
			// Create a map of the validation property descriptors by property name.
			BeanDescriptor vbd = getSwingEngine().getBeanValidator()
				.getConstraintsForClass(getBindClass());
			Set<jakarta.validation.metadata.PropertyDescriptor> vcpSet = vbd.getConstrainedProperties();
			Map<String, jakarta.validation.metadata.PropertyDescriptor> vpdMap = new HashMap<>();
			for ( jakarta.validation.metadata.PropertyDescriptor vcp : vcpSet )
				vpdMap.put(vcp.getPropertyName(), vcp);

			// Create new columns from properties of the bind class.
			PropertyDescriptor[] bpds = getPropertyDescriptors(getBindClass());
			for ( int index=0; index < bpds.length; ++index )
			{
				// Get the Bean and the Validation property descriptors.
				PropertyDescriptor bpd = bpds[index];
				String bpdName = bpd.getName();
				jakarta.validation.metadata.PropertyDescriptor vpd = vpdMap.get(bpdName);
				
				// Assign column default values
				String bindWith = bpd.getName();
				Class<?> type = bpd.getReadMethod().getReturnType();
		        String headerValue = bpd.getDisplayName();
		        String identifier = bindWith + "@" + getBindClass().getName();

		        // Assign default values then validation inferred values.
				int maxWidth = 20;
				int minWidth = 1;
				for ( ConstraintDescriptor<?> vcd : vpd.getConstraintDescriptors() )
				{
					if ( vcd.getAnnotation() instanceof Size )
					{
						Size size = (Size) vcd.getAnnotation();
						minWidth = max(size.min(), 1);
						maxWidth = max(size.max(), 0);
					}
				}
				
				// Evaluate size (char) into Swing units (pixels).
				int minWidthField = fieldWidth(minWidth);
				int maxWidthField = fieldWidth(maxWidth);

				// Reuse or create table column and assign values.
				TableColumnBind tc1 = null;
				if ( tcMap.containsKey(bindWith) )
					tc1 = tcMap.get(bindWith);
				TableColumnBind tc2 = new TableColumnBind();
				{
					tc2.setModelIndex(index);
					tc2.setBindWith(bindWith);
					
					tc2.setType(type.getName());
					tc2.setEditable(true);
					tc2.setResizable(true);

					tc2.setWidth(maxWidthField);
					tc2.setPreferredWidth(maxWidthField);
					tc2.setMinWidth(minWidthField);
					tc2.setMaxWidth(maxWidthField);

					tc2.setIdentifier(identifier);

					tc2.setHeaderRenderer(null);
					tc2.setHeaderValue(headerValue);

					tc2.setCellRenderer(null);
					tc2.setCellEditor(null);

					// Some table column values are reconfigurable here.
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

				// Add the new table column to the model.
				cm.addColumn(tc2);
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
					getBindList(), getBindClass(), isAllPropertiesBound());
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
		super.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				onRowOrColSelection(e);
			}
		});
		// ISSUE-6
		super.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if ( e.getClickCount() == 2 )
				{
					Action a = getDblClickAction();
					if ( a != null && a.isEnabled() )
					{
						ActionEvent ev = new ActionEvent(e, 0, null);
						a.actionPerformed(ev);
					}
				}
			}
		});
		super.getColumnModel().addColumnModelListener(new TableColumnModelListener()
		{
			@Override
			public void columnAdded(TableColumnModelEvent e)
			{
			}

			@Override
			public void columnRemoved(TableColumnModelEvent e)
			{
			}

			@Override
			public void columnMoved(TableColumnModelEvent e)
			{
			}

			@Override
			public void columnMarginChanged(ChangeEvent e)
			{
			}

			@Override
			public void columnSelectionChanged(ListSelectionEvent e)
			{
				onRowOrColSelection(e);
			}
		});
		
		/*
		 * super.getModel().addTableModelListener( new TableModelListener() {
		 * 
		 * public void tableChanged(TableModelEvent e) { throw new
		 * UnsupportedOperationException("Not supported yet."); } });
		 */
	}

	private void onRowOrColSelection(ListSelectionEvent e)
	{
		// ISSUE-5
		if ( e.getValueIsAdjusting() )
			return;
		if ( getSelectedRow() == -1 )
			return;
		Action a = getAction();
		if ( null == a )
			return;
		ActionEvent ev = new ActionEvent(e, 0, null);
		a.actionPerformed(ev);
	}
}
