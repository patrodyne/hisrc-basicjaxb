package org.jvnet.basicjaxb.xml.bind.model.concrete;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.xml.namespace.QName;

import org.glassfish.jaxb.core.v2.model.core.ClassInfo;
import org.glassfish.jaxb.core.v2.model.core.ElementInfo;
import org.glassfish.jaxb.core.v2.model.core.EnumLeafInfo;
import org.glassfish.jaxb.core.v2.model.core.TypeInfoSet;
import org.jvnet.basicjaxb.xml.bind.model.MBuiltinLeafInfo;
import org.jvnet.basicjaxb.xml.bind.model.MClassInfo;
import org.jvnet.basicjaxb.xml.bind.model.MCustomizations;
import org.jvnet.basicjaxb.xml.bind.model.MElementInfo;
import org.jvnet.basicjaxb.xml.bind.model.MEnumLeafInfo;
import org.jvnet.basicjaxb.xml.bind.model.MModelInfo;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfo;
import org.jvnet.basicjaxb.xml.bind.model.concrete.origin.ClassInfoOrigin;
import org.jvnet.basicjaxb.xml.bind.model.concrete.origin.ElementInfoOrigin;
import org.jvnet.basicjaxb.xml.bind.model.concrete.origin.EnumLeafInfoOrigin;
import org.jvnet.basicjaxb.xml.bind.model.concrete.origin.TypeInfoSetOrigin;
import org.jvnet.basicjaxb.xml.bind.model.origin.MModelInfoOrigin;

public class CMModel<T, C extends T> implements MModelInfo<T, C> {

	private final MModelInfoOrigin origin;

	private final CMCustomizations customizations = new CMCustomizations();

	private final Collection<MBuiltinLeafInfo<T, C>> builtinLeafInfos = new ArrayList<MBuiltinLeafInfo<T, C>>();
	private final Collection<MBuiltinLeafInfo<T, C>> unmodifiableBuiltinLeafInfos = Collections
			.unmodifiableCollection(builtinLeafInfos);
	private final Map<QName, MBuiltinLeafInfo<T, C>> builtinLeafInfosMap = new HashMap<QName, MBuiltinLeafInfo<T, C>>();
	private final Map<QName, MBuiltinLeafInfo<T, C>> unmodifiableBuiltinLeafInfosMap = Collections
			.unmodifiableMap(builtinLeafInfosMap);

	private final Map<String, MClassInfo<T, C>> classInfosMap = new LinkedHashMap<String, MClassInfo<T, C>>();
	private final Collection<MClassInfo<T, C>> classInfos = new ArrayList<MClassInfo<T, C>>();
	private final Collection<MClassInfo<T, C>> unmodifiableClassInfos = Collections
			.unmodifiableCollection(classInfos);

	private final Collection<MEnumLeafInfo<T, C>> enumLeafInfos = new ArrayList<MEnumLeafInfo<T, C>>();
	private final Collection<MEnumLeafInfo<T, C>> unmodifiableEnumLeafInfos = Collections
			.unmodifiableCollection(enumLeafInfos);

	private final Map<QName, MTypeInfo<T, C>> typeInfosMap = new LinkedHashMap<QName, MTypeInfo<T, C>>();
	private final Collection<MTypeInfo<T, C>> typeInfos = new ArrayList<MTypeInfo<T, C>>();
	private final Collection<MTypeInfo<T, C>> unmodifiableTypeInfos = Collections
			.unmodifiableCollection(typeInfos);

	private final Collection<MElementInfo<T, C>> elementInfos = new ArrayList<MElementInfo<T, C>>();
	private final Map<QName, MElementInfo<T, C>> globalElementInfosMap = new LinkedHashMap<QName, MElementInfo<T, C>>();
	private final Collection<MElementInfo<T, C>> unmodifiableElementInfos = Collections
			.unmodifiableCollection(elementInfos);

	private final Map<QName, MElementInfo<T, C>> elementInfosMap = new HashMap<QName, MElementInfo<T, C>>();
	private final Map<QName, MElementInfo<T, C>> unmodifiableElementInfosMap = Collections
			.unmodifiableMap(elementInfosMap);

	public CMModel(MModelInfoOrigin origin) {
		requireNonNull(origin);
		this.origin = origin;
	}

