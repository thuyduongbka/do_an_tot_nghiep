package datn.repository;

import datn.base.BaseRepository;
import datn.entity.user.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface AccountRepository extends BaseRepository<AccountEntity> {

    Page<AccountEntity> findAll(Specification<AccountEntity> specs, Pageable pageable);

    List<AccountEntity> findAll(Specification<AccountEntity> specs);

    AccountEntity findUserByUsernameAndIsDeleted(String username, boolean isDeleted);
}
