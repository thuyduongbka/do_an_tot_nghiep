package datn.entity;

import datn.base.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "scholarship")
public class ScholarshipEntity extends BaseEntity implements Serializable {
    private int webId;
    private String name;
    private Date time;
    private String country;
    private String school;
    private String url;
    private Boolean isExpired;
    
    private Long number_seen;
    private Long number_share;
    private Long number_comment;
    
    public int getWebId() {
        return webId;
    }
    
    public void setWebId(int webId) {
        this.webId = webId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getTime() {
        return time;
    }
    
    public void setTime(Date time) {
        this.time = time;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    
    public String getSchool() {
        return school;
    }
    
    public void setSchool(String school) {
        this.school = school;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public Boolean getExpired() {
        return isExpired;
    }
    
    public void setExpired(Boolean expired) {
        isExpired = expired;
    }
    
    public Long getNumber_seen() {
        return number_seen;
    }
    
    public void setNumber_seen(Long number_seen) {
        this.number_seen = number_seen;
    }
    
    public Long getNumber_share() {
        return number_share;
    }
    
    public void setNumber_share(Long number_share) {
        this.number_share = number_share;
    }
    
    public Long getNumber_comment() {
        return number_comment;
    }
    
    public void setNumber_comment(Long number_comment) {
        this.number_comment = number_comment;
    }
}
