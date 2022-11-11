package org.jvnet.basicjaxb.tests.simple_hashcode_equals_01.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

	@BeforeEach
	public void setUp() throws Exception
	{
	}

	@AfterEach
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testCustomerEquals()
	{
		// First, generate the customer class with Maven plugin or RunPluginsForCustomer.
		// Second compare hand-created class with auto-generated class.
		
		Customer2 customerHand = new Customer2();
		Customer customerAuto = new Customer();
		
		customerHand.setAddress("address");
		customerHand.setBlueEyes(true);
		customerHand.setFamilyName("familyName");
		customerHand.setGivenName("firstName");
		customerHand.getMiddleInitials().add("m");
		customerHand.setPostCode("pc");
		customerHand.setSingle(true);
		
		customerAuto.setAddress(customerHand.getAddress());
		customerAuto.setBlueEyes(customerHand.isBlueEyes());
		customerAuto.setFamilyName(customerHand.getFamilyName());
		customerAuto.setGivenName(customerHand.getGivenName());
		customerAuto.getMiddleInitials().addAll(customerHand.getMiddleInitials());
		customerAuto.setPostCode(customerHand.getPostCode());
		customerAuto.setSingle(customerHand.isSingle());

		assertEquals(customerHand.hashCode(), customerAuto.hashCode());
	}
}
