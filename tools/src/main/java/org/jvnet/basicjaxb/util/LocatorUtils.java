package org.jvnet.basicjaxb.util;

import org.jvnet.basicjaxb.lang.StringUtils;
import org.xml.sax.Locator;

import com.sun.codemodel.JDefinedClass;
import com.sun.tools.xjc.model.CClassInfo;
import com.sun.tools.xjc.model.CCustomizable;
import com.sun.tools.xjc.model.CPluginCustomization;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.CTypeInfo;
import com.sun.tools.xjc.outline.ClassOutline;
import com.sun.tools.xjc.outline.ElementOutline;
import com.sun.tools.xjc.outline.EnumOutline;
import com.sun.tools.xjc.outline.FieldOutline;

/**
 * Utility methods to get a SAX {@link Locator} from
 * {@link com.sun.tools.xjc.model.Model} and
 * {@link com.sun.tools.xjc.outline.Outline} classes.
 */
public class LocatorUtils
{
	private LocatorUtils() {  }

	public static Locator getLocator(Object metadata)
	{
		if ( metadata instanceof Locator )
			return (Locator) metadata;
		else
			return null;
	}
	
	public static Locator getLocator(JDefinedClass dc)
	{
		return (dc != null ) ? getLocator(dc.metadata) : null;
	}

	public static Locator getLocator(CClassInfo ci)
	{
		Locator locator = null;
		if ( ci != null )
		{
			locator = ci.getLocator();
			if ( (locator == null) && (ci.getSchemaComponent() != null ) )
				locator = ci.getSchemaComponent().getLocator();
		}
		return locator;
	}

	public static Locator getLocator(CCustomizable cu)
	{
		Locator locator = null;
		if ( cu != null )
		{
			locator = cu.getLocator();
			if ( (locator == null) && (cu.getSchemaComponent() != null) )
				locator = cu.getSchemaComponent().getLocator();
		}
		return locator;
	}

	public static Locator getLocator(ClassOutline co)
	{
		Locator locator = null;
		if ( co.getTarget() != null )
			locator = getLocator(co.getTarget());
		else if ( co.getImplClass() != null )
			locator = getLocator(co.getImplClass());
		return locator;
	}

	public static Locator getLocator(FieldOutline fo)
	{
		Locator locator = null;
		if ( (fo != null) && (fo.getPropertyInfo() != null) )
		{
			CPropertyInfo fieldInfo = fo.getPropertyInfo();
			locator = fieldInfo.getLocator();
			if ( (locator == null) && (fieldInfo.parent() != null) )
			{
				CTypeInfo fieldParent = fieldInfo.parent();
				if ( fieldParent.getSchemaComponent() != null )
					locator = fieldParent.getSchemaComponent().getLocator();
				else
					locator = fieldParent.getLocator();
			}
		}
		return locator;
	}
	
	public static Locator getLocator(EnumOutline eo)
	{
		Locator locator = null;
		if ( eo.getTarget() != null )
			locator = getLocator(eo.getTarget());
		else if ( eo.getImplClass() != null )
			locator = getLocator(eo.getImplClass());
		return locator;
	}
	
	public static Locator getLocator(CPropertyInfo pi)
	{
		Locator locator = null;
		if ( pi != null )
		{
			locator = pi.getLocator();
			if ( locator == null )
			{
				if ( pi.getSchemaComponent() != null )
					locator = pi.getSchemaComponent().getLocator();
				if ( locator == null )
				{
					if ( (pi.parent() != null) && (pi.parent().getLocator() != null) )
						locator = pi.parent().getLocator();
				}
			}
		}
		return locator;
	}
	
	public static Locator getLocator(CPropertyInfo pi, CClassInfo ci)
	{
		Locator locator = getLocator(pi);
		if ( locator == null )
			locator = getLocator(ci);
		return locator;
	}
	
	public static Locator getLocator(ElementOutline eo)
	{
		Locator locator = null;
		if ( eo != null )
		{
			if ( eo.getTarget() != null )
				locator = getLocator(eo.getTarget());
			else if ( eo.getImplClass() != null )
				locator = getLocator(eo.getImplClass());
		}
		return locator;
	}
	
	public static Locator getLocator(CPluginCustomization cpc)
	{
		return (cpc != null) ? cpc.locator : null;
	}
	
	public static Locator getLocator(CPluginCustomization cpc, ElementOutline eo)
	{
		Locator locator = getLocator(cpc);
		if ( locator == null )
			locator = getLocator(eo);
		return locator;
	}
	
	public static Locator getLocator(CPluginCustomization cpc, ClassOutline co)
	{
		Locator locator = getLocator(cpc);
		if ( locator == null )
			locator = getLocator(co);
		return locator;
	}
	
	public static Locator getLocator(CPluginCustomization cpc, FieldOutline fo)
	{
		Locator locator = getLocator(cpc);
		if ( locator == null )
			locator = getLocator(fo);
		return locator;
	}
	
	public static Locator getLocator(CPluginCustomization cpc, EnumOutline eo)
	{
		Locator locator = getLocator(cpc);
		if ( locator == null )
			locator = getLocator(eo);
		return locator;
	}

	public static String toLocation(Locator locator)
	{
		return StringUtils.toLocation(locator);
	}
	
	public static String toLocation(Object metadata)
	{
		return StringUtils.toLocation(getLocator(metadata));
	}
	
	public static String toLocation(Object metadata, int maxIdSize)
	{
		return StringUtils.toLocation(getLocator(metadata), maxIdSize);
	}
	
	public static String toLocation(ClassOutline classOutline)
	{
		return toLocation(getLocator(classOutline));
	}
	
	public static String toLocation(CPluginCustomization cpc, ClassOutline co)
	{
		return toLocation(getLocator(cpc, co));
	}
	
	public static String toLocation(CPluginCustomization cpc, FieldOutline fo)
	{
		return toLocation(getLocator(cpc, fo));
	}
	
	public static String toLocation(CPluginCustomization cpc, EnumOutline eo)
	{
		return toLocation(getLocator(cpc, eo));
	}

	public static String toLocation(CPluginCustomization cpc)
	{
		return toLocation(getLocator(cpc));
	}

	public static String toLocation(CClassInfo ci)
	{
		return toLocation(getLocator(ci));
	}
	
	public static String toLocation(FieldOutline fieldOutline)
	{
		return toLocation(getLocator(fieldOutline));
	}
	
	public static String toLocation(CPropertyInfo pi)
	{
		return toLocation(pi, null);
	}
	
	public static String toLocation(CPropertyInfo pi, CClassInfo ci)
	{
		return toLocation(getLocator(pi, ci));
	}

	public static String toLocation(ElementOutline eo)
	{
		return toLocation(getLocator(eo));
	}
	
	public static String toLocation(CPluginCustomization cpc, ElementOutline eo)
	{
		return toLocation(getLocator(cpc, eo));
	}
}
