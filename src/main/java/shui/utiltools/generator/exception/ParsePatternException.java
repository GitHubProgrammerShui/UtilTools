package shui.utiltools.generator.exception;

public class ParsePatternException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID=8389656903021324175L;
	
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
