package datn.entity.favorite;


import datn.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "country_favorite")
public class CountryFavoriteEntity extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;
    @Column(name = "country_id")
    private Long countryId;
    private Boolean isImplicit;

    public CountryFavoriteEntity(){}

    public CountryFavoriteEntity(Long userId, Long countryId, Boolean isImplicit) {
        this.userId = userId;
        this.countryId = countryId;
        this.isImplicit = isImplicit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryFavoriteEntity that = (CountryFavoriteEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(countryId, that.countryId) &&
                Objects.equals(isImplicit, that.isImplicit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, countryId, isImplicit);
    }
}
