package datn.service;

import datn.base.BaseService;
import datn.custom.domain.Account;
import datn.custom.domain.Admin;
import datn.entity.user.AccountEntity;
import datn.entity.user.AdminEntity;
import datn.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class AdminService extends BaseService<AdminEntity, AdminRepository> {
    private static Logger logger = LoggerFactory.getLogger(AdminService.class);
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    public Admin findByAccountId(Long accountId) {
        Admin admin = new Admin();
        
        AdminEntity entity = repository.findByAccountEntity_id(accountId);
        
        admin.setId(entity.getId());
        admin.setAccountId(entity.getAccountEntity().getId());
        admin.setUsername(entity.getAccountEntity().getUsername());
        return admin;
    }
    public Admin entityToDomain(AdminEntity entity){
        Admin domain = new Admin();
        
        domain.setId(entity.getId());
        domain.setAccountId(entity.getAccountEntity().getId());
        domain.setLastLogin(entity.getLastLogin());
        domain.setUsername(entity.getAccountEntity().getUsername());
        
        return domain;
    }
    
    @Transactional
    public AdminEntity saveAdmin(String email){
        AccountEntity accountEntity = new AccountEntity(email);
        accountEntity = accountService.save(accountEntity);
        AdminEntity admin = new AdminEntity();
        admin.setAccountEntity(accountEntity);
        admin = save(admin);
        return admin;
    }
    @Transactional
    public AdminEntity updateAdmin(Account account){
        AccountEntity accountEntity = accountService.findById(account.getId());
        accountEntity.setUsername(account.getUsername());
        accountEntity.setActive(account.isActive());
        accountEntity.setUpdatedTime(new Date());
        if (!account.getPassword().equals("")) {
                accountEntity.setPassword(passwordEncoder.encode(account.getPassword()));
        }
        accountEntity = accountService.update(accountEntity);
        AdminEntity admin = repository.findByAccountEntity(accountEntity);
        return admin;
    }
    
}
