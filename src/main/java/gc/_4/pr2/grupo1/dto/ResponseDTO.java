package gc._4.pr2.grupo1.dto;

public class ResponseDTO <T> {
	private boolean status; 
	private String message;
	private T data;
	private int cantidadItems;
	
	
	public ResponseDTO(boolean status, String message, T data, int cantidadItems) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.cantidadItems = cantidadItems;
		
		
	}
	
	public ResponseDTO() {
	
	}

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

	public int getCantidadItems() {
		return cantidadItems;
	}

	public void setCantidadItems(int cantidadItems) {
		this.cantidadItems = cantidadItems;
	}

		
	
}