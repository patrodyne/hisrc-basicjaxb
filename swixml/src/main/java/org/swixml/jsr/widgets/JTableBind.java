package org.swixml.jsr.widgets;

import static org.swixml.jsr295.BindingUtils.initTableBindingFromBeanInfo;
import static org.swixml.jsr295.BindingUtils.initTableBindingFromTableColumns;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyDescriptor;
import java.util.List;

import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.apache.commons.beanutils.PropertyUtils;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Converter;
import org.swixml.SwingEngine;
import org.swixml.jsr295.BindingUtils;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * @author sorrentino
 */
public class JTableBind
	extends JTable
	implements BindableListWidget, BindableBasicWidget
{
	private static final long serialVersionUID = 20240701L;
	
	private Action action;
	private Action dblClickAction = null;
	private Class<?> beanClass;
	private List<?> beanList;
	private boolean allPropertiesBound = true;

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

	@Override
	public TableCellRenderer getCellRenderer(int row, int col)
	{
		if ( beanClass != null && beanList != null )
		{
			PropertyDescriptor[] pp = PropertyUtils.getPropertyDescriptors(beanClass);
			PropertyDescriptor p = pp[col];
			Object r = p.getValue(BindingUtils.TABLE_COLUMN_RENDERER);
			if ( r instanceof TableCellRenderer )
				return (TableCellRenderer) r;
		}
		return super.getCellRenderer(row, col);
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

	public final boolean isAllPropertiesBound()
	{
		return allPropertiesBound;
	}

	public final void setAllPropertiesBound(boolean allPropertyBound)
	{
		this.allPropertiesBound = allPropertyBound;
	}

	@Deprecated
	public Class<?> getBindClass()
	{
		return beanClass;
	}

	@Deprecated
	public void setBindClass(Class<?> beanClass)
	{
		this.beanClass = beanClass;
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
	@Deprecated
	public List<?> getBindList()
	{
		return beanList;
	}

	@Override
	@Deprecated
	public void setBindList(List<?> beanList)
	{
		this.beanList = beanList;
	}

	@Override
	public void setConverter(Converter<?, ?> converter)
	{
	}

	@Override
	public Converter<?, ?> getConverter()
	{
		return null;
	}

	public Action getAction()
	{
		return action;
	}

	public void setAction(Action action)
	{
		this.action = action;
	}

	public final Action getDblClickAction()
	{
		return dblClickAction;
	}

	public final void setDblClickAction(Action dblClickAction)
	{
		this.dblClickAction = dblClickAction;
	}

	@Override
	public void addNotify()
	{
		if ( beanList == null )
		{
			if ( getBindWith() != null )
			{
				Object client = getClientProperty(SwingEngine.CLIENT_PROPERTY);
				BeanProperty<Object, List<?>> p = BeanProperty.create(getBindWith());
				beanList = p.getValue(client);
			}
		}
		if ( beanList != null )
		{
			if ( beanClass != null )
			{
				initTableBindingFromBeanInfo(null, UpdateStrategy.READ_WRITE, this,
					beanList, getBindClass(), isAllPropertiesBound());
			}
			else
			{
				super.setAutoCreateColumnsFromModel(false);
				initTableBindingFromTableColumns(null, UpdateStrategy.READ_WRITE, this, beanList);
			}
		}
		super.addNotify();
	}
}
