package org.swixml.examples.combo;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static javax.swing.JOptionPane.showMessageDialog;
import static org.jdesktop.observablecollections.ObservableCollections.observableList;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ComboBoxEditor;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import org.jdesktop.application.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComboDialog extends JDialog
{
	private static final long serialVersionUID = 20240701L;
	private static final Logger logger = LoggerFactory.getLogger(ComboDialog.class);
	
	private static final String VALID_PROPERTY = "validInput";
	private static final String ENTER_ACTION = "ENTER";
	private static final String ESCAPE_ACTION = "ESCAPE";
	
	public JComboBox<?> cmbResult; // Automatically bound
	public JComboBox<?> cmbTemplate; // Automatically bound
	protected JComboBox<?> cmbNumber; // Automatically bound
	
	public JButton btnOK; // Automatically bound

	// Property: template
	private String template;
	public final String getTemplate()
	{
		return template;
	}
	public final void setTemplate(String template)
	{
		this.template = template;
		firePropertyChange("template", null, null);
	}

	// Property: resultList
	private final List<String> resultList;
	public final List<String> getResultList()
	{
		return resultList;
	}

	// Property: templateList
	private final List<String> templateList;
	public final List<String> getTemplateList()
	{
		return templateList;
	}

	// Property: removeOnSubmit
	private boolean removeOnSubmit = false;
	public final boolean isRemoveOnSubmit()
	{
		return removeOnSubmit;
	}
	public final void setRemoveOnSubmit(boolean value)
	{
		this.removeOnSubmit = value;
	}

	// Property: number
	private String number;
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number = number;
		firePropertyChange("number", null, null);
	}
	
	// Constructor
	public ComboDialog()
	{
		templateList = asList("<select item>", "Item1", "Item2", "Item3");
		resultList = observableList(new ArrayList<String>(asList("Item1", "Item2", "Item3")));
		setNumber("Item2");
		setTemplate("Item3");
	}

	@Override
	public void addNotify()
	{
		ComboBoxEditor cmbNumberEditor = cmbNumber.getEditor();
		Object cmbNumberEditorComponent = cmbNumberEditor.getEditorComponent();
		if ( cmbNumberEditorComponent instanceof JTextField )
		{
			logger.info("TextField");
			final JTextComponent cmbNumberEditorTextComponent =
				(JTextComponent) cmbNumberEditorComponent;
			
			cmbNumberEditorTextComponent
			.getDocument()
			.addDocumentListener(new DocumentListener()
			{
				@Override
				public void insertUpdate(DocumentEvent de)
				{
//					SwingUtilities.invokeLater(new Runnable()
//					{
//						public void run()
//						{
//							String text = tc.getText();
//							tc.setText(text.replace('\n', '\b').replace('\r', '\b'));
//						}
//					});
					logEvent("insertUpdate", de);
				}

				@Override
				public void removeUpdate(DocumentEvent de)
				{
					logEvent("removeUpdate", de);
				}

				@Override
				public void changedUpdate(DocumentEvent de)
				{
					logEvent("changedUpdate", de);
				}
				
				private void logEvent(String label, DocumentEvent de)
				{
					Document doc = de.getDocument();
					try
					{
						String text = doc.getText(0, doc.getLength());
						logger.info("{}: {}", label, text);
					}
					catch (BadLocationException ex)
					{
						logger.info("{}: {}", label, doc);
					}
				}
			});
		}
		super.addNotify();
	}

	@Action
	public void selectTemplate()
	{
		firePropertyChange(VALID_PROPERTY, null, null);
	}

	@Action
	public void selectResult()
	{
		firePropertyChange(VALID_PROPERTY, null, null);
		setNumber("Item2");
	}

	public boolean isValidInput()
	{
		return cmbTemplate.getSelectedIndex() > 0;
	}

	@Action(enabledProperty = VALID_PROPERTY)
	public void submit()
	{
		String msg = format("selectedItem=[%s]\ntext=[%s]\nnumber=[%s]",
			cmbNumber.getSelectedItem(), cmbNumber.getEditor().getItem(), getNumber());
		
		showMessageDialog(this, msg);
		
		String selectItem = (String) cmbTemplate.getSelectedItem();
		if ( isRemoveOnSubmit() )
			resultList.remove(selectItem);
		
		// cmbTemplate.setSelectedIndex(0);
		firePropertyChange(VALID_PROPERTY, null, null);
		logger.info("submit");
	}

	public void cancel()
	{
		logger.info("cancel");
	}

	@Override
	protected JRootPane createRootPane()
	{
		JRootPane rootPane = new JRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke(ESCAPE_ACTION);
		KeyStroke strokeOk = KeyStroke.getKeyStroke(ENTER_ACTION);
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, ESCAPE_ACTION);
		inputMap.put(strokeOk, ENTER_ACTION);
		
		rootPane.getActionMap().put(ESCAPE_ACTION, new AbstractAction()
		{
			private static final long serialVersionUID = 20240701L;
			@Override
			public void actionPerformed(ActionEvent actionEvent)
			{
				cancel();
			}
		});
		
		rootPane.getActionMap().put(ENTER_ACTION, new AbstractAction()
		{
			private static final long serialVersionUID = 20240701L;
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if ( isValid() )
				{
					submit();
				}
			}
		});
		
		return rootPane;
	}
}
