package org.jvnet.basicjaxb.xml.bind.model.concrete;

import static java.util.Objects.requireNonNull;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.xml.bind.model.MBuiltinLeafInfo;
import org.jvnet.basicjaxb.xml.bind.model.MCustomizations;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfoVisitor;
import org.jvnet.basicjaxb.xml.bind.model.origin.MBuiltinLeafInfoOrigin;

public class CMBuiltinLeafInfo<T, C extends T> implements MBuiltinLeafInfo<T, C> {

	private final MBuiltinLeafInfoOrigin origin;
	private final T targetType;
	private final QName typeName;
	private final MCustomizations customizations = new CMCustomizations();

	public CMBuiltinLeafInfo(MBuiltinLeafInfoOrigin origin, T targetType,
			QName typeName) {
		requireNonNull(origin);
		requireNonNull(targetType);
		requireNonNull(typeName);
		this.origin = origin;
		this.targetType = targetType;
		this.typeName = typeName;
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
	public MBuiltinLeafInfoOrigin getOrigin() {
		return origin;
	}

	@Override
	public QName getTypeName() {
		return typeName;
	}

	@Override
	public boolean isSimpleType() {
		return true;
	}

	@Override
	public String toString() {
		return "BuiltinLeafInfo [" + getTypeName() + "]";
	}

	@Override
	public <V> V acceptTypeInfoVisitor(MTypeInfoVisitor<T, C, V> visitor) {
		return visitor.visitBuiltinLeafInfo(this);
	}
}
