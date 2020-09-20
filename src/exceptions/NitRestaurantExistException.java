package exceptions;

public class NitRestaurantExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NitRestaurantExistException() {
		super("The nit entered is already in the system");
	}

}
