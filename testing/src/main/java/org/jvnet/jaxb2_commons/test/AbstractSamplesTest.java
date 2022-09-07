package org.jvnet.jaxb2_commons.test;

import static java.util.Arrays.sort;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSamplesTest {

	protected Logger logger = LoggerFactory.getLogger(getTestClass());

	protected String getContextPath() {
		return getTestClass().getPackage().getName();
	}

	protected abstract void checkSample(File sample) throws Exception;

	@Test
	public void testSamples() throws Exception {
		logger.debug("Testing samples, start");
		int failed = 0;
		final File[] sampleFiles = getSampleFiles();
		for (final File sampleFile : sampleFiles) {
			logger.debug("Testing sample, start [" + sampleFile.getName() + "].");
			String result = "SUCCESS";
			try {
				checkSample(sampleFile);
			} catch (Throwable ex) {
				logger.error("Testing sample [" + sampleFile.getName()
						+ "] failed the check.", ex);
				failed++;
				result="FAILURE";
			}
			logger.info("Testing sample, " + result + " [" + sampleFile.getName() + "].");
		}
		logger.debug("Testing samples, finish");

		String summary = "Testing summary [" + failed + "/" + sampleFiles.length + "] failed";
		// logger.info(summary);
		assertTrue(failed == 0, summary + " the check. Check previous errors for details.");
	}

	protected File getBaseDir() {
		try {
			return (new File(getTestClass().getProtectionDomain()
					.getCodeSource().getLocation().getFile())).getParentFile()
					.getParentFile().getAbsoluteFile();
		} catch (Exception ex) {
			throw new AssertionError(ex);
		}
	}

	protected Class<? extends Object> getTestClass() {
		return getClass();
	}

	protected File getSamplesDirectory() {
		return new File(getBaseDir(), getSamplesDirectoryName());
	}

	public static final String DEFAULT_SAMPLES_DIRECTORY_NAME = "src/test/samples";

	protected String getSamplesDirectoryName() {
		return DEFAULT_SAMPLES_DIRECTORY_NAME;
	}

	protected File[] getSampleFiles() {
		File samplesDirectory = getSamplesDirectory();
		logger.debug("Sample directory [" + samplesDirectory.getAbsolutePath()
				+ "].");
		final Collection<File> files = FileUtils.listFiles(samplesDirectory,
				new String[] { "xml" }, true);
		File[] fileArray = files.toArray(new File[files.size()]);
		sort(fileArray);
		return fileArray;
	}

	protected ClassLoader getContextClassLoader() {
		return getTestClass().getClassLoader();
	}

	protected Map<String, ?> getContextProperties() {
		return null;
	}

	public JAXBContext createContext() throws JAXBException {
		final String contextPath = getContextPath();
		final ClassLoader classLoader = getContextClassLoader();
		final Map<String, ?> properties = getContextProperties();
		if (classLoader == null) {
			return JAXBContext.newInstance(contextPath);
		} else {
			if (properties == null) {
				return JAXBContext.newInstance(contextPath, classLoader);
			} else {
				return JAXBContext.newInstance(contextPath, classLoader,
						properties);
			}
		}
	}
}
