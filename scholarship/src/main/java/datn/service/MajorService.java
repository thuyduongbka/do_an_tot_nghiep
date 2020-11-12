package datn.service;

import datn.base.BaseService;
import datn.entity.MajorEntity;
import datn.repository.MajorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MajorService extends BaseService<MajorEntity, MajorRepository> {
    public List<MajorEntity> findAllChild(){
        return repository.findByParentIdNotNull();
    }
}
