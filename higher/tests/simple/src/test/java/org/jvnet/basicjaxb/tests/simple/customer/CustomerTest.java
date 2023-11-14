package org.jvnet.basicjaxb.tests.simple.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * First, generate the customer class with Maven plugin or RunPluginsForCustomer.
 * Second compare hand-created class with auto-generated class.
 */
public class CustomerTest
{
	private Customer customerAuto0, customerAuto1, customerAuto2;
	private Customer2 customerHand;

	@BeforeEach
	public void setUp() throws Exception
	{
		customerHand = new Customer2();
		customerHand.setAddress("address");
		customerHand.setBlueEyes(true);
		customerHand.setFamilyName("familyName");
		customerHand.setGivenName("firstName");
		customerHand.getMiddleInitials().add("m");
		customerHand.setPostCode("pc");
		customerHand.setSingle(true);
		customerHand.setPhoto("cGhvdG8K".getBytes());
		
		customerAuto0 = new Customer();
		
		customerAuto1 = new Customer();
		customerAuto1.setAddress(customerHand.getAddress());
		customerAuto1.setBlueEyes(customerHand.isBlueEyes());
		customerAuto1.setFamilyName(customerHand.getFamilyName());
		customerAuto1.setGivenName(customerHand.getGivenName());
		customerAuto1.getMiddleInitials().addAll(customerHand.getMiddleInitials());
		customerAuto1.setPostCode(customerHand.getPostCode());
		customerAuto1.setSingle(customerHand.isSingle());
		customerAuto1.setPhoto(customerHand.getPhoto());
		
		customerAuto2 = new Customer();
		customerAuto2.setAddress(customerHand.getAddress());
		customerAuto2.setBlueEyes(customerHand.isBlueEyes());
		customerAuto2.setFamilyName(customerHand.getFamilyName());
		customerAuto2.setGivenName(customerHand.getGivenName());
		customerAuto2.getMiddleInitials().addAll(customerHand.getMiddleInitials());
		customerAuto2.setPostCode(customerHand.getPostCode());
		customerAuto2.setSingle(customerHand.isSingle());
		customerAuto2.setPhoto(customerHand.getPhoto());
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		customerAuto0 = null;
		customerAuto1 = null;
		customerAuto2 = null;
		customerHand = null;
	}

	@Test
	public void testCustomerHashCode()
	{
		assertNotEquals(customerAuto0.hashCode(), customerAuto1.hashCode(), "hashCode (not)");
		assertEquals(customerAuto1.hashCode(), customerAuto2.hashCode(), "hashCode (auto)");
		assertEquals(customerHand.hashCode(), customerAuto1.hashCode(), "hashCode (hand)");
	}
	
	@Test
	public void testCustomerEquals()
	{
		assertNotEquals(customerAuto0, customerAuto1, "equals (not)");
		assertEquals(customerAuto1, customerAuto2, "equals");
	}

	@Test
	public void testCustomerToString()
	{
		assertNotNull(customerAuto0.toString(), "toString 0");
		assertNotNull(customerAuto1.toString(), "toString 1");
		assertNotNull(customerAuto2.toString(), "toString 2");
//		System.out.println(customerAuto0.toString());
//		System.out.println(customerAuto1.toString());
//		System.out.println(customerAuto2.toString());
	}
}