	@Override
	public MCustomizations getCustomizations() {
		return customizations;
	}

	@Override
	public MModelInfoOrigin getOrigin() {
		return origin;
	}

	@Override
	public Collection<MBuiltinLeafInfo<T, C>> getBuiltinLeafInfos() {
		return unmodifiableBuiltinLeafInfos;
	}

	@Override
	public Collection<MClassInfo<T, C>> getClassInfos() {
		return unmodifiableClassInfos;
	}

	@Override
	public MClassInfo<T, C> getClassInfo(String name) {
		return this.classInfosMap.get(name);
	}

	@Override
	public Collection<MEnumLeafInfo<T, C>> getEnumLeafInfos() {
		return unmodifiableEnumLeafInfos;
	}

	@Override
	public Collection<MElementInfo<T, C>> getElementInfos() {
		return unmodifiableElementInfos;
	}

	@Override
	public MElementInfo<T, C> getGlobalElementInfo(QName elementName) {
		return this.globalElementInfosMap.get(elementName);
	}

	public Map<QName, MElementInfo<T, C>> getElementInfosMap() {
		return unmodifiableElementInfosMap;
	}

	@Override
	public Collection<MTypeInfo<T, C>> getTypeInfos() {
		return unmodifiableTypeInfos;
	}

	@Override
	public MTypeInfo<T, C> getTypeInfo(QName typeName) {
		return this.typeInfosMap.get(typeName);
	}

	public MBuiltinLeafInfo<T, C> getBuiltinLeafInfo(QName name) {
		Objects.requireNonNull(name);
		return this.unmodifiableBuiltinLeafInfosMap.get(name);
	}

	@Override
	public void addBuiltinLeafInfo(MBuiltinLeafInfo<T, C> builtinLeafInfo) {
		Objects.requireNonNull(builtinLeafInfo);
		this.builtinLeafInfos.add(builtinLeafInfo);
		this.typeInfos.add(builtinLeafInfo);
		final QName typeName = builtinLeafInfo.getTypeName();
		if (typeName != null) {
			this.typeInfosMap.put(typeName, builtinLeafInfo);
			this.builtinLeafInfosMap.put(typeName, builtinLeafInfo);
		}
	}

	@Override
	public void addEnumLeafInfo(MEnumLeafInfo<T, C> enumLeafInfo) {
		Objects.requireNonNull(enumLeafInfo);
		this.enumLeafInfos.add(enumLeafInfo);
		this.typeInfos.add(enumLeafInfo);
		final QName typeName = enumLeafInfo.getTypeName();
		if (typeName != null) {
			this.typeInfosMap.put(typeName, enumLeafInfo);
		}

		final QName elementName = enumLeafInfo.getElementName();
		if (elementName != null) {
			final MElementInfo<T, C> elementInfo = enumLeafInfo
					.createElementInfo(null, null);
			this.elementInfos.add(elementInfo);
			this.elementInfosMap.put(elementName, elementInfo);
			if (elementInfo.getScope() == null) {
				this.globalElementInfosMap.put(elementName, elementInfo);
			}
		}

	}

	@Override
	public void removeEnumLeafInfo(MEnumLeafInfo<T, C> enumLeafInfo) {
		Objects.requireNonNull(enumLeafInfo);
		this.enumLeafInfos.remove(enumLeafInfo);
		this.typeInfos.remove(enumLeafInfo);
		final QName typeName = enumLeafInfo.getTypeName();
		if (typeName != null) {
			this.typeInfosMap.remove(typeName);
		}
		final QName elementName = enumLeafInfo.getElementName();
		if (elementName != null) {
			final MElementInfo<T, C> elementInfo = this.elementInfosMap
					.remove(elementName);
			this.globalElementInfosMap.remove(elementName);
			if (elementInfo != null) {
				this.elementInfos.remove(elementInfo);
			}
		}
		// TODO Not very good
		if (getOrigin() instanceof TypeInfoSetOrigin
				&& enumLeafInfo.getOrigin() instanceof EnumLeafInfoOrigin) {

			@SuppressWarnings("unchecked")
			final TypeInfoSet<T, C, ?, ?> tis = ((TypeInfoSetOrigin<T, C, TypeInfoSet<T, C, ?, ?>>) getOrigin())
					.getSource();

			@SuppressWarnings("unchecked")
			final EnumLeafInfo<T, C> eli = ((EnumLeafInfoOrigin<T, C, EnumLeafInfo<T, C>>) enumLeafInfo
					.getOrigin()).getSource();
			tis.enums().remove(eli.getClazz());
		}
	}

