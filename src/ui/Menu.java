package ui;
import model.*;
import java.util.Scanner;
import java.util.Date;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.IndexOutOfBoundsException;
import exceptions.NitRestaurantNotExistException;
import exceptions.NumClientInvalidException;
import exceptions.NumProductNotExist;
import exceptions.NumProductsInvalidException;
import exceptions.NumRestaurantInvalidException;
import exceptions.NumberIdentificationNotExistException;
import exceptions.StatusInvalidException;
import exceptions.CodeProductNotExistException;
import exceptions.DocumentClientNotExistException;
import exceptions.EqualsStatusException;
import exceptions.NameClientNotExistException;
import exceptions.NitRestaurantExistException;
import exceptions.CodeOrderNotExistException;
import exceptions.CodeProductExistException;

public class Menu {
	
	private final static int NEW_RESTAURANT = 1;
	private final static int NEW_PRODUCT = 2;
	private final static int NEW_CLIENT = 3;
	private final static int NEW_ORDER = 4;
	private final static int NEW_ACTUALIZATION = 5;
	private final static int VISUALIZE = 6;
	private final static int SEARCH_CLIENT = 7;
	private final static int EXPORT_ORDERS_REPORT = 8; 
	private final static int IMPORT = 9;
	private Scanner in = new Scanner(System.in);
	private MyFastFood myFastFood = new MyFastFood();
	
	/*
	name: menuPrincipal
	show the principal menu
	*/
	public void menuPrincipal() {
		int option;
		do {
			System.out.println("===========================");
			System.out.println("|BIENVENIDO A MY FAST FOOD|");
			System.out.println("===========================");
			System.out.println("\nQue opción desea realizar?");
			System.out.println("(1)<---Ingresar un nuevo restaurante");
			System.out.println("(2)<---Ingresar un nuevo producto");
			System.out.println("(3)<---Ingresar un nuevo cliente");
			System.out.println("(4)<---Hacer un nuevo pedido");
			System.out.println("(5)<---Realizar una actualizacion");
			System.out.println("(6)<---Mostrar en pantalla...");
			System.out.println("(7)<---Buscar un cliente");
			System.out.println("(8)<---Exportar registro de pedidos");
			System.out.println("(9)<---Importar...");
			System.out.println("(10)<---Mostrar Toda la Informacion(No hacia parte de la tarea)");
			System.out.println("(11)<---Cerrar programa");
			option = Integer.parseInt(in.nextLine());
			
			switch(option) {
				case NEW_RESTAURANT:
					newRestaurant();
				break;
				
				case NEW_PRODUCT:
					newProduct();
				break;
				
				case NEW_CLIENT:
					newClient();
				break;
				
				case NEW_ORDER:
					newOrder();
				break;
				
				case NEW_ACTUALIZATION:
					newActualization();
				break;
				
				case VISUALIZE:
					visualize();
				break;
				
				case SEARCH_CLIENT:
					searchClient();
				break;
				
				case EXPORT_ORDERS_REPORT:
					exportOrdesReport();
				break; 
				
				case IMPORT:
					importData();
				break;
				
				case 10:
					showAll();
				break;
			}
		}while( option < 11);
	}
	
	/*
	name: newRestaurant
	register a new restaurant
	*/
	public void newRestaurant() {
		try{
			System.out.println("\nIngrese el nombre del restaurante");
			String nameRestaurant = in.nextLine();
			System.out.println("Ingrese el nit del restaurante");
			String nit = in.nextLine();
			myFastFood.confirmNotRepeatNitRestaurant(nit);
			System.out.println("Ingrese el nombre del administrador");
			String nameAdministrator = in.nextLine();
			myFastFood.addNewRestaurant(nameRestaurant, nit, nameAdministrator);
			myFastFood.saveDataRestaurants();
			System.out.println("El restaurante se añadio en la lista y se guardo exitosamente");
		}catch(InputMismatchException ime) {
			System.err.println("Ingrese datos validos");
		}catch(NitRestaurantExistException nre) {
			System.err.println("El nit que ingreso ya existe en el sistema");
		}catch(IOException ioe) {
			System.err.println("Los datos no pueden ser cargados");
		}
	}
	
