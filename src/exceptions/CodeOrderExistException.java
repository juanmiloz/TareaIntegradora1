package exceptions;

public class CodeOrderExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CodeOrderExistException() {
		super("The code order entered is already in the system");
	}

}
