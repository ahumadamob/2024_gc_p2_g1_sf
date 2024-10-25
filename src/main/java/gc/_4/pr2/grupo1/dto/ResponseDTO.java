package gc._4.pr2.grupo1.dto;

public class ResponseDTO<T> {
	//ATRIBUTOS
	private boolean status;
	private String message;
	private T data;
	
	//CONSTRUCTORES
	public ResponseDTO(boolean status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public ResponseDTO() {
	}
	
	//GETTERS Y SETTERS
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}