	/*
	name: newProduct
	register a new product
	*/
	public void newProduct() {
		try{
			System.out.println("Ingrese el codigo del producto");
			String codeProduct = in.nextLine();
			System.out.println("Ingrese el nombre del producto");
			String nameProduct = in.nextLine();
			System.out.println("Ingrese la descripcion del producto");
			String description = in.nextLine();
			System.out.println("Ingrese el costo del producto");
			Double cost = Double.parseDouble(in.nextLine());
			System.out.println(myFastFood.getInfoRestaurants());
			if(!myFastFood.getInfoRestaurants().equalsIgnoreCase("no hay restaurantes disponibles")) {
				System.out.println("Que restaurante tendra este producto?(ingrese el numero)");
				int numberRestaurant = Integer.parseInt(in.nextLine());
				myFastFood.confirmNotExistCodeProducto(codeProduct, numberRestaurant);
				String nit = myFastFood.assignNit(numberRestaurant);
				Product newProduct = new Product(codeProduct, nameProduct, description,cost,nit);
				myFastFood.addNewProduct(numberRestaurant, newProduct);
				myFastFood.saveDataRestaurants();
				System.out.println("El producto fue añadido y guardado exitosamente al restaurante con numero de nit " + nit);
			}
		}catch(NumberFormatException nfe) {
			System.err.println("Ingrese datos validos");
		}catch(IndexOutOfBoundsException ibe) {
			System.err.println("El restaurante que ingreso no existe");
		}catch(CodeProductExistException cpe) {
			System.err.println("El codigo del producto que ingreso ya existe");
		}catch(IOException ioe) {
			System.err.println("Los datos no pueden ser cargados"); 
			ioe.printStackTrace();
		}
	}
	
	/*
	name: newClient
	register a new client
	*/
	public void newClient() {
		boolean repeat;
		String document = "";
		try {
			do {
				System.out.println("Ingrese el numero del tipo de identificacion que posea");
				System.out.println("1<---Tarjeta de Identidad\n2<---Cedula Ciudadania\n3<---Pasaporte\n4<---Cedula Extranjera\n");
				int typeIdentification = Integer.parseInt(in.nextLine());
				if(typeIdentification == 1 || typeIdentification == 2 || typeIdentification == 3 || typeIdentification == 4){
					document = myFastFood.assignDocument(typeIdentification);
					repeat = false; 
				}else {
					repeat = true;
					System.err.println("Ingrese un numero valido");
				}
			}while(repeat);
			System.out.println("Ingrese el numero de identificacion");
			String numberIdentification = in.nextLine();
			myFastFood.confirmNotExistIdClient(numberIdentification);
			System.out.println("Ingrese el nombre");
			String name = in.nextLine();
			System.out.println("Ingrese el apellido");
			String lastName = in.nextLine();
			System.out.println("Ingrese el telefono");
			String phone = in.nextLine();
			System.out.println("Ingrese la direccion");
			String address = in.nextLine();
			myFastFood.addNewClient(document, numberIdentification, name, lastName , phone, address);
			myFastFood.saveDataClients();
			System.out.println("El cliente fue añadido y guardado exitosamente");
		}catch(NumberFormatException nfe) {
			System.err.println("Ingrese valores validos porfavor");
		}catch(NumberIdentificationNotExistException nine) {
			System.err.println("El numero de identificacion ya esta registrado con otro cliente");
		}catch(IOException ioe) {
			System.err.println("Los datos no pueden ser cargados");
		}
	}
	
	/*
	name: newOrder
	ask for some information for an order
	*/
	public void newOrder() {
		boolean repeat = true;
		String numero;
		do {
			int numRam = myFastFood.numRam();
			numero = String.valueOf(numRam);
			repeat = myFastFood.codeValid(numero);
			System.out.println("El codigo de la orden es: " + numero);
		}while(repeat);
		Date date = new java.util.Date();
		System.out.println("la fecha de la orden es: " + date);
		try {
			if(!myFastFood.getClients().isEmpty()) {
				String infoClient = myFastFood.getInfoClients();
				System.out.println(infoClient);
				System.out.println("Ingrese el numero del cliente que desea desarrollar la orden?");
				int posIdClient = Integer.parseInt(in.nextLine());
				String idClient = myFastFood.assingIdentificationClientToOrder(posIdClient);
				if(!myFastFood.getRestaurants().isEmpty()) {
					String infoRestaurants = myFastFood.getInfoRestaurants();
					System.out.println(infoRestaurants);
					System.out.println("Ingrese el numero del restaurante al que hara la orden");
					int posNitRestaurant = Integer.parseInt(in.nextLine());
					String nitRestaurant = myFastFood.assingNitRestaurantToOrder(posNitRestaurant);
					continueOrder(numero,date,idClient,nitRestaurant,posNitRestaurant);
				}else {
					System.out.println("No hay restaurantes ingresados");
				}
			}else {
				System.out.println("No hay clientes ingresados");
			}
		}catch(NumClientInvalidException ncie) {
			System.err.println("El cliente que intento elegir no existe");
		}catch(NumRestaurantInvalidException nrie) {
			System.err.println("El restaurante que intento elegir no existe");
		}
	}
	
