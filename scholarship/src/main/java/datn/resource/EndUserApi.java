package datn.resource;

import datn.custom.domain.EndUser;
import datn.custom.dto.ChangePasswordDTO;
import datn.custom.dto.ResponseCase;
import datn.custom.dto.ServerResponseDTO;
import datn.custom.exception.LoginFailureException;
import datn.custom.exception.OldPasswordNotEqualException;
import datn.entity.user.EndUserEntity;
import datn.service.EndUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-public/end-user")
public class EndUserApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(EndUserApi.class);
    
    @Autowired
    private EndUserService endUserService;
    
    @PostMapping("/save")
    public ResponseEntity<EndUserEntity> save(@RequestBody EndUser endUser) throws Exception {
        LOGGER.info("save end user");
        return ResponseEntity.ok(endUserService.save(endUser));
    }
    @PostMapping("/update")
    public ResponseEntity<EndUserEntity> update(@RequestBody EndUser endUser) throws Exception {
        LOGGER.info("save end user");
        return ResponseEntity.ok(endUserService.update(endUser));
    }

    @PostMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) throws Exception {
        LOGGER.info("change pass end user");
        try {
            return ResponseEntity.ok(endUserService.changePassword(changePasswordDTO));
        } catch (OldPasswordNotEqualException e){
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity<EndUserEntity> get(@RequestParam Long id){
        LOGGER.info("get user");
        return ResponseEntity.ok(endUserService.get(id));
    }
    @PostMapping("/login")
    public ResponseEntity login (@RequestParam("username") String username,
                                 @RequestParam("password") String password){
        LOGGER.info("login");
        try{
            
            return ResponseEntity.ok(new ServerResponseDTO(ResponseCase.SUCCESS, endUserService.login(username,password)));
        } catch (LoginFailureException e) {
            return ResponseEntity.ok(e);
        }
    }

    @GetMapping("/addCountryFavorite")
    public ResponseEntity addCountryFavorite(@RequestParam("countryId") Long countryId){
        return ResponseEntity.ok(endUserService.addCountryFavorite(countryId));
    }
    @GetMapping("/addSchoolFavorite")
    public ResponseEntity addSchoolFavorite(@RequestParam("schoolId") Long schoolId){
        return ResponseEntity.ok(endUserService.addSchoolFavorite(schoolId));
    }
    @GetMapping("/addMajorFavorite")
    public ResponseEntity addMajorFavorite(@RequestParam("majorId") Long majorId){
        return ResponseEntity.ok(endUserService.addMajorFavorite(majorId));
    }
    @DeleteMapping("/deleteCountryFavorite")
    public ResponseEntity deleteCountryFavorite(@RequestParam("countryId") Long countryId){
        endUserService.deleteCountryFavorite(countryId);
        return ResponseEntity.ok("ok");
    }
    @DeleteMapping("/deleteSchoolFavorite")
    public ResponseEntity deleteSchoolFavorite(@RequestParam("schoolId") Long schoolId){
        endUserService.deleteSchoolFavorite(schoolId);
        return ResponseEntity.ok("ok");
    }
    @DeleteMapping("/deleteMajorFavorite")
    public ResponseEntity deleteMajorFavorite(@RequestParam("majorId") Long majorId){
        endUserService.deleteMajorFavorite(majorId);
        return ResponseEntity.ok("ok");
    }
}
