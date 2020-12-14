package datn.entity.favorite;

import datn.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "school_favorite")
public class SchoolFavoriteEntity extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "school_id")
    private Long schoolId;
    private Boolean isImplicit;

    public SchoolFavoriteEntity(){}

    public SchoolFavoriteEntity(Long userId, Long schoolId, Boolean isImplicit) {
        this.userId = userId;
        this.schoolId = schoolId;
        this.isImplicit = isImplicit;
    }
}
