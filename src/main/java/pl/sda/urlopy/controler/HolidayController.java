package pl.sda.urlopy.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HolidayController {
    @GetMapping({"/holiday"})
    public String holidayPage(){
        return "holiday";
    }
}
