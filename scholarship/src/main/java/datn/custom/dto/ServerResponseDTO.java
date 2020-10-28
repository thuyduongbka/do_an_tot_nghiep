package datn.custom.dto;


public class ServerResponseDTO {

	private ResponseStatus status;
	private Object data;
	
	public ServerResponseDTO(ResponseStatus responseStatus) {
		this.status = responseStatus;
	}

	public ServerResponseDTO(ResponseStatus responseStatus, Object data) {
		this.status = responseStatus;
		this.data = data;
	}

	public static ServerResponseDTO success(Object data){
		ServerResponseDTO response = new ServerResponseDTO(ResponseCase.SUCCESS, data);
		return response;
	}
	
	public static ServerResponseDTO successBoolean(boolean data){
		if(data) {
			return new ServerResponseDTO(ResponseCase.SUCCESS,null);
		}else {
			return new ServerResponseDTO(ResponseCase.ERROR, null); 
		}
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
