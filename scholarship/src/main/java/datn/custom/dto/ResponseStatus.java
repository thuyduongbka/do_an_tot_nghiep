package datn.custom.dto;

public class ResponseStatus {
	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseStatus(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public ResponseStatus() {
	}

}
