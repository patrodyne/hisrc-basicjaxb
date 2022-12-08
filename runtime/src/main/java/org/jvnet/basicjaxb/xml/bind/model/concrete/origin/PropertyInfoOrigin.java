package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import org.jvnet.basicjaxb.xml.bind.model.MSourced;

import org.glassfish.jaxb.core.v2.model.core.PropertyInfo;

/**
 * PropertyInfoOrigin extends MSourced.
 * 
 * @param <T> The underlying Java type that object represents.
 * @param <C> The declaration class the ClassInfo object is wrapping.
 * @param <PI> Property Information about a JAXB-bound property.
 */
public interface PropertyInfoOrigin<T, C, PI extends PropertyInfo<T, C>> extends MSourced<PI>
{
}