	/*
	name: continueOrder 
	register a new order
	*/
	public void continueOrder(String numRamdom, Date date, String idClient, String nitRestaurant, int x) {
		try {
			if(!myFastFood.getRestaurants().get(x-1).getProducts().isEmpty()) {
				String infoProducts = myFastFood.getInfoProductsOfRestaurant(x);
				System.out.println(infoProducts);
				System.out.println("Cuantos productos diferentes desea llevar?");
				int numOfProductsToOrder = Integer.parseInt(in.nextLine());
				myFastFood.confirmNumberProducts(nitRestaurant, numOfProductsToOrder);
				String status = "SOLICITADO";
				myFastFood.addNewOrder(numRamdom, date, idClient, nitRestaurant, status);
				for(int i = 0; i < numOfProductsToOrder; i++) {
					System.out.println("Ingrese el numero del producto el cual desea llevar");
					int numProduct = Integer.parseInt(in.nextLine());
					myFastFood.confirmProductNotExist(nitRestaurant, numProduct);
					String nameProduct = myFastFood.assingNameProductToOrder(x, numProduct);
					System.out.println("Ingrese la cantidad de unidades que desea llevar");
					int quantity = Integer.parseInt(in.nextLine());
					String quantityToOrder = String.valueOf(quantity);
					myFastFood.addProductsToOrder(numRamdom, nameProduct, quantityToOrder);
				}
				myFastFood.saveDataOrders();
				System.out.println("La orden fue añadida y guardada exitosamente");
			}else {
				System.out.println("No hay productos ingresados");
			}
		}catch(NumProductsInvalidException npie) {
			System.err.println("El numero de productos diferentes que desea llevar excede la cantidad de productos que tiene el restaurante");
		}catch(IOException ioe) {
			System.err.println("Los datos no pueden ser cargados");
		}catch(NumProductNotExist npne) {
			System.err.println("El numero de producto que ingreso no es valido");
		}
	}
	
	/*
	name: newActualization
	show the menu of actualizations
	*/
	public void newActualization() {
		System.out.println("\n");
		System.out.println("=========================");
		System.out.println("|MENU DE ACTUALIZACIONES|");
		System.out.println("=========================");
		System.out.println("Que elemento desea actualizar?");
		System.out.println("(1)<---Actualizar datos de un restaurante");
		System.out.println("(2)<---Actualizar datos de un poducto");
		System.out.println("(3)<---Actualizar datos de un cliente");
		System.out.println("(4)<---Actualizar datos de una orden");
		System.out.println("(5)<---Actualizar estado del pedido");
		int options = Integer.parseInt(in.nextLine());
		
		switch(options) {
			case 1:
				upgrateDataRestaurant();
			break;
			case 2: 
				upgrateDataProduct();
			break;
			case 3:
				upgrateDataClient();
			break;
			case 4:
				upgrateDataOrder();
			break;
			case 5:
				upgrateStatusOrder();
			break; 
			default:
				System.out.println("Debe ingresar una opcion valida");
			break;
		}
	}
	
	/*
	name: upgrateDataRestaurant
	show the submenu of actualization of restaurant
	*/
	public void upgrateDataRestaurant() {
		try {
			if(!myFastFood.getInfoRestaurants().isEmpty()) {
				String infoRestaurant = myFastFood.getInfoRestaurants();
				System.out.println( infoRestaurant + "\nIngrese el nit del restaurante que desea actualizar");
				String nitRestaurant = in.nextLine();
				myFastFood.confirmNit(nitRestaurant);
				int numPositionRestaurant = myFastFood.getPosRestaurant(nitRestaurant);
				System.out.println("Que desea actualizar?");
				System.out.println("(1)<---Nombre restaurante\n(2)<---Nombre administrador");
				int optionUpgrade = Integer.parseInt(in.nextLine());
				switch(optionUpgrade){
					case 1:
						upgrateNameRestaurant(numPositionRestaurant);
					break;
					
					case 2: 
						upgrateNameAdministratorRestaurant(numPositionRestaurant);
					break;
						
					default:
						System.out.println("Debe ingresar una opcion valida");
					break;
				}
			}else {
				System.out.println("No hay restaurantes en el sistema");
			}	
		}catch(NitRestaurantNotExistException nrde) {
			System.err.println("El nit del restaurante no existe");
		}	
	}
	
