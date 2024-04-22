package org.example.nb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.math.BigInteger;

import org.example.nb.A.NotebookAnalog;
import org.example.nb.B.NotebookDigital;

public class FluentAPITest extends AbstractNotebookTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		setFailFast(true);
		final Object object = getUnmarshaller().unmarshal(sample);
		if ( object instanceof NotebookAnalog )
		{
			NotebookAnalog nba1 = (NotebookAnalog) object;
			NotebookAnalog nba2 = OFA.createNotebookAnalog()
				.useTitle("PaperBook")
				.useOwner(OFA.createOwnerAnalog()
					.useFirstname("Paul")
					.useLastname("McCartney"))
				.usePageSpec(OFA.createPageSpecAnalog()
					.useLinesPerPage(new BigInteger("66"))
					.usePageCount(new BigInteger("100")));
			
			getLogger().debug("NBA1: {}", nba1);
			getLogger().debug("NBA2: {}", nba2);
			
			assertEquals(nba1, nba2, "Unmarshaled and Fluent NBAs are equal.");
		}
		else if ( object instanceof NotebookDigital )
		{
			NotebookDigital nbb1 = (NotebookDigital) object;
			NotebookDigital nbb2 = OFB.createNotebookDigital()
				.useTitle("EBook")
				.useOwner(OFB.createOwnerDigital()
					.useFirstname("John")
					.useLastname("Lennon"))
				.usePageSpec(OFB.createPageSpecDigital()
					.useKbPerPage(new BigInteger("512"))
					.usePageCount(new BigInteger("1024")));
			
			getLogger().debug("NBB1: {}", nbb1);
			getLogger().debug("NBB2: {}", nbb2);
			
			assertEquals(nbb1, nbb2, "Unmarshaled and Fluent NBBs are equal.");
		}
	}
}
