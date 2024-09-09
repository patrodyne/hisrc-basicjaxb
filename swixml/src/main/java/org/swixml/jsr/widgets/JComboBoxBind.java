package org.swixml.jsr.widgets;

import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE;
import static org.jdesktop.beansbinding.BeanProperty.create;
import static org.jdesktop.beansbinding.Bindings.createAutoBinding;
import static org.swixml.SwingEngine.ENGINE_PROPERTY;
import static org.swixml.jsr295.BindingUtils.initComboBinding;
import static org.swixml.jsr295.BindingUtils.isBound;

import java.awt.Component;
import java.awt.Container;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Converter;
import org.jdesktop.beansbinding.Property;
import org.swixml.SwingEngine;

/**
 * Extend {@link JComboBox} to support <em>Bean Bindings (JSR 295)</em>.
 * 
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @param <E> The type of the elements of this combo box.
 * @param <SS> The source type to bind to.
 * @param <TS> The target type to bind to.
 */
public class JComboBoxBind<E, SS, TS>
	extends JComboBox<E>
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
	@SuppressWarnings("unchecked")
	public Converter<List<E>, List<?>> getConverter()
	{
		return (Converter<List<E>, List<?>>) getClientProperty(CONVERTER_PROPERTY);
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
	
	public JComboBoxBind()
	{
		super();
	}

	public JComboBoxBind(ComboBoxModel<E> model)
	{
		super(model);
	}

	public JComboBoxBind(E[] items)
	{
		super(items);
	}

	public JComboBoxBind(Vector<E> items)
	{
		super(items);
	}
	
    /**
     * Create and add {@link AutoBinding} instance(s) to synchronize model
     * properties with this {@link JComboBox}.
     * 
     * <p>Notifies this {@link Component} that it now has a parent component. It
     * makes the {@link Container} displayable by connecting it to a native
     * screen resource.</p>
     */
	@Override
	public void addNotify()
	{
		if ( getBindList() != null && !isBound(this) )
		{
			initComboBinding(getBindingGroup(), READ_WRITE, this, getBindList(), getConverter());
			if ( getBindWith() != null )
			{
				SwingEngine<?> engine = (SwingEngine<?>) getClientProperty(ENGINE_PROPERTY);
				Container client = engine.getClient();

				AutoBinding<SS, List<E>, TS, List<?>> bindToSelected = createSelectedBinding(client);
				getBindingGroup().addBinding(bindToSelected);
				setBinding(bindToSelected);
				
				if ( isEditable() )
				{
					Binding<SS, List<E>, TS, List<?>> bindToText = createTextBinding(client);
					getBindingGroup().addBinding(bindToText);
				}
			}
			getBindingGroup().bind();
		}
		
		super.addNotify();
	}

	private AutoBinding<SS, List<E>, TS, List<?>> createSelectedBinding(Object client)
	{
		UpdateStrategy strategy = READ_WRITE;
		@SuppressWarnings("unchecked")
		SS sourceObject = (SS) client;
		Property<SS, List<E>> sourceProperty = create(getBindWith());
		@SuppressWarnings("unchecked")
		TS targetObject = (TS) this;
		Property<TS, List<?>> targetProperty = create("selectedItem");
		
		//
		// Fix Issue 72
		//
		AutoBinding<SS, List<E>, TS, List<?>> autoBinding = createAutoBinding
		(
			strategy,
			sourceObject,
			sourceProperty,
			targetObject,
			targetProperty
		);

		if ( getConverter() != null )
		{
			autoBinding.setConverter(getConverter());
		}
		return autoBinding;
	}

	private Binding<SS, List<E>, TS, List<?>> createTextBinding(Object client)
	{
		UpdateStrategy strategy = READ_WRITE;
		@SuppressWarnings("unchecked")
		SS sourceObject = (SS) client;
		Property<SS, List<E>> sourceProperty = create(getBindWith());
		@SuppressWarnings("unchecked")
		TS targetObject = (TS) getEditor().getEditorComponent();
		Property<TS, List<?>> targetProperty = create("text");
		
		Binding<SS, List<E>, TS, List<?>> bindToText =
			createAutoBinding
			(
				strategy,
				sourceObject,
				sourceProperty,
				targetObject,
				targetProperty
			);
		
		if ( getConverter() != null )
			bindToText.setConverter(getConverter());
		return bindToText;
	}
}
