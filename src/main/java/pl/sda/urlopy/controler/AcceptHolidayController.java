package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.model.Holiday;
import pl.sda.urlopy.service.HolidayService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AcceptHolidayController {

    private final HolidayService holidayService;

    @GetMapping({"/acceptholiday"})
    public String acceptHolidayPage(Model model){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", principal.getUsername());
        model.addAttribute("role", principal.getAuthorities());
        List<Holiday> holidays = holidayService.findAll();
        model.addAttribute("holidays", holidays);
    return "acceptholiday";
    }

    @PostMapping("/acceptholiday")
    public String saveAcceptHoliday(@ModelAttribute("holiday") HolidayDto holiday, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "holiday";
        }
        holidayService.save(holiday);
        return "index";
    }
}
