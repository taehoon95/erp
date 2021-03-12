package erp.ui.exception;

@SuppressWarnings("serial")
public class NullPointException extends RuntimeException {

	public NullPointException() {
		super("해당 사원이 존재 하지 않습니다.");
	}

	public NullPointException(Throwable cause) {
		super("해당 사원이 존재 하지 않습니다.",cause);
	}

}
