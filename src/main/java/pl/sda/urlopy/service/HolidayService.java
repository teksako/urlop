package pl.sda.urlopy.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.urlopy.assembler.HolidayAssembler;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.model.Holiday;
import pl.sda.urlopy.repository.HolidayRepository;
import pl.sda.urlopy.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class HolidayService {
    private final HolidayRepository holidayRepository;
    private final HolidayAssembler holidayAssembler;
    private final UserRepository userRepository;

    public Long save(HolidayDto holidayDto) {
        Holiday holiday = holidayAssembler.toEntity(holidayDto);
        Holiday savedHoliday = holidayRepository.save(holiday);
        return savedHoliday.getId();
    }

    public List<Holiday> findAll() {
        return holidayRepository.findAll();
    }

    //public  List<Holiday> findAllByActualUser(){ return holidayRepository.findAllByActualLoggedUser();}

    public void acceptHoliday(HolidayDto holidayDto) {
        if(Accept)
        Optional<Holiday> holiday = holidayRepository.findById(holidayDto.getId());
        holiday.get().setAccepted(holidayDto.getAccepted());
        holidayRepository.save(holiday.get());
    }




}
