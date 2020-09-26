package exceptions;

public class NumProductNotExist extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumProductNotExist() {
		super("the num of product entered not exist");
	}

}
