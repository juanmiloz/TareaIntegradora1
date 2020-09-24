package model;

import java.io.Serializable;;

public class Client implements Comparable<Client>, Serializable{
	
	private final static long serialVersionUID = 1; 
	private String typeIdentification;
	private String numberIdentification;
	private String name;
	private String lastName;
	private String phone; 
	private String adress;
	
	public Client(String typeIdentification, String numberIdentification, String name ,String lastName,String phone, String adress) {
		this.typeIdentification = typeIdentification; 
		this.numberIdentification = numberIdentification;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone; 
		this.adress = adress;
	}
	
	public void setTypeIdentification(String typeIdentification) {
		this.typeIdentification = typeIdentification;
	}
	
	public String getTypeIdentification() {
		return typeIdentification;
	}
	
	public void setNumberIdentification(String numberIdentification) {
		this.numberIdentification = numberIdentification; 
	}
	
	public String getNumberIdentification() {
		return numberIdentification; 
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public String getAdress() {
		return adress;
	}
	
	public String toString() {
		String infoClient = "Nombre completo: " + name + " " + lastName;
		infoClient += "\nTipo y numero de indentificacion: " + typeIdentification + " - " + numberIdentification;
		infoClient += "\nDireccion y numero : " + adress + " - " + phone;
		return infoClient;
	}
	
	@Override
	public int compareTo(Client client) {
		int comp;
		String nom = client.getLastName();
		String nom2 = client.getName();
		comp = lastName.compareTo(nom);
		if(comp == 0) {
			comp = name.compareTo(nom2);
		}
		return comp;
	}
}