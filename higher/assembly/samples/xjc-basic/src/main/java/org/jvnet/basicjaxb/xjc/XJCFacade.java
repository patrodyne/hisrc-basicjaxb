package org.jvnet.basicjaxb.xjc;

import java.io.Closeable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A driver to invoke XJC1 or XJC2 depending on the command line switch.
 */
public final class XJCFacade
{
	private static Logger logger = LoggerFactory.getLogger(XJCFacade.class);
	private static Logger getLogger() { return logger; }
	
	private static final String JDK_REQUIRED = "XJC requires Java SE 11 or later.";

	public static String parseVersion(String version)
	{
		// no other versions supported as of now
		return "3.0";
	}
	
	/**
	 * No arg constructor
	 */
	private XJCFacade()
	{
		// Seal this class.
	}

	/**
	 * Command line invocation.
	 * 
	 * @param args The XJC CLI options.
	 * 
	 * @throws Throwable When XML Schema cannot generate Java source files.
	 */
	public static void main(String[] args)
		throws Throwable
	{
		@SuppressWarnings("unused")
		String version = "3.0"; // by default, we go 3.0
		for (int i = 0; i < args.length; i++)
		{
			if (args[i].equals("-source"))
			{
				if (i + 1 < args.length)
					version = parseVersion(args[i + 1]);
			}
		}
		getLogger().debug("Using JAXB version {}", version);
		
		ClassLoader oldContextCl = getContextClassLoader();
		try
		{
			ClassLoader secureClassLoader = useSecureClassLoader();
			Class<?> driver = secureClassLoader.loadClass("com.sun.tools.xjc.Driver");
			Method mainMethod = driver.getDeclaredMethod("main", String[].class);
			try
			{
				mainMethod.invoke(null, new Object[] { args });
			}
			catch (InvocationTargetException e)
			{
				if (e.getTargetException() != null)
					throw e.getTargetException();
			}
		}
		catch (UnsupportedClassVersionError e)
		{
			getLogger().error(JDK_REQUIRED);
		}
		finally
		{
			ClassLoader curContextCL = getSecureClassLoader(oldContextCl);
			// close/cleanup all classLoaders but the one which loaded this class
			while (curContextCL != null && !oldContextCl.equals(curContextCL))
			{
				if (curContextCL instanceof Closeable)
				{
					// JDK7+, ParallelWorldClassLoader
					((Closeable) curContextCL).close();
				}
				curContextCL = getParentClassLoader(curContextCL);
			}
		}
	}

	/**
	 * This method is only useful in conjunction with {@link SecurityManager},
	 * which is deprecated and subject to removal in a future release.
	 * 
	 * @param curContextCL The current context class loader.
	 * 
	 * @return The parent secure class loader.
	 */
	@SuppressWarnings("removal")
	private static ClassLoader getParentClassLoader(ClassLoader curContextCL)
	{
		return SecureLoader.getParentClassLoader(curContextCL);
	}

	/**
	 * This method is only useful in conjunction with {@link SecurityManager},
	 * which is deprecated and subject to removal in a future release.
	 * 
	 * @return A secure class loader.
	 */
	@SuppressWarnings("removal")
	private static ClassLoader getContextClassLoader()
	{
		return SecureLoader.getContextClassLoader();
	}

	/**
	 * This method is only useful in conjunction with {@link SecurityManager},
	 * which is deprecated and subject to removal in a future release.
	 * 
	 * @return A secure class loader.
	 */
	@SuppressWarnings("removal")
	private static ClassLoader useSecureClassLoader()
	{
		ClassLoader cl = SecureLoader.getClassClassLoader(XJCFacade.class);
		SecureLoader.setContextClassLoader(cl);
		return cl;
	}

	/**
	 * This method is only useful in conjunction with {@link SecurityManager},
	 * which is deprecated and subject to removal in a future release.
	 * 
	 * @param oldContext The old context class loader.
	 * 
	 * @return The current context class loader.
	 */
	@SuppressWarnings("removal")
	private static ClassLoader getSecureClassLoader(ClassLoader oldContext)
	{
		ClassLoader curContext = SecureLoader.getContextClassLoader();
		SecureLoader.setContextClassLoader(oldContext);
		return curContext;
	}
}
