package datn.resource;

import datn.entity.CountryEntity;
import datn.entity.LevelEntity;
import datn.service.CountryService;
import datn.service.LevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-public/level")
public class LevelApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(LevelApi.class);
    @Autowired
    private LevelService service;
    
    @GetMapping("/list-all")
    public ResponseEntity<List<String>> getAll(){
        LOGGER.info("get all level");
        return ResponseEntity.ok(service.getAllLevel());
    }
}
