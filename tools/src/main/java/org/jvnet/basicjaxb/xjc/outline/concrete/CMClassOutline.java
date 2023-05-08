package org.jvnet.basicjaxb.xjc.outline.concrete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.jvnet.basicjaxb.xjc.outline.MClassOutline;
import org.jvnet.basicjaxb.xjc.outline.MModelOutline;
import org.jvnet.basicjaxb.xjc.outline.MPackageOutline;
import org.jvnet.basicjaxb.xjc.outline.MPropertyOutline;
import org.jvnet.basicjaxb.xml.bind.model.MClassInfo;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JDefinedClass;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

public class CMClassOutline implements MClassOutline {

	private final MModelOutline parent;
	private final MPackageOutline packageOutline;
	private final MClassInfo<NType, NClass> target;
	private final MClassOutline superClassOutline;

	private final JDefinedClass referenceCode;
	private final JDefinedClass implementationCode;
	private final JClass implementationReferenceCode;

	private final List<MPropertyOutline> declaredPropertyOutlines = new ArrayList<MPropertyOutline>();
	private final List<MPropertyOutline> _delcaredPropertyOutlines = Collections
			.unmodifiableList(declaredPropertyOutlines);

	public CMClassOutline(MModelOutline parent, MPackageOutline packageOutline,
			MClassInfo<NType, NClass> target, MClassOutline superClassOutline,
			JDefinedClass referenceCode, JDefinedClass implementationCode,
			JClass implementationReferenceCode) {
		Validate.notNull(parent);
		Validate.notNull(packageOutline);
		Validate.notNull(target);
		Validate.notNull(referenceCode);
		Validate.notNull(implementationCode);
		Validate.notNull(implementationReferenceCode);
		this.parent = parent;
		this.packageOutline = packageOutline;
		this.target = target;
		this.superClassOutline = superClassOutline;
		this.referenceCode = referenceCode;
		this.implementationCode = implementationCode;
		this.implementationReferenceCode = implementationReferenceCode;
	}

	@Override
	public MModelOutline getParent() {
		return parent;
	}

	@Override
	public MPackageOutline getPackageOutline() {
		return packageOutline;
	}

	@Override
	public MClassInfo<NType, NClass> getTarget() {
		return target;
	}

	@Override
	public MClassOutline getSuperClassOutline() {
		return superClassOutline;
	}

	@Override
	public JDefinedClass getReferenceCode() {
		return referenceCode;
	}

	@Override
	public JDefinedClass getImplementationCode() {
		return implementationCode;
	}

	@Override
	public JClass getImplementationReferenceCode() {
		return implementationReferenceCode;
	}

	@Override
	public List<MPropertyOutline> getPropertyOutlines() {
		if (getSuperClassOutline() == null) {
			return getDeclaredPropertyOutlines();
		} else {
			final List<MPropertyOutline> propertyOutlines = new ArrayList<MPropertyOutline>();
			propertyOutlines.addAll(getSuperClassOutline()
					.getPropertyOutlines());
			propertyOutlines.addAll(getDeclaredPropertyOutlines());
			return Collections.unmodifiableList(propertyOutlines);
		}
	}

	@Override
	public List<MPropertyOutline> getDeclaredPropertyOutlines() {
		return _delcaredPropertyOutlines;
	}

	public void addDeclaredPropertyOutline(MPropertyOutline propertyOutline) {
		this.declaredPropertyOutlines.add(propertyOutline);
	}

}
