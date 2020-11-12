package datn.repository;

import datn.base.BaseRepository;
import datn.entity.ScholarshipInteractiveEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ScholarshipInteractiveRepository extends BaseRepository<ScholarshipInteractiveEntity> {
    
    @Modifying
    @Query(value = "UPDATE scholarship_interactive SET number_seen = number_seen + 1 WHERE scholarship_id = ?1 and user_id = ?2 ", nativeQuery = true)
    int increaseNumberSeen(Long scholarshipId, Long userId);
    
    @Modifying
    @Query(value = "UPDATE scholarship_interactive SET number_comment = number_comment + 1 WHERE scholarship_id = ?1 and user_id = ?2 ", nativeQuery = true)
    int increaseNumberComment(Long scholarshipId, Long userId);
    
    @Modifying
    @Query(value = "UPDATE scholarship_interactive SET number_click_contact = number_click_contact + 1 WHERE scholarship_id = ?1 and user_id = ?2 ", nativeQuery = true)
    int increaseNumberClickContact(Long scholarshipId, Long userId);
    
    @Modifying
    @Query(value = "UPDATE scholarship_interactive SET number_compare = number_compare + 1 WHERE scholarship_id = ?1 and user_id = ?2 ", nativeQuery = true)
    int increaseNumberCompare(Long scholarshipId, Long userId);
    
    @Modifying
    @Query(value = "UPDATE scholarship_interactive SET rating = ?3 WHERE scholarship_id = ?1 and user_id = ?2 ", nativeQuery = true)
    int changeRating(Long scholarshipId, Long userId, Integer rating);
    
    @Modifying
    @Query(value = "UPDATE scholarship_interactive SET is_liked = ?3 WHERE scholarship_id = ?1 and user_id = ?2 ", nativeQuery = true)
    void changeLike(Long scholarshipId, Long userId, Boolean isLiked);
    
    @Modifying
    @Query(value = "UPDATE scholarship_interactive SET is_in_list_favorite = ?3 WHERE scholarship_id = ?1 and user_id = ?2 ", nativeQuery = true)
    void changeFavorite(Long scholarshipId, Long userId, Boolean isInListFavorite);
    
    @Query(value = "select * from scholarship_interactive where scholarship_id = ?1 and user_id = ?2 ", nativeQuery = true)
    ScholarshipInteractiveEntity findByScholarshipIdAndUserId(Long scholarshipId, Long userId);
}
