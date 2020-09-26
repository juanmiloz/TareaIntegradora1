package model;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;


public class Order implements Comparable<Order>, Serializable{
	
	private static final long serialVersionUID = 1L;
	private String code;
	private Date date;
	private String codeClient;
	private String nitRestaurant;
	private ArrayList<String[]> productsOrder;
	private String status;
	
	/*
	name: Order
	it´s the constructor of class Order
	@param:code
	@param:date
	@param:codeClient
	@param:nitRestaurant
	@param:status
	*/
	public Order (String code, Date date, String codeClient, String nitRestaurant, String status){
		this.code = code;
		this.date = date;
		this.codeClient = codeClient;
		this.nitRestaurant = nitRestaurant;
		productsOrder = new ArrayList<String[]>();
		this.status = status;
	}
	
	/*
	name: setCode
	allows entering a new code for the order.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new code will be saved.
	@param:code
	*/
	public void setCode(String code) {
		this.code = code; 
	}
	
	/*
	name: getCode
	allows to obtain the code of order.
	<b>pre:</b>the code of the order must be entered
	<b>post:</b>obtain the code of the order saved in variable "code"
	*/
	public String getCode() {
		return code;
	}
	
	/*
	name: setDate
	allows entering a new date for the order.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new date will be saved.
	@param:date
	*/
	public void setDate(Date date) {
		this.date = date;
	}
	
	/*
	name: getDate
	allows to obtain the date of order.
	<b>pre:</b>the date of the order must be entered
	<b>post:</b>obtain the date of the order saved in variable "date"
	*/
	public Date getDate() {
		return date;
	}
	
	/*
	name: setCodeClient
	allows entering a new id of client for the order.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new id of client will be saved.
	@param:codeClient
	*/
	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}
	
	/*
	name: getCodeClient
	allows to obtain the id of client of order.
	<b>pre:</b>the id of client of the order must be entered
	<b>post:</b>obtain the id of client of the order saved in variable "codeClient"
	*/
	public String getCodeClient() {
		return codeClient;
	}
	
	/*
	name: setNitRestaurant
	allows entering a new nit of restaurant for the order.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new nit of restaurant will be saved.
	@param:nitRestaurant
	*/
	public void setNitRestaurant(String nitRestaurant) {
		this.nitRestaurant = nitRestaurant;
	}
	
	/*
	name: getNitRestaurant
	allows to obtain the nit of restaurant of order.
	<b>pre:</b>the nit of restaurant of the order must be entered
	<b>post:</b>obtain the nit of restaurant of the order saved in variable "nitRestaurant"
	*/
	public String getNitRestaurant() {
		return nitRestaurant;
	}
	
	/*
	name: getProductsOrder
	allows to obtain a product of order.
	<b>pre:</b>the product of the order must be entered
	<b>post:</b>obtain product of the order
	*/
	public ArrayList<String[]> getProductsOrder(){
		return productsOrder;
	}
	
	/*
	name: setStatus
	allows entering a new status for the order.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new status will be saved.
	@param:status
	*/
	public void setStatus(String status) {
		this.status = status;
	}
	
	/*
	name: getStatus
	allows to obtain the status of order.
	<b>pre:</b>the status of the order must be entered
	<b>post:</b>obtain the status of the order saved in variable "status"
	*/
	public String getStatus() {
		return status;
	}
	
	/*
	name: toString
	Allows you to print a order information on the screen
	<b>pre:</b>there must be an object created.
	<b>post:</b>show the information of the order.
	*/
	public String toString() {
		String infoOrder = "\nCodigo de orden: " + code + "\nFecha de pedido: " + date + "\nNumero de identidad del cliente: " + codeClient;
		infoOrder += "\nCodigo del restaurante: " + nitRestaurant + "\nEstatus: " + status;
		for(int i = 0; i < productsOrder.size();i++) {
			String temp[] = productsOrder.get(i);
			infoOrder += "\nProducto a llevar numero: " + (i+1) + "\nnombre: " + temp[0] + " cantidad: " + temp[1] + "\n";
		}
		return infoOrder; 
	}
	
	/*
	name: addProducts
	allows entering a new product for the order.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new product will be saved.
	@param:productsToAdded
	*/
	public void addProducts(String[] productsToAdded) {
		productsOrder.add(productsToAdded);
	}
	
	/*
	name: compareTo
	Allows you to compare two objects of type order using criteria
	<b>pre:</b>there must be an object created.
	<b>post:</b>organized orders.
	@param: otherOrder
	*/
	@Override
	public int compareTo(Order otherOrder) {
		int comp;
		double num1 = Double.parseDouble(nitRestaurant);
		double num2 = Double.parseDouble(otherOrder.getNitRestaurant());
		if(num1>num2) {
			comp = 1;
		}else if(num1<num2) {
			comp = -1;
		}else {
			comp = sortByDocumentClient(otherOrder);
		}
		return comp;
	}
	
	/*
	name: sortByDocumentClient
	Allows you to compare two objects of type order using criteria
	<b>pre:</b>there must be an object created.
	<b>post:</b>organized orders.
	@param: otherOrder
	*/
	private int sortByDocumentClient(Order otherOrder) {
		int comp;
		double num1 = Double.parseDouble(codeClient);
		double num2 = Double.parseDouble(otherOrder.getCodeClient());
		if(num1>num2) {
			comp = -1;
		}else if(num1<num2) {
			comp = 1;
		}else{
			comp = sortByDate(otherOrder);
		}
		return comp;
	}
	
	private int sortByDate(Order otherOrder) {
		return 0;
	}
}
