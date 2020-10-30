package datn.entity;

import datn.base.BaseEntity;
import datn.enums.TypeTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "web")
public class WebEntity extends BaseEntity {
    private String url;
    private int time;
    @Enumerated(value = EnumType.STRING)
    private TypeTime typeTime;
    private Date lastCrawled;
    private Boolean isActive;
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public int getTime() {
        return time;
    }
    
    public void setTime(int time) {
        this.time = time;
    }
    
    public TypeTime getTypeTime() {
        return typeTime;
    }
    
    public void setTypeTime(TypeTime typeTime) {
        this.typeTime = typeTime;
    }
    
    public Date getLastCrawled() {
        return lastCrawled;
    }
    
    public void setLastCrawled(Date lastCrawled) {
        this.lastCrawled = lastCrawled;
    }
    
    public Boolean getActive() {
        return isActive;
    }
    
    public void setActive(Boolean active) {
        isActive = active;
    }
}
