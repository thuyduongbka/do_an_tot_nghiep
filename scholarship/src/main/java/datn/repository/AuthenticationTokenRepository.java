package datn.repository;


import datn.base.BaseRepository;
import datn.entity.user.AuthTokenEntity;
import datn.enums.TokenType;

public interface AuthenticationTokenRepository extends BaseRepository<AuthTokenEntity> {

    public AuthTokenEntity findByTokenAndIsDeleted(String token, boolean isDeleted);

    public AuthTokenEntity findByAccountIdAndTokenTypeAndIsDeleted(Long accountId, TokenType tokenType, boolean isDeleted);

}
