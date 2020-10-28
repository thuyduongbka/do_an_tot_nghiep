package datn.custom.dto;


import datn.security.CustomUserDetails;

public class LoginSuccessResponse {

    private CustomUserDetails userDetails;
    private String redirectUrl;
    private String accessToken;
    private String refreshToken;

    public CustomUserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(CustomUserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public LoginSuccessResponse(CustomUserDetails userDetails, String redirectUrl) {
        this.userDetails = userDetails;
        this.redirectUrl = redirectUrl;
    }

    public LoginSuccessResponse() {
    }
}
