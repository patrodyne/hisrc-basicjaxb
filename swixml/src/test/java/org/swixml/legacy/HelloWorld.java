package org.swixml.legacy;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.swixml.SwingEngine;

public class HelloWorld extends JFrame
{
	private static final long serialVersionUID = 20240701L;

	/** submit counter */
	private int clicks;
	
	/**
	 * JTextField member gets instantiated through Swixml
	 * (look for id="tf" in xml descriptor)
	 */
	public JTextField tf;
	
	/** Jlabel to display number of button clicks */
	public JLabel cnt;
	
	/** Action appends a '#' to the textfields content. */
	public Action submit = new AbstractAction()
	{
		private static final long serialVersionUID = 20240701L;

		@Override
		public void actionPerformed(ActionEvent e)
		{
			tf.setText(tf.getText() + '#');
			cnt.setText(String.valueOf(++clicks));
		}
	};

	/** Renders UI at construction */
	private HelloWorld()
		throws Exception
	{
		new SwingEngine<JFrame>(this)
			.render("org/swixml/legacy/helloworld.xml");
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/** Makes the class bootable */
	public static void main(String[] args)
		throws Exception
	{
		new HelloWorld();
	}
}
