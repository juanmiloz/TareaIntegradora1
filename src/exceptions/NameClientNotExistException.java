package exceptions;

public class NameClientNotExistException extends Exception{

	public NameClientNotExistException() {
		super("The name entered cannot be found in the system");
	}

}
