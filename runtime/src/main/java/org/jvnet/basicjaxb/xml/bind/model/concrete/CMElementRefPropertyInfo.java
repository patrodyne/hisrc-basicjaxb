package org.jvnet.basicjaxb.xml.bind.model.concrete;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.xml.bind.model.MClassInfo;
import org.jvnet.basicjaxb.xml.bind.model.MElementRefPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MPropertyInfoVisitor;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MPropertyInfoOrigin;

public class CMElementRefPropertyInfo<T, C extends T> extends
		CMPropertyInfo<T, C> implements MElementRefPropertyInfo<T, C> {

	private final MTypeInfo<T, C> typeInfo;
	private final QName elementName;
	private final QName wrapperElementName;

	private final boolean mixed;
	private final boolean domAllowed;
	private final boolean typedObjectAllowed;
	private final String defaultValue;
	private final NamespaceContext defaultValueNamespaceContext;

	public CMElementRefPropertyInfo(MPropertyInfoOrigin origin,
			MClassInfo<T, C> classInfo, String privateName, boolean collection,
			boolean required, MTypeInfo<T, C> typeInfo, QName elementName,
			QName wrapperElementName, boolean mixed, boolean domAllowed,
			boolean typedObjectAllowed, String defaultValue,
			NamespaceContext defaultValueNamespaceContext) {
		super(origin, classInfo, privateName, collection, required);
		this.typeInfo = typeInfo;
		this.elementName = elementName;
		this.wrapperElementName = wrapperElementName;
		this.mixed = mixed;
		this.domAllowed = domAllowed;
		this.typedObjectAllowed = typedObjectAllowed;
		this.defaultValue = defaultValue;
		this.defaultValueNamespaceContext = defaultValueNamespaceContext;
	}

	@Override
	public MTypeInfo<T, C> getTypeInfo() {
		return typeInfo;
	}

	@Override
	public QName getElementName() {
		return elementName;
	}

	@Override
	public QName getWrapperElementName() {
		return wrapperElementName;
	}

	@Override
	public boolean isMixed() {
		return mixed;
	}

	@Override
	public boolean isDomAllowed() {
		return domAllowed;
	}

	@Override
	public boolean isTypedObjectAllowed() {
		return typedObjectAllowed;
	}

	@Override
	public boolean isNillable() {
		return true;
	}

	@Override
	public String getDefaultValue() {
		return defaultValue;
	}

	@Override
	public NamespaceContext getDefaultValueNamespaceContext() {
		return defaultValueNamespaceContext;
	}

	@Override
	public <V> V acceptPropertyInfoVisitor(MPropertyInfoVisitor<T, C, V> visitor) {
		return visitor.visitElementRefPropertyInfo(this);
	}

}
