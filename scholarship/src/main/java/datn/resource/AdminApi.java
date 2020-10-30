package datn.resource;

import datn.custom.domain.Account;
import datn.custom.domain.Admin;
import datn.entity.user.AdminEntity;
import datn.security.SecurityUtils;
import datn.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginApi.class);
    
    @Autowired
    private AdminService adminService;
    
    @GetMapping("/getAll")
    public ResponseEntity<List<AdminEntity>> getAll() {
        LOGGER.info("Get All Admin");
        return ResponseEntity.ok(adminService.findAll());
    }
    
    @PostMapping("/update")
    public ResponseEntity<AdminEntity> update(@RequestBody Account account) {
        LOGGER.info("Update Admin");
        return ResponseEntity.ok(adminService.updateAdmin(account));
    }
    
    @PostMapping("/save")
    public ResponseEntity<AdminEntity> save(@RequestParam String email){
        LOGGER.info("Create Admin");
        return ResponseEntity.ok(adminService.saveAdmin(email));
    }
}
