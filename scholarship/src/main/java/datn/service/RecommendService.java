package datn.service;

import datn.crawler.HttpService;
import datn.entity.ScholarshipEntity;
import datn.security.CurrentUserDetailsContainer;
import datn.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendService {
    @Autowired
    private ScholarshipService scholarshipService;

    @Autowired
    private HttpService httpService;

    @Autowired
    private CurrentUserDetailsContainer currentUserDetailsContainer;

    public CustomUserDetails getCurrentUser() {
        return this.currentUserDetailsContainer.getUserDetails();
    }

    public List<ScholarshipEntity> getRecommend(){
        Long userId = getCurrentUser().getEndUserId();
        List<Integer> listScholarshipIdRecommend = httpService.getRecommend(userId);
        List<ScholarshipEntity> listScholarshipRecommend = listScholarshipIdRecommend.stream().map(id ->
            scholarshipService.findById(Long.valueOf(id))).collect(Collectors.toList());
        return listScholarshipRecommend;
    }
    public List<ScholarshipEntity> getRecommendByScholarship(Long scholarshipId){
        Long userId = getCurrentUser().getEndUserId();
        List<Integer> listScholarshipIdRecommend = httpService.getRecommend(userId, scholarshipId);
        listScholarshipIdRecommend = listScholarshipIdRecommend.subList(0,6);
        List<ScholarshipEntity> listScholarshipRecommend = listScholarshipIdRecommend.stream().map(id ->
                scholarshipService.findById(Long.valueOf(id))).collect(Collectors.toList());
        return listScholarshipRecommend;
    }
}
