package datn.entity.user;

import datn.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "end_user")
public class EndUserEntity extends BaseEntity {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity accountEntity;
    
    private String accessToken;
    
    private String name;
    private String gender;
    private String level;
    private Date birthday;
    private String country;
    private Date graduationDate;
    private String phone;
    
    public AccountEntity getAccountEntity() {
        return accountEntity;
    }
    
    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getLevel() {
        return level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    public Date getBirthday() {
        return birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public Date getGraduationDate() {
        return graduationDate;
    }
    
    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
