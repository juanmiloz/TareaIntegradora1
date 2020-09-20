package model;

public class Product {
	
	private String code;
	private String name;
	private String description;
	private double cost;
	private String nitRestaurant;
	
	public Product(String code, String name, String description, double cost, String nitRestaurant) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.nitRestaurant = nitRestaurant;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setNitRestaurant(String nitRestaurant) {
		this.nitRestaurant = nitRestaurant;
	}
	
	public String getNitRestaurant() {
		return nitRestaurant; 
	}
	
	public String toString() {
		String infoProduct = "\nCodigo Producto: " + code + "\nNombre Producto: " + name + "\nDescripcion producto: " + description;
		infoProduct += "\nCosto: " + cost +"\nNit Restaurante: " + nitRestaurant + "\n";
		return infoProduct;
	}
}
