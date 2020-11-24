package datn.repository;

import datn.base.BaseRepository;
import datn.entity.MajorEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MajorRepository extends BaseRepository<MajorEntity> {
    List<MajorEntity> findByParentIdNotNull();
    List<MajorEntity> findByParentIdNull();

    @Query(value = "select m from MajorEntity m where m.parentId is null ")
    List<MajorEntity> findAllChild(Long level);

    List<MajorEntity> findByParentId(Long parent_id);

}
