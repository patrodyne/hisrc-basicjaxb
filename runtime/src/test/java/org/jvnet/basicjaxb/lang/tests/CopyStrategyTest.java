package org.jvnet.basicjaxb.lang.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAnyElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.lang.CopyStrategy;
import org.jvnet.basicjaxb.lang.CopyTo;
import org.jvnet.basicjaxb.lang.JAXBCopyStrategy;
import org.jvnet.basicjaxb.locator.DefaultRootObjectLocator;
import org.jvnet.basicjaxb.locator.ObjectLocator;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;

public class CopyStrategyTest
{
	@Test
	public void testPolygon()
	{
		final GeometryFactory geometryFactory = new GeometryFactory();
		final Polygon polygon = geometryFactory.createPolygon
		(
			geometryFactory.createLinearRing
			(
				new Coordinate[]
				{
				 	new Coordinate(0, 0, 0),
				 	new Coordinate(1, 1, 0),
				 	new Coordinate(1, 0, 0),
					new Coordinate(0, 1, 0),
					new Coordinate(0, 0, 0) 
				}
			),
			null
		);
		
		Polygon ploygonClone = (Polygon) polygon.clone();
		assertEquals(polygon, ploygonClone, "A cloned polygon must equal the original.");
		
		CopyStrategy copyStrategy= JAXBCopyStrategy.getInstance();
		
		Polygon ploygonCopy = (Polygon) copyStrategy.copy(new DefaultRootObjectLocator(polygon), polygon, true);
		assertEquals(polygon, ploygonCopy, "A copied polygon must equal the original.");
	}

	@Test
	public void testAny() throws Exception
	{
		JAXBContext context = JAXBContext.newInstance(A.class);
		try ( InputStream is = getClass().getResourceAsStream("Test[0].xml") )
		{
			A a = (A) context.createUnmarshaller().unmarshal(is);
			a.copyTo(a.createNewInstance());
		}
	}

	@XmlRootElement(name = "a")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class A implements CopyTo
	{
		@XmlAnyElement(lax = true)
		private Object any;

		public Object getAny()
		{
			return any;
		}

		public void setAny(Object any)
		{
			this.any = any;
		}

		@Override
		public Object createNewInstance()
		{
			return new A();
		}

		@Override
		public Object copyTo(Object target)
		{
			ObjectLocator thisLocator = null;
			JAXBCopyStrategy copyStrategy = JAXBCopyStrategy.getInstance();
	        if ( copyStrategy.isDebugEnabled() )
	            thisLocator = new DefaultRootObjectLocator(this);
			return copyTo(thisLocator, target, copyStrategy);
		}

		@Override
		public Object copyTo(ObjectLocator locator, Object target, CopyStrategy copyStrategy)
		{
			final A copy = ((target == null) ? ((A) createNewInstance()) : ((A) target));
			{
				Object sourceAny;
				sourceAny = this.getAny();
				Object copyAny = ((Object) copyStrategy.copy(locator, sourceAny, any != null));
				copy.setAny(copyAny);
			}
			return copy;
		}
		
		@Override
		public Object clone()
		{
			return copyTo(createNewInstance());
		}
	}
}
