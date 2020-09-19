package exceptions;

public class NitRestaurantNotExistException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NitRestaurantNotExistException() {
		super("The nit entered not exist");
	}
}
