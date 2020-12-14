package datn.service;

import datn.base.BaseService;
import datn.entity.favorite.MajorFavoriteEntity;
import datn.entity.favorite.SchoolFavoriteEntity;
import datn.repository.MajorFavoriteRepository;
import datn.repository.SchoolFavoriteRepository;
import org.springframework.stereotype.Service;

@Service
public class SchoolFavoriteService extends BaseService<SchoolFavoriteEntity, SchoolFavoriteRepository> {
    public SchoolFavoriteEntity findByUserIdAndCountryId(Long userId, Long schoolId){
        return repository.findByUserIdAndSchoolId(userId, schoolId);
    }
}

