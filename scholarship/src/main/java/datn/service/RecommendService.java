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
        System.out.println(listScholarshipIdRecommend);

        for (Integer id : listScholarshipIdRecommend){
            System.out.println(id);
        }

        List<ScholarshipEntity> listScholarshipRecommend = listScholarshipIdRecommend.stream().map(id ->
            scholarshipService.findById(Long.valueOf(id))).collect(Collectors.toList());
        return listScholarshipRecommend;
    }
}
