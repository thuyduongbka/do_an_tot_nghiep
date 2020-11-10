package datn.resource;

import datn.custom.domain.Scholarship;
import datn.custom.dto.ScholarshipFilterDto;
import datn.entity.ScholarshipEntity;
import datn.service.ScholarshipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/scholarship")
public class ScholarshipApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScholarshipApi.class);
    @Autowired
    private ScholarshipService service;
    
    @PostMapping("/getAll")
    public ResponseEntity<Page<ScholarshipEntity>> getAll(@RequestBody ScholarshipFilterDto scholarshipFilterDto,
                                                          @RequestParam(value = "page", defaultValue = "1") int page,
                                                          @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
                                                          @RequestParam(value = "sortDirection", defaultValue = "DESC") String sortDirection,
                                                          @RequestParam(value = "sortField", defaultValue = "createdTime") String sortField){
        LOGGER.info("call /api/scholarship/getAll");
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection), sortField);
        PageRequest request = PageRequest.of(page - 1, pageSize, sort);
        return ResponseEntity.ok(service.getAll(scholarshipFilterDto,request));
    }
}
