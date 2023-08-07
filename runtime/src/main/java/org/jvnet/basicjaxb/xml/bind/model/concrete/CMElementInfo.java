package org.jvnet.basicjaxb.xml.bind.model.concrete;

import static java.util.Objects.requireNonNull;

import java.text.MessageFormat;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.xml.bind.model.MClassInfo;
import org.jvnet.basicjaxb.xml.bind.model.MContainer;
import org.jvnet.basicjaxb.xml.bind.model.MElementInfo;
import org.jvnet.basicjaxb.xml.bind.model.MPackageInfo;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfo;
import org.jvnet.basicjaxb.xml.bind.model.origin.MElementInfoOrigin;

public class CMElementInfo<T, C extends T> implements MElementInfo<T, C> {

	private final MElementInfoOrigin origin;

	private final MPackageInfo _package;

	private final MContainer container;

	private final String localName;

	private final QName elementName;

	private final MClassInfo<T, C> scope;

	private final MTypeInfo<T, C> typeInfo;

	private final QName substitutionHead;

	private final String defaultValue;

	private final NamespaceContext defaultValueNamespaceContext;

	public CMElementInfo(MElementInfoOrigin origin, MPackageInfo _package,
			MContainer container, String localName, QName elementName,
			MClassInfo<T, C> scope, MTypeInfo<T, C> typeInfo,
			QName substitutionHead, String defaultValue,
			NamespaceContext defaultValueNamespaceContext) {
		super();
		requireNonNull(origin);
		requireNonNull(elementName);
		requireNonNull(_package);
		this.origin = origin;
		this._package = _package;
		this.container = container;
		this.localName = localName;
		this.elementName = elementName;
		this.scope = scope;
		this.typeInfo = typeInfo;
		this.substitutionHead = substitutionHead;
		this.defaultValue = defaultValue;
		this.defaultValueNamespaceContext = defaultValueNamespaceContext;
	}

	@Override
	public MElementInfoOrigin getOrigin() {
		return origin;
	}

	@Override
	public MPackageInfo getPackageInfo() {
		return _package;
	}

	@Override
	public MContainer getContainer() {
		return container;
	}

	@Override
	public String getLocalName() {
		return localName;
	}

	@Override
	public String getContainerLocalName(String delimiter) {
		final String localName = getLocalName();
		if (localName == null) {
			return null;
		} else {
			final MContainer container = getContainer();
			if (container == null) {
				return localName;
			} else {
				final String containerLocalName = container
						.getContainerLocalName(delimiter);
				return containerLocalName == null ? localName
						: containerLocalName + delimiter + localName;
			}
		}
	}

	@Override
	public QName getElementName() {
		return elementName;
	}

	@Override
	public MClassInfo<T, C> getScope() {
		return scope;
	}

	@Override
	public MTypeInfo<T, C> getTypeInfo() {
		return typeInfo;
	}

	@Override
	public QName getSubstitutionHead() {
		return substitutionHead;
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
	public String toString() {
		return MessageFormat.format("ElementInfo [{0}: {1}]", getElementName(),
				getTypeInfo());
	}

}
