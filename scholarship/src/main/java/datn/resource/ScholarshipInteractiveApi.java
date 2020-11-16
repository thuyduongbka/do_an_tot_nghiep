package datn.resource;

import datn.entity.ScholarshipEntity;
import datn.entity.SchoolEntity;
import datn.service.ScholarshipInteractiveService;
import datn.service.SchoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/interactive")
public class ScholarshipInteractiveApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchoolApi.class);
    @Autowired
    private ScholarshipInteractiveService service;
    
    @GetMapping("/addFavorite")
    public ResponseEntity addFavorite(@RequestParam(value = "userId") Long userId,
                                      @RequestParam(value = "scholarshipId") Long scholarshipId,
                                      @RequestParam(value = "isFavorite") Boolean isFavorite){
        LOGGER.info("add-favorite");
        service.addFavorite(userId,scholarshipId,isFavorite);
        return ResponseEntity.ok("OK");
    }
    @GetMapping("/like")
    public ResponseEntity like(@RequestParam(value = "userId") Long userId,
                               @RequestParam(value = "scholarshipId") Long scholarshipId,
                               @RequestParam(value = "isLiked") Boolean isLiked){
        LOGGER.info("like");
        service.like(userId,scholarshipId,isLiked);
        return ResponseEntity.ok("OK");
    }
    @GetMapping("/rating")
    public ResponseEntity like(@RequestParam(value = "userId") Long userId,
                               @RequestParam(value = "scholarshipId") Long scholarshipId,
                               @RequestParam(value = "rating") Float rating){
        LOGGER.info("rating");
        service.changeRating(userId,scholarshipId,rating);
        return ResponseEntity.ok("OK");
    }
    
    @GetMapping("/countView")
    public ResponseEntity countView(@RequestParam(value = "userId") Long userId,
                                    @RequestParam(value = "scholarshipId") Long scholarshipId){
        service.countView(userId,scholarshipId);
        return ResponseEntity.ok("Ok");
    }
    @GetMapping("/countCompare")
    public ResponseEntity countCompare(@RequestParam(value = "userId") Long userId,
                                        @RequestParam(value = "scholarshipId") Long scholarshipId){
        service.countCompare(userId,scholarshipId);
        return ResponseEntity.ok("Ok");
    }
    @GetMapping("/countClickContact")
    public ResponseEntity countClickContact(@RequestParam(value = "userId") Long userId,
                                       @RequestParam(value = "scholarshipId") Long scholarshipId){
        service.countContact(userId,scholarshipId);
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/listAllFavorite")
    public ResponseEntity<List<ScholarshipEntity>> listAllFavorite(){
        return ResponseEntity.ok(service.findScholarshipFavoriteByUserId());
    }
}