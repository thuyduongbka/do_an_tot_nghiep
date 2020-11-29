package datn.entity;

import datn.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "money")
public class MoneyEntity extends BaseEntity {

    @Column(name = "scholarship_id")
    private Long scholarshipId;
    private String value;
}
