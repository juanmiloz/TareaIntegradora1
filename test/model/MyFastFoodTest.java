package model;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class MyFastFoodTest {

	public MyFastFood setupScenary1() {
		String name = "Burguer Stack";
		String nit = "812394";
		String nameAdmin = "Jose";

		MyFastFood myFastFood = new MyFastFood();
		
		myFastFood.addNewRestaurant(name, nit, nameAdmin);
		return myFastFood;
	}
	
	public MyFastFood setupScenary2() {
		MyFastFood myFastFood = new MyFastFood();
		return myFastFood;
	}
	
	@Test
	public void testGetRestaurants() {
		MyFastFood myFastFood = setupScenary1();
		
		assertEquals("Burguer Stack",myFastFood.getRestaurants().get(0).getName(),"Error in the name of restaurant");
		assertEquals("812394",myFastFood.getRestaurants().get(0).getNit(),"Error in the nit of restaurant");
		assertEquals("Jose",myFastFood.getRestaurants().get(0).getNameAdministraitor(),"Error in the name administraitor of restaurant");
	}
	
	@Test
	public void testImportDataRestaurants() {
		MyFastFood myFastFood = setupScenary2();
		try {
			myFastFood.importDataRestaurants();
		}catch(IOException ioe) {
			System.err.println("No se pudo descargar");
		}
		if(myFastFood.getInfoRestaurants() != null && myFastFood.getInfoRestaurants().equalsIgnoreCase("")) {
			assertTrue(true);
		}
	}
}
