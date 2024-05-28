package org.jvnet.basicjaxb.testing;

import static jakarta.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;
import static java.util.Arrays.sort;
import static javax.xml.catalog.CatalogFeatures.Feature.FILES;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * A test harness to test XML sample files in a JAXB context.
 */
public abstract class AbstractSamplesTest
{
	public static final String DEFAULT_SAMPLES_DIRECTORY_NAME = "src/test/samples";
	
	// Represents the Logger for this class and sub-classes.
	private Logger logger = LoggerFactory.getLogger(getTestClass());
	protected Logger getLogger() { return logger; }
	
	private Boolean failFast = false;
	public Boolean isFailFast() { return failFast; }
	public void setFailFast(Boolean failFast) { this.failFast = failFast; }

	/**
	 * Get this class or configure a test class by override.
	 * 
	 * @return The test class.
	 */
	protected Class<? extends Object> getTestClass()
	{
		return getClass();
	}
	
	// Enable OASIS DTD Entity Resolution XML Catalog
	private URI catalogURI;
	public URI getCatalogURI() { return catalogURI; }
	public void setCatalogURI(URI catalogURI) { this.catalogURI = catalogURI; }
	
	/**
	 * Get the JAXB context path or configure a path by override.
	 * 
	 * @return The JAXB context path.
	 */
	protected String getContextPath()
	{
		return getTestClass().getPackage().getName();
	}

	/**
	 * Get the default samples directory name or configure by override.
	 * 
	 * @return The sample directory name.
	 */
	protected String getSamplesDirectoryName()
	{
		return DEFAULT_SAMPLES_DIRECTORY_NAME;
	}

	/**
	 * Get the base directory for the configured test class.
	 * 
	 * @return The base directory for the configured test class.
	 */
	protected File getBaseDir()
	{
		try
		{
			return getMavenProjectDir(getTestClass());
		}
		catch (Exception ex)
		{
			throw new AssertionError(ex);
		}
	}
	
	/**
	 * Get the samples directory file for the configured base directory and samples directory name.
	 * 
	 * @return The samples directory file for the configured base directory and samples directory name.
	 */
	protected File getSamplesDirectory()
	{
		return new File(getBaseDir(), getSamplesDirectoryName());
	}

	/**
	 * Get the sorted array of sample file(s).
	 * 
	 * @return The sorted array of sample file(s).
	 */
	protected File[] getSampleFiles()
	{
		File samplesDirectory = getSamplesDirectory();
		getLogger().debug("Sample directory [" + samplesDirectory.getAbsolutePath() + "].");
		final Collection<File> files = FileUtils.listFiles(samplesDirectory, new String[] { "xml" }, true);
		File[] fileArray = files.toArray(new File[files.size()]);
		sort(fileArray);
		return fileArray;
	}

	// Represents a map of sample file(s) by file name(s).
	private Map<String, File> sampleMap = null;
	protected Map<String, File> getSampleMap()
	{
		if ( sampleMap == null )
		{
			setSampleMap(new HashMap<>());
			for ( File sampleFile : getSampleFiles() )
				getSampleMap().put(sampleFile.getName(), sampleFile);
		}
		return sampleMap;
	}
	protected void setSampleMap(Map<String, File> sampleMap)
	{
		this.sampleMap = sampleMap;
	}

	/**
	 * Get the JAXB context class loader for the configured test class.
	 * 
	 * @return The JAXB context class loader for the configured test class.
	 */
	protected ClassLoader getContextClassLoader()
	{
		return getTestClass().getClassLoader();
	}

	/**
	 * Configure a map of JAXB context properties.
	 * 
	 * @return A map of JAXB context properties.
	 */
	protected Map<String, ?> getContextProperties()
	{
		return null;
	}

	/**
	 * Create a JAXB context for the configured class path.
	 * 
	 * @return A new instance of a {@link JAXBContext}.
	 * 
	 * @throws JAXBException When a {@link JAXBContext} cannot be created.
	 */
	public JAXBContext createContext()
		throws JAXBException
	{
		final String contextPath = getContextPath();
		final ClassLoader classLoader = getContextClassLoader();
		final Map<String, ?> properties = getContextProperties();
		if (classLoader == null)
			return JAXBContext.newInstance(contextPath);
		else
		{
			if (properties == null)
				return JAXBContext.newInstance(contextPath, classLoader);
			else
				return JAXBContext.newInstance(contextPath, classLoader, properties);
		}
	}

