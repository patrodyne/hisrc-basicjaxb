package org.jvnet.basicjaxb.xml.bind.model.concrete.origin;

import org.glassfish.jaxb.core.v2.model.core.ClassInfo;
import org.jvnet.basicjaxb.xml.bind.model.MSourced;

/**
 * ClassInfoOrigin extends MSourced.
 * 
 * @param <T> The underlying Java type that object represents.
 * @param <C> The declaration class the ClassInfo object is wrapping.
 * @param <CI> Class Information about JAXB-bound class.
 */
public interface ClassInfoOrigin<T, C, CI extends ClassInfo<T, C>> extends MSourced<CI>
{
}
