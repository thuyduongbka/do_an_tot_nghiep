package datn.resource;

import datn.entity.SchoolEntity;
import datn.service.SchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-public/school")
public class SchoolApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolApi.class);
    @Autowired
    private SchoolService schoolService;
    
    @GetMapping("/list-all")
    public ResponseEntity<List<SchoolEntity>> getAll(){
        LOGGER.info("get all school");
        return ResponseEntity.ok(schoolService.findAll());
    }
    
}
