package org.jvnet.basicjaxb.xml.bind.model.concrete;

import static java.util.Objects.requireNonNull;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.xml.bind.model.MAttributePropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.MClassInfo;
import org.jvnet.basicjaxb.xml.bind.model.MPropertyInfoVisitor;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MPropertyInfoOrigin;

public class CMAttributePropertyInfo<T, C extends T> extends
		CMSingleTypePropertyInfo<T, C> implements MAttributePropertyInfo<T, C> {

	private final QName attributeName;

	public CMAttributePropertyInfo(MPropertyInfoOrigin origin,
			MClassInfo<T, C> classInfo, String privateName,
			MTypeInfo<T, C> typeInfo, QName attributeName, boolean required,
			String defaultValue, NamespaceContext defaultValueNamespaceContext) {
		super(origin, classInfo, privateName, false, typeInfo, required,
				defaultValue, defaultValueNamespaceContext);
		requireNonNull(attributeName);
		this.attributeName = attributeName;
	}

	@Override
	public QName getAttributeName() {
		return attributeName;
	}

	@Override
	public <V> V acceptPropertyInfoVisitor(MPropertyInfoVisitor<T, C, V> visitor) {
		return visitor.visitAttributePropertyInfo(this);
	}

}
