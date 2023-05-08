package org.jvnet.basicjaxb.xml.bind.model.concrete;

import java.text.MessageFormat;

import javax.xml.namespace.QName;

import org.jvnet.basicjaxb.lang.Validate;
import org.jvnet.basicjaxb.xml.bind.model.MCustomizations;
import org.jvnet.basicjaxb.xml.bind.model.MIDREF;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfo;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfoVisitor;
import org.jvnet.basicjaxb.xmlschema.XmlSchemaConstants;

public class CMIDREF<T, C extends T> implements MIDREF<T, C> {

	private final MTypeInfo<T, C> valueTypeInfo;
	private final T targetType;
	private final MCustomizations customizations = new CMCustomizations();

	public CMIDREF(T targetType, MTypeInfo<T, C> itemTypeInfo) {
		Validate.notNull(targetType);
		Validate.notNull(itemTypeInfo);
		this.targetType = targetType;
		this.valueTypeInfo = itemTypeInfo;
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
	public MTypeInfo<T, C> getValueTypeInfo() {
		return valueTypeInfo;
	}

	@Override
	public QName getTypeName() {
		return XmlSchemaConstants.IDREF;
	}
	
	@Override
	public boolean isSimpleType() {
		return getValueTypeInfo().isSimpleType(); 
	}

	@Override
	public String toString() {
		return MessageFormat.format("ID [{0}]", getValueTypeInfo());
	}

	@Override
	public <V> V acceptTypeInfoVisitor(MTypeInfoVisitor<T, C, V> visitor) {
		return visitor.visitIDREF(this);
	}
}
