package datn.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import datn.custom.dto.ResponseCase;
import datn.custom.dto.ServerResponseDTO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handle return response when authentication fail
 */
@Component
public class ApiAuthenticationFailureHandler implements AuthenticationFailureHandler {

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		response.getWriter().write(objectMapper.writeValueAsString(new ServerResponseDTO(ResponseCase.INVALID_INPUT)));
	}

}
