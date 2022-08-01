package org.jvnet.jaxb2_commons.reflection.util.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.jvnet.jaxb2_commons.reflection.util.Accessor;
import org.jvnet.jaxb2_commons.reflection.util.FieldAccessor;

public class FieldAccessorTest {

	@Test
	public void testGetAndSet() throws URISyntaxException
	{
		final URI uri = new URI("urn:test");

		/* 2022-07-19: See https://stackoverflow.com/questions/50251798/what-is-an-illegal-reflective-access
					   Note: Support for --illegal-access=permit was removed with Java 17.
		[INFO] Running org.jvnet.jaxb2_commons.reflection.util.test.FieldAccessorTest
		[ERROR] WARNING: An illegal reflective access operation has occurred
		[ERROR] WARNING: Illegal reflective access by org.jvnet.jaxb2_commons.reflection.util.FieldAccessor 
		                 (file:../hisrc-basicjaxb/tools/target/classes/) to field java.net.URI.scheme
		[ERROR] WARNING: Please consider reporting this to the maintainers of org.jvnet.jaxb2_commons.reflection.util.FieldAccessor
		[ERROR] WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
		[ERROR] WARNING: All illegal access operations will be denied in a future release
		 */
		final Accessor<String> schemeAccessor =
			new FieldAccessor<String>(URI.class, "scheme", String.class);

		Assert.assertEquals("urn", schemeAccessor.get(uri));
		schemeAccessor.set(uri, "nru");
		Assert.assertEquals("nru", schemeAccessor.get(uri));
		Assert.assertEquals("nru", uri.getScheme());

	}

}