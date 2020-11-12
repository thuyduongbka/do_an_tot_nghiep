package datn.service;

import datn.base.BaseService;
import datn.entity.CommentEntity;
import datn.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CommentService extends BaseService<CommentEntity, CommentRepository> {
    @Autowired
    private ScholarshipInteractiveService interactiveService;
    
    @Transactional
    public CommentEntity create(Long userId, Long scholarshipId, String message){
        interactiveService.countComment(userId,scholarshipId);
        CommentEntity commentEntity = new CommentEntity(userId,scholarshipId,message);
        return save(commentEntity);
    }
    public CommentEntity delete(Long id){
        return delete(id);
    }
    public List<CommentEntity> getByScholarshipId(Long scholarshipId){
        return repository.findByScholarshipId(scholarshipId);
    }
}
