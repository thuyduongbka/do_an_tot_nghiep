package datn.resource;

import datn.entity.CountryEntity;
import datn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-public/country")
public class CountryApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryApi.class);
    @Autowired
    private CountryService service;
    
    @GetMapping("/list-all")
    public ResponseEntity<List<CountryEntity>> getAll(){
        LOGGER.info("get all country");
        return ResponseEntity.ok(service.findAll());
    }
}
