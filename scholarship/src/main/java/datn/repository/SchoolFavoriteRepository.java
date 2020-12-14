package datn.repository;

import datn.base.BaseRepository;
import datn.entity.favorite.SchoolFavoriteEntity;

public interface SchoolFavoriteRepository extends BaseRepository<SchoolFavoriteEntity> {
    SchoolFavoriteEntity findByUserIdAndSchoolId(Long userId, Long schoolId);
}
