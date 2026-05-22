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
		if ( sample instanceof Organization organization )
		{
			assertEquals(2, organization.getProjects().size());
			assertEquals(4, organization.getDepartments().size());
			assertEquals(8, organization.getEmployees().size());

			for ( Project project : organization.getProjects() )
				assertEquals(0, project.getEmployees().size() );

			for ( Employee employee : organization.getEmployees() )
			{
				getLogger().debug("Employee: {}", employee);
				projectTieEmployees(employee);
				getLogger().debug("Employee #: {}", employee.hashCode());
			}

			for ( Project project : organization.getProjects() )
				getLogger().debug("Project: {}", project);

			String orgXml = marshalToString(organization);
			getLogger().trace("Organization:\n{}", orgXml);

			Organization org1 = (Organization) getUnmarshaller().unmarshal(sampleFile);
			Organization org2 = (Organization) getUnmarshaller().unmarshal(sampleFile);
			assertEquals(org1.hashCode(), org2.hashCode());
			assertTrue(org1.equals(org2));
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
