package datn.custom.dto;

import datn.entity.ScholarshipEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScholarshipNameDto {
    Long id;
    String name;
    public ScholarshipNameDto(ScholarshipEntity entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
