package org.jvnet.basicjaxb.xml.bind.model;

public abstract class MPackagedTypeInfoVisitor<T, C extends T, V> implements
		MTypeInfoVisitor<T, C, V> {

	public abstract V visitPackagedTypeInfo(MPackagedTypeInfo<T, C> info);

	@Override
	public V visitList(MList<T, C> info) {
		return null;
	}

	@Override
	public V visitBuiltinLeafInfo(MBuiltinLeafInfo<T, C> info) {
		return null;
	}

	@Override
	public V visitID(MID<T, C> info) {
		return null;
	}

	@Override
	public V visitIDREF(MIDREF<T, C> info) {
		return null;
	}

	@Override
	public V visitIDREFS(MIDREFS<T, C> info) {
		return null;
	}

	@Override
	public V visitEnumLeafInfo(MEnumLeafInfo<T, C> info) {
		return visitPackagedTypeInfo(info);
	}

	@Override
	public V visitWildcardTypeInfo(MWildcardTypeInfo<T, C> info) {
		return null;
	}

	@Override
	public V visitClassInfo(MClassInfo<T, C> info) {
		return visitPackagedTypeInfo(info);
	}

}
