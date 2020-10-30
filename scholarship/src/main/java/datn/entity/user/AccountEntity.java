package datn.entity.user;

import datn.base.BaseEntity;
import datn.custom.domain.Account;
import datn.enums.Role;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {

	private String username;
	private String password;

	@Enumerated(value = EnumType.STRING)
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
	
	public AccountEntity(){
	
	}
	
	public AccountEntity(Account domain){
		this.username = domain.getUsername();
		this.password = domain.getPassword();
		this.role = domain.getRole();
		this.isActive = true;
		this.isDeleted = false;
	}
	public AccountEntity(String username){
		this.username = username;
		this.password = "";
		this.isActive = true;
		this.role = Role.ADMIN;
	}
}
