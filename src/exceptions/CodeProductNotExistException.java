package exceptions;

public class CodeProductNotExistException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CodeProductNotExistException() {
		super("The code of the product entered not exist");
	}
}
