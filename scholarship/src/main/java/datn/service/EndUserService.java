package datn.service;

import datn.base.BaseService;
import datn.custom.domain.EndUser;
import datn.custom.dto.ChangePasswordDTO;
import datn.custom.dto.LoginSuccessResponse;
import datn.custom.exception.LoginFailureException;
import datn.custom.exception.OldPasswordNotEqualException;
import datn.entity.*;
import datn.entity.favorite.CountryFavoriteEntity;
import datn.entity.favorite.MajorFavoriteEntity;
import datn.entity.favorite.SchoolFavoriteEntity;
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
import java.util.*;
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
    private ScholarshipService scholarshipService;
    
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;
    
    @Autowired
    private AuthenticationTokenService authenticationTokenService;

    @Autowired
    private CountryFavoriteService countryFavoriteService;
    @Autowired
    private SchoolFavoriteService schoolFavoriteService;
    @Autowired
    private MajorFavoriteService majorFavoriteService;
    
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
    @Transactional
    public EndUserEntity update(EndUser dto){
        EndUserEntity userEntity = findByAccountId(dto.getAccountId());
        userEntity.setName(dto.getName());
        userEntity.setPhone(dto.getPhone());
        userEntity.setGender(dto.getGender());
        userEntity.setBirthday(dto.getBirthday());
        userEntity.setLevel(dto.getLevel());
        userEntity.setGraduationDate(dto.getGraduationDate());
        return update(userEntity);
    }
    @Transactional
    public EndUserEntity updateUserByScholarship(Long scholarshipId){
        ScholarshipEntity scholarship = scholarshipService.findById(scholarshipId);

        Long userId = getCurrentUser().getEndUserId();
        EndUserEntity userEntity = findById(userId);

        List<String> listNameLevel = scholarship.getLevelEntities().stream().map(LevelEntity::getName).collect(Collectors.toList());
        if (userEntity.getLevel().equals("")) {
            userEntity.setLevel(String.join(",",listNameLevel));
        } else {
            for (String level : listNameLevel){
                if (!userEntity.getLevel().contains(level)){
                    userEntity.setLevel(userEntity.getLevel() + ',' + level );
                }
            }
        }

        Long countryId = scholarship.getCountryEntity().getId();
        CountryFavoriteEntity countryFavorite = countryFavoriteService.findByUserIdAndCountryId(userId, countryId);
        if (countryFavorite == null) {
            countryFavorite = new CountryFavoriteEntity(userId, countryId, true);
            countryFavoriteService.save(countryFavorite);
        }

        if (scholarship.getSchoolEntity() != null){
            Long schoolId = scholarship.getSchoolEntity().getId();
            SchoolFavoriteEntity schoolFavorite = schoolFavoriteService.findByUserIdAndCountryId(userId, schoolId);
            if (schoolFavorite == null){
                schoolFavorite = new SchoolFavoriteEntity(userId, schoolId, true);
                schoolFavoriteService.save(schoolFavorite);
            }
        }

        if (scholarship.getMajorEntities().size() != 0){
            for (MajorEntity major : scholarship.getMajorEntities()){
                Long majorId = major.getId();
                MajorFavoriteEntity majorFavorite =majorFavoriteService.findByUserIdAndCountryId(userId,majorId);
                if (majorFavorite == null) {
                    majorFavorite = new MajorFavoriteEntity(userId, majorId, true);
                    majorFavoriteService.save(majorFavorite);
                }
            }
        }

        return update(userEntity);
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

    @Transactional
    public EndUserEntity addCountryFavorite(Long countryId){
        Long currentUserId = getCurrentUser().getEndUserId();
        CountryFavoriteEntity countryFavoriteEntity = countryFavoriteService.findByUserIdAndCountryId(currentUserId, countryId);
        if (countryFavoriteEntity != null){
            if (countryFavoriteEntity.getIsImplicit()){
                countryFavoriteEntity.setIsImplicit(false);
                countryFavoriteService.update(countryFavoriteEntity);
            }
        } else {
            countryFavoriteEntity = new CountryFavoriteEntity(currentUserId, countryId, false);
            countryFavoriteService.save(countryFavoriteEntity);
        }
        EndUserEntity userEntity = findById(currentUserId);
        return userEntity;
    }
    public EndUserEntity addSchoolFavorite(Long schoolId){
        Long currentUserId = getCurrentUser().getEndUserId();
        SchoolFavoriteEntity schoolFavoriteEntity = schoolFavoriteService.findByUserIdAndCountryId(currentUserId,schoolId);
        if (schoolFavoriteEntity != null){
            if (schoolFavoriteEntity.getIsImplicit()){
                schoolFavoriteEntity.setIsImplicit(false);
                schoolFavoriteService.update(schoolFavoriteEntity);
            }
        } else {
            schoolFavoriteEntity = new SchoolFavoriteEntity(currentUserId, schoolId, false);
            schoolFavoriteService.save(schoolFavoriteEntity);
        }
        EndUserEntity userEntity = findById(currentUserId);
        return userEntity;
    }
    public EndUserEntity addMajorFavorite(Long majorID){
        Long currentUserId = getCurrentUser().getEndUserId();
        MajorFavoriteEntity majorFavoriteEntity = majorFavoriteService.findByUserIdAndCountryId(currentUserId, majorID);
        if (majorFavoriteEntity != null){
            if (majorFavoriteEntity.getIsImplicit()){
                majorFavoriteEntity.setIsImplicit(false);
                majorFavoriteService.update(majorFavoriteEntity);
            }
        } else {
            majorFavoriteEntity = new MajorFavoriteEntity(currentUserId, majorID, false);
            majorFavoriteService.save(majorFavoriteEntity);
        }

        EndUserEntity userEntity = findById(currentUserId);
        return userEntity;
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

    public Collection<String> changeStringToSet(String listImplicit, Long id ){
        List<String> list = Arrays.asList(listImplicit.split(","));
        Set<String> set = new HashSet<>(list);
        set.add(String.valueOf(id));
        return set;
    }
}
