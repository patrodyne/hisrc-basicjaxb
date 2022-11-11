package org.jvnet.basicjaxb.xjc.outline;

import java.util.Collection;

import org.jvnet.basicjaxb.xml.bind.model.MPackageInfo;
import org.jvnet.basicjaxb.xml.bind.model.MTargeted;

import com.sun.codemodel.JPackage;

public interface MPackageOutline extends MChildOutline, MTargeted<MPackageInfo> {

	public MPackageInfo getTarget();

	public Collection<MElementOutline> getElementOutlines();

	public Collection<MClassOutline> getClassOutlines();

	public Collection<MEnumOutline> getEnumOutlines();

	public MObjectFactoryOutline getObjectFactoryOutline();

	public JPackage getCode();

}
