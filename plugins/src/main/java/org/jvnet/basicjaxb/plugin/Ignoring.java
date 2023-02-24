package org.jvnet.basicjaxb.plugin;

import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CEnumLeafInfo;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.EnumOutline;
import com.sun.tools.xjc.outline.FieldOutline;

/**
 * Interface for filtering XJC {@link com.sun.tools.xjc.model.Model} and
 * {@link com.sun.tools.xjc.outline.Outline} objects.
 */
public interface Ignoring
{
	public boolean isIgnored(ClassOutline classOutline);

	public boolean isIgnored(EnumOutline enumOutline);

	public boolean isIgnored(FieldOutline fieldOutline);

	public boolean isIgnored(CClassInfo classInfo);

	public boolean isIgnored(CEnumLeafInfo enumLeafInfo);

	public boolean isIgnored(CPropertyInfo propertyInfo);
}
