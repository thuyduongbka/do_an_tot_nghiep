package datn.entity.user;

import datn.base.BaseEntity;
import datn.custom.domain.EndUser;
import datn.entity.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
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
    
    @ManyToMany
            @JoinTable(
                    name = "country_favorite",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "country_id")
            )
    private List<CountryEntity> countryEntities;
    @ManyToMany
    @JoinTable(
            name = "school_favorite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "school_id")
    )
    private List<SchoolEntity> schoolEntities;
    @ManyToMany
    @JoinTable(
            name = "major_favorite",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "major_id")
    )
    private List<MajorEntity> majorEntities;
    
    @OneToMany
    @JoinColumn(name = "id")
    private List<ScholarshipInteractiveEntity> interactiveEntities;
    
    public EndUserEntity(){}
    public EndUserEntity(EndUser dto){
        this.name = dto.getName();
        this.gender = dto.getGender();
        this.level = dto.getLevel();
        this.birthday = dto.getBirthday();
        this.country = dto.getCountry();
        this.graduationDate = dto.getGraduationDate();
        this.phone = dto.getPhone();
    }
    
    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

}
