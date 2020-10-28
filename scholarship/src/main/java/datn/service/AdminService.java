package datn.service;

import datn.base.BaseService;
import datn.custom.domain.Admin;
import datn.entity.user.AdminEntity;
import datn.repository.AdminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        admin.setCode(entity.getCode());
        admin.setAccountId(entity.getAccountEntity().getId());
        admin.setUsername(entity.getAccountEntity().getUsername());
        return admin;
    }
    public Admin entityToDomain(AdminEntity entity){
        Admin domain = new Admin();
        
        domain.setId(entity.getId());
        domain.setCode(entity.getCode());
        domain.setAccountId(entity.getAccountEntity().getId());
        domain.setLastLogin(entity.getLastLogin());
        domain.setUsername(entity.getAccountEntity().getUsername());
        
        return domain;
    }
    
}
