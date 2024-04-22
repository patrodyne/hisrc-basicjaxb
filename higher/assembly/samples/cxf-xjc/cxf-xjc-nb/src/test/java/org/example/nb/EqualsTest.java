package org.example.nb;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.example.nb.A.NotebookAnalog;
import org.example.nb.B.NotebookDigital;

public class EqualsTest extends AbstractNotebookTest
{
	@Override
	protected void checkSample(File sample) throws Exception
	{
		final Object lhs = getUnmarshaller().unmarshal(sample);
		final Object rhs = getUnmarshaller().unmarshal(sample);
		
		if ( lhs instanceof NotebookAnalog && rhs instanceof NotebookAnalog )
		{
			NotebookAnalog nba1 = (NotebookAnalog) lhs;
			NotebookAnalog nba2 = (NotebookAnalog) rhs;
			assertTrue(nba1.equals(nba2), "NBA1 should equal NBA2");
		}
		else if ( lhs instanceof NotebookDigital && rhs instanceof NotebookDigital )
		{
			NotebookDigital nbb1 = (NotebookDigital) lhs;
			NotebookDigital nbb2 = (NotebookDigital) rhs;
			assertTrue(nbb1.equals(nbb2), "NBB1 should equal NBB2");
		}
		else
			fail("Sample is not a NotebookAnalog or a NotebookDigital");
	}
}
