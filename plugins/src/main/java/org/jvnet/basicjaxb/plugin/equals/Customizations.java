package org.jvnet.basicjaxb.plugin.equals;

import javax.xml.namespace.QName;

public class Customizations
{
	public static String NAMESPACE_URI = "http://jvnet.org/basicjaxb/xjc/equals";
	public static QName IGNORED_ELEMENT_NAME = new QName(NAMESPACE_URI, "ignored");
}
