package org.jvnet.basicjaxb.tests.strategic.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.junit.jupiter.api.Order;
import org.jvnet.basicjaxb.testing.AbstractSamplesTest;

@Order(2)
public class OrganizationTest extends AbstractSamplesTest
{
	@Override
	protected String getContextPath()
	{
		return Organization.class.getPackageName();
	}

	@Override
	protected void checkSample(File sampleFile)
		throws Exception
	{
		assertNotNull(sampleFile, "Sample must exist.");
		Object sample = getUnmarshaller().unmarshal(sampleFile);
		if ( sample instanceof Organization org0 )
		{
			assertEquals(2, org0.getProjects().size());
			assertEquals(4, org0.getDepartments().size());
			assertEquals(8, org0.getEmployees().size());

			for ( Project project : org0.getProjects() )
				assertEquals(0, project.getEmployees().size() );

			for ( Employee employee : org0.getEmployees() )
			{
				getLogger().debug("Employee: {}", employee);
				projectTieEmployees(employee);
				getLogger().debug("Employee #: {}", employee.hashCode());
			}

			for ( Project project : org0.getProjects() )
				getLogger().debug("Project: {}", project);

			getLogger().trace("Organization 0:\n{}", marshalToString(org0));

			Organization org1 = (Organization) getUnmarshaller().unmarshal(sampleFile);
			Organization org2 = (Organization) getUnmarshaller().unmarshal(sampleFile);
			assertEquals(org1.hashCode(), org2.hashCode());
			assertTrue(org1.equals(org2));

			Organization org3 = (Organization) org0.clone();
			assertEquals(org0, org3, "Clone must equal original.");

			getLogger().trace("Organization 3:\n{}", marshalToString(org3));

			Organization org4 = new Organization();
			org4.mergeFrom(org3, org1);

			getLogger().trace("Organization 4:\n{}", marshalToString(org4));
		}
		else
			fail("Sample must be an Organization");
	}

	public void projectTieEmployees(Employee employee)
	{
		for ( Project project : employee.getProjects() )
		{
			if ( !project.getEmployees().contains(employee) )
				project.getEmployees().add(employee);
		}
	}
}
