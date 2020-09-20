package exceptions;

public class StatusInvalidException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StatusInvalidException() {
		super("The new status selected is less than the previous");
	}

}
