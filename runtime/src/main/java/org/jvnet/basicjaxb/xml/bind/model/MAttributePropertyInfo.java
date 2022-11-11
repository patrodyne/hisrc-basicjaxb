package org.jvnet.basicjaxb.xml.bind.model;

import javax.xml.namespace.QName;

public interface MAttributePropertyInfo<T, C extends T> extends
		MSingleTypePropertyInfo<T, C> {

	public QName getAttributeName();

}