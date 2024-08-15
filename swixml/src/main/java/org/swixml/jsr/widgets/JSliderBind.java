package org.swixml.jsr.widgets;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.beansbinding.Converter;
import org.swixml.jsr295.BindingUtils;

/**
 * @see <a href="file:../../package-info.java">LICENSE: package-info</a>
 * 
 * @author softphone
 */
public class JSliderBind
	extends JSlider
	implements BindableBasicWidget
{
	private static final long serialVersionUID = 20240701L;
	private Action action;
	private ChangeListener listener = new ChangeListener()
	{
		@Override
		public void stateChanged(ChangeEvent e)
		{
			if ( action != null )
				action.actionPerformed(new ActionEvent(e, 0, "changed"));
		}
	};

	public final Action getAction()
	{
		return action;
	}

	public final void setAction(Action action)
	{
		this.action = action;
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
		final String bindWith = getBindWith();
		if ( null != bindWith && !bindWith.isEmpty() )
		{
			BindingUtils.parseBind(this, "value", bindWith, getConverter());
		}
		super.addChangeListener(listener);
		super.addNotify();
	}

	@Override
	public void removeNotify()
	{
		super.removeNotify();
		super.removeChangeListener(listener);
	}
}
