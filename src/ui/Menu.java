package ui;
import model.*;
import java.util.Scanner;
import java.util.Date;
import java.util.InputMismatchException;
import java.lang.NumberFormatException;
import java.lang.IndexOutOfBoundsException;
import exceptions.NitRestaurantNotExistException;
import exceptions.CodeProductNotExistException;
import exceptions.DocumentClientNotExistException;
import exceptions.CodeOrderNotExistException;

public class Menu {
	
	private final static int NEW_RESTAURANT = 1;
	private final static int NEW_PRODUCT = 2;
	private final static int NEW_CLIENT = 3;
	private final static int NEW_ORDER = 4;
	private final static int NEW_ACTUALIZATION = 5;
	private Scanner in = new Scanner(System.in);
	private MyFastFood myFastFood = new MyFastFood();
	
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
			System.out.println("(6)<---Cerrar programa");
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
				
				case 7:
					mostrarTodo();
				break;
			}
		}while(option != 8 && option <= 8);
	}
	
	public void newRestaurant() {
		try{
			System.out.println("\nIngrese el nombre del restaurante");
			String nameRestaurant = in.nextLine();
			System.out.println("Ingrese el nit del restaurante");
			String nit = in.nextLine();
			System.out.println("Ingrese el nombre del administrador");
			String nameAdministrator = in.nextLine();
			myFastFood.addNewRestaurant(nameRestaurant, nit, nameAdministrator);
		}catch(InputMismatchException ime) {
			System.err.println("Ingrese datos validos");
		}
	}
	
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
				String nit = myFastFood.assignNit(numberRestaurant);
				Product newProduct = new Product(codeProduct, nameProduct, description,cost,nit);
				myFastFood.addNewProduct(numberRestaurant, newProduct);
			}
		}catch(NumberFormatException nfe) {
			System.err.println("Ingrese datos validos");
		}catch(IndexOutOfBoundsException ibe) {
			System.err.println("El restaurante que ingreso no existe");
		}
	}
	
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
			System.out.println("Ingrese el nombre");
			String name = in.nextLine();
			System.out.println("Ingrese el apellido");
			String lastName = in.nextLine();
			System.out.println("Ingrese el telefono");
			String phone = in.nextLine();
			System.out.println("Ingrese la direccion");
			String address = in.nextLine();
			myFastFood.addNewClient(document, numberIdentification, name, lastName , phone, address);
		}catch(NumberFormatException nfe) {
			System.err.println("Ingrese valores validos porfavor");
		}
	}
	
	public void newOrder() {
		boolean repeat = true;
		String numero;
		do {
			int numRam = myFastFood.numRam();
			numero = String.valueOf(numRam);
			repeat = myFastFood.codeValid(numero);
			System.out.println("El codigo de la orden es: " + numero);
			repeat = false;
		}while(repeat);
		Date date = new java.util.Date();
		System.out.println("la fecha de la orden es: " + date);
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
	}
	
	public void continueOrder(String numRamdom, Date date, String idClient, String nitRestaurant, int x) {
		if(!myFastFood.getRestaurants().get(x-1).getProducts().isEmpty()) {
			String infoProducts = myFastFood.getInfoProductsOfRestaurant(x);
			System.out.println(infoProducts);
			System.out.println("Cuantos productos desea llevar");
			int numOfProductsToOrder = Integer.parseInt(in.nextLine());
			myFastFood.addNewOrder(numRamdom, date, idClient, nitRestaurant);
			for(int i = 0; i < numOfProductsToOrder; i++) {
				System.out.println("Ingrese el numero del producto el cual desea llevar");
				int numProduct = Integer.parseInt(in.nextLine());
				String nameProduct = myFastFood.assingNameProductToOrder(x, numProduct);
				System.out.println("Ingrese la cantidad de unidades que desea llevar");
				int quantity = Integer.parseInt(in.nextLine());
				String quantityToOrder = String.valueOf(quantity);
				myFastFood.addProductsToOrder(numRamdom, nameProduct, quantityToOrder);
			}
		}else {
			System.out.println("No hay productos ingresados");
		}
	}
	
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
		int options = Integer.parseInt(in.nextLine());
		
		switch(options) {
			case 1:
				upgrateDataRestaurant();
			break;
			case 2: 
	
			break;
			case 3:
				upgrateDataClient();
			break;
			case 4:
				upgrateDataOrder();
			break;
		}
	}
	
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
	
	public void upgrateNameRestaurant(int posRestaurant) {
		System.out.println("Ingrese el nuevo nombre del restaurante");
		String newName = in.nextLine();
		myFastFood.getRestaurants().get(posRestaurant).setName(newName);
	}
	
	public void upgrateNameAdministratorRestaurant(int posRestaurant) {
		System.out.println("Ingrese el nuevo nombre del administrador del restaurante");
		String newNameAdministrator = in.nextLine();
		myFastFood.getRestaurants().get(posRestaurant).setNameAdministraitor(newNameAdministrator);
	}
	
	public void upgrateDataProduct() {
		try{
			if(!myFastFood.getOrder().isEmpty()) {
				String infoRestaurants = myFastFood.getInfoRestaurants();
				System.out.println(infoRestaurants + "\nIngrese en que restaurante se encuentra el producto que desea actualizar");
				int numRestaurant = Integer.parseInt(in.nextLine());
				String infoProductsOfRestaurant = myFastFood.getInfoProductsOfRestaurant(numRestaurant);
				System.out.println(infoProductsOfRestaurant + "\nIngrese el codigo del producto que desea actualizar");
				String codeProduct = in.nextLine();
				myFastFood.confirmCodeProduct(codeProduct, (numRestaurant-1));
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
						upgrateDescriptionProduct(numPosProduct, numRestaurant);
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
		}
	}
	
	public void upgrateNameProduct(int posProduct, int numRestaurant) {
		System.out.println("Ingrese el nuevo nombre del producto");
		String newNameProduct = in.nextLine();
		myFastFood.getRestaurants().get(numRestaurant).getProducts().get(posProduct).setName(newNameProduct);
	}
	
	public void upgrateDescriptionProduct(int posProduct, int numRestaurant) {
		System.out.println("Ingrese la nueva descripcion del producto");
		String newDescriptionProduct = in.nextLine();
		myFastFood.getRestaurants().get(numRestaurant).getProducts().get(posProduct).setDescription(newDescriptionProduct);
	}
	
	public void upgrateCostProduct(int posProduct, int numRestaurant) {
		System.out.println("Ingrese el nuevo costo del producto");
		Double newCost = Double.parseDouble(in.nextLine());
		myFastFood.getRestaurants().get(numRestaurant).getProducts().get(posProduct).setCost(newCost);;
	}
	
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
	
	public void upgrateTypeIdentificationClient(int posClient) {
		System.out.println("Ingrese el nuevo tipo de identificacion");
		System.out.println("1<---Tarjeta de Identidad\n2<---Cedula Ciudadania\n3<---Pasaporte\n4<---Cedula Extranjera\n");
		int numOption = Integer.parseInt(in.nextLine());
		String document = myFastFood.assignDocument(numOption);
		myFastFood.getClients().get(posClient).setTypeIdentification(document);
	}
	
	public void upgrateNameClient(int posClient) {
		System.out.println("Ingrese el nuevo nombre del cliente");
		String newName = in.nextLine();
		myFastFood.getClients().get(posClient).setName(newName);
	}
	
	public void upgrateApellidoClient(int posClient) {
		System.out.println("Ingrese el nuevo apellido del cliente");
		String newLastName = in.nextLine();
		myFastFood.getClients().get(posClient).setLastName(newLastName);
	}
	
	public void upgratePhoneClient(int posClient) {
		System.out.println("Ingrese el nuevo numero de telefono");
		String newPhone = in.nextLine();
		myFastFood.getClients().get(posClient).setPhone(newPhone);
	}
	
	public void upgrateAdressClient(int posClient) {
		System.out.println("Ingrese la nueva direccion");
		String newAdress = in.nextLine();
		myFastFood.getClients().get(posClient).setAdress(newAdress);
	}
	
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
	
	public void upgrateDate(int posOrder) {
		Date newDate = new java.util.Date();
		myFastFood.getOrder().get(posOrder).setDate(newDate);
		System.out.println("La nueva fecha es"+ newDate);
	}
	
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
	
	public void startProgram() {
		menuPrincipal();
	}
	
	/*probar que todo este bien*/
	public void mostrarTodo() {
		String todaInfo;
		todaInfo = myFastFood.getInfoRestaurantsAndProducts();
		todaInfo += "\n" + myFastFood.getInfoClients();
		todaInfo += "\n" + myFastFood.getInfoOrder();
		System.out.println(todaInfo);
	}
}
