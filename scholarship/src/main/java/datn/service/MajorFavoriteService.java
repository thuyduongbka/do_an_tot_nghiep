package datn.service;

import datn.base.BaseService;
import datn.entity.favorite.CountryFavoriteEntity;
import datn.entity.favorite.MajorFavoriteEntity;
import datn.repository.CountryFavoriteRepository;
import datn.repository.MajorFavoriteRepository;
import org.springframework.stereotype.Service;

@Service
public class MajorFavoriteService extends BaseService<MajorFavoriteEntity, MajorFavoriteRepository> {
    public MajorFavoriteEntity findByUserIdAndCountryId(Long userId, Long majorId){
        return repository.findByUserIdAndMajorId(userId, majorId);
    }
}
