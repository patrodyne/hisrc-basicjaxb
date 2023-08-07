package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import static java.util.Objects.requireNonNull;

import org.glassfish.jaxb.core.v2.model.core.WildcardTypeInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MWildcardTypeInfoOrigin;

public class CMWildcardTypeInfoOrigin<T, C, WTI extends WildcardTypeInfo<T, C>>
		implements MWildcardTypeInfoOrigin, WildcardTypeInfoOrigin<T, C, WTI> {

	private final WTI source;

	public CMWildcardTypeInfoOrigin(WTI source) {
		requireNonNull(source);
		this.source = source;
	}

	@Override
	public WTI getSource() {
		return source;
	}

}