	// Represents the {@link JAXBContext}.
	private JAXBContext jaxbContext;
	protected JAXBContext getJaxbContext() throws JAXBException
	{
		if ( jaxbContext == null )
			setJaxbContext(createContext());
		return jaxbContext;
	}
	protected void setJaxbContext(JAXBContext jaxbContext)
	{
		this.jaxbContext = jaxbContext;
	}

	// Represents a {@link DocumentBuilderFactory} with support for catalogs.
	private DocumentBuilderFactory documentBuilderFactory = null;
	public DocumentBuilderFactory getDocumentBuilderFactory()
	{
		if ( documentBuilderFactory == null )
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    dbf.setNamespaceAware(true);
			if ( getCatalogURI() != null )
				   dbf.setAttribute(FILES.getPropertyName(), getCatalogURI().toString());
			setDocumentBuilderFactory(dbf);
		}
		return documentBuilderFactory;
	}
	public void setDocumentBuilderFactory(DocumentBuilderFactory documentBuilderFactory)
	{
		this.documentBuilderFactory = documentBuilderFactory;
	}
	
	// Represents the JAXB {@link Unmarshaller}.
	private Unmarshaller unmarshaller = null;
	protected Unmarshaller getUnmarshaller() throws JAXBException
	{
		if ( unmarshaller == null )
			setUnmarshaller(getJaxbContext().createUnmarshaller());
		return unmarshaller;
	}
	protected void setUnmarshaller(Unmarshaller unmarshaller)
	{
		this.unmarshaller = unmarshaller;
	}

	// Represents the JAXB {@link Marshaller}.
	private Marshaller marshaller;
	protected Marshaller getMarshaller() throws JAXBException
	{
		if ( marshaller == null )
		{
			setMarshaller(getJaxbContext().createMarshaller());
			getMarshaller().setProperty(JAXB_FORMATTED_OUTPUT, true);
		}
		return marshaller;
	}
	protected void setMarshaller(Marshaller marshaller)
	{
		this.marshaller = marshaller;
	}

	/**
	 * An abstract method to be implements by the sub-class.
	 * The implementor should read the given sample file and assert expectations.
	 * 
	 * @param sample A sample file to be tested.
	 * 
	 * @throws Exception When the test aborts.
	 */
	protected abstract void checkSample(File sample)
		throws Exception;

	/**
	 * Scan the samples path for all file(s) and call the <code>checkSample(sampleFile)</code>
	 * method. Count the failures, if any, and assert a summary.
	 * 
	 * @throws Exception When the <code>checkSample(sampleFile)</code> aborts.
	 */
	@Test
	public void testSamples()
		throws Exception
	{
		getLogger().debug("Testing samples, start");
		int failed = 0;
		final File[] sampleFiles = getSampleFiles();
		for (final File sampleFile : sampleFiles)
		{
			getLogger().debug("Testing sample, start [" + sampleFile.getName() + "].");
			String result = "SUCCESS";
			try
			{
				checkSample(sampleFile);
			}
			catch (Throwable ex)
			{
				getLogger().error("Testing sample [" + sampleFile.getName() + "] failed the check.", ex);
				failed++;
				result = "FAILURE";
				if ( isFailFast() )
					throw ex;
			}
			getLogger().info("Testing sample, " + result + " [" + sampleFile.getName() + "].");
		}
		getLogger().debug("Testing samples, finish");
		String summary = "Testing summary [" + failed + "/" + sampleFiles.length + "] failed";
		
		// logger.info(summary);
		assertTrue(failed == 0, summary + " the check. Check previous errors for details.");
	}
	
    /**
     * Get the Maven project directory for a given target class.
     * 
	 * <p><b>Strategy:</b> Start with any project class and get the code source for its
	 * protection domain. For standard Maven projects, the code source is
	 * <code>"target/project-classes"</code> which is two sub-directories below the project
	 * directory. <em>Note:</em> The original project class can be at any package depth.</p>
     * 
     * @param targetClass A Maven project target class.
     * 
     * @return The Maven project directory.
     * 
     * @throws URISyntaxException When the @{link CodeSource} location cannot be resolved.
     */
	public static File getMavenProjectDir(Class<?> targetClass) throws URISyntaxException
	{
		ProtectionDomain targetProtectionDomain = targetClass.getProtectionDomain();
		CodeSource targetCodeSource = targetProtectionDomain.getCodeSource();
		File targetClassesDir = new File(targetCodeSource.getLocation().toURI());
		File targetClassesDirParentFile = targetClassesDir.getParentFile();
		File targetClassesDirParentParentFile = targetClassesDirParentFile.getParentFile();
		File targetClassesDirAbsoluteParentParentFile = targetClassesDirParentParentFile.getAbsoluteFile();
		return targetClassesDirAbsoluteParentParentFile;
	}
	
	// JAXB Context Methods

	/**
	 * Use the current {@link Unmarshaller} to unmarshal the given {@link File}.
	 * 
	 * @param <T> The type of the declared class.
	 * @param xmlFileName The file name to be read for unmarshalling.
	 * 
	 * @return An instance representaion of the contents of the given {@link File}.
	 * 
	 * @throws JAXBException When the instance cannot be marshaled to a {@link File}.
	 */
	protected <T> T unmarshal(String xmlFileName) throws JAXBException
	{
		File xmlFile = new File(xmlFileName);
		return unmarshal(xmlFile);
	}
	
	/**
	 * Use the current {@link Unmarshaller} to unmarshal the given {@link File}.
	 * 
	 * @param <T> The type of the declared class.
	 * @param xmlFile The {@link File} to be read for unmarshalling.
	 * 
	 * @return An instance representaion of the contents of the given {@link File}.
	 * 
	 * @throws JAXBException When the instance cannot be marshaled to a {@link File}.
	 */
	protected <T> T unmarshal(File xmlFile) throws JAXBException
	{
		return unmarshal(xmlFile, null);
	}
	
	/**
	 * Use the current {@link Unmarshaller} to unmarshal the given {@link File}
	 * and {@link Class} declaration to a Java object.
	 * 
	 * @param <T> The type of the declared class.
	 * @param xmlFileName The file name to be read for unmarshalling.
	 * @param clazz The declared class type expected.
	 * 
	 * @return An instance representaion of the contents of the given {@link File}.
	 * 
	 * @throws JAXBException When the instance cannot be marshaled to a {@link File}.
	 */
	protected <T> T unmarshal(String xmlFileName, Class<T> clazz) throws JAXBException
	{
		File xmlFile = new File(xmlFileName);
		return unmarshal(xmlFile, clazz);
	}
	
	/**
	 * Use the current {@link Unmarshaller} to unmarshal the given {@link File}
	 * and {@link Class} declaration to a Java object.
	 * 
	 * <p>This implementation uses a {@link DocumentBuilderFactory} with
	 * support for an OASIS catalog file.</p>
	 * 
	 * @param <T> The type of the declared class.
	 * @param xmlFile The {@link File} to be read for unmarshalling.
	 * @param clazz The declared class type expected.
	 * 
	 * @return An instance representaion of the contents of the given {@link File}.
	 * 
	 * @throws JAXBException When the instance cannot be marshaled to a {@link File}.
	 */
	@SuppressWarnings("unchecked")
	protected <T> T unmarshal(File xmlFile, Class<T> clazz) throws JAXBException
	{
		try
		{
			DocumentBuilder db = getDocumentBuilderFactory().newDocumentBuilder();
			Document doc = db.parse(xmlFile);

			Object result = null;
			if ( clazz != null )
				result = getUnmarshaller().unmarshal(doc, clazz);
			else
				result = getUnmarshaller().unmarshal(doc);
			
			if ( result instanceof JAXBElement )
				return ((JAXBElement<T>) result).getValue();
			else
				return (T) result;
		}
		catch (Exception ex)
		{
			throw new JAXBException("unmarshal: "+xmlFile, ex);
		}
	}
	
	/**
     * Use the current {@link Marshaller} to marshal the given instance
     * to a {@link File} with the given name.
	 * 
     * @param instance The object to be marshaled.
	 * @param xmlFileName The name of the file to be created.
	 * 
	 * @throws JAXBException When the instance cannot be marshaled to a {@link File}.
	 */
    protected void marshal(Object instance, String xmlFileName) throws JAXBException
    {
		File xmlFile = new File(xmlFileName);
    	try
    	{
	        if ( instance != null)
                getMarshaller().marshal(instance, xmlFile);
    	}
		catch (Exception ex)
		{
			throw new JAXBException("marshal: " + xmlFileName, ex);
		}
    }

    /**
     * Use the current {@link Marshaller} to marshal the given instance
     * to a {@link String}.
     * 
     * @param instance The object to be marshaled.
     * 
     * @return An XML {@link String} representation of the instance.
     * 
	 * @throws JAXBException When the instance cannot be marshaled to a {@link String}.
     */
    protected String marshalToString(Object instance) throws JAXBException
    {
    	try
    	{
	        String xml = null;
	        if ( instance != null)
	        {
	            try ( StringWriter writer = new StringWriter() )
	            {
	                getMarshaller().marshal(instance, writer);
	                xml = writer.toString();
	            }
	        }
	        return xml;
    	}
		catch (Exception ex)
		{
			throw new JAXBException("marshalToString: "+instance, ex);
		}
    }
}
