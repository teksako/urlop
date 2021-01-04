package pl.sda.urlopy.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class HolidayDto {
    private Long id;
    private Date startDate;
    private Date endDate;
    private Long countOfDays;
    private boolean accepted;
}