	@Override
	public void addClassInfo(MClassInfo<T, C> classInfo) {
		Objects.requireNonNull(classInfo);
		this.classInfos.add(classInfo);
		this.classInfosMap.put(classInfo.getName(), classInfo);
		this.typeInfos.add(classInfo);

		final QName typeName = classInfo.getTypeName();
		if (typeName != null) {
			this.typeInfosMap.put(typeName, classInfo);
		}

		final QName elementName = classInfo.getElementName();
		if (elementName != null) {
			// TODO why null, null?
			final MElementInfo<T, C> elementInfo = classInfo.createElementInfo(
					null, null);
			this.elementInfos.add(elementInfo);
			this.elementInfosMap.put(elementName, elementInfo);
			if (elementInfo.getScope() == null) {
				this.globalElementInfosMap.put(elementName, elementInfo);
			}
		}
	}

	@Override
	public void removeClassInfo(MClassInfo<T, C> classInfo) {
		Objects.requireNonNull(classInfo);
		this.classInfos.remove(classInfo);
		this.classInfosMap.remove(classInfo.getName());
		this.typeInfos.remove(classInfo);
		final QName typeName = classInfo.getTypeName();
		if (typeName != null) {
			this.typeInfosMap.remove(typeName);
		}
		final QName elementName = classInfo.getElementName();
		if (elementName != null) {
			final MElementInfo<T, C> elementInfo = this.elementInfosMap
					.remove(elementName);
			this.globalElementInfosMap.remove(elementName);
			if (elementInfo != null) {
				this.elementInfos.remove(elementInfo);
			}
		}
		if (getOrigin() instanceof TypeInfoSetOrigin
				&& classInfo.getOrigin() instanceof ClassInfoOrigin) {
			@SuppressWarnings("unchecked")
			final TypeInfoSet<T, C, ?, ?> tis = ((TypeInfoSetOrigin<T, C, TypeInfoSet<T, C, ?, ?>>) getOrigin())
					.getSource();
			@SuppressWarnings("unchecked")
			final ClassInfo<T, C> ci = ((ClassInfoOrigin<T, C, ClassInfo<T, C>>) classInfo
					.getOrigin()).getSource();
			tis.beans().remove(ci);

		}
	}

	@Override
	public void addElementInfo(MElementInfo<T, C> elementInfo) {
		Objects.requireNonNull(elementInfo);
		Objects.requireNonNull(elementInfo.getElementName());
		this.elementInfos.add(elementInfo);
		this.elementInfosMap.put(elementInfo.getElementName(), elementInfo);
		if (elementInfo.getScope() == null) {
			this.globalElementInfosMap.put(elementInfo.getElementName(),
					elementInfo);
		}
	}

	@Override
	public void removeElementInfo(MElementInfo<T, C> elementInfo) {
		Objects.requireNonNull(elementInfo);
		Objects.requireNonNull(elementInfo.getElementName());
		this.elementInfos.remove(elementInfo);
		this.elementInfosMap.remove(elementInfo.getElementName());
		this.globalElementInfosMap.remove(elementInfo.getElementName());
		// TODO Not very good
		if (getOrigin() instanceof TypeInfoSetOrigin
				&& elementInfo.getOrigin() instanceof ElementInfoOrigin) {
			@SuppressWarnings("unchecked")
			final TypeInfoSet<T, C, ?, ?> tis = ((TypeInfoSetOrigin<T, C, TypeInfoSet<T, C, ?, ?>>) getOrigin())
					.getSource();

			@SuppressWarnings("unchecked")
			final ElementInfo<T, C> ei = ((ElementInfoOrigin<T, C, ElementInfo<T, C>>) elementInfo
					.getOrigin()).getSource();

			tis.getElementMappings(ei.getScope().getClazz()).remove(
					ei.getElementName());
		}

	}
}
