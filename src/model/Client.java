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
	
	/*
	name: Client
	it´s the constructor of class Client
	@param: typeIdentification
	@param: numberIdentification
	@param: name
	@param:lastName
	@param:phone
	@param:addres
	*/
	public Client(String typeIdentification, String numberIdentification, String name ,String lastName,String phone, String adress) {
		this.typeIdentification = typeIdentification; 
		this.numberIdentification = numberIdentification;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone; 
		this.adress = adress;
	}
	
	/*
	name: setTypeIdentification
	allows entering a new identification type for the client.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new identification type will be saved.
	@param:typeIdentification
	*/
	public void setTypeIdentification(String typeIdentification) {
		this.typeIdentification = typeIdentification;
	}
	
	/*
	name: getTypeIdentification
	allows to obtain the type of identification.
	<b>pre:</b>the identification type of the Client must be entered
	<b>post:</b>obtain the identification type of the client saved in variable "typeIdentification"
	*/
	public String getTypeIdentification() {
		return typeIdentification;
	}
	
	/*
	name: setNumberIdentification
	allows entering a new number of identification for the client.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new number of identification will be saved.
	@param:numberIdentification
	*/
	public void setNumberIdentification(String numberIdentification) {
		this.numberIdentification = numberIdentification; 
	}
	
	/*
	name: getNumberIdentification
	allows to obtain the number identification.
	<b>pre:</b>the number identification of the Client must be entered
	<b>post:</b>obtain the number identification of the client saved in variable "numberIdentification"
	*/
	public String getNumberIdentification() {
		return numberIdentification; 
	}
	
	/*
	name: setName
	allows entering a new name for the client.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new name will be saved.
	@param:name
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	name: getName
	allows to obtain the name.
	<b>pre:</b>the name of the Client must be entered
	<b>post:</b>obtain the name of the client saved in variable "name"
	*/
	public String getName() {
		return name;
	}
	
	/*
	name: setLastName
	allows entering a new last name for the client.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new last name will be saved.
	@param: lastName
	*/
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/*
	name: getLastName
	allows to obtain the last name.
	<b>pre:</b>the last name of the Client must be entered
	<b>post:</b>obtain the last name of the client saved in variable "lastName"
	*/
	public String getLastName() {
		return lastName;
	}
	
	/*
	name: setPhone
	allows entering a new phone for the client.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new phone will be saved.
	@param:phone
	*/
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/*
	name: getPhone
	allows to obtain the phone.
	<b>pre:</b>the phone of the Client must be entered
	<b>post:</b>obtain the phone of the client saved in variable "phone"
	*/
	public String getPhone() {
		return phone;
	}
	
	/*
	name: setAdress
	allows entering a new addres for the client.
	<b>pre:</b>there must be an object created.
	<b>post:</b>the new addres will be saved.
	@param: adress
	*/
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	/*
	name: getAdress
	allows to obtain the addres.
	<b>pre:</b>the addres of the Client must be entered
	<b>post:</b>obtain the addres of the client saved in variable "adress"
	*/
	public String getAdress() {
		return adress;
	}
	
	/*
	name: toString
	Allows you to print a customer's information on the screen
	<b>pre:</b>there must be an object created.
	<b>post:</b>show the information of the client.
	*/
	public String toString() {
		String infoClient = "Nombre completo: " + name + " " + lastName;
		infoClient += "\nTipo y numero de indentificacion: " + typeIdentification + " - " + numberIdentification;
		infoClient += "\nDireccion y numero : " + adress + " - " + phone;
		return infoClient;
	}
	
	/*
	name: compareTo
	Allows you to compare two objects of type customer using criteria
	<b>pre:</b>there must be an object created.
	<b>post:</b>organized clients.
	@param: client
	*/
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