package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.service.HolidayService;


@Controller
@RequiredArgsConstructor
public class HolidayController {
    private final HolidayService holidayService;

    @GetMapping({"/holiday"})
    public String holidayPage(Model model){
        model.addAttribute("holiday", new HolidayDto());
        return "holiday";
    }

    @PostMapping("/holiday")
    public String saveHoliday(@ModelAttribute("holiday") HolidayDto holiday, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "holiday";
        }
        holidayService.save(holiday);
        return "login";
    }

}
