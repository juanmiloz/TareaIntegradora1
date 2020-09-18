package model;

import java.util.ArrayList;
import java.util.Date;


public class Order {
	private String code;
	private Date date;
	private String codeClient;
	private String nitRestaurant;
	private String productsAndQuantity [];
	private ArrayList<String[]> productsOrder;
	
	public Order (String code, Date date, String codeClient, String nitRestaurant) {
		this.code = code;
		this.date = date;
		this.codeClient = codeClient;
		this.nitRestaurant = nitRestaurant;
		productsAndQuantity = new String [2];
		productsOrder = new ArrayList<String[]>();
	}
	
	public void setCode(String code) {
		this.code = code; 
	}
	
	public String getCode() {
		return code;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}
	
	public String getCodeClient() {
		return codeClient;
	}
	
	public void setNitRestaurant(String nitRestaurant) {
		this.nitRestaurant = nitRestaurant;
	}
	
	public String getNitRestaurant() {
		return nitRestaurant;
	}
	
	public void setProductsAndQuantity(String [] productsAndQuantity) {
		this.productsAndQuantity = productsAndQuantity;
	}
	
	public String [] getProductsAndClient() {
		return productsAndQuantity;
	}
	
	public String toString() {
		String infoOrder = "Codigo de orden: " + code + "\nFecha de pedido: " + date + "\nNumero de identidad del cliente: " + codeClient;
		infoOrder += "Codigo del restaurante: " + nitRestaurant;
		for(int i = 0; i < productsOrder.size();i++) {
			infoOrder += productsOrder.get(i).toString();
		}
		return infoOrder; 
	}
	
	public void addProducts(String[] productsToAdded) {
		productsOrder.add(productsToAdded);
	}
}
