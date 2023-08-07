package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.glassfish.jaxb.core.v2.model.core.ElementPropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.TypeRef;
import org.jvnet.basicjaxb.xml.bind.model.origin.MElementTypeRefOrigin;

public class CMElementTypeRefOrigin<T, C, EPI extends ElementPropertyInfo<T, C>, TR extends TypeRef<T, C>>
		implements MElementTypeRefOrigin {

	private final EPI source;
	private final TR typeRef;

	public CMElementTypeRefOrigin(EPI source, TR typeRef) {
		requireNonNull(source);
		requireNonNull(typeRef);
		this.source = source;
		this.typeRef = typeRef;
	}

	public EPI getSource() {
		return source;
	}

	public TR getTypeRef() {
		return typeRef;
	}
}
