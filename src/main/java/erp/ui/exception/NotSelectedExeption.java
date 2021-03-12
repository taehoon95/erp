package erp.ui.exception;

@SuppressWarnings("serial")
public class NotSelectedExeption extends RuntimeException {

	public NotSelectedExeption() {
		super("목록을 선택하세요.");
	}

	public NotSelectedExeption(Throwable cause) {
		super("목록을 선택하세요.", cause);
	}
	
}
