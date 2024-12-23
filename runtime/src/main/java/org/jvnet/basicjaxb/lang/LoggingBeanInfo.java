package org.jvnet.basicjaxb.lang;

import java.beans.SimpleBeanInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a logging support class to make it easier for people to provide
 * BeanInfo classes.
 * 
 * <p>
 * It defaults to providing {@link SimpleBeanInfo} information, and can be
 * selectively overridden to provide more explicit information on chosen topics.
 * </p>
 */
public class LoggingBeanInfo extends SimpleBeanInfo
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	public Logger getLogger() { return logger; }
	
	/** Default constructor */
	public LoggingBeanInfo()
	{
		super();
	}
}
