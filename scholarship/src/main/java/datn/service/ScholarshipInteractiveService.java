package datn.service;

import datn.base.BaseService;
import datn.entity.ScholarshipEntity;
import datn.entity.ScholarshipInteractiveEntity;
import datn.entity.SchoolEntity;
import datn.repository.ScholarshipInteractiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ScholarshipInteractiveService extends BaseService<ScholarshipInteractiveEntity, ScholarshipInteractiveRepository> {
    @Autowired
    private ScholarshipService scholarshipService;

    @Autowired
    private EndUserService userService;
    
    public ScholarshipInteractiveEntity findByScholarshipIdAndUserId(Long scholarshipId, Long userId){
        return repository.findByScholarshipIdAndUserId(scholarshipId, userId);
    }
    
    @Transactional
    public void countView(Long userId, Long scholarshipId){
        scholarshipService.countView(scholarshipId);
        ScholarshipInteractiveEntity entity = findByScholarshipIdAndUserId(scholarshipId,userId);
        if (findByScholarshipIdAndUserId(scholarshipId, userId) == null) {
            save(new ScholarshipInteractiveEntity(scholarshipId,userId));
        }
        repository.increaseNumberSeen(scholarshipId,userId);
    }
    @Transactional
    public void countContact(Long userId, Long scholarshipId){
        userService.updateUserByScholarship(scholarshipId);
        repository.increaseNumberClickContact(scholarshipId,userId);
    }
    @Transactional
    public void countComment(Long userId, Long scholarshipId){
        scholarshipService.increaseNumberComment(scholarshipId);
        repository.increaseNumberComment(scholarshipId,userId);

    }
    @Transactional
    public void countCompare(Long userId, Long scholarshipId){
        repository.increaseNumberCompare(scholarshipId,userId);
    }
    @Transactional
    public void like(Long userId, Long scholarshipId, Boolean isLiked){
        repository.changeLike(scholarshipId,userId,isLiked);
    }
    @Transactional
    public void addFavorite(Long scholarshipId, Boolean addFavorite){
        Long userId = getCurrentUser().getEndUserId();
        repository.changeFavorite(scholarshipId,userId,addFavorite);
    }
    @Transactional
    public void changeRating(Long userId, Long scholarshipId, Float rating){
        repository.changeRating(scholarshipId,userId,rating);
        Float ratingAverage = repository.getRating(scholarshipId);
        Long numberRating = repository.getNumberRating(scholarshipId);
        scholarshipService.changeRating(scholarshipId, ratingAverage);
        scholarshipService.changeNumberRating(scholarshipId, numberRating);
    }
    public List<ScholarshipEntity> findScholarshipFavoriteByUserId(){
        Long userId = getCurrentUser().getEndUserId();
        return repository.findScholarshipFavoriteByUser(userId);
    }
}
