package org.example.nb;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.example.nb.A.NotebookAnalog;
import org.example.nb.B.NotebookDigital;

public class ToStringTest extends AbstractNotebookTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		setFailFast(true);
		final Object src = getUnmarshaller().unmarshal(sample);
		if ( src instanceof NotebookAnalog )
		{
			NotebookAnalog nba = (NotebookAnalog) src;
			String nbs = nba.toString();
			getLogger().info("NotebookAnalog: " + nba.getTitle() + "\n\n" + nbs + "\n");
			assertNotNull(nbs);
		}
		else if ( src instanceof NotebookDigital )
		{
			NotebookDigital nbb = (NotebookDigital) src;
			String nbbs = nbb.toString();
			getLogger().info("NotebookDigital: " + nbb.getTitle() + "\n\n" + nbbs + "\n");
			assertNotNull(nbbs);
		}
		else
			fail("Sample is not a NotebookAnalog or a NotebookDigital");
	}
}
