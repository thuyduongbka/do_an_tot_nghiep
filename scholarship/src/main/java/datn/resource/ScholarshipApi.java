package datn.resource;

import datn.entity.ScholarshipEntity;
import datn.service.ScholarshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/scholarship")
public class ScholarshipApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScholarshipApi.class);
    @Autowired
    private ScholarshipService service;
    
    @GetMapping("/getAll")
    public ResponseEntity<List<ScholarshipEntity>> getAll(){
        LOGGER.info("call /api/scholarship/getAll");
        return ResponseEntity.ok(service.getAll());
    }
}
