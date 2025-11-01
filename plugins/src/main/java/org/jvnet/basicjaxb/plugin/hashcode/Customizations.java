package org.jvnet.basicjaxb.plugin.hashcode;

import javax.xml.namespace.QName;

public class Customizations
{
	public static String NAMESPACE_URI = "urn:jvnet.org:basicjaxb:xjc:hashCode";
	public static QName IGNORED_ELEMENT_NAME = new QName(NAMESPACE_URI, "ignored");
}
