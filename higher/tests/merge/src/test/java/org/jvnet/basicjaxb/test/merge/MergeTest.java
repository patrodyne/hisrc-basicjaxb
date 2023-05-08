package org.jvnet.basicjaxb.test.merge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jvnet.basicjaxb.test.AbstractSamplesTest;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class MergeTest extends AbstractSamplesTest
{
	private Map<String, File> sampleMap = new HashMap<>();
	@Override
	protected Map<String, File> getSampleMap() { return sampleMap; }
	@Override
	protected void setSampleMap(Map<String, File> sampleMap) { this.sampleMap = sampleMap; }

	@BeforeEach
	public void beforeAll()
	{
		for ( File sampleFile : getSampleFiles() )
			getSampleMap().put(sampleFile.getName(), sampleFile);
	}
	
	@Override
	protected void checkSample(File sampleFile)
		throws Exception
	{
		assertNotNull(sampleFile, "Sample must exist.");
		getSampleMap().put(sampleFile.getName(), sampleFile);
	}
	
	@Test
	public void testMerge() throws JAXBException
	{
		Unmarshaller unmarshaller = createContext().createUnmarshaller();
		Match matchA = (Match) unmarshaller.unmarshal(getSampleMap().get("MatchA.xml"));
		Match matchB = (Match) unmarshaller.unmarshal(getSampleMap().get("MatchB.xml"));
		Match matchC = (Match) unmarshaller.unmarshal(getSampleMap().get("MatchC.xml"));
		
		Match mergeAB = new Match();
		mergeAB.mergeFrom(matchA, matchB);
		assertNotEquals(matchC, mergeAB, "mergeAB must not equal matchC");

		Match mergeBA = new Match();
		mergeBA.mergeFrom(matchB, matchA);
		assertEquals(matchC, mergeBA, "mergeBA must equal matchC");
	}
}
