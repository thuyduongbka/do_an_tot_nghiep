package datn.security;

import datn.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CustomUserDetails extends User {

    private static final long serialVersionUID = 1L;
    private Long accountId;
    
    private Long adminId;
    private Long endUserId;
    
    private String name;
    private Date lastLoginTime;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, Long accountId, String name, Date lastLoginTime) {
        super(username, password, authorities);
        this.accountId = accountId;
        this.lastLoginTime = lastLoginTime;
        this.name = name;
    }
    
    public Long getAdminId() {
        return adminId;
    }
    
    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getEndUserId() {
        return endUserId;
    }

    public void setEndUserId(Long endUserId) {
        this.endUserId = endUserId;
    }
    
    /**
     * check current login user has a role.
     *
     * @param role
     * @return
     */
    public boolean isRole(Role role) {
        for (GrantedAuthority authority : this.getAuthorities()) {
            if (authority.getAuthority().equals(SecurityUtils.getAuthorityFromRole(role))) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAnyRole(Role... roles) {
        for (Role role : roles) {
            if (isRole(role)) {
                return true;
            }
        }
        return false;
    }

    public static CustomUserDetails.UserDetailsBuilder initBuilder() {
        return new CustomUserDetails.UserDetailsBuilder();
    }

    /**
     * Class to build CustomUserDetails instance.
     */
    public static class UserDetailsBuilder {
        private String username;
        private String password;
        private String name;
        private Long accountId;
        private Long adminId;
        private Long endUserId;
        private Date lastLoginTime;
        private Collection<? extends GrantedAuthority> authorities;

        public CustomUserDetails.UserDetailsBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public CustomUserDetails.UserDetailsBuilder setPassword(String password) {
            this.password = password;
            return this;
        }
    
        public CustomUserDetails.UserDetailsBuilder setAdminId(Long adminId) {
            this.adminId = adminId;
            return this;
        }
        
        public CustomUserDetails.UserDetailsBuilder setEndUserId(Long userId) {
            this.endUserId = userId;
            return this;
        }
        

        public CustomUserDetails.UserDetailsBuilder setAccountId(Long accountId) {
            this.accountId = accountId;
            return this;
        }

        public CustomUserDetails.UserDetailsBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CustomUserDetails.UserDetailsBuilder setAuthorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }
        

        public CustomUserDetails.UserDetailsBuilder setLastLoginTime(Date loginTime) {
            this.lastLoginTime = loginTime;
            return this;
        }

        public CustomUserDetails build() {
            if (this.username == null) {
                throw new IllegalArgumentException("Username can not be null");
            }
            if (this.password == null) {
                throw new IllegalArgumentException("Password can not be null");
            }
            if (this.accountId == null) {
                throw new IllegalArgumentException("UserId can not be null");
            }
            if (this.authorities == null) {
                throw new IllegalArgumentException("Authorities can not be null");
            }
            if (this.lastLoginTime == null) {
                throw new IllegalArgumentException("Login time can not be null");
            }

            CustomUserDetails customUserDetails = new CustomUserDetails(this.username, this.password, this.authorities, this.accountId, this.name, this.lastLoginTime);
            
            if (this.adminId != null) {
                customUserDetails.setAdminId(adminId);
            }
            if (this.endUserId != null) {
                customUserDetails.setEndUserId(endUserId);
            }
            return customUserDetails;
        }
    }
}
