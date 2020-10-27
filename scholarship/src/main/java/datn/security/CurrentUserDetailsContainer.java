package datn.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserDetailsContainer {
    public CustomUserDetail getUserDetail(){
        if (SecurityContextHolder.getContext() != null){
            Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()){
                Object principal = authentication.getPrincipal();
                if ( principal instanceof CustomUserDetail)
                    return (CustomUserDetail) principal;
            }
        }
        return null;
    }
}
