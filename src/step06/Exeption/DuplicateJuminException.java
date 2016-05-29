package step06.Exeption;

public class DuplicateJuminException extends Exception{

	public DuplicateJuminException() {
		super("동일한 주민번호가 이미 등록되어 있습니다.");
	}

	public DuplicateJuminException(String message) {
		super(message);
	}

	
}
