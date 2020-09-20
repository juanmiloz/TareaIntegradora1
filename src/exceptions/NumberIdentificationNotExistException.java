package exceptions;

public class NumberIdentificationNotExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumberIdentificationNotExistException() {
		super("the identification number entered is already in the system");
	}

}
