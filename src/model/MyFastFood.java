package model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import exceptions.NitRestaurantNotExistException;
import exceptions.DocumentClientNotExistException;
import exceptions.CodeOrderNotExistException;

public class MyFastFood{
	
	private ArrayList<Restaurant> restaurants;
	private ArrayList<Client> clients;
	private ArrayList<Order> orders;
	
	public MyFastFood() {
		restaurants = new ArrayList<Restaurant>();
		clients = new ArrayList<Client>();
		orders = new ArrayList<Order>();
	}
	
	public List<Restaurant> getRestaurants(){
		return restaurants;
	}
	
	public List<Client> getClients(){
		return clients;
	}
	
	public List<Order> getOrder(){
		return orders; 
	}
	
	public String getInfoRestaurants(){
		String infoRestaurant;
		if(!restaurants.isEmpty()) {
			infoRestaurant = "LOS RESTAURANTES INGRESADOS EN LOS DATOS SON:\n";
			for(int i = 0; i<restaurants.size(); i++) {
				infoRestaurant += "-Restaurante #" + (i+1) + "\n" + restaurants.get(i).toString() + "\n";
			}
		}else {
			infoRestaurant = "No hay restaurantes ingresados en el sistema";
		}
		return infoRestaurant;
	}
	
	public String getInfoRestaurantsAndProducts() {
		String infoRestaurantWithProducts;
		if(!restaurants.isEmpty()) {
			infoRestaurantWithProducts = "LOS RESTAURANTES INGRESADOS EN LOS DATOS SON:\n";
			for(int i = 0; i<restaurants.size(); i++) {
				infoRestaurantWithProducts += "\n-Restaurante #" + (i+1) + "\n" + restaurants.get(i).toString();
				if(!restaurants.get(i).getProducts().isEmpty()) {
					infoRestaurantWithProducts += restaurants.get(i).getInfoProducts();
				}else {
					infoRestaurantWithProducts += "Este restaurante no posee productos"; 
				}
			}
		}else {
			infoRestaurantWithProducts = "No hay restaurantes ingresados en el sistema";
		}
		return infoRestaurantWithProducts;
	}
	
	public String getInfoProductsOfRestaurant(int num) {
		String infoProducts;		
		if(restaurants.get(num-1).getProducts().isEmpty() == false) {
			infoProducts = "\nLOS PRODUCTOS DEL RESTAURANTE SON:\n";
			for(int i = 0; i < restaurants.get(num-1).getProducts().size(); i++) {
				String nameProduct = restaurants.get(num-1).getProducts().get(i).getName();
				String nitProduct = restaurants.get(num-1).getProducts().get(i).getNitRestaurant();
				infoProducts += (i+1) + ") " + nameProduct + " - " + nitProduct + "\n"; 
			}
		}else{
			infoProducts = "No hay productos disponibles en el restaurante";
		}
		return infoProducts;
	}
	
	/*prueba*/
	public String getInfoClients() {
		String infoClients;
		if(clients.isEmpty() == false) {
			infoClients = "\nLOS CLIENTES INGRESADOS SON:\n";
			for(int i = 0; i < clients.size(); i++) {
				infoClients += "\n======================================" + "\n-Cliente numero " + (i+1) + "\n";
				infoClients += clients.get(i).toString(); 
			}
		}else{
			infoClients = "No hay clientes ingresados";
		}
		return infoClients;
	}
	
	public String getInfoOrder() {
		String infoOrder = "\nLAS ORDENES EN TRANSCURSO SON:\n";
		for(int i = 0; i < orders.size();i++) {
			infoOrder += "\nOrden numero = " + (i+1) + "\n";
			infoOrder += orders.get(i).toString();
		}
		return infoOrder;
	}
	
	public void addNewRestaurant(String nameRestaurant, String nit, String nameAdministrator) {
		Restaurant newRestaurant = new Restaurant(nameRestaurant, nit, nameAdministrator);
		restaurants.add(newRestaurant);
	}
	
	public void addNewProduct(int numRestaurant, Product newProduct) {
		restaurants.get(numRestaurant-1).addProduct(newProduct);
	}
	
