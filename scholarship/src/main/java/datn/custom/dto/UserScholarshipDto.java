package datn.custom.dto;

import datn.entity.ScholarshipEntity;
import datn.entity.ScholarshipInteractiveEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserScholarshipDto {
    private ScholarshipInteractiveEntity interactiveEntity;
    private ScholarshipEntity scholarshipEntity;
    
    public UserScholarshipDto(ScholarshipInteractiveEntity interactiveEntity,
                              ScholarshipEntity scholarshipEntity ) {
        this.interactiveEntity = interactiveEntity;
        this.scholarshipEntity = scholarshipEntity;
    }
}
