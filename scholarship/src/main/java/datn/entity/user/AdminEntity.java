package datn.entity.user;


import datn.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "admin")
public class AdminEntity extends BaseEntity {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity accountEntity;
    private Date lastLogin;
    
    public AccountEntity getAccountEntity() {
        return accountEntity;
    }
    
    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
    
    public Date getLastLogin() {
        return lastLogin;
    }
    
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
