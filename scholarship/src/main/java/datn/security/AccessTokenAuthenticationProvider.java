package datn.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import datn.custom.dto.ResponseCase;
import datn.custom.dto.ServerResponseDTO;
import datn.custom.dto.TokenValidationResultDTO;
import datn.entity.user.AccountEntity;
import datn.enums.TokenType;
import datn.service.AccountService;
import datn.service.AuthenticationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessTokenAuthenticationProvider  implements Filter {

    public static final String ACCESS_TOKEN_PARAM = "Authorization";
    public static final String ACCESS_TOKEN_PREFIX = "Bearer ";

    @Autowired
    private AuthenticationTokenService authenticationTokenService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenAuthenticationProvider.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (!SecurityUtils.isAuthenticated()) {
            String accessToken = request.getHeader(ACCESS_TOKEN_PARAM);
            if (accessToken != null) {
                accessToken = accessToken.replace(ACCESS_TOKEN_PREFIX, ""); // remove access token prefix
                TokenValidationResultDTO validationResult = authenticationTokenService.validateToken(accessToken, TokenType.ACCESS_TOKEN);
                switch (validationResult.getStatus()){
                    case EXPIRED:
                        LOGGER.info("Access token is expired.");
                        response.getWriter().write(objectMapper.writeValueAsString(new ServerResponseDTO(ResponseCase.EXPIRED_TOKEN)));
                        response.getWriter().flush();
                        response.getWriter().close();
                        return;
                    case INVALID:
                        LOGGER.info("Access token is in-valid");
                        response.getWriter().write(objectMapper.writeValueAsString(new ServerResponseDTO(ResponseCase.INVALID_TOKEN)));
                        response.getWriter().flush();
                        response.getWriter().close();
                        return;
                    case VALID:
                        LOGGER.info("Authentication with access token success.");
                        setUserAuthenticatedManually(validationResult.getToken().getAccountId());
                        break;
                }
            }
        }

        chain.doFilter(servletRequest, servletResponse);

    }

    private void setUserAuthenticatedManually(Long accountId) {
        AccountEntity accountEntity = accountService.findById(accountId);
        UserDetails userDetails = userDetailsService.loadUserByUsername(accountEntity.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, accountEntity.getPassword(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
