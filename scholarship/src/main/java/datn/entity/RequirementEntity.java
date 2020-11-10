package datn.entity;

import datn.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "requirement")
public class RequirementEntity extends BaseEntity {
    
    private Long scholarshipId;
    private String name;
    private String value;
}
