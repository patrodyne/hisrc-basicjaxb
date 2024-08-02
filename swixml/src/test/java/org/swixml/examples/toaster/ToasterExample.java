package org.swixml.examples.toaster;

import org.swixml.jsr.widgets.Toaster;

public class ToasterExample
{
	public static void main(String[] args)
	{
	    Toaster toasterManager = new Toaster();
	    toasterManager.showToaster("JToaster Hello World!");
	}
}

