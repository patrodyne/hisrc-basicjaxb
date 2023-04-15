package org.jvnet.basicjaxb.xjc;

/**
 * <p>
 * Class defined for safe calls of getClassLoader methods of any kind
 * (context/system/class) classloader. This MUST be package private and
 * defined in every package which uses such invocations.
 * </p>
 * 
 * <p>
 * @deprecated This class is only useful in conjunction with {@link SecurityManager},
 * which is deprecated and subject to removal in a future release.  Consequently, 
 * this class is also deprecated and subject to removal. There is no replacement 
 * for the {@link SecurityManager} or this class.
 * </p>
 * 
 * <p>
 * See <a href="https://openjdk.java.net/jeps/411">JEP 411</a> for discussion
 * and alternatives.
 * </p>
 * 
 * @author snajper
 */
@Deprecated(since="17", forRemoval=true)
class SecureLoader
{
	static ClassLoader getContextClassLoader()
	{
		if (System.getSecurityManager() == null)
		{
			return Thread.currentThread().getContextClassLoader();
		}
		else
		{
			return java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<>()
			{
				@Override
				public ClassLoader run()
				{
					return Thread.currentThread().getContextClassLoader();
				}
			});
		}
	}

	static ClassLoader getClassClassLoader(final Class<?> c)
	{
		if (System.getSecurityManager() == null)
		{
			return c.getClassLoader();
		}
		else
		{
			return java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<>()
			{
				@Override
				public ClassLoader run()
				{
					return c.getClassLoader();
				}
			});
		}
	}

	static ClassLoader getSystemClassLoader()
	{
		if (System.getSecurityManager() == null)
		{
			return ClassLoader.getSystemClassLoader();
		}
		else
		{
			return java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<>()
			{
				@Override
				public ClassLoader run()
				{
					return ClassLoader.getSystemClassLoader();
				}
			});
		}
	}

	static void setContextClassLoader(final ClassLoader cl)
	{
		if (System.getSecurityManager() == null)
		{
			Thread.currentThread().setContextClassLoader(cl);
		}
		else
		{
			java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<ClassLoader>()
			{
				@Override
				public ClassLoader run()
				{
					Thread.currentThread().setContextClassLoader(cl);
					return null;
				}
			});
		}
	}

	static ClassLoader getParentClassLoader(final ClassLoader cl)
	{
		if (System.getSecurityManager() == null)
		{
			return cl.getParent();
		}
		else
		{
			return java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<>()
			{
				@Override
				public ClassLoader run()
				{
					return cl.getParent();
				}
			});
		}
	}
}
