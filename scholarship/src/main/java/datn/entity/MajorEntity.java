package datn.entity;

import datn.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="major")
public class MajorEntity extends BaseEntity {
    private Long parentId;
    private String name;
    private String child;
    private String note;

    public MajorEntity(){}
    public MajorEntity(String name){
        this.name = name;
    }
}
