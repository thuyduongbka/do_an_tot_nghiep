package datn.custom.dto;


public interface ResponseCase {

    ResponseStatus SUCCESS = new ResponseStatus(200, "SUCCESS");
    ResponseStatus ACCOUNT_EXISTED = new ResponseStatus(201, "Account is existed.");
    ResponseStatus INVALID_INPUT = new ResponseStatus(400, "INVALID_INPUT");
    ResponseStatus ERROR = new ResponseStatus(500, "Server error");

    ResponseStatus INVALID_TOKEN = new ResponseStatus(900, "INVALID_TOKEN");
    ResponseStatus EXPIRED_TOKEN = new ResponseStatus(901, "EXPIRED_TOKEN");

}
