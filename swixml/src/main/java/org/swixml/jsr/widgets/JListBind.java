package org.swixml.jsr.widgets;

import static org.swixml.jsr295.BindingUtils.initListBinding;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.Action;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Converter;
import org.swixml.SwingEngine;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @param <E>
 */
public class JListBind<E>
	extends JList<E>
	implements BindableListWidget, BindableBasicWidget
{
	private static final long serialVersionUID = 20240701L;
	private List<?> beanList;
	private javax.swing.Action action;

	public JListBind()
	{
		super.addListSelectionListener(new ListSelectionListener()
		{
			@Override
			public void valueChanged(ListSelectionEvent e)
			{
				if ( e.getValueIsAdjusting() )
					return;
				
				if ( getSelectedIndex() == -1 )
					return;
				
				Action a = getAction();
				if ( null == a )
					return;
				
				ActionEvent ev = new ActionEvent(e, 0, null);
				a.actionPerformed(ev);
			}
		});
	}

	/**
	 * 
	 * @return
	 */
	public javax.swing.Action getAction()
	{
		return action;
	}

	/**
	 * 
	 * @param action
	 */
	public void setAction(javax.swing.Action action)
	{
		this.action = action;
	}

	@Override
	@Deprecated
	public final List<?> getBindList()
	{
		return beanList;
	}

	@Override
	@Deprecated
	public final void setBindList(List<?> beanList)
	{
		this.beanList = beanList;
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
	public void setConverter(Converter<?, ?> converter)
	{
		putClientProperty(CONVERTER_PROPERTY, converter);
	}

	@Override
	public Converter<?, ?> getConverter()
	{
		return (Converter<?, ?>) getClientProperty(CONVERTER_PROPERTY);
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
			initListBinding(null, UpdateStrategy.READ_WRITE, this, beanList, getConverter());
		}
		
		super.addNotify();
	}
}
