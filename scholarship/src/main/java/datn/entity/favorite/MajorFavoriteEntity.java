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
@Table(name = "major_favorite")
public class MajorFavoriteEntity extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "major_id")
    private Long majorId;
    private Boolean isImplicit;

    public MajorFavoriteEntity(){}

    public MajorFavoriteEntity(Long userId, Long majorId, Boolean isImplicit) {
        this.userId = userId;
        this.majorId = majorId;
        this.isImplicit = isImplicit;
    }
}
