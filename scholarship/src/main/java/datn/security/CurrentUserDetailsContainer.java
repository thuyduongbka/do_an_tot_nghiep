package datn.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * author: datnh
 * class hold current login user detail instance.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentUserDetailsContainer {

    /**
     * get user details of current login user.
     *
     * @return CustomUserDetails instance if user authenticated , return null if user not authenticated.
     */
    public CustomUserDetails getUserDetails() {
        if (SecurityContextHolder.getContext() != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {

                Object principal = authentication.getPrincipal();
                if (principal instanceof CustomUserDetails) {
                    return (CustomUserDetails) principal;
                }
            }
        }
        return null;
    }

}
