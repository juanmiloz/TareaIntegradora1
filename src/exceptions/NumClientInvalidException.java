package exceptions;

public class NumClientInvalidException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumClientInvalidException() {
		super("The number of client enteres is invalid");
	}

}
