package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class OrderTest {

	public void setupScenary1() {
		
	}
	
	@Test
	public void testOrder() {
		String code = "928374";
		Date date = new java.util.Date();;
		String codeClient = "1007462934";
		String nitRestaurant = "812394";
		String [] productsOrder = {"Blue Mike","2"}; 
		String status = "SOLICITADO";
		
		Order newOrder = new Order(code, date, codeClient, nitRestaurant, status);
		newOrder.addProducts(productsOrder);
		
		assertEquals(code,newOrder.getCode(),"The code of the order is incorrect");
		assertEquals(date,newOrder.getDate(),"The date of the order is incorrect");
		assertEquals(codeClient,newOrder.getCodeClient(),"The code client of the order is incorrect");
		assertEquals(nitRestaurant,newOrder.getNitRestaurant(),"The nit restaurant of the order is incorrect");
		assertEquals(productsOrder,newOrder.getProductsOrder().get(0),"The products of the order is incorrect");
		assertEquals(status,newOrder.getStatus(),"The status of the order is incorrect");
	}
	
	@Test
	public void testSeters() {
		Order newOrder = new Order("",null,"","","");
		
		String code = "928374";
		Date date = new java.util.Date();;
		String codeClient = "1007462934";
		String nitRestaurant = "812394";
		String [] productsOrder = {"Blue Mike","2"}; 
		String status = "SOLICITADO";
		
		newOrder.setCode(code);
		newOrder.setDate(date);
		newOrder.setCodeClient(codeClient);
		newOrder.setNitRestaurant(nitRestaurant);
		newOrder.addProducts(productsOrder);
		newOrder.setStatus(status);
		
		assertEquals(code,newOrder.getCode(),"The code of the order is incorrect");
		assertEquals(date,newOrder.getDate(),"The date of the order is incorrect");
		assertEquals(codeClient,newOrder.getCodeClient(),"The code client of the order is incorrect");
		assertEquals(nitRestaurant,newOrder.getNitRestaurant(),"The nit restaurant of the order is incorrect");
		assertEquals(productsOrder,newOrder.getProductsOrder().get(0),"The products of the order is incorrect");
		assertEquals(status,newOrder.getStatus(),"The status of the order is incorrect");
	}
}
