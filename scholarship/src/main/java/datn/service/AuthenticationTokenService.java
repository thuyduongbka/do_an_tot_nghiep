package datn.service;

import datn.base.BaseService;
import datn.custom.dto.TokenValidationResultDTO;
import datn.entity.user.AuthTokenEntity;
import datn.enums.TokenType;
import datn.enums.TokenValidationStatus;
import datn.repository.AuthenticationTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthenticationTokenService extends BaseService<AuthTokenEntity, AuthenticationTokenRepository> {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationTokenService.class);

    private long accessTokenActivePeriod = 24 * 60 * 60; // 1 day

    private long refreshTokenActivePeriod = 30 * 24 * 60 * 60; // 31 day

    private static final long NUM_SECOND_OF_MINUTE = 1000;

    public String createAccessToken(Long accountId) {
        logger.info("Create access token for account {}",accountId);

        AuthTokenEntity tokenEntity = new AuthTokenEntity();
        tokenEntity.setAccountId(accountId);
        tokenEntity.setTokenType(TokenType.ACCESS_TOKEN);
        tokenEntity.setToken(UUID.randomUUID().toString());

        super.save(tokenEntity);

        return tokenEntity.getToken();
    }

    public String getAccessToken(String refreshToken) {
        TokenValidationResultDTO validationResultDto = validateToken(refreshToken, TokenType.REFRESH_TOKEN);
        if (validationResultDto.getStatus() != TokenValidationStatus.VALID) {
            throw new IllegalArgumentException("invalid refresh token");
        } else {
            return createAccessToken(validationResultDto.getToken().getAccountId());
        }
    }

    public String createRefreshToken(Long accountId) {
        logger.info("Create refresh token for account {}",accountId);

        AuthTokenEntity tokenEntity = new AuthTokenEntity();
        tokenEntity.setAccountId(accountId);
        tokenEntity.setTokenType(TokenType.REFRESH_TOKEN);
        tokenEntity.setToken(UUID.randomUUID().toString());

        super.save(tokenEntity);

        return tokenEntity.getToken();
    }

    public TokenValidationResultDTO validateToken(String token, TokenType tokenType) {
        AuthTokenEntity tokenEntity = this.repository.findByTokenAndIsDeleted(token, false);
        if (tokenEntity == null) {
            return new TokenValidationResultDTO(TokenValidationStatus.INVALID);
        } else {
            Date now = new Date();
            long activePeriod = TokenType.ACCESS_TOKEN.equals(tokenType) ? accessTokenActivePeriod : refreshTokenActivePeriod;

            boolean isExpired = (now.getTime() - tokenEntity.getCreatedTime().getTime()) / NUM_SECOND_OF_MINUTE > activePeriod;

            if (isExpired) {
                return new TokenValidationResultDTO(TokenValidationStatus.EXPIRED);
            } else {
                TokenValidationResultDTO result = new TokenValidationResultDTO(TokenValidationStatus.VALID);
                result.setToken(tokenEntity);
                return result;
            }
        }
    }
}
