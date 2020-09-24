package exceptions;

public class NumProductsInvalidException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumProductsInvalidException() {
		super("The number of products entered is invalid");
	}

}
