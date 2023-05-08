package org.jvnet.basicjaxb.xml.bind.model.concrete;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.lang.Validate;
import org.jvnet.basicjaxb.xml.bind.model.MElementTypeInfo;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfo;

public abstract class CMElementTypeInfo<T, C extends T, O> implements
		MElementTypeInfo<T, C, O> {

	private final QName elementName;

	private final MTypeInfo<T, C> typeInfo;

	private final boolean nillable;

	private final String defaultValue;

	private final O origin;

	private final NamespaceContext defaultValueNamespaceContext;

	public CMElementTypeInfo(O origin, QName elementName,
			MTypeInfo<T, C> typeInfo, boolean nillable, String defaultValue,
			NamespaceContext defaultValueNamespaceContext) {
		Validate.notNull(origin);
		Validate.notNull(elementName);
		Validate.notNull(typeInfo);
		this.origin = origin;
		this.elementName = elementName;
		this.typeInfo = typeInfo;
		this.nillable = nillable;
		this.defaultValue = defaultValue;
		this.defaultValueNamespaceContext = defaultValueNamespaceContext;
	}

	@Override
	public O getOrigin() {
		return this.origin;
	}

	@Override
	public QName getElementName() {
		return elementName;
	}

	@Override
	public MTypeInfo<T, C> getTypeInfo() {
		return typeInfo;
	}

	@Override
	public boolean isNillable() {
		return this.nillable;
	}

	@Override
	public String getDefaultValue() {
		return defaultValue;
	}

	@Override
	public NamespaceContext getDefaultValueNamespaceContext() {
		return defaultValueNamespaceContext;
	}

}
