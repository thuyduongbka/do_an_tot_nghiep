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
@Table(name = "level")
public class LevelEntity extends BaseEntity {
    
    private Long scholarshipId;
    private String name;
}
