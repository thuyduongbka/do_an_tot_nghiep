package datn.custom.exception;


public class OldPasswordNotEqualException extends Throwable {

    private String message;
    public OldPasswordNotEqualException(String oldPass) {
        this.message = String.format("oldPass [%s] not equal", oldPass);
    }
    @Override
    public String getMessage() {
        return message;
    }
}
