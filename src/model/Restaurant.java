package model;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
	
	private String name;
	private String nit;
	private String nameAdministrator;
	private ArrayList<Product> products;
	
	public Restaurant(String name, String nit, String nameAdministrator) {
		this.name = name;
		this.nit = nit;
		this.nameAdministrator = nameAdministrator;
		products = new ArrayList<Product>();
	}
	
	public void setName(String name ) {
		this.name = name;
	}
	
	public String getName() {
		return name; 
	}
	
	public void setNit(String nit) {
		this.nit = nit;
	}
	
	public String getNit() {
		return nit;
	}
	
	public void setNameAdministraitor(String nameAdministraitor) {
		this.nameAdministrator = nameAdministraitor;
	}
	
	public String getNameAdministraitor() {
		return nameAdministrator;
	}
	
	public List<Product> getProducts(){
		return products;
	}
	
	public void addProduct(Product newProduct) {
		products.add(newProduct);
	}
	
	public String getInfoProducts() {
		String infoProducts = "\nLos productos del restaurante son:\n";
		for(int i = 0; i <products.size();i++) {
			infoProducts += "-Producto numero " + (i+1) + "\n" + products.get(i).toString();
		}
		return infoProducts;
	}
	
	public String toString() {
		String infoRestaurant;
		infoRestaurant = "Nombre: " + getName() + "\nNit: " + getNit() + "\nAdministrador: " + getNameAdministraitor() + "\n";
		return infoRestaurant;
	}
}
