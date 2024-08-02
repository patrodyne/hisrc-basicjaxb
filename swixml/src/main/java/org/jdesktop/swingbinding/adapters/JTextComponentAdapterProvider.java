package org.jdesktop.swingbinding.adapters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;

import org.jdesktop.beansbinding.ext.BeanAdapterProvider;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @author Shannon Hickey
 */
public final class JTextComponentAdapterProvider implements BeanAdapterProvider
{
	private static final String PROPERTY_BASE = "text";
	private static final String ON_ACTION_OR_FOCUS_LOST = PROPERTY_BASE + "_ON_ACTION_OR_FOCUS_LOST";
	private static final String ON_FOCUS_LOST = PROPERTY_BASE + "_ON_FOCUS_LOST";

	public final class Adapter extends BeanAdapterBase
	{
		private JTextComponent component;
		private Document document;
		private boolean installedFilter;
		private String cachedText;
		private Handler handler;
		@SuppressWarnings("unused")
		private boolean inDocumentListener;

		private Adapter(JTextComponent component, String property)
		{
			super(property);
			this.component = component;
		}

		public String getText()
		{
			return component.getText();
		}

		public String getText_ON_ACTION_OR_FOCUS_LOST()
		{
			return getText();
		}

		public String getText_ON_FOCUS_LOST()
		{
			return getText();
		}

		public void setText(String text)
		{
			component.setText(text);
			component.setCaretPosition(0);
			cachedText = text;
		}

		public void setText_ON_ACTION_OR_FOCUS_LOST(String text)
		{
			setText(text);
		}

		public void setText_ON_FOCUS_LOST(String text)
		{
			setText(text);
		}

		@Override
		protected void listeningStarted()
		{
			cachedText = component.getText();
			handler = new Handler();
			component.addPropertyChangeListener("document", handler);
			if ( property != PROPERTY_BASE )
			{
				component.addFocusListener(handler);
			}
			if ( property == ON_ACTION_OR_FOCUS_LOST && component instanceof JTextField )
			{
				((JTextField) component).addActionListener(handler);
			}
			document = component.getDocument();
			installDocumentListener();
		}

		@Override
		protected void listeningStopped()
		{
			cachedText = null;
			component.removePropertyChangeListener("document", handler);
			if ( property != PROPERTY_BASE )
			{
				component.removeFocusListener(handler);
			}
			if ( property == ON_ACTION_OR_FOCUS_LOST && (component instanceof JTextField) )
			{
				((JTextField) component).removeActionListener(handler);
			}
			uninstallDocumentListener();
			document = null;
			handler = null;
		}

		private void installDocumentListener()
		{
			if ( property != PROPERTY_BASE )
			{
				return;
			}
			boolean useDocumentFilter = !(component instanceof JFormattedTextField);
			if ( useDocumentFilter	&& (document instanceof AbstractDocument)
					&& ((AbstractDocument) document).getDocumentFilter() == null )
			{
				((AbstractDocument) document).setDocumentFilter(handler);
				installedFilter = true;
			}
			else
			{
				document.addDocumentListener(handler);
				installedFilter = false;
			}
		}

		private void uninstallDocumentListener()
		{
			if ( property != PROPERTY_BASE )
			{
				return;
			}
			if ( installedFilter )
			{
				AbstractDocument ad = (AbstractDocument) document;
				if ( ad.getDocumentFilter() == handler )
				{
					ad.setDocumentFilter(null);
				}
			}
			else
			{
				document.removeDocumentListener(handler);
			}
		}

		private class Handler
			extends DocumentFilter
			implements ActionListener, DocumentListener, FocusListener, PropertyChangeListener
		{
			private void updateText()
			{
				Object oldText = cachedText;
				cachedText = getText();
				firePropertyChange(oldText, cachedText);
			}

			private void documentTextChanged()
			{
				try
				{
					inDocumentListener = true;
					textChanged();
				}
				finally
				{
					inDocumentListener = false;
				}
			}

			private void textChanged()
			{
				updateText();
			}

			@Override
			public void propertyChange(PropertyChangeEvent pce)
			{
				uninstallDocumentListener();
				document = component.getDocument();
				installDocumentListener();
				updateText();
			}

			@Override
			public void actionPerformed(ActionEvent e)
			{
				updateText();
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				if ( !e.isTemporary() )
				{
					updateText();
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e)
			{
				documentTextChanged();
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				documentTextChanged();
			}

			@Override
			public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException
			{
				super.replace(fb, offset, length, text, attrs);
				textChanged();
			}

			@Override
			public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException
			{
				super.insertString(fb, offset, string, attr);
				textChanged();
			}

			@Override
			public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
				throws BadLocationException
			{
				super.remove(fb, offset, length);
				textChanged();
			}

			@Override
			public void focusGained(FocusEvent e)
			{
			}

			@Override
			public void changedUpdate(DocumentEvent e)
			{
			}
		}
	}

	@Override
	public boolean providesAdapter(Class<?> type, String property)
	{
		if ( !JTextComponent.class.isAssignableFrom(type) )
		{
			return false;
		}
		property = property.intern();
		return property == PROPERTY_BASE || property == ON_ACTION_OR_FOCUS_LOST || property == ON_FOCUS_LOST;
	}

	@Override
	public Object createAdapter(Object source, String property)
	{
		if ( !providesAdapter(source.getClass(), property) )
		{
			throw new IllegalArgumentException();
		}
		return new Adapter((JTextComponent) source, property);
	}

	@Override
	public Class<?> getAdapterClass(Class<?> type)
	{
		return JTextComponent.class.isAssignableFrom(type) ? JTextComponentAdapterProvider.Adapter.class : null;
	}
}
