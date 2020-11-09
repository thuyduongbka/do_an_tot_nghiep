package datn.custom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import datn.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EndUser {
    private Long accountId;
    private String username;
    private String password;
    private Role role;
    private boolean isActive;
    private String name;
    private String gender;
    private String level;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date birthday;
    private String country;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date graduationDate;
    private String phone;
    private List<Long> listCountryId;
    private List<Long> listSchoolId;
    private List<Long> listMajorId;
    
    
}