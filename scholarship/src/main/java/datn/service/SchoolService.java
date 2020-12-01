package datn.service;

import datn.base.BaseService;
import datn.entity.SchoolEntity;
import datn.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService extends BaseService<SchoolEntity, SchoolRepository> {
    public List<SchoolEntity> findByCountryId(List<Long> listCountryId){
        return repository.findByCountryIdIn(listCountryId);
    }
}
