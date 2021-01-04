package pl.sda.urlopy.assembler;

import org.springframework.stereotype.Component;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.model.Holiday;

@Component
public class HolidayAssembler {

    public Holiday toEntity(HolidayDto holidayDto) {
        Holiday entity = new Holiday();
        entity.setStartDate(holidayDto.getStartDate());
        entity.setEndDate(holidayDto.getEndDate());
        entity.setCountOfDays(holidayDto.getCountOfDays());
        entity.setAccepted(false);
        return entity;
    }
}
