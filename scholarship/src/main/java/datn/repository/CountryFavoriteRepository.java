package datn.repository;

import datn.base.BaseRepository;
import datn.entity.favorite.CountryFavoriteEntity;

public interface CountryFavoriteRepository extends BaseRepository<CountryFavoriteEntity> {
    CountryFavoriteEntity findByUserIdAndCountryId(Long userId, Long countryId);
}
