package datn.entity;


import datn.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "country")
public class CountryEntity extends BaseEntity {
    private String name;
    private String area;

    @OneToMany
    @JoinColumn(name = "country_id")
    private List<SchoolEntity> schoolEntities;

}
