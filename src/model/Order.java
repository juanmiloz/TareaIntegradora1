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
	
	public Order (String code, Date date, String codeClient, String nitRestaurant, String status){
		this.code = code;
		this.date = date;
		this.codeClient = codeClient;
		this.nitRestaurant = nitRestaurant;
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
