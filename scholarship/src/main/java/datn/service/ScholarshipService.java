package datn.service;

import datn.base.BaseService;
import datn.custom.dto.ScholarshipFilterDto;
import datn.entity.LevelEntity;
import datn.entity.MajorEntity;
import datn.entity.ScholarshipEntity;
import datn.repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScholarshipService extends BaseService<ScholarshipEntity, ScholarshipRepository> {
    @Autowired
    private MajorService majorService;
    
    @Autowired
    private LevelService levelService;
    
    public Page<ScholarshipEntity> getAll(ScholarshipFilterDto dto, PageRequest page){
        List<Long> listSchoolId = dto.getListSchoolId();
        List<Long> listCountryId = dto.getListCountryId();
        
        if (listSchoolId.size() == 0 && listCountryId.size() == 0){
            return repository.findAll(dto.getMajorId(), dto.getLevelName(), dto.getDueDate(), page);
        }
        if (listCountryId.size() == 0){
            return repository.findAllByListSchool(listSchoolId, dto.getMajorId(), dto.getLevelName(), dto.getDueDate(), page);
        }
        if (listSchoolId.size() == 0){
            return repository.findAllByListCountry(listCountryId, dto.getMajorId(), dto.getLevelName(), dto.getDueDate(), page);
        }
        return repository.findAll(listSchoolId, listCountryId, dto.getMajorId(), dto.getLevelName(), dto.getDueDate(), page);
    }
}
