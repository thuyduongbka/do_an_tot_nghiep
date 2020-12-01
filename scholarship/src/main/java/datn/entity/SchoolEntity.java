package datn.entity;

import datn.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "school")
public class SchoolEntity extends BaseEntity {
    private String name;

    @Column(name = "country_id")
    private Long countryId;

}
