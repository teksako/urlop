package pl.sda.urlopy.controler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.urlopy.dto.HolidayDto;
import pl.sda.urlopy.model.Department;
import pl.sda.urlopy.model.Holiday;
import pl.sda.urlopy.repository.HolidayRepository;
import pl.sda.urlopy.service.HolidayService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RaportOfHoliday {
    private final HolidayService holidayService;

    @GetMapping({"/raport"})
    public String raportPage(Model model) {
        List<Holiday> holidays = holidayService.findAll();
        model.addAttribute("holidays", holidays);
        return "raportofholiday";
    }


}
