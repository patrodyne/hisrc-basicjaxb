package org.jvnet.basicjaxb.reflection.util.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.reflection.util.Accessor;
import org.jvnet.basicjaxb.reflection.util.FieldAccessor;

public class FieldAccessorTest {

	@Test
	public void testGetAndSet() throws URISyntaxException
	{
		final URI uri = new URI("urn:test");

		/*
		 * 2022-07-19: See https://stackoverflow.com/questions/50251798/what-is-an-illegal-reflective-access
					   Note: Support for --illegal-access=permit was removed with Java 17.
		[INFO] Running org.jvnet.basicjaxb.reflection.util.test.FieldAccessorTest
		[ERROR] WARNING: An illegal reflective access operation has occurred
		[ERROR] WARNING: Illegal reflective access by org.jvnet.basicjaxb.reflection.util.FieldAccessor 
		                 (file:../hisrc-basicjaxb/tools/target/classes/) to field java.net.URI.scheme
		[ERROR] WARNING: Please consider reporting this to the maintainers of org.jvnet.basicjaxb.reflection.util.FieldAccessor
		[ERROR] WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
		[ERROR] WARNING: All illegal access operations will be denied in a future release
		 *
		 * 2023-01-11 See https://docs.oracle.com/javase/9/migrate/toc.htm#JSMIG-GUID-3A71ECEF-5FC5-46FE-9BA9-88CBFCE828CB
		 *            Note: You can enable reflective access on a library-by-library basis by using '--add-opens' system arg.
		 */
		final Accessor<String> schemeAccessor =
			new FieldAccessor<String>(URI.class, "scheme", String.class);

		assertEquals("urn", schemeAccessor.get(uri));
		schemeAccessor.set(uri, "nru");
		assertEquals("nru", schemeAccessor.get(uri));
		assertEquals("nru", uri.getScheme());
	}

}
