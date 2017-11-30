package shui.utiltools.exception;

public class UnsupportedClassException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3276610323776700600L;
	
	private String message;
	
	@Deprecated
	public UnsupportedClassException(){
		//默认构造，不建议使用
	}
	public UnsupportedClassException(String message){
		this.message=message;
	}
	public UnsupportedClassException(String message, Throwable cause) {
		super(message, cause);
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString(){
		String s = getClass().getName();
        return (message != null) ? (s + ": " + message) : s;
	}
}
