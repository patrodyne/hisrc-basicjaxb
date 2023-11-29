package org.jvnet.basicjaxb.plugin.wildcard;

import static com.sun.tools.xjc.model.nav.NavigatorImpl.create;

import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;

import org.glassfish.jaxb.core.v2.model.core.ID;
import org.glassfish.jaxb.core.v2.model.core.PropertyKind;
import org.glassfish.jaxb.core.v2.model.core.ReferencePropertyInfo;
import org.glassfish.jaxb.core.v2.model.core.WildcardMode;

import com.sun.tools.xjc.model.CAdapter;
import com.sun.tools.xjc.model.CElement;
import com.sun.tools.xjc.model.CPropertyInfo;
import com.sun.tools.xjc.model.CPropertyVisitor;
import com.sun.tools.xjc.model.CPropertyVisitor2;
import com.sun.tools.xjc.model.CReferencePropertyInfo;
import com.sun.tools.xjc.model.CTypeInfo;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;

import jakarta.activation.MimeType;
import jakarta.xml.bind.annotation.DomHandler;

/**
 * An implementation of {@link ReferencePropertyInfo} that delegates to a {@link CReferencePropertyInfo} instance
 * plus implements a configurable {@link DomHandler} property.
 */
public class CReferencePropertyInfoPlus extends CPropertyInfo implements ReferencePropertyInfo<NType,NClass> {

	// Reference to {@link CReferencePropertyInfo} instance for delegation.
	private CReferencePropertyInfo refPropInfo = null;
	public CReferencePropertyInfo getRefPropInfo()
	{
		return refPropInfo;
	}
	public void setRefPropInfo(CReferencePropertyInfo refPropInfo)
	{
		this.refPropInfo = refPropInfo;
	}

	/**
	 * Construct by delegating to a {@link CReferencePropertyInfo} instance.
	 * 
	 * @param refPropInfo A {@link CReferencePropertyInfo} instance.
	 */
	public CReferencePropertyInfoPlus(CReferencePropertyInfo refPropInfo)
	{	
		super(refPropInfo.getName(true), refPropInfo.isCollection(),
			refPropInfo.getSchemaComponent(), refPropInfo.getCustomizations(),
			refPropInfo.getLocator());
		setRefPropInfo(refPropInfo);
	}

	@Override
	public Set<? extends CTypeInfo> ref()
	{
		return getRefPropInfo().ref();
	}

	@Override
	public Set<CElement> getElements()
	{
		return getRefPropInfo().getElements();
	}

	@Override
	public boolean isMixed()
	{
		return getRefPropInfo().isMixed();
	}

	public boolean isDummy()
	{
		return getRefPropInfo().isDummy();
	}

	public boolean isContent()
	{
		return getRefPropInfo().isContent();
	}

	public boolean isMixedExtendedCust()
	{
		return getRefPropInfo().isMixedExtendedCust();
	}

	@Deprecated
	@Override
	public QName getXmlName()
	{
		return getRefPropInfo().getXmlName();
	}

	@Override
	public boolean isUnboxable()
	{
		return getRefPropInfo().isUnboxable();
	}

	// the same as above
	@Override
	public boolean isOptionalPrimitive()
	{
		return getRefPropInfo().isOptionalPrimitive();
	}

	@Override
	public <V> V accept(CPropertyVisitor<V> visitor)
	{
		return visitor.onReference(getRefPropInfo());
	}

	@Override
	public <R, P> R accept(CPropertyVisitor2<R, P> visitor, P p)
	{
		return visitor.visit(getRefPropInfo(), p);
	}

	@Override
	public CAdapter getAdapter()
	{
		return getRefPropInfo().getAdapter();
	}

	@Override
	public PropertyKind kind()
	{
		return getRefPropInfo().kind();
	}

	@Override
	public ID id()
	{
		return getRefPropInfo().id();
	}

	@Override
	public WildcardMode getWildcard()
	{
		return getRefPropInfo().getWildcard();
	}

	public void setWildcard(WildcardMode mode)
	{
		getRefPropInfo().setWildcard(mode);
	}

	private NClass domHandler = null;
	@Override
	public NClass getDOMHandler()
	{
		if ( domHandler == null )
			return getRefPropInfo().getDOMHandler();
		else
			return domHandler;
	}
	public void setDOMHandler(NClass domHandler)
	{
		this.domHandler = domHandler;
	}
	public void setDOMHandler(Class<?> domHandlerClass)
	{
		setDOMHandler(create(domHandlerClass));
	}
	public void setDOMHandler(String domHandlerClassName)
	{
		if ( (domHandlerClassName != null) && !domHandlerClassName.isEmpty() )
		{
			try
			{
				Class<?> domHandlerClass = Class.forName(domHandlerClassName);
				setDOMHandler(domHandlerClass);
			}
			catch (ClassNotFoundException | LinkageError ex)
			{
				setDOMHandler(new LazyNClass(domHandlerClassName));
			}
		}
	}
   
	@Override
	public MimeType getExpectedMimeType()
	{
		return getRefPropInfo().getExpectedMimeType();
	}
	
	@Override
	public boolean isCollectionNillable()
	{
		return getRefPropInfo().isCollectionNillable();
	}

	@Override
	public boolean isCollectionRequired()
	{
		return getRefPropInfo().isCollectionRequired();
	}

	@Override
	public QName getSchemaType()
	{
		return getRefPropInfo().getSchemaType();
	}

	@Override
	public boolean isRequired()
	{
		return getRefPropInfo().isRequired();
	}

	@Override
	public QName collectElementNames(Map<QName, CPropertyInfo> table)
	{
		return getRefPropInfo().collectElementNames(table);
	}
}
