package datn.repository;

import datn.base.BaseRepository;
import datn.entity.LevelEntity;
import datn.entity.MajorEntity;
import datn.entity.ScholarshipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScholarshipRepository extends BaseRepository<ScholarshipEntity> {
    
    @Query(value = "select distinct s from ScholarshipEntity as s , in(s.majorEntities) as major, in(s.levelEntities) as level " +
            " where ( s.schoolEntity.id in :listSchoolId ) " +
            " and ( s.countryEntity.id in :listCountryId ) " +
            " and (:majorId is null or :majorId = major.id ) " +
            " and (:levelName is null or level.name like concat('%',:levelName,'%') ) " +
            " and (:dueDate is null or :dueDate BETWEEN CURRENT_DATE AND s.time ) ")
    Page<ScholarshipEntity> findAll(@Param("listSchoolId") List<Long> listSchoolId,
                                    @Param("listCountryId") List<Long> listCountryId,
                                    @Param("majorId") Long majorId,
                                    @Param("levelName") String levelName,
                                    @Param("dueDate")Date dueDate, Pageable pageable);
    
    @Query(value = "select distinct s from ScholarshipEntity as s , in(s.majorEntities) as major, in(s.levelEntities) as level " +
            " where ( s.schoolEntity.id in :listSchoolId ) " +
            " and (:majorId is null or :majorId = major.id ) " +
            " and (:levelName is null or level.name like concat('%',:levelName,'%') ) " +
            " and (:dueDate is null or :dueDate BETWEEN CURRENT_DATE AND s.time ) ")
    Page<ScholarshipEntity> findAllByListSchool(@Param("listSchoolId") List<Long> listSchoolId,
                                    @Param("majorId") Long majorId,
                                    @Param("levelName") String levelName,
                                    @Param("dueDate")Date dueDate, Pageable pageable);
    @Query(value = "select distinct s from ScholarshipEntity as s , in(s.majorEntities) as major, in(s.levelEntities) as level " +
            " where ( s.countryEntity.id in :listCountryId ) " +
            " and (:majorId is null or :majorId = major.id ) " +
            " and (:levelName is null or level.name like concat('%',:levelName,'%') ) " +
            " and (:dueDate is null or :dueDate BETWEEN CURRENT_DATE AND s.time ) ")
    Page<ScholarshipEntity> findAllByListCountry(@Param("listCountryId") List<Long> listCountryId,
                                    @Param("majorId") Long majorId,
                                    @Param("levelName") String levelName,
                                    @Param("dueDate")Date dueDate, Pageable pageable);
    
    @Query(value = "select distinct s from ScholarshipEntity as s , in(s.majorEntities) as major, in(s.levelEntities) as level " +
            " where (:majorId is null or :majorId = major.id ) " +
            " and (:levelName is null or level.name like concat('%',:levelName,'%') ) " +
            " and (:dueDate is null or :dueDate BETWEEN CURRENT_DATE AND s.time ) ")
    Page<ScholarshipEntity> findAll(@Param("majorId") Long majorId,
                                    @Param("levelName") String levelName,
                                    @Param("dueDate")Date dueDate, Pageable pageable);
}
