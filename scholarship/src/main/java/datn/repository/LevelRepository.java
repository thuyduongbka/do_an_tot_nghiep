package datn.repository;

import datn.base.BaseRepository;
import datn.entity.LevelEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LevelRepository extends BaseRepository<LevelEntity> {
    
    @Query(value = "select distinct l.name from LevelEntity as l")
    List<String> findDistinctName();
}
