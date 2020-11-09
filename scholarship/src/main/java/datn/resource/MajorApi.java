package datn.resource;

import datn.entity.MajorEntity;
import datn.entity.SchoolEntity;
import datn.service.MajorService;
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
@RequestMapping("/api-public/major")
public class MajorApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(MajorApi.class);
    @Autowired
    private MajorService service;
    
    @GetMapping("/list-all")
    public ResponseEntity<List<MajorEntity>> getAll(){
        LOGGER.info("get all major");
        return ResponseEntity.ok(service.findAll());
    }
}
