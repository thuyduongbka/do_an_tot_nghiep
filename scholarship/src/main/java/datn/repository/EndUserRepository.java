package datn.repository;

import datn.base.BaseRepository;
import datn.entity.user.AccountEntity;
import datn.entity.user.EndUserEntity;

import java.util.Set;

public interface EndUserRepository extends BaseRepository<EndUserEntity> {
    EndUserEntity findByAccountEntity(AccountEntity accountEntity);
    
    EndUserEntity findByAccountEntity_id(Long id);
    
    Set<EndUserEntity> findByIdIn(Set<Long> listUserId);
}
