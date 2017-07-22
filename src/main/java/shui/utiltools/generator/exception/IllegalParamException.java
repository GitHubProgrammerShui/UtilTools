package shui.utiltools.generator.exception;

public class IllegalParamException extends RuntimeException{

	private String message;

	public IllegalParamException(String message) {
		super();
		this.message = message;
	}
	public IllegalParamException() {
	}
	
	@Override
	public String toString() {
		System.out.println(message);
		return super.toString();
	}
}
