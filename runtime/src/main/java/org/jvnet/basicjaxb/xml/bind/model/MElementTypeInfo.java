package org.jvnet.basicjaxb.xml.bind.model;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.xml.bind.model.origin.MOriginated;

public interface MElementTypeInfo<T, C extends T, O> extends MTyped<T, C>,
		MNillable, MDefaultValue, MOriginated<O> {

	public QName getElementName();

}
