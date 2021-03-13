package erp.ui.exception;

@SuppressWarnings("serial")
public class InvaildCheckException extends RuntimeException { //추가버튼 누른 경우 텍스트필드에 아무것도 없을때 예외 처리 해준다. 

	public InvaildCheckException() {
		super("공백이 존재합니다.");
	}
	
	public InvaildCheckException(Throwable cause) {
		super("공백이 존재합니다.", cause);
	}
}