	/*
	name: upgrateNameRestaurant
	allows actualized the name of restaurant
	*/
	public void upgrateNameRestaurant(int posRestaurant) {
		System.out.println("Ingrese el nuevo nombre del restaurante");
		String newName = in.nextLine();
		myFastFood.getRestaurants().get(posRestaurant).setName(newName);
	}
	
	/*
	name: upgrateNameAdministraitorRestaurant
	allows actualized the name of administraitor of restaurant
	*/
	public void upgrateNameAdministratorRestaurant(int posRestaurant) {
		System.out.println("Ingrese el nuevo nombre del administrador del restaurante");
		String newNameAdministrator = in.nextLine();
		myFastFood.getRestaurants().get(posRestaurant).setNameAdministraitor(newNameAdministrator);
	}
	
	/*
	name: upgrateDataProduct
	show the submenu of actualization of product
	*/
	public void upgrateDataProduct() {
		try{
			if(myFastFood.productsEmpty()) {
				String infoRestaurants = myFastFood.getInfoRestaurants();
				System.out.println(infoRestaurants + "\nIngrese en que numero de restaurante se encuentra el producto que desea actualizar");
				int numRestaurant = Integer.parseInt(in.nextLine());
				String infoProductsOfRestaurant = myFastFood.getInfoProductsOfRestaurant(numRestaurant);
				System.out.println(infoProductsOfRestaurant + "\nIngrese el codigo del producto que desea actualizar");
				String codeProduct = in.nextLine();
				numRestaurant = numRestaurant-1;
				myFastFood.confirmCodeProduct(codeProduct, numRestaurant);
				int numPosProduct = myFastFood.getPosProduct(codeProduct, numRestaurant);
				System.out.println("Que desea actualizar");
				System.out.println("(1)<---Nombre del producto\n(2)<---Descripcion del producto\n(3)<---Costo del producto");
				int optionProduct = Integer.parseInt(in.nextLine());
				switch(optionProduct) {
					case 1:
						upgrateNameProduct(numPosProduct, numRestaurant);
					break;
					
					case 2:
						upgrateDescriptionProduct(numPosProduct, numRestaurant);
					break;
					
					case 3:
						upgrateCostProduct(numPosProduct, numRestaurant);
					break;
					
					default:
						System.out.println("Debe ingresar una opcion valida");
					break;
				}
			}else {
				System.out.println("No hay productos ingresados en el sistema");
			}
		}catch(CodeProductNotExistException cpde) {
			System.err.println("El codigo del producto ingresado no existe");
		}catch(java.lang.IndexOutOfBoundsException iobe) {
			System.err.println("Debe seleccionar un restaurante que exista");
		}
	}
	
	/*
	name: upgrateNameProduct
	allows actualized the name of product
	*/
	public void upgrateNameProduct(int posProduct, int numRestaurant) {
		System.out.println("Ingrese el nuevo nombre del producto");
		String newNameProduct = in.nextLine();
		myFastFood.getRestaurants().get(numRestaurant).getProducts().get(posProduct).setName(newNameProduct);
	}
	
	/*
	name: upgrateDescription
	allows actualized the description of product
	*/
	public void upgrateDescriptionProduct(int posProduct, int numRestaurant) {
		System.out.println("Ingrese la nueva descripcion del producto");
		String newDescriptionProduct = in.nextLine();
		myFastFood.getRestaurants().get(numRestaurant).getProducts().get(posProduct).setDescription(newDescriptionProduct);
	}
	
	/*
	name: upgrateCostproduct
	allows actualized the cost of product
	*/
	public void upgrateCostProduct(int posProduct, int numRestaurant) {
		System.out.println("Ingrese el nuevo costo del producto");
		Double newCost = Double.parseDouble(in.nextLine());
		myFastFood.getRestaurants().get(numRestaurant).getProducts().get(posProduct).setCost(newCost);;
	}
	
