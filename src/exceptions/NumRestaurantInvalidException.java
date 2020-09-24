package exceptions;

public class NumRestaurantInvalidException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumRestaurantInvalidException() {
		super("The num of restaurant entered is invalid");
	}

}
