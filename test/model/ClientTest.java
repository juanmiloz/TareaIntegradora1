package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClientTest {

	public void setupScenary1() {
		
	}
	
	@Test
	public void testClient() {
		String typeIdentification = "Cedula Ciudadania";
		String numberIdentification = "1007462934";
		String name = "Adolfo";
		String lastName = "Restrepo";
		String phone = "3185471356";
		String adress = "Cra 1k #48-56";
		
		Client newClient = new Client(typeIdentification, numberIdentification, name, lastName, phone, adress);
		
		assertEquals(typeIdentification, newClient.getTypeIdentification(),"The type identification of the client is incorrect");
		assertEquals(numberIdentification, newClient.getNumberIdentification(),"The identification number of the client is incorrect");
		assertEquals(name, newClient.getName(),"The name of the client is incorrect");
		assertEquals(lastName, newClient.getLastName(),"The last name of the client is incorrect");
		assertEquals(phone, newClient.getPhone(),"The phone of the client is incorrect");
		assertEquals(adress, newClient.getAdress(),"The addres of the client is incorrect");
	}
	
	@Test
	public void testSeters() {
		Client newClient = new Client("","","","","","");
		
		String typeIdentification = "Cedula Ciudadania";
		String numberIdentification = "1007462934";
		String name = "Adolfo";
		String lastName = "Restrepo";
		String phone = "3185471356";
		String adress = "Cra 1k #48-56";
		
		newClient.setTypeIdentification(typeIdentification);
		newClient.setNumberIdentification(numberIdentification);
		newClient.setName(name);
		newClient.setLastName(lastName);
		newClient.setPhone(phone);
		newClient.setAdress(adress);
		
		assertEquals(typeIdentification, newClient.getTypeIdentification(),"The type identification of the client is incorrect");
		assertEquals(numberIdentification, newClient.getNumberIdentification(),"The identification number of the client is incorrect");
		assertEquals(name, newClient.getName(),"The name of the client is incorrect");
		assertEquals(lastName, newClient.getLastName(),"The last name of the client is incorrect");
		assertEquals(phone, newClient.getPhone(),"The phone of the client is incorrect");
		assertEquals(adress, newClient.getAdress(),"The addres of the client is incorrect");
	}
}
