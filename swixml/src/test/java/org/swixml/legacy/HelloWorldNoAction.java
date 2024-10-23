package org.swixml.legacy;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.swixml.SwingEngine;

public class HelloWorldNoAction extends JFrame
{
	private static final long serialVersionUID = 20240701L;

	/**
	 * submit counter
	 */
	private int clicks;
	
	/**
	 * JTextField member gets instantiated through Swixml (look for id="tf" in
	 * xml descriptor)
	 */
	public JTextField tf;
	
	/**
	 * JLabel to display number of button clicks
	 */
	public JLabel cnt;

	/**
	 * bound, using an element's action attribute, which was set to submit.
	 */
	public void submit()
	{
		tf.setText(tf.getText() + '#');
		cnt.setText(String.valueOf(++clicks));
	}

	/**
	 * Renders UI at construction
	 */
	private HelloWorldNoAction()
		throws Exception
	{
		SwingEngine<JFrame> swix = new SwingEngine<>(this);
		swix.getELProcessor().defineBean("el", swix.getELMethods());
		swix.getELProcessor().defineBean("window", this);
		swix.render("org/swixml/legacy/helloworld.xml");
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Makes the class bootable
	 */
	public static void main(String[] args)
		throws Exception
	{
		new HelloWorldNoAction();
	}
}
