package org.swixml.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @see <a href="file:../package-info.java">LICENSE: package-info</a>
 * 
 * @author bsorrentino
 */
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target(ElementType.FIELD)
public @interface SchemaAware
{
}
