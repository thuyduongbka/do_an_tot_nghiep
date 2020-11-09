package datn.service;

import datn.base.BaseService;
import datn.custom.domain.EndUser;
import datn.entity.CountryEntity;
import datn.entity.MajorEntity;
import datn.entity.SchoolEntity;
import datn.entity.user.AccountEntity;
import datn.entity.user.EndUserEntity;
import datn.enums.Role;
import datn.repository.EndUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EndUserService extends BaseService<EndUserEntity, EndUserRepository> {
    @Autowired
    private AccountService accountService;
    
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private CountryService countryService;
    
    @Autowired
    private SchoolService schoolService;
    
    @Autowired
    private MajorService majorService;
    
    @Transactional
    public EndUserEntity save(EndUser dto) throws Exception {
        EndUserEntity endUserEntity = new EndUserEntity(dto);
        AccountEntity accountEntity = new AccountEntity(dto.getUsername());
        accountEntity.setRole(Role.END_USER);
        accountEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        accountEntity = accountService.save(accountEntity);
        endUserEntity.setAccountEntity(accountEntity);
        List<Long> listCountryId = dto.getListCountryId();
        List<CountryEntity> listCountry = dto.getListCountryId().stream().map(id -> countryService.findById(id)).collect(Collectors.toList());
        List<SchoolEntity> listSchool = dto.getListSchoolId().stream().map(id -> schoolService.findById(id)).collect(Collectors.toList());
        List<MajorEntity> listMajor = dto.getListMajorId().stream().map(id -> majorService.findById(id)).collect(Collectors.toList());
        endUserEntity.setCountryEntities(listCountry);
        endUserEntity.setSchoolEntities(listSchool);
        endUserEntity.setMajorEntities(listMajor);
        
        return save(endUserEntity);
    }
    public EndUserEntity get(Long id){
        return findById(id);
    }
}
