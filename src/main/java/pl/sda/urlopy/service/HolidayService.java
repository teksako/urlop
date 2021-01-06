package pl.sda.urlopy.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.urlopy.assembler.HolidayAssembler;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.model.Holiday;
import pl.sda.urlopy.repository.HolidayRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class HolidayService {
    private final HolidayRepository holidayRepository;
    private final HolidayAssembler holidayAssembler;

    public Long save(HolidayDto holidayDto) {
        Holiday holiday = holidayAssembler.toEntity(holidayDto);
        Holiday savedHoliday = holidayRepository.save(holiday);
        return savedHoliday.getId();
    }

    public List<Holiday> findAll(){
        return holidayRepository.findAll();
    }
}
