package datn.custom.dto.report;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthReportDto implements MonthReportInterface {
    String year;
    String month;
    Long total;

    public MonthReportDto(String year, String month, Long total) {
        this.year = year;
        this.month = month;
        this.total = total;
    }
}
