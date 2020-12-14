package datn.service;

import datn.base.BaseService;
import datn.entity.favorite.CountryFavoriteEntity;
import datn.repository.CountryFavoriteRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryFavoriteService extends BaseService<CountryFavoriteEntity, CountryFavoriteRepository> {
    public CountryFavoriteEntity findByUserIdAndCountryId(Long userId, Long countryId){
        return repository.findByUserIdAndCountryId(userId, countryId);
    }
}
