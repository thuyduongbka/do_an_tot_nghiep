package datn.repository;

import datn.base.BaseRepository;
import datn.entity.MajorEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MajorRepository extends BaseRepository<MajorEntity> {
    List<MajorEntity> findByParentIdNotNull();
    List<MajorEntity> findByParentIdNull();
    List<MajorEntity> findByOrderByNameAsc();

    @Query(value = "select m from MajorEntity m where m.parentId is null ")
    List<MajorEntity> findAllChild(Long level);

    List<MajorEntity> findByParentId(Long parent_id);

    @Modifying
    @Query(value = "UPDATE major SET child = concat(child,?2) WHERE id = ?1 ", nativeQuery = true)
    int increaseChildMajor(Long majorId, String child);

    @Modifying
    @Query(value = "UPDATE major SET child = REPLACE(child,?2, '') WHERE id = ?1 ", nativeQuery = true)
    int decreaseChildMajor(Long majorId, String child);

}