	/*
	name: upgrateDataClient
	show the submenu of actualization of clients
	*/
	public void upgrateDataClient() {
		try{
			if(!myFastFood.getClients().isEmpty()) {
				String infoClient = myFastFood.getInfoClients();
				System.out.println( infoClient + "\nIngrese el numero del documento del cliente que desea actualizar");
				String numberDocument = in.nextLine();
				myFastFood.confirmNumberIdentity(numberDocument);
				int numPosClient = myFastFood.getPosClient(numberDocument);
				System.out.println("Que desea actualizar?");
				System.out.println("(1)<---Tipo de identificacion\n(2)<---Nombre del cliente\n(3)<---Apellido del cliente\n(4)<---Telefono\n(5)<---Adress");
				int optionUpgrade = Integer.parseInt(in.nextLine());
				switch(optionUpgrade) {
					case 1:
						upgrateTypeIdentificationClient(numPosClient);
					break;
					
					case 2:
						upgrateNameClient(numPosClient);
					break;
					
					case 3:
						upgrateApellidoClient(numPosClient);
					break;
						
					case 4:
						upgratePhoneClient(numPosClient);
					break;
						
					case 5:
						upgrateAdressClient(numPosClient);
					break;
					
					default:
						System.out.println("Debe ingresar una opcion valida");
					break;
				}
			}else {
				System.out.println("No hay clientes ingresados en el sistema");
			}
		}catch(DocumentClientNotExistException dcne) {
			System.err.println("El documento no existe en el sistema");
		}
	}
	
	/*
	name: upgrateTypeIdentificationClient
	allows actualized the type identification of the client
	*/
	public void upgrateTypeIdentificationClient(int posClient) {
		System.out.println("Ingrese el nuevo tipo de identificacion");
		System.out.println("1<---Tarjeta de Identidad\n2<---Cedula Ciudadania\n3<---Pasaporte\n4<---Cedula Extranjera\n");
		int numOption = Integer.parseInt(in.nextLine());
		String document = myFastFood.assignDocument(numOption);
		myFastFood.getClients().get(posClient).setTypeIdentification(document);
	}
	
	/*
	name: upgrateNameClient
	allows actualized the name of the client
	*/
	public void upgrateNameClient(int posClient) {
		System.out.println("Ingrese el nuevo nombre del cliente");
		String newName = in.nextLine();
		myFastFood.getClients().get(posClient).setName(newName);
	}
	
	/*
	name: upgrateApellidoClient
	allows actualized the last name of the client
	*/
	public void upgrateApellidoClient(int posClient) {
		System.out.println("Ingrese el nuevo apellido del cliente");
		String newLastName = in.nextLine();
		myFastFood.getClients().get(posClient).setLastName(newLastName);
	}
	
	/*
	name: upgratePhone
	allows actualized the phone of the client
	*/
	public void upgratePhoneClient(int posClient) {
		System.out.println("Ingrese el nuevo numero de telefono");
		String newPhone = in.nextLine();
		myFastFood.getClients().get(posClient).setPhone(newPhone);
	}
	
	/*
	name: upgrateAddresClient 
	allows actualized the addres of the client
	*/
	public void upgrateAdressClient(int posClient) {
		System.out.println("Ingrese la nueva direccion");
		String newAdress = in.nextLine();
		myFastFood.getClients().get(posClient).setAdress(newAdress);
	}
	
	/*
	name: upgrateDataOrder
	show the submenu of actualization of order
	*/
	public void upgrateDataOrder() {
		try{
			if(!myFastFood.getOrder().isEmpty()) {
				String infoOrder = myFastFood.getInfoOrder();
				System.out.println( infoOrder + "\nIngrese el codigo de la orden que desea actualizar");
				String codeOrder = in.nextLine();
				myFastFood.confirmCodeOrder(codeOrder);
				int numPosOrder = myFastFood.getPosOrder(codeOrder);
				System.out.println("Que desea actualizar?");
				System.out.println("(1)<---Fecha\n(2)<---Codigo del cliente\n(3)<---Nit del restaurante");
				int optionOrder = Integer.parseInt(in.nextLine());
				switch(optionOrder){
					case 1:
						upgrateDate(numPosOrder);
					break;
					
					case 2:
						upgrateCodeClient(numPosOrder);
					break;
						
					case 3:
						upgrateNitRestaurant(numPosOrder);
					break;
					
					default:
						System.out.println("Debe ingresar una opcion valida");
					break;
				}
			}else {
				System.out.println("No hay ordenes ingresadas al sistema");
			}
		}catch(CodeOrderNotExistException cone) {
			System.err.println("El codigo ingresado no corresponde a ninguna orden");
		}
	}
	
