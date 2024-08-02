package org.jdesktop.application;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @author Hans Muller (Hans.Muller@Sun.COM)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Resource
{
	String key() default "";
}
