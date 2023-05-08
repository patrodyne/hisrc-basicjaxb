package org.jvnet.basicjaxb.xml.bind.model.util;

import org.jvnet.basicjaxb.xml.bind.model.MBuiltinLeafInfo;
import org.jvnet.basicjaxb.xml.bind.model.MClassInfo;
import org.jvnet.basicjaxb.xml.bind.model.MClassRef;
import org.jvnet.basicjaxb.xml.bind.model.MEnumLeafInfo;
import org.jvnet.basicjaxb.xml.bind.model.MID;
import org.jvnet.basicjaxb.xml.bind.model.MIDREF;
import org.jvnet.basicjaxb.xml.bind.model.MIDREFS;
import org.jvnet.basicjaxb.xml.bind.model.MList;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfo;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfoVisitor;
import org.jvnet.basicjaxb.xml.bind.model.MWildcardTypeInfo;

public class DefaultTypeInfoVisitor<T, C extends T, V> implements
		MTypeInfoVisitor<T, C, V> {

	public V visitTypeInfo(MTypeInfo<T, C> typeInfo) {
		return null;
	}

	@Override
	public V visitList(MList<T, C> info) {
		return visitTypeInfo(info);
	}

	@Override
	public V visitID(MID<T, C> info) {
		return visitTypeInfo(info);
	}

	@Override
	public V visitIDREF(MIDREF<T, C> info) {
		return visitTypeInfo(info);
	}

	@Override
	public V visitIDREFS(MIDREFS<T, C> info) {
		return visitTypeInfo(info);
	}

	@Override
	public V visitBuiltinLeafInfo(MBuiltinLeafInfo<T, C> info) {
		return visitTypeInfo(info);
	}

	@Override
	public V visitEnumLeafInfo(MEnumLeafInfo<T, C> info) {
		return visitTypeInfo(info);
	}

	@Override
	public V visitWildcardTypeInfo(MWildcardTypeInfo<T, C> info) {
		return visitTypeInfo(info);
	}

	@Override
	public V visitClassInfo(MClassInfo<T, C> info) {
		return visitTypeInfo(info);
	}
	
	@Override
	public V visitClassRef(MClassRef<T, C> info) {
		return visitTypeInfo(info);
	}
}
