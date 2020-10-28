package datn.custom.domain;


import datn.base.BaseDomain;
import datn.enums.Role;

public class Account extends BaseDomain {

    private String username;
    private String password;
    private Role role;
    private boolean isActive;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", role=" + role + ", isActive=" + isActive
				+ "]";
	}
    
    
}
