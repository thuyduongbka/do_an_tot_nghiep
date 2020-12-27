package datn.resource;

import datn.custom.domain.Scholarship;
import datn.custom.dto.ScholarshipFilterDto;
import datn.custom.dto.ScholarshipNameDto;
import datn.custom.dto.UserScholarshipDto;
import datn.custom.dto.report.CountryReportDto;
import datn.custom.dto.report.CountryReportInterface;
import datn.custom.dto.report.MonthReportDto;
import datn.custom.dto.report.MonthReportInterface;
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

import java.util.ArrayList;
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
    
    @GetMapping("/get")
    public ResponseEntity<UserScholarshipDto> get(@RequestParam(value = "userId") Long userId,
                                                  @RequestParam(value = "scholarshipId") Long scholarshipId){
        LOGGER.info("call /api/scholarship/get/{id}");
        return ResponseEntity.ok(service.findScholarshipById(userId, scholarshipId));
    }
    @GetMapping("/get-recommend-same")
    public ResponseEntity<List<ScholarshipEntity>> getRecommendSame(@RequestParam(value = "scholarshipId") Long scholarshipId){
        LOGGER.info("call /api/scholarship/get/{id}");
        return ResponseEntity.ok(service.getRecommendSame(scholarshipId));
    }

    @GetMapping("/getAllName")
    public ResponseEntity<List<ScholarshipNameDto>> getAllName(){
        LOGGER.info("call /api/scholarship/getAllName");
        return ResponseEntity.ok(service.findAllName());
    }

    @GetMapping("/find-all-by")
    public ResponseEntity<List<ScholarshipEntity>> get(@RequestParam(value = "countryId", required = false) Long countryId,
                                                 @RequestParam(value = "majorId", required = false) Long majorId,
                                                 @RequestParam(value = "levelName", required = false ) String levelName){
        LOGGER.info("call /api/scholarship/get/{id}");
        if (countryId != null)
            return ResponseEntity.ok(service.findByCountryId(countryId));
        if (majorId != null)
            return ResponseEntity.ok(service.findByMajorId(majorId));
        if (levelName != null)
            return ResponseEntity.ok(service.findByLevelName(levelName));
        return ResponseEntity.ok(new ArrayList<>());
    }
    @GetMapping("/find-most-view")
    public ResponseEntity<List<ScholarshipEntity>> findMostViews(){
        return ResponseEntity.ok(service.findScholarshipMostViews());
    }

    @GetMapping("/report-by-month")
    public ResponseEntity<List<MonthReportInterface>> reportByMonth(){
        return ResponseEntity.ok(service.getTotalEveryMonth());
    }
    @GetMapping("report-by-country")
    public ResponseEntity<List<CountryReportInterface>> reportByCountry(){
        return ResponseEntity.ok(service.getTotalByCountry());
    }


}
