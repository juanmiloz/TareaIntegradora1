package exceptions;

public class DocumentClientNotExistException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DocumentClientNotExistException() {
		super("The document entered not exist");
	}
}
