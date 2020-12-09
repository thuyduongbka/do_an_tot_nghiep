package datn.repository;

import datn.base.BaseRepository;
import datn.custom.dto.report.CountryReportDto;
import datn.custom.dto.report.CountryReportInterface;
import datn.custom.dto.report.MonthReportDto;
import datn.custom.dto.report.MonthReportInterface;
import datn.entity.LevelEntity;
import datn.entity.MajorEntity;
import datn.entity.ScholarshipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
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
            " and (:dueDate is null or (s.time >= NOW() and s.time <= :dueDate ) ) ")
    Page<ScholarshipEntity> findAll(@Param("listSchoolId") List<Long> listSchoolId,
                                    @Param("listCountryId") List<Long> listCountryId,
                                    @Param("majorId") Long majorId,
                                    @Param("levelName") String levelName,
                                    @Param("dueDate")Date dueDate, Pageable pageable);
    
    @Query(value = "select distinct s from ScholarshipEntity as s , in(s.majorEntities) as major, in(s.levelEntities) as level " +
            " where ( s.schoolEntity.id in :listSchoolId ) " +
            " and (:majorId is null or :majorId = major.id ) " +
            " and (:levelName is null or level.name like concat('%',:levelName,'%') ) " +
            " and (:dueDate is null or (s.time >= NOW() and s.time <= :dueDate ) ) ")
    Page<ScholarshipEntity> findAllByListSchool(@Param("listSchoolId") List<Long> listSchoolId,
                                    @Param("majorId") Long majorId,
                                    @Param("levelName") String levelName,
                                    @Param("dueDate")Date dueDate, Pageable pageable);
    @Query(value = "select distinct s from ScholarshipEntity as s , in(s.majorEntities) as major, in(s.levelEntities) as level " +
            " where ( s.countryEntity.id in :listCountryId ) " +
            " and (:majorId is null or :majorId = major.id ) " +
            " and (:levelName is null or level.name like concat('%',:levelName,'%') ) " +
            " and (:dueDate is null or (s.time >= NOW() and s.time <= :dueDate ) ) ")
    Page<ScholarshipEntity> findAllByListCountry(@Param("listCountryId") List<Long> listCountryId,
                                    @Param("majorId") Long majorId,
                                    @Param("levelName") String levelName,
                                    @Param("dueDate")Date dueDate, Pageable pageable);
    
    @Query(value = "select distinct s from ScholarshipEntity as s , in(s.majorEntities) as major, in(s.levelEntities) as level " +
            " where (:majorId is null or :majorId = major.id ) " +
            " and (:levelName is null or level.name like concat('%',:levelName,'%') ) " +
            " and (:dueDate is null or (s.time >= NOW() and s.time <= :dueDate )) ")
    Page<ScholarshipEntity> findAll(@Param("majorId") Long majorId,
                                    @Param("levelName") String levelName,
                                    @Param("dueDate")Date dueDate, Pageable pageable);
    
    @Modifying
    @Query(value = "UPDATE scholarship SET number_seen = number_seen + 1 WHERE id = ?1 ", nativeQuery = true)
    int increaseNumberSeen(Long scholarshipId);

    List<ScholarshipEntity> findByIsExpiredIsFalseAndIsDeletedFalse();

    @Modifying
    @Query(value = "UPDATE scholarship SET rating = ?2 WHERE id = ?1 ", nativeQuery = true)
    int changeRating(Long scholarshipId, Float rating);
    @Modifying
    @Query(value = "UPDATE scholarship SET number_rating = ?2 WHERE id = ?1 ", nativeQuery = true)
    int changeNumberRating(Long scholarshipId, Long numberRating);
    @Modifying
    @Query(value = "UPDATE scholarship SET number_comment = number_comment + 1 WHERE id = ?1 ", nativeQuery = true)
    int increaseNumberComment(Long scholarshipId);
    @Modifying
    @Query(value = "UPDATE scholarship SET number_share = number_share + 1 WHERE id = ?1 ", nativeQuery = true)
    int increaseNumberShare(Long scholarshipId);

    @Query(value = "select distinct s from ScholarshipEntity as s , in(s.countryEntity) as country " +
            " where country.id = ?1 and s.time >= current_date ")
    List<ScholarshipEntity> findByCountryId(Long countryId);

    @Query(value = "select distinct s from ScholarshipEntity as s , in(s.majorEntities) as major " +
            " where major.id = ?1 and s.time >= current_date ")
    List<ScholarshipEntity> findByMajorId(Long majorId);

    @Query(value = "select distinct s from ScholarshipEntity as s , in(s.levelEntities) as level " +
            " where level.name = ?1 and s.time >= current_date ")
    List<ScholarshipEntity> findByLevelName(String levelName);

    @Query(value = "select * from scholarship as s order by number_seen desc limit 6", nativeQuery = true)
    List<ScholarshipEntity> findMostViews();

    @Query(value = "SELECT Year(`time`) as year, Month(`time`) as month, Count(*) As total FROM scholarship WHERE time >= now() GROUP BY Year(`time`), Month(`time`)", nativeQuery = true)
    List<MonthReportInterface> getTotalEveryMonth();
    @Query(value = "SELECT c.name as name, COUNT(*) AS total FROM scholarship s join country c on c.id=s.country_id WHERE TIME >= NOW() GROUP BY country_id", nativeQuery = true)
    List<CountryReportInterface> getTotalByCountry();
}
