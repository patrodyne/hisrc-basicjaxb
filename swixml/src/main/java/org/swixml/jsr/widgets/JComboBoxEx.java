package org.swixml.jsr.widgets;

import static org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE;
import static org.jdesktop.beansbinding.BeanProperty.create;
import static org.jdesktop.beansbinding.Bindings.createAutoBinding;
import static org.swixml.SwingEngine.CLIENT_PROPERTY;
import static org.swixml.jsr295.BindingUtils.initComboBinding;
import static org.swixml.jsr295.BindingUtils.isBound;

import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Converter;
import org.jdesktop.beansbinding.Property;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @param <E>
 * @param <SS>
 * @param <TS>
 */
public class JComboBoxEx<E, SS, TS>
	extends JComboBox<E>
	implements BindableListWidget, BindableBasicWidget
{
	private static final long serialVersionUID = 20240701L;
	
	private List<?> beanList;

	public JComboBoxEx()
	{
		super();
	}

	public JComboBoxEx(ComboBoxModel<E> model)
	{
		super(model);
	}

	public JComboBoxEx(E[] items)
	{
		super(items);
	}

	public JComboBoxEx(Vector<E> items)
	{
		super(items);
	}

	@Override
	public final List<?> getBindList()
	{
		return beanList;
	}

	@Override
	public final void setBindList(List<?> beanList)
	{
		this.beanList = beanList;
	}

	@Override
	public void setConverter(Converter<?, ?> converter)
	{
		putClientProperty(CONVERTER_PROPERTY, converter);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Converter<List<E>, List<?>> getConverter()
	{
		return (Converter<List<E>, List<?>>) getClientProperty(CONVERTER_PROPERTY);
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
	public void addNotify()
	{
		if ( getBindList() != null && !isBound(this) )
		{
			BindingGroup context = new BindingGroup();
			initComboBinding(context, READ_WRITE, this, getBindList(), getConverter());
			if ( getBindWith() != null )
			{
				Object client = getClientProperty(CLIENT_PROPERTY);

				AutoBinding<SS, List<E>, TS, List<?>> binding = createBinding(client);
				context.addBinding(binding);
				
				if ( isEditable() )
				{
					Binding<SS, List<E>, TS, List<?>> bindToText = createTextBinding(client);
					context.addBinding(bindToText);
				}
			}
			context.bind();
		}
		super.addNotify();
	}

	private AutoBinding<SS, List<E>, TS, List<?>> createBinding(Object client)
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
