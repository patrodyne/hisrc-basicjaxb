package org.jdesktop.application;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @author Hans Muller (Hans.Muller@Sun.COM)
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ProxyActions
{
	String[] value() default {};
}
