package datn.service;

import datn.base.BaseService;
import datn.entity.ScholarshipEntity;
import datn.repository.ScholarshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScholarshipService extends BaseService<ScholarshipEntity, ScholarshipRepository> {
    
    public List<ScholarshipEntity> getAll(){
        return repository.findAll();
    }
    
}
