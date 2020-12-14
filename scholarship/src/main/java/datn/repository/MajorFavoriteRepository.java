package datn.repository;

import datn.base.BaseRepository;
import datn.entity.favorite.MajorFavoriteEntity;

public interface MajorFavoriteRepository extends BaseRepository<MajorFavoriteEntity> {
    MajorFavoriteEntity findByUserIdAndMajorId(Long userId, Long majorId);
}

