package datn.service;

import datn.base.BaseService;
import datn.custom.domain.Account;
import datn.custom.dto.ChangePasswordDTO;
import datn.custom.dto.UserFilter;
import datn.custom.specs.AccountSpecs;
import datn.entity.user.AccountEntity;
import datn.repository.AccountRepository;
import datn.security.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService extends BaseService<AccountEntity, AccountRepository> {
    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    PasswordEncoder passwordEncoder;
    
    public AccountEntity findByUsername(String username){
        return findUserEntity(username);
    }

    public Account findUser(String username) {
        AccountEntity entity = findUserEntity(username);

        if (entity == null) {
            return null;
        }

        return entityToDomain(entity);
    }

    private Account entityToDomain(AccountEntity entity) {
        Account domain = new Account();

        domain.setId(entity.getId());
        domain.setUsername(entity.getUsername());
        domain.setPassword(entity.getPassword());
        domain.setRole(entity.getRole());
        domain.setActive(entity.isActive());

        return domain;
    }

    /**
     * Get list
     *
     * @param page
     * @return
     */
    public Page<AccountEntity> filter(UserFilter filter, Pageable page) {
        logger.info("Filter");
        return repository.findAll(AccountSpecs.getUsers(filter), page);
    }

    public AccountEntity findUserEntity(String username) {
        return repository.findUserByUsernameAndIsDeleted(username, false);
    }

    public boolean checkUsernameExist(String username) {
        AccountEntity accountEntity = findUserEntity(username);
        return accountEntity != null;
    }

    public boolean changePassword(ChangePasswordDTO changePasswordDto) {
        AccountEntity accountEntity = findById(getCurrentUser().getAccountId());
        if (passwordEncoder.matches(changePasswordDto.getOldPassword(), accountEntity.getPassword())) {
            changePassword(accountEntity, changePasswordDto.getNewPassword());
            return true;
        }
        return false;
    }

    public AccountEntity changePassword(AccountEntity accountEntity, String newPassword) {
        accountEntity.setPassword(passwordEncoder.encode(newPassword));
        return update(accountEntity);
    }
}

