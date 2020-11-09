package datn.entity;


import datn.base.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class CountryEntity extends BaseEntity {
    private String name;
    private String area;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getArea() {
        return area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
}