	/*
	name: upgrateDate
	allows actualized the date of the order
	*/
	public void upgrateDate(int posOrder) {
		Date newDate = new java.util.Date();
		myFastFood.getOrder().get(posOrder).setDate(newDate);
		System.out.println("La nueva fecha es"+ newDate);
	}
	
	/*
	name: upgrateCodeClient
	allows actualized the code client of the order
	*/
	public void upgrateCodeClient(int posOrder) {
		try {
			String infoClients = myFastFood.getInfoClients();
			System.out.println(infoClients + "\nDigite el nuevo numero de documento que tendra la orden");
			String numberDocument = in.nextLine();
			myFastFood.confirmNumberIdentity(numberDocument);
			myFastFood.getOrder().get(posOrder).setCodeClient(numberDocument);
		}catch(DocumentClientNotExistException dcne) {
			System.err.println("El numero del documento que ingreso no existe en el sistema");
		}
	}
	
	/*
	name: upgrateNitRestaurant
	allows actualized the nit restaurant of the order
	*/
	public void upgrateNitRestaurant(int posOrder) {
		try {
			String infoRestaurant = myFastFood.getInfoRestaurants();
			System.out.println(infoRestaurant + "\nDigite el nuevo nit de restaurante que tendra la orden");
			String newNitRestaurant = in.nextLine();
			myFastFood.confirmNit(newNitRestaurant);
			myFastFood.getOrder().get(posOrder).setNitRestaurant(newNitRestaurant);
		}catch(NitRestaurantNotExistException nrne) {
			System.err.println("El nit del restaurante que ingreso no existe en el sistema");
		}
	}
	
	/*
	name: upgrateStatusOrder
	allows actualized the status of the order
	*/
	public void upgrateStatusOrder() {
		if(!myFastFood.getOrder().isEmpty()) {
			String infoOrder = myFastFood.getInfoOrder();
			System.out.println(infoOrder + "\nA que numero de orden le desea actualizar el estatus");
			int numOrder = Integer.parseInt(in.nextLine());
			System.out.println("A que estatus desea actualizar la orden?\n(1)EN PROCESO\n(2)ENVIADO\n(3)ENTREGADO");
			int numNewStatus = Integer.parseInt(in.nextLine());
			try {
				myFastFood.upgradeStatus(numOrder, numNewStatus);
			}catch(StatusInvalidException sie) {
				System.err.println("El estado que intenta introducir es menor al actual");
			}catch(EqualsStatusException ese) {
				System.err.println("El estado que intenta introducir es el mismo que el actual");
			}catch(IndexOutOfBoundsException ipobe) {
				System.err.println("El numero de orden que ingreso no existe");
			}
		}else {
			System.out.println("No hay ordenes ingresadas al sistema");
		}
	}
	
	/*
	name: visualize
	allows show the menu of visualizations
	*/
	public void visualize() {
		System.out.println("=========================");
		System.out.println("|MENU DE VISUALIZACIONES|");
		System.out.println("=========================");
		System.out.println("Que desea visualizar?");
		System.out.println("(1)<---Ver los restaurantes ingresados al sistema");
		System.out.println("(2)<---Vel los clientes ingresados al sistema");
		int optionVisualize = Integer.parseInt(in.nextLine());
		switch(optionVisualize) {
			case 1:
				visualizeRestaurants();
			break;
			
			case 2:
				visualizeClients();
			break;
			
			default:
				System.out.println("Ingrese un valor valido");
			break;
		}
	}
	
	/*
	name: visualizeRestaurants
	allows show the restaurants in order
	*/
	public void visualizeRestaurants() {
		String infoOrganizedRestaurants = myFastFood.getRestaurantsInOrden();
		System.out.println(infoOrganizedRestaurants);
	}
	
	/*
	name: visualizeClients
	allows show the clients in order
	*/
	public void visualizeClients() {
		String infoOrganizedClients = myFastFood.getClientsInOrden();
		System.out.println(infoOrganizedClients);
	}
	
