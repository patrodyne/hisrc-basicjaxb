package org.jvnet.basicjaxb.xml.bind.model.concrete;

import java.text.MessageFormat;

import org.jvnet.basicjaxb.xml.bind.model.MIDREFS;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfo;
import org.jvnet.basicjaxb.xml.bind.model.MTypeInfoVisitor;
import org.jvnet.basicjaxb.xmlschema.XmlSchemaConstants;

public class CMIDREFS<T, C extends T> extends CMList<T, C> implements MIDREFS<T, C> {

	public CMIDREFS(T targetType, MTypeInfo<T, C> itemTypeInfo) {
		super(targetType, itemTypeInfo, XmlSchemaConstants.IDREFS);
	}

	@Override
	public String toString() {
		return MessageFormat.format("IDREFS [{0}]", getItemTypeInfo());
	}

	@Override
	public <V> V acceptTypeInfoVisitor(MTypeInfoVisitor<T, C, V> visitor) {
		return visitor.visitIDREFS(this);
	}
}
