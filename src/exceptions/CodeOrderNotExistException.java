package exceptions;

public class CodeOrderNotExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CodeOrderNotExistException() {
		super("The code of the order entered not exist");
	}
}
