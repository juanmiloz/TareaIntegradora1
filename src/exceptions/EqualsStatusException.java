package exceptions;

public class EqualsStatusException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EqualsStatusException() {
		super("The status selected is equals whit the actual");
	}

}
