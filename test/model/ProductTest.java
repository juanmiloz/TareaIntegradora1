package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductTest {
	
	public void setupScenary1() {
		
	}
	
	@Test
	void testProduct() {
		String code = "789235";
		String name = "Blue Mike";
		String description = "La Blue Mike es generosa en carne: 200 gramos de la marca registrada Certified Angus Beef, salsa de queso azul, cebolla morada y tocineta caramelizada. Esto, dentro de un pan artesanal. ";
		Double cost = 17900.0;
		String nitRestaurant = "812394";
		
		Product newProduct = new Product(code, name, description,cost,nitRestaurant);
		
		assertEquals(code, newProduct.getCode(),"The product code is incorrect");
		assertEquals(name, newProduct.getName(),"The product name is incorrect");
		assertEquals(description, newProduct.getDescription(),"The product description is incorrect");
		assertEquals(cost, newProduct.getCost(),"The product cost is incorrect");
		assertEquals(nitRestaurant, newProduct.getNitRestaurant(),"The nit of restaurant to the product is incorrect");
	}
	
	@Test
	void testSeters() {
		Product newProduct = new Product("","", "",0,"");
		
		String code = "789235";
		String name = "Blue Mike";
		String description = "La Blue Mike es generosa en carne: 200 gramos de la marca registrada Certified Angus Beef, salsa de queso azul, cebolla morada y tocineta caramelizada. Esto, dentro de un pan artesanal. ";
		Double cost = 17900.0;
		String nitRestaurant = "812394";
		
		newProduct.setCode(code);
		newProduct.setName(name);
		newProduct.setDescription(description);
		newProduct.setCost(cost);
		newProduct.setNitRestaurant(nitRestaurant);
		
		assertEquals(code, newProduct.getCode(),"The product code is incorrect");
		assertEquals(name, newProduct.getName(),"The product name is incorrect");
		assertEquals(description, newProduct.getDescription(),"The product description is incorrect");
		assertEquals(cost, newProduct.getCost(),"The product cost is incorrect");
		assertEquals(nitRestaurant, newProduct.getNitRestaurant(),"The nit of restaurant to the product is incorrect");
	}

}
