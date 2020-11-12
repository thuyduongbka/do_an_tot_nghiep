package datn.repository;

import datn.base.BaseRepository;
import datn.entity.MajorEntity;

import java.util.List;

public interface MajorRepository extends BaseRepository<MajorEntity> {
    List<MajorEntity> findByParentIdNotNull();
}
