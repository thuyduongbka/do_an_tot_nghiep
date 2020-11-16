package datn.repository;

import datn.base.BaseRepository;
import datn.entity.user.AccountEntity;
import datn.entity.user.EndUserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Set;

public interface EndUserRepository extends BaseRepository<EndUserEntity> {
    EndUserEntity findByAccountEntity(AccountEntity accountEntity);
    
    EndUserEntity findByAccountEntity_id(Long id);
    
    Set<EndUserEntity> findByIdIn(Set<Long> listUserId);

    @Transactional
    @Modifying
    @Query(value = "delete from country_favorite where country_id = ?1 and user_id = ?2", nativeQuery = true)
    void deleteCountryFavorite(Long countryId, Long userId);

    @Transactional
    @Modifying
    @Query(value = "delete from school_favorite where school_id = ?1 and user_id = ?2", nativeQuery = true)
    void deleteSchoolFavorite(Long schoolId, Long userId);

    @Transactional
    @Modifying
    @Query(value = "delete from major_favorite where major_id = ?1 and user_id = ?2", nativeQuery = true)
    void deleteMajorFavorite(Long majorId, Long userId);
}
