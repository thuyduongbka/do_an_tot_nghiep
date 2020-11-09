package datn.entity;

import datn.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "school")
public class SchoolEntity extends BaseEntity {
    private String name;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
