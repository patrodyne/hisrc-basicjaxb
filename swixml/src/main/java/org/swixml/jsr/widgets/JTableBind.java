package org.swixml.jsr.widgets;

import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE;
import static org.swixml.SwingEngine.ENGINE_PROPERTY;
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

	@Override
	public void addNotify()
	{
		if ( getBindList() == null )
		{
			if ( getBindWith() != null )
			{
				SwingEngine<?> engine = (SwingEngine<?>) getClientProperty(ENGINE_PROPERTY);
				BeanProperty<Object, List<?>> beanProperty = BeanProperty.create(getBindWith());
				setBindList(beanProperty.getValue(engine.getClient()));
			}
		}
		
		if ( getBindList() != null )
		{
			if ( getBindClass() != null )
			{
				initTableBindingFromBeanInfo(null, READ_WRITE, this,
					getBindList(), getBindClass(), isAllPropertiesBound());
			}
			else
			{
				super.setAutoCreateColumnsFromModel(false);
				initTableBindingFromTableColumns(null, READ_WRITE, this, getBindList());
			}
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
