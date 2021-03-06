package datn.service;

import datn.base.BaseService;
import datn.entity.CountryEntity;
import datn.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService extends BaseService<CountryEntity, CountryRepository> {
    public List<CountryEntity> findAll(){
        return repository.findByOrderByNameAsc();
    }
}
