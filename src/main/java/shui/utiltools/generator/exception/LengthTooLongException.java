package shui.utiltools.generator.exception;

public class LengthTooLongException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID=-5797825765207412190L;
	
	private String message;

	public LengthTooLongException(String message) {
		super();
		this.message = message;
	}
	public LengthTooLongException() {
		super();
	}
	
	public String toString(){
		System.out.println(message);
		return super.toString();
	}
}
