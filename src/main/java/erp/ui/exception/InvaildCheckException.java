package erp.ui.exception;

public class InvaildCheckException extends RuntimeException {

	public InvaildCheckException() {
		super("공백이 존재합니다.");
	}
	
	public InvaildCheckException(Throwable cause) {
		super("공백이 존재합니다.", cause);
	}
}
