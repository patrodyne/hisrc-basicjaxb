package org.swixml.examples;

import javax.swing.JFrame;

import org.swixml.jsr296.SwingApplication;


public class BindingExamplesApplication extends SwingApplication<BindingExamplesFrame> {

	public static void main(String args []) {
		SwingApplication.launch(BindingExamplesApplication.class, args);
	}
	
	
	@Override
	protected void startup() {

		try {
/*			
			JDialog dialog = render( new LoginDialog(), "org/swixml/examples/SimpleDialog.xml"); 
			
			show( dialog );
*/
			JFrame frame = render( new BindingExamplesFrame(), "org/swixml/examples/BindingExamples.xml" );

			show( frame );
			
		} catch (Exception e) {

			e.printStackTrace();
			exit();
		}
		
	}

}
