package datn.service;

import datn.base.BaseService;
import datn.entity.MajorEntity;
import datn.repository.MajorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MajorService extends BaseService<MajorEntity, MajorRepository> {
    public List<MajorEntity> findAllChild(){
        return repository.findByParentIdNull();
    }

    @Transactional
    public MajorEntity updateParent(Long majorId,Long parentId){
        MajorEntity child = findById(majorId);
        MajorEntity parent = findById(parentId);
        child.setParentId(parentId);
        child.setNote(child.getNote() + parentId + ",");
        parent.setChild(parent.getChild() + majorId + ",");
        update(parent);
        return update(child);
    }
    @Transactional
    public MajorEntity deleteParent(Long majorId,Long parentId){
        MajorEntity child = findById(majorId);
        MajorEntity parent = findById(parentId);

        child.setParentId(null);
        child.setNote(child.getNote().replace(parentId + ",", ""));
        parent.setNote(parent.getChild().replace(majorId + ",", ""));

        return update(child);
    }
    public List<MajorEntity> findByParent(Long parentId){
        return repository.findByParentId(parentId);
    }
}
