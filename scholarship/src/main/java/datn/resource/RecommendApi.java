package datn.resource;

import datn.custom.dto.ConversationDto;
import datn.custom.dto.UserScholarshipDto;
import datn.entity.ScholarshipEntity;
import datn.service.RecommendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recommend")
public class RecommendApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendApi.class);

    @Autowired
    private RecommendService recommendService;

    @GetMapping("/get-recommend")
    public ResponseEntity<List<ScholarshipEntity>> get(){
        LOGGER.info("call get recommend");
        return ResponseEntity.ok(recommendService.getRecommend());
    }

    @PostMapping("get-recommend-conversation")
    public ResponseEntity<List<ScholarshipEntity>> getRecommendConversation(@RequestBody ConversationDto dto){
        LOGGER.info("get recommend conversation");
        return ResponseEntity.ok(recommendService.getRecommendConversation(dto));
    }
}
