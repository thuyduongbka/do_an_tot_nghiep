package datn.custom.dto.report;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryReportDto {
    String name;
    Long total;

    public CountryReportDto(String name, Long total) {
        this.name = name;
        this.total = total;
    }
}
