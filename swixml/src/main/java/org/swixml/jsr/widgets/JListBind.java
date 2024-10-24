package org.swixml.jsr.widgets;

import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE;
import static org.swixml.SwingEngine.ENGINE_PROPERTY;
import static org.swixml.jsr295.BindingUtils.initListBinding;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.Action;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Converter;
import org.swixml.SwingEngine;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @param <E>
 */
public class JListBind<E>
	extends JList<E>
	implements BindableListWidget
{
	private static final long serialVersionUID = 20240701L;

	private List<?> bindList;
	@Override
	public final List<?> getBindList()
	{
		return bindList;
	}
	@Override
	public final void setBindList(List<?> bindList)
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
		return (Converter<?, ?>) getClientProperty(CONVERTER_PROPERTY);
	}
	@Override
	public void setConverter(Converter<?, ?> converter)
	{
		putClientProperty(CONVERTER_PROPERTY, converter);
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
	
	private javax.swing.Action action;
	public javax.swing.Action getAction()
	{
		return action;
	}
	public void setAction(javax.swing.Action action)
	{
		this.action = action;
	}

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
	 * Create and add {@link AutoBinding} instance(s) to synchronize model
	 * properties with this {@link JList}.
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
				SwingEngine<?> engine = (SwingEngine<?>) getClientProperty(ENGINE_PROPERTY);
				BeanProperty<Object, List<?>> beanProperty = BeanProperty.create(getBindWith());
				setBindList(beanProperty.getValue(engine.getClient()));
			}
		}
		
		if ( getBindList() != null )
		{
			setBinding(initListBinding(getBindingGroup(), READ_WRITE, this, getBindList(), getConverter()));
			getBindingGroup().bind();
		}
		
		super.addNotify();
	}
}
