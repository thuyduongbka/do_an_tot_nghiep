package datn.service;

import datn.entity.ScholarshipEntity;
import datn.security.CurrentUserDetailsContainer;
import datn.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendService {
    @Autowired
    private ScholarshipService scholarshipService;

    @Autowired
    private CurrentUserDetailsContainer currentUserDetailsContainer;

    public CustomUserDetails getCurrentUser() {
        return this.currentUserDetailsContainer.getUserDetails();
    }

    public List<ScholarshipEntity> getRecommend(){
        Long userId = getCurrentUser().getEndUserId();
        return scholarshipService.findAll();
    }
}
