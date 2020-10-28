package datn.repository;

import datn.base.BaseRepository;
import datn.entity.user.AccountEntity;
import datn.entity.user.AdminEntity;
import datn.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AdminRepository extends BaseRepository<AdminEntity> {
    AdminEntity findByAccountEntity(AccountEntity accountEntity);
    
    AdminEntity findByAccountEntity_id(Long id);
    
    Set<AdminEntity> findByIdIn(Set<Long> listUserId);
}
