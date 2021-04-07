package pl.sda.urlopy.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.sda.urlopy.assembler.HolidayAssembler;
import pl.sda.urlopy.assembler.UserAssembler;
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
    private final UserAssembler userAssembler;
    private final UserService userService;



    public Long save(HolidayDto holidayDto) {
        Holiday holiday = holidayAssembler.toEntity(holidayDto);
        Holiday savedHoliday = holidayRepository.save(holiday);
        return savedHoliday.getId();
    }

    public List<Holiday> findAllByAcceptedIsAndActualLoggedUserIs() {
        return holidayRepository.findAllByAcceptedIsAndActualLoggedUserIsNot("BRAK DECYZJI", userService.actualLoginUser());
    }

    public Long count(String accept) {
        return holidayRepository.countHolidayByAcceptedIsAndActualLoggedUserIs(accept, userService.actualLoginUser());
    }

    public Long countAll(){
        return holidayRepository.countAllByActualLoggedUserIs(userService.actualLoginUser());
    }


    public  Long allDays(){
    long zeroDays=0;
    if(holidayRepository.allDays(userService.actualLoginUser())==null){
        return zeroDays;
    }
        return holidayRepository.allDays(userService.actualLoginUser());
    }

    public List<Holiday> findAllByActualLoggedUserIs() {
        return holidayRepository.findAllByActualLoggedUserIs(userService.actualLoginUser());
    }


    public List<Holiday> findAll() {
        return holidayRepository.findAll();
    }

    //public  List<Holiday> findAllByActualUser(){ return holidayRepository.findAllByActualLoggedUser();}

    public void acceptHoliday(HolidayDto holidayDto) {
        Optional<Holiday> holiday = holidayRepository.findById(holidayDto.getId());
        holiday.get().setAccepted(holidayDto.getAccepted());
        holidayRepository.save(holiday.get());
    }


}
