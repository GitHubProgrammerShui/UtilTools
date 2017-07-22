package shui.utiltools.generator.exception;

public class LengthTooLongException extends RuntimeException{

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
