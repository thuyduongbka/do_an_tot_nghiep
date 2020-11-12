package datn.repository;

import datn.base.BaseRepository;
import datn.entity.CommentEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends BaseRepository<CommentEntity> {
    List<CommentEntity> findByUserIdAndScholarshipIdAndIsDeleted(Long userId, Long scholarshipId, Boolean isDeleted);
    
    @Query(value = "select c from CommentEntity c where c.scholarshipId = ?1 and c.isDeleted = false order by c.createdTime asc ")
    List<CommentEntity> findByScholarshipId(Long scholarshipId);
}
