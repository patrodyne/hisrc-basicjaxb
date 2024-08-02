package org.jdesktop.application;

import java.io.File;
import java.security.PrivilegedAction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Access to per application, per user, local file storage.
 * 
 * @see ApplicationContext#getLocalStorage
 * @see SessionStorage
 * @see <a href="file:package-info.java">LICENSE: package-info</a>
 * 
 * @author Hans Muller (Hans.Muller@Sun.COM)
 */
public class LocalStorage extends AbstractBean
{
	private static final Logger logger = LoggerFactory.getLogger(LocalStorage.class);
	private final ApplicationContext context;
	private long storageLimit = -1L;
	private final File unspecifiedFile = new File("unspecified");
	private File directory = unspecifiedFile;

	protected LocalStorage(ApplicationContext context)
	{
		if ( context == null )
		{
			throw new IllegalArgumentException("null context");
		}
		this.context = context;
	}

	protected final ApplicationContext getContext()
	{
		return context;
	}

	public long getStorageLimit()
	{
		return storageLimit;
	}

	public void setStorageLimit(long storageLimit)
	{
		if ( storageLimit < -1L )
		{
			throw new IllegalArgumentException("invalid storageLimit");
		}
		long oldValue = this.storageLimit;
		this.storageLimit = storageLimit;
		firePropertyChange("storageLimit", oldValue, this.storageLimit);
	}

	private String getId(String key, String def)
	{
		ResourceMap appResourceMap = getContext().getResourceMap();
		String id = appResourceMap.getString(key);
		if ( id == null )
		{
			logger.warn("unspecified resource " + key + " using " + def);
			id = def;
		}
		else if ( id.trim().length() == 0 )
		{
			logger.warn("empty resource " + key + " using " + def);
			id = def;
		}
		return id;
	}

	private String getApplicationId()
	{
		return getId("Application.id", getContext().getApplicationClass().getSimpleName());
	}

	private String getVendorId()
	{
		return getId("Application.vendorId", "UnknownApplicationVendor");
	}

	/*
	 * The following enum and method only exist to distinguish Windows and OSX
	 * for the sake of getDirectory().
	 */
	private enum OSId
	{
		WINDOWS, OSX, UNIX
	}

	private OSId getOSId()
	{
		PrivilegedAction<String> doGetOSName = new PrivilegedAction<String>()
		{
			@Override
			public String run()
			{
				return System.getProperty("os.name");
			}
		};
		OSId id = OSId.UNIX;
		String osName = doGetOSName.run();
		if ( osName != null )
		{
			if ( osName.toLowerCase().startsWith("mac os x") )
			{
				id = OSId.OSX;
			}
			else if ( osName.contains("Windows") )
			{
				id = OSId.WINDOWS;
			}
		}
		return id;
	}

	public File getDirectory()
	{
		if ( directory == unspecifiedFile )
		{
			directory = null;
			String userHome = null;
			try
			{
				userHome = System.getProperty("user.home");
			}
			catch (SecurityException ignore)
			{
			}
			if ( userHome != null )
			{
				String applicationId = getApplicationId();
				OSId osId = getOSId();
				if ( osId == OSId.WINDOWS )
				{
					File appDataDir = null;
					try
					{
						String appDataEV = System.getenv("APPDATA");
						if ( (appDataEV != null) && (appDataEV.length() > 0) )
						{
							appDataDir = new File(appDataEV);
						}
					}
					catch (SecurityException ignore)
					{
					}
					String vendorId = getVendorId();
					if ( (appDataDir != null) && appDataDir.isDirectory() )
					{
						// ${APPDATA}\{vendorId}\${applicationId}
						String path = vendorId + "\\" + applicationId + "\\";
						directory = new File(appDataDir, path);
					}
					else
					{
						// ${userHome}\Application
						// Data\${vendorId}\${applicationId}
						String path = "Application Data\\" + vendorId + "\\" + applicationId + "\\";
						directory = new File(userHome, path);
					}
				}
				else if ( osId == OSId.OSX )
				{
					// ${userHome}/Library/Application Support/${applicationId}
					String path = "Library/Application Support/" + applicationId + "/";
					directory = new File(userHome, path);
				}
				else
				{
					// ${userHome}/.${applicationId}/
					String path = "." + applicationId + "/";
					directory = new File(userHome, path);
				}
			}
		}
		return directory;
	}

	public void setDirectory(File directory)
	{
		File oldValue = this.directory;
		this.directory = directory;
		firePropertyChange("directory", oldValue, this.directory);
	}
}
