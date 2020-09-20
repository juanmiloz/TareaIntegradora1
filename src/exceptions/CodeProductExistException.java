package exceptions;

public class CodeProductExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CodeProductExistException() {
		super("The code entered is already in the system");
	}

}
