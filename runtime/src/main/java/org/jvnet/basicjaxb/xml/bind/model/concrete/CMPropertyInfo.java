package org.jvnet.basicjaxb.xml.bind.model.concrete;

import static java.util.Objects.requireNonNull;

import org.jvnet.basicjaxb.xml.bind.model.MClassInfo;
import org.jvnet.basicjaxb.xml.bind.model.MCustomizable;
import org.jvnet.basicjaxb.xml.bind.model.MCustomizations;
import org.jvnet.basicjaxb.xml.bind.model.MPropertyInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MPropertyInfoOrigin;

public abstract class CMPropertyInfo<T, C extends T> implements
		MPropertyInfo<T, C>, MCustomizable {

	private CMCustomizations customizations = new CMCustomizations();
	private MPropertyInfoOrigin origin;
	private MClassInfo<T, C> classInfo;

	private final String privateName;

	private final boolean collection;

	private final boolean required;

	public CMPropertyInfo(MPropertyInfoOrigin origin,
			MClassInfo<T, C> classInfo, String privateName, boolean collection,
			boolean required) {
		requireNonNull(origin);
		requireNonNull(classInfo);
		requireNonNull(privateName);
		this.origin = origin;
		this.classInfo = classInfo;
		this.privateName = privateName;
		this.collection = collection;
		this.required = required;
	}

	@Override
	public MCustomizations getCustomizations() {
		return customizations;
	}

	@Override
	public MPropertyInfoOrigin getOrigin() {
		return origin;
	}

	@Override
	public MClassInfo<T, C> getClassInfo() {
		return classInfo;
	}

	@Override
	public String getPrivateName() {
		return privateName;
	}

	@Override
	public String getPublicName() {
		// TODO
		return this.getPrivateName();
	}

	@Override
	public boolean isCollection() {
		return collection;
	}

	@Override
	public boolean isRequired() {
		return required;
	}

}
