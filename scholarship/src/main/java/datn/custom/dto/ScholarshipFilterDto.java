package datn.custom.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date dueDate;
    
}
