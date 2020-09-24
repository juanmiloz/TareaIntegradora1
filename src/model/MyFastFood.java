package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import exceptions.NitRestaurantExistException;
import exceptions.CodeProductExistException;
import java.util.Random;
import exceptions.NitRestaurantNotExistException;
import exceptions.NumClientInvalidException;
import exceptions.NumProductsInvalidException;
import exceptions.NumRestaurantInvalidException;
import exceptions.NumberIdentificationNotExistException;
import exceptions.StatusInvalidException;
import model.comparators.NumberClientComparator;
import exceptions.CodeProductNotExistException;
import exceptions.DocumentClientNotExistException;
import exceptions.EqualsStatusException;
import exceptions.CodeOrderExistException;
import exceptions.CodeOrderNotExistException;
import exceptions.NameClientNotExistException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class MyFastFood{
	
	private final static String FILE_NAME = "data/ordersRegister.csv";
	private final static String FILE_DATA_RESTAURANT = "data/dataRestaurants.csv";
	private final static String FILE_DATA_PRODUCTS = "data/dataProducts.csv";
	private final static String FILE_DATA_CLIENTS = "data/dataClients.csv";
	private final static String FILE_DATA_ORDERS = "data/dataOrder.csv";
	private final static String FILE_SERIALIZABLE_RESTAURANT = "data/seriarizableRestaurants.ap2";
	private final static String FILE_SERIALIZABLE_CLIENTS = "data/seriarizableClients.ap2";
	private final static String FILE_SERIALIZABLE_ORDERS = "data/seriarizableOrders.ap2";
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
		if(!restaurants.get(num-1).getProducts().isEmpty()) {
			infoProducts = "\nLOS PRODUCTOS DEL RESTAURANTE SON:";
			for(int i = 0; i<restaurants.get(num-1).getProducts().size(); i++) {
				infoProducts += "\nProducto numero = " + (i+1);
				infoProducts += restaurants.get(num-1).getProducts().get(i).toString();
			}
		}else {
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
		String infoOrder;
		if(!orders.isEmpty()) {
			infoOrder = "\nLAS ORDENES EN TRANSCURSO SON:\n";
			for(int i = 0; i < orders.size();i++) {
				infoOrder += "\nOrden numero = " + (i+1) + "\n";
				infoOrder += orders.get(i).toString();
			}
		}else {
			infoOrder = "No hay ordenes ingresadas en el sistema";
		}
		return infoOrder;
	}
	
	public void confirmNotRepeatNitRestaurant(String nit) throws NitRestaurantExistException{
		boolean exist = false;
		for(int i = 0; i < restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equalsIgnoreCase(nit)) {
				exist = true;
			}
		}
		if(exist) {
			throw new NitRestaurantExistException();
		}
	}
	
	public void addNewRestaurant(String nameRestaurant, String nit, String nameAdministrator) {
		Restaurant newRestaurant = new Restaurant(nameRestaurant, nit, nameAdministrator);
		restaurants.add(newRestaurant);
	}
	
	public void confirmNotExistCodeProducto(String code, int numRestaurant) throws CodeProductExistException{
		boolean exist = false;
		numRestaurant = numRestaurant-1;
		for(int i = 0; i < restaurants.get(numRestaurant).getProducts().size(); i++) {
			if(restaurants.get(numRestaurant).getProducts().get(i).getCode().equalsIgnoreCase(code)) {
				exist = true;
			}
		}
		if(exist) {
			throw new CodeProductExistException();
		}
	}
	
	public void saveDataRestaurants() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SERIALIZABLE_RESTAURANT));
		oos.writeObject(restaurants);
		oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadDataRestaurant() throws IOException, ClassNotFoundException{	
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_SERIALIZABLE_RESTAURANT));
		restaurants = (ArrayList<Restaurant>)ois.readObject();
		ois.close();
	}
	
	public void addNewProduct(int numRestaurant, Product newProduct) {
		restaurants.get(numRestaurant-1).addProduct(newProduct);
	}
	
	public void confirmNotExistIdClient(String numberDocument) throws NumberIdentificationNotExistException{
		boolean exist = false;
		for(int i = 0; i<clients.size(); i++) {
			if(clients.get(i).getNumberIdentification().equalsIgnoreCase(numberDocument)) {
				exist = true;
			}
		}
		if(exist) {
			throw new NumberIdentificationNotExistException();
		}
	}
	
	public void addNewClient(String document, String numberIdentification, String lastName ,String name , String phone, String adress) {
		Client newClient = new Client(document, numberIdentification ,lastName ,name ,phone ,adress);
		if(clients.isEmpty()) {
			clients.add(newClient);
		}else {
			int i = 0;
			while(i<clients.size() && newClient.compareTo(clients.get(i))<0 ) {
				i++;
			}
			clients.add(i, newClient);
		}
	}
	
	public void saveDataClients() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SERIALIZABLE_CLIENTS));
		oos.writeObject(clients);
		oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadDataClients() throws IOException, ClassNotFoundException{	
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_SERIALIZABLE_CLIENTS));
		clients = (ArrayList<Client>)ois.readObject();
		ois.close();
	}
	
	public void addNewOrder(String code, Date date, String codeClient, String nitRestaurant, String status) {
		Order newOrder = new Order(code,date,codeClient,nitRestaurant,status);
		orders.add(newOrder);
	}
	
	public void saveDataOrders() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SERIALIZABLE_ORDERS));
		oos.writeObject(orders);
		oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadDataOrder() throws IOException, ClassNotFoundException{	
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_SERIALIZABLE_ORDERS));
		orders = (ArrayList<Order>)ois.readObject();
		ois.close();
	}
	
	public void confirmNumberProducts(String nitRestaurant, int numProducts) throws NumProductsInvalidException{
		for(int i = 0; i<restaurants.size(); i++) {
			if(restaurants.get(i).getNit().equalsIgnoreCase(nitRestaurant)) {
				if(numProducts>restaurants.get(i).getProducts().size()) {
					throw new NumProductsInvalidException();
				}
			}
		}
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
	
	public String assingIdentificationClientToOrder(int num) throws NumClientInvalidException{
		String numIdentification = null;
		if(num <= clients.size()) {
			numIdentification = clients.get(num-1).getNumberIdentification();
		}else {
			throw new NumClientInvalidException();
		}
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
	
	public String assingNitRestaurantToOrder(int num) throws NumRestaurantInvalidException{
		String nitRestaurant = null;
		if(num <= restaurants.size()) {
			nitRestaurant = restaurants.get(num-1).getNit();
		}else {
			throw new NumRestaurantInvalidException();
		}
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
	
	public boolean productsEmpty() {
		boolean empty = false;
		for(int i = 0; i < restaurants.size(); i++) {
			if(!restaurants.get(i).getProducts().isEmpty()) {
				empty = true; 
			}
		}
		return empty;
	}
	
	public void confirmCodeProduct(String codeProduct, int numRestaurant) throws CodeProductNotExistException{
		boolean exist = false;
		for(int i = 0; i < restaurants.get(numRestaurant).getProducts().size(); i++) {
			if(restaurants.get(numRestaurant).getProducts().get(i).getCode().equalsIgnoreCase(codeProduct)) {
				exist = true;
			}
		}
		if(exist == false) {
			throw new CodeProductNotExistException();
		}
	}
	
	public int getPosProduct(String codeProduct, int numRestaurant) {
		int posProduct; 
		int i = 0;
		while(!restaurants.get(numRestaurant).getProducts().get(i).getCode().equalsIgnoreCase(codeProduct)) {
			i++;
		}
		posProduct = i;
		return posProduct;
	}
	
	public void confirmNumberIdentity(String numberDocument) throws DocumentClientNotExistException{
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
	
	public void confirmCodeOrder(String codeOrder) throws CodeOrderNotExistException{
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
	
	public void upgradeStatus(int pos, int numNewStatus)throws StatusInvalidException, EqualsStatusException {
		pos = pos-1;
		String statusActual = orders.get(pos).getStatus();
		int statusActualNum;
		if(statusActual.equalsIgnoreCase("EN PROCESO")) {
			statusActualNum = 2;
		}else if(statusActual.equalsIgnoreCase("ENVIADO")) {
			statusActualNum = 3;
		}else if(statusActual.equalsIgnoreCase("ENTREGADO")) {
			statusActualNum = 4;
		}else {
			statusActualNum = 1;
		}
		if(statusActualNum < numNewStatus) {
			assingNewStatus(pos,numNewStatus);
		}else if(statusActualNum < numNewStatus) {
			throw new StatusInvalidException();
		}else {
			throw new EqualsStatusException();
		}
	}
	
	public void assingNewStatus(int pos, int numNewStatus) {
		if(numNewStatus == 2) {
			orders.get(pos).setStatus("EN PROCESO");
		}else if(numNewStatus == 3) {
			orders.get(pos).setStatus("ENVIADO");
		}else if(numNewStatus == 4) {
			orders.get(pos).setStatus("ENTREGADO");
		}
	}
	
	public String getRestaurantsInOrden() {
		String organizedRestaurant = "";
		ArrayList<Restaurant> organizedRestaurants = restaurants;
		Collections.sort(restaurants);
		for(int i = 0; i < organizedRestaurants.size(); i++) {
			organizedRestaurant += "\n-Restaurante #" + (i+1) + "\n" + organizedRestaurants.get(i).toString();
		}
		return organizedRestaurant;
	}
	
	public String getClientsInOrden() {
		String organizedClients = "";
		ArrayList<Client> organizedClientByNumber = clients;
		NumberClientComparator ncc = new NumberClientComparator();
		Collections.sort(organizedClientByNumber, ncc);
		for(int i = 0; i<organizedClientByNumber.size(); i++) {
			organizedClients += "\n-Cliente #" + (i+1) + "\n" + organizedClientByNumber.get(i).toString();
		}
		return organizedClients;
	}
	
	public void confirmExistName(String name) throws NameClientNotExistException{
		boolean exist = false;
		for(int i = 0; i<clients.size(); i++) {
			if(clients.get(i).getName().equalsIgnoreCase(name)) {
				exist = true;
			}
		}
		if(exist == false) {
			throw new NameClientNotExistException();
		}
	} 
	
	public String searchName(String name) {
		String infoName = "";
		name = name.trim();
		name = name.toLowerCase();
		boolean exist = false;
		int start = 0;
		int end = clients.size()-1;
		
		while(start <= end && !exist) { 
			int medium = (start+end)/2;
			String otherName = clients.get(medium).getName();
			otherName = otherName.toLowerCase();
			if(name.compareTo(otherName) == 0) {
				exist = true;
				infoName = clients.get(medium).toString();
			}else if(name.compareTo(otherName) < 0) {
				start = medium+1;
			}else {
				end = medium-1;
			}
		}
		return infoName;
	}
	
	//Con dudas
	public void exportRegisterOrder(String s) throws FileNotFoundException{
		PrintWriter pw = new PrintWriter(FILE_NAME);
		ArrayList<Order> ordersOrganized = orders;
		Collections.sort(ordersOrganized);
		s = s.trim();
		pw.println("Codigo orden" + s + "Fecha" + s + "Numero documento cliente" + s + "Nit restaurante" + s + "Nombre producto" + s + "Cantidad producto" + s + "estatus orden");
		for(int i = 0; i<orders.size();i++) {
			Order currOrder = ordersOrganized.get(i);
			for(int j = 0; j < currOrder.getProductsOrder().size(); j++) {
				String[] array = currOrder.getProductsOrder().get(j);
				String nameProduct = array[0];
				String quantity = array[1];
				pw.println(currOrder.getCode() + s + currOrder.getDate() + s + currOrder.getCodeClient() + s + currOrder.getNitRestaurant() + s + nameProduct + s + quantity + s + currOrder.getStatus());
			}
		}
		pw.close();
	}
	
	public String importDataRestaurants() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(FILE_DATA_RESTAURANT));
		String line = br.readLine();
		String message = "";
		int cont = -1;
		while(line != null) {
			String [] partsLine = line.split(",");
			cont += 1;
			try {
				if(!restaurants.isEmpty()) {
					confirmNotRepeatNitRestaurant(partsLine[1]);
				}
				addNewRestaurant(partsLine[0], partsLine[1], partsLine[2]);
			}catch(NitRestaurantExistException nre) {
				message += "El nit del restaurante en la fila " + cont + " ya existe en el sistema\n";
			}
			line = br.readLine();
		}
		br.close();
		return message;
	}
	
	public void importDataProducts() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(FILE_DATA_PRODUCTS));
		String line = br.readLine();
		while(line != null) {
			String [] partsLine = line.split(",");
			double cost = Double.parseDouble(partsLine[3]);
			Product newProduct = new Product(partsLine[0],partsLine[1],partsLine[2],cost,partsLine[4]);
			for(int i = 0; i < restaurants.size();i++) {
				if(restaurants.get(i).getNit().equalsIgnoreCase(partsLine[4])) {
					restaurants.get(i).addProduct(newProduct);
				}
			}
			line = br.readLine();
		}
		br.close();
	}
	
	public String importDataClients() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(FILE_DATA_CLIENTS));
		String line = br.readLine();
		String message = "";
		int cont = -1;
		while(line != null) {
			String [] partsLine = line.split(",");
			cont += 1;
			try {
				if(!clients.isEmpty()) {
					confirmNotExistIdClient(partsLine[1]);
				}
				addNewClient(partsLine[0], partsLine[1], partsLine[2], partsLine[3], partsLine[4], partsLine[5]);
			}catch(NumberIdentificationNotExistException nine) {
				message += "El numero de identificacion del cliente en la fila " + cont + " ya existe en el sistema";
			}
			line = br.readLine();
		}
		br.close();
		return message;
	}
	
	public void confirmNotExistOrder(String codeOrder) throws CodeOrderExistException{
		boolean exist = false;
		for(int i = 0; i<orders.size(); i++) {
			if(orders.get(i).getCode().equalsIgnoreCase(codeOrder)) {
				exist = true;
			}
			if(exist) {
				throw new CodeOrderExistException();
			}
		}
	}
	
	private Date ParseFecha(String fecha){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } catch (ParseException ex){
            fechaDate = null;
        }
        return fechaDate;
    }
	
	public String importDataOrders() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(FILE_DATA_ORDERS));
		String line = br.readLine();
		String message = "";
		int cont = -1;
		while(line != null) {
			String [] partsLine = line.split(",");
			cont += 1;
			try {
				if(!orders.isEmpty()) {
					confirmNotExistOrder(partsLine[1]);
				}
				Date date = ParseFecha(partsLine[1]);
				addNewOrder(partsLine[0], date, partsLine[2], partsLine[3], partsLine[6]);
				addProductsToOrder(partsLine[0], partsLine[4], partsLine[5]);
			}catch(CodeOrderExistException coe) {
				message += "El numero de orden en la fila " + cont + " ya existe en el sistema";
			}
			line = br.readLine();
		}
		br.close();
		return message;
	}
	
	public void loadDataMyFastFood() throws ClassNotFoundException, IOException {	
		loadDataRestaurant();
		loadDataClients();
		loadDataClients();
	}
}
