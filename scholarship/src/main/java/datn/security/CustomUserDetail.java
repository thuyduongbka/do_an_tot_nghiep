package datn.security;

import datn.enums.UserType;
import datn.entity.user.AdminEntity;
import datn.entity.user.StudentEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CustomUserDetail implements UserDetails {
    private UserType userType;
    private AdminEntity adminEntity;
    private StudentEntity studentEntity;
    private String accessToken;
    
    public CustomUserDetail(AdminEntity adminEntity) {
        super();
        this.userType = UserType.ADMIN;
        this.adminEntity = adminEntity;
    }
    public CustomUserDetail(StudentEntity studentEntity) {
        super();
        this.userType = UserType.STUDENT;
        this.studentEntity = studentEntity;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (Objects.equals(UserType.ADMIN, this.userType)) {
            authorities.add(new SimpleGrantedAuthority(UserType.ADMIN.getName()));
            return authorities;
        } else {
            authorities.add(new SimpleGrantedAuthority(UserType.STUDENT.getName()));
            return authorities;
        }
        
    }
    public boolean hasAnyRole(UserType... roles) {
        for (UserType role : roles) {
            boolean hasRole = this.getAuthorities()
                    .stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role.getName()));
            if (hasRole)
                return true;
        }
        return false;
    }
    
    
    public Long getUserId() {
        if (adminEntity == null && studentEntity == null)
            return null;
        
        if (adminEntity != null)
            return adminEntity.getId();
        
        return studentEntity.getId();
    }
    
    @Override
    public String getPassword() {
        return null;
    }
    
    @Override
    public String getUsername() {
        return null;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
    
    @Override
    public boolean isEnabled() {
        return false;
    }
    
}
