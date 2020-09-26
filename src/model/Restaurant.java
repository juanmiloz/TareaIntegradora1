package model;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Restaurant implements Comparable<Restaurant>, Serializable{
	
	public final static long serialVersionUID = 1;
	private String name;
	private String nit;
	private String nameAdministrator;
	private ArrayList<Product> products;
	
	/*
	name: Restaurant
	it´s the constructor of class Restaurant
	@param:name 
	@param:nit
	@param:nameAdministrator
	*/
	public Restaurant(String name, String nit, String nameAdministrator) {
		this.name = name;
		this.nit = nit;
		this.nameAdministrator = nameAdministrator;
		products = new ArrayList<Product>();
	}
	
	/*
	name: setName
	allows entering a new name for the restaurant.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new name will be saved.
	@param:name
	*/
	public void setName(String name ) {
		this.name = name;
	}
	
	/*
	name: getName
	allows to obtain the name of restaurant.
	<b>pre:</b>the name of the restaurant must be entered
	<b>post:</b>obtain the name of the restaurant saved in variable "name"
	*/
	public String getName() {
		return name; 
	}
	
	/*
	name: setNit
	allows entering a new nit for the restaurant.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new nit will be saved.
	@param:nit
	*/
	public void setNit(String nit) {
		this.nit = nit;
	}
	
	/*
	name: getNit
	allows to obtain the nit of restaurant.
	<b>pre:</b>the nit of the restaurant must be entered
	<b>post:</b>obtain the nit of the restaurant saved in variable "nit"
	*/
	public String getNit() {
		return nit;
	}
	
	/*
	name: setNameAdministraitor
	allows entering a new name of the administraitor for the restaurant.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new name of the administraitor will be saved.
	@param:nameAdministraitor
	*/
	public void setNameAdministraitor(String nameAdministraitor) {
		this.nameAdministrator = nameAdministraitor;
	}
	
	/*
	name: getNameAdministraitor
	allows to obtain the name of the administraitor of restaurant.
	<b>pre:</b>the name of the administraitor of the restaurant must be entered
	<b>post:</b>obtain the name of the administraitor of the restaurant saved in variable "nameAdministraitor"
	*/
	public String getNameAdministraitor() {
		return nameAdministrator;
	}
	
	/*
	name: getProducts
	allows to obtain a product of restaurant.
	<b>pre:</b>the product of the restaurant must be entered
	<b>post:</b>obtain product of the restaurant
	*/
	public List<Product> getProducts(){
		return products;
	}
	
	/*
	name: addNameAdministraitor
	allows entering a new product for the restaurant.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new product will be saved.
	@param:newProduct
	*/
	public void addProduct(Product newProduct) {
		products.add(newProduct);
	}
	
	/*
	name: getInfoProducts
	allows to obtain a info of the product of restaurant.
	<b>pre:</b>the product of the restaurant must be entered
	<b>post:</b>obtain info of product of the restaurant
	*/
	public String getInfoProducts() {
		String infoProducts = "\nLos productos del restaurante son:\n";
		for(int i = 0; i <products.size();i++) {
			infoProducts += "-Producto numero " + (i+1) + "\n" + products.get(i).toString();
		}
		return infoProducts;
	}
	
	/*
	name: toString
	Allows you to print a restaurant information on the screen
	<b>pre:</b>there must be an object created.
	<b>post:</b>show the information of the restaurant.
	*/
	public String toString() {
		String infoRestaurant;
		infoRestaurant = "Nombre: " + getName() + "\nNit: " + getNit() + "\nAdministrador: " + getNameAdministraitor() + "\n";
		return infoRestaurant;
	}

	/*
	name: compareTo
	Allows you to compare two objects of type restaurant using criteria
	<b>pre:</b>there must be an object created.
	<b>post:</b>organized restaurants.
	@param: restaurant
	*/
	@Override
	public int compareTo(Restaurant restaurant) {
		int comp;
		comp = name.compareTo(restaurant.getName());
		return comp;
	}
}
