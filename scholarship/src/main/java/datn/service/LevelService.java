package datn.service;

import datn.base.BaseService;
import datn.entity.LevelEntity;
import datn.repository.LevelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService extends BaseService<LevelEntity, LevelRepository> {
    public List<String> getAllLevel(){
        return repository.findDistinctName();
    }
}
