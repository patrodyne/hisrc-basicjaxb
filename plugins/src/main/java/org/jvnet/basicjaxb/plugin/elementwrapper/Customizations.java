package org.jvnet.basicjaxb.plugin.elementwrapper;

import javax.xml.namespace.QName;

public class Customizations {

	public static String NAMESPACE_URI = "http://jvnet.org/basicjaxb/xjc/elementWrapper";

	public static QName IGNORED_ELEMENT_NAME = new QName(NAMESPACE_URI,
			"ignored");

}
