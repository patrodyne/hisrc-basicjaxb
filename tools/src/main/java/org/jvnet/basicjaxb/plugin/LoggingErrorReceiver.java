package org.jvnet.basicjaxb.plugin;

import org.jvnet.basicjaxb.locator.util.LocatorBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXParseException;

import com.sun.tools.xjc.ErrorReceiver;

public class LoggingErrorReceiver extends ErrorReceiver
{
	private Logger logger = LoggerFactory.getLogger(LoggingErrorReceiver.class);
    public Logger getLogger() { return logger; }
    public void setLogger(Logger logger) { this.logger = logger; }
	
	private boolean verbose = false;
    public boolean isVerbose() { return verbose; }
	public void setVerbose(boolean verbose) { this.verbose = verbose; }
	
	private String messagePrefix = "ERROR";
	public String getMessagePrefix() { return messagePrefix; }
	public void setMessagePrefix(String messagePrefix) { this.messagePrefix = messagePrefix; }
	
	public LoggingErrorReceiver()
	{
	}
	
	public LoggingErrorReceiver(Logger logger)
	{
		setLogger(logger);
	}
	
	public LoggingErrorReceiver(String messagePrefix)
	{
		setMessagePrefix(messagePrefix);
	}
	
	public LoggingErrorReceiver(String messagePrefix, Logger logger)
	{
		setMessagePrefix(messagePrefix);
		setLogger(logger);
	}
	
	public LoggingErrorReceiver(String messagePrefix, Logger logger, boolean verbose)
	{
		setMessagePrefix(messagePrefix);
		setLogger(logger);
		setVerbose(verbose);
	}

	public void warning(SAXParseException saxex)
	{
		if (isVerbose())
			getLogger().warn(getMessage(saxex), saxex);
		else
		{
			if ( saxex.getMessage().contains("experimental") )
				getLogger().warn("Current configuration is experimental!");
			else
			{
				getLogger().warn(saxex.getMessage());
				getLogger().warn(getMessage(saxex));
			}
		}
	}

	public void error(SAXParseException saxex)
	{
		getLogger().error(getMessage(saxex), saxex);
	}

	public void fatalError(SAXParseException saxex)
	{
		getLogger().error(getMessage(saxex), saxex);
	}

	public void info(SAXParseException saxex)
	{
		if (isVerbose())
			getLogger().info(getMessage(saxex));
	}

	private String getMessage(SAXParseException ex)
	{
		final String pub = ex.getPublicId();
		final String sys = ex.getSystemId();
		final int row = ex.getLineNumber();
		final int col = ex.getColumnNumber();
		LocatorBean locatorBean = new LocatorBean(pub, sys, row, col);
		return getMessagePrefix() + " Location " + locatorBean + ".";
	}
}
