package org.jvnet.basicjaxb.xjc.outline.concrete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.jvnet.basicjaxb.xjc.outline.MClassOutline;
import org.jvnet.basicjaxb.xjc.outline.MElementOutline;
import org.jvnet.basicjaxb.xjc.outline.MEnumOutline;
import org.jvnet.basicjaxb.xjc.outline.MModelOutline;
import org.jvnet.basicjaxb.xjc.outline.MObjectFactoryOutline;
import org.jvnet.basicjaxb.xjc.outline.MPackageOutline;
import org.jvnet.basicjaxb.xml.bind.model.MPackageInfo;

import com.sun.codemodel.JPackage;
import com.sun.tools.xjc.outline.PackageOutline;

public class CMPackageOutline implements MPackageOutline {

	private final MModelOutline parent;

	private final MPackageInfo target;

	private final JPackage code;

	private final MObjectFactoryOutline objectFactoryOutline;

	private final List<MElementOutline> elementOutlines = new ArrayList<MElementOutline>();
	private final List<MClassOutline> classOutlines = new ArrayList<MClassOutline>();
	private final List<MEnumOutline> enumOutlines = new ArrayList<MEnumOutline>();

	private final List<MElementOutline> _elementOutlines = Collections
			.unmodifiableList(elementOutlines);
	private final List<MClassOutline> _classOutlines = Collections
			.unmodifiableList(classOutlines);
	private final List<MEnumOutline> _enumOutlines = Collections
			.unmodifiableList(enumOutlines);

	public CMPackageOutline(MModelOutline parent, MPackageInfo target,
			PackageOutline packageOutline) {
		Validate.notNull(parent);
		Validate.notNull(target);
		this.parent = parent;
		this.target = target;
		this.code = packageOutline._package();
		this.objectFactoryOutline = new CMObjectFactoryOutline(parent, this,
				packageOutline.objectFactory());
	}

	@Override
	public MModelOutline getParent() {
		return parent;
	}

	@Override
	public MPackageInfo getTarget() {
		return target;
	}

	@Override
	public JPackage getCode() {
		return code;
	}

	@Override
	public MObjectFactoryOutline getObjectFactoryOutline() {
		return objectFactoryOutline;
	}

	@Override
	public Collection<MClassOutline> getClassOutlines() {
		return _classOutlines;
	}

	public void addClassOutline(MClassOutline classOutline) {
		Validate.notNull(classOutline);
		Validate.isTrue(classOutline.getPackageOutline() == this);
		this.classOutlines.add(classOutline);
	}

	@Override
	public Collection<MElementOutline> getElementOutlines() {
		return _elementOutlines;
	}

	public void addElementOutline(MElementOutline elementOutline) {
		Validate.notNull(elementOutline);
		Validate.isTrue(elementOutline.getPackageOutline() == this);
		this.elementOutlines.add(elementOutline);
	}

	@Override
	public Collection<MEnumOutline> getEnumOutlines() {
		return _enumOutlines;
	}

	public void addEnumOutline(MEnumOutline enumOutline) {
		Validate.notNull(enumOutline);
		Validate.isTrue(enumOutline.getPackageOutline() == this);
		this.enumOutlines.add(enumOutline);
	}
}
