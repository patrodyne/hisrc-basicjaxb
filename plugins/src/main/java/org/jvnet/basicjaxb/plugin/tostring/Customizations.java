package org.jvnet.basicjaxb.plugin.tostring;

import javax.xml.namespace.QName;

public class Customizations
{
	public static String NAMESPACE_URI = "http://jvnet.org/basicjaxb/xjc/toString";
	public static QName IGNORED_ELEMENT_NAME = new QName(NAMESPACE_URI, "ignored");
}
