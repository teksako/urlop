package pl.sda.urlopy.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.urlopy.assembler.HolidayAssembler;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.model.Holiday;
import pl.sda.urlopy.repository.HolidayRepository;

@Service
@RequiredArgsConstructor
public class HolidayService {
    private final HolidayRepository holidayRepository;
    private final HolidayAssembler holidayAssembler;

    public Long save(HolidayDto holidayDto) {
        Holiday holiday = holidayAssembler.toEntity(holidayDto);
        Holiday savedHoliday = holidayRepository.save(holiday);
        return savedHoliday.getId();
    }
}
