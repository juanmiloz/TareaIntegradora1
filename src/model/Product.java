package model;

import java.io.Serializable;

public class Product implements Serializable{
	
	private static final long serialVersionUID = 1; 
	private String code;
	private String name;
	private String description;
	private double cost;
	private String nitRestaurant;
	
	/*
	name: Product
	it´s the constructor of class Product
	@param:code
	@param:name
	@param:description
	@param:cost
	@param:nitRestaurant
	*/
	public Product(String code, String name, String description, double cost, String nitRestaurant) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.nitRestaurant = nitRestaurant;
	}
	
	/*
	name: setCode
	allows entering a new code for the product.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new code will be saved.
	@param:code
	*/
	public void setCode(String code) {
		this.code = code;
	}
	
	/*
	name: getCode
	allows to obtain the code of product.
	<b>pre:</b>the code of the product must be entered
	<b>post:</b>obtain the code of the product saved in variable "code"
	*/
	public String getCode() {
		return code;
	}
	
	/*
	name: setName
	allows entering a new name for the product.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new name will be saved.
	@param:name
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	name: getName
	allows to obtain the name of product.
	<b>pre:</b>the name of the product must be entered
	<b>post:</b>obtain the name of the product saved in variable "name"
	*/
	public String getName() {
		return name;
	}
	
	/*
	name: setDescription
	allows entering a new description for the product.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new description will be saved.
	@param:description
	*/
	public void setDescription(String description) {
		this.description = description;
	}
	
	/*
	name: getDescription
	allows to obtain the description of product.
	<b>pre:</b>the description of the product must be entered
	<b>post:</b>obtain the description of the product saved in variable "description"
	*/
	public String getDescription() {
		return description;
	}
	
	/*
	name: setCost
	allows entering a new cost for the product.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new cost will be saved.
	@param:cost
	*/
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	/*
	name: getCost
	allows to obtain the cost of product.
	<b>pre:</b>the cost of the product must be entered
	<b>post:</b>obtain the cost of the product saved in variable "cost"
	*/
	public double getCost() {
		return cost;
	}
	
	/*
	name: setNitRestaurant
	allows entering a new nit of restaurant for the product.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new nit of restaurant will be saved.
	@param:nitRestaurant 
	*/
	public void setNitRestaurant(String nitRestaurant) {
		this.nitRestaurant = nitRestaurant;
	}
	
	/*
	name: getNitRestaurant
	allows to obtain the nit of restaurant of product.
	<b>pre:</b>the nit of restaurant of the product must be entered
	<b>post:</b>obtain the nit of restaurant of the product saved in variable "nitRestaurant"
	*/
	public String getNitRestaurant() {
		return nitRestaurant; 
	}
	
	/*
	name: toString
	Allows you to print a product information on the screen
	<b>pre:</b>there must be an object created.
	<b>post:</b>show the information of the product.
	*/
	public String toString() {
		String infoProduct = "\nCodigo Producto: " + code + "\nNombre Producto: " + name + "\nDescripcion producto: " + description;
		infoProduct += "\nCosto: " + cost +"\nNit Restaurante: " + nitRestaurant + "\n";
		return infoProduct;
	}
}
