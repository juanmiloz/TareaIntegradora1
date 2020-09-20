package exceptions;

public class NameClientNotExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NameClientNotExistException() {
		super("The name entered cannot be found in the system");
	}

}
