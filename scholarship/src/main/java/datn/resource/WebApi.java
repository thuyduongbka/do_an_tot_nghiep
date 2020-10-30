package datn.resource;

import datn.custom.domain.Account;
import datn.entity.WebEntity;
import datn.entity.user.AdminEntity;
import datn.service.WebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/web")
public class WebApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebApi.class);
    @Autowired
    private WebService webService;
    
    @GetMapping("/getAll")
    public ResponseEntity<List<WebEntity>> getAll(){
        return ResponseEntity.ok(webService.getAll());
    }
    
    @PostMapping("/update")
    public ResponseEntity<WebEntity> update(@RequestBody WebEntity entity) {
        LOGGER.info("Update Web");
        return ResponseEntity.ok(webService.update(entity));
    }
}
