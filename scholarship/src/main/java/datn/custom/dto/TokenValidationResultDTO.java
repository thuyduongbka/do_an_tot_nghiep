package datn.custom.dto;


import datn.entity.user.AuthTokenEntity;
import datn.enums.TokenValidationStatus;

public class TokenValidationResultDTO {

    private TokenValidationStatus status;

    private AuthTokenEntity token;

    public TokenValidationStatus getStatus() {
        return status;
    }

    public void setStatus(TokenValidationStatus status) {
        this.status = status;
    }

    public AuthTokenEntity getToken() {
        return token;
    }

    public void setToken(AuthTokenEntity token) {
        this.token = token;
    }

    public TokenValidationResultDTO(TokenValidationStatus status) {
        this.status = status;
    }
}
