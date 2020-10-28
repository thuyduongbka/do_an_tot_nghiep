package datn.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import datn.custom.dto.LoginSuccessResponse;
import datn.custom.dto.ResponseCase;
import datn.custom.dto.ServerResponseDTO;
import datn.entity.user.AdminEntity;
import datn.enums.Role;
import datn.service.AdminService;
import datn.service.AuthenticationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Handle return response when authentication success
 */
@Component
public class ApiAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();
    

    @Autowired
    private AuthenticationTokenService authenticationTokenService;

    @Autowired
    private AdminService adminService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // Caching previous request
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        // Save last login of system user
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (userDetails.hasAnyRole(Role.ADMIN)) {
            AdminEntity adminEntity = adminService.findById(userDetails.getAdminId());
            adminEntity.setLastLogin(new Date());
            adminService.update(adminEntity);
        }

        if (savedRequest == null) {
            clearAuthenticationAttributes(request);
            responseAuthenticationInfo(authentication, response, null);
            return;
        }
        String targetUrlParam = getTargetUrlParameter();
        String redirectUrl = savedRequest.getRedirectUrl();

        if (isAlwaysUseDefaultTargetUrl()
                || (targetUrlParam != null && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
            responseAuthenticationInfo(authentication, response, targetUrlParam);
            return;
        }

        clearAuthenticationAttributes(request);
        responseAuthenticationInfo(authentication, response, redirectUrl);
    }

    public void responseAuthenticationInfo(Authentication authentication, HttpServletResponse response, String redirectUrl)
            throws IOException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        if (StringUtils.isEmpty(redirectUrl)) {
//            redirectUrl = getDefaultRedirectUrl(userDetails);
//        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LoginSuccessResponse loginSuccessResponse = new LoginSuccessResponse(userDetails, redirectUrl);
            loginSuccessResponse.setAccessToken(authenticationTokenService.createAccessToken(userDetails.getAccountId()));
            loginSuccessResponse.setRefreshToken(authenticationTokenService.createRefreshToken(userDetails.getAccountId()));

            response.getWriter().write(objectMapper.writeValueAsString(new ServerResponseDTO(ResponseCase.SUCCESS, loginSuccessResponse)));
        } catch (IOException e) {
            logger.error("Error when try to parse authentication info to json data");
            logger.error(e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

//    private String getDefaultRedirectUrl(CustomUserDetails userDetails) {
//        String redirectUrl;
//
//        if (userDetails.hasAnyRole(Role.COMPANY_ADMIN)) {
//            CompanyEntity companyEntity = companyService.findById(userDetails.getCompanyId());
//            String companyCode = companyEntity.getCode().toString();
//            redirectUrl = "/company/" + companyCode + "/info";
//
//        } else if (userDetails.hasAnyRole(Role.COMPANY_USER)) {
//            CompanyEntity companyEntity = companyService.findById(userDetails.getCompanyId());
//            String companyCode = companyEntity.getCode().toString();
//            redirectUrl = "/company/" + companyCode + "/coupon";
//
//        } else if (userDetails.isRole(Role.END_USER)) {
//            redirectUrl = "/endUser/coupon";
//
//        } else {
//            redirectUrl = "/";
//        }
//
//        return redirectUrl;
//    }
}
