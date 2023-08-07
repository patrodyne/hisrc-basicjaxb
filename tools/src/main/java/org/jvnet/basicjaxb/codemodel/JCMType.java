package org.jvnet.basicjaxb.codemodel;

import static java.util.Objects.requireNonNull;

import com.sun.codemodel.JType;

public abstract class JCMType<JT extends JType> {

	private final JCMTypeFactory factory;
	private final JT type;
	private final String fullName;

	public JCMType(JCMTypeFactory factory, JT type) {
		this.factory = requireNonNull(factory);
		this.type = requireNonNull(type);
		this.fullName = type.fullName();
	}

	public JCMTypeFactory getFactory() {
		return factory;
	}

	public JT getType() {
		return type;
	}

	public String getFullName() {
		return fullName;
	}

	public abstract JType getDeclarableType();

	public abstract boolean matches(JCMType<?> type);

	public abstract <V> V accept(JCMTypeVisitor<V> visitor);

	@Override
	public String toString() {
		return getType().toString();
	}
}
