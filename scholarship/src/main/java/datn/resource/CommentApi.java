package datn.resource;

import datn.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentApi.class);
    
    @Autowired
    private CommentService service;
    
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody String message,
                                 @RequestParam(value = "userId") Long userId,
                                 @RequestParam(value = "scholarshipId") Long scholarshipId){
        LOGGER.info("create comment");
        return ResponseEntity.ok(service.create(userId, scholarshipId, message));
    }
    @GetMapping("/delete")
    public ResponseEntity delete(@RequestParam(value = "id") Long id){
        LOGGER.info("delete comment");
        return ResponseEntity.ok("OK");
    }
}
