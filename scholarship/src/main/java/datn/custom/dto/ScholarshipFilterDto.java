package datn.custom.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ScholarshipFilterDto {
    private List<Long> listSchoolId;
    private List<Long> listCountryId;
    private Long majorId;
    private String levelName;
    
    private Date dueDate;
    
}
