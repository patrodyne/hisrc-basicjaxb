package org.jvnet.basicjaxb.plugin;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.writer.FilterCodeWriter;

public class LoggingCodeWriter extends FilterCodeWriter
{
	private Logger logger = LoggerFactory.getLogger(LoggingCodeWriter.class);
    public Logger getLogger() { return logger; }
    public void setLogger(Logger logger) { this.logger = logger; }
    
	private boolean verbose;
    public boolean isVerbose() { return verbose; }
	public void setVerbose(boolean verbose) { this.verbose = verbose; }

	public LoggingCodeWriter(CodeWriter output, Logger logger, boolean verbose)
	{
		super(output);
		setLogger(logger);
		setVerbose(verbose);
	}

	public Writer openSource(JPackage pkg, String fileName)
		throws IOException
	{
		if (isVerbose())
		{
			if (pkg.isUnnamed())
				getLogger().info("XJC writing: " + fileName);
			else
				getLogger().info("XJC writing: " + pkg.name().replace('.', File.separatorChar) + File.separatorChar + fileName);
		}
		return core.openSource(pkg, fileName);
	}

	public OutputStream openBinary(JPackage pkg, String fileName)
		throws IOException
	{
		if (isVerbose())
		{
			if (pkg.isUnnamed())
				getLogger().info("XJC writing: " + fileName);
			else
				getLogger().info("XJC writing: " + pkg.name().replace('.', File.separatorChar) + File.separatorChar + fileName);
		}
		return core.openBinary(pkg, fileName);
	}

	@Override
	public void close()
		throws IOException
	{
		core.close();
	}
}
