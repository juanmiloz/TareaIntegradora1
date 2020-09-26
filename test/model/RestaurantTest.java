package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RestaurantTest {

	public void setupScenary1() {
		
	}
		
	@Test
	public void testRestaurant() {
		String name = "Burguer Stack";
		String nit = "812394";
		String nameAdmin = "Jose";
		
		Restaurant newContact = new Restaurant(name, nit , nameAdmin);
		
		assertEquals(name,newContact.getName(),"The restaurant name is incorrect");
		assertEquals(nit,newContact.getNit(),"The nit is incorrect");
		assertEquals(nameAdmin,newContact.getNameAdministraitor(),"The name administraitor is incorrect");
	}
	
	@Test
	public void testSeters() {
		Restaurant newContact = new Restaurant("", "" , "");
		
		String name = "Burguer Stack";
		String nit = "812394";
		String nameAdmin = "Jose";
		
		newContact.setName(name);
		newContact.setNit(nit);
		newContact.setNameAdministraitor(nameAdmin);
		
		assertEquals(name,newContact.getName(),"The restaurant name is incorrect");
		assertEquals(nit,newContact.getNit(),"The nit is incorrect");
		assertEquals(nameAdmin,newContact.getNameAdministraitor(),"The name administraitor is incorrect");
	}
}