	public void addNewClient(String document, String numberIdentification, String lastName ,String name , String phone, String adress) {
		Client newClient = new Client(document, numberIdentification ,lastName ,name ,phone ,adress);
		if(clients.isEmpty()) {
			clients.add(newClient);
		}else {
			int i = 0;
			while(clients.get(i).compareTo(newClient) > 0) {
				i++;
			}
			clients.add(i, newClient);
		}
	}
	
	public void addNewOrder(String code, Date date, String codeClient, String nitRestaurant) {
		Order newOrder = new Order(code,date,codeClient,nitRestaurant);
		orders.add(newOrder);
	}
	
	public String assignDocument(int numberTypeDocument) {
		String document = "";
		switch(numberTypeDocument){
			case 1: 
				document = "Tarjeta de identidad"; 
			break;
			case 2:
				document = "Cedula de ciudadania";
			break;
			case 3:
				document = "Pasaporte";
			break;
			case 4:
				document = "Cedula extranjera";
			break;
		}
		return document;
	}
	
	public int numRam() {
		Random random = new Random();
		int low = 100000, high = 999999;
		int randomNumber = random.nextInt(high-low)+low;
		return randomNumber;
	}
	
	public String assingIdentificationClientToOrder(int num) {
		String numIdentification = clients.get(num-1).getNumberIdentification();
		return numIdentification;
	}
	
	public String assignNit(int numRestaurant) {
		String nit;
		nit = restaurants.get(numRestaurant-1).getNit();
		return nit;
	} 
	
	public boolean codeValid(String numero) {
		boolean repeat = false;
		for(int i = 0; i < orders.size(); i++) {
			if(numero.equalsIgnoreCase(orders.get(i).getCode())) {
				repeat = true;
			}
		}
		return repeat;
	}
	
	public String assingNitRestaurantToOrder(int num) {
		String nitRestaurant = restaurants.get(num-1).getNit();
		return nitRestaurant;
	}
	
	public String assingCodeProductToOrder(int num, int num2) {
		String infoProduct = restaurants.get(num-1).getProducts().get(num2-1).getCode();
		return infoProduct;
	}
	
	public String assingNameProductToOrder(int numRestaurant, int numProduct) {
		String nameProduct = restaurants.get(numRestaurant-1).getProducts().get(numProduct-1).getName();
		return nameProduct;
	}
	
	public void addProductsToOrder(String codeOrder, String nameProduct, String quantity) {
		int i = 0;
		while(!getOrder().get(i).getCode().equalsIgnoreCase(codeOrder)) {
			i++;
		}
		String productToAdded [] = {nameProduct, quantity};
		getOrder().get(i).addProducts(productToAdded);
	}
	
	/*metodos utilizados para upgrate*/
	
	public void confirmNit(String nit) throws NitRestaurantNotExistException {
		boolean exist = false;
		for(int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equalsIgnoreCase(nit)) {
				exist = true; 
			}
		}
		if(exist == false) {
			throw new NitRestaurantNotExistException();
		}
	}
	
	public int getPosRestaurant(String nit) {
		int posRestaurant;
		int i = 0;
		while(!restaurants.get(i).getNit().equalsIgnoreCase(nit)) {
			i++;
		}
		posRestaurant = i;
		return posRestaurant;
	}
	
	public void comfirmNumberIdentity(String numberDocument) throws DocumentClientNotExistException{
		boolean exist = false;
		for(int i = 0; i < clients.size(); i++) {
			if(clients.get(i).getNumberIdentification().equalsIgnoreCase(numberDocument)) {
				exist = true;
			}
		}
		if(exist == false) {
			throw new DocumentClientNotExistException();
		}
	}
	
	public int getPosClient(String numberDocument) {
		int posClient;
		int i = 0;
		while(!clients.get(i).getNumberIdentification().equalsIgnoreCase(numberDocument)) {
			i++;
		}
		posClient = i;
		return posClient;
	}
	
	public void comfirmCodeOrder(String codeOrder) throws CodeOrderNotExistException{
		boolean exist = false;
		for(int i = 0; i<orders.size(); i++) {
			if(orders.get(i).getCode().equalsIgnoreCase(codeOrder)) {
				exist = true;
			}
		}
		if(exist == false) {
			throw new CodeOrderNotExistException();
		}
	}
	
	public int getPosOrder(String code) {
		int posOrder;
		int i = 0;
		while(!orders.get(i).getCode().equalsIgnoreCase(code)) {
			i++;
		}
		posOrder = i;
		return posOrder;
	}
	
}
