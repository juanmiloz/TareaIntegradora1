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
	private String status;
	
	public Order (String code, Date date, String codeClient, String nitRestaurant, String status) {
		this.code = code;
		this.date = date;
		this.codeClient = codeClient;
		this.nitRestaurant = nitRestaurant;
		productsAndQuantity = new String [2];
		productsOrder = new ArrayList<String[]>();
		this.status = status;
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
	
	public ArrayList<String[]> getProductsOrder(){
		return productsOrder;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String toString() {
		String infoOrder = "\nCodigo de orden: " + code + "\nFecha de pedido: " + date + "\nNumero de identidad del cliente: " + codeClient;
		infoOrder += "\nCodigo del restaurante: " + nitRestaurant + "\nEstatus: " + status;
		for(int i = 0; i < productsOrder.size();i++) {
			String temp[] = productsOrder.get(i);
			infoOrder += "\nProducto a llevar numero: " + (i+1) + "\nnombre: " + temp[0] + " cantidad: " + temp[1] + "\n";
		}
		return infoOrder; 
	}
	
	public void addProducts(String[] productsToAdded) {
		productsOrder.add(productsToAdded);
	}
}
