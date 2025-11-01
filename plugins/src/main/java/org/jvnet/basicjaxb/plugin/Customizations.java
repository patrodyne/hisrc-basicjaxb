package org.jvnet.basicjaxb.plugin;

import javax.xml.namespace.QName;

public class Customizations
{
	public static final String NAMESPACE_URI = "urn:jvnet.org:basicjaxb:xjc";
	public static final QName GENERATED_ELEMENT_NAME = new QName(NAMESPACE_URI, "generated");
	public static final QName IGNORED_ELEMENT_NAME = new QName(NAMESPACE_URI, "ignored");
	public static final QName PROPERTY_ELEMENT_NAME = new QName(NAMESPACE_URI, "property");
}
