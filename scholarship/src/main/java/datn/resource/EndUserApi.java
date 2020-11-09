package datn.resource;

import datn.custom.domain.EndUser;
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
        return ResponseEntity.ok(endUserService.save(endUser));
    }
    @GetMapping("/get")
    public ResponseEntity<EndUserEntity> get(@RequestParam Long id){
        LOGGER.info("get user");
        return ResponseEntity.ok(endUserService.get(id));
    }
}
