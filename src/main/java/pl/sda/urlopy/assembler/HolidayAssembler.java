package pl.sda.urlopy.assembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.Holiday;

@Component
public class HolidayAssembler {

private UserDto userDto;

    public Holiday toEntity(HolidayDto holidayDto) {
        Holiday entity = new Holiday();
        entity.setStartDate(holidayDto.getStartDate());
        entity.setEndDate(holidayDto.getEndDate());
        entity.setCountOfDays(holidayDto.getCountOfDays());
        entity.setAccepted(holidayDto.getAccepted());
        entity.setReplacement(holidayDto.getReplacement());
        //entity.setId(userDto.getId().longValue());
        return entity;
    }
}