	/*
	name: searchClient
	allows search a client whit your name
	*/
	public void searchClient() {
		System.out.println("Ingrese el nombre del cliente que desea buscar");
		String name = in.nextLine();
		try {
			long start = System.currentTimeMillis();
			myFastFood.confirmExistName(name);
			String infoClient = myFastFood.searchName(name);
			long finish = System.currentTimeMillis();
			System.out.println(infoClient);
			System.out.println("El tiempo que tardo en encontrarlo fue: " + (finish-start));
		}catch(NameClientNotExistException ncne) {
			System.err.println("El nombre que ingreso no se encuentra registrado en el sistema");
		}
	}
	
	/*
	name: exportOrdersReport
	allows export data of orders
	*/
	public void exportOrdesReport() {
		try {
			System.out.println("Cual es el separador que utilizara?");
			String separator = in.nextLine();
			myFastFood.exportRegisterOrder(separator);
			System.out.println("Los datos fueron exportados exitosamente");
		}catch(FileNotFoundException fnfe) {
			System.err.println("Los datos no pueden ser exportados");
		}
	}
	
	/*
	name: importData
	allows show the menu of import.
	*/
	public void importData() {
		System.out.println("================================");
		System.out.println("|MENU DE IMPORTACIONES DE DATOS|");
		System.out.println("================================");
		System.out.println("Que desea importar?");
		System.out.println("(1)<---importar datos de restaurantes");
		System.out.println("(2)<---importar datos de productos(primero debe importar los restaurantes)");
		System.out.println("(3)<---importar datos de clientes");
		System.out.println("(4)<---importar datos de ordenes");
		int optionImport = Integer.parseInt(in.nextLine());
		switch(optionImport) {
			case 1:
				importRestaurants();
			break;
			case 2:
				importProducts();
			break;
			case 3:
				importClients();
			break;
			case 4:
				importOrders();
			break;
			default:
				System.out.println("Ingrese una opcion valida");
			break;
		}
	}
	
	/*
	name: importRestaurant
	allows import data of restaurants
	*/
	public void importRestaurants(){
		String messageError = null;
		System.out.println("Importando datos....");
		try {
			messageError = myFastFood.importDataRestaurants();
			System.out.println("Datos importados exitosamente");
		}catch(IOException ioe){
			System.err.println("Los datos no se pudieron importar");	
		}
		if(!messageError.equalsIgnoreCase("")) {
			System.err.println(messageError);
		}
	}
	
	/*
	name: importProducts
	allows import data of products
	*/
	public void importProducts() {
		String messageError = ""; 
		System.out.println("importando datos");
		try {
			if(!myFastFood.getRestaurants().isEmpty()) {
				myFastFood.importDataProducts();
				System.out.println("Datos importados exitosamente");
			}else {
				System.err.println("No hay restaurantes donde ingresar los productos");
			}
		}catch(IOException ioe) {
			System.err.println("Los datos no se pudieron importar");
		}
		if(!messageError.equalsIgnoreCase("")) {
			System.err.println(messageError);
		}
	}
	
	/*
	name: importClients
	allows import data of clients
	*/
	public void importClients(){
		String messageError = null;
		System.out.println("Importando datos....");
		try {
			messageError = myFastFood.importDataClients();
			System.out.println("Datos importados exitosamente");
		}catch(IOException ioe){
			System.err.println("Los datos no se pudieron importar");	
		}
		if(!messageError.equalsIgnoreCase("")) {
			System.err.println(messageError);
		}
	}
	
	/*
	name: importOrders
	allows import data of orders
	*/
	public void importOrders(){
		String messageError = "";
		System.out.println("Importando datos....");
		try {
			messageError = myFastFood.importDataOrders();
			System.out.println("Datos importados exitosamente");
		}catch(IOException ioe){
			System.err.println("Los datos no se pudieron importar");	
		}
		if(!messageError.equalsIgnoreCase("")) {
			System.err.println(messageError);
		}
	}
	
	/*
	name: startProgram
	allows inicialized program
	*/
	public void startProgram() {
		try {
			myFastFood.loadDataMyFastFood();
		}catch(IOException | ClassNotFoundException cnfe) {
			System.err.println("No se pudo descargar los datos");
		}
		menuPrincipal();
	}
	
	/*
//	name: showAll 
	allows show all information of restaurants, products, clients and orders.
	*/
	public void showAll() {
		String todaInfo;
		todaInfo = myFastFood.getInfoRestaurantsAndProducts();
		todaInfo += "\n" + myFastFood.getInfoClients();
		todaInfo += "\n" + myFastFood.getInfoOrder();
		System.out.println(todaInfo);
	}
}
