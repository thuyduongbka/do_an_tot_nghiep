package datn.service;

import datn.base.BaseService;
import datn.custom.domain.EndUser;
import datn.custom.dto.ChangePasswordDTO;
import datn.custom.dto.LoginSuccessResponse;
import datn.custom.exception.LoginFailureException;
import datn.custom.exception.OldPasswordNotEqualException;
import datn.entity.CountryEntity;
import datn.entity.MajorEntity;
import datn.entity.SchoolEntity;
import datn.entity.user.AccountEntity;
import datn.entity.user.EndUserEntity;
import datn.enums.Role;
import datn.repository.EndUserRepository;
import datn.security.CustomAuthenticationProvider;
import datn.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;
    
    @Autowired
    private AuthenticationTokenService authenticationTokenService;
    
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
    public EndUserEntity update(EndUser dto){
        EndUserEntity userEntity = findByAccountId(dto.getAccountId());
        userEntity.setName(dto.getName());
        userEntity.setPhone(dto.getPhone());
        userEntity.setGender(dto.getGender());
        userEntity.setBirthday(dto.getBirthday());
        userEntity.setLevel(dto.getLevel());
        userEntity.setGraduationDate(dto.getGraduationDate());
        return save(userEntity);
    }
    @Transactional
    public boolean changePassword(ChangePasswordDTO changePasswordDTO) throws OldPasswordNotEqualException {
        String oldPassword = changePasswordDTO.getOldPassword();
        String newPassword = changePasswordDTO.getNewPassword();
        Long accountId = getCurrentUser().getAccountId();
        AccountEntity accountEntity = accountService.findById(accountId);
        if (accountEntity != null) {
            if (passwordEncoder.matches(oldPassword, accountEntity.getPassword())) {
                accountEntity.setPassword(passwordEncoder.encode(newPassword));
                accountService.update(accountEntity);
                return true;
            } else {
                throw new OldPasswordNotEqualException(oldPassword);
            }
        }
        return false;
    }
    public EndUserEntity get(Long id){
        return findById(id);
    }
    
    public LoginSuccessResponse login(String username, String password){
        Authentication authentication = new UsernamePasswordAuthenticationToken(username,password);
        authentication = authenticationProvider.authenticate(authentication);
    
        CustomUserDetails userDetail = (CustomUserDetails) authentication.getPrincipal();
        if (userDetail.isRole(Role.END_USER)){
            String refreshToken = authenticationTokenService.createRefreshToken(userDetail.getAccountId());
            String accessToken = authenticationTokenService.createAccessToken(userDetail.getAccountId());
            LoginSuccessResponse loginSuccessResponse = new LoginSuccessResponse(userDetail, null);
            loginSuccessResponse.setAccessToken(accessToken);
            loginSuccessResponse.setRefreshToken(refreshToken);
            return loginSuccessResponse;
        }
        throw new LoginFailureException("Login Fail");
    }
    public EndUserEntity findByAccountId(Long accountId){
        return repository.findByAccountEntity_id(accountId);
    }

    public EndUserEntity addCountryFavorite(Long countryId){
        Long currentUserId = getCurrentUser().getEndUserId();
        EndUserEntity userEntity = findById(currentUserId);
        CountryEntity countryEntity = countryService.findById(countryId);
        userEntity.getCountryEntities().add(countryEntity);
        return save(userEntity);
    }
    public EndUserEntity addSchoolFavorite(Long schoolId){
        Long currentUserId = getCurrentUser().getEndUserId();
        EndUserEntity userEntity = findById(currentUserId);
        SchoolEntity schoolEntity = schoolService.findById(schoolId);
        userEntity.getSchoolEntities().add(schoolEntity);
        return save(userEntity);
    }
    public EndUserEntity addMajorFavorite(Long majorID){
        Long currentUserId = getCurrentUser().getEndUserId();
        EndUserEntity userEntity = findById(currentUserId);
        MajorEntity majorEntity = majorService.findById(majorID);
        userEntity.getMajorEntities().add(majorEntity);
        return save(userEntity);
    }

    public void deleteCountryFavorite(Long countryId){
        Long currentUserId = getCurrentUser().getEndUserId();
        repository.deleteCountryFavorite(countryId,currentUserId);
    }
    public void deleteSchoolFavorite(Long schoolId){
        Long currentUserId = getCurrentUser().getEndUserId();
        repository.deleteSchoolFavorite(schoolId,currentUserId);
    }
    public void deleteMajorFavorite(Long majorId){
        Long currentUserId = getCurrentUser().getEndUserId();
        repository.deleteMajorFavorite(majorId,currentUserId);
    }
}
