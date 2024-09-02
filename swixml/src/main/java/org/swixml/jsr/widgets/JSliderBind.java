package org.swixml.jsr.widgets;

import static org.swixml.jsr295.BindingUtils.parseBind;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.beansbinding.Converter;

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
	public void addNotify()
	{
		final String bindWith = getBindWith();
		if ( (null != bindWith) && !bindWith.isEmpty() )
			parseBind(this, "value", bindWith, getConverter());
		
		super.addChangeListener(listener);
		super.addNotify();
	}
	
	private ChangeListener listener = new ChangeListener()
	{
		@Override
		public void stateChanged(ChangeEvent e)
		{
			if ( getAction() != null )
				getAction().actionPerformed(new ActionEvent(e, 0, "changed"));
		}
	};

	@Override
	public void removeNotify()
	{
		super.removeNotify();
		super.removeChangeListener(listener);
	}
}
