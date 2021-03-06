package pl.sda.urlopy.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.sda.urlopy.assembler.HolidayAssembler;
import pl.sda.urlopy.assembler.UserAssembler;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.dto.UserDto;
import pl.sda.urlopy.model.Holiday;
import pl.sda.urlopy.model.User;
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


    public Long save(HolidayDto holidayDto) {
        Holiday holiday = holidayAssembler.toEntity(holidayDto);
        Holiday savedHoliday = holidayRepository.save(holiday);
        return savedHoliday.getId();
    }

    public List<Holiday> findAllByAcceptedIs() {

        return holidayRepository.findAllByAcceptedIs("BRAK DECYZJI");
    }

    public List<Holiday> findAllByActualLoggedUserIs(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String owner = principal.getUsername();
        //User user = userAssembler.toEntity(userDto).
        //model.addAttribute("owner", principal.getUsername());
        return holidayRepository.findAllByActualLoggedUserIs(owner);
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
