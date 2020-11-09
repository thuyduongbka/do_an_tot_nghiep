package datn.entity;

import datn.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name="major")
public class MajorEntity extends BaseEntity {
    private Long parentId;
    private String name;
    private Long level;
   
    public Long getParentId() {
        return parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getLevel() {
        return level;
    }
    
    public void setLevel(Long level) {
        this.level = level;
    }
}
