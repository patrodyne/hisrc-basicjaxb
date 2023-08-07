package org.jvnet.basicjaxb.xml.bind.model.concrete;

import static java.util.Objects.requireNonNull;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.xml.namespace.QName;

import org.glassfish.jaxb.core.v2.model.core.EnumConstant;
import org.glassfish.jaxb.core.v2.model.core.EnumLeafInfo;
import org.jvnet.basicjaxb.xml.bind.model.MClassInfo;
import org.jvnet.basicjaxb.xml.bind.model.MContainer;
import org.jvnet.basicjaxb.xml.bind.model.MCustomizable;
import org.jvnet.basicjaxb.xml.bind.model.MCustomizations;
import org.jvnet.basicjaxb.xml.bind.model.MElementInfo;
import org.jvnet.basicjaxb.xml.bind.model.MEnumConstantInfo;
import org.jvnet.basicjaxb.xml.bind.model.MEnumLeafInfo;
import org.jvnet.basicjaxb.xml.bind.model.MPackageInfo;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfo;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfoVisitor;
import org.jvnet.basicjaxb.xml.bind.model.concrete.origin.EnumConstantOrigin;
import org.jvnet.basicjaxb.xml.bind.model.concrete.origin.EnumLeafInfoOrigin;
import org.jvnet.basicjaxb.xml.bind.model.origin.MEnumLeafInfoOrigin;

public class CMEnumLeafInfo<T, C extends T> implements MEnumLeafInfo<T, C>, MCustomizable
{
	private final MEnumLeafInfoOrigin origin;
	private final CMCustomizations customizations = new CMCustomizations();
	private final C targetClass;
	private final MPackageInfo _package;
	private final MContainer container;
	private final String name;
	private final String localName;
	private final MTypeInfo<T, C> baseTypeInfo;
	private final List<MEnumConstantInfo<T, C>> constants = new ArrayList<MEnumConstantInfo<T, C>>();
	private final List<MEnumConstantInfo<T, C>> _constants = Collections.unmodifiableList(constants);
	private final QName elementName;
	private final QName typeName;

	public CMEnumLeafInfo(MEnumLeafInfoOrigin origin, C targetClass, MPackageInfo _package, MContainer container,
		String localName, MTypeInfo<T, C> baseTypeInfo, QName elementName, QName typeName)
	{
		requireNonNull(origin);
		requireNonNull(targetClass);
		requireNonNull(_package);
		requireNonNull(localName);
		requireNonNull(baseTypeInfo);
		this.origin = origin;
		this.targetClass = targetClass;
		this._package = _package;
		this.container = container;
		this.localName = localName;
		this.name = _package.getPackagedName(localName);
		this.baseTypeInfo = baseTypeInfo;
		// May be null
		this.elementName = elementName;
		this.typeName = typeName;
	}

	@Override
	public MCustomizations getCustomizations()
	{
		return customizations;
	}

	@Override
	public MEnumLeafInfoOrigin getOrigin()
	{
		return origin;
	}

	@Override
	public C getTargetClass()
	{
		return targetClass;
	}

	@Override
	public T getTargetType()
	{
		return targetClass;
	}

	@Override
	public QName getTypeName()
	{
		return this.typeName;
	}

	@Override
	public boolean isSimpleType()
	{
		return true;
	}

	@Override
	public MElementInfo<T, C> createElementInfo(MClassInfo<T, C> scope, QName substitutionHead)
	{
		return new CMElementInfo<T, C>(getOrigin().createElementInfoOrigin(), getPackageInfo(), getContainer(),
			getLocalName(), getElementName(), scope, this, substitutionHead, null, null);
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public String getLocalName()
	{
		return localName;
	}

	@Override
	public MPackageInfo getPackageInfo()
	{
		return _package;
	}

	@Override
	public MContainer getContainer()
	{
		return container;
	}

	@Override
	public String getContainerLocalName(String delimiter)
	{
		final String localName = getLocalName();
		if (localName == null)
			return null;
		else
		{
			final MContainer container = getContainer();
			if (container == null)
				return localName;
			else
			{
				final String containerLocalName = container.getContainerLocalName(delimiter);
				return containerLocalName == null ? localName : containerLocalName + delimiter + localName;
			}
		}
	}

	@Override
	public MTypeInfo<T, C> getBaseTypeInfo()
	{
		return baseTypeInfo;
	}

	@Override
	public List<MEnumConstantInfo<T, C>> getConstants()
	{
		return _constants;
	}

	@Override
	public void addEnumConstantInfo(MEnumConstantInfo<T, C> enumConstantInfo)
	{
		Objects.requireNonNull(enumConstantInfo);
		this.constants.add(enumConstantInfo);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void removeEnumConstantInfo(MEnumConstantInfo<T, C> enumConstantInfo)
	{
		Objects.requireNonNull(enumConstantInfo);
		if (getOrigin() instanceof EnumLeafInfoOrigin && enumConstantInfo.getOrigin() instanceof EnumConstantOrigin)
		{
			EnumLeafInfo<T, C> eli = ((EnumLeafInfoOrigin<T, C, EnumLeafInfo<T, C>>) getOrigin()).getSource();
			EnumConstant<T, C> ec = ((EnumConstantOrigin<T, C, EnumConstant<T, C>>) enumConstantInfo.getOrigin()).getSource();
			@SuppressWarnings("rawtypes")
			Iterator<? extends EnumConstant> iterator = eli.getConstants().iterator();
			while (iterator.hasNext())
			{
				if (iterator.next() == ec)
					iterator.remove();
			}
		}
	}

	@Override
	public QName getElementName()
	{
		return elementName;
	}

	@Override
	public String toString()
	{
		return MessageFormat.format("EnumInfo [{0}]", getBaseTypeInfo());
	}

	@Override
	public <V> V acceptTypeInfoVisitor(MTypeInfoVisitor<T, C, V> visitor)
	{
		return visitor.visitEnumLeafInfo(this);
	}
}
