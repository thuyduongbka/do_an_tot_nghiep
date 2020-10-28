package datn.entity.user;

import datn.base.BaseEntity;
import datn.enums.TokenType;

import javax.persistence.*;

@Entity
@Table(name="auth_token")
public class AuthTokenEntity extends BaseEntity {

    private String token;

    private Long accountId;

    @Enumerated(value = EnumType.STRING)
    @Column(name="type")
    private TokenType tokenType;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }
}
