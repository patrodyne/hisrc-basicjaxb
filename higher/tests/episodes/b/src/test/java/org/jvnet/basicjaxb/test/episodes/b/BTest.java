package org.jvnet.basicjaxb.test.episodes.b;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.jvnet.basicjaxb.lang.ContextUtils;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;
import org.jvnet.basicjaxb.test.episodes.a.A1Type;
import org.jvnet.basicjaxb.test.episodes.a.AType;
import org.jvnet.basicjaxb.test.episodes.a.IssueHJIII24Animal;
import org.jvnet.basicjaxb.test.episodes.a.XType;
import org.w3c.dom.Element;

import jakarta.xml.bind.JAXBElement;

public class BTest extends AbstractSamplesTest
{
	private static final ObjectFactory OF = new ObjectFactory();
	
    @Override
    protected String getContextPath()
    {
        return ContextUtils.getContextPath(OF.getClass());
    }
    
	@Override
	protected void checkSample(File sample) throws Exception
	{
		Object object = createContext().createUnmarshaller().unmarshal(sample);

		if ( object instanceof JAXBElement)
			object = ((JAXBElement<?>) object).getValue();

		if ( object instanceof BType )
		{
			BType bOne = new BType();
			{
				A1Type a1 = new A1Type();
				a1.setA1("test");
				bOne.setA(a1);
				B1Type b1 = new B1Type();
				b1.setB1("test");
				bOne.setB(b1);
			}
			BType bTwo = (BType) object;
			getLogger().debug("bOne: {}", bOne);
			getLogger().debug("bTwo: {}", bTwo);
			assertEquals(bOne, bTwo, "Expected BType and unmarshaled BType are equal.");
		}
		else if ( object instanceof AType )
		{
			AType aOne = new AType();
			{
				A1Type a1 = new A1Type();
				a1.setA1("test");
				aOne.setA(a1);
			}
			AType aTwo = (AType) object;
			getLogger().debug("aOne: {}", aOne);
			getLogger().debug("aTwo: {}", aTwo);
			assertEquals(aOne, aTwo, "Expected AType and unmarshaled AType are equal.");
		}
		else if ( object instanceof IssueHJIII24Animal )
		{
			IssueHJIII24Animal animal1 = new IssueHJIII24Animal();
			animal1.setName("test");
			IssueHJIII24Animal animal2 = (IssueHJIII24Animal) object;
			getLogger().debug("animal1: {}", animal1);
			getLogger().debug("animal2: {}", animal2);
			assertEquals(animal1, animal2, "Expected animal and unmarshaled animal are equal.");
		}
		else if ( object instanceof XType )
		{
			XType x1 = new XType();
			{
				x1.setX1("x1");
				x1.setX2("x2");
			}
			XType x2 = (XType) object;
			{
				// kludge
				Element test = x2.getAny().get(0);
				x1.getAny().add(test);
			}
			getLogger().debug("x1: {}", x1);
			getLogger().debug("x2: {}", x2);
			assertEquals(x1, x2, "Expected XType and unmarshaled XType are equal.");
		}
		else
			fail("Unknown type: " + object);
	}
}
