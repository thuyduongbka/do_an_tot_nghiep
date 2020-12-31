package datn.custom.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ConversationDto {
    private Long scholarshipId;
    private Boolean countryDislike;
    private List<String> listCountryLike;
    private Boolean schoolDislike;
    private List<String> listSchoolLike;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private Date appropriateTime;
    private List<String> listLevelLike;
    private List<String> listLevelDislike;
    private List<String> listMajorLike;
    private List<String> listMajorDislike;

}
