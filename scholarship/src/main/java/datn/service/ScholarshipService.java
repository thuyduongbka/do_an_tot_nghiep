package datn.service;

import datn.base.BaseService;
import datn.custom.domain.Scholarship;
import datn.custom.dto.ScholarshipFilterDto;
import datn.custom.dto.ScholarshipNameDto;
import datn.custom.dto.UserScholarshipDto;
import datn.custom.dto.report.CountryReportDto;
import datn.custom.dto.report.CountryReportInterface;
import datn.custom.dto.report.MonthReportDto;
import datn.custom.dto.report.MonthReportInterface;
import datn.entity.*;
import datn.repository.ScholarshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ScholarshipService extends BaseService<ScholarshipEntity, ScholarshipRepository> {
    @Autowired
    private MajorService majorService;
    
    @Autowired
    private LevelService levelService;
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private ScholarshipInteractiveService interactiveService;

    @Autowired
    private RecommendService recommendService;
    
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
    
    @Transactional
    public void countView(Long scholarshipId){
        repository.increaseNumberSeen(scholarshipId);
    }
    
    public UserScholarshipDto findScholarshipById(Long userId, Long scholarshipId){
        ScholarshipEntity scholarshipEntity = findById(scholarshipId);
        List<CommentEntity> commentEntities = commentService.getByScholarshipId(scholarshipId);
        scholarshipEntity.setCommentEntities(commentEntities);
        ScholarshipInteractiveEntity interactiveEntity =  interactiveService.findByScholarshipIdAndUserId(scholarshipId,userId);
        return new UserScholarshipDto(interactiveEntity, scholarshipEntity);
    }

    public List<ScholarshipEntity> getRecommendSame(Long scholarshipId){
        return recommendService.getRecommendByScholarship(scholarshipId);
    }

    public List<ScholarshipNameDto> findAllName(){
        List<ScholarshipEntity> scholarshipEntities = repository.findByIsExpiredIsFalseAndIsDeletedFalse();
        return scholarshipEntities.stream()
                .map(scholarshipEntity -> new ScholarshipNameDto(scholarshipEntity))
                .collect(Collectors.toList());
    }
    public void changeRating(Long scholarshipId, Float rating){
        repository.changeRating(scholarshipId, rating);
    }
    public void changeNumberRating(Long scholarshipId, Long numberRating){
        repository.changeNumberRating(scholarshipId, numberRating);
    }
    public int increaseNumberComment(Long scholarshipId){
        return repository.increaseNumberComment(scholarshipId);
    }
    public void increaseNumberShare(Long scholarshipId){
        repository.increaseNumberShare(scholarshipId);
    }

    public List<ScholarshipEntity> findByCountryId(Long countryId){
        return repository.findByCountryId(countryId);
    }
    public List<ScholarshipEntity> findByMajorId(Long majorId){
        return repository.findByMajorId(majorId);
    }
    public List<ScholarshipEntity> findByLevelName(String name){
        return repository.findByLevelName(name);
    }

    public List<ScholarshipEntity> findScholarshipMostViews(){
        return repository.findMostViews();
    }

    public List<MonthReportInterface> getTotalEveryMonth(){
        return repository.getTotalEveryMonth();
    }
    public List<CountryReportInterface> getTotalByCountry(){
        return repository.getTotalByCountry();
    }
}
