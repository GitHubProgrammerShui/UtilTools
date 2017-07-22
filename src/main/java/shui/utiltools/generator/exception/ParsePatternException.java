package shui.utiltools.generator.exception;

public class ParsePatternException extends RuntimeException{

	private String message;
	
	
	public ParsePatternException(String message) {
		super();
		this.message = message;
	}
	public ParsePatternException(){
	}


	@Override
	public String toString() {
		System.out.println(message);
		return super.toString();
	}
}
