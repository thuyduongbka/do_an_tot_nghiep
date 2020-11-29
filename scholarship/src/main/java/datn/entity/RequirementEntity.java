package datn.entity;

import datn.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "requirement")
public class RequirementEntity extends BaseEntity {

    @Column(name = "scholarship_id")
    private Long scholarshipId;
    private String name;
    private String value;
}
