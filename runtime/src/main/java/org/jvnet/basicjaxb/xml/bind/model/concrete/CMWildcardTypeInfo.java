package org.jvnet.basicjaxb.xml.bind.model.concrete;

import static java.util.Objects.requireNonNull;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.xml.bind.model.MCustomizations;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfoVisitor;
import org.jvnet.basicjaxb.xml.bind.model.MWildcardTypeInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MWildcardTypeInfoOrigin;

public class CMWildcardTypeInfo<T, C extends T> implements
		MWildcardTypeInfo<T, C> {

	private final T targetType;
	private final MWildcardTypeInfoOrigin origin;
	private final MCustomizations customizations = new CMCustomizations();

	public CMWildcardTypeInfo(MWildcardTypeInfoOrigin origin, T targetType) {
		requireNonNull(origin);
		this.origin = origin;
		this.targetType = targetType;
	}

	@Override
	public MCustomizations getCustomizations() {
		return customizations;
	}

	@Override
	public T getTargetType() {
		return targetType;
	}

	@Override
	public QName getTypeName() {
		return null;
	}

	@Override
	public boolean isSimpleType() {
		return false;
	}

	@Override
	public MWildcardTypeInfoOrigin getOrigin() {
		return origin;
	}

	@Override
	public <V> V acceptTypeInfoVisitor(MTypeInfoVisitor<T, C, V> visitor) {
		return visitor.visitWildcardTypeInfo(this);
	}

